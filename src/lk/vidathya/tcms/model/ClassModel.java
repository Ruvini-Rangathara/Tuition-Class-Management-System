package lk.vidathya.tcms.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.HallReservation;
import lk.vidathya.tcms.transferObject.NotPaidTutorSalary;
import lk.vidathya.tcms.util.CrudUtil;
import lk.vidathya.tcms.util.MonthName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClassModel {

    public static String getLastClassCode() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT classCode FROM Classes ORDER BY classCode DESC LIMIT 1");
        if(result.next()){
            return result.getString("classCode");
        }
        return null;
    }

    public static ObservableList<String> loadClassCodeToComboBox() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT classCode FROM Classes ORDER BY classCode ASC");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (result.next()) {
            options.add(result.getString("classCode"));
        }
        return options;
    }

    public static ObservableList<String> loadGradeToComboBox() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT DISTINCT grade FROM Classes");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (result.next()) {
            options.add(result.getString("grade"));
        }
        return options;

    }


    public static Classes getClassDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Classes WHERE classCode=?",id);
        if(result.next()){
            Classes classes = new Classes(
                    result.getString("classCode"),
                    result.getString("grade"),
                    result.getString("subject"),
                    result.getString("tutorId"),
                    result.getString("day"),
                    result.getString("startTime"),
                    result.getString("endTime"),
                    result.getString("hallNo"),
                    result.getDouble("classFee"),
                    result.getString("date")
            );
            return classes;
        }
        return null;
    }


    public static boolean addClass(Classes classes, HallReservation hallReservation) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdd = CrudUtil.execute("INSERT INTO Classes VALUES (?,?,?,?,?,?,?,?,?,?)",
                    classes.getClassCode(),
                    classes.getGrade(),
                    classes.getSubject(),
                    classes.getTutorId(),
                    classes.getDay(),
                    classes.getStartTime(),
                    classes.getEndTime(),
                    classes.getHallNo(),
                    classes.getClassFee(),
                    classes.getDate()
            );
            if (isAdd){
                boolean isAddHallReservation = HallReservationModel.addHallReservation(hallReservation);
                if(isAddHallReservation){

                    String date = String.valueOf(LocalDate.now());
                    int year = Integer.parseInt(date.split("-")[0]);

                    NotPaidTutorSalary notPaidTutorSalary = new NotPaidTutorSalary(
                            classes.getTutorId(),
                            classes.getClassCode(),
                            year,
                            MonthName.getMonthName(date),
                            0.00
                    );

                    boolean isAddNotPaid = NotPaidTutorSalaryModel.addTutorRecord(notPaidTutorSalary);

                    if(isAddNotPaid){
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }

                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }

    public static boolean updateClass(Classes classes, HallReservation hallReservation) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isUpdate = CrudUtil.execute("UPDATE Classes SET day=?, startTime=?, endTime=?, hallNo=? , classFee=? WHERE classCode=?",
                    classes.getDay(),
                    classes.getStartTime(),
                    classes.getEndTime(),
                    classes.getHallNo(),
                    classes.getClassFee(),
                    classes.getClassCode()
            );
            if(isUpdate){
                boolean isUpdateHallReservation = HallReservationModel.updateHallReservation(hallReservation);
                if(isUpdateHallReservation){
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

    public static ResultSet getDataToScheduleTable(String dayName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT subject,grade,hallNo,startTime,endTime FROM Classes WHERE day=?",dayName);
        return resultSet;
    }

    public static ResultSet getAllClasses() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Classes ORDER  BY classCode ASC");
        return result;

    }


}
