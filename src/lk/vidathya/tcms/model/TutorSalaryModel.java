package lk.vidathya.tcms.model;

import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.transferObject.IncomeAndExpenditure;
import lk.vidathya.tcms.transferObject.TutorSalary;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorSalaryModel {
    public static String getLastPaymentCode() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT paymentCode FROM TutorSalary ORDER BY paymentCode DESC LIMIT 1");
        if(result.next()){
            return result.getString("paymentCode");
        }
        return null;
    }

    public static ResultSet getAllNotPaidData(String monthName, int year) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM NotPaidTutorSalary WHERE (year=?) AND (month=?)",year, monthName);
        return result;
    }

    public static ResultSet getAllData(String monthName, int year) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM TutorSalary WHERE (year=?) AND (month=?)",year, monthName);
        return result;
    }

    public static double getNotPaidTutorSalary(String tutorId, String classCode) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT salary FROM NotPaidTutorSalary WHERE (tutorId=?) AND (classCode=?)", tutorId, classCode);
        if(result.next()){
            return result.getDouble("salary");
        }
        return 0;
    }

    public static boolean addPayment(TutorSalary tutorSalary) throws SQLException, ClassNotFoundException {

        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);

            boolean isAddTutorSalary = CrudUtil.execute("INSERT INTO TutorSalary VALUES (?,?,?,?,?,?,?,?)",
                    tutorSalary.getPaymentCode(),
                    tutorSalary.getClassCode(),
                    tutorSalary.getTutorId(),
                    tutorSalary.getYear(),
                    tutorSalary.getMonth(),
                    tutorSalary.getSalary(),
                    tutorSalary.getDate(),
                    tutorSalary.getPayerId()
            );

            if (isAddTutorSalary) {

                boolean isUpdateNotPaidTutorSalary = TutorSalaryModel.updateTutorSalary(tutorSalary.getTutorId(), tutorSalary.getClassCode());
                 if(isUpdateNotPaidTutorSalary){
                     IncomeAndExpenditure budget = new IncomeAndExpenditure(
                             tutorSalary.getPayerId(),
                             "Expenditure",
                             "Tutor Salary : "+tutorSalary.getTutorId(),
                             tutorSalary.getSalary(),
                             tutorSalary.getYear(),
                             tutorSalary.getMonth(),
                             tutorSalary.getDate()
                     );

                     boolean isAddExpenditure = IncomeAndExpenditureModel.addIncomeAndExpenditure(budget);
                     if (isAddExpenditure) {
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

    private static boolean updateTutorSalary(String tutorId, String classCode) throws SQLException, ClassNotFoundException {
        boolean update = CrudUtil.execute("UPDATE NotPaidTutorSalary SET salary=? WHERE (tutorId=?) AND (classCode=?)", 0,tutorId,classCode);
        return update;
    }
}
