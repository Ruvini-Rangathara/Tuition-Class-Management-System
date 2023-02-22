package lk.vidathya.tcms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;

public class ReportFormController {

    @FXML
    private AnchorPane context;

    @FXML
    private Button btnStudentsReports;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnStaffReports;

    @FXML
    private Button btnTutorsReports;

    @FXML
    private Button btnStaffSalarySheet;

    @FXML
    private Button btnTutorSalarySheet;

    @FXML
    private Button btnIncomeExpenditure;

    @FXML
    private Button btnClassFeePayments;

    @FXML
    private Button btnRegistrationPayments;


    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW, context);
    }


    @FXML
    void btnStaffReportsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.REPORTSTAFFINFO, context);
    }


    @FXML
    void btnTutorsReportsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.REPORTTUTORINFO, context);
    }


}
