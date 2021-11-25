package bo.custom.impl;

import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import db.DBConnection;
import dto.*;
import entity.Customer;
import entity.Item;
import entity.Order;
import entity.OrderDetail;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    CustomerDAO  customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_DETAIL);

    @Override
    public boolean placeOrder(OrderDTO dto,List<ItemDTO> list) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Order order = new Order(dto.getOrderId(),dto.getOrderDate(),dto.getcId());
        List<OrderDetail> orderDetailsList = new ArrayList<>();
        for (OrderDetailDTO orderDetailsDTO : dto.getAllOrderDetail()) {
            orderDetailsList.add(new OrderDetail(orderDetailsDTO.getItemCode(),orderDetailsDTO.getOrderId(),orderDetailsDTO.getQty(),orderDetailsDTO.getDiscount()));

        }
        List<Item> itemList = new ArrayList<>();
        for (ItemDTO itemDTO : list) {
            itemList.add(new Item(itemDTO.getItemCode(),itemDTO.getIName(),itemDTO.getQtyOnHand(), itemDTO.getUnitPrice()));

        }

        boolean add = orderDAO.add(order);
        try {
            if (add){

                boolean response = orderDetailDAO.add(orderDetailsList);
                    if (response){
                        boolean updateItem = itemDAO.update(itemList);
                        if(updateItem) {
                            connection.commit();
                            return true;
                        }
                    }

            }else{
                connection.rollback();
                return false;
            }

        }finally {
           connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public List<String> getAllCustomerId() throws Exception {
        ArrayList<Customer> all = customerDAO.getAll();
        List<String> list = new ArrayList<>();
        for (Customer customer : all) {
            list.add(customer.getCustomerId());
        }
        return list;
    }

    @Override
    public List<String> getAllItemId() throws Exception {
        ArrayList<Item> all = itemDAO.getAll();
        List<String> list = new ArrayList<>();
        for (Item item: all) {
            list.add(item.getItemCode());

        }
        return list;
    }

    @Override
    public List<String> getAllOrderId() throws Exception {
        ArrayList<Order> all = orderDAO.getAll();
        List<String> list = new ArrayList<>();
        for (Order order: all) {
            list.add(order.getOrderId());

        }
        return list;
    }
}
