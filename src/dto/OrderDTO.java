package dto;

import java.util.ArrayList;

public class OrderDTO {
    private String orderId;
    private String orderDate;
    private String cId;
    private ArrayList<OrderDetailDTO> allOrderDetail;

    public OrderDTO() {

    }

    public OrderDTO(String orderId, String orderDate, String cId, ArrayList<OrderDetailDTO> allOrderDetail) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cId = cId;
        this.allOrderDetail = allOrderDetail;
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

    public ArrayList<OrderDetailDTO> getAllOrderDetail() {
        return allOrderDetail;
    }

    public void setAllOrderDetail(ArrayList<OrderDetailDTO> allOrderDetail) {
        this.allOrderDetail = allOrderDetail;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", cId='" + cId + '\'' +
                ", allOrderDetail=" + allOrderDetail +
                '}';
    }
}
