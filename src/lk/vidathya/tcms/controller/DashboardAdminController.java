package lk.vidathya.tcms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.ExtraClassModel;
import lk.vidathya.tcms.model.StaffModel;
import lk.vidathya.tcms.tableModel.ClassScheduleTM;
import lk.vidathya.tcms.tableModel.ExtraClassScheduleTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardAdminController implements Initializable {


    public AnchorPane dashboardAdminContext;
    public Button btnAddStudentsToClasses;
    public Button btnStudentsAttendance;
    public Button btnStudentsPayments;
    public Button btnRefunds;
    public Button btnAddNewExtraClass;
    public Button btnManageExtraClass;
    public Button btnExit;
    public ImageView adminImage;
    public Label lblDashboardContactNo;
    public Label lblDashboardEmail;


    @FXML
    private TableView<ExtraClassScheduleTM> tblEClassSchedule;

    @FXML
    private TableColumn<ExtraClassScheduleTM, String> colEClassSubject;

    @FXML
    private TableColumn<ExtraClassScheduleTM, String> colEClassGrade;

    @FXML
    private TableColumn<ExtraClassScheduleTM, String> colEClassHall;

    @FXML
    private TableColumn<ExtraClassScheduleTM, String> colEClassStartTime;

    @FXML
    private TableColumn<ExtraClassScheduleTM, String> colEClassEndTime;


    @FXML
    private Label lblUser;

    @FXML
    private Button btnStaff;

    @FXML
    private Button btnTutors;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnClass;

    @FXML
    private Button btnHall;

    @FXML
    private Button btnFinanceManage;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnMail;

    @FXML
    private Button btnLoginControl;

    @FXML
    private Button btnReports;

    @FXML
    private AnchorPane dashboardContext;

    @FXML
    private Button btnStudentRegistration;

    @FXML
    private Button btnManageStudents;

    @FXML
    private Button btnTutorRegistration;

    @FXML
    private Button btnManageTutors;

    @FXML
    private Button btnStaffRegistration;

    @FXML
    private Button btnManageStaff;

    @FXML
    private Button btnAddNewUser;

    @FXML
    private Button btnManageUserAccounts;

    @FXML
    private Button btnAddNewClass;

    @FXML
    private Button btnManageClasses;

    @FXML
    private TableView<ClassScheduleTM> tblSchedule;

    @FXML
    private TableColumn<ClassScheduleTM, String> colSubject;

    @FXML
    private TableColumn<ClassScheduleTM, String> colGrade;

    @FXML
    private TableColumn<ClassScheduleTM, String> colHallNo;

    @FXML
    private TableColumn<ClassScheduleTM, String> colStartTime;

    @FXML
    private TableColumn<ClassScheduleTM, String> colEndTime;

    @FXML
    private Label lblExit;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;

    static String dayName;
    static String monthName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblDashboardEmail.setText(InstituteData.getEmail());
        lblDashboardContactNo.setText(InstituteData.getContactNo());

        setUserImage();

        lblUser.setText(LoginCredentials.getName());
        DateTime.localTime(lblTime);

        String date = String.valueOf(LocalDate.now());
        lblDate.setText(DateTime.setDate(date));

        dayName=DayName.getDayName(date);
        monthName= MonthName.getMonthName(date);

        lblExit.setVisible(false);

        btnStaffRegistration.setVisible(false);
        btnManageStaff.setVisible(false);
        btnTutorRegistration.setVisible(false);
        btnManageTutors.setVisible(false);
        btnStudentRegistration.setVisible(false);
        btnManageStudents.setVisible(false);
        btnAddNewClass.setVisible(false);
        btnManageClasses.setVisible(false);

        btnAddNewUser.setVisible(false);
        btnManageUserAccounts.setVisible(false);
        btnAddStudentsToClasses.setVisible(false);
        btnStudentsAttendance.setVisible(false);
        btnStudentsPayments.setVisible(false);
        btnRefunds.setVisible(false);
        btnAddNewExtraClass.setVisible(false);
        btnManageExtraClass.setVisible(false);

        dashboardContext.getChildren().clear();
        try {
            dashboardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/vidathya/tcms/view/DashboardView.fxml")));
        } catch (IOException e) {
            System.out.println(e);
        }


        colEClassSubject.setCellValueFactory(new PropertyValueFactory<>("extraClassSubject"));
        colEClassGrade.setCellValueFactory(new PropertyValueFactory<>("extraClassGrade"));
        colEClassHall.setCellValueFactory(new PropertyValueFactory<>("extraClassHallNo"));
        colEClassStartTime.setCellValueFactory(new PropertyValueFactory<>("extraClassStartTime"));
        colEClassEndTime.setCellValueFactory(new PropertyValueFactory<>("extraClassEndTime"));


        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colHallNo.setCellValueFactory(new PropertyValueFactory<>("hall"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        loadScheduleTableData();

        loadExtraClassScheduleTableData();
    }

    private void setUserImage(){

        try {
            ResultSet resultSet = StaffModel.getImage(LoginCredentials.getCurrentUser());
            System.out.println("current user admin  "+LoginCredentials.getCurrentUser());
            if(resultSet.next()){
                Image img = new Image(resultSet.getBinaryStream("image"));
                adminImage.setImage(img);
                adminImage.setPreserveRatio(false);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadExtraClassScheduleTableData() {

        ObservableList<ExtraClassScheduleTM> extraClassData = FXCollections.observableArrayList();
        String date = String.valueOf(LocalDate.now());
        try {
            //String newDate = "2022-12-3";

            ResultSet result = ExtraClassModel.getDataToScheduleTable(date);

            while (result.next()){
                Classes classes = ClassModel.getClassDetails(result.getString("classCode"));
                extraClassData.add(
                        new ExtraClassScheduleTM(
                                classes.getSubject(),
                                classes.getGrade(),
                                result.getString("hallNo"),
                                result.getString("startTime"),
                                result.getString("endTime")
                        ));
//
//                System.out.println(classes.getSubject());
//                System.out.println(classes.getGrade());
//                System.out.println(result.getString("hallNo"));
//                System.out.println(result.getString("startTime"));
//                System.out.println(result.getString("endTime"));

            }
            tblEClassSchedule.setItems(extraClassData);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadScheduleTableData() {

        ObservableList<ClassScheduleTM> data = FXCollections.observableArrayList();

        String date = String.valueOf(LocalDate.now());
        String dayName = DayName.getDayName(date);

        try {
            ResultSet result = ClassModel.getDataToScheduleTable(dayName);
            while (result.next()){

                data.add(
                        new ClassScheduleTM(
                                result.getString("subject"),
                                result.getString("grade"),
                                result.getString("hallNo"),
                                result.getString("startTime"),
                                result.getString("endTime")
                        ));

            }
            tblSchedule.setItems(data);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }


    @FXML
    void btnAddNewClassOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Add New Class");
        Navigation.navigate(Routes.ADDCLASS, dashboardContext);
    }


    @FXML
    void btnAddNewUserOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Add New User");
        Navigation.navigate(Routes.NEWUSER, dashboardContext);
    }

    @FXML
    void btnClassOnAction(ActionEvent event) {
        classOptions(true);
    }

    @FXML
    void btnFinanceManageOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Finance Manage");
        Navigation.navigate(Routes.FINANCEMANAGE, dashboardContext);
    }

    @FXML
    void btnHallOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Add New Hall");
        Navigation.navigate(Routes.HALL, dashboardContext);
    }

    @FXML
    void btnInfoOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("View Information");
        Navigation.navigate(Routes.VIEWINFORMATION, dashboardContext);
    }

    @FXML
    void btnLoginControlOnAction(ActionEvent event) {
        btnAddNewUser.setVisible(true);
        btnManageUserAccounts.setVisible(true);
    }

    @FXML
    void btnMailOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("New Message");
        Navigation.navigate(Routes.MAIL, dashboardContext);
    }

    @FXML
    void btnManageClassesOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Manage Classes");
        Navigation.navigate(Routes.MANAGECLASSES, dashboardContext);
    }

    @FXML
    void btnManageHallsOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Manage Halls");
        Navigation.navigate(Routes.HALL, dashboardContext);
    }

    @FXML
    void btnManageStaffOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Manage Staff");
        Navigation.navigate(Routes.STAFFMANAGE, dashboardContext);
    }

    @FXML
    void btnManageStudentsOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Manage Students");
        Navigation.navigate(Routes.STUDENTMANAGE, dashboardContext);
    }

    @FXML
    void btnManageTutorsOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Manage Tutors");
        Navigation.navigate(Routes.TUTORMANAGE, dashboardContext);
    }

    @FXML
    void btnManageUserAccountsOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Manage User Accounts");
        Navigation.navigate(Routes.MANAGEUSER, dashboardContext);
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) throws IOException {
//        lblFormName.setText("Reports");
        Navigation.navigate(Routes.REPORTS, dashboardContext);
    }

    @FXML
    void btnStaffOnAction(ActionEvent event) {
        btnStaffRegistration.setVisible(true);
        btnManageStaff.setVisible(true);
    }

    @FXML
    void btnStaffRegistrationOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Staff Registration");
        Navigation.navigate(Routes.STAFFREGISTRATION,dashboardContext);
    }

    @FXML
    void btnStudentRegistrationOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Student Registration");
        Navigation.navigate(Routes.STUDENTREGISTRATION, dashboardContext);
    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) {
        studentOptionButtons(true);
    }

    @FXML
    void btnTutorRegistrationOnAction(ActionEvent event) throws IOException {
        //lblFormName.setText("Tutor Registration");
        Navigation.navigate(Routes.TUTORREGISTRATION, dashboardContext);
    }

    @FXML
    void btnTutorsOnAction(ActionEvent event) {
        btnTutorRegistration.setVisible(true);
        btnManageTutors.setVisible(true);
    }


