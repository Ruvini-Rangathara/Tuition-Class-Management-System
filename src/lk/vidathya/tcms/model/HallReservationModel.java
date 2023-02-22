package lk.vidathya.tcms.model;

import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.HallReservation;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HallReservationModel {
    public static boolean addHallReservation(HallReservation hallReservation) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO HallReservation VALUES (?,?,?,?,?,?)",
                hallReservation.getHallReservationNo(),
                hallReservation.getHallNo(),
                hallReservation.getClassCode(),
                hallReservation.getDay(),
                hallReservation.getStartTime(),
                hallReservation.getEndTime()
                );
        return isAdd;
    }

    public static boolean deleteHallReservation(String hallReservationNo) throws SQLException, ClassNotFoundException {
        boolean isDelete = CrudUtil.execute("DELETE FROM HallReservation WHERE hallReservationNo=?", hallReservationNo);
        return isDelete;
    }

    public static boolean updateHallReservation(HallReservation hallReservation) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE HallReservation SET hallNo=? , day=?, startTime=?, endTime=? WHERE hallReservationNo=?",
                hallReservation.getHallNo(),
                hallReservation.getDay(),
                hallReservation.getStartTime(),
                hallReservation.getEndTime(),
                hallReservation.getHallReservationNo()
        );
        return isUpdate;
    }

    public static String getHallReservationNo(String classCode) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT hallReservationNo FROM HallReservation WHERE classCode=? ",classCode);
        if(result.next()){
            return result.getString("hallReservationNo");
        }
        return null;
    }

    public static ResultSet getDataToViewTable() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM HallReservation ORDER BY day ASC");
        return result;
    }

    public static String getLastHallReservationNo() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT hallReservationNo FROM HallReservation ORDER BY hallReservationNo DESC LIMIT 1");
        if(result.next()){
            return result.getString("hallReservationNo");
        }
        return null;
    }


}
