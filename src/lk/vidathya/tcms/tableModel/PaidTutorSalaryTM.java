package lk.vidathya.tcms.tableModel;

public class PaidTutorSalaryTM {

    private String paymentCode;
    private String tutorId;
    private String name;
    private String classCode;
    private double salary;
    private String payerId;
    private String date;

    public PaidTutorSalaryTM() {
    }

    public PaidTutorSalaryTM(String paymentCode, String tutorId, String name, String classCode, double salary, String payerId, String date) {
        this.paymentCode = paymentCode;
        this.tutorId = tutorId;
        this.name = name;
        this.classCode = classCode;
        this.salary = salary;
        this.payerId = payerId;
        this.date = date;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setSubject(String classCode) {
        this.classCode = classCode;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
