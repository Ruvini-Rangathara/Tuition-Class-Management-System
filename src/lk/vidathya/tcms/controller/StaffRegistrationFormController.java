package lk.vidathya.tcms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.model.ClassModel;
import lk.vidathya.tcms.model.StaffModel;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.util.DataLoad;
import lk.vidathya.tcms.util.Navigation;
import lk.vidathya.tcms.util.RegExPatterns;
import lk.vidathya.tcms.util.Routes;

import javax.naming.spi.InitialContextFactory;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class StaffRegistrationFormController implements Initializable {

    public AnchorPane context;
    public ImageView staffImage;
    public Button btnBrowseImage;

    @FXML
    private Label lblStaffId;

    @FXML
    private Label lblDate;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRegister;

    @FXML
    private Label lblInvalidStaffName;

    @FXML
    private Label lblInvalidStaffNic;

    @FXML
    private Label lblInvalidStaffContactNo;

    @FXML
    private Label lblInvalidStaffEmail;

    @FXML
    private Label lblInvalidStaffDob;

    @FXML
    private Label lblInvalidStaffSalary;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtJob;

    @FXML
    private TextField txtNic;

    @FXML
    private ComboBox<?> cmbGender;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtDob;

    @FXML
    private TextField txtSalary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setStaffId();
        lblDate.setText(String.valueOf(LocalDate.now()));

        DataLoad.loadGenderOptions((ComboBox<String>) cmbGender);

        setLabelVisibility();
    }

    private void setLabelVisibility(){
        lblInvalidStaffName.setVisible(false);
        lblInvalidStaffNic.setVisible(false);
        lblInvalidStaffContactNo.setVisible(false);
        lblInvalidStaffEmail.setVisible(false);
        lblInvalidStaffDob.setVisible(false);
        lblInvalidStaffSalary.setVisible(false);
    }


    private void setStaffId() {
        try {
            String lastStaffId= StaffModel.getLastStaffId();
            if(lastStaffId==null){
                lblStaffId.setText("E0001");
            }else{
                String[] split=lastStaffId.split("[E]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newStaffId=String.format("E%04d", lastDigits);
                lblStaffId.setText(newStaffId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARDVIEW,context);
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        setLabelVisibility();

        boolean isNameMatched = RegExPatterns.getNamePattern().matcher(txtName.getText()).matches();
        boolean isNicMatched = RegExPatterns.getIdPattern().matcher(txtNic.getText()).matches();
        boolean isOldNicMatched = RegExPatterns.getOldIDPattern().matcher(txtNic.getText()).matches();
        boolean isContactNoMatched = RegExPatterns.getMobilePattern().matcher(txtContactNo.getText()).matches();
        boolean isEmailMatched = RegExPatterns.getEmailPattern().matcher(txtEmail.getText()).matches();
        boolean isDobMatched = RegExPatterns.getDobPattern().matcher(txtDob.getText()).matches();
        boolean isSalaryMatched = RegExPatterns.getSalaryPattern().matcher(txtSalary.getText()).matches();

        if(isNameMatched){
            if(isNicMatched || isOldNicMatched){
                if(isContactNoMatched){
                    if(isEmailMatched){
                        if(isDobMatched){
                            if(isSalaryMatched){


                                Staff staff = new Staff(
                                        lblStaffId.getText(),
                                        txtName.getText(),
                                        txtJob.getText(),
                                        txtNic.getText(),
                                        String.valueOf(cmbGender.getValue()),
                                        txtAddress.getText(),
                                        txtContactNo.getText(),
                                        txtEmail.getText(),
                                        txtDob.getText(),
                                        Double.parseDouble(txtSalary.getText()),
                                        String.valueOf(LocalDate.now())
                                );

                                try {
                                    boolean isAdded = StaffModel.addStaff(staff);
                                    if(isAdded){

                                        Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Staff Member Added Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
                                        if(choose.get()==ButtonType.OK){
                                            try {
                                                Navigation.navigate(Routes.STAFFREGISTRATION,context);
                                            } catch (IOException e) {
                                                new Alert(Alert.AlertType.ERROR,e+"").show();
                                            }
                                        }

                                    }
                                } catch (SQLException | ClassNotFoundException e) {
                                    new Alert(Alert.AlertType.ERROR,e+"").show();
                                }


                            }else{
                                lblInvalidStaffSalary.setVisible(true);
                                txtSalary.requestFocus();
                            }
                        }else{
                            lblInvalidStaffDob.setVisible(true);
                            txtDob.requestFocus();
                        }
                    }else{
                        lblInvalidStaffEmail.setVisible(true);
                        txtEmail.requestFocus();
                    }
                }else{
                    lblInvalidStaffContactNo.setVisible(true);
                    txtContactNo.requestFocus();
                }
            }else{
                lblInvalidStaffNic.setVisible(true);
                txtNic.requestFocus();
            }
        }else{
            lblInvalidStaffName.setVisible(true);
            txtName.requestFocus();
        }

    }

    @FXML
    void cmbGenderOnAction(ActionEvent event) {

    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtContactNoOnAction(ActionEvent event) {

    }

    @FXML
    void txtDobOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtJobOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtNicOnAction(ActionEvent event) {

    }

    @FXML
    void txtSalaryOnAction(ActionEvent event) {

    }


    public void btnBrowseImageOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("..\\tcms-2.0\\StaffImages"));
        try {

            boolean isExist = StaffModel.isExistImage(lblStaffId.getText());
            if(!isExist){
                String sql = "INSERT INTO StaffImage VALUES (?, ?)";
                PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

                File file = fileChooser.showOpenDialog(new Stage());

                if(file!=null){
                    FileInputStream fis = new FileInputStream(file);
                    pstm.setString(1,lblStaffId.getText());
                    pstm.setBinaryStream(2,fis,fis.available());
                    int x = pstm.executeUpdate();

                    if(x>0){
                        JOptionPane.showMessageDialog(null, "Image SuccessFully Added!");

                        ResultSet resultSet = StaffModel.getImage(lblStaffId.getText());
                        if(resultSet.next()){
                            Image img = new Image(resultSet.getBinaryStream("image"));
                            staffImage.setImage(img);
                            staffImage.setPreserveRatio(false);
                        }
                    }

                }

            }else{
                String sql = "UPDATE StaffImage SET image=? WHERE staffId=? ";
                PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

                fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(new Stage());

                if(file!=null){
                    FileInputStream fis = new FileInputStream(file);
                    pstm.setString(2,lblStaffId.getText());
                    pstm.setBinaryStream(1,fis,fis.available());
                    int x = pstm.executeUpdate();

                    if(x>0){
                        JOptionPane.showMessageDialog(null, "Image Added SuccessFully!");

                        ResultSet resultSet = StaffModel.getImage(lblStaffId.getText());
                        if(resultSet.next()){
                            Image img = new Image(resultSet.getBinaryStream("image"));
                            staffImage.setImage(img);
                            staffImage.setPreserveRatio(false);
                        }
                    }

                }
            }

        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}
