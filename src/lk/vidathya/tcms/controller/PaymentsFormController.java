package lk.vidathya.tcms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.model.*;
import lk.vidathya.tcms.transferObject.*;
import lk.vidathya.tcms.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public class PaymentsFormController {


    public AnchorPane context;
    public Label lblPaymentCode;
    public Label lblUserId;
    public TextField txtYear;
    public Button btnBackToAttendance;
    public Button btnPrintBil;


    @FXML
    private Label lblDate;

    @FXML
    private Button btnPay;

    @FXML
    private Button btnCancel;

    @FXML
    private ComboBox<?> cmbClassCode;

    @FXML
    private TextField txtGrade;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtStudentId;

    @FXML
    private ComboBox<?> cmbMonth;

    @FXML
    private ComboBox<?> cmbDescription;

    public void initialize(){
        setPaymentCode();
        lblDate.setText(String.valueOf(LocalDate.now()));

        DataLoad.loadClassCode((ComboBox<String>) cmbClassCode);

        DataLoad.loadPaymentDescription((ComboBox<String>) cmbDescription);
        DataLoad.loadMonths((ComboBox<String>) cmbMonth);

        lblUserId.setText(LoginCredentials.getCurrentUser());

        txtYear.setText(String.valueOf(LocalDate.now()).split("-")[0]);


    }

    private void setPaymentCode() {
            try {
                String lastPaymentCode= PaymentModel.getLastPaymentCode();
                if(lastPaymentCode==null){
                    lblPaymentCode.setText("P0001");
                }else{
                    String[] split=lastPaymentCode.split("[P]");
                    int lastDigits=Integer.parseInt(split[1]);
                    lastDigits++;
                    String newPaymentCode=String.format("P%04d", lastDigits);
                    lblPaymentCode.setText(newPaymentCode);
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e+"").show();
            }

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    @FXML
    void btnPayOnAction(ActionEvent event) {

        String description=null;
        if(String.valueOf(cmbDescription.getValue())==null){
            description=String.valueOf(cmbDescription.getPromptText());
        }else{
            description= String.valueOf(cmbDescription.getValue());
        }
        //System.out.println(description +"    description");

        String classCode=null;
        if(String.valueOf(cmbClassCode.getValue())==null){
            classCode=String.valueOf(cmbClassCode.getPromptText());
        }else{
            classCode= String.valueOf(cmbClassCode.getValue());
        }
//        System.out.println(classCode+"  classCode");

//        String month=null;
//        if(String.valueOf(cmbMonth.getValue())==null){
//            month=String.valueOf(cmbMonth.getPromptText());
//        }else{
//            month= String.valueOf(cmbMonth.getValue());
//        }
//
//        System.out.println(month+"  month");

        Optional<ButtonType> choose = new Alert(Alert.AlertType.WARNING,"Confirm?",ButtonType.YES,ButtonType.NO).showAndWait();
        if(choose.get()==ButtonType.YES){

            try {

                if(description.equals("Class Fee")){

                    boolean isExistStudent = StudentClassModel.existStudent(classCode, txtStudentId.getText());
                    if(isExistStudent){

                        Payment payment = new Payment(
                                lblPaymentCode.getText(),
                                description,
                                classCode,
                                Double.parseDouble(txtFee.getText()),
                                txtStudentId.getText(),
                                Integer.parseInt(txtYear.getText()),
                                String.valueOf(cmbMonth.getValue()),
                                lblDate.getText(),
                                lblUserId.getText()
                        );
//                        System.out.print(lblPaymentCode.getText()+"     ");
//                        System.out.print(description+"     ");
//                        System.out.print(classCode+"     ");
//                        System.out.print(Double.parseDouble(txtFee.getText())+"     ");
//                        System.out.print(txtStudentId.getText()+"     ");
//                        System.out.print(Integer.parseInt(txtYear.getText())+"     ");
//                        System.out.print(String.valueOf(cmbMonth.getValue())+"     ");
//                        System.out.print(lblDate.getText()+"     ");
//                        System.out.print(lblUserId.getText()+"     ");

                        boolean isAdd = PaymentModel.addPayment(payment);
                        //System.out.println("is Add all  "+isAdd);

                        if(isAdd){
                            Student student = StudentModel.getStudentDetails(txtStudentId.getText());
                            String subject = "Class Fee Payment Accepted.";
                            String text="\n\n\nStudent ID : "+txtStudentId.getText()+"\nName : "+txtStudentName.getText()+"\n\nPayment Code : "+lblPaymentCode.getText()+"\nClass Code  : "+String.valueOf(cmbClassCode.getValue()+"\nGrade : "+txtGrade.getText()+"\nSubject : "+txtSubject.getText()+"\nMonth : "+String.valueOf(cmbMonth.getValue())+"\n\n\n       Thank You!");

                            Mail.sendMail(student.getEmail(),subject,text );
                            new Alert(Alert.AlertType.CONFIRMATION,"Success!").show();

                            Navigation.navigate(Routes.PAYMENTS,context);
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Check this student is in class").show();
                    }

                }

                if(description.equals("Registration Fee")){

                    RegistrationPayment registrationPayment = new RegistrationPayment(
                            Double.parseDouble(txtFee.getText()),
                            txtStudentId.getText(),
                            Integer.parseInt(txtYear.getText()),
                            String.valueOf(cmbMonth.getValue()),
                            lblDate.getText(),
                            lblUserId.getText()
                    );
                    boolean isAdd = RegistrationPaymentModel.addPayment(registrationPayment);


                    if(isAdd){
                        Navigation.navigate(Routes.PAYMENTS,context);
                        new Alert(Alert.AlertType.CONFIRMATION,"Success!").show();
                    }
                }

                } catch (IOException | SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR,e+"").show();
                } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }


    }




    @FXML
    void cmbClassCodeOnAction(ActionEvent event) {
        try {
            Classes classes =  ClassModel.getClassDetails((String) cmbClassCode.getValue());
            txtGrade.setText(classes.getGrade());
            txtSubject.setText(classes.getSubject());
            txtFee.setText(String.valueOf(classes.getClassFee()));

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void cmbDescriptionOnAction(ActionEvent event) {
        if(cmbDescription.getValue().equals("Registration Fee")){
            cmbClassCode.setDisable(true);
            txtFee.setText(String.valueOf(InstituteData.getRegistrationFee()));
            txtGrade.setText(null);
            txtSubject.setText(null);
            cmbClassCode.setPromptText(null);
        }
    }

    @FXML
    void cmbMonthOnAction(ActionEvent event) {

    }

    @FXML
    void txtFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtGradeOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {
        try {
            Student student = StudentModel.getStudentDetails(txtStudentId.getText());
            txtStudentName.setText(student.getName());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void txtStudentNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtSubjectOnAction(ActionEvent event) {

    }

    public void txtYearOnAction(ActionEvent actionEvent) {
    }

    public void btnBackToAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ATTENDANCE, context);
    }

    public void btnPrintBilOnAction(ActionEvent actionEvent) {
        Staff staff = null;
        try {
            staff = StaffModel.getStaffDetails(LoginCredentials.getCurrentUser());
            Student student = StudentModel.getStudentDetails(txtStudentId.getText());

            HashMap<String,Object> hm = new HashMap<>();
            hm.put("address",InstituteData.getAddress());
            hm.put("contactNo",InstituteData.getContactNo());
            hm.put("email",InstituteData.getEmail());
            hm.put("paymentCode",lblPaymentCode.getText());
            hm.put("date",lblDate.getText());
            hm.put("studentId",txtStudentId.getText());
            hm.put("name",student.getName());
            hm.put("description",String.valueOf(cmbDescription.getValue()));
            hm.put("amount",txtFee.getText());
            hm.put("staffName",staff.getName());

            getBill(hm);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getBill(HashMap<String, Object> hm) {
        try {
            //JasperDesign inputStream = JRXmlLoader.load(getClass().getResourceAsStream("lk/vidathya/tcms/report/ReceiptNew.jrxml"));
            InputStream inputStream = this.getClass().getResourceAsStream("../report/ReceiptNew.jrxml");

            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,hm, new JREmptyDataSource());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            System.out.println(e);

        }
    }
}
