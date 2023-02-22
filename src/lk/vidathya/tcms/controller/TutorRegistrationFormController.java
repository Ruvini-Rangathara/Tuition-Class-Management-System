package lk.vidathya.tcms.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.StaffModel;
import lk.vidathya.tcms.model.TutorModel;
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

public class TutorRegistrationFormController {

    public AnchorPane context;
    public Button btnBack;
    public TextField txtSubject;
    public ImageView tutorImage;
    public Button btnBrowseImage;
    @FXML
    private Button btnRegister;

    @FXML
    private Label lblTutorId;

    @FXML
    private Label lblDate;

    @FXML
    private Button btnCancel;

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
        setTutorId();
        lblDate.setText(String.valueOf(LocalDate.now()));

        DataLoad.loadGenderOptions((ComboBox<String>) cmbGender);

        setLabelVisibility();

    }

    private void setTutorId() {
        try {
            String lastTutorId= TutorModel.getLastTutorId();
            if(lastTutorId==null){
                lblTutorId.setText("T0001");
            }else{
                String[] split=lastTutorId.split("[T]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newTutorId=String.format("T%04d", lastDigits);
                lblTutorId.setText(newTutorId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.TUTORREGISTRATION,context);
    }

    private void setLabelVisibility(){

        lblInvalidTutorName.setVisible(false);
        lblInvalidTutorNic.setVisible(false);
        lblInvalidTutorContactNo.setVisible(false);
        lblInvalidTutorEmail.setVisible(false);
        lblInvalidTutorDob.setVisible(false);
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        setLabelVisibility();

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


                            Tutor tutor = new Tutor(
                                    lblTutorId.getText(),
                                    txtName.getText(),
                                    txtNic.getText(),
                                    String.valueOf(cmbGender.getValue()),
                                    txtAddress.getText(),
                                    txtContactNo.getText(),
                                    txtEmail.getText(),
                                    txtDob.getText(),
                                    txtSubject.getText(),
                                    lblDate.getText()
                            );

                            try {
                                boolean isAdded = TutorModel.addTutor(tutor);
                                if(isAdded){
                                    InstituteData.setTutorCount();
                                    Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Tutor Added Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                                    if(choose.get()==ButtonType.OK){
                                        try {
                                            Navigation.navigate(Routes.TUTORREGISTRATION,context);
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

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    public void txtSubjectOnAction(ActionEvent actionEvent) {

    }

    public void btnBrowseImageOnAction(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        //fileChooser.setInitialDirectory(new File("C:\\Users\\Lenovo\\Desktop\\tcms"));
        fileChooser.setInitialDirectory(new File("..\\tcms-2.0\\TutorImages"));
        try {

            boolean isExist = TutorModel.isExistImage(lblTutorId.getText());
            if(!isExist){
                String sql = "INSERT INTO TutorImage VALUES (?, ?)";
                PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

                File file = fileChooser.showOpenDialog(new Stage());

                if(file!=null){
                    FileInputStream fis = new FileInputStream(file);
                    pstm.setString(1,lblTutorId.getText());
                    pstm.setBinaryStream(2,fis,fis.available());
                    int x = pstm.executeUpdate();

                    if(x>0){
                        JOptionPane.showMessageDialog(null, "Image SuccessFully Added!");

                        ResultSet resultSet = TutorModel.getImage(lblTutorId.getText());
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
                    pstm.setString(2,lblTutorId.getText());
                    pstm.setBinaryStream(1,fis,fis.available());
                    int x = pstm.executeUpdate();

                    if(x>0){
                        JOptionPane.showMessageDialog(null, "Image Added SuccessFully!");

                        ResultSet resultSet = TutorModel.getImage(lblTutorId.getText());
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
