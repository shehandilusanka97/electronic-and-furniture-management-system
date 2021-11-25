package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.add(new Customer(customer.getCustomerId(),customer.getCustomerName(),customer.getAddress(),customer.getContactNo()));
    }
    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }
    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.update(new Customer(customer.getCustomerId(),customer.getCustomerName(),customer.getAddress(),customer.getContactNo()));
    }
    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Customer search = customerDAO.search(id);
        return new CustomerDTO(search.getCustomerId(),search.getCustomerName(),search.getAddress(),search.getContactNo());
    }
    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException, Exception {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
        for (Customer c:all) {
            CustomerDTO dto = new CustomerDTO(c.getCustomerId(), c.getCustomerName(), c.getAddress(), c.getContactNo()
            );
            allCustomer.add(dto);
        }
        return allCustomer;

    }

    @Override
    public String getLastID() throws Exception {
        return customerDAO.getCustLastId();
    }

}

