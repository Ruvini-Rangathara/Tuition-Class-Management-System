package lk.vidathya.tcms.tableModel;

public class RefundsTM {
    private String paymentCode;
    private String paymentDescription;
    private String studentId;
    private String studentName;
    private String paymentDate;
    private String refundDate;
    private String payerId;

    public RefundsTM() {
    }

    public RefundsTM(String paymentCode, String paymentDescription, String studentId, String studentName, String paymentDate, String refundDate, String payerId) {
        this.paymentCode = paymentCode;
        this.paymentDescription = paymentDescription;
        this.studentId = studentId;
        this.studentName = studentName;
        this.paymentDate = paymentDate;
        this.refundDate = refundDate;
        this.payerId = payerId;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }
}
