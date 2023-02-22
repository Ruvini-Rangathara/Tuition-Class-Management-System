package lk.vidathya.tcms.transferObject;

public class TutorSalary {
    private String paymentCode;
    private String classCode;
    private String tutorId;
    private int year;
    private String month;
    private double salary;
    private String date;
    private String payerId;

    public TutorSalary() {
    }

    public TutorSalary(String paymentCode, String classCode, String tutorId, int year, String month, double salary, String date, String payerId) {
        this.paymentCode = paymentCode;
        this.classCode = classCode;
        this.tutorId = tutorId;
        this.year = year;
        this.month = month;
        this.salary = salary;
        this.date = date;
        this.payerId = payerId;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
