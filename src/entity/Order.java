package entity;

public class Order implements SuperEntity{
    private String orderId;
    private String orderDate;
    private String cId;

    public Order() {
    }

    public Order(String orderId, String orderDate, String cId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cId = cId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", cId='" + cId + '\'' +
                '}';
    }
}
