package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws Exception {
        String sql = "Insert into Customer values(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,customer.getCustomerId(), customer.getCustomerName(), customer.getAddress(), customer.getContactNo());
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "Delete from Customer Where CId = ?";
        return CrudUtil.executeUpdate(sql, id);
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        String sql = "Update Customer set CName=?,Address=?,ContactNo=? Where CId=?";
        return CrudUtil.executeUpdate(sql,customer.getCustomerName(),customer.getAddress(),customer.getContactNo(),customer.getCustomerId());

    }

    @Override
    public Customer search(String id) throws Exception {
        String sql = "Select * From Customer Where CId = ?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()) {
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4));
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        String sql = "Select * from Customer";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Customer> allCustomer = new ArrayList<>();
        while (rst.next()) {
            allCustomer.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
        }
        return allCustomer;
    }


    @Override
    public String getCustLastId() throws SQLException, ClassNotFoundException {
        String sql = "Select CId from Customer ORDER BY CId DESC LIMIT 1 ";
        ResultSet rst =CrudUtil.executeQuery(sql);
        if (rst.next()){
            String temp = rst.getString(1);
            String[] split = temp.split("C");
            int id = Integer.valueOf(split[1]);
            id++;
            if(id<10){
                return "C00"+id;
            }else if(id<100){
                return "C0"+id;
            }else{
                return "C"+id;
            }
        }else{
            return "C001";
        }
    }
}
