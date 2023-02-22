package lk.vidathya.tcms.transferObject;

public class HallReservation {
    private String hallReservationNo;
    private String hallNo;
    private String classCode;
    private String day;
    private String startTime;
    private String endTime;

    public HallReservation() {
    }

    public HallReservation(String hallReservationNo, String hallNo, String classCode, String day, String startTime, String endTime) {
        this.hallReservationNo = hallReservationNo;
        this.hallNo = hallNo;
        this.classCode = classCode;
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

    public String getHallReservationNo() {
        return hallReservationNo;
    }

    public void setHallReservationNo(String hallReservationNo) {
        this.hallReservationNo = hallReservationNo;
    }
}
