package dao.custom;

import dao.CrudDAO;
import entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order, String> {
    String getOrderId() throws SQLException, ClassNotFoundException;
}
