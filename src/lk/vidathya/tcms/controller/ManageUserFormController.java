package lk.vidathya.tcms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.StaffModel;
import lk.vidathya.tcms.model.UserModel;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.User;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.RegExPatterns;
import lk.vidathya.tcms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class ManageUserFormController {

    public AnchorPane context;
    public Button btnBack;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblInvalidUsername;

    @FXML
    private Label lblInvalidPassword;

    @FXML
    private Label lblInvalidPasswordHint;

    @FXML
    private Label lblInvalidReEnteredPassword;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblExit;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtReEnterPassword;

    @FXML
    private TextField txtPasswordHint;

    @FXML
    private ComboBox<?> cmbStaffId;

    @FXML
    private TextField txtName;

    public void initialize(){
        lblDate.setText(String.valueOf(LocalDate.now()));

        DataLoad.loadStaffId((ComboBox<String>) cmbStaffId);

        lblInvalidUsername.setVisible(false);
        lblInvalidPassword.setVisible(false);
        lblInvalidPasswordHint.setVisible(false);
        lblInvalidReEnteredPassword.setVisible(false);

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
//        if(String.valueOf(cmbStaffId.getValue())!=null){
//            try {
//                Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO).showAndWait();
//                if(choose.get()==ButtonType.YES){
//                    boolean isDelete = UserModel.deleteUser(String.valueOf(cmbStaffId.getValue()));
//                    if(isDelete){
//                        new Alert(Alert.AlertType.CONFIRMATION,"User Deleted SuccessFully!").show();
//                        Navigation.navigate(Routes.MANAGEUSER,context);
//                    }
//                }
//            } catch (SQLException | ClassNotFoundException | IOException e) {
//                new Alert(Alert.AlertType.ERROR,e+"").show();
//            }
//        }

    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        lblInvalidUsername.setVisible(false);
        lblInvalidPassword.setVisible(false);
        lblInvalidReEnteredPassword.setVisible(false);
        lblInvalidPasswordHint.setVisible(false);

        boolean isUsernameMatched = RegExPatterns.getUsernamePattern().matcher(txtUsername.getText()).matches();
        boolean isPasswordMatched = RegExPatterns.getPasswordPattern().matcher(txtPassword.getText()).matches();
        boolean isPasswordHintMatched = RegExPatterns.getPasswordPattern().matcher(txtPasswordHint.getText()).matches();

        if(isUsernameMatched){
            if(isPasswordMatched){
                if(isPasswordHintMatched){



                    if(txtPassword.getText().equals(txtPasswordHint.getText())){
                        User user = new User(
                                String.valueOf(cmbStaffId.getValue()),
                                txtUsername.getText(),
                                txtPassword.getText(),
                                txtPasswordHint.getText()
                        );

                        try {
                            boolean isUpdate =  UserModel.updateUser(user);
                            if(isUpdate){

                                Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"User Updated Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                                if(choose.get()==ButtonType.OK){
                                    try {
                                        Navigation.navigate(Routes.MANAGEUSER,context);
                                    } catch (IOException e) {
                                        new Alert(Alert.AlertType.ERROR,e+"").show();
                                    }
                                }

                            }


                        } catch (SQLException | ClassNotFoundException e) {
                            new Alert(Alert.AlertType.ERROR,e+"").show();
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Password does not matched!").show();

                    }


                }else{
                    lblInvalidPasswordHint.setVisible(true);
                    txtPasswordHint.requestFocus();
                }
            }else{
                lblInvalidPassword.setVisible(true);
                txtPassword.requestFocus();
            }
        }else{
            lblInvalidUsername.setVisible(true);
            txtUsername.requestFocus();
        }


    }

    @FXML
    void cmbStaffIdOnAction(ActionEvent event) {
        try {
            User user = UserModel.getUserDetails((String) cmbStaffId.getValue());
            if(user!=null){
                txtUsername.setText(user.getUsername());
                txtPassword.setText(user.getPassword());
                txtPasswordHint.setText(user.getPasswordHint());

                Staff staff = StaffModel.getStaffDetails((String)cmbStaffId.getValue());
                txtName.setText(staff.getName());
            }else{
                new Alert(Alert.AlertType.ERROR,"User Not Found").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordHintOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtReEnterPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }
}
