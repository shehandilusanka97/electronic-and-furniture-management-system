package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order order) throws Exception {
        String sql = "Insert into Orders values(?,?,?)";
        return CrudUtil.executeUpdate(sql,order.getOrderId(), order.getOrderDate(), order.getcId());

    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "Delete From Orders Where OrderId=?";
        return CrudUtil.executeUpdate(sql, id);
    }

    @Override
    public boolean update(Order order) throws Exception {
        String sql = "Update Orders set CId=?,OrderDate=? Where OrderId=?";
        return CrudUtil.executeUpdate(sql,order.getcId(),order.getOrderDate(),order.getOrderId());
    }

    @Override
    public Order search(String id) throws Exception {
       String sql = "Select * From Orders Where OrderId=?";
        ResultSet rst = CrudUtil.executeQuery(sql,id);
        if (rst.next()){
            return new Order(rst.getString(1),rst.getString(2),rst.getString(3));
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws Exception {
        String sql = "Select * From Orders";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Order> allOrder = new ArrayList<>();
        while (rst.next()){
            allOrder.add(new Order(rst.getString(1),rst.getString(2),rst.getString(3)));
        }
        return allOrder;
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        String sql = "Select OrderId from Orders ORDER BY OrderId DESC LIMIT 1 ";
        ResultSet rst =CrudUtil.executeQuery(sql);
        if (rst.next()){
            String temp = rst.getString(1);
            String[] split = temp.split("O");
            int id = Integer.valueOf(split[1]);
            id++;
            if(id<10){
                return "O00"+id;
            }else if(id<100){
                return "O0"+id;
            }else{
                return "O"+id;
            }
        }else{
            return "O001";
        }
    }
}
