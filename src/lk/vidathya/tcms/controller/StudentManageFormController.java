package lk.vidathya.tcms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.model.GuardianModel;
import lk.vidathya.tcms.model.StudentModel;
import lk.vidathya.tcms.transferObject.Guardian;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.RegExPatterns;
import lk.vidathya.tcms.util.Routes;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentManageFormController implements Initializable {

    public Button btnBack;
    public AnchorPane context;
    public ImageView studentImage;
    public Button btnBrowseImage;

    @FXML
    private Label lblDate;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblExit;

    @FXML
    private Label lblInvalidStudentName;

    @FXML
    private Label lblInvalidStudentNic;

    @FXML
    private Label lblInvalidStudentContactNo;

    @FXML
    private Label lblInvalidStudentEmail;

    @FXML
    private Label lblInvalidStudentDob;

    @FXML
    private Label lblInvalidGuardianName;

    @FXML
    private Label lblInvalidGuardianNic;

    @FXML
    private Label lblInvalidGuardianContactNo;

    @FXML
    private Label lblInvalidGuardianEmail;

    @FXML
    private ComboBox<?> cmbStudentGender;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtStudentAddress;

    @FXML
    private TextField txtStudentNic;

    @FXML
    private TextField txtStudentContactNo;

    @FXML
    private TextField txtStudentEmail;

    @FXML
    private TextField txtStudentDob;

    @FXML
    private TextField txtStudentAge;

    @FXML
    private ComboBox<?> cmbGrade;

    @FXML
    private TextField txtGuardianName;

    @FXML
    private TextField txtGuardianContactNo;

    @FXML
    private TextField txtGuardianEmail;

    @FXML
    private TextField txtOccupation;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtGuardianNic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblDate.setText(String.valueOf(LocalDate.now()));
        DataLoad.loadGenderOptions((ComboBox<String>) cmbStudentGender);
        DataLoad.loadGrade((ComboBox<String>) cmbGrade);

        txtGuardianNic.setEditable(false);

        setLabelVisibility();
    }

    private void setLabelVisibility(){
        lblInvalidStudentName.setVisible(false);
        lblInvalidStudentNic.setVisible(false);
        lblInvalidStudentContactNo.setVisible(false);
        lblInvalidStudentEmail.setVisible(false);
        lblInvalidStudentDob.setVisible(false);

        lblInvalidGuardianName.setVisible(false);
        lblInvalidGuardianNic.setVisible(false);
        lblInvalidGuardianContactNo.setVisible(false);
        lblInvalidGuardianEmail.setVisible(false);
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        setLabelVisibility();

        boolean isStudentNameMatched = RegExPatterns.getNamePattern().matcher(txtStudentName.getText()).matches();
        boolean isStudentNicMatched = RegExPatterns.getIdPattern().matcher(txtStudentNic.getText()).matches();
        boolean isStudentOldNicMatched = RegExPatterns.getOldIDPattern().matcher(txtStudentNic.getText()).matches();
        boolean isStudentContactNoMatched = RegExPatterns.getMobilePattern().matcher(txtStudentContactNo.getText()).matches();
        boolean isStudentEmailMatched = RegExPatterns.getEmailPattern().matcher(txtStudentEmail.getText()).matches();
        boolean isStudentDobMatched = RegExPatterns.getDobPattern().matcher(txtStudentDob.getText()).matches();

        boolean isGuardianNameMatched = RegExPatterns.getNamePattern().matcher(txtGuardianName.getText()).matches();
        boolean isGuardianNicMatched = RegExPatterns.getIdPattern().matcher(txtGuardianNic.getText()).matches();
        boolean isGuardianOldNicMatched = RegExPatterns.getOldIDPattern().matcher(txtGuardianNic.getText()).matches();
        boolean isGuardianContactNoMatched = RegExPatterns.getMobilePattern().matcher(txtGuardianContactNo.getText()).matches();
        boolean isGuardianEmailMatched = RegExPatterns.getEmailPattern().matcher(txtGuardianEmail.getText()).matches();

        if(isStudentNameMatched){
            if(isStudentNicMatched || isStudentOldNicMatched){
                if(isStudentContactNoMatched){
                    if(isStudentEmailMatched){
                        if(isStudentDobMatched){
                            if(isGuardianNameMatched){
                                if(isGuardianNicMatched || isGuardianOldNicMatched){
                                    if(isGuardianContactNoMatched){
                                        if(isGuardianEmailMatched){


                                            String gender = null;
                                            if(cmbStudentGender.getValue()==null){
                                                gender=String.valueOf(cmbStudentGender.getPromptText());
                                            }else{
                                                gender=String.valueOf(cmbStudentGender.getValue());
                                            }

                                            String grade = null;
                                            if(cmbGrade.getValue()==null){
                                                grade=String.valueOf(cmbGrade.getPromptText());
                                            }else{
                                                grade=String.valueOf(cmbGrade.getValue());
                                            }

                                            Student student = new Student(
                                                    txtStudentId.getText(),
                                                    txtStudentName.getText(),
                                                    txtStudentNic.getText(),
                                                    gender,
                                                    txtStudentAddress.getText(),
                                                    txtStudentContactNo.getText(),
                                                    txtStudentEmail.getText(),
                                                    txtStudentDob.getText(),
                                                    Integer.parseInt(txtStudentAge.getText()),
                                                    grade,
                                                    lblDate.getText(),
                                                    txtGuardianNic.getText()
                                            );

                                            Guardian guardian = new Guardian(
                                                    txtGuardianNic.getText(),
                                                    txtGuardianName.getText(),
                                                    txtGuardianContactNo.getText(),
                                                    txtGuardianEmail.getText(),
                                                    txtOccupation.getText()
                                            );

                                            try {
                                                boolean isUpdate = GuardianModel.updateGuardian(student,guardian);
                                                if(isUpdate){
                                                    Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                                                    if(choose.get()==ButtonType.OK){
                                                        Navigation.navigate(Routes.STUDENTMANAGE,context);
                                                    }

                                                }
                                            } catch (SQLException | ClassNotFoundException | IOException e) {
                                                throw new RuntimeException(e);
                                            }


                                        }else{
                                            lblInvalidGuardianEmail.setVisible(true);
                                            txtGuardianEmail.requestFocus();
                                        }
                                    }else{
                                        lblInvalidGuardianContactNo.setVisible(true);
                                        txtGuardianContactNo.requestFocus();
                                    }
                                }else{
                                    lblInvalidGuardianNic.setVisible(true);
                                    txtGuardianNic.requestFocus();
                                }
                            }else{
                                lblInvalidGuardianName.setVisible(true);
                                txtGuardianName.requestFocus();
                            }
                        }else{
                            lblInvalidStudentDob.setVisible(true);
                            txtStudentDob.requestFocus();
                        }
                    }else{
                        lblInvalidStudentEmail.setVisible(true);
                        txtStudentEmail.requestFocus();
                    }
                }else{
                    lblInvalidStudentContactNo.setVisible(true);
                    txtStudentContactNo.requestFocus();
                }
            }else{
                lblInvalidStudentNic.setVisible(true);
                txtStudentNic.requestFocus();
            }
        }else{
            lblInvalidStudentName.setVisible(true);
            txtStudentName.requestFocus();
        }

    }

    @FXML
    void cmbGradeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStudentGenderOnAction(ActionEvent event) {

    }


    @FXML
    void txtGuardianContactNoOnAction(ActionEvent event) {

    }

    @FXML
    void txtGuardianEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtGuardianNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtGuardianNicOnAction(ActionEvent event) {

    }

    @FXML
    void txtOccupationOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentAgeOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentContactNoOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentDobOnAction(ActionEvent event) {
        String date = String.valueOf(LocalDate.now());
        int nowYear = Integer.parseInt(date.split("-")[0]);

        String dob = txtStudentDob.getText();
        int birthYear = Integer.parseInt(dob.split("-")[0]);

        int age = nowYear-birthYear;
        txtStudentAge.setText(String.valueOf(age));
    }

    @FXML
    void txtStudentEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {
        try {
            Student student = StudentModel.getStudentDetails(txtStudentId.getText());
            txtStudentName.setText(student.getName());
            txtStudentNic.setText(student.getNic());
            cmbStudentGender.setPromptText(student.getGender());
            txtStudentAddress.setText(student.getAddress());
            txtStudentContactNo.setText(student.getContactNo());
            txtStudentEmail.setText(student.getEmail());
            txtStudentDob.setText(student.getDob());
            txtStudentAge.setText(String.valueOf(student.getAge()));
            cmbGrade.setPromptText(student.getGrade());
            txtGuardianNic.setText(student.getGuardianNic());

            Guardian guardian = GuardianModel.getGuardianDetails(txtGuardianNic.getText());
            txtGuardianName.setText(guardian.getName());
            txtGuardianContactNo.setText(guardian.getContactNo());
            txtGuardianEmail.setText(guardian.getEmail());
            txtOccupation.setText(guardian.getOccupation());

            ResultSet resultSet = StudentModel.getImage(txtStudentId.getText());
            if(resultSet.next()){
                Image img = new Image(resultSet.getBinaryStream("image"));
                studentImage.setImage(img);
                studentImage.setPreserveRatio(false);
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void txtStudentNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentNicOnAction(ActionEvent event) {

    }

    public void btnBrowseImageOnAction(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("..\\tcms-2.0\\StudentImages"));
        try {

            boolean isExist = StudentModel.isExistImage(txtStudentId.getText());
            if(!isExist){
                String sql = "INSERT INTO StudentImage VALUES (?, ?)";
                PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

                File file = fileChooser.showOpenDialog(new Stage());

                if(file!=null){
                    FileInputStream fis = new FileInputStream(file);
                    pstm.setString(1,txtStudentId.getText());
                    pstm.setBinaryStream(2,fis,fis.available());
                    int x = pstm.executeUpdate();

                    if(x>0){
                        JOptionPane.showMessageDialog(null, "Image SuccessFully Added!");

                        ResultSet resultSet = StudentModel.getImage(txtStudentId.getText());
                        if(resultSet.next()){
                            Image img = new Image(resultSet.getBinaryStream("image"));
                            studentImage.setImage(img);
                            studentImage.setPreserveRatio(false);
                        }
                    }

                }

            }else{
                String sql = "UPDATE StudentImage SET image=? WHERE studentId=? ";
                PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

                fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(new Stage());

                if(file!=null){
                    FileInputStream fis = new FileInputStream(file);
                    pstm.setString(2,txtStudentId.getText());
                    pstm.setBinaryStream(1,fis,fis.available());
                    int x = pstm.executeUpdate();

                    if(x>0){
                        JOptionPane.showMessageDialog(null, "Image Added SuccessFully!");

                        ResultSet resultSet = StudentModel.getImage(txtStudentId.getText());
                        if(resultSet.next()){
                            Image img = new Image(resultSet.getBinaryStream("image"));
                            studentImage.setImage(img);
                            studentImage.setPreserveRatio(false);
                        }
                    }

                }
            }

        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void backImageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }
}
