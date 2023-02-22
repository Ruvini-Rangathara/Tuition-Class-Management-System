package lk.vidathya.tcms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.model.TutorModel;
import lk.vidathya.tcms.tableModel.TutorInfoTM;
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

public class ReportTutorInfoController implements Initializable {

    @FXML
    private AnchorPane context;

    @FXML
    private TableView<TutorInfoTM> tblTutors;

    @FXML
    private TableColumn<?, ?> colTutorId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSubject;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private Button btnOk;

    @FXML
    private TextField txtCount;

    @FXML
    private Button btnGeneratePDF;

    @FXML
    private Button btnPrintReport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colTutorId.setCellValueFactory(new PropertyValueFactory<>("tutorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadTableData();
    }

    private void loadTableData() {
        ObservableList<TutorInfoTM> data = FXCollections.observableArrayList();
        try {

            ResultSet result = TutorModel.getAllTutors();
            int count = 0;
            while (result.next()){
                count++;
                data.add(
                        new TutorInfoTM(
                                result.getString("tutorId"),
                                result.getString("name"),
                                result.getString("subject"),
                                result.getString("contactNo"),
                                result.getString("email")
                        ));
            }
            tblTutors.setItems(data);
            txtCount.setText(String.valueOf(count));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnGeneratePDFOnAction(ActionEvent event) {
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("address", InstituteData.getAddress());
        hm.put("contactNo", InstituteData.getContactNo());
        hm.put("whatsAppNo", InstituteData.getWhatsAppContactNo());
        hm.put("email", InstituteData.getEmail());

        InputStream inputStream = this.getClass().getResourceAsStream("..//report//TutorInfo.jrxml");
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
    void btnPrintReportOnAction(ActionEvent event) {
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("address", InstituteData.getAddress());
        hm.put("contactNo", InstituteData.getContactNo());
        hm.put("whatsAppNo", InstituteData.getWhatsAppContactNo());
        hm.put("email", InstituteData.getEmail());

        InputStream inputStream = this.getClass().getResourceAsStream("..//report//TutorInfo.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,hm, DBConnection.getInstance().getConnection());
            JasperPrintManager.printReport(jasperPrint,true);
            //JasperViewer.viewReport(jasperPrint);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            System.out.println(e);

        }
    }

    @FXML
    void txtCountOnAction(ActionEvent event) {

    }


}
