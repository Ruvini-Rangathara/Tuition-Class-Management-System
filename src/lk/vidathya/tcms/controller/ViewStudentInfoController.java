package lk.vidathya.tcms.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.*;
import lk.vidathya.tcms.tableModel.StudentInfoTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewStudentInfoController implements Initializable {

    public AnchorPane context;
    public ComboBox cmbClassCode;
    public TableColumn colNic;
    @FXML
    private TableView<StudentInfoTM> tblStudent;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colName;


    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnRefresh;

    @FXML
    private TextField txtGrade;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtCount;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataLoad.loadClassCode(cmbClassCode);

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        if(cmbClassCode.getValue()!=null){

            ObservableList<StudentInfoTM> data = FXCollections.observableArrayList();
            try {
                ArrayList<String> studentIdArray = StudentClassModel.getClassesStudents(String.valueOf(cmbClassCode.getValue()));

                for(String id : studentIdArray){
                    Student student = StudentModel.getStudentDetails(id);

                    data.add(new StudentInfoTM(
                            student.getStudentId(),
                            student.getName(),
                            student.getNic(),
                            student.getContactNo(),
                            student.getEmail()
                    ));
                }

                tblStudent.setItems(data);
                txtCount.setText(String.valueOf(studentIdArray.size()));
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @FXML
    void txtCountOnAction(ActionEvent event) {

    }

    @FXML
    void txtGradeOnAction(ActionEvent event) {

    }

    @FXML
    void txtSubjectOnAction(ActionEvent event) {

    }

    public void cmbClassCodeOnAction(ActionEvent actionEvent) {

        try {
            Classes classes =  ClassModel.getClassDetails((String) cmbClassCode.getValue());
            txtGrade.setText(classes.getGrade());
            txtSubject.setText(classes.getSubject());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


}
