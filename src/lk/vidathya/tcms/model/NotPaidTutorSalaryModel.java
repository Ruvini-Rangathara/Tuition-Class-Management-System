package lk.vidathya.tcms.model;

import lk.vidathya.tcms.transferObject.NotPaidTutorSalary;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotPaidTutorSalaryModel {

    public static boolean addTutorRecord(NotPaidTutorSalary notPaidTutorSalary) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO NotPaidTutorSalary VALUES (?,?,?,?,?)",
                notPaidTutorSalary.getTutorId(),
                notPaidTutorSalary.getClassCode(),
                notPaidTutorSalary.getYear(),
                notPaidTutorSalary.getMonth(),
                notPaidTutorSalary.getSalary()
                );
        return  isAdd;
    }

    public static boolean updateTutorRecord(NotPaidTutorSalary notPaidTutorSalary ) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE NotPaidTutorSalary SET year=?, month=?, salary=? WHERE (tutorId=?) AND (classCode=?)",
                notPaidTutorSalary.getYear(),
                notPaidTutorSalary.getMonth(),
                notPaidTutorSalary.getSalary(),
                notPaidTutorSalary.getTutorId(),
                notPaidTutorSalary.getClassCode()
        );
        return  isUpdate;
    }

    public static double getExistSalary(String tutorId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT salary FROM NotPaidTutorSalary WHERE tutorId=?", tutorId);
        if(result.next()){
            return result.getDouble("salary");
        }
        return 0;
    }


}
