package dto;

public class RepairDTO {
    private String RepairId;
    private String CId;
    private String ItemName;
    private String Description;
    private String RepairDate;

    public RepairDTO() {
    }

    public RepairDTO(String repairId, String CId, String itemName, String description, String repairDate) {
        RepairId = repairId;
        this.CId = CId;
        ItemName = itemName;
        Description = description;
        RepairDate = repairDate;
    }

    public String getRepairId() {
        return RepairId;
    }

    public void setRepairId(String repairId) {
        RepairId = repairId;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String CId) {
        this.CId = CId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getRepairDate() {
        return RepairDate;
    }

    public void setRepairDate(String repairDate) {
        RepairDate = repairDate;
    }

    @Override
    public String toString() {
        return "RepairDTO{" +
                "RepairId='" + RepairId + '\'' +
                ", CId='" + CId + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", Description='" + Description + '\'' +
                ", RepairDate='" + RepairDate + '\'' +
                '}';
    }
}
