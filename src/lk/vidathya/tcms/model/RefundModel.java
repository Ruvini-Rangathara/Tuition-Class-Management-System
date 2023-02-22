package lk.vidathya.tcms.model;

import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.transferObject.IncomeAndExpenditure;
import lk.vidathya.tcms.transferObject.Refund;
import lk.vidathya.tcms.util.CrudUtil;
import lk.vidathya.tcms.util.MonthName;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefundModel {
    public static boolean makeRefund(Refund refund) throws SQLException, ClassNotFoundException {

        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAddRefund = CrudUtil.execute("INSERT INTO Refund VALUES (?,?,?,?,?,?,?)",
                    refund.getPaymentCode(),
                    refund.getStudentId(),
                    refund.getDescription(),
                    refund.getAmount(),
                    refund.getDate(),
                    refund.getStaffId(),
                    refund.getMonth()
            );
            if (isAddRefund) {
                int year = Integer.parseInt(refund.getDate().split("-")[0]);
                IncomeAndExpenditure budget = new IncomeAndExpenditure(
                        refund.getStaffId(),
                        "Expenditure",
                        "Refund",
                        refund.getAmount(),
                        year,
                        refund.getMonth(),
                        refund.getDate()
                );
                boolean isAddBudget = IncomeAndExpenditureModel.addIncomeAndExpenditure(budget);
                if (isAddBudget) {
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

    public static ResultSet getAllData(String month) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Refund WHERE month=? ORDER BY paymentCode DESC", month);
        return result;
    }


}
