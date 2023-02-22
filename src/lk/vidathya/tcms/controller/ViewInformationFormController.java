package lk.vidathya.tcms.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;

public class ViewInformationFormController {

    public AnchorPane context;
    public Label studentCount;
    public Label tutorCount;
    public Button btnOk;
    @FXML
    private Button btnStaff;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnTutors;

    @FXML
    private Button btnClasses;

    @FXML
    private Button btnHalls;

    @FXML
    private Button btnPayments;

    @FXML
    private Button btnAttendance;

    @FXML
    private AnchorPane viewInfoContext;

    @FXML
    private Label lblViewInfo;

    @FXML
    private Button btnStaffInfo;

    @FXML
    private Button btnStaffSalary;

    @FXML
    private Button btnStudentInfo;

    @FXML
    private Button btnGuardianInfo;

    @FXML
    private Button btnTutorsInfo;

    @FXML
    private Button btnTutorSalary;

    @FXML
    private Button btnClassesInfo;

    @FXML
    private Button btnClassStudents;

    @FXML
    private Button btnHallInfo;

    @FXML
    private Button btnHallReservations;

    @FXML
    private Button btnRegistrationFees;

    @FXML
    private Button btnClassFees;

    @FXML
    private Button btnRefunds;

    @FXML
    private Button btnExtraClasses;

    @FXML
    private Button btnStudentsClasses;

