package entity;

public class OrderDetail implements SuperEntity{
    private String itemCode;
    private String orderId;
    private int qty;
    private int discount;

    public OrderDetail(String string, String rstString, int anInt, double aDouble) {
    }

    public OrderDetail(String itemCode, String orderId, int qty, int discount) {
        this.itemCode = itemCode;
        this.orderId = orderId;
        this.qty = qty;
        this.discount = discount;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetailTM{" +
                "itemCode='" + itemCode + '\'' +
                ", orderId='" + orderId + '\'' +
                ", qty=" + qty +
                ", discount=" + discount +
                '}';
    }
}
