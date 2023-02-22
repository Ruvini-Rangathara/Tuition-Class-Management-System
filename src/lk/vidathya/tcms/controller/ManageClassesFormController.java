package lk.vidathya.tcms.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.HallModel;
import lk.vidathya.tcms.model.HallReservationModel;
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

public class ManageClassesFormController {


    public AnchorPane context;
    public Button btnBack;

    public TextField txtGrade;
    public TextField txtSubject;
    public ComboBox cmbSubject;


    public TableView tblHallReservation;

    public TableColumn colHallNo;
    public TableColumn colDay;
    public TableColumn colClassCode;
    public TableColumn colStartTime;
    public TableColumn colEndTime;
    public Button btnDeactivate;
    public Label lblHallReservationNo;


    @FXML
    private Label lblDate;

    @FXML
    private Label lblInvalidStarTime;

    @FXML
    private Label lblInvalidEndTime;

    @FXML
    private Label lblInvalidClassFee;


    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblExit;

    @FXML
    private TextField txtTutorName;

    @FXML
    private TextField txtEndTime;

    @FXML
    private TextField txtStartTime;

    @FXML
    private TextField txtClassFee;

    @FXML
    private ComboBox<String> cmbTutorId;

    @FXML
    private ComboBox<String> cmbClassCode;

    @FXML
    private ComboBox<String> cmbDay;

    @FXML
    private ComboBox<String> cmbHallNo;

    public void initialize(){

        lblDate.setText(String.valueOf(LocalDate.now()));

        DataLoad.loadClassCode(cmbClassCode);

        DataLoad.loadHallNo(cmbHallNo);

        DataLoad.loadTutorId(cmbTutorId);

        DataLoad.loadDays(cmbDay);

        DataLoad.loadSubject(cmbSubject);

        lblInvalidClassFee.setVisible(false);
        lblInvalidEndTime.setVisible(false);
        lblInvalidStarTime.setVisible(false);

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        lblInvalidClassFee.setVisible(false);
        lblInvalidEndTime.setVisible(false);
        lblInvalidStarTime.setVisible(false);

        boolean isStartTimeMatched = RegExPatterns.getTimePattern().matcher(txtStartTime.getText()).matches();
        boolean isEndTimeMatched = RegExPatterns.getTimePattern().matcher(txtEndTime.getText()).matches();
        boolean isClassFeeMatched = RegExPatterns.getSalaryPattern().matcher(txtStartTime.getText()).matches();

        if(isStartTimeMatched){
            if(isEndTimeMatched){
                if(isClassFeeMatched){



                    String hallNo = null;
                    if(cmbHallNo.getValue()==null){
                        hallNo=String.valueOf(cmbHallNo.getPromptText());
                    }else{
                        hallNo=String.valueOf(cmbHallNo.getValue());
                    }


                    String subject = null;
                    if(cmbSubject.getValue()==null){
                        subject=String.valueOf(cmbSubject.getPromptText());
                    }else{
                        subject=String.valueOf(cmbSubject.getValue());
                    }


                    String tutorId = null;
                    if(cmbTutorId.getValue()==null){
                        tutorId=String.valueOf(cmbTutorId.getPromptText());
                    }else{
                        tutorId=String.valueOf(cmbTutorId.getValue());
                    }


                    String day = null;
                    if(cmbDay.getValue()==null){
                        day=String.valueOf(cmbDay.getPromptText());
                    }else{
                        day=String.valueOf(cmbDay.getValue());
                    }


                    Classes classes = new Classes(
                            String.valueOf(cmbClassCode.getValue()),
                            txtGrade.getText(),
                            subject,
                            tutorId,
                            day,
                            txtStartTime.getText(),
                            txtEndTime.getText(),
                            hallNo,
                            Double.parseDouble(txtClassFee.getText()),
                            lblDate.getText()
                    );

                    HallReservation hallReservation = new HallReservation(
                            lblHallReservationNo.getText(),
                            hallNo,
                            String.valueOf(cmbClassCode.getValue()),
                            day,
                            txtStartTime.getText(),
                            txtEndTime.getText()
                    );

                    try {
                        boolean isUpdate =  ClassModel.updateClass(classes, hallReservation);
                        if(isUpdate){

                            Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Class Updated Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                            if(choose.get()==ButtonType.OK){
                                Navigation.navigate(Routes.MANAGECLASSES,context);
                            }
                        }


                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        throw new RuntimeException(e);
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

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    public void cmbClassCodeOnAction(ActionEvent actionEvent) {
        try {
            Classes classes = ClassModel.getClassDetails(String.valueOf(cmbClassCode.getValue()));
            txtGrade.setText(classes.getGrade());
            cmbSubject.setPromptText(classes.getSubject());
            cmbTutorId.setPromptText(classes.getTutorId());
            cmbDay.setPromptText(classes.getDay());
            txtStartTime.setText(classes.getStartTime());
            txtEndTime.setText(classes.getEndTime());
            cmbHallNo.setPromptText(classes.getHallNo());
            txtClassFee.setText(String.valueOf(classes.getClassFee()));
            lblDate.setText(classes.getDate());

            String hallReservationNo = HallReservationModel.getHallReservationNo(String.valueOf(cmbClassCode.getValue()));
            lblHallReservationNo.setText(hallReservationNo);

            Tutor tutor = TutorModel.getTutorDetails(String.valueOf(cmbTutorId.getPromptText()));
            txtTutorName.setText(tutor.getName());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtGradeOnAction(ActionEvent actionEvent) {
    }

    public void txtSubjectOnAction(ActionEvent actionEvent) {
    }

    public void cmbSubjectOnAction(ActionEvent actionEvent) {

    }

    public void btnDeactivateOnAction(ActionEvent actionEvent) {

        try {
            Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO).showAndWait();
            if(choose.get()==ButtonType.YES){


                boolean isDelete = HallReservationModel.deleteHallReservation( lblHallReservationNo.getText());
                System.out.println("is Delete hall "+isDelete);

                if(isDelete){
                    new Alert(Alert.AlertType.CONFIRMATION,"The Class Was SuccessFully Deactivated!").show();
                    Navigation.navigate(Routes.MANAGECLASSES,context);
                }
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }
}
