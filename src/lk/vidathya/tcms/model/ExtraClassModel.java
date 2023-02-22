package lk.vidathya.tcms.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.tableModel.ExtraClassScheduleTM;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.ExtraClass;
import lk.vidathya.tcms.transferObject.ExtraClassHallReservation;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtraClassModel {

    public static String getLastExtraClassCode() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT eClassCode FROM ExtraClass ORDER BY eClassCode DESC LIMIT 1");
        if(result.next()){
            return result.getString("eClassCode");
        }
        return null;
    }

    public static ObservableList<String> loadExtraClassCodeToComboBox() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT eClassCode FROM ExtraClass ORDER BY eClassCode DESC");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (result.next()) {
            options.add(result.getString("eClassCode"));
        }
        return options;
    }

    public static ExtraClass getEClassDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM ExtraClass WHERE eClassCode=?",id);
        if(result.next()){
            ExtraClass extraClass = new ExtraClass(
                    result.getString("eClassCode"),
                    result.getString("classCode"),
                    result.getString("date"),
                    result.getString("startTime"),
                    result.getString("endTime"),
                    result.getString("hallNo")
            );
            return extraClass;
        }
        return null;

    }

    public static boolean addExtraClass(ExtraClass extraClass, String eCHallReservationNo) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdd = CrudUtil.execute("INSERT INTO ExtraClass Values(?,?,?,?,?,?)",
                    extraClass.geteClassCode(),
                    extraClass.getClassCode(),
                    extraClass.getDate(),
                    extraClass.getStartTime(),
                    extraClass.getEndTime(),
                    extraClass.getHallNo()
            );
            if (isAdd){
                ExtraClassHallReservation extraClassHallReservation = new ExtraClassHallReservation(
                        eCHallReservationNo,
                        extraClass.getHallNo(),
                        extraClass.geteClassCode(),
                        extraClass.getDate(),
                        extraClass.getStartTime(),
                        extraClass.getEndTime()
                );
                boolean isAddHallReservation = ExtraClassHallReservationModel.addExtraClassHallReservation(extraClassHallReservation);
                if(isAddHallReservation){
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


    public static boolean updateExtraClass(ExtraClass extraClass, String hallReservationNo) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isUpdate = CrudUtil.execute("UPDATE ExtraClass SET date=?, startTime=?, endTime=?, hallNo=? WHERE eClassCode=?",
                    extraClass.getDate(),
                    extraClass.getStartTime(),
                    extraClass.getEndTime(),
                    extraClass.getHallNo(),
                    extraClass.geteClassCode()
            );

            if (isUpdate){
                ExtraClassHallReservation extraClassHallReservation = new ExtraClassHallReservation(
                        hallReservationNo,
                        extraClass.getHallNo(),
                        extraClass.geteClassCode(),
                        extraClass.getDate(),
                        extraClass.getStartTime(),
                        extraClass.getEndTime()
                );
                boolean isUpdateHallReservation = ExtraClassHallReservationModel.updateExtraClassHallReservation(extraClassHallReservation);
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

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM ExtraClass ORDER BY date DESC");
        return result;
    }

    public static ResultSet getDataToScheduleTable(String date) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM ExtraClass WHERE date=?", date);
        return result;
    }


}
