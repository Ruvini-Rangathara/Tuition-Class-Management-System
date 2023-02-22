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
import lk.vidathya.tcms.tableModel.ClassStudentTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewClassStudentController implements Initializable {

    public AnchorPane context;
    public ComboBox cmbClassCode;
    @FXML
    private TableView<ClassStudentTM> tblClassStudent;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colJoinDate;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnRefresh;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtClassCode;

    @FXML
    private TextField txtGrade;

    @FXML
    private TextField txtTutorName;

    @FXML
    private TextField txtCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataLoad.loadClassCode(cmbClassCode);

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colJoinDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));

    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

        if(cmbClassCode.getValue()!=null){
            ObservableList<ClassStudentTM> data = FXCollections.observableArrayList();
            try {
                ResultSet result = StudentClassModel.getAllData(String.valueOf(cmbClassCode.getValue()));
                while (result.next()){
                    Student student = StudentModel.getStudentDetails(result.getString("studentId"));
                    data.add(
                            new ClassStudentTM(
                                    student.getStudentId(),
                                    student.getName(),
                                    student.getEmail(),
                                    student.getContactNo(),
                                    student.getDate()
                            ));
                }
                tblClassStudent.setItems(data);
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e+"").show();
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

    @FXML
    void txtTutorNameOnAction(ActionEvent event) {

    }

    public void cmbClassCodeOnAction(ActionEvent actionEvent) {

        try {
            Classes classes =  ClassModel.getClassDetails((String) cmbClassCode.getValue());
            txtGrade.setText(classes.getGrade());
            txtSubject.setText(classes.getSubject());

            Tutor tutor = TutorModel.getTutorDetails(classes.getTutorId());
            txtTutorName.setText(tutor.getName());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


}
