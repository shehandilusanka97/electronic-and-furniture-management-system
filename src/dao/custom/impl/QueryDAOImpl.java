package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import entity.CustomEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
//    @Override
//    public ArrayList<CustomEntity> getOrderDetailFormOID(String oid) throws ClassNotFoundException, SQLException {
//        String sql = "Select Orders.OrderId,Orders.OrderDate,Orders.CId,OrderDetail.ItemCode,OrderDetail.Qty,OrderDetail.Discount" +
//                "from Orders inner join OrderDetail on Orders.OrderId = OrderDetail.OrderId Where Orders.OrderId=?";
//        ResultSet rst = CrudUtil.executeQuery(sql,oid);
//        ArrayList<CustomEntity> allDetails = new ArrayList<>();
//        while (rst.next()){
//            String orderId = rst.getString(1);
//            String orderDate = rst.getString(2);
//            String customerId = rst.getString(3);
//            String itemCode = rst.getString(4);
//            int qty = rst.getInt(5);
//            double discount = rst.getDouble(6);
//
//            CustomEntity t = new CustomEntity(orderId,orderDate,customerId,itemCode,qty,discount);
//            allDetails.add(t);
//
//        }
//        return allDetails;
//    }
}
