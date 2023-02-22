package lk.vidathya.tcms.tableModel;

public class StaffInfoTM {
    private String staffId;
    private String name;
    private String job;
    private String nic;
    private String contactNo;
    private String email;

    public StaffInfoTM() {
    }

    public StaffInfoTM(String staffId, String name, String job, String nic, String contactNo, String email) {
        this.staffId = staffId;
        this.name = name;
        this.job = job;
        this.nic = nic;
        this.contactNo = contactNo;
        this.email = email;
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
}
