package lk.vidathya.tcms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.vidathya.tcms.model.*;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.Guardian;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.StudentClass;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Mail;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MailFormController {

    public JFXTextField txtRecipient;
    public JFXTextField txtSubject;
    public TextArea txtText;


    public AnchorPane pane;
    public ComboBox cmbGroupRecipient;
    public AnchorPane context;
    public Button btnSend;
    public Button btnDiscard;
    public ComboBox cmbClassCode;
    public Label lblClassCode;
    public TextField txtGrade;
    public TextField txtClassSubject;

    private Pattern emailPattern;

    public void initialize(){

        emailPattern = Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)$");
        DataLoad.loadRecipientGroup(cmbGroupRecipient);

        DataLoad.loadClassCode(cmbClassCode);
        cmbClassCode.setDisable(true);
    }


    public void btnSendOnAction(ActionEvent actionEvent) {

        if(txtRecipient.getText()!=null){
            boolean isEmailMatched = emailPattern.matcher(txtRecipient.getText()).matches();
            if(isEmailMatched){
                try {
                    Mail.sendMail(txtRecipient.getText(), txtSubject.getText(), txtText.getText());
                    new Alert(Alert.AlertType.INFORMATION, "Mail send successfully!").show();
                } catch (MessagingException e) {
                    new Alert(Alert.AlertType.WARNING, ""+e).show();
                }
            }
        }

        if(cmbGroupRecipient.getValue()!=null){

            String group = (String) cmbGroupRecipient.getValue();
            switch (group){


                case "To Staff" :
                    try {
                        ArrayList<String> mailAddress = StaffModel.getStaffEmailAddress();
                        if(mailAddress!=null){
                            for(String recipientAddress : mailAddress){
                                if(recipientAddress!=null){
                                    sendMail(recipientAddress);
                                }
                            }
                        }
                        new Alert(Alert.AlertType.INFORMATION, "Mail send successfully!").show();
                    } catch (SQLException | ClassNotFoundException | MessagingException e) {
                        new Alert(Alert.AlertType.ERROR,e+"").show();
                    }
                    break;


                case "To Tutors" :
                    try {
                        ArrayList<String> mailAddress = TutorModel.getTutorEmailAddress();
                        if(mailAddress!=null){
                            for(String recipientAddress : mailAddress){
                                if(recipientAddress!=null){
                                    sendMail(recipientAddress);
                                }
                            }
                        }
                        new Alert(Alert.AlertType.INFORMATION, "Mail send successfully!").show();
                    } catch (SQLException | ClassNotFoundException | MessagingException e) {
                        new Alert(Alert.AlertType.ERROR,e+"").show();
                    }
                    break;


                case "To Students" :

                    if(cmbClassCode.getValue()==null){
                        try {
                            ArrayList<String> allMails = StudentModel.getAllEmailAddress();
                            for(String mailAddress : allMails){
                                if(mailAddress!=null){
                                    sendMail(mailAddress);
                                }

                            }
                            new Alert(Alert.AlertType.INFORMATION, "Mail send successfully!").show();
                        } catch (SQLException | ClassNotFoundException | MessagingException e) {
                            new Alert(Alert.AlertType.ERROR,e+"").show();
                        }


                    }else{
                        String classCode= String.valueOf(cmbClassCode.getValue());
                        try {
                            ArrayList<String> studentIdArray = StudentClassModel.getDetails(classCode);
                            for(String studentId : studentIdArray ){
                                String studentMail = StudentModel.getStudentMailAddress(studentId);
                                if(studentMail!=null){
                                    sendMail(studentMail);
                                }
                            }
                            new Alert(Alert.AlertType.INFORMATION, "Mail send successfully!").show();
                        } catch (SQLException | ClassNotFoundException | MessagingException e) {
                            new Alert(Alert.AlertType.ERROR,e+"").show();
                        }
                    }


                    break;


                case "To Parents" :

                    if(cmbClassCode.getValue()==null){

                        try {
                            ArrayList<String> allMails = GuardianModel.getAllEmailAddress();
                            for(String mailAddress : allMails){
                                if(mailAddress!=null){
                                    sendMail(mailAddress);
                                }

                            }
                            new Alert(Alert.AlertType.INFORMATION, "Mail send successfully!").show();
                        } catch (SQLException | ClassNotFoundException | MessagingException e) {
                            new Alert(Alert.AlertType.ERROR,e+"").show();
                        }

                    }else{
                        String classCodes= String.valueOf(cmbClassCode.getValue());

                        try {
                            ArrayList<String> guardianNicArray = StudentClassModel.getGuardianNic(classCodes);

                            for(String guardianNic : guardianNicArray ){
                                String guardianMail = GuardianModel.GuardianMailAddress(guardianNic);
                                if(guardianMail!=null){
                                    sendMail(guardianMail);
                                }
                            }
                            new Alert(Alert.AlertType.INFORMATION, "Mail send successfully!").show();
                        }catch (SQLException | ClassNotFoundException | MessagingException e) {
                            new Alert(Alert.AlertType.ERROR,e+"").show();
                        }
                    }



                    break;

            }
        }

        if(txtRecipient.getText()==null && cmbGroupRecipient.getValue()==null){
            new Alert(Alert.AlertType.WARNING, "Please Enter Recipient!").show();
        }

    }

    public void sendMail(String recipientAddress) throws MessagingException {
        Mail.sendMail(recipientAddress, txtSubject.getText(), txtText.getText());
    }

    public void cmbGroupRecipientOnAction(ActionEvent actionEvent) {
        if(cmbGroupRecipient.getValue().equals("To Students") || cmbGroupRecipient.getValue().equals("To Parents")){
            cmbClassCode.setDisable(false);

        }else{
            cmbClassCode.setDisable(true);
            cmbClassCode.setPromptText("");
        }
    }

    public void txtSubjectOnAction(ActionEvent actionEvent) {
        txtText.setFocusTraversable(true);
    }

    public void btnDiscardOnAction(ActionEvent actionEvent) throws IOException {
        new Alert(Alert.AlertType.WARNING, "Discard Message.").show();
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    public void cmbClassCodeOnAction(ActionEvent actionEvent) {

        try {
            Classes classes =  ClassModel.getClassDetails((String) cmbClassCode.getValue());
            txtGrade.setText(classes.getGrade());
            txtClassSubject.setText(classes.getSubject());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e+"").show();
        }

    }
}
