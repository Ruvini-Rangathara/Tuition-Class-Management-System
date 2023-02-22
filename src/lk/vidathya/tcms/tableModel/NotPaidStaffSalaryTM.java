package lk.vidathya.tcms.tableModel;

public class NotPaidStaffSalaryTM {
    private String notPaidStaffId;
    private String notPaidName;
    private String notPaidJob;
    private String notPaidEmail;
    private double notPaidSalary;

    public NotPaidStaffSalaryTM() {
    }

    public NotPaidStaffSalaryTM(String notPaidStaffId, String notPaidName, String notPaidJob, String notPaidEmail, double notPaidSalary) {
        this.notPaidStaffId = notPaidStaffId;
        this.notPaidName = notPaidName;
        this.notPaidJob = notPaidJob;
        this.notPaidEmail = notPaidEmail;
        this.notPaidSalary = notPaidSalary;
    }

    public String getNotPaidStaffId() {
        return notPaidStaffId;
    }

    public void setNotPaidStaffId(String notPaidStaffId) {
        this.notPaidStaffId = notPaidStaffId;
    }

    public String getNotPaidName() {
        return notPaidName;
    }

    public void setNotPaidName(String notPaidName) {
        this.notPaidName = notPaidName;
    }

    public String getNotPaidJob() {
        return notPaidJob;
    }

    public void setNotPaidJob(String notPaidJob) {
        this.notPaidJob = notPaidJob;
    }

    public String getNotPaidEmail() {
        return notPaidEmail;
    }

    public void setNotPaidEmail(String notPaidEmail) {
        this.notPaidEmail = notPaidEmail;
    }

    public double getNotPaidSalary() {
        return notPaidSalary;
    }

    public void setNotPaidSalary(double notPaidSalary) {
        this.notPaidSalary = notPaidSalary;
    }
}
