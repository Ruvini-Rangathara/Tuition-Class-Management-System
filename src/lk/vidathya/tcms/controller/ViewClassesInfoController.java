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
import lk.vidathya.tcms.tableModel.ClassesInfoTM;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewClassesInfoController implements Initializable {

    public AnchorPane context;
    @FXML
    private TableView<ClassesInfoTM> tblClass;

    @FXML
    private TableColumn<?, ?> colClassCode;

    @FXML
    private TableColumn<?, ?> colSubject;

    @FXML
    private TableColumn<?, ?> colGrade;

    @FXML
    private TableColumn<?, ?> colTutorName;

    @FXML
    private TableColumn<?, ?> colDay;

    @FXML
    private Button btnOk;

    @FXML
    private TextField txtCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colClassCode.setCellValueFactory(new PropertyValueFactory<>("classCode"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colTutorName.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        colDay.setCellValueFactory(new PropertyValueFactory<>("day"));

        loadClassesDetails();
    }

    private void loadClassesDetails() {
        ObservableList<ClassesInfoTM> data = FXCollections.observableArrayList();
        try {
            int count=0;
            ResultSet result = ClassModel.getAllClasses();
            while (result.next()){
                count++;
                Tutor tutor = TutorModel.getTutorDetails(result.getString("tutorId"));
                data.add(
                        new ClassesInfoTM(
                                result.getString("classCode"),
                                result.getString("subject"),
                                result.getString("grade"),
                                tutor.getName(),
                                result.getString("day")
                        ));
            }
            tblClass.setItems(data);
            txtCount.setText(String.valueOf(count));
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWINFOCONTEXT,context);
    }

    @FXML
    void txtCountOnAction(ActionEvent event) {

    }


}
