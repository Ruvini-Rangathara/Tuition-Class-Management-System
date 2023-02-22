package lk.vidathya.tcms.tableModel;

public class NotPaidTutorSalaryTM {

    private String notPaidTutorId;
    private String notPaidName;
    private String notPaidSubject;
    private String notPaidGrade;
    private String notPaidClassCode;
    private double notPaidSalary;

    public NotPaidTutorSalaryTM() {
    }

    public NotPaidTutorSalaryTM(String notPaidTutorId, String notPaidName, String notPaidSubject, String notPaidGrade, String notPaidClassCode, double notPaidSalary) {
        this.notPaidTutorId = notPaidTutorId;
        this.notPaidName = notPaidName;
        this.notPaidSubject = notPaidSubject;
        this.notPaidGrade = notPaidGrade;
        this.notPaidClassCode = notPaidClassCode;
        this.notPaidSalary = notPaidSalary;
    }

    public String getNotPaidTutorId() {
        return notPaidTutorId;
    }

    public void setNotPaidTutorId(String notPaidTutorId) {
        this.notPaidTutorId = notPaidTutorId;
    }

    public String getNotPaidName() {
        return notPaidName;
    }

    public void setNotPaidName(String notPaidName) {
        this.notPaidName = notPaidName;
    }

    public String getNotPaidSubject() {
        return notPaidSubject;
    }

    public void setNotPaidSubject(String notPaidSubject) {
        this.notPaidSubject = notPaidSubject;
    }

    public String getNotPaidClassCode() {
        return notPaidClassCode;
    }

    public void setNotPaidClassCode(String notPaidClassCode) {
        this.notPaidClassCode = notPaidClassCode;
    }

    public double getNotPaidSalary() {
        return notPaidSalary;
    }

    public void setNotPaidSalary(double notPaidSalary) {
        this.notPaidSalary = notPaidSalary;
    }

    public String getNotPaidGrade() {
        return notPaidGrade;
    }

    public void setNotPaidGrade(String notPaidGrade) {
        this.notPaidGrade = notPaidGrade;
    }
}
