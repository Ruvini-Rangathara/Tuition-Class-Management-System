package lk.vidathya.tcms.transferObject;

public class Hall {

    private String hallNo;
    private String capacity;
    private String availableFacilities;

    public Hall() {
    }

    public Hall(String hallNo, String capacity, String availableFacilities) {
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

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getAvailableFacilities() {
        return availableFacilities;
    }

    public void setAvailableFacilities(String availableFacilities) {
        this.availableFacilities = availableFacilities;
    }
}
