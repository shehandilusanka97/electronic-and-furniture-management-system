package dto;

public class CustomerReturnDTO {
    private String returnId;
    private String orderId;
    private String itemCode;
    private String returnDate;
    private String reason;

    public CustomerReturnDTO() {
    }

    public CustomerReturnDTO(String returnId, String orderId, String itemCode, String returnDate, String reason) {
        this.returnId = returnId;
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.returnDate = returnDate;
        this.reason = reason;
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "CustomerReturnDTO{" +
                "returnId='" + returnId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
