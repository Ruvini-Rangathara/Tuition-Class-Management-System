package lk.vidathya.tcms.transferObject;

public class RegistrationPayment {
    private double fee;
    private String studentId;
    private int year;
    private String month;
    private String date;
    private String staffId;

    public RegistrationPayment(double fee, String studentId, int year, String month, String date, String staffId) {
        this.fee = fee;
        this.studentId = studentId;
        this.year = year;
        this.month = month;
        this.date = date;
        this.staffId = staffId;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
}