    public void initialize(){

        viewInfoContext.getChildren().clear();
        try {
            viewInfoContext.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/vidathya/tcms/view/ViewInfoContextForm.fxml")));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    void btnAttendanceOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWATTENDANCE,viewInfoContext);
    }

    @FXML
    void btnClassFeesOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWCLASSFEEPAYMENTS,viewInfoContext);
    }

    @FXML
    void btnClassStudentsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWCLASSSTUDENTS,viewInfoContext);
    }

    @FXML
    void btnClassesInfoOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWCLASSESINFO,viewInfoContext);
    }

    @FXML
    void btnExtraClassesOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWEXTRACLASSES,viewInfoContext);
    }

    @FXML
    void btnGuardianInfoOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWGUARDIANINFO,viewInfoContext);
    }

    @FXML
    void btnHallInfoOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWHALLINFO,viewInfoContext);
    }

    @FXML
    void btnHallReservationsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWHALLRESERVATIONS,viewInfoContext);
    }

    @FXML
    void btnHallsOnAction(ActionEvent event) {
        btnHallReservations.setVisible(true);
        btnHallInfo.setVisible(true);
    }

    @FXML
    void btnRefundsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWREFUNDS,viewInfoContext);
    }

    @FXML
    void btnRegistrationFeesOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWREGISTRATIONPAYMENTS,viewInfoContext);
    }

    @FXML
    void btnStaffInfoOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWSTAFFINFO,viewInfoContext);
    }

    @FXML
    void btnStaffSalaryOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWSTAFFSALARY,viewInfoContext);
    }

    @FXML
    void btnStudentInfoOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWSTUDENTINFO,viewInfoContext);
    }

    @FXML
    void btnStudentsClassesOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWSTUDENTSCLASSES,viewInfoContext);
    }

    @FXML
    void btnTutorSalaryOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWTUTORSSALARY,viewInfoContext);
    }

    @FXML
    void btnTutorsInfoOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWTUTORSINFO,viewInfoContext);
    }

    @FXML
    void btnClassesOnAction(ActionEvent event) {
        classesOptions(true);
    }

    @FXML
    void btnPaymentsOnAction(ActionEvent event) {
        paymentsOptions(true);
    }

    @FXML
    void btnStaffOnAction(ActionEvent event) {
        btnStaffInfo.setVisible(true);
        btnStaffSalary.setVisible(true);
    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) {
        studentOptions(true);
    }

    @FXML
    void btnTutorsOnAction(ActionEvent event) {
        btnTutorsInfo.setVisible(true);
        btnTutorSalary.setVisible(true);
    }

    public void btnStaffInfoOnMouseExited(MouseEvent mouseEvent) {
        btnStaffInfo.setVisible(false);
        btnStaffSalary.setVisible(false);
    }

    public void btnStaffInfoOnMouseMoved(MouseEvent mouseEvent) {
        btnStaffInfo.setVisible(true);
        btnStaffSalary.setVisible(true);
    }

    public void btnStaffSalaryOnMouseExited(MouseEvent mouseEvent) {
        btnStaffInfo.setVisible(false);
        btnStaffSalary.setVisible(false);
    }

    public void btnStaffSalaryOnMouseMoved(MouseEvent mouseEvent) {
        btnStaffInfo.setVisible(true);
        btnStaffSalary.setVisible(true);
    }



    public void btnStudentsClassesOnMouseExited(MouseEvent mouseEvent) {
        studentOptions(false);
    }

    public void btnStudentsClassesOnMouseMoved(MouseEvent mouseEvent) {
        studentOptions(true);
    }

    public void btnStudentInfoOnMouseExited(MouseEvent mouseEvent) {
        studentOptions(false);
    }

    public void btnStudentInfoOnMouseMoved(MouseEvent mouseEvent) {
        studentOptions(true);
    }

    public void btnGuardianInfoOnMouseExited(MouseEvent mouseEvent) {
        studentOptions(false);
    }

    public void btnGuardianInfoOnMouseMoved(MouseEvent mouseEvent) {
        studentOptions(true);
    }

    public void studentOptions(boolean b){
        btnStudentInfo.setVisible(b);
        btnGuardianInfo.setVisible(b);
        btnStudentsClasses.setVisible(b);
    }


    public void btnTutorsInfoOnMouseExited(MouseEvent mouseEvent) {
        btnTutorsInfo.setVisible(false);
        btnTutorSalary.setVisible(false);
    }

    public void btnTutorsInfoOnMouseMoved(MouseEvent mouseEvent) {
        btnTutorsInfo.setVisible(true);
        btnTutorSalary.setVisible(true);
    }

    public void btnTutorSalaryOnExited(MouseEvent mouseEvent) {
        btnTutorsInfo.setVisible(false);
        btnTutorSalary.setVisible(false);
    }

    public void btnTutorSalaryOnMoved(MouseEvent mouseEvent) {
        btnTutorsInfo.setVisible(true);
        btnTutorSalary.setVisible(true);
    }


    public void btnClassesInfoOnMouseExited(MouseEvent mouseEvent) {
        classesOptions(false);
    }

    public void btnClassesInfoOnMouseMoved(MouseEvent mouseEvent) {
        classesOptions(true);
    }

    public void btnClassStudentsOnMouseExited(MouseEvent mouseEvent) {
        classesOptions(false);
    }

    public void btnClassStudentsOnMouseMoved(MouseEvent mouseEvent) {
        classesOptions(true);
    }

    public void btnExtraClassesOnMouseExited(MouseEvent mouseEvent) {
        classesOptions(false);
    }

    public void btnExtraClassesOnMouseMoved(MouseEvent mouseEvent) {
        classesOptions(true);
    }

    public void classesOptions(boolean b){
        btnClassesInfo.setVisible(b);
        btnClassStudents.setVisible(b);
        btnExtraClasses.setVisible(b);
    }


    public void btnHallInfoOnMouseExited(MouseEvent mouseEvent) {
        btnHallInfo.setVisible(false);
        btnHallReservations.setVisible(false);
    }

    public void btnHallInfoOnMouseMoved(MouseEvent mouseEvent) {
        btnHallInfo.setVisible(true);
        btnHallReservations.setVisible(true);
    }

    public void btnHallReservationsOnMouseExited(MouseEvent mouseEvent) {
        btnHallInfo.setVisible(false);
        btnHallReservations.setVisible(false);
    }

    public void btnHallReservationsOnMouseMoved(MouseEvent mouseEvent) {
        btnHallInfo.setVisible(true);
        btnHallReservations.setVisible(true);
    }


    public void btnRegistrationFeesOnMouseExited(MouseEvent mouseEvent) {
        paymentsOptions(false);
    }

    public void btnRegistrationFeesOnMouseMoved(MouseEvent mouseEvent) {
        paymentsOptions(true);
    }

    public void btnClassFeesOnMouseExited(MouseEvent mouseEvent) {
        paymentsOptions(false);
    }

    public void btnClassFeesOnMouseMoved(MouseEvent mouseEvent) {
        paymentsOptions(true);
    }

    public void btnRefundsOnMouseExited(MouseEvent mouseEvent) {
        paymentsOptions(false);
    }

    public void btnRefundsOnMouseMoved(MouseEvent mouseEvent) {
        paymentsOptions(true);
    }

    public void paymentsOptions(boolean b){
        btnRegistrationFees.setVisible(b);
        btnClassFees.setVisible(b);
        btnRefunds.setVisible(b);
    }

    public void btnOkOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }


    public void backImageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }
}
