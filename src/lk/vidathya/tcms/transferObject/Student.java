package lk.vidathya.tcms.transferObject;

public class Student {
    private String studentId;
    private String name ;
    private String nic;
    private String gender;
    private String address ;
    private String contactNo ;
    private String email;
    private String dob;
    private int age ;
    private String grade ;
    private String date ;
    private String guardianNic ;

    public Student() {
    }

    public Student(String studentId, String name, String nic, String gender, String address, String contactNo, String email, String dob, int age, String grade, String date, String guardianNic) {
        this.studentId = studentId;
        this.name = name;
        this.nic = nic;
        this.gender = gender;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
        this.dob = dob;
        this.age = age;
        this.grade = grade;
        this.date = date;
        this.guardianNic = guardianNic;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGuardianNic() {
        return guardianNic;
    }

    public void setGuardianNic(String guardianNic) {
        this.guardianNic = guardianNic;
    }
}
