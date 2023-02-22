package lk.vidathya.tcms.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.PaymentModel;
import lk.vidathya.tcms.model.RefundModel;
import lk.vidathya.tcms.model.StudentModel;
import lk.vidathya.tcms.transferObject.Payment;
import lk.vidathya.tcms.transferObject.Refund;
import lk.vidathya.tcms.transferObject.Student;
import lk.vidathya.tcms.util.LoginCredentials;
import lk.vidathya.tcms.util.MonthName;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class RefundsFormController {

    public AnchorPane context;
    public Label lblUserId;
    @FXML
    private Label lblDate;

    @FXML
    private Button btnPayBack;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtPaymentDate;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPaymentDescription;

    @FXML
    private TextField txtPaymentCode;

    public void initialize(){
        lblDate.setText(String.valueOf(LocalDate.now()));
        lblUserId.setText(LoginCredentials.getCurrentUser());

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    @FXML
    void btnPayBackOnAction(ActionEvent event) {
        Refund refund = new Refund(
                txtPaymentCode.getText(),
                txtStudentId.getText(),
                txtPaymentDescription.getText(),
                Double.parseDouble(txtAmount.getText()),
                lblDate.getText(),
                LoginCredentials.getCurrentUser(),
                MonthName.getMonthName(String.valueOf(LocalDate.now()))
        );

        try {
            boolean isDone = RefundModel.makeRefund(refund);
            if(isDone){
                Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Refund Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                if(choose.get()==ButtonType.OK){
                    try {
                        Navigation.navigate(Routes.REFUNDS,context);
                    } catch (IOException e) {
                        new Alert(Alert.AlertType.ERROR,e+"").show();
                    }
                }

            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {

    }

    @FXML
    void txtPaymentCodeOnAction(ActionEvent event) {
        try {
            Payment payment = PaymentModel.getPaymentDetails(txtPaymentCode.getText());
            txtStudentId.setText(payment.getStudentId());
            txtPaymentDescription.setText(payment.getDescription());
            txtAmount.setText(String.valueOf(payment.getFee()));
            txtPaymentDate.setText(payment.getDate());

            Student student = StudentModel.getStudentDetails(txtStudentId.getText());
            txtStudentName.setText(student.getName());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void txtPaymentDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtPaymentDescriptionOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentNameOnAction(ActionEvent event) {

    }
}
