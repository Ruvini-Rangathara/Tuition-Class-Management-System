package lk.vidathya.tcms.transferObject;

public class Payment {

    private String paymentCode;
    private String description;
    private String classCode;
    private double fee;
    private String studentId;
    private int year;
    private String month;
    private String date;
    private String staffId;

    public Payment() {
    }

    public Payment(String paymentCode, String description, String classCode, double fee, String studentId, int year, String month, String date, String staffId) {
        this.paymentCode = paymentCode;
        this.description = description;
        this.classCode = classCode;
        this.fee = fee;
        this.studentId = studentId;
        this.year = year;
        this.month = month;
        this.date = date;
        this.staffId = staffId;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
