package lk.vidathya.tcms.tableModel;

public class IncomeExpenditureTM {
    private String date;
    private String description;
    private double amount;

    public IncomeExpenditureTM() {
    }

    public IncomeExpenditureTM(String date, String description, double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
