package lk.vidathya.tcms.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.PaymentModel;
import lk.vidathya.tcms.model.RegistrationPaymentModel;
import lk.vidathya.tcms.model.StaffModel;
import lk.vidathya.tcms.model.StudentModel;
import lk.vidathya.tcms.tableModel.RegistrationPaymentTM;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.MonthName;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ViewRegistrationPaymentsController {

    public AnchorPane context;
    public TableColumn colAmount;
    public TextField txtYear;
    @FXML
    private TableView<RegistrationPaymentTM> tblRegistrationPayments;

    @FXML
    private TableColumn<?, ?> colPaymentCode;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStaffId;

    @FXML
    private TableColumn<?, ?> colStaffName;

    @FXML
    private Button btnOk;


    @FXML
    private ComboBox<?> cmbMonth;

    public void initialize(){
        DataLoad.loadMonths((ComboBox<String>) cmbMonth);

        colPaymentCode.setCellValueFactory(new PropertyValueFactory<>("paymentCode"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStaffId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        colStaffName.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("fee"));

        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        loadPaymentData(MonthName.getMonthName(String.valueOf(LocalDate.now()))  ,year);
    }

    private void loadPaymentData(String monthName, int year) {
        ObservableList<RegistrationPaymentTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = RegistrationPaymentModel.getAllData(monthName,year);
            while (result.next()){
                Student student = StudentModel.getStudentDetails(result.getString("studentId"));
                Staff staff = StaffModel.getStaffDetails(result.getString("staffId"));
                data.add(
                        new RegistrationPaymentTM(
                                result.getString("code"),
                                result.getString("studentId"),
                                student.getName(),
                                result.getString("date"),
                                result.getString("staffId"),
                                staff.getName(),
                                result.getDouble("fee")
                        ));
            }
            tblRegistrationPayments.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }


    @FXML
    void cmbMonthOnAction(ActionEvent event) {
        if(txtYear.getText()==null){
            loadPaymentData(String.valueOf(cmbMonth.getValue())  , Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]));
        }else{
            loadPaymentData(String.valueOf(cmbMonth.getValue()) , Integer.parseInt(txtYear.getText()));
        }
    }

    public void txtYearOnAction(ActionEvent actionEvent) {
        if(cmbMonth.getValue()==null){
            loadPaymentData(MonthName.getMonthName(String.valueOf(LocalDate.now()))  , Integer.parseInt(txtYear.getText()));
        }else{
            loadPaymentData(String.valueOf(cmbMonth.getValue()) , Integer.parseInt(txtYear.getText()));
        }
    }
}
