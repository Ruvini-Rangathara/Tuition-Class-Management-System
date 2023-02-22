package lk.vidathya.tcms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.HallModel;
import lk.vidathya.tcms.transferObject.Hall;
import lk.vidathya.tcms.util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class HallFormController implements Initializable {

    public AnchorPane context;
    public Button btnBack;
    public Label lblEnterHallNo;
    @FXML
    private Label lblDate;

    @FXML
    private Button btnCancel;

    @FXML
    private Label lblInvalidCapacity;

    @FXML
    private Button btnAddHall;

    @FXML
    private Button btnAddHallOption;

    @FXML
    private Button btnManageHallOption;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtHallNo;

    @FXML
    private TextField txtCapacity;

    @FXML
    private TextField txtAvailableFacilities;

    @FXML
    private ComboBox<?> cmbEnterHallNo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAddHall.setVisible(false);
        btnCancel.setVisible(false);
        btnUpdate.setVisible(false);

        lblEnterHallNo.setVisible(false);
        cmbEnterHallNo.setVisible(false);

        lblDate.setText(String.valueOf(LocalDate.now()));

        lblInvalidCapacity.setVisible(false);

        btnAddHallOption.setFocusTraversable(false);
        btnManageHallOption.setFocusTraversable(false);
    }


    private void setHallNo() {
        try {
            String lastHallNo= HallModel.getLastHallNo();
            if(lastHallNo==null){
                txtHallNo.setText("H0001");
            }else{
                String[] split=lastHallNo.split("[H]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newHall=String.format("H%04d", lastDigits);
                txtHallNo.setText(newHall);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void btnAddHallOptionOnAction(ActionEvent event) {

        cmbEnterHallNo.setPromptText(null);
        txtHallNo.setText(null);
        txtCapacity.setText(null);
        txtAvailableFacilities.setText(null);

        setHallNo();

        btnAddHall.setVisible(true);
        btnCancel.setVisible(true);

        btnUpdate.setVisible(false);
    }

    @FXML
    void btnManageHallOptionOnAction(ActionEvent event) {
        cmbEnterHallNo.setPromptText(null);
        txtHallNo.setText(null);
        txtCapacity.setText(null);
        txtAvailableFacilities.setText(null);

        btnUpdate.setVisible(true);

        btnAddHall.setVisible(false);
        btnCancel.setVisible(false);

        lblEnterHallNo.setVisible(true);
        cmbEnterHallNo.setVisible(true);

        txtHallNo.setText(null);

        DataLoad.loadHallNo((ComboBox<String>) cmbEnterHallNo);
        lblDate.setText(String.valueOf(LocalDate.now()));

    }

    @FXML
    void btnAddHallOnAction(ActionEvent event) {

        lblInvalidCapacity.setVisible(false);
        boolean isCapacityMatched = RegExPatterns.getCapacityPattern().matcher(txtCapacity.getText()).matches();

        if(isCapacityMatched){

            Hall hall = new Hall(
                    txtHallNo.getText(),
                    txtCapacity.getText(),
                    txtAvailableFacilities.getText()
            );

            try {
                boolean isAdd = HallModel.addHall(hall);
                if(isAdd){

                    Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Hall Added Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                    if(choose.get()==ButtonType.OK){
                        try {
                            Navigation.navigate(Routes.HALL,context);
                        } catch (IOException e) {
                            new Alert(Alert.AlertType.ERROR,e+"").show();
                        }
                    }

                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e+"").show();
            }

            InstituteData.setHallCount();
        }else{
            lblInvalidCapacity.setVisible(true);
            txtCapacity.requestFocus();
        }


    }

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
//        try {
//            Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO).showAndWait();
//            if(choose.get()==ButtonType.YES){
//                boolean isDelete = HallModel.deleteHall(String.valueOf(cmbEnterHallNo.getValue()));
//                if(isDelete){
//                    InstituteData.setHallCount();
//                    new Alert(Alert.AlertType.CONFIRMATION,"Hall Delete SuccessFully!").show();
//                    Navigation.navigate(Routes.HALL,context);
//                }
//            }
//        } catch (SQLException | ClassNotFoundException | IOException e) {
//            new Alert(Alert.AlertType.ERROR,e+"").show();
//        }
    }



    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        lblInvalidCapacity.setVisible(false);
        boolean isCapacityMatched = RegExPatterns.getCapacityPattern().matcher(txtCapacity.getText()).matches();

        if(isCapacityMatched){

            Hall hall = new Hall(
                    String.valueOf(cmbEnterHallNo.getValue()),
                    txtCapacity.getText(),
                    txtAvailableFacilities.getText()
            );

            try {
                boolean isUpdate = HallModel.updateHallDetails(hall);
                if(isUpdate){

                    Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Hall Updated Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                    if(choose.get()==ButtonType.OK){
                        try {
                            Navigation.navigate(Routes.HALL,context);
                        } catch (IOException e) {
                            new Alert(Alert.AlertType.ERROR,e+"").show();
                        }
                    }

                }

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e+"").show();
            }
        }else{
            lblInvalidCapacity.setVisible(true);
            txtCapacity.requestFocus();
        }

    }

    @FXML
    void cmbEnterHallNoOnAction(ActionEvent event) {
        txtHallNo.setText((String) cmbEnterHallNo.getValue());
        try {
            Hall hall = HallModel.getDetails(String.valueOf(cmbEnterHallNo.getValue()));
            if(hall!=null){
                txtCapacity.setText(hall.getCapacity());
                txtAvailableFacilities.setText(hall.getAvailableFacilities());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }


    @FXML
    void txtAvailableFacilitiesOnAction(ActionEvent event) {

    }

    @FXML
    void txtCapacityOnAction(ActionEvent event) {
        txtAvailableFacilities.requestFocus();
    }

    @FXML
    void txtHallNoOnAction(ActionEvent event) {

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }


}
