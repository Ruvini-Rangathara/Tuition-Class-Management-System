package lk.vidathya.tcms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.util.InstituteData;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.RegExPatterns;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinanceManageController implements Initializable {


    public AnchorPane financeManageContext;
    public Label lblInvalidEmailAddress;
    public TextField txtEmail;
    public Button btnEmailUpdate;
    public Label lblInvalidContactNo;
    public TextField txtContactNo;
    public Button btnContactNoUpdate;
    public Label lblInvalidWhatsAppContactNo;
    public TextField txtWhatsAppContactNo;
    public Button btnWhatsAppContactNoUpdate;
    @FXML
    private Label lblInvalidRegistrationFee;

    @FXML
    private Label lblInvalidSalaryRate;

    @FXML
    private TextField txtRegistrationFee;

    @FXML
    private TextField txtSalaryRate;

    @FXML
    private Button btnRegistrationFeeUpdate;

    @FXML
    private Button btnSalaryRateUpdate;

    @FXML
    private Button btnClassFeeInfo;

    @FXML
    private Button btnStaffSalaryInfo;

    @FXML
    private Button btnIncomeExpenditure;

    @FXML
    private Button btnPayStaffSalaries;

    @FXML
    private Button btnPayTutorSalaries;

    @FXML
    private Button btnOk;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setLabelVisibility();
    }

    private  void setLabelVisibility(){
        lblInvalidEmailAddress.setVisible(false);
        lblInvalidRegistrationFee.setVisible(false);
        lblInvalidSalaryRate.setVisible(false);
        lblInvalidContactNo.setVisible(false);
        lblInvalidWhatsAppContactNo.setVisible(false);
    }

    @FXML
    void btnClassFeeInfoOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.CLASSFEEINFO,financeManageContext);
    }

    @FXML
    void btnIncomeExpenditureOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.INCOMEANDEXPENDITURE,financeManageContext);
    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,financeManageContext);
    }

    @FXML
    void btnPayStaffSalariesOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.PAYSTAFFSALARIES,financeManageContext);
    }

    @FXML
    void btnPayTutorSalariesOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.PAYTUTORSALARIES,financeManageContext);
    }

    @FXML
    void btnRegistrationFeeUpdateOnAction(ActionEvent event) {
        setLabelVisibility();
        boolean isFeeMatched = RegExPatterns.getSalaryPattern().matcher(txtRegistrationFee.getText()).matches();

        if(isFeeMatched){
            InstituteData.setRegistrationFee(Double.parseDouble(txtRegistrationFee.getText()));
        }else{
            lblInvalidRegistrationFee.setVisible(true);
            txtRegistrationFee.requestFocus();
        }

    }

    @FXML
    void btnSalaryRateUpdateOnAction(ActionEvent event) {
        setLabelVisibility();
        boolean isRateMatched = RegExPatterns.getRatePattern().matcher(txtSalaryRate.getText()).matches();

        if(isRateMatched){
            InstituteData.setSalaryRate(Double.parseDouble(txtSalaryRate.getText()));
        }else {
            lblInvalidSalaryRate.setVisible(true);
            txtSalaryRate.requestFocus();
        }

    }

    public void btnEmailUpdateOnAction(ActionEvent actionEvent) {
        setLabelVisibility();
        boolean isEmailMatched = RegExPatterns.getEmailPattern().matcher(txtEmail.getText()).matches();

        if(isEmailMatched){
            InstituteData.setEmail(txtEmail.getText());
        }else{
            lblInvalidEmailAddress.setVisible(true);
            txtEmail.requestFocus();
        }

    }

    @FXML
    void btnStaffSalaryInfoOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.STAFFSALARYINFO,financeManageContext);
    }


    @FXML
    void txtRegistrationFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtSalaryRateOnAction(ActionEvent event) {

    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
    }


    public void txtContactNoOnAction(ActionEvent actionEvent) {
    }

    public void btnContactNoUpdateOnAction(ActionEvent actionEvent) {
        setLabelVisibility();
        boolean isContactNoMatched = RegExPatterns.getMobilePattern().matcher(txtContactNo.getText()).matches();

        if(isContactNoMatched){
            InstituteData.setContactNo(txtContactNo.getText());
        }else{
            lblInvalidContactNo.setVisible(true);
            txtContactNo.requestFocus();
        }

    }

    public void txtWhatsAppContactNoOnAction(ActionEvent actionEvent) {

    }

    public void btnWhatsAppContactNoUpdateOnAction(ActionEvent actionEvent) {
        setLabelVisibility();
        boolean isContactNoMatched = RegExPatterns.getMobilePattern().matcher(txtWhatsAppContactNo.getText()).matches();

        if(isContactNoMatched){
            InstituteData.setWhatsAppContactNo(txtWhatsAppContactNo.getText());
        }else{
            lblInvalidWhatsAppContactNo.setVisible(true);
            txtWhatsAppContactNo.requestFocus();
        }

    }


}
