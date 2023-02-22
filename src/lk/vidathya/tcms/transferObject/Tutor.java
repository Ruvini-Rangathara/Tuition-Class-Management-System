package lk.vidathya.tcms.transferObject;

public class Tutor {

    private String tutorId;
    private String name;
    private String nic;
    private String gender;
    private String address;
    private String contactNo;
    private String email;
    private String dob;
    private String subject;
    private String date;

    public Tutor() {
    }

    public Tutor(String tutorId, String name, String nic, String gender, String address, String contactNo, String email, String dob, String subject, String date) {
        this.tutorId = tutorId;
        this.name = name;
        this.nic = nic;
        this.gender = gender;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
        this.dob = dob;
        this.subject = subject;
        this.date = date;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
