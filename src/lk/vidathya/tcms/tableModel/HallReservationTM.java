package lk.vidathya.tcms.tableModel;

public class HallReservationTM {
    private String hallNo;
    private String classCode;
    private String grade;
    private String subject;
    private String day;
    private String startTime;
    private String endTime;

    public HallReservationTM() {
    }

    public HallReservationTM(String hallNo, String classCode, String grade, String subject, String day, String startTime, String endTime) {
        this.hallNo = hallNo;
        this.classCode = classCode;
        this.grade = grade;
        this.subject = subject;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getHallNo() {
        return hallNo;
    }

    public void setHallNo(String hallNo) {
        this.hallNo = hallNo;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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
}
