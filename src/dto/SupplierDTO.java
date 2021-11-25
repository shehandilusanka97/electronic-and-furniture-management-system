package dto;

public class SupplierDTO {
    private String SId;
    private String SName;
    private int ContactNo;
    private String Email;

    public SupplierDTO() {
    }

    public SupplierDTO(String SId, String SName, int contactNo, String email) {
        this.SId = SId;
        this.SName = SName;
        ContactNo = contactNo;
        Email = email;
    }

    public String getSId() {
        return SId;
    }

    public void setSId(String SId) {
        this.SId = SId;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public int getContactNo() {
        return ContactNo;
    }

    public void setContactNo(int contactNo) {
        ContactNo = contactNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
                "SId='" + SId + '\'' +
                ", SName='" + SName + '\'' +
                ", ContactNo=" + ContactNo +
                ", Email='" + Email + '\'' +
                '}';
    }
}
