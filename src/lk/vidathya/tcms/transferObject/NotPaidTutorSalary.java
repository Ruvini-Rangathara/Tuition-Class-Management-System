package lk.vidathya.tcms.transferObject;

public class NotPaidTutorSalary {
    private String tutorId;
    private String classCode;
    private int year;
    private String month;
    private double salary;

    public NotPaidTutorSalary() {
    }

    public NotPaidTutorSalary(String tutorId, String classCode, int year, String month, double salary) {
        this.tutorId = tutorId;
        this.classCode = classCode;
        this.year = year;
        this.month = month;
        this.salary = salary;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
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

}
