package lk.vidathya.tcms.tableModel;

public class EClassHallReservationTM {

    private String extraClassHallNo;
    private String extraClassCode;
    private String extraClassGrade;
    private String extraClassSubject;
    private String extraClassDate;
    private String extraClassStartTime;
    private String extraClassEndTime;

    public EClassHallReservationTM() {
    }

    public EClassHallReservationTM(String extraClassHallNo, String extraClassCode, String extraClassGrade, String extraClassSubject, String extraClassDate, String extraClassStartTime, String extraClassEndTime) {
        this.extraClassHallNo = extraClassHallNo;
        this.extraClassCode = extraClassCode;
        this.extraClassGrade = extraClassGrade;
        this.extraClassSubject = extraClassSubject;
        this.extraClassDate = extraClassDate;
        this.extraClassStartTime = extraClassStartTime;
        this.extraClassEndTime = extraClassEndTime;
    }

    public String getExtraClassHallNo() {
        return extraClassHallNo;
    }

    public void setExtraClassHallNo(String extraClassHallNo) {
        this.extraClassHallNo = extraClassHallNo;
    }

    public String getExtraClassCode() {
        return extraClassCode;
    }

    public void setExtraClassCode(String extraClassCode) {
        this.extraClassCode = extraClassCode;
    }

    public String getExtraClassGrade() {
        return extraClassGrade;
    }

    public void setExtraClassGrade(String extraClassGrade) {
        this.extraClassGrade = extraClassGrade;
    }

    public String getExtraClassSubject() {
        return extraClassSubject;
    }

    public void setExtraClassSubject(String extraClassSubject) {
        this.extraClassSubject = extraClassSubject;
    }

    public String getExtraClassDate() {
        return extraClassDate;
    }

    public void setExtraClassDate(String extraClassDate) {
        this.extraClassDate = extraClassDate;
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
