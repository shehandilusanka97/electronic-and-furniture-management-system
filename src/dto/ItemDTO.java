package dto;

public class ItemDTO {
    private String ItemCode;
    private String IName;
    private int QtyOnHand;
    private double UnitPrice;

    public ItemDTO(String itemCode, String IName,String name, int qtyOnHand, double unitPrice) {
    }

    public ItemDTO(String itemCode, String IName, int qtyOnHand, double unitPrice) {
        ItemCode = itemCode;
        this.IName = IName;
        QtyOnHand = qtyOnHand;
        UnitPrice = unitPrice;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getIName() {
        return IName;
    }

    public void setIName(String IName) {
        this.IName = IName;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "ItemCode='" + ItemCode + '\'' +
                ", IName='" + IName + '\'' +
                ", QtyOnHand=" + QtyOnHand +
                ", UnitPrice=" + UnitPrice +
                '}';
    }
}
