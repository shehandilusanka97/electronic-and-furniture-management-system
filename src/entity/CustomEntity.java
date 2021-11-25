package entity;

import java.util.ArrayList;

public class CustomEntity implements SuperEntity{
    private String OrderId;
    private String OrderDate;
    private String CId;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public CustomEntity() {
    }

    public CustomEntity(String orderId, String orderDate, String CId, ArrayList<Customer> customers, ArrayList<Item> items) {
        OrderId = orderId;
        OrderDate = orderDate;
        this.CId = CId;
        this.customers = customers;
        this.items = items;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String CId) {
        this.CId = CId;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "OrderId='" + OrderId + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                ", CId='" + CId + '\'' +
                ", customers=" + customers +
                ", items=" + items +
                '}';
    }
}
