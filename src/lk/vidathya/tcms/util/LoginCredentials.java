package lk.vidathya.tcms.util;

import javafx.scene.control.Alert;
import lk.vidathya.tcms.model.StaffModel;
import lk.vidathya.tcms.model.UserModel;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.User;

import java.sql.SQLException;

public class LoginCredentials {

    private static String name = "Sachini Nayanathara";
    private static String currentUser="E0002";

    public static void setCurrentUser(String password){
        try {
            User user = UserModel.getUserId(password);
            currentUser=user.getStaffId();

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public static String getCurrentUser() {
        return currentUser;
    }



    public static String getName() {
        return name;
    }

    public static void setName(String password) {
        try {
            User user = UserModel.getUserId(password);
            String staffId=user.getStaffId();

            Staff staff = StaffModel.getStaffDetails(staffId);
            LoginCredentials.name = staff.getName();


        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }
}
