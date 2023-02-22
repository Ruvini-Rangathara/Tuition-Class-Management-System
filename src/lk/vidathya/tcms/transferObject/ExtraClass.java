package lk.vidathya.tcms.transferObject;

import javax.mail.Session;

public class ExtraClass {
    private String eClassCode;
    private String classCode;
    private String date;
    private String startTime;
    private String endTime;
    private String hallNo;

    public ExtraClass() {
    }

    public ExtraClass(String eClassCode, String classCode, String date, String startTime, String endTime, String hallNo) {
        this.eClassCode = eClassCode;
        this.classCode = classCode;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hallNo = hallNo;
    }

    public String geteClassCode() {
        return eClassCode;
    }

    public void seteClassCode(String eClassCode) {
        this.eClassCode = eClassCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
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

    public String getHallNo() {
        return hallNo;
    }

    public void setHallNo(String hallNo) {
        this.hallNo = hallNo;
    }
}
