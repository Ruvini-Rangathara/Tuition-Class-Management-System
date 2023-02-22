package lk.vidathya.tcms.tableModel;

public class ClassScheduleTM {
    private String subject;
    private String grade;
    private String hall;
    private String startTime;
    private String endTime;

    public ClassScheduleTM() {
    }

    public ClassScheduleTM(String subject, String grade, String hall, String startTime, String endTime) {
        this.subject = subject;
        this.grade = grade;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
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
