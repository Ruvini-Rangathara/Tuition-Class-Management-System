package lk.vidathya.tcms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.StudentClassModel;
import lk.vidathya.tcms.model.StudentModel;
import lk.vidathya.tcms.model.TutorModel;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.transferObject.StudentClass;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class AddStudentToClassesController {

    public AnchorPane context;
    public Button btnBack;
    public ComboBox cmbClassCode;
    @FXML
    private Label lblDate;

    @FXML
    private Button btnAddStudent;

    @FXML
    private TextField txtClassCode;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtStudentGrade;

    @FXML
    private TextField txtClassGrade;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtTutorName;

    public void initialize(){

        lblDate.setText(String.valueOf(LocalDate.now()));
        DataLoad.loadClassCode(cmbClassCode);

    }


    @FXML
    void btnAddStudentOnAction(ActionEvent event) {

        String guardianNic = null;
        try {
            guardianNic = StudentModel.getGuardianNic(txtStudentId.getText());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

        StudentClass studentClass = new StudentClass(
                txtStudentId.getText(),
                String.valueOf(cmbClassCode.getValue()),
                guardianNic,
                lblDate.getText()
        );



        try {

            boolean isExists  = StudentClassModel.isExist(txtStudentId.getText(), String.valueOf(cmbClassCode.getValue()));
            if(isExists){
                new Alert(Alert.AlertType.WARNING,"Student Already Exists").show();
            }else{
                boolean isAdd = StudentClassModel.addData(studentClass);

                if(isAdd){
                    Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Student Added Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                    if(choose.get()==ButtonType.OK){
                        try {
                            Navigation.navigate(Routes.ADDSTUDENTTOCLASSES,context);
                        } catch (IOException e) {
                            new Alert(Alert.AlertType.ERROR,e+"").show();
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtClassGradeOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentGradeOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {
        try {
            Student student = StudentModel.getStudentDetails(txtStudentId.getText());
            txtStudentName.setText(student.getName());
            txtStudentGrade.setText(student.getGrade());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void txtStudentNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtSubjectOnAction(ActionEvent event) {

    }

    @FXML
    void txtTutorNameOnAction(ActionEvent event) {

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    public void cmbClassCodeOnAction(ActionEvent actionEvent) {
        setClassDetails();
    }


    private void setClassDetails() {
        try {
            Classes classes =  ClassModel.getClassDetails((String) cmbClassCode.getValue());
            txtClassGrade.setText(classes.getGrade());
            txtSubject.setText(classes.getSubject());

            Tutor tutor = TutorModel.getTutorDetails(classes.getTutorId());
            txtTutorName.setText(tutor.getName());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

}