//        try {
//            Thread.sleep(1000);
//        } catch ( Exception e) {
//            System.out.println("sleep failed.");
//        }

    public void btnStaffRegistrationOnMouseExited(MouseEvent mouseEvent) {
        btnStaffRegistration.setVisible(false);
        btnManageStaff.setVisible(false);
    }

    public void btnStaffRegistrationOnMouseMoved(MouseEvent mouseEvent) {
        btnStaffRegistration.setVisible(true);
        btnManageStaff.setVisible(true);
    }

    public void btnManageStaffOnMouseExited(MouseEvent mouseEvent) {
        btnStaffRegistration.setVisible(false);
        btnManageStaff.setVisible(false);
    }

    public void btnManageStaffOnMouseMoved(MouseEvent mouseEvent) {
        btnStaffRegistration.setVisible(true);
        btnManageStaff.setVisible(true);
    }

    public void btnTutorRegistrationOnMouseExited(MouseEvent mouseEvent) {
        btnTutorRegistration.setVisible(false);
        btnManageTutors.setVisible(false);
    }

    public void btnTutorRegistrationOnMouseMoved(MouseEvent mouseEvent) {
        btnTutorRegistration.setVisible(true);
        btnManageTutors.setVisible(true);
    }

    public void btnManageTutorsOnMouseExited(MouseEvent mouseEvent) {
        btnTutorRegistration.setVisible(false);
        btnManageTutors.setVisible(false);
    }

    public void btnManageTutorsOnMouseMoved(MouseEvent mouseEvent) {
        btnTutorRegistration.setVisible(true);
        btnManageTutors.setVisible(true);
    }

    public void btnAddNewUserOnMouseExited(MouseEvent mouseEvent) {
        btnAddNewUser.setVisible(false);
        btnManageUserAccounts.setVisible(false);
    }

    public void btnAddNewUserOnMouseMoved(MouseEvent mouseEvent) {
        btnAddNewUser.setVisible(true);
        btnManageUserAccounts.setVisible(true);
    }

    public void btnManageUserAccountsOnMouseExited(MouseEvent mouseEvent) {
        btnAddNewUser.setVisible(false);
        btnManageUserAccounts.setVisible(false);
    }

    public void btnManageUserAccountsOnMouseMoved(MouseEvent mouseEvent) {
        btnAddNewUser.setVisible(true);
        btnManageUserAccounts.setVisible(true);
    }


    public void btnStudentRegistrationOnMouseExited(MouseEvent mouseEvent) {
        studentOptionButtons(false);
    }

    public void btnStudentRegistrationOnMouseMoved(MouseEvent mouseEvent) {
        studentOptionButtons(true);
    }

    public void btnManageStudentsOnExited(MouseEvent mouseEvent) {
        studentOptionButtons(false);
    }

    public void btnManageStudentsOnMoved(MouseEvent mouseEvent) {
        studentOptionButtons(true);
    }

    public void btnAddStudentsToClassesOnAction(ActionEvent actionEvent) throws IOException {
        //lblFormName.setText("Add Students To Classes");
        Navigation.navigate(Routes.ADDSTUDENTTOCLASSES,dashboardContext);
    }

    public void btnAddStudentsToClassesOnMouseExited(MouseEvent mouseEvent) {
        studentOptionButtons(false);
    }

    public void btnStudentsAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        //lblFormName.setText("Students Attendance");
        Navigation.navigate(Routes.ATTENDANCE,dashboardContext);
    }

    public void btnStudentsAttendanceOnMouseExited(MouseEvent mouseEvent) {
        studentOptionButtons(false);
    }

    public void btnStudentsPaymentsOnAction(ActionEvent actionEvent) throws IOException {
        //lblFormName.setText("Students Payments");
        Navigation.navigate(Routes.PAYMENTS,dashboardContext);
    }

    public void btnStudentsPaymentsOnMouseExited(MouseEvent mouseEvent) {
        studentOptionButtons(false);
    }

    public void btnStudentsPaymentsOnMouseMoved(MouseEvent mouseEvent) {
        studentOptionButtons(true);
    }

    public void btnStudentsAttendanceOnMouseMoved(MouseEvent mouseEvent) {
        studentOptionButtons(true);
    }

    public void btnAddStudentsToClassesOnMouseMoved(MouseEvent mouseEvent) {
        studentOptionButtons(true);
    }

    public void btnRefundsOnAction(ActionEvent actionEvent) throws IOException {
        //lblFormName.setText("Refunds");
        Navigation.navigate(Routes.REFUNDS,dashboardContext);
    }

    public void btnRefundsOnMouseExited(MouseEvent mouseEvent) {
        studentOptionButtons(false);
    }

    public void btnRefundsOnMouseMoved(MouseEvent mouseEvent) {
        studentOptionButtons(true);
    }

    public void studentOptionButtons(boolean b){
        btnStudentRegistration.setVisible(b);
        btnManageStudents.setVisible(b);
        btnAddStudentsToClasses.setVisible(b);
        btnStudentsAttendance.setVisible(b);
        btnStudentsPayments.setVisible(b);
        btnRefunds.setVisible(b);
    }


    public void btnAddNewClassOnMouseExited(MouseEvent mouseEvent) {
        classOptions(false);
    }

    public void btnAddNewClassOnMouseMoved(MouseEvent mouseEvent) {
        classOptions(true);;
    }

    public void btnManageClassesOnMouseExited(MouseEvent mouseEvent) {
        classOptions(false);
    }

    public void btnManageClassesOnMouseMoved(MouseEvent mouseEvent) {
        classOptions(true);
    }

    public void btnAddNewExtraClassOnAction(ActionEvent actionEvent) throws IOException {
        //lblFormName.setText("Add Extra Class");
        Navigation.navigate(Routes.ADDEXTRACLASS,dashboardContext);
    }

    public void btnManageExtraClassOnAction(ActionEvent actionEvent) throws IOException {
        //lblFormName.setText("Manage Extra Class");
        Navigation.navigate(Routes.MANAGEEXTRACLASSES,dashboardContext);
    }

    public void btnAddNewExtraClassOnMouseExited(MouseEvent mouseEvent) {
        classOptions(false);
    }

    public void btnAddNewExtraClassOnMouseMoved(MouseEvent mouseEvent) {
        classOptions(true);
    }

    public void btnManageExtraClassesOnMouseExited(MouseEvent mouseEvent) {
        classOptions(false);
    }

    public void btnManageExtraClassesOnMouseMoved(MouseEvent mouseEvent) {
        classOptions(true);
    }

    public void classOptions(boolean b){
        btnAddNewClass.setVisible(b);
        btnManageClasses.setVisible(b);
        btnAddNewExtraClass.setVisible(b);
        btnManageExtraClass.setVisible(b);
    }


    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,dashboardAdminContext);
    }

    public void btnExitOnMouseExited(MouseEvent mouseEvent) {
        lblExit.setVisible(false);
    }

    public void btnExitOnMouseMoved(MouseEvent mouseEvent) {
        lblExit.setVisible(true);
    }

}
