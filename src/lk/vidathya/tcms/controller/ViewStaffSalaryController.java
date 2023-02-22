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
import lk.vidathya.tcms.tableModel.StaffSalaryTM;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.MonthName;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ViewStaffSalaryController implements Initializable {

    public AnchorPane context;
    public TableColumn colPaymentCode;
    @FXML
    private TableView<StaffSalaryTM> tblStaffSalary;

    @FXML
    private TableColumn<?, ?> colStaffId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colJob;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colMonth;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private Button btnOk;

    @FXML
    private TextField txtYear;

    @FXML
    private ComboBox<?> cmbMonth;


    @FXML
    private Button btnRefresh;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DataLoad.loadMonths((ComboBox<String>) cmbMonth);

        colPaymentCode.setCellValueFactory(new PropertyValueFactory<>("paymentCode"));
        colStaffId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colJob.setCellValueFactory(new PropertyValueFactory<>("job"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));


        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        loadStaffSalaryData(year, MonthName.getMonthName(String.valueOf(LocalDate.now())));


    }

    private void loadStaffSalaryData(int year, String monthName) {
        ObservableList<StaffSalaryTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = StaffSalaryModel.getSalaryData(year, monthName);
            while (result.next()){
                Staff staff = StaffModel.getStaffDetails(result.getString("staffId"));

                data.add(
                        new StaffSalaryTM(
                                result.getString("paymentCode"),
                                result.getString("staffId"),
                                staff.getName(),
                                staff.getJob(),
                                result.getDouble("salary"),
                                result.getString("month"),
                                result.getString("date")
                        ));

            }
            tblStaffSalary.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    private void loadStaffSalaryData(int year) {
        ObservableList<StaffSalaryTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = StaffSalaryModel.getSalaryData(year);
            while (result.next()){
                Staff staff = StaffModel.getStaffDetails(result.getString("staffId"));
                data.add(
                        new StaffSalaryTM(
                                result.getString("paymentCode"),
                                result.getString("staffId"),
                                staff.getName(),
                                staff.getJob(),
                                result.getDouble("salary"),
                                result.getString("month"),
                                result.getString("date")
                        ));
            }
            tblStaffSalary.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    private void loadStaffSalaryData(String month) {
        ObservableList<StaffSalaryTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = StaffSalaryModel.getSalaryData(month);
            while (result.next()){
                Staff staff = StaffModel.getStaffDetails(result.getString("staffId"));
                data.add(
                        new StaffSalaryTM(
                                result.getString("paymentCode"),
                                result.getString("staffId"),
                                staff.getName(),
                                staff.getJob(),
                                result.getDouble("salary"),
                                result.getString("month"),
                                result.getString("date")
                        ));
            }
            tblStaffSalary.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void cmbMonthOnAction(ActionEvent event) {
        if(txtYear.getText()==null){
            loadStaffSalaryData(String.valueOf(cmbMonth.getValue()));
        }else{
            loadStaffSalaryData(Integer.parseInt(txtYear.getText()), String.valueOf(cmbMonth.getValue()));
        }
    }


    @FXML
    void txtYearOnAction(ActionEvent event) {
        if(cmbMonth.getValue()==null){
            loadStaffSalaryData(Integer.parseInt(txtYear.getText()));
        }else{
            loadStaffSalaryData(Integer.parseInt(txtYear.getText()), String.valueOf(cmbMonth.getValue()));
        }

    }


}
