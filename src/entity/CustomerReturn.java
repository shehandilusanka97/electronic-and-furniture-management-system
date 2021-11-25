package entity;

import dto.CustomerReturnDTO;

public class CustomerReturn extends CustomerReturnDTO implements SuperEntity {
    private String ReturnId;
    private String OrderId;
    private String ItemCode;
    private String ReturnDate;
    private String Reason;

    public CustomerReturn() {
    }

    public CustomerReturn(String returnId, String orderId, String itemCode, String returnDate, String reason) {
        ReturnId = returnId;
        OrderId = orderId;
        ItemCode = itemCode;
        ReturnDate = returnDate;
        Reason = reason;
    }

    public String getReturnId() {
        return ReturnId;
    }

    public void setReturnId(String returnId) {
        ReturnId = returnId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    @Override
    public String toString() {
        return "CustomerReturn{" +
                "ReturnId='" + ReturnId + '\'' +
                ", OrderId='" + OrderId + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", ReturnDate='" + ReturnDate + '\'' +
                ", Reason='" + Reason + '\'' +
                '}';
    }
}
