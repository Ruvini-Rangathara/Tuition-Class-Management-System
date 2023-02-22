package lk.vidathya.tcms.model;

import lk.vidathya.tcms.transferObject.ExtraClassHallReservation;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtraClassHallReservationModel {

    public static boolean addExtraClassHallReservation(ExtraClassHallReservation extraClassHallReservation) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO ExtraClassHallReservation VALUES (?,?,?,?,?,?)",
                extraClassHallReservation.getHallReservationNo(),
                extraClassHallReservation.getHallNo(),
                extraClassHallReservation.geteClassCode(),
                extraClassHallReservation.getDate(),
                extraClassHallReservation.getStartTime(),
                extraClassHallReservation.getEndTime()
                );
        return isAdd;
    }

    public static boolean deleteExtraClassHallReservation(String reservationNo ) throws SQLException, ClassNotFoundException {
        boolean isDelete = CrudUtil.execute("DELETE FROM ExtraClassHallReservation WHERE hallReservationNo=?",
                reservationNo
                );
        return isDelete;

    }

    public static boolean updateExtraClassHallReservation(ExtraClassHallReservation extraClassHallReservation) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("UPDATE ExtraClassHallReservation SET hallNo=?, eClassCode=?, date=?, startTime=?, endTime=? WHERE hallReservationNo=?",
                extraClassHallReservation.getHallNo(),
                extraClassHallReservation.geteClassCode(),
                extraClassHallReservation.getDate(),
                extraClassHallReservation.getStartTime(),
                extraClassHallReservation.getEndTime(),
                extraClassHallReservation.getHallReservationNo()
                );
        return isAdd;

    }

    public static ResultSet getDataToViewTable() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM ExtraClassHallReservation ORDER BY date DESC");
        return result;
    }

    public static String getHallReservationNo(String eClassCode) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT hallReservationNo FROM ExtraClassHallReservation WHERE eClassCode =? ",eClassCode);
        if(result.next()){
            return result.getString("hallReservationNo");
        }
        return null;
    }

    public static String getLastHallReservationNo() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT hallReservationNo FROM ExtraClassHallReservation ORDER BY hallReservationNo DESC LIMIT 1");
        if(result.next()){
            return result.getString("hallReservationNo");
        }
        return null;
    }


}
