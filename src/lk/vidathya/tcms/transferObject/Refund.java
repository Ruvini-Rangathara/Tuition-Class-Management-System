package lk.vidathya.tcms.transferObject;

public class Refund {
    private String paymentCode;
    private String studentId;
    private String description;
    private double amount;
    private String date;
    private String staffId;
    private String month;

    public Refund() {
    }

    public Refund(String paymentCode, String studentId, String description, double amount, String date, String staffId, String month) {
        this.paymentCode = paymentCode;
        this.studentId = studentId;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.staffId = staffId;
        this.month = month;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
