package lk.vidathya.tcms.transferObject;

public class IncomeAndExpenditure {
    private String staffId;
    private String type;
    private String description;
    private double amount;
    private int year;
    private String month;
    private String date;


    public IncomeAndExpenditure() {
    }

    public IncomeAndExpenditure(String staffId, String type, String description, double amount, int year, String month, String date) {
        this.staffId = staffId;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.year = year;
        this.month = month;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
