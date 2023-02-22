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
import lk.vidathya.tcms.tableModel.ViewAttendanceTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewAttendanceController implements Initializable {

    public AnchorPane context;
    public ComboBox cmbClassCode;
    public TextField txtSubject;
    @FXML
    private TableView<ViewAttendanceTM> tblAttendance;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStartTime;

    @FXML
    private TableColumn<?, ?> colEndTime;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnRefresh;

    @FXML
    private DatePicker dteDate;

    @FXML
    private TextField txtGrade;


    @FXML
    private TextField txtCount;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataLoad.loadClassCode(cmbClassCode);

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

    }


    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        int count=0;
        if(String.valueOf(cmbClassCode.getValue())!=null && String.valueOf(dteDate.getValue())!=null){
            ObservableList<ViewAttendanceTM> data = FXCollections.observableArrayList();
            try {
                ResultSet result = AttendanceModel.getAllData(String.valueOf(cmbClassCode.getValue()),String.valueOf(dteDate.getValue()));
                while (result.next()){
                    Student student = StudentModel.getStudentDetails(result.getString("studentId"));
                    Classes classes = ClassModel.getClassDetails(String.valueOf(cmbClassCode.getValue()));

                    data.add(
                            new ViewAttendanceTM(
                                    result.getString("studentId"),
                                    student.getName(),
                                    result.getString("date"),
                                    classes.getStartTime(),
                                    classes.getEndTime()
                            ));
                    count++;
                }
                tblAttendance.setItems(data);
                txtCount.setText(String.valueOf(count));
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e+"").show();
            }

        }

    }


    @FXML
    void dteDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtCountOnAction(ActionEvent event) {

    }

    @FXML
    void txtGradeOnAction(ActionEvent event) {

    }


    public void cmbClassCodeOnAction(ActionEvent actionEvent) {

        try {
            Classes classes = ClassModel.getClassDetails((String) cmbClassCode.getValue());
            txtGrade.setText(classes.getGrade());
            txtSubject.setPromptText(classes.getSubject());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public void txtSubjectOnAction(ActionEvent actionEvent) {

    }

}
