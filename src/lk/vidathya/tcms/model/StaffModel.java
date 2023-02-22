package lk.vidathya.tcms.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.util.CrudUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffModel {

    public static String getLastStaffId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT staffId FROM Staff ORDER BY staffId DESC LIMIT 1");
        if(result.next()){
            return result.getString("staffId");
        }
        return null;
    }

    public static ObservableList<String> loadStaffIdToComboBox() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT staffId FROM Staff ORDER BY staffId ASC");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (result.next()) {
            options.add(result.getString("staffId"));
        }
        return options;
    }

    public static Staff getStaffDetails(String id) throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT * FROM Staff WHERE staffId=?",id);
        if(result.next()){
            Staff staff = new Staff(
                    result.getString("staffId"),
                    result.getString("name"),
                    result.getString("job"),
                    result.getString("nic"),
                    result.getString("gender"),
                    result.getString("address"),
                    result.getString("contactNo"),
                    result.getString("email"),
                    result.getString("dob"),
                    result.getDouble("salary"),
                    result.getString("date")
            );
            return staff;
        }
        return null;
    }

    public static ArrayList<String> getStaffEmailAddress() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT email FROM Staff");
        ArrayList<String> mailAddress = new ArrayList<>();
        while(result.next()){
            mailAddress.add(result.getString("email"));
        }
        return mailAddress;

    }

    public static boolean deleteStaff(String staffId) throws SQLException, ClassNotFoundException {
        boolean isDeleted= CrudUtil.execute("DELETE FROM Staff WHERE StaffId = ?", staffId );
        return isDeleted;
    }

    public static boolean updateStaff(Staff staff) throws SQLException, ClassNotFoundException {
        boolean isUpdated = CrudUtil.execute("UPDATE Staff SET name=?, job=?, nic=?, gender=?, address=?, contactNo=?, email=?, dob=?, salary=? WHERE staffId=?",
                staff.getName(),
                staff.getJob(),
                staff.getNic(),
                staff.getGender(),
                staff.getAddress(),
                staff.getContactNo(),
                staff.getEmail(),
                staff.getDob(),
                staff.getSalary(),
                staff.getStaffId()
                );
        return isUpdated;
    }

    public static boolean addStaff(Staff staff) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdd=CrudUtil.execute("INSERT INTO Staff VALUES(?,?,?,?,?,?,?,?,?,?,?)",
                    staff.getStaffId(),
                    staff.getName(),
                    staff.getJob(),
                    staff.getNic(),
                    staff.getGender(),
                    staff.getAddress(),
                    staff.getContactNo(),
                    staff.getEmail(),
                    staff.getDob(),
                    staff.getSalary(),
                    staff.getDate()
            );

            if (isAdd) {
                boolean isAddNotPaidStaffSalary = StaffSalaryModel.addNotPaidStaffSalary(staff);
                if (isAddNotPaidStaffSalary) {
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }

    public static ResultSet getAllStaffDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Staff ORDER BY StaffId");
        return result;
    }

    public static ResultSet getImage(String staffId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT image FROM StaffImage Where staffId=?",staffId);
        return result;
    }

    public static boolean isExistImage(String staffId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT staffId FROM StaffImage");

        while (result.next()){
            if(result.getString("staffId").equals(staffId)){
                return true;
            }
        }
        return false;
    }


}
