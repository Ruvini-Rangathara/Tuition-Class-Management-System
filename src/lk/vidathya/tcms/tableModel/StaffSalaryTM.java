package lk.vidathya.tcms.tableModel;

public class StaffSalaryTM {
    private String paymentCode;
    private String staffId;
    private String name;
    private String job;
    private double salary;
    private String month;
    private String paymentDate;

    public StaffSalaryTM() {
    }

    public StaffSalaryTM(String paymentCode, String staffId, String name, String job, double salary, String month, String paymentDate) {
        this.paymentCode = paymentCode;
        this.staffId = staffId;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.month = month;
        this.paymentDate = paymentDate;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }
}
