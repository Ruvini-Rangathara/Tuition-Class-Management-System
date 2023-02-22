package lk.vidathya.tcms.model;

import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.transferObject.IncomeAndExpenditure;
import lk.vidathya.tcms.transferObject.Staff;
import lk.vidathya.tcms.transferObject.StaffSalary;
import lk.vidathya.tcms.util.CrudUtil;
import lk.vidathya.tcms.util.MonthName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StaffSalaryModel {

    public static String getLastPaymentCode() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT paymentCode FROM StaffSalary ORDER BY paymentCode DESC LIMIT 1");
        if(result.next()){
            return result.getString("paymentCode");
        }
        return null;
    }

    public static boolean addPayment(StaffSalary staffSalary) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAddPayment = CrudUtil.execute("INSERT INTO StaffSalary VALUES(?,?,?,?,?,?,?)",
                    staffSalary.getPaymentCode(),
                    staffSalary.getStaffId(),
                    staffSalary.getYear(),
                    staffSalary.getMonth(),
                    staffSalary.getSalary(),
                    staffSalary.getDate(),
                    staffSalary.getPayerId()
            );
            boolean isUpdate =false ;
            if(isAddPayment){
                int year=staffSalary.getYear();
                if(staffSalary.getMonth().equals("December")){
                    year++;
                }

                String month=null;
                if(staffSalary.getMonth().equals("January")){
                    month="February";
                }
                if(staffSalary.getMonth().equals("February")){
                    month="March";
                }
                if(staffSalary.getMonth().equals("March")){
                    month="April";
                }
                if(staffSalary.getMonth().equals("April")){
                    month="May";
                }
                if(staffSalary.getMonth().equals("May")){
                    month="June";
                }
                if(staffSalary.getMonth().equals("June")){
                    month="July";
                }
                if(staffSalary.getMonth().equals("July")){
                    month="August";
                }
                if(staffSalary.getMonth().equals("August")){
                    month="September";
                }
                if(staffSalary.getMonth().equals("September")){
                    month="October";
                }
                if(staffSalary.getMonth().equals("October")){
                    month="November";
                }
                if(staffSalary.getMonth().equals("November")){
                    month="December";
                }
                if(staffSalary.getMonth().equals("December")){
                    month="January";
                }

                isUpdate = CrudUtil.execute("UPDATE NotPaidStaffSalary SET year=?, month=? WHERE staffId=?",
                        year,
                        month,
                        staffSalary.getStaffId());
            }
            if (isUpdate){
                IncomeAndExpenditure budget = new IncomeAndExpenditure(
                        staffSalary.getPayerId(),
                        "Expenditure",
                        "Staff Salary : "+staffSalary.getStaffId(),
                        staffSalary.getSalary(),
                        staffSalary.getYear(),
                        staffSalary.getMonth(),
                        staffSalary.getDate()
                );
                boolean isAddExpenditure = IncomeAndExpenditureModel.addIncomeAndExpenditure(budget);
                if(isAddExpenditure){
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

    public static ResultSet getSalaryData(int year, String monthName) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM StaffSalary WHERE (year=?) AND (month=?) ORDER BY paymentCode ASC",year,monthName);
        return result;
    }

    public static ResultSet getSalaryData(int year) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM StaffSalary WHERE year=? ORDER BY paymentCode ASC",year);
        return result;
    }

    public static ResultSet getSalaryData(String month) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM StaffSalary WHERE month=? ORDER BY paymentCode ASC",month);
        return result;
    }

    public static ResultSet getAllData(String monthName, int year) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM StaffSalary WHERE month=? AND year=? ORDER BY paymentCode ASC",monthName, year);
        return result;
    }

    public static ResultSet getAllNotPaidData(String monthName, int year) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM NotPaidStaffSalary WHERE month=? AND year=? ORDER BY staffId ASC",monthName, year);
        return result;
    }

    public static boolean addNotPaidStaffSalary(Staff staff) throws SQLException, ClassNotFoundException {
        String date = String.valueOf(LocalDate.now());
        String month = MonthName.getMonthName(date);
        int year = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);

        boolean isAdd = CrudUtil.execute("INSERT INTO NotPaidStaffSalary VALUES (?,?,?,?)",
                staff.getStaffId(),
                year,
                month,
                staff.getSalary()
                );
        return isAdd;
    }


}
