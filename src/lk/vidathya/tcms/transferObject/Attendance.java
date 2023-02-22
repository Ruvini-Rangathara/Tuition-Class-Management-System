package lk.vidathya.tcms.transferObject;

public class Attendance {
    private String studentId;
    private String  classCode;
    private String  presentOrAbsent;
    private String  date;
    private int year;
    private String month;

    public Attendance() {
    }

    public Attendance(String studentId, String classCode, String presentOrAbsent, String date, int year, String month) {
        this.studentId = studentId;
        this.classCode = classCode;
        this.presentOrAbsent = presentOrAbsent;
        this.date = date;
        this.year = year;
        this.month = month;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getPresentOrAbsent() {
        return presentOrAbsent;
    }

    public void setPresentOrAbsent(String presentOrAbsent) {
        this.presentOrAbsent = presentOrAbsent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
