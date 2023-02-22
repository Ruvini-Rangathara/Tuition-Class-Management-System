package lk.vidathya.tcms.transferObject;

public class Classes {
    private String classCode;
    private String grade;
    private String subject;
    private String tutorId;
    private String day;
    private String startTime;
    private String endTime;
    private String hallNo;
    private double classFee;
    private String date;

    public Classes() {
    }

    public Classes(String classCode, String grade, String subject, String tutorId, String day, String startTime, String endTime, String hallNo, double classFee, String date) {
        this.classCode = classCode;
        this.grade = grade;
        this.subject = subject;
        this.tutorId = tutorId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hallNo = hallNo;
        this.classFee = classFee;
        this.date = date;

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

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHallNo() {
        return hallNo;
    }

    public void setHallNo(String hallNo) {
        this.hallNo = hallNo;
    }

    public double getClassFee() {
        return classFee;
    }

    public void setClassFee(double classFee) {
        this.classFee = classFee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
