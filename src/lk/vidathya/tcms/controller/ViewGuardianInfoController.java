package lk.vidathya.tcms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.GuardianModel;
import lk.vidathya.tcms.model.StudentClassModel;
import lk.vidathya.tcms.tableModel.GuardianTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.Guardian;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewGuardianInfoController implements Initializable {

    public AnchorPane context;

    @FXML
    private ComboBox<?> cmbClassCode;

    @FXML
    private Button btnRefresh;

    @FXML
    private TextField txtGrade;

    @FXML
    private TextField txtSubject;

    @FXML
    private TableView<GuardianTM> tblGuardian;

    @FXML
    private TableColumn<?, ?> colGuardianNic;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colOccupation;

    @FXML
    private Button btnOk;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataLoad.loadClassCode((ComboBox<String>) cmbClassCode);

        colGuardianNic.setCellValueFactory(new PropertyValueFactory<>("guardianNic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colOccupation.setCellValueFactory(new PropertyValueFactory<>("occupation"));
    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        ObservableList<GuardianTM> data = FXCollections.observableArrayList();
        if(cmbClassCode.getValue()!=null){
            try {
                ArrayList<String> guardianNicArray = StudentClassModel.getGuardianNic(String.valueOf(cmbClassCode.getValue()));
                for(String nic : guardianNicArray){
                    Guardian guardian = GuardianModel.getGuardianDetails(nic);
                    data.add(new GuardianTM(
                            guardian.getGuardianNic(),
                            guardian.getName(),
                            guardian.getContactNo(),
                            guardian.getEmail(),
                            guardian.getOccupation()
                    ));
                }

                tblGuardian.setItems(data);
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e+"").show();
            }
        }
    }

    @FXML
    void cmbClassCodeOnAction(ActionEvent event) {
        try {
            Classes classes =  ClassModel.getClassDetails((String) cmbClassCode.getValue());
            txtGrade.setText(classes.getGrade());
            txtSubject.setText(classes.getSubject());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


}
