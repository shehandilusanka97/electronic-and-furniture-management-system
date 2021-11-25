package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerReturnDAO;
import entity.CustomerReturn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerReturnDAOImpl implements CustomerReturnDAO {

    @Override
    public boolean add(CustomerReturn customerReturn) throws Exception {
        String sql = "Insert Into CustomerReturn Values(?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,customerReturn.getItemCode(),customerReturn.getOrderId(),
                customerReturn.getItemCode(),customerReturn.getReturnDate(),customerReturn.getReason());
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "Delete from CustomerReturn Where ReturnId=?";
        return CrudUtil.executeUpdate(sql,id);
    }

    @Override
    public boolean update(CustomerReturn customerReturn) throws Exception {
        String sql = "Update CustomerReturn set OrderId=?,ItemCode=?,ReturnDate=?,Reason=? where ReturnId=?";
        return CrudUtil.executeUpdate(sql,customerReturn.getOrderId(),customerReturn.getItemCode(),customerReturn.getReturnDate(),
                customerReturn.getReason(),customerReturn.getReturnId());
    }

    @Override
    public CustomerReturn search(String id) throws Exception {
        String sql = "Select * From CustomerReturn Where ReturnId=?";
        ResultSet rs = CrudUtil.executeQuery(sql,id);
        if (rs.next()){
            return new CustomerReturn(rs.getString(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5));
        }
        return null;
    }

    @Override
    public ArrayList<CustomerReturn> getAll() throws Exception {
        String sql = "Select * From CustomerReturn";
        ArrayList<CustomerReturn>allCustomerReturn = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery(sql);
        while (rs.next()){
            allCustomerReturn.add(new CustomerReturn(rs.getString(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5)));
        }
        return allCustomerReturn;
    }

    @Override
    public String getCustReturnId() throws SQLException, ClassNotFoundException {
        String sql = "Select ReturnId from CustomerReturn ORDER BY ReturnId DESC LIMIT 1 ";
        ResultSet rst =CrudUtil.executeQuery(sql);
        if (rst.next()){
            String temp = rst.getString(1);
            String[] split = temp.split("R");
            int id = Integer.valueOf(split[1]);
            id++;
            if(id<10){
                return "R00"+id;
            }else if(id<100){
                return "R0"+id;
            }else{
                return "R"+id;
            }
        }else{
            return "R001";
        }
    }
}
