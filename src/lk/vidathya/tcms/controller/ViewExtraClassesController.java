package lk.vidathya.tcms.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.*;
import lk.vidathya.tcms.tableModel.ExtraClassTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewExtraClassesController implements Initializable {

    public AnchorPane context;


    @FXML
    private TableView<ExtraClassTM> tblEClass;

    @FXML
    private TableColumn<?, ?> colEClassCode;

    @FXML
    private TableColumn<?, ?> colClassCode;

    @FXML
    private TableColumn<?, ?> colSubject;

    @FXML
    private TableColumn<?, ?> colGrade;

    @FXML
    private TableColumn<?, ?> colTutorName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private Button btnOk;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        colEClassCode.setCellValueFactory(new PropertyValueFactory<>("eCode"));
        colClassCode.setCellValueFactory(new PropertyValueFactory<>("classCode"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colTutorName.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadTableData();
    }

    private void loadTableData() {
        ObservableList<ExtraClassTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = ExtraClassModel.getAllData();
            while (result.next()){
                Classes classes = ClassModel.getClassDetails(result.getString("classCode"));
                Tutor tutor = TutorModel.getTutorDetails(classes.getTutorId());

                data.add(
                        new ExtraClassTM(
                                result.getString("eClassCode"),
                                result.getString("classCode"),
                                classes.getSubject(),
                                classes.getGrade(),
                                tutor.getName(),
                                result.getString("date")
                        ));
            }
            tblEClass.setItems(data);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }


}
