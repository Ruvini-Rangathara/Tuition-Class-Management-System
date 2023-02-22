package lk.vidathya.tcms.tableModel;

public class TutorSalaryInfoTM {

    private String tutorId;
    private String name;
    private String subject;
    private double salary;
    private String month;
    private String paymentDate;
    private String payerId;

    public TutorSalaryInfoTM() {
    }

    public TutorSalaryInfoTM(String tutorId, String name, String subject, double salary, String month, String paymentDate, String payerId) {
        this.tutorId = tutorId;
        this.name = name;
        this.subject = subject;
        this.salary = salary;
        this.month = month;
        this.paymentDate = paymentDate;
        this.payerId = payerId;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }
}
