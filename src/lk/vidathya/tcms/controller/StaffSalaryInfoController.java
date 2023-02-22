package lk.vidathya.tcms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.StaffModel;
import lk.vidathya.tcms.tableModel.StaffSalaryInfoTM;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StaffSalaryInfoController implements Initializable {

    public AnchorPane context;
    public TableColumn colJoinDate;
    @FXML
    private TableView<StaffSalaryInfoTM> tblSalarySheet;

    @FXML
    private TableColumn<?, ?> colStaffId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colJob;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private Button btnOk;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStaffId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colJob.setCellValueFactory(new PropertyValueFactory<>("job"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colJoinDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));

        loadTableData();
    }

    private void loadTableData() {
        ObservableList<StaffSalaryInfoTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = StaffModel.getAllStaffDetails();
            while (result.next()){

                data.add(
                        new StaffSalaryInfoTM(
                                result.getString("staffId"),
                                result.getString("name"),
                                result.getString("job"),
                                result.getDouble("salary"),
                                result.getString("date")

                        ));
            }
            tblSalarySheet.setItems(data);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.FINANCEMANAGE,context);
    }


}
