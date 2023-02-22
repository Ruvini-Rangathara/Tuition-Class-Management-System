package lk.vidathya.tcms.tableModel;

public class PaidStaffSalaryTM {
    private String paymentCode;
    private String staffId;
    private String name;
    private String job;
    private double salary;
    private String payerId;
    private String date;

    public PaidStaffSalaryTM() {
    }

    public PaidStaffSalaryTM(String paymentCode, String staffId, String name, String job, double salary, String payerId, String date) {
        this.paymentCode = paymentCode;
        this.staffId = staffId;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.payerId = payerId;
        this.date = date;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
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

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
