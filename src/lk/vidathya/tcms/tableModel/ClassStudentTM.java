package lk.vidathya.tcms.tableModel;

public class ClassStudentTM {
    private String studentId;
    private String name;
    private String email;
    private String contactNo;
    private String joinDate;

    public ClassStudentTM() {
    }

    public ClassStudentTM(String studentId, String name, String email, String contactNo, String joinDate) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.joinDate = joinDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
