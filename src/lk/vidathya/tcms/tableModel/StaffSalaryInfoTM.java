package lk.vidathya.tcms.tableModel;

public class StaffSalaryInfoTM {
    private String staffId;
    private String name;
    private String job;
    private double salary;
    private String joinDate;

    public StaffSalaryInfoTM() {
    }

    public StaffSalaryInfoTM(String staffId, String name, String job, double salary, String joinDate) {
        this.staffId = staffId;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.joinDate = joinDate;
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

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
