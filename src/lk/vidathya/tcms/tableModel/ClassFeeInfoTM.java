package lk.vidathya.tcms.tableModel;

public class ClassFeeInfoTM {
    private String classCode;
    private String subject;
    private String grade;
    private double fee;
    private String tutorName;

    public ClassFeeInfoTM() {
    }

    public ClassFeeInfoTM(String classCode, String subject, String grade, double fee, String tutorName) {
        this.classCode = classCode;
        this.subject = subject;
        this.grade = grade;
        this.fee = fee;
        this.tutorName = tutorName;
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }
}
