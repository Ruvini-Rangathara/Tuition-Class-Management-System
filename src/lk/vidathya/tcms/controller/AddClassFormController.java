package lk.vidathya.tcms.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.HallReservationModel;
import lk.vidathya.tcms.model.StudentModel;
import lk.vidathya.tcms.model.TutorModel;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.HallReservation;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.RegExPatterns;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class AddClassFormController {


    public AnchorPane context;
    public TextField txtGrade;

    public ComboBox cmbSubject;


    public TableColumn colHallNo;
    public TableColumn colDay;
    public TableColumn colClassCode;
    public TableColumn colStartTime;
    public TableColumn colEndTime;
    public Label lblHallReservationNo;


    @FXML
    private Label lblClassCode;

    @FXML
    private Label lblDate;

    @FXML
    private Button btnCancel;

    @FXML
    private Label lblInvalidStarTime;

    @FXML
    private Label lblInvalidEndTime;

    @FXML
    private Button btnAddClass;

    @FXML
    private Label lblInvalidClassFee;

    @FXML
    private TextField txtTutorName;

    @FXML
    private TextField txtStartTime;

    @FXML
    private TextField txtEndTime;

    @FXML
    private ComboBox<String> cmbTutorId;

    @FXML
    private ComboBox<String> cmbDay;

    @FXML
    private ComboBox<String> cmbHallNo;

    @FXML
    private TextField txtClassFee;


    public void initialize(){
        setClassCode();

        DataLoad.loadDays(cmbDay);

        DataLoad.loadHallNo(cmbHallNo);

        DataLoad.loadTutorId(cmbTutorId);

        DataLoad.loadSubject(cmbSubject);

        lblDate.setText(String.valueOf(LocalDate.now()));
        setHallReservationId();

        lblInvalidClassFee.setVisible(false);
        lblInvalidEndTime.setVisible(false);
        lblInvalidStarTime.setVisible(false);

    }


    private void setHallReservationId() {
        try {
            String lastHallReservationNo= HallReservationModel.getLastHallReservationNo();
            if(lastHallReservationNo==null){
                lblHallReservationNo.setText("HR0001");
            }else{
                String[] split=lastHallReservationNo.split("[H][R]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newHallReservationNo=String.format("HR%04d", lastDigits);
                lblHallReservationNo.setText(newHallReservationNo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    private void setClassCode() {
        try {
            String lastClassCode= ClassModel.getLastClassCode();
            if(lastClassCode==null){
                lblClassCode.setText("C0001");
            }else{
                String[] split=lastClassCode.split("[C]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newClassCode=String.format("C%04d", lastDigits);
                lblClassCode.setText(newClassCode);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


    @FXML
    void btnAddClassOnAction(ActionEvent event) {

        lblInvalidClassFee.setVisible(false);
        lblInvalidEndTime.setVisible(false);
        lblInvalidStarTime.setVisible(false);

        boolean isStartTimeMatched = RegExPatterns.getTimePattern().matcher(txtStartTime.getText()).matches();
        boolean isEndTimeMatched = RegExPatterns.getTimePattern().matcher(txtEndTime.getText()).matches();
        boolean isClassFeeMatched = RegExPatterns.getSalaryPattern().matcher(txtStartTime.getText()).matches();

        if(isStartTimeMatched){
            if(isEndTimeMatched){
                if(isClassFeeMatched){

                    Classes classes = new Classes(
                            lblClassCode.getText(),
                            txtGrade.getText(),
                            String.valueOf(cmbSubject.getValue()),
                            cmbTutorId.getValue(),
                            String.valueOf(cmbDay.getValue()),
                            txtStartTime.getText(),
                            txtEndTime.getText(),
                            String.valueOf(cmbHallNo.getValue()),
                            Double.parseDouble(txtClassFee.getText()),
                            String.valueOf(LocalDate.now())
                    );

                    HallReservation hallReservation = new HallReservation(
                            lblHallReservationNo.getText(),
                            String.valueOf(cmbHallNo.getValue()),
                            lblClassCode.getText(),
                            String.valueOf(cmbDay.getValue()),
                            txtStartTime.getText(),
                            txtEndTime.getText()
                    );

                    try {
                        boolean isAdded = ClassModel.addClass(classes, hallReservation);
                        if(isAdded){

                            Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Class Added Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                            if(choose.get()==ButtonType.OK){
                                try {
                                    Navigation.navigate(Routes.ADDCLASS,context);
                                } catch (IOException e) {
                                    new Alert(Alert.AlertType.ERROR,e+"").show();
                                }
                            }

                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        new Alert(Alert.AlertType.ERROR,e+"").show();
                    }

                }else{
                    lblInvalidClassFee.setVisible(true);
                    txtClassFee.requestFocus();
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
    void cmbDayOnAction(ActionEvent event) {

    }


    @FXML
    void cmbHallNoOnAction(ActionEvent event) {

    }


    @FXML
    void cmbTutorIdOnAction(ActionEvent event) {
        try {
            Tutor tutor = TutorModel.getTutorDetails(cmbTutorId.getValue());
            txtTutorName.setText(tutor.getName());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void txtClassFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtEndTimeOnAction(ActionEvent event) {

    }

    @FXML
    void txtStartTimeOnAction(ActionEvent event) {

    }

    @FXML
    void txtTutorNameOnAction(ActionEvent event) {

    }


    public void txtGradeOnAction(ActionEvent actionEvent) {

    }

    public void txtSubjectOnAction(ActionEvent actionEvent) {

    }

    public void cmbSubjectOnAction(ActionEvent actionEvent) {
    }
}
