package lk.vidathya.tcms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.HallModel;
import lk.vidathya.tcms.model.StudentModel;
import lk.vidathya.tcms.model.TutorModel;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.InstituteData;

import java.sql.SQLException;

public class DashboardViewController {

    public AnchorPane context;

    public Label lblStudentCount;
    public Label lblTutorCount;
    public Label lblHallCount;
    public TitledPane contactUs;
    public JFXButton btnContactUs;
    public Label lblEmailAddress;
    public Label lblWhatsAppContactNo;
    public Label lblContactNo;
    public Label lblAddress;

    public void initialize(){

        InstituteData.setStudentCount();
        InstituteData.setTutorCount();
        InstituteData.setHallCount();

        lblStudentCount.setText(String.valueOf(InstituteData.getStudentCount()));
        lblTutorCount.setText(String.valueOf(InstituteData.getTutorCount()));
        lblHallCount.setText(String.valueOf(InstituteData.getHallCount()));

        contactUs.setExpanded(false);
        contactUs.setVisible(false);

        //btnContactUs.setVisible(false);

        lblEmailAddress.setText(InstituteData.getEmail());
        lblContactNo.setText(InstituteData.getContactNo());
        lblWhatsAppContactNo.setText(InstituteData.getWhatsAppContactNo());
        lblAddress.setText(InstituteData.getAddress());

    }


    public void btnContactUsOnAction(ActionEvent actionEvent) {

        if(contactUs.isExpanded()){
            contactUs.setExpanded(false);
        }else{
            contactUs.setExpanded(true);
        }
        if(contactUs.isVisible()){
            contactUs.setVisible(false);
        }else{
            contactUs.setVisible(true);
        }

    }

}
