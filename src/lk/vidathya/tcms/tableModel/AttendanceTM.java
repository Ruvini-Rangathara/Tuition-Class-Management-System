package lk.vidathya.tcms.tableModel;

import javafx.scene.control.RadioButton;

public class AttendanceTM {

    private String studentId;
    private String name;
    private String paymentStatus;
    private double fee;

    public AttendanceTM() {
    }

    public AttendanceTM(String studentId, String name, String paymentStatus, double fee) {
        this.studentId = studentId;
        this.name = name;

        this.paymentStatus = paymentStatus;
        this.fee = fee;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }


}
