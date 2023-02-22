package lk.vidathya.tcms.tableModel;

public class RegistrationPaymentTM {
    private String paymentCode;
    private String studentId;
    private String studentName;
    private String date;
    private String staffId;
    private String staffName;
    private double fee;

    public RegistrationPaymentTM() {
    }

    public RegistrationPaymentTM(String paymentCode, String studentId, String studentName, String date, String staffId, String staffName, double fee) {
        this.paymentCode = paymentCode;
        this.studentId = studentId;
        this.studentName = studentName;
        this.date = date;
        this.staffId = staffId;
        this.staffName = staffName;
        this.fee = fee;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
