package bo.custom.impl;

import bo.custom.CustomerReturnBO;
import dao.DAOFactory;
import dao.custom.CustomerReturnDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dto.CustomerReturnDTO;
import entity.CustomerReturn;
import entity.Item;
import entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerReturnBOImpl implements CustomerReturnBO {
    CustomerReturnDAO customerReturnDAO = (CustomerReturnDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER_RETURN);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);


    @Override
    public boolean addCustomerReturn(CustomerReturnDTO cr) throws Exception {
        return customerReturnDAO.add(new CustomerReturn(cr.getReturnId(), cr.getOrderId(),
                cr.getItemCode(), cr.getReturnDate(), cr.getReason()));
    }

    @Override
    public boolean deleteCustomerReturn(String id) throws Exception {
        return customerReturnDAO.delete(id);
    }

    @Override
    public boolean updateCustomerReturn(CustomerReturnDTO cr) throws Exception {
        return customerReturnDAO.update(new CustomerReturn(cr.getReturnId(),cr.getOrderId(),cr.getItemCode(),cr.getReturnDate(),
                cr.getReason()));
    }

    @Override
    public CustomerReturnDTO searchCustomerReturn(String id) throws Exception {
        CustomerReturn search = customerReturnDAO.search(id);
        return new CustomerReturnDTO(search.getReturnId(),search.getOrderId(),search.getItemCode(),search.getReturnDate(),search.getReason());
    }

    @Override
    public ArrayList<CustomerReturnDTO> getAllCustomerReturn() throws Exception {
        ArrayList<CustomerReturn> all= customerReturnDAO.getAll();
        ArrayList<CustomerReturnDTO> allReturn = new ArrayList<>();
        for (CustomerReturn cr:all) {
            CustomerReturnDTO dto = new CustomerReturnDTO(cr.getReturnId(),cr.getOrderId(),cr.getItemCode(),cr.getReturnDate(),
                    cr.getReason());
            allReturn.add(dto);
        }
        return allReturn;
    }

    @Override
    public String getCustReturnId() throws SQLException, ClassNotFoundException {
        return customerReturnDAO.getCustReturnId();
    }

    @Override
    public List<String> getAllOrderId() throws Exception {
       ArrayList<Order> all = orderDAO.getAll();
       List<String> list = new ArrayList<>();
        for (Order order:all) {
            list.add(order.getOrderId());

        }
        return list;
    }

    @Override
    public List<String> getAllItemCode() throws Exception {
        ArrayList<Item> all = itemDAO.getAll();
        List<String> list = new ArrayList<>();
        for (Item item:all) {
            list.add(item.getItemCode());
        }
        return list;
    }
}
