package bo.custom;

import bo.SuperBO;
import dto.CustomerReturnDTO;
import entity.CustomerReturn;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerReturnBO extends SuperBO {
    public boolean addCustomerReturn(CustomerReturnDTO customerReturn) throws Exception;

    public boolean deleteCustomerReturn(String id) throws Exception;

    public boolean updateCustomerReturn(CustomerReturnDTO customerReturn) throws Exception;

    public CustomerReturnDTO searchCustomerReturn(String id) throws Exception;

    public ArrayList<CustomerReturnDTO> getAllCustomerReturn() throws Exception;

    public String getCustReturnId() throws SQLException, ClassNotFoundException;

    public List<String> getAllOrderId()throws Exception;

    public List<String> getAllItemCode()throws Exception;

}