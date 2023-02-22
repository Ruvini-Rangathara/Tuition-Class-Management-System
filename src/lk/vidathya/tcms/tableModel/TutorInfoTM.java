package lk.vidathya.tcms.tableModel;

public class TutorInfoTM {
    private String tutorId;
    private String name;
    private String subject;
    private String contactNo;
    private String email;

    public TutorInfoTM() {
    }

    public TutorInfoTM(String tutorId, String name, String subject, String contactNo, String email) {
        this.tutorId = tutorId;
        this.name = name;
        this.subject = subject;
        this.contactNo = contactNo;
        this.email = email;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
