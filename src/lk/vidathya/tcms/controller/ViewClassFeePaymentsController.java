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
import lk.vidathya.tcms.tableModel.ClassFeeNotPaidTM;
import lk.vidathya.tcms.tableModel.ClassFeePaymentTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Mail;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewClassFeePaymentsController implements Initializable {

    public AnchorPane context;
    public ComboBox cmbClassCode;
    public TextField txtGrade;
    public TextField txtSubject;
    public TextField txtYear;
    public Button btnSendMail;
    public Button btnBack;
    public TableColumn colNotPaidMail;
    public TableColumn colNotPaidEmail;
    public TableColumn colNotPaidAmount;
    public TableColumn colAmount;
    public TableView tblClassFeePaid;


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
    private Button btnRefresh;

    @FXML
    private ComboBox<?> cmbMonth;

    @FXML
    private TableView<ClassFeeNotPaidTM> tblNotPaid;

    @FXML
    private TableColumn<?, ?> colNotPaidStudentId;

    @FXML
    private TableColumn<?, ?> colNotPaidStudentName;

    @FXML
    private TableColumn<?, ?> colNotPaidContactNo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataLoad.loadMonths((ComboBox<String>) cmbMonth);
        DataLoad.loadClassCode(cmbClassCode);


        colPaymentCode.setCellValueFactory(new PropertyValueFactory<>("paymentCode"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStaffId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        colStaffName.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        colNotPaidStudentId.setCellValueFactory(new PropertyValueFactory<>("notPaidStudentId"));
        colNotPaidStudentName.setCellValueFactory(new PropertyValueFactory<>("notPaidStudentName"));
        colNotPaidContactNo.setCellValueFactory(new PropertyValueFactory<>("notPaidContactNo"));
        colNotPaidEmail.setCellValueFactory(new PropertyValueFactory<>("notPaidEmail"));
        colNotPaidAmount.setCellValueFactory(new PropertyValueFactory<>("notPaidAmount"));


    }

    private void loadPaidData(String classCode, String month, int year){
        ObservableList<ClassFeePaymentTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = PaymentModel.getPaidData(classCode,year, month);
            while (result.next()){
                Student student = StudentModel.getStudentDetails(result.getString("studentId"));
                Staff staff = StaffModel.getStaffDetails(result.getString("staffId"));
                data.add(
                        new ClassFeePaymentTM(
                                result.getString("paymentCode"),
                                result.getString("studentId"),
                                student.getName(),
                                result.getString("date"),
                                result.getDouble("fee"),
                                result.getString("staffId"),
                                staff.getName()
                        ));
            }
            tblClassFeePaid.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


    private void loadNotPaidData(String classCode, String month, int year){
        ObservableList<ClassFeeNotPaidTM> data = FXCollections.observableArrayList();
        try {

            ResultSet result = PaymentModel.getPaidData(classCode,year, month);
            ArrayList<String> paidArrayList = new ArrayList<>();
            while (result.next()){
                paidArrayList.add(result.getString("studentId"));

            }

            ResultSet result2 = StudentClassModel.getAllData(classCode);
            ArrayList<String> allStudentArrayList = new ArrayList<>();
            while (result2.next()){
                allStudentArrayList.add(result2.getString("studentId"));

            }

            ArrayList<String> notPaidArrayList = new ArrayList<>();

            for(int i=0; i<allStudentArrayList.size(); i++){
                for (int j=0; j<paidArrayList.size(); j++){
                    if(!(allStudentArrayList.get(i).equals(paidArrayList.get(j)))){
                        notPaidArrayList.add(allStudentArrayList.get(i));
                        break;
                    }
                }
            }

            for (String notPaidStudent : notPaidArrayList){
                Student student = StudentModel.getStudentDetails(notPaidStudent);
                Classes classes = ClassModel.getClassDetails(classCode);
                data.add(new ClassFeeNotPaidTM(
                        student.getStudentId(),
                        student.getName(),
                        student.getContactNo(),
                        student.getEmail(),
                        classes.getClassFee()
                ));
            }
            tblNotPaid.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }


    @FXML
    void btnRefreshOnAction(ActionEvent event) {

        if( (cmbClassCode.getValue()!=null) && (txtYear.getText()!=null) && (cmbMonth.getValue()!=null) ){
            loadPaidData(String.valueOf(cmbClassCode.getValue()), String.valueOf(cmbMonth.getValue()), Integer.parseInt(txtYear.getText()));
            loadNotPaidData(String.valueOf(cmbClassCode.getValue()), String.valueOf(cmbMonth.getValue()), Integer.parseInt(txtYear.getText()));

        }
    }

    @FXML
    void cmbMonthOnAction(ActionEvent event) {

    }

    public void cmbClassCodeOnAction(ActionEvent actionEvent) {
        try {
            Classes classes = ClassModel.getClassDetails((String) cmbClassCode.getValue());
            txtGrade.setText(classes.getGrade());
            txtSubject.setPromptText(classes.getSubject());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public void txtGradeOnAction(ActionEvent actionEvent) {
    }

    public void txtSubjectOnAction(ActionEvent actionEvent) {
    }

    public void txtYearOnAction(ActionEvent actionEvent) {

    }

    public void btnSendMailOnAction(ActionEvent actionEvent) {
        String classCode = String.valueOf(cmbClassCode.getValue());
        int year = Integer.parseInt(txtYear.getText());
        String month = String.valueOf(cmbMonth.getValue());

        try {
            ResultSet result = PaymentModel.getPaidData(classCode,year, month);
            ArrayList<String> paidArrayList = new ArrayList<>();
            while (result.next()){
                paidArrayList.add(result.getString("studentId"));
            }

            ResultSet result2 = StudentClassModel.getAllData(classCode);
            ArrayList<String> allStudentArrayList = new ArrayList<>();
            while (result2.next()){
                allStudentArrayList.add(result2.getString("studentId"));

            }

            ArrayList<String> notPaidArrayList = new ArrayList<>();

            for(int i=0; i<allStudentArrayList.size(); i++){
                for (int j=0; j<paidArrayList.size(); j++){
                    if(!(allStudentArrayList.get(i).equals(paidArrayList.get(j)))){
                        notPaidArrayList.add(allStudentArrayList.get(i));
                        break;
                    }
                }
            }

            String subject = "Notification of non-payment of class fees";
            for (String notPaidStudent : notPaidArrayList){
                Student student = StudentModel.getStudentDetails(notPaidStudent);
                Classes classes = ClassModel.getClassDetails(classCode);
                Tutor tutor = TutorModel.getTutorDetails(classes.getTutorId());

                String text = "Grade    :  "+classes.getGrade()+"\nSubject     :  "+classes.getSubject()+"\nTutor    :  "+tutor.getName()+"\n\nKindly pay the class fees.";
                if(student.getEmail()!=null){
                    Mail.sendMail(student.getEmail(), subject,text);
                }

//                System.out.println(student.getEmail());
//                System.out.println(subject);
//                System.out.println(text);
//                System.out.println();
//                System.out.println();
            }


            new Alert(Alert.AlertType.CONFIRMATION,"Send Mails SuccessFully!").show();

        } catch (SQLException | ClassNotFoundException | MessagingException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }


}
