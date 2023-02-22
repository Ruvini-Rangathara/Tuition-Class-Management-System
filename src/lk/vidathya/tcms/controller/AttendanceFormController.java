package lk.vidathya.tcms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.*;
import lk.vidathya.tcms.tableModel.AttendanceTM;
import lk.vidathya.tcms.transferObject.Attendance;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.MonthName;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceFormController {

    public AnchorPane context;
    public AnchorPane testContext;

    public TableColumn colPaymentStatus;

    public TableColumn colFee;


    public Button btnPayClassFee;
    public Button btnMark;
    public RadioButton btnPresent;
    public RadioButton btnAbsent;
    public ToggleGroup ap;
    public TextField txtStudentName;
    public TextField txtStudentId;

    @FXML
    private TextField txtGrade;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtTutorName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblMonth;

    @FXML
    private TableView<AttendanceTM> tblAttendance;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colName;


    @FXML
    private ComboBox<?> cmbClassCode;


    public void initialize(){
        lblDate.setText(String.valueOf(LocalDate.now()));
        String month = MonthName.getMonthName(String.valueOf(LocalDate.now()));
        lblMonth.setText(month);
        DataLoad.loadClassCode((ComboBox<String>) cmbClassCode);

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        btnAbsent.setSelected(true);
    }


    @FXML
    void cmbClassCodeOnAction(ActionEvent event) {
        try {
            Classes classes =  ClassModel.getClassDetails((String) cmbClassCode.getValue());
            txtGrade.setText(classes.getGrade());
            txtSubject.setText(classes.getSubject());

            Tutor tutor = TutorModel.getTutorDetails(classes.getTutorId());
            txtTutorName.setText(tutor.getName());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        loadStudents(String.valueOf(cmbClassCode.getValue()));
    }

    private void loadStudents(String classCode) {
        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        ObservableList<AttendanceTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = StudentClassModel.getAllData(classCode);
            ArrayList<String> classesStudentsId = new ArrayList<>();

            while (result.next()){
                classesStudentsId.add(result.getString("studentId"));
            }


            ArrayList<String> attendanceExistStudent = AttendanceModel.getStudentIds(classCode,lblDate.getText());


            ArrayList<String> notMarkAttendanceIds = new ArrayList<>();
            for (int i=0; i<classesStudentsId.size(); i++){
                int count=0;
                for(int j=0; j<attendanceExistStudent.size(); j++){
                    if(classesStudentsId.get(i).equals(attendanceExistStudent.get(j))){
                        count++;
                    }
                }

                if(count==0){
                    notMarkAttendanceIds.add(classesStudentsId.get(i));
                }
            }

            for(int i=0; i<notMarkAttendanceIds.size(); i++){

                Student student = StudentModel.getStudentDetails(notMarkAttendanceIds.get(i));
                Classes classes = ClassModel.getClassDetails(classCode);

                String paymentStatus = StudentPaymentModel.getPaymentStatus(
                        notMarkAttendanceIds.get(i),
                        classCode,
                        year,
                        MonthName.getMonthName(String.valueOf(LocalDate.now()))
                );

                data.add(
                        new AttendanceTM(
                                notMarkAttendanceIds.get(i),
                                student.getName(),
                                paymentStatus,
                                classes.getClassFee()
                        ));
            }
            tblAttendance.setItems(data);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


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

    public void backImageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    public void btnPayClassFeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENTS,context);
    }

    public void btnMarkOnAction(ActionEvent actionEvent) {
        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);

        String status = null;
        if(btnAbsent.isSelected()){
            status = "Absent";
        }
        if(btnPresent.isSelected()){
            status = "Present";
        }

        Attendance attendance = new Attendance(
                txtStudentId.getText(),
                String.valueOf(cmbClassCode.getValue()),
                status,
                lblDate.getText(),
                year,
                MonthName.getMonthName(String.valueOf(LocalDate.now()))
        );

        try {
            boolean isAddAttendance = AttendanceModel.addAttendance(attendance);

            if(isAddAttendance){
                txtStudentId.setText(null);
                txtStudentName.setText(null);
                btnAbsent.setSelected(true);


            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadStudents(String.valueOf(cmbClassCode.getValue()));

    }

    public void txtStudentNameOnAction(ActionEvent actionEvent) {
    }

    public void txtStudentIdOnAction(ActionEvent actionEvent) {
    }

    public void tblAttendanceOnMouseClicked(MouseEvent mouseEvent) {

        if(tblAttendance.getSelectionModel().getSelectedItem() != null){
            txtStudentId.setText(tblAttendance.getSelectionModel().getSelectedItem().getStudentId());
            txtStudentName.setText(tblAttendance.getSelectionModel().getSelectedItem().getName());
        }

    }
}
