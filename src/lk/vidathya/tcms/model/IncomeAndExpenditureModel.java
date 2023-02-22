package lk.vidathya.tcms.model;

import lk.vidathya.tcms.transferObject.IncomeAndExpenditure;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeAndExpenditureModel {

    public static boolean addIncomeAndExpenditure(IncomeAndExpenditure incomeAndExpenditure) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO IncomeExpenditure (staffId, type, description, amount, year, month, date ) VALUES(?,?,?,?,?,?,?)",
                incomeAndExpenditure.getStaffId(),
                incomeAndExpenditure.getType(),
                incomeAndExpenditure.getDescription(),
                incomeAndExpenditure.getAmount(),
                incomeAndExpenditure.getYear(),
                incomeAndExpenditure.getMonth(),
                incomeAndExpenditure.getDate()
                );
        return isAdd;
    }

    public static ResultSet getIncomeDataTable(int year, String monthName) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT date,description,amount FROM IncomeExpenditure WHERE (year=?) AND (month=?) AND (type=?)",year, monthName,"Income");

        return result;
    }

    public static ResultSet getExpenditureDataTable(int year,String monthName) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT date,description,amount FROM IncomeExpenditure WHERE (year=?) AND (month=?) AND (type=?)",year, monthName,"Expenditure");

        return result;
    }


}
