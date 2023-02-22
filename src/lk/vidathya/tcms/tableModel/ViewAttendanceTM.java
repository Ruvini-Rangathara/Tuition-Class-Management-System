package lk.vidathya.tcms.tableModel;

public class ViewAttendanceTM {

    private String studentId;
    private String name;
    private String date;
    private String startTime;
    private String endTime;

    public ViewAttendanceTM() {
    }

    public ViewAttendanceTM(String studentId, String name, String date, String startTime, String endTime) {
        this.studentId = studentId;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
