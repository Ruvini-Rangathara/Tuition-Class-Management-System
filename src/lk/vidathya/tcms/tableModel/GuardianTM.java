package lk.vidathya.tcms.tableModel;

public class GuardianTM {
    private String guardianNic;
    private String name;
    private String contactNo;
    private String email;
    private String occupation;

    public GuardianTM() {
    }

    public GuardianTM(String guardianNic, String name, String contactNo, String email, String occupation) {
        this.guardianNic = guardianNic;
        this.name = name;
        this.contactNo = contactNo;
        this.email = email;
        this.occupation = occupation;
    }

    public String getGuardianNic() {
        return guardianNic;
    }

    public void setGuardianNic(String guardianNic) {
        this.guardianNic = guardianNic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
