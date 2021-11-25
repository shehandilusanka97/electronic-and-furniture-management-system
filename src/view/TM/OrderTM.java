package view.TM;

import com.jfoenix.controls.JFXButton;

public class OrderTM {
    private String ItemCode;
    private String Description;
    private double UnitPrice;
    private int Qty;
    private JFXButton button;

    public OrderTM(String itemCode, String description, double unitPrice, int qty, JFXButton button) {
        ItemCode = itemCode;
        Description = description;
        UnitPrice = unitPrice;
        Qty = qty;
        this.button = button;
    }

    public OrderTM() {
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public JFXButton getButton() {
        return button;
    }

    public void setButton(JFXButton button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "ItemCode='" + ItemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", Qty=" + Qty +
                ", button=" + button +
                '}';
    }
}
