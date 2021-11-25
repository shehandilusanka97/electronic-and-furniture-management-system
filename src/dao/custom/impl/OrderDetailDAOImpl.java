package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;

import entity.OrderDetail;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean add(List<OrderDetail> t) throws Exception {
        if (t != null) {
            for (OrderDetail orderDetails : t) {
                if (!add(orderDetails)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean add(OrderDetail orderDetail) throws Exception {
        String sql = "Insert Into OrderDetail values(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,orderDetail.getOrderId(),orderDetail.getItemCode(),orderDetail.getQty(),orderDetail.getDiscount());
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "Delete from OrderDetail Where OrderId=?";
        return CrudUtil.executeUpdate(sql,id);
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws Exception {
       String sql = "Update OrderDetail set ItemCode=?,Qty=?,Discount=? Where OrderId =?";
       return CrudUtil.executeUpdate(sql, orderDetail.getItemCode(),orderDetail.getQty(),orderDetail.getDiscount(),orderDetail.getOrderId());
    }

    @Override
    public OrderDetail search(String id) throws Exception {
       String sql = "Select * From OrderDetail Where OrderId=?";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()){
            return new OrderDetail(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws Exception {
        String sql = "Select * From OrderDetail";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<OrderDetail> allOrderDetail = new ArrayList<>();
        while (rst.next()){
            allOrderDetail.add(new OrderDetail(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4)));
        }
        return allOrderDetail;
    }


}
