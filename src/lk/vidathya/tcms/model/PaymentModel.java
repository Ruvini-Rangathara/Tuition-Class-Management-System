package lk.vidathya.tcms.model;

import lk.vidathya.tcms.db.DBConnection;
import lk.vidathya.tcms.transferObject.Classes;
import lk.vidathya.tcms.transferObject.IncomeAndExpenditure;
import lk.vidathya.tcms.transferObject.NotPaidTutorSalary;
import lk.vidathya.tcms.transferObject.Payment;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PaymentModel {

    public static String getLastPaymentCode() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT paymentCode FROM StudentPayment ORDER BY paymentCode DESC LIMIT 1");
        if(result.next()){
            return result.getString("paymentCode");
        }
        return null;
    }

    public static Payment getPaymentDetails(String code) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM StudentPayment WHERE paymentCode=?",code);
        if(result.next()){
            Payment payment = new Payment(
                    result.getString("paymentCode"),
                    result.getString("description"),
                    result.getString("classCode"),
                    result.getDouble("fee"),
                    result.getString("studentId"),
                    result.getInt("year"),
                    result.getString("month"),
                    result.getString("date"),
                    result.getString("staffId")
            );
            return payment;
        }
        return null;
    }

    public static boolean addPayment(Payment payment) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAddPayment = CrudUtil.execute("INSERT INTO StudentPayment VALUES(?,?,?,?,?,?,?,?,?)",
                    payment.getPaymentCode(),
                    payment.getDescription(),
                    payment.getClassCode(),
                    payment.getFee(),
                    payment.getStudentId(),
                    payment.getYear(),
                    payment.getMonth(),
                    payment.getDate(),
                    payment.getStaffId()
            );

                if(isAddPayment){
                    Classes classes = ClassModel.getClassDetails(payment.getClassCode());

                    IncomeAndExpenditure budget = new IncomeAndExpenditure(
                            payment.getStaffId(),
                            "Income",
                            payment.getDescription(),
                            payment.getFee(),
                            payment.getYear(),
                            payment.getMonth(),
                            payment.getDate()
                    );

                    boolean isAddIncome = IncomeAndExpenditureModel.addIncomeAndExpenditure(budget);

                    if(isAddIncome){
                        double existSalary = NotPaidTutorSalaryModel.getExistSalary(classes.getTutorId());

                        NotPaidTutorSalary notPaidTutorSalary = new NotPaidTutorSalary(
                                classes.getTutorId(),
                                classes.getClassCode(),
                                payment.getYear(),
                                payment.getMonth(),
                                payment.getFee()+existSalary
                        );

                        boolean isUpdate = NotPaidTutorSalaryModel.updateTutorRecord(notPaidTutorSalary);
                        if(isUpdate){
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

    public static ResultSet getPaidData(String classCode, int year, String month) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM StudentPayment WHERE (classCode=?) AND (year=?) AND (month=?) ORDER BY paymentCode DESC",
                classCode,
                year,
                month
                );
        return result;
    }


}
