package lk.vidathya.tcms.model;

import lk.vidathya.tcms.transferObject.Attendance;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceModel {

    public static boolean addAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {

        boolean isAdd = CrudUtil.execute("INSERT INTO StudentAttendance VALUES (?,?,?,?,?,?)",
                attendance.getStudentId(),
                attendance.getClassCode(),
                attendance.getPresentOrAbsent(),
                attendance.getDate(),
                attendance.getYear(),
                attendance.getMonth()
                );

        return isAdd;
    }

    public static ArrayList<String> getStudentIds(String classCode, String date) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT studentId FROM StudentAttendance WHERE classCode=? AND date=?", classCode,date);

        ArrayList<String> ids = new ArrayList<>();
        while (result.next()){
            ids.add(result.getString("studentId"));
        }
        return ids;
    }

    public static ResultSet getAllData(String classCode, String date) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM StudentAttendance WHERE classCode=? AND date=?", classCode, date);
        return result;
    }

}
