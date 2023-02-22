package lk.vidathya.tcms.controller;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.StaffModel;
import lk.vidathya.tcms.model.UserModel;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.User;
import lk.vidathya.tcms.util.DateTime;
import lk.vidathya.tcms.util.LoginCredentials;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.Routes;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class LoginFormController {


    public AnchorPane loginContext;

    public Label lblDate;
    public JFXTextField txtUsername;
    
    public JFXButton btnForgetPassword;
    public JFXButton btnLogin;
    public JFXPasswordField txtPassword;
    public Label lblInvalidUsername;
    public Label lblInvalidPassword;
    public Label lblPasswordHint;
    public Label lblPasswordHintLabel;


    public void initialize() throws MessagingException {

        String date = String.valueOf(LocalDate.now());
        lblDate.setText(DateTime.setDate(date));

        lblInvalidUsername.setVisible(false);
        lblInvalidPassword.setVisible(false);

        lblPasswordHint.setVisible(false);
        lblPasswordHintLabel.setVisible(false);

    }

    public void btnAssistantOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDASSISTANT,loginContext);

    }

    public void btnAdminOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDADMIN,loginContext);

    }

    public void txtUsernameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
    }

    public void btnForgetPasswordOnAction(ActionEvent actionEvent) {
        try {
            boolean isMatchedUsername = UserModel.checkUsername(txtUsername.getText());
            if(isMatchedUsername){
                String passwordHint = UserModel.getPasswordHint(txtUsername.getText());

                lblPasswordHintLabel.setVisible(true);
                lblPasswordHint.setVisible(true);
                lblPasswordHint.setText(passwordHint);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        lblInvalidPassword.setVisible(false);
        lblInvalidUsername.setVisible(false);
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            boolean isMatchedUsername = UserModel.checkUsername(username);
            if(isMatchedUsername){

                boolean isMatchedPassword = UserModel.checkPassword(username, txtPassword.getText());
                if(isMatchedPassword){
                    User user = UserModel.getUserId(password);
                    String staffId = user.getStaffId();

                    Staff staff = StaffModel.getStaffDetails(staffId);
                    String job = staff.getJob();

                    if(job.equals("Manager") || job.equals("manager") ){
                        LoginCredentials.setCurrentUser(password);
                        LoginCredentials.setName(password);
                        Navigation.navigate(Routes.DASHBOARDADMIN,loginContext);
                    }else if(job.equals("Assistant") || job.equals("assistant")){
                        LoginCredentials.setCurrentUser(password);
                        LoginCredentials.setName(password);
                        Navigation.navigate(Routes.DASHBOARDASSISTANT,loginContext);
                    }

                }else {
                    new Shake(txtPassword).play();
                    txtPassword.requestFocus();
                    lblInvalidPassword.setVisible(true);
                }

            }else{
                new Shake(txtUsername).play();
                txtUsername.requestFocus();
                lblInvalidUsername.setVisible(true);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }


//        if(username.equals("a")&&password.equals("b")){
//            Navigation.navigate(Routes.DASHBOARDADMIN,loginContext);
//
//        }else if(username.equals("c")&&password.equals("d")){
//            Navigation.navigate(Routes.DASHBOARDASSISTANT,loginContext);
//        }else{
//            //new Alert(Alert.AlertType.ERROR,"Invalid username or password").show();
//            txtUsername.requestFocus();
//        }
    }
}
