package dto;

public class EmployeeDTO {
    private String EmpId;
    private String EmpName;
    private String Address;
    private int ContactNo;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String empId, String empName, String address, int contactNo) {
        EmpId = empId;
        EmpName = empName;
        Address = address;
        ContactNo = contactNo;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
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
        return "EmployeeDTO{" +
                "EmpId='" + EmpId + '\'' +
                ", EmpName='" + EmpName + '\'' +
                ", Address='" + Address + '\'' +
                ", ContactNo=" + ContactNo +
                '}';
    }
}
