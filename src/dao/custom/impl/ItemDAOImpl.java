package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {


    @Override
    public boolean add(Item item) throws Exception {
        String sql = "Insert into Item Values(?,?,?,?)";
        return CrudUtil.executeUpdate(sql, item.getItemCode(), item.getIName(), item.getQtyOnHand(), item.getUnitPrice());
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "Delete From Item Where ItemCode=?";
        return CrudUtil.executeUpdate(sql, id);
    }

    public boolean update(List<Item> list) throws Exception {
        if (list != null) {
            for (Item item: list) {
                if (!update(item)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Item item) throws Exception {
        String sql = "Update Item Set IName=?,QtyOnHand=?,UnitPrice=? Where ItemCode=?";
        return CrudUtil.executeUpdate(sql,item.getIName(), item.getQtyOnHand(), item.getUnitPrice(),item.getItemCode());
    }

    @Override
    public Item search(String id) throws Exception {
        String sql = "Select * From Item Where ItemCode=?";
        ResultSet rs = CrudUtil.executeQuery(sql, id);
        if (rs.next()) {
            return new Item(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        String sql = "Select * From Item";
        ResultSet rs = CrudUtil.executeQuery(sql);
        ArrayList<Item> allItem = new ArrayList<>();
        while (rs.next()) {
            allItem.add(new Item(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
        }
        return allItem;
    }

    @Override
    public String getItemId() throws SQLException, ClassNotFoundException {
        String sql = "Select ItemCode from Item ORDER BY ItemCode DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            String temp = rst.getString(1);
            String[] split = temp.split("I");
            int id = Integer.valueOf(split[1]);
            id++;
            if(id<10){
                return "I00"+id;
            }else if(id<100){
                return "I0"+id;
            }else{
                return "I"+id;
            }

        }else {
            return "I001";
        }
    }
}
