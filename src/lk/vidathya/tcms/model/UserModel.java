package lk.vidathya.tcms.model;

import javafx.scene.control.ComboBox;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.User;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel {
    public static User getUserDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM User WHERE staffId=?",id);
        if(result.next()){
            User user = new User(
                    result.getString("staffId"),
                    result.getString("username"),
                    result.getString("password"),
                    result.getString("passwordHint")

            );
            return user;
        }
        return null;
    }

    public static User getUserId(String password) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM User WHERE password=?",password);
        if(result.next()){
            User user = new User(
                    result.getString("staffId"),
                    result.getString("username"),
                    result.getString("password"),
                    result.getString("passwordHint")

            );
            return user;
        }
        return null;
    }

    public static boolean checkUsername(String username) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT username FROM User");

        ArrayList<String> usernameArray = new ArrayList<>();
        while (result.next()){
            usernameArray.add(result.getString("username"));
        }
         for(String i : usernameArray){
             if(i.equals(username)){
                 return true;
             }
         }
        return false;
    }

    public static boolean checkPassword(String username, String uncheckedPassword) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT password FROM User WHERE username=?", username);
        if(result.next()){
            if(uncheckedPassword.equals(result.getString("password"))){
                return true;
            }
        }
        return false;
    }

    public static boolean deleteUser(String staffId) throws SQLException, ClassNotFoundException {
        boolean isDelete = CrudUtil.execute("DELETE FROM User WHERE staffId=?",staffId);
        return isDelete;
    }

    public static boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE User SET username=?, password=?, passwordHint=? WHERE staffId=?",
                user.getUsername(),
                user.getPassword(),
                user.getPasswordHint(),
                user.getStaffId()
                );
        return isUpdate;
    }

    public static boolean addUser(User user) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO User VALUES(?,?,?,?)",
                user.getStaffId(),
                user.getUsername(),
                user.getPassword(),
                user.getPasswordHint()
                );
        return isAdd;
    }

    public static String getPasswordHint(String username) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT passwordHint FROM User WHERE username=?",username );
        if(result.next()){
            return result.getString("passwordHint");
        }
        return null;
    }
}
