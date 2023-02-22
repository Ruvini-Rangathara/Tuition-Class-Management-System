package lk.vidathya.tcms.tableModel;

public class StudentInfoTM {
    private String studentId;
    private String name;
    private String nic;
    private String contactNo;
    private String email;

    public StudentInfoTM() {
    }

    public StudentInfoTM(String studentId, String name, String nic, String contactNo, String email) {
        this.studentId = studentId;
        this.name = name;
        this.nic = nic;
        this.contactNo = contactNo;
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
