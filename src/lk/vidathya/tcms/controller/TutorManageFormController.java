package lk.vidathya.tcms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.model.StaffModel;
import lk.vidathya.tcms.model.TutorModel;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.*;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class TutorManageFormController {

    public Button btnCancel;
    public AnchorPane context;
    public TextField txtSubject;
    public ImageView tutorImage;
    public Button btnBrowseImage;


    @FXML
    private Label lblDate;


    @FXML
    private Label lblInvalidTutorName;

    @FXML
    private Label lblInvalidTutorNic;

    @FXML
    private Label lblInvalidTutorContactNo;

    @FXML
    private Label lblInvalidTutorEmail;

    @FXML
    private Label lblInvalidTutorDob;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblExit;

    @FXML
    private ComboBox<?> cmbTutorId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private ComboBox<?> cmbGender;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtDob;


    public void initialize(){
        lblDate.setText(String.valueOf(LocalDate.now()));
        DataLoad.loadTutorId((ComboBox<String>) cmbTutorId);
        DataLoad.loadGenderOptions((ComboBox<String>) cmbGender);

        lblInvalidTutorName.setVisible(false);
        lblInvalidTutorNic.setVisible(false);
        lblInvalidTutorContactNo.setVisible(false);
        lblInvalidTutorEmail.setVisible(false);
        lblInvalidTutorDob.setVisible(false);

    }

//    @FXML
//    void btnDeleteOnAction(ActionEvent event) {
//        try {
//            Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO).showAndWait();
//            if(choose.get()==ButtonType.YES){
//                boolean isDelete = TutorModel.deleteTutor(String.valueOf(cmbTutorId.getValue()));
//                if(isDelete){
//                    InstituteData.setTutorCount();
//                    new Alert(Alert.AlertType.CONFIRMATION,"Tutor Deleted SuccessFully!").show();
//                    Navigation.navigate(Routes.TUTORMANAGE,context);
//                }
//            }
//        } catch (SQLException | ClassNotFoundException | IOException e) {
//            new Alert(Alert.AlertType.ERROR,e+"").show();
//        }
//
//    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        lblInvalidTutorName.setVisible(false);
        lblInvalidTutorNic.setVisible(false);
        lblInvalidTutorContactNo.setVisible(false);
        lblInvalidTutorEmail.setVisible(false);
        lblInvalidTutorDob.setVisible(false);

        boolean isNameMatched = RegExPatterns.getNamePattern().matcher(txtName.getText()).matches();
        boolean isNicMatched = RegExPatterns.getIdPattern().matcher(txtNic.getText()).matches();
        boolean isOldNicMatched = RegExPatterns.getOldIDPattern().matcher(txtNic.getText()).matches();
        boolean isContactNoMatched = RegExPatterns.getMobilePattern().matcher(txtContactNo.getText()).matches();
        boolean isEmailMatched = RegExPatterns.getEmailPattern().matcher(txtEmail.getText()).matches();
        boolean isDobMatched = RegExPatterns.getDobPattern().matcher(txtDob.getText()).matches();

        if(isNameMatched){
            if(isNicMatched || isOldNicMatched){
                if(isContactNoMatched){
                    if(isEmailMatched){
                        if(isDobMatched){



                            String gender = null;
                            if(cmbGender.getValue()==null){
                                gender=cmbGender.getPromptText();
                            }else{
                                gender=String.valueOf(cmbGender.getValue());
                            }

                            Tutor tutor = new Tutor(
                                    String.valueOf(cmbTutorId.getValue()),
                                    txtName.getText(),
                                    txtNic.getText(),
                                    gender,
                                    txtAddress.getText(),
                                    txtContactNo.getText(),
                                    txtEmail.getText(),
                                    txtDob.getText(),
                                    txtSubject.getText(),
                                    lblDate.getText()
                            );

                            try {
                                boolean isUpdate =  TutorModel.updateTutor(tutor);
                                if(isUpdate){

                                    Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Tutor Updated Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                                    if(choose.get()==ButtonType.OK){
                                        try {
                                            Navigation.navigate(Routes.TUTORMANAGE,context);
                                        } catch (IOException e) {
                                            new Alert(Alert.AlertType.ERROR,e+"").show();
                                        }
                                    }

                                }


                            } catch (SQLException | ClassNotFoundException e) {
                                new Alert(Alert.AlertType.ERROR,e+"").show();
                            }


                        }else{
                            lblInvalidTutorDob.setVisible(true);
                            txtDob.requestFocus();
                        }
                    }else{
                        lblInvalidTutorEmail.setVisible(true);
                        txtEmail.requestFocus();
                    }
                }else{
                    lblInvalidTutorContactNo.setVisible(true);
                    txtContactNo.requestFocus();
                }
            }else{
                lblInvalidTutorNic.setVisible(true);
                txtNic.requestFocus();
            }
        }else{
            lblInvalidTutorName.setVisible(true);
            txtName.requestFocus();
        }

    }

    @FXML
    void cmbGenderOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTutorIdOnAction(ActionEvent event) {
        try {
            Tutor tutor = TutorModel.getTutorDetails((String) cmbTutorId.getValue());
            txtName.setText(tutor.getName());
            txtNic.setText(tutor.getNic());
            cmbGender.setPromptText(tutor.getGender());
            txtAddress.setText(tutor.getAddress());
            txtContactNo.setText(tutor.getContactNo());
            txtEmail.setText(tutor.getEmail());
            txtDob.setText(tutor.getDob());
            txtSubject.setText(tutor.getSubject());

            ResultSet resultSet = TutorModel.getImage(String.valueOf(cmbTutorId.getValue()));
            if(resultSet.next()){
                Image img = new Image(resultSet.getBinaryStream("image"));
                tutorImage.setImage(img);
                tutorImage.setPreserveRatio(false);
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtContactNoOnAction(ActionEvent event) {

    }

    @FXML
    void txtDobOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtNicOnAction(ActionEvent event) {

    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    public void txtSubjectOnAction(ActionEvent actionEvent) {

    }

    public void btnBrowseImageOnAction(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("..\\tcms-2.0\\TutorImages"));
        try {

            boolean isExist = TutorModel.isExistImage(String.valueOf(cmbTutorId.getValue()));
            if(!isExist){
                String sql = "INSERT INTO TutorImage VALUES (?, ?)";
                PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

                File file = fileChooser.showOpenDialog(new Stage());

                if(file!=null){
                    FileInputStream fis = new FileInputStream(file);
                    pstm.setString(1,String.valueOf(cmbTutorId.getValue()));
                    pstm.setBinaryStream(2,fis,fis.available());
                    int x = pstm.executeUpdate();

                    if(x>0){
                        JOptionPane.showMessageDialog(null, "Image SuccessFully Added!");

                        ResultSet resultSet = TutorModel.getImage(String.valueOf(cmbTutorId.getValue()));
                        if(resultSet.next()){
                            Image img = new Image(resultSet.getBinaryStream("image"));
                            tutorImage.setImage(img);
                            tutorImage.setPreserveRatio(false);
                        }
                    }

                }

            }else{
                String sql = "UPDATE TutorImage SET image=? WHERE tutorId=? ";
                PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

                fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(new Stage());

                if(file!=null){
                    FileInputStream fis = new FileInputStream(file);
                    pstm.setString(2,String.valueOf(cmbTutorId.getValue()));
                    pstm.setBinaryStream(1,fis,fis.available());
                    int x = pstm.executeUpdate();

                    if(x>0){
                        JOptionPane.showMessageDialog(null, "Image Added SuccessFully!");

                        ResultSet resultSet = TutorModel.getImage(String.valueOf(cmbTutorId.getValue()));
                        if(resultSet.next()){
                            Image img = new Image(resultSet.getBinaryStream("image"));
                            tutorImage.setImage(img);
                            tutorImage.setPreserveRatio(false);
                        }
                    }

                }
            }

        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
