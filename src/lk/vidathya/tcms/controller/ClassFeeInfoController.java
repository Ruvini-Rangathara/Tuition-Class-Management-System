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
import lk.vidathya.tcms.model.*;
import lk.vidathya.tcms.tableModel.ClassFeeInfoTM;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClassFeeInfoController implements Initializable {

    public AnchorPane context;
    public TableColumn colTutorName;
    @FXML
    private TableView<ClassFeeInfoTM> tblClassFee;

    @FXML
    private TableColumn<?, ?> colClassCode;

    @FXML
    private TableColumn<?, ?> colSubject;

    @FXML
    private TableColumn<?, ?> colGrade;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private Button btnOk;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colClassCode.setCellValueFactory(new PropertyValueFactory<>("classCode"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colTutorName.setCellValueFactory(new PropertyValueFactory<>("tutorName"));

        loadTableData();
    }

    private void loadTableData() {
        ObservableList<ClassFeeInfoTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = ClassModel.getAllClasses();
            while (result.next()){
                Tutor tutor = TutorModel.getTutorDetails(result.getString("tutorId"));
                data.add(
                        new ClassFeeInfoTM(
                                result.getString("classCode"),
                                result.getString("subject"),
                                result.getString("grade"),
                                result.getDouble("classFee"),
                                tutor.getName()

                        ));
            }
            tblClassFee.setItems(data);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.FINANCEMANAGE,context);
    }


}
