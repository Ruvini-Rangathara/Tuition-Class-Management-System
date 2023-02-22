package lk.vidathya.tcms.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.IncomeAndExpenditure;
import lk.vidathya.tcms.transferObject.NotPaidTutorSalary;
import lk.vidathya.tcms.transferObject.Tutor;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TutorModel {

    public static String getLastTutorId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT tutorId FROM Tutor ORDER BY tutorId DESC LIMIT 1");
        if(result.next()){
            return result.getString("tutorId");
        }
        return null;
    }

    public static ObservableList<String> loadTutorIdToComboBox() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT tutorId FROM Tutor ORDER BY tutorId ASC");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (result.next()) {
            options.add(result.getString("tutorId"));
        }
        return options;
    }

    public static ObservableList<String> loadSubjectToComboBox() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT DISTINCT subject FROM Tutor");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (result.next()) {
            options.add(result.getString("subject"));
        }
        return options;

    }

    public static Tutor getTutorDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Tutor WHERE tutorId=?",id);
        if(result.next()){
            Tutor tutor = new Tutor(
                    result.getString("tutorId"),
                    result.getString("name"),
                    result.getString("nic"),
                    result.getString("gender"),
                    result.getString("address"),
                    result.getString("contactNo"),
                    result.getString("email"),
                    result.getString("dob"),
                    result.getString("subject"),
                    result.getString("date")
            );
            return tutor;
        }
        return null;
    }

    public static int getTutorCount() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT tutorId FROM Tutor");
        int count=0;
        while(result.next()){
            count++;
        }
        return count;

    }

    public static ArrayList<String> getTutorEmailAddress() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT email FROM Tutor");
        ArrayList<String> mailAddress = new ArrayList<>();
        while(result.next()){
            mailAddress.add(result.getString("email"));
        }
        return mailAddress;
    }


    public static boolean addTutor(Tutor tutor) throws SQLException, ClassNotFoundException {

            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdd = CrudUtil.execute("INSERT INTO Tutor VALUES(?,?,?,?,?,?,?,?,?,?)",
                    tutor.getTutorId(),
                    tutor.getName(),
                    tutor.getNic(),
                    tutor.getGender(),
                    tutor.getAddress(),
                    tutor.getContactNo(),
                    tutor.getEmail(),
                    tutor.getDob(),
                    tutor.getSubject(),
                    tutor.getDate()
            );

            return isAdd;

    }

    public static boolean deleteTutor(String tutorId) throws SQLException, ClassNotFoundException {
        boolean isDelete = CrudUtil.execute("DELETE FROM Tutor WHERE tutorId=?", tutorId);
        return isDelete;
    }

    public static boolean updateTutor(Tutor tutor) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE Tutor SET name=?, nic=?, gender=?, address=?, contactNo=?, email=?, dob=?, subject=? WHERE tutorId=?",
                tutor.getName(),
                tutor.getNic(),
                tutor.getGender(),
                tutor.getAddress(),
                tutor.getContactNo(),
                tutor.getEmail(),
                tutor.getDob(),
                tutor.getSubject(),
                tutor.getTutorId()
        );
        return isUpdate;
    }

    public static ResultSet getAllTutors() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Tutor");
        return result;
    }

    public static boolean isExistImage(String tutorId) throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT tutorId FROM TutorImage");

        while (result.next()){
            if(result.getString("tutorId").equals(tutorId)){
                return true;
            }
        }
        return false;
    }

    public static ResultSet getImage(String tutorId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT image FROM TutorImage Where tutorId=?",tutorId);
        return result;
    }
}
