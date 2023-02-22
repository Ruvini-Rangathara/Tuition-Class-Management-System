package lk.vidathya.tcms.transferObject;

public class Staff {
    private String staffId;
    private String name;
    private String job;
    private String nic;
    private String gender;
    private String address;
    private String contactNo;
    private String email;
    private String dob;
    private double salary;
    private String date;

    public Staff() {
    }

    public Staff(String staffId, String name, String job, String nic, String gender, String address, String contactNo, String email, String dob, double salary, String date) {
        this.staffId = staffId;
        this.name = name;
        this.job = job;
        this.nic = nic;
        this.gender = gender;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
        this.dob = dob;
        this.salary = salary;
        this.date = date;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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
}
