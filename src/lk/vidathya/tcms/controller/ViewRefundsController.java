package lk.vidathya.tcms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.PaymentModel;
import lk.vidathya.tcms.model.RefundModel;
import lk.vidathya.tcms.model.StudentModel;
import lk.vidathya.tcms.tableModel.RefundsTM;
import lk.vidathya.tcms.transferObject.Payment;
import lk.vidathya.tcms.transferObject.Student;
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

public class ViewRefundsController implements Initializable {

    public AnchorPane context;
    public TableColumn colPayerId;
    @FXML
    private TableView<RefundsTM> tblRefunds;

    @FXML
    private TableColumn<?, ?> colPaymentCode;

    @FXML
    private TableColumn<?, ?> colPaymentDescription;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colRefundDate;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnRefresh;

    @FXML
    private ComboBox<?> cmbMonth;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataLoad.loadMonths((ComboBox<String>) cmbMonth);

        colPaymentCode.setCellValueFactory(new PropertyValueFactory<>("paymentCode"));
        colPaymentDescription.setCellValueFactory(new PropertyValueFactory<>("paymentDescription"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colRefundDate.setCellValueFactory(new PropertyValueFactory<>("refundDate"));
        colPayerId.setCellValueFactory(new PropertyValueFactory<>("payerId"));

        loadRefundData(MonthName.getMonthName(String.valueOf(LocalDate.now())));
    }

    private void loadRefundData(String monthName) {
        ObservableList<RefundsTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = RefundModel.getAllData(monthName);
            while (result.next()){
                Student student = StudentModel.getStudentDetails(result.getString("studentId"));
                Payment payment = PaymentModel.getPaymentDetails(result.getString("paymentCode"));

                data.add(
                        new RefundsTM(
                                result.getString("paymentCode"),
                                result.getString("description"),
                                result.getString("studentId"),
                                student.getName(),
                                payment.getDate(),
                                result.getString("date"),
                                result.getString("staffId")
                        ));

            }
            tblRefunds.setItems(data);
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
        loadRefundData(String.valueOf(cmbMonth.getValue()));
    }

    @FXML
    void cmbMonthOnAction(ActionEvent event) {

    }


}
