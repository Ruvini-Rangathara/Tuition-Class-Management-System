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
import lk.vidathya.tcms.tableModel.NotPaidTutorSalaryTM;
import lk.vidathya.tcms.tableModel.PaidStaffSalaryTM;
import lk.vidathya.tcms.tableModel.PaidTutorSalaryTM;
import lk.vidathya.tcms.transferObject.*;
import lk.vidathya.tcms.util.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class PayTutorSalariesController {
    public ComboBox cmbTutorId;
    public TableView tblTutorSalaryNotPaid;
    public TableView tblTutorSalary;
    public TableColumn colTutorId;
    public TableColumn colNotPaidTutorId;
    public TableColumn colNotPaidGrade;
    public TableColumn colClassCode;
    public Button btnRefresh;
    public TextField txtSalary;
    public TextField txtNetSalary;
    public ComboBox cmbClassCode;
    public TableColumn colNotPaidClassCode;
    @FXML
    private AnchorPane context;


    @FXML
    private Label lblDate;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colName;

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
    private TableColumn<?, ?> colNotPaidName;

    @FXML
    private TableColumn<?, ?> colNotPaidSubject;

    @FXML
    private TableColumn<?, ?> colNotPaidSalary;

    @FXML
    private Button btnPay;

    @FXML
    private Label lblPaymentCode;

    public void initialize(){
        lblDate.setText(String.valueOf(LocalDate.now()));
        DataLoad.loadMonths((ComboBox<String>) cmbMonth);
        setPaymentCode();

        DataLoad.loadTutorId(cmbTutorId);
        DataLoad.loadClassCode(cmbClassCode);

        colNotPaidTutorId.setCellValueFactory(new PropertyValueFactory<>("notPaidTutorId"));
        colNotPaidName.setCellValueFactory(new PropertyValueFactory<>("notPaidName"));
        colNotPaidSubject.setCellValueFactory(new PropertyValueFactory<>("notPaidSubject"));
        colNotPaidGrade.setCellValueFactory(new PropertyValueFactory<>("notPaidGrade"));
        colNotPaidClassCode.setCellValueFactory(new PropertyValueFactory<>("notPaidClassCode"));
        colNotPaidSalary.setCellValueFactory(new PropertyValueFactory<>("notPaidSalary"));


        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentCode"));
        colTutorId.setCellValueFactory(new PropertyValueFactory<>("tutorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colClassCode.setCellValueFactory(new PropertyValueFactory<>("classCode"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colPayerId.setCellValueFactory(new PropertyValueFactory<>("payerId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        loadPaidData(MonthName.getMonthName(String.valueOf(LocalDate.now()))  ,year);

        loadNotPaidData(MonthName.getMonthName(String.valueOf(LocalDate.now()))  ,year);

    }

    private void loadNotPaidData(String monthName, int year) {

        ObservableList<NotPaidTutorSalaryTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = TutorSalaryModel.getAllNotPaidData(monthName,year);
            while (result.next()){
                Classes classes = ClassModel.getClassDetails(result.getString("classCode"));
                Tutor tutor = TutorModel.getTutorDetails(result.getString("tutorId"));
                data.add(
                        new NotPaidTutorSalaryTM(
                                result.getString("tutorId"),
                                tutor.getName(),
                                classes.getSubject(),
                                classes.getGrade(),
                                result.getString("classCode"),
                                result.getDouble("salary")
                        ));

//                System.out.print(result.getString("tutorId")+"    ");
//                System.out.print(tutor.getName()+"    ");
//                System.out.print(classes.getSubject()+"    ");
//                System.out.print(classes.getGrade()+"    ");
//                System.out.print(tutor.getEmail()+"    ");
//                System.out.print(result.getDouble("salary")+"    ");
            }
            tblTutorSalaryNotPaid.setItems(data);
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPaidData(String monthName, int year) {

        ObservableList<PaidTutorSalaryTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = TutorSalaryModel.getAllData(monthName,year);
            while (result.next()){
                Tutor tutor = TutorModel.getTutorDetails(result.getString("tutorId"));
                data.add(
                        new PaidTutorSalaryTM(
                                result.getString("paymentCode"),
                                result.getString("tutorId"),
                                tutor.getName(),
                                result.getString("classCode"),
                                result.getDouble("salary"),
                                LoginCredentials.getCurrentUser(),
                                lblDate.getText()
                        ));
//                System.out.print(result.getString("paymentCode")+"    ");
//                System.out.print(result.getString("tutorId")+"    ");
//                System.out.print(tutor.getName()+"    ");
//                System.out.print(result.getString("classCode")+"    ");
//                System.out.print(result.getDouble("salary")+"    ");
//                System.out.print(LoginCredentials.getCurrentUser()+"    ");
//                System.out.println(lblDate.getText()+"    \n");
            }
            tblTutorSalary.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    private void setPaymentCode() {
        try {
            String lastPaymentCode= TutorSalaryModel.getLastPaymentCode();
            if(lastPaymentCode==null){
                lblPaymentCode.setText("TP0001");
            }else{
                String[] split=lastPaymentCode.split("[T][P]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newPaymentCode=String.format("TP%04d", lastDigits);
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

        if( !(String.valueOf(cmbTutorId.getValue()).equals(null)) ){
            Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Confirm?",ButtonType.YES,ButtonType.NO).showAndWait();
            if(choose.get()==ButtonType.YES){

                try {
                    Tutor tutor = TutorModel.getTutorDetails(String.valueOf(cmbTutorId.getValue()));
                    TutorSalary tutorSalary = new TutorSalary(
                            lblPaymentCode.getText(),
                            String.valueOf(cmbClassCode.getValue()),
                            String.valueOf(cmbTutorId.getValue()),
                            year,
                            month,
                            Double.parseDouble(txtNetSalary.getText()),
                            lblDate.getText(),
                            LoginCredentials.getCurrentUser()

                    );

                    boolean isAdd = TutorSalaryModel.addPayment(tutorSalary);
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

    public void cmbTutorIdOnAction(ActionEvent actionEvent) {
        if(cmbClassCode.getValue()!=null){
            try {
                double salary = TutorSalaryModel.getNotPaidTutorSalary(String.valueOf(cmbTutorId.getValue()), String.valueOf(cmbClassCode.getValue()));
                txtSalary.setText(String.valueOf(salary));

                double netSalary = salary - (((salary*InstituteData.getSalaryRate()))/100);
                txtNetSalary.setText(String.valueOf(netSalary));

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

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

    public void cmbClassCodeOnAction(ActionEvent actionEvent) {
        if(cmbTutorId.getValue()!=null){
            try {
                double salary = TutorSalaryModel.getNotPaidTutorSalary(String.valueOf(cmbTutorId.getValue()), String.valueOf(cmbClassCode.getValue()));
                txtSalary.setText(String.valueOf(salary));

                double netSalary = salary - (((salary*InstituteData.getSalaryRate()))/100);
                txtNetSalary.setText(String.valueOf(netSalary));

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
