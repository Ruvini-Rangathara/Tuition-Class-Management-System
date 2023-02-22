package lk.vidathya.tcms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.StudentClassModel;
import lk.vidathya.tcms.model.StudentModel;
import lk.vidathya.tcms.model.TutorModel;
import lk.vidathya.tcms.tableModel.StudentClassTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewStudentsClassesController implements Initializable {

    public AnchorPane context;
    public TableColumn colTutorName;
    @FXML
    private TableView<StudentClassTM> tblStudentClasses;

    @FXML
    private TableColumn<?, ?> colClassCode;

    @FXML
    private TableColumn<?, ?> colGrade;

    @FXML
    private TableColumn<?, ?> colSubject;

    @FXML
    private TableColumn<?, ?> colJoinDate;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnRefresh;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colClassCode.setCellValueFactory(new PropertyValueFactory<>("classCode"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colTutorName.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        colJoinDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {
        try {
            Student student = StudentModel.getStudentDetails(txtStudentId.getText());
            txtName.setText(student.getName());

            ObservableList<StudentClassTM> data = FXCollections.observableArrayList();
            ResultSet result = StudentClassModel.getInfo(txtStudentId.getText());
            while (result.next()){
                Classes classes = ClassModel.getClassDetails(result.getString("classCode"));
                Tutor tutor = TutorModel.getTutorDetails(classes.getTutorId());
                data.add(
                        new StudentClassTM(
                                result.getString("classCode"),
                                classes.getGrade(),
                                classes.getSubject(),
                                tutor.getName(),
                                result.getString("date")
                        ));
                }
            tblStudentClasses.setItems(data);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


}
