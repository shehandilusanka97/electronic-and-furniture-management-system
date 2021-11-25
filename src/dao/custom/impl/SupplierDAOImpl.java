package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.SupplierDAO;
import entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean add(Supplier supplier) throws Exception {
        String sql = "Insert Into Supplier Values(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,supplier.getSId(),supplier.getSName(),supplier.getContactNo(),supplier.getEmail());
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "Delete from Supplier Where SId=?";
        return CrudUtil.executeUpdate(sql,id);
    }

    @Override
    public boolean update(Supplier supplier) throws Exception {
       String sql = "Update Supplier set SName=?,ContactNo=?,Email=? Where SId=?";
       return CrudUtil.executeUpdate(sql,supplier.getSId(),supplier.getSName(),supplier.getContactNo(),supplier.getEmail());
    }

    @Override
    public Supplier search(String id) throws Exception {
        String sql = "Select * From Supplier Where SId=?";
        ResultSet rst = CrudUtil.executeQuery(sql,id);
        if (rst.next()){
            return new Supplier(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getString(4));
        }
        return null;
    }

    @Override
    public ArrayList<Supplier> getAll() throws Exception {
      String sql = "Select * From Supplier";
      ResultSet rst = CrudUtil.executeQuery(sql);
      ArrayList<Supplier> allSupplier = new ArrayList<>();
      while (rst.next()){
          allSupplier.add(new Supplier(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getString(4)));
      }
      return allSupplier;
    }

    @Override
    public String getSupLastId() throws SQLException, ClassNotFoundException {
        String sql = "Select SId from Supplier ORDER BY SId DESC LIMIT 1 ";
        ResultSet rst =CrudUtil.executeQuery(sql);
        if (rst.next()){
            String temp = rst.getString(1);
            String[] split = temp.split("S");
            int id = Integer.valueOf(split[1]);
            id++;
            if(id<10){
                return "S00"+id;
            }else if(id<100){
                return "S0"+id;
            }else{
                return "S"+id;
            }
        }else{
            return "S001";
        }
    }
}
