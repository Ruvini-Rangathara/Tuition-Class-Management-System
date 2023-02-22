package lk.vidathya.tcms.tableModel;

public class ClassFeeNotPaidTM {
    private String notPaidStudentId;
    private String notPaidStudentName;
    private String notPaidContactNo;
    private String notPaidEmail;
    private double notPaidAmount;

    public ClassFeeNotPaidTM() {
    }

    public ClassFeeNotPaidTM(String notPaidStudentId, String notPaidStudentName, String notPaidContactNo, String notPaidEmail, double notPaidAmount) {
        this.notPaidStudentId = notPaidStudentId;
        this.notPaidStudentName = notPaidStudentName;
        this.notPaidContactNo = notPaidContactNo;
        this.notPaidEmail = notPaidEmail;
        this.notPaidAmount = notPaidAmount;
    }

    public String getNotPaidStudentId() {
        return notPaidStudentId;
    }

    public void setNotPaidStudentId(String notPaidStudentId) {
        this.notPaidStudentId = notPaidStudentId;
    }

    public String getNotPaidStudentName() {
        return notPaidStudentName;
    }

    public void setNotPaidStudentName(String notPaidStudentName) {
        this.notPaidStudentName = notPaidStudentName;
    }

    public String getNotPaidContactNo() {
        return notPaidContactNo;
    }

    public void setNotPaidContactNo(String notPaidContactNo) {
        this.notPaidContactNo = notPaidContactNo;
    }

    public String getNotPaidEmail() {
        return notPaidEmail;
    }

    public void setNotPaidEmail(String notPaidEmail) {
        this.notPaidEmail = notPaidEmail;
    }

    public double getNotPaidAmount() {
        return notPaidAmount;
    }

    public void setNotPaidAmount(double notPaidAmount) {
        this.notPaidAmount = notPaidAmount;
    }
}
