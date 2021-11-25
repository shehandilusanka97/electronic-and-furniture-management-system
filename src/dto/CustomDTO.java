package dto;

import java.util.ArrayList;

public class CustomDTO {
    private String orderId;
    private String orderDate;
    private String cId;
    ArrayList<CustomDTO> customerDTO = new ArrayList<>();
    ArrayList<ItemDTO> itemDTO = new ArrayList<>();

    public CustomDTO() {
    }

    public CustomDTO(String orderId, String orderDate, String cId, ArrayList<CustomDTO> customerDTO, ArrayList<ItemDTO> itemDTO) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cId = cId;
        this.customerDTO = customerDTO;
        this.itemDTO = itemDTO;
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

    public ArrayList<CustomDTO> getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(ArrayList<CustomDTO> customerDTO) {
        this.customerDTO = customerDTO;
    }

    public ArrayList<ItemDTO> getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ArrayList<ItemDTO> itemDTO) {
        this.itemDTO = itemDTO;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", cId='" + cId + '\'' +
                ", customerDTO=" + customerDTO +
                ", itemDTO=" + itemDTO +
                '}';
    }
}
