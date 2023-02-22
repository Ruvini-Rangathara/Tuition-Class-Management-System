package lk.vidathya.tcms.tableModel;

public class HallTM {
    private String hallNo;
    private int capacity;
    private String availableFacilities;

    public HallTM() {
    }

    public HallTM(String hallNo, int capacity, String availableFacilities) {
        this.hallNo = hallNo;
        this.capacity = capacity;
        this.availableFacilities = availableFacilities;
    }

    public String getHallNo() {
        return hallNo;
    }

    public void setHallNo(String hallNo) {
        this.hallNo = hallNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAvailableFacilities() {
        return availableFacilities;
    }

    public void setAvailableFacilities(String availableFacilities) {
        this.availableFacilities = availableFacilities;
    }
}
