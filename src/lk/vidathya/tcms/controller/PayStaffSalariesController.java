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
import lk.vidathya.tcms.tableModel.NotPaidStaffSalaryTM;
import lk.vidathya.tcms.tableModel.PaidStaffSalaryTM;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.StaffSalary;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.util.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class PayStaffSalariesController {

    public TableView tblStaffSalaryNotPaid;
    public Button btnRefresh;
    @FXML
    private AnchorPane context;

    @FXML
    private TableView<PaidStaffSalaryTM> tblStaffSalary;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colStaffId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colJob;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colPayerId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private ComboBox<?> cmbMonth;

    @FXML
    private TextField txtYear;

    @FXML
    private Label lblDate;

    @FXML
    private TableColumn<?, ?> colNotPaiStaffId;

    @FXML
    private TableColumn<?, ?> colNotPaidName;

    @FXML
    private TableColumn<?, ?> colNotPaidJob;

    @FXML
    private TableColumn<?, ?> colNotPaidEmail;

    @FXML
    private TableColumn<?, ?> colNotPaidSalary;

    @FXML
    private ComboBox<?> cmbStaffId;

    @FXML
    private Button btnPay;

    @FXML
    private Label lblPaymentCode;


    public void initialize(){
        lblDate.setText(String.valueOf(LocalDate.now()));
        DataLoad.loadMonths((ComboBox<String>) cmbMonth);
        setPaymentCode();
        DataLoad.loadStaffId((ComboBox<String>) cmbStaffId);

        colNotPaiStaffId.setCellValueFactory(new PropertyValueFactory<>("notPaidStaffId"));
        colNotPaidName.setCellValueFactory(new PropertyValueFactory<>("notPaidName"));
        colNotPaidJob.setCellValueFactory(new PropertyValueFactory<>("notPaidJob"));
        colNotPaidEmail.setCellValueFactory(new PropertyValueFactory<>("notPaidEmail"));
        colNotPaidSalary.setCellValueFactory(new PropertyValueFactory<>("notPaidSalary"));


        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentCode"));
        colStaffId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colJob.setCellValueFactory(new PropertyValueFactory<>("job"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colPayerId.setCellValueFactory(new PropertyValueFactory<>("payerId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        loadPaidData(MonthName.getMonthName(String.valueOf(LocalDate.now()))  ,year);

        loadNotPaidData(MonthName.getMonthName(String.valueOf(LocalDate.now()))  ,year);

    }

    private void loadNotPaidData(String monthName, int year) {

        ObservableList<NotPaidStaffSalaryTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = StaffSalaryModel.getAllNotPaidData(monthName,year);
            while (result.next()){
                Staff staff = StaffModel.getStaffDetails(result.getString("staffId"));
                data.add(
                        new NotPaidStaffSalaryTM(
                                result.getString("staffId"),
                                staff.getName(),
                                staff.getJob(),
                                staff.getEmail(),
                                result.getDouble("salary")
                        ));

//                System.out.print(result.getString("staffId")+"    ");
//                System.out.print(staff.getName()+"    ");
//                System.out.print(staff.getJob()+"    ");
//                System.out.print(staff.getEmail()+"    ");
//                System.out.print(result.getDouble("salary")+"    ");
            }
            tblStaffSalaryNotPaid.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadPaidData(String monthName, int year) {
        //db table = StaffSalary
        ObservableList<PaidStaffSalaryTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = StaffSalaryModel.getAllData(monthName,year);
            while (result.next()){
                Staff staff = StaffModel.getStaffDetails(result.getString("staffId"));
                data.add(
                        new PaidStaffSalaryTM(
                                result.getString("paymentCode"),
                                result.getString("staffId"),
                                staff.getName(),
                                staff.getJob(),
                                result.getDouble("salary"),
                                LoginCredentials.getCurrentUser(),
                                lblDate.getText()
                        ));
//                System.out.print(result.getString("paymentCode")+"    ");
//                System.out.print(result.getString("staffId")+"    ");
//                System.out.print(staff.getName()+"    ");
//                System.out.print(staff.getJob()+"    ");
//                System.out.print(result.getDouble("salary")+"    ");
//                System.out.print(LoginCredentials.getCurrentUser()+"    ");
//                System.out.println(lblDate.getText()+"    \n");
            }
            tblStaffSalary.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    private void setPaymentCode() {
        try {
            String lastPaymentCode= StaffSalaryModel.getLastPaymentCode();
            if(lastPaymentCode==null){
                lblPaymentCode.setText("EP0001");
            }else{
                String[] split=lastPaymentCode.split("[E][P]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newPaymentCode=String.format("EP%04d", lastDigits);
                lblPaymentCode.setText(newPaymentCode);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


    @FXML
    void btnPayOnAction(ActionEvent event) {

        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        String month = MonthName.getMonthName(String.valueOf(LocalDate.now()));

        if( !(String.valueOf(cmbStaffId.getValue()).equals(null)) ){
            Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Confirm?",ButtonType.YES,ButtonType.NO).showAndWait();
            if(choose.get()==ButtonType.YES){

                try {
                    Staff staff = StaffModel.getStaffDetails(String.valueOf(cmbStaffId.getValue()));
                    StaffSalary staffSalary = new StaffSalary(
                            lblPaymentCode.getText(),
                            String.valueOf(cmbStaffId.getValue()),
                            year,
                            month,
                            staff.getSalary(),
                            lblDate.getText(),
                            LoginCredentials.getCurrentUser()

                    );

                    boolean isAdd = StaffSalaryModel.addPayment(staffSalary);
                    if(isAdd){
                        new Alert(Alert.AlertType.CONFIRMATION,"Pay Successfully!").show();
                        loadPaidData(month, year);
                        loadNotPaidData(month,year);

                    }


                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR,e+"").show();
                }


            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Please Select the Staff member.").show();
        }


    }

    @FXML
    void cmbMonthOnAction(ActionEvent event) {

    }

    @FXML
    void txtYearOnAction(ActionEvent event) {

    }


    public void backImageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.FINANCEMANAGE,context);
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {

        int year = Integer.parseInt(txtYear.getText());
        if(year==0){
            year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        }

        String month = String.valueOf(cmbMonth.getValue());
        if(month.equals(null)){
            month = MonthName.getMonthName(String.valueOf(LocalDate.now()));
        }

        loadPaidData(month, year);
        loadNotPaidData(month, year);
    }
}
