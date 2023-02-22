package lk.vidathya.tcms.model;

import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentPaymentModel {

    public static String getPaymentStatus(String studentId, String classCode, int year, String monthName) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("select * from studentPayment where studentId=? and classCode=? and year=? and month=?",
                studentId,
                classCode,
                year,
                monthName
                );

        if(result.next()){
            return "paid";
        }
        return "Not paid";
    }
}
