package lk.vidathya.tcms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.*;
import lk.vidathya.tcms.tableModel.ExtraClassTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.ExtraClass;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.RegExPatterns;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddExtraClassController implements Initializable {

    public AnchorPane context;

    public TableView tblExtraClassHallReservation;

    public TableColumn colHallNo;
    public TableColumn colDate;
    public TableColumn colClassCode;
    public TableColumn colStartTime;
    public TableColumn colEndTime;
    public Label lblEClassHallReservationNo;


    @FXML
    private Label lblEClassCode;

    @FXML
    private Label lblDate;

    @FXML
    private Button btnCancel;

    @FXML
    private Label lblInvalidStarTime;

    @FXML
    private Label lblInvalidEndTime;

    @FXML
    private Button btnAddEClass;

    @FXML
    private TextField txtTutorName;

    @FXML
    private TextField txtStartTime;

    @FXML
    private TextField txtEndTime;

    @FXML
    private ComboBox<?> cmbHallNo;

    @FXML
    private ComboBox<String> cmbClassCode;

    @FXML
    private TextField txtClassCode;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtGrade;

    @FXML
    private DatePicker dteDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setExtraClassCode();
        setHallReservationId();
        lblDate.setText(String.valueOf(LocalDate.now()));

        DataLoad.loadClassCode(cmbClassCode);
        DataLoad.loadHallNo((ComboBox<String>) cmbHallNo);

        lblInvalidEndTime.setVisible(false);
        lblInvalidStarTime.setVisible(false);
    }


    private void setHallReservationId() {
        try {
            String lastHallReservationNo= ExtraClassHallReservationModel.getLastHallReservationNo();
            if(lastHallReservationNo==null){
                lblEClassHallReservationNo.setText("HR0001");
            }else{
                String[] split=lastHallReservationNo.split("[H][R]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newHallReservationNo=String.format("HR%04d", lastDigits);
                lblEClassHallReservationNo.setText(newHallReservationNo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


    private void setExtraClassCode() {
        try {
            String lastExtraClassCode= ExtraClassModel.getLastExtraClassCode();
            if(lastExtraClassCode==null){
                lblEClassCode.setText("EC0001");
            }else{
                String[] split=lastExtraClassCode.split("[E][C]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newExtraClassCode=String.format("EC%04d", lastDigits);
                lblEClassCode.setText(newExtraClassCode);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void btnAddEClassOnAction(ActionEvent event) {

        lblInvalidEndTime.setVisible(false);
        lblInvalidStarTime.setVisible(false);

        boolean isStartTimeMatched = RegExPatterns.getTimePattern().matcher(txtStartTime.getText()).matches();
        boolean isEndTimeMatched = RegExPatterns.getTimePattern().matcher(txtEndTime.getText()).matches();

        if(isStartTimeMatched){
            if(isEndTimeMatched){


                ExtraClass extraClass = new ExtraClass(
                        lblEClassCode.getText(),
                        String.valueOf(cmbClassCode.getValue()),
                        lblDate.getText(),
                        txtStartTime.getText(),
                        txtEndTime.getText(),
                        String.valueOf(cmbHallNo.getValue())
                );


                try {
                    boolean isAdd = ExtraClassModel.addExtraClass(extraClass, lblEClassHallReservationNo.getText());
                    if(isAdd){

                        Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Extra Class Added Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                        if(choose.get()==ButtonType.OK){
                            try {
                                Navigation.navigate(Routes.ADDEXTRACLASS,context);
                            } catch (IOException e) {
                                new Alert(Alert.AlertType.ERROR,e+"").show();
                            }
                        }

                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR,e+"").show();
                }




            }else{
                lblInvalidEndTime.setVisible(true);
                txtEndTime.requestFocus();
            }
        }else{
            lblInvalidStarTime.setVisible(true);
            txtStartTime.requestFocus();
        }


    }

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    @FXML
    void cmbHallNoOnAction(ActionEvent event) {

    }

    @FXML
    void dteDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtEndTimeOnAction(ActionEvent event) {

    }

    @FXML
    void txtGradeOnAction(ActionEvent event) {

    }

    @FXML
    void txtStartTimeOnAction(ActionEvent event) {

    }

    @FXML
    void txtSubjectOnAction(ActionEvent event) {

    }

    @FXML
    void txtTutorNameOnAction(ActionEvent event) {

    }

    public void cmbClassCodeOnAction(ActionEvent actionEvent) {
        try {
            Classes classes =  ClassModel.getClassDetails(cmbClassCode.getValue());
            txtGrade.setText(classes.getGrade());
            txtSubject.setText(classes.getSubject());

            Tutor tutor = TutorModel.getTutorDetails(classes.getTutorId());
            txtTutorName.setText(tutor.getName());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }


}
