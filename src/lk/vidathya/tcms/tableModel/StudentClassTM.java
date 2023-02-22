package lk.vidathya.tcms.tableModel;

public class StudentClassTM {

    private String classCode;
    private String grade;
    private String subject;
    private String tutorName;
    private String joinDate;

    public StudentClassTM() {
    }

    public StudentClassTM(String classCode, String grade, String subject, String tutorName, String joinDate) {
        this.classCode = classCode;
        this.grade = grade;
        this.subject = subject;
        this.tutorName = tutorName;
        this.joinDate = joinDate;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
