package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public boolean addCustomer(CustomerDTO customer) throws Exception;

    public boolean deleteCustomer(String id) throws Exception;

    public boolean updateCustomer (CustomerDTO customer) throws Exception;

    public CustomerDTO searchCustomer(String id) throws Exception;

    public ArrayList<CustomerDTO> getAllCustomer() throws Exception;

    public String getLastID() throws Exception;
}
