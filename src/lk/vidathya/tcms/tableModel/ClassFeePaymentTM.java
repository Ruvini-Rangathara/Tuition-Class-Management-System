package lk.vidathya.tcms.tableModel;

public class ClassFeePaymentTM {
    private String paymentCode;
    private String studentId;
    private String name;
    private String date;
    private double amount;
    private String staffId;
    private String staffName;

    public ClassFeePaymentTM() {
    }

    public ClassFeePaymentTM(String paymentCode, String studentId, String name, String date, double amount, String staffId, String staffName) {
        this.paymentCode = paymentCode;
        this.studentId = studentId;
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.staffId = staffId;
        this.staffName = staffName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
