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
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.ExtraClassHallReservationModel;
import lk.vidathya.tcms.model.ExtraClassModel;
import lk.vidathya.tcms.model.HallReservationModel;
import lk.vidathya.tcms.tableModel.EClassHallReservationTM;
import lk.vidathya.tcms.tableModel.HallReservationTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.ExtraClass;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewHallReservationsController implements Initializable {

    @FXML
    private AnchorPane context;

    @FXML
    private TableView<EClassHallReservationTM> tblExtraClassHallReservation;

    @FXML
    private TableColumn<?, ?> colEClassHallNo;

    @FXML
    private TableColumn<?, ?> colEClassCode;

    @FXML
    private TableColumn<?, ?> colEClassGrade;

    @FXML
    private TableColumn<?, ?> colEClassSubject;

    @FXML
    private TableColumn<?, ?> colEClassDate;

    @FXML
    private TableColumn<?, ?> colEClassStartTime;

    @FXML
    private TableColumn<?, ?> colEClassEndTime;

    @FXML
    private TableView<HallReservationTM> tblHallReservation;

    @FXML
    private TableColumn<?, ?> colHallNo;

    @FXML
    private TableColumn<?, ?> colClassCode;

    @FXML
    private TableColumn<?, ?> colGrade;

    @FXML
    private TableColumn<?, ?> colSubject;

    @FXML
    private TableColumn<?, ?> colDay;

    @FXML
    private TableColumn<?, ?> colStartTime;

    @FXML
    private TableColumn<?, ?> colEndTime;

    @FXML
    private Button btnOk;

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colHallNo.setCellValueFactory(new PropertyValueFactory<>("hallNo"));
        colClassCode.setCellValueFactory(new PropertyValueFactory<>("classCode"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        colEClassHallNo.setCellValueFactory(new PropertyValueFactory<>("extraClassHallNo"));
        colEClassCode.setCellValueFactory(new PropertyValueFactory<>("extraClassCode"));
        colEClassGrade.setCellValueFactory(new PropertyValueFactory<>("extraClassGrade"));
        colEClassSubject.setCellValueFactory(new PropertyValueFactory<>("extraClassSubject"));
        colEClassDate.setCellValueFactory(new PropertyValueFactory<>("extraClassDate"));
        colEClassStartTime.setCellValueFactory(new PropertyValueFactory<>("extraClassStartTime"));
        colEClassEndTime.setCellValueFactory(new PropertyValueFactory<>("extraClassEndTime"));

        loadDataToExtraClassHallReservation();
        loadDataToHallReservation();

    }

    private void loadDataToExtraClassHallReservation() {
        ObservableList<EClassHallReservationTM> extraClassHallData = FXCollections.observableArrayList();
        try {
            ResultSet result = ExtraClassHallReservationModel.getDataToViewTable();
            while (result.next()){

                ExtraClass extraClass = ExtraClassModel.getEClassDetails(result.getString("eClassCode"));
                Classes classes = ClassModel.getClassDetails(extraClass.getClassCode());

                extraClassHallData.add(
                        new EClassHallReservationTM(
                                result.getString("hallNo"),
                                result.getString("eClassCode"),
                                classes.getGrade(),
                                classes.getSubject(),
                                result.getString("date"),
                                result.getString("startTime"),
                                result.getString("endTime")
                        ));
                System.out.print(result.getString("hallNo")+"   ");
                System.out.print(result.getString("eClassCode")+"   ");
                System.out.print(classes.getGrade()+"   ");
                System.out.print(classes.getSubject()+"    ");
                System.out.print(result.getString("date")+"    ");
                System.out.print(result.getString("startTime")+"    ");
                System.out.println(result.getString("endTime")+"     ");
            }
            tblExtraClassHallReservation.setItems(extraClassHallData);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    private void loadDataToHallReservation() {
        ObservableList<HallReservationTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = HallReservationModel.getDataToViewTable();
            while (result.next()){

                Classes classes = ClassModel.getClassDetails(result.getString("classCode"));

                data.add(
                        new HallReservationTM(
                                result.getString("hallNo"),
                                result.getString("classCode"),
                                classes.getGrade(),
                                classes.getSubject(),
                                result.getString("day"),
                                result.getString("startTime"),
                                result.getString("endTime")
                        ));
            }
            tblHallReservation.setItems(data);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }
}
