package lk.vidathya.tcms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.*;
import lk.vidathya.tcms.tableModel.TutorSalaryInfoTM;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.MonthName;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ViewTutorsSalaryController {

    public AnchorPane context;
    public TableColumn colPayerId;
    @FXML
    private TableView<TutorSalaryInfoTM> tblTutorSalary;

    @FXML
    private TableColumn<?, ?> colTutorId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSubject;

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


    public void initialize(){
        DataLoad.loadMonths((ComboBox<String>) cmbMonth);

        colTutorId.setCellValueFactory(new PropertyValueFactory<>("tutorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colPayerId.setCellValueFactory(new PropertyValueFactory<>("payerId"));

        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        loadSalaryData(MonthName.getMonthName(String.valueOf(LocalDate.now()))  ,year);

    }

    private void loadSalaryData(String monthName, int year) {

        ObservableList<TutorSalaryInfoTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = TutorSalaryModel.getAllData(monthName,year);
            while (result.next()){
                Tutor tutor = TutorModel.getTutorDetails(result.getString("tutorId"));
                data.add(
                        new TutorSalaryInfoTM(
                                result.getString("tutorId"),
                                tutor.getName(),
                                tutor.getSubject(),
                                result.getDouble("salary"),
                                result.getString("month"),
                                result.getString("date"),
                                result.getString("payerId")
                        ));
            }
            tblTutorSalary.setItems(data);
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
        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        if(txtYear.getText()==null && cmbMonth.getValue()==null){
            loadSalaryData(MonthName.getMonthName(String.valueOf(LocalDate.now())), year);
        }else if(txtYear.getText()!=null && cmbMonth.getValue()==null){
            loadSalaryData(MonthName.getMonthName(String.valueOf(LocalDate.now())), Integer.parseInt(txtYear.getText()));
        }else if(txtYear.getText()==null && cmbMonth.getValue()!=null){
            loadSalaryData(String.valueOf(cmbMonth.getValue()), year);
        }else{
            loadSalaryData(String.valueOf(cmbMonth.getValue()), Integer.parseInt(txtYear.getText()));
        }

    }

    @FXML
    void cmbMonthOnAction(ActionEvent event) {

    }

    @FXML
    void txtYearOnAction(ActionEvent event) {

    }


}
