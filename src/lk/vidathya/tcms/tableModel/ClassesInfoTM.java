package lk.vidathya.tcms.tableModel;

public class ClassesInfoTM {
    private String classCode;
    private String subject;
    private String grade;
    private String tutorName;
    private String day;

    public ClassesInfoTM() {
    }

    public ClassesInfoTM(String classCode, String subject, String grade, String tutorName, String day) {
        this.classCode = classCode;
        this.subject = subject;
        this.grade = grade;
        this.tutorName = tutorName;
        this.day = day;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
