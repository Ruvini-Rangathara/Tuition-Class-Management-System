package lk.vidathya.tcms.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.*;
import lk.vidathya.tcms.transferObject.*;
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

public class ManageExtraClassController implements Initializable {


    public AnchorPane context;
    public Button btnBack;
    public ComboBox cmbClassCode;
    public ComboBox cmbEClassCode;

    public TableView tblExtraClassHallReservation;

    public TableColumn colHallNo;
    public TableColumn colDate;
    public TableColumn colClassCode;
    public TableColumn colStartTime;
    public TableColumn colEndTime;
    public Label lblEClassHallReservationNo;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblInvalidStarTime;

    @FXML
    private Label lblInvalidEndTime;

    @FXML
    private TextField txtTutorName;

    @FXML
    private TextField txtStartTime;

    @FXML
    private TextField txtEndTime;

    @FXML
    private ComboBox<?> cmbHallNo;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtGrade;

    @FXML
    private DatePicker dteDate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataLoad.loadHallNo((ComboBox<String>) cmbHallNo);
        DataLoad.loadExtraClassCode(cmbEClassCode);
        DataLoad.loadClassCode(cmbClassCode);

        lblDate.setText(String.valueOf(LocalDate.now()));


        lblInvalidEndTime.setVisible(false);
        lblInvalidStarTime.setVisible(false);
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(cmbEClassCode.getValue()!=null){
            try {
                Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO).showAndWait();
                if(choose.get()==ButtonType.YES){



                    boolean isDelete = ExtraClassHallReservationModel.deleteExtraClassHallReservation(lblEClassHallReservationNo.getText());
                    if(isDelete){
                        new Alert(Alert.AlertType.CONFIRMATION,"The Extra Class Was SuccessFully Deactivated!").show();
                        Navigation.navigate(Routes.MANAGEEXTRACLASSES,context);
                    }
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }



    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        lblInvalidEndTime.setVisible(false);
        lblInvalidStarTime.setVisible(false);

        boolean isStartTimeMatched = RegExPatterns.getTimePattern().matcher(txtStartTime.getText()).matches();
        boolean isEndTimeMatched = RegExPatterns.getTimePattern().matcher(txtEndTime.getText()).matches();

        if(isStartTimeMatched){
            if(isEndTimeMatched){



                String classCode = null;
                if(cmbClassCode.getValue()==null){
                    classCode=cmbClassCode.getPromptText();
                }else{
                    classCode=String.valueOf(cmbClassCode.getValue());
                }

                String hallNo = null;
                if(cmbHallNo.getValue()==null){
                    hallNo=cmbHallNo.getPromptText();
                }else{
                    hallNo=String.valueOf(cmbHallNo.getValue());
                }

                ExtraClass extraClass = new ExtraClass(
                        String.valueOf(cmbEClassCode.getValue()),
                        classCode,
                        lblDate.getText(),
                        txtStartTime.getText(),
                        txtEndTime.getText(),
                        hallNo
                );

                try {
                    boolean isUpdate =  ExtraClassModel.updateExtraClass(extraClass, lblEClassHallReservationNo.getText());
                    if(isUpdate){

                        Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Extra Class Updated Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                        if(choose.get()==ButtonType.OK){
                            try {
                                Navigation.navigate(Routes.MANAGEEXTRACLASSES,context);
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

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    public void cmbClassCodeOnAction(ActionEvent actionEvent) {
        try {
            Classes classes =  ClassModel.getClassDetails((String) cmbClassCode.getValue());
            txtGrade.setText(classes.getGrade());
            txtSubject.setText(classes.getSubject());

            Tutor tutor = TutorModel.getTutorDetails(classes.getTutorId());
            txtTutorName.setText(tutor.getName());


        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public void cmbEClassCodeOnAction(ActionEvent actionEvent)  {
        try {

            ExtraClass extraClass = ExtraClassModel.getEClassDetails((String) cmbEClassCode.getValue());
            cmbClassCode.setPromptText(extraClass.getClassCode());

            Classes classes =  ClassModel.getClassDetails((String) cmbClassCode.getPromptText());
            txtGrade.setText(classes.getGrade());
            txtSubject.setText(classes.getSubject());

            Tutor tutor = TutorModel.getTutorDetails(classes.getTutorId());
            txtTutorName.setText(tutor.getName());

            txtStartTime.setText(extraClass.getStartTime());
            txtEndTime.setText(extraClass.getEndTime());


            cmbHallNo.setPromptText(extraClass.getHallNo());
            dteDate.setPromptText(extraClass.getDate());

            String hallReservationNo = ExtraClassHallReservationModel.getHallReservationNo(String.valueOf(cmbEClassCode.getValue()));
            lblEClassHallReservationNo.setText(hallReservationNo);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



}
