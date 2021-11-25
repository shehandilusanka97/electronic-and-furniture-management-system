package dto;

public class CustomerDTO {
    private String CustomerId;
    private String CustomerName;
    private String Address;
    private int ContactNo;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String customerName, String address, int contactNo) {
        CustomerId = customerId;
        CustomerName = customerName;
        Address = address;
        ContactNo = contactNo;
    }



    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getContactNo() {
        return ContactNo;
    }

    public void setContactNo(int contactNo) {
        ContactNo = contactNo;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "CustomerId='" + CustomerId + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", Address='" + Address + '\'' +
                ", ContactNo=" + ContactNo +
                '}';
    }
}
