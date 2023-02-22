package lk.vidathya.tcms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.IncomeAndExpenditureModel;
import lk.vidathya.tcms.tableModel.IncomeExpenditureTM;
import lk.vidathya.tcms.transferObject.IncomeAndExpenditure;
import lk.vidathya.tcms.util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class IncomeAndExpenditureFormController implements Initializable {

    public AnchorPane context;
    public Button btnBack;
    public ComboBox cmbMonth;
    public TextField txtYear;
    @FXML
    private TableView<IncomeExpenditureTM> tblIncome;

    @FXML
    private TableColumn<?, ?> colIncomeDate;

    @FXML
    private TableColumn<?, ?> colIncomeDescription;

    @FXML
    private TableColumn<?, ?> colIncomeAmount;

    @FXML
    private TableView<IncomeExpenditureTM> tblExpenditure;

    @FXML
    private TableColumn<?, ?> colExpenditureDate;

    @FXML
    private TableColumn<?, ?> colExpenditureDescription;

    @FXML
    private TableColumn<?, ?> colExpenditureAmount;

    @FXML
    private TextField txtIncomeTotal;

    @FXML
    private TextField txtExpenditureTotal;

    @FXML
    private TextField txtNetProfit;

    @FXML
    private Button btnOk;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDescription;

    @FXML
    private DatePicker dteDate;

    @FXML
    private ComboBox<?> cmbIncomeExpenditure;

    @FXML
    private Button btnAdd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataLoad.loadIncomeAndExpenditureToComboBox((ComboBox<String>) cmbIncomeExpenditure);
        dteDate.setPromptText(String.valueOf(LocalDate.now()));

        DataLoad.loadMonths(cmbMonth);
        String monthName = MonthName.getMonthName(String.valueOf(LocalDate.now()));


        colIncomeDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colIncomeDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colIncomeAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        colExpenditureDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colExpenditureDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colExpenditureAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        loadBudgetData(year,monthName);


    }

    public void loadBudgetData(int year,String month){
        ObservableList<IncomeExpenditureTM> incomeData = FXCollections.observableArrayList();
        try {
            ResultSet result = IncomeAndExpenditureModel.getIncomeDataTable(year, month);
            while (result.next()){
                incomeData.add(
                        new IncomeExpenditureTM(
                                result.getString("date"),
                                result.getString("description"),
                                result.getDouble("amount")
                        ));
            }
            tblIncome.setItems(incomeData);
            calculateTotal(incomeData,txtIncomeTotal);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }


        ObservableList<IncomeExpenditureTM> expenditureData = FXCollections.observableArrayList();
        try {
            if(month==null){
                month=cmbMonth.getPromptText();
            }
            ResultSet result = IncomeAndExpenditureModel.getExpenditureDataTable(year, month);
            while (result.next()){
                expenditureData.add(
                        new IncomeExpenditureTM(
                                result.getString("date"),
                                result.getString("description"),
                                result.getDouble("amount")
                        ));
            }
            tblExpenditure.setItems(expenditureData);
            calculateTotal(expenditureData,txtExpenditureTotal);


        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

        double netProfit = Double.parseDouble(txtIncomeTotal.getText())-Double.parseDouble(txtExpenditureTotal.getText());
        txtNetProfit.setText(String.valueOf(netProfit));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String date = String.valueOf(dteDate.getValue());
        String month = MonthName.getMonthName(date);
        int year = Integer.parseInt(date.split("-")[0]);

        IncomeAndExpenditure incomeAndExpenditure = new IncomeAndExpenditure(
                LoginCredentials.getCurrentUser(),
                String.valueOf(cmbIncomeExpenditure.getValue()),
                txtDescription.getText(),
                Double.parseDouble(txtAmount.getText()),
                year,
                month,
                date
        );

        try {
            boolean isAdd = IncomeAndExpenditureModel.addIncomeAndExpenditure(incomeAndExpenditure);
            if(isAdd){

                cmbMonth.setPromptText("");
                txtDescription.setText("");
                dteDate.setPromptText("");
                txtAmount.setText("");
                cmbIncomeExpenditure.setPromptText("");

                //int y = Integer.parseInt(String.valueOf(dteDate.getValue()).split("-")[0]);
                //int y = Integer.parseInt(String.valueOf(dteDate.getValue()).split("-")[0]);
                //String m = MonthName.getMonthName(String.valueOf(dteDate.getValue()));
               // loadBudgetData(year,m);


            }else{
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong.").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


    private void calculateTotal(ObservableList<IncomeExpenditureTM> data, TextField txt) {
        double total=0;
        for (IncomeExpenditureTM i:data) {
            total+=i.getAmount();
        }
        txt.setText(String.valueOf(total));
    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    @FXML
    void cmbIncomeExpenditureOnAction(ActionEvent event) {

    }

    @FXML
    void dteDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {

    }

    @FXML
    void txtDescriptionOnAction(ActionEvent event) {

    }

    @FXML
    void txtExpenditureTotalOnAction(ActionEvent event) {

    }

    @FXML
    void txtIncomeTotalOnAction(ActionEvent event) {

    }

    @FXML
    void txtNetProfitOnAction(ActionEvent event) {

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.FINANCEMANAGE,context);
    }


    public void cmbMonthOnAction(ActionEvent actionEvent) {

        //loadBudgetData((String) cmbMonth.getValue());
    }

    public void txtYearOnAction(ActionEvent actionEvent) {
    }
}
