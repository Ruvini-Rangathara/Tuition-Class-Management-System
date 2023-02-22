package lk.vidathya.tcms.tableModel;

public class ExtraClassScheduleTM {
    private String extraClassSubject;
    private String extraClassGrade;
    private String extraClassHallNo;
    private String extraClassStartTime;
    private String extraClassEndTime;

    public ExtraClassScheduleTM() {
    }

    public ExtraClassScheduleTM(String extraClassSubject, String extraClassGrade, String extraClassHallNo, String extraClassStartTime, String extraClassEndTime) {
        this.extraClassSubject = extraClassSubject;
        this.extraClassGrade = extraClassGrade;
        this.extraClassHallNo = extraClassHallNo;
        this.extraClassStartTime = extraClassStartTime;
        this.extraClassEndTime = extraClassEndTime;
    }

    public String getExtraClassSubject() {
        return extraClassSubject;
    }

    public void setExtraClassSubject(String extraClassSubject) {
        this.extraClassSubject = extraClassSubject;
    }

    public String getExtraClassGrade() {
        return extraClassGrade;
    }

    public void setExtraClassGrade(String extraClassGrade) {
        this.extraClassGrade = extraClassGrade;
    }

    public String getExtraClassHallNo() {
        return extraClassHallNo;
    }

    public void setExtraClassHallNo(String extraClassHallNo) {
        this.extraClassHallNo = extraClassHallNo;
    }

    public String getExtraClassStartTime() {
        return extraClassStartTime;
    }

    public void setExtraClassStartTime(String extraClassStartTime) {
        this.extraClassStartTime = extraClassStartTime;
    }

    public String getExtraClassEndTime() {
        return extraClassEndTime;
    }

    public void setExtraClassEndTime(String extraClassEndTime) {
        this.extraClassEndTime = extraClassEndTime;
    }
}
