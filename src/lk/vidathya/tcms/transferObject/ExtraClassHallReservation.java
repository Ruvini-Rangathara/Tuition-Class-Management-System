package lk.vidathya.tcms.transferObject;

public class ExtraClassHallReservation {
    private String hallReservationNo;
    private String hallNo;
    private String eClassCode;
    private String date;
    private String startTime;
    private String endTime;

    public ExtraClassHallReservation() {
    }

    public ExtraClassHallReservation(String hallReservationNo, String hallNo, String eClassCode, String date, String startTime, String endTime) {
        this.hallReservationNo = hallReservationNo;
        this.hallNo = hallNo;
        this.eClassCode = eClassCode;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getHallNo() {
        return hallNo;
    }

    public void setHallNo(String hallNo) {
        this.hallNo = hallNo;
    }

    public String geteClassCode() {
        return eClassCode;
    }

    public void seteClassCode(String eClassCode) {
        this.eClassCode = eClassCode;
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

    public String getHallReservationNo() {
        return hallReservationNo;
    }

    public void setHallReservationNo(String hallReservationNo) {
        this.hallReservationNo = hallReservationNo;
    }
}
