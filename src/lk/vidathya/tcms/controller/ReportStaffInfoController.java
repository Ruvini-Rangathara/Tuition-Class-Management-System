package lk.vidathya.tcms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.model.StaffModel;
import lk.vidathya.tcms.tableModel.StaffInfoTM;
import lk.vidathya.tcms.util.InstituteData;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ReportStaffInfoController implements Initializable {


    public TextField txtCount;
    @FXML
    private AnchorPane context;

    @FXML
    private TableView<StaffInfoTM> tblStaffInfo;

    @FXML
    private TableColumn<?, ?> colStaffId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colJob;

    @FXML
    private TableColumn<?, ?> colNicNo;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnGeneratePDF;

    @FXML
    private Button btnPrintReport;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStaffId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colJob.setCellValueFactory(new PropertyValueFactory<>("job"));
        colNicNo.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        loadTableData();
    }
    private void loadTableData() {
        ObservableList<StaffInfoTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = StaffModel.getAllStaffDetails();
            int count=0;
            while (result.next()){
                count++;
                data.add(
                        new StaffInfoTM(
                                result.getString("staffId"),
                                result.getString("name"),
                                result.getString("job"),
                                result.getString("nic"),
                                result.getString("contactNo"),
                                result.getString("email")

                        ));
            }
            tblStaffInfo.setItems(data);
            txtCount.setText(String.valueOf(count));
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void btnGeneratePDFOnAction(ActionEvent event)throws RuntimeException  {

        HashMap<String,Object> hm = new HashMap<>();
        hm.put("address", InstituteData.getAddress());
        hm.put("contactNo", InstituteData.getContactNo());
        hm.put("whatsAppNo", InstituteData.getWhatsAppContactNo());
        hm.put("email", InstituteData.getEmail());

        InputStream inputStream = this.getClass().getResourceAsStream("..//report//StaffInfo.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,hm, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.REPORTS, context);
    }

    @FXML
    void btnPrintReportOnAction(ActionEvent event)throws RuntimeException  {

        HashMap<String,Object> hm = new HashMap<>();
        hm.put("address", InstituteData.getAddress());
        hm.put("contactNo", InstituteData.getContactNo());
        hm.put("whatsAppNo", InstituteData.getWhatsAppContactNo());
        hm.put("email", InstituteData.getEmail());

        InputStream inputStream = this.getClass().getResourceAsStream("..//report//StaffInfo.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,hm, DBConnection.getInstance().getConnection());
            JasperPrintManager.printReport(jasperPrint,true);
            //JasperViewer.viewReport(jasperPrint);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            System.out.println(e);

        }

    }

    public void txtCountOnAction(ActionEvent actionEvent) {

    }
}
