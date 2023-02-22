package lk.vidathya.tcms.model;

import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.transferObject.IncomeAndExpenditure;
import lk.vidathya.tcms.transferObject.RegistrationPayment;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationPaymentModel {

    public static boolean addPayment(RegistrationPayment registrationPayment) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdd = CrudUtil.execute("INSERT INTO RegistrationPayment (fee, studentId, year, month, date, staffId ) VALUES(?,?,?,?,?,?)",
                    registrationPayment.getFee(),
                    registrationPayment.getStudentId(),
                    registrationPayment.getYear(),
                    registrationPayment.getMonth(),
                    registrationPayment.getDate(),
                    registrationPayment.getStaffId()
                    );

            if (isAdd){
                IncomeAndExpenditure budget = new IncomeAndExpenditure(
                        registrationPayment.getStaffId(),
                        "Income",
                        "Registration Payment",
                        registrationPayment.getFee(),
                        registrationPayment.getYear(),
                        registrationPayment.getMonth(),
                        registrationPayment.getDate()
                );
                boolean isAddIncome = IncomeAndExpenditureModel.addIncomeAndExpenditure(budget);
                if(isAddIncome){
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

    public static ResultSet getAllData(String monthName, int year) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM RegistrationPayment WHERE (year=?) AND (month=?)", year, monthName);
        return result;
    }


}
