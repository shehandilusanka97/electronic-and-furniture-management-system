package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.RepairDAO;
import entity.Repairs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairDAOImpl implements RepairDAO {
    @Override
    public boolean add(Repairs repairs) throws Exception {
        String sql = "Insert into Repairs Values(?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, repairs.getRepairId(),repairs.getCId(), repairs.getItemName(), repairs.getDescription(), repairs.getRepairDate());
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "Delete From Repairs Where RepairId=?";
        return CrudUtil.executeUpdate(sql, id);
    }

    @Override
    public boolean update(Repairs repairs) throws Exception {
        String sql = "Update Repairs set CId=?,ItemName=?,Description=?,RepairDate=? Where RepairId=?";
        return CrudUtil.executeUpdate(sql, repairs.getCId(), repairs.getItemName(), repairs.getDescription(), repairs.getRepairDate(),repairs.getRepairId());
    }

    @Override
    public Repairs search(String id) throws Exception {
        String sql = "Select*From Repairs Where RepairId =?";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return new Repairs(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }

    @Override
    public ArrayList<Repairs> getAll() throws Exception {
        String sql = "Select * From Repairs";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Repairs> allRepairs = new ArrayList<>();
        while (rst.next()) {
            allRepairs.add(new Repairs(rst.getString(1),
                    rst.getString(2), rst.getString(3),
                    rst.getString(4), rst.getString(5)));

        }
        return allRepairs;
    }

    @Override
    public String getLastRepairId() throws SQLException, ClassNotFoundException {
        String sql = "Select RepairId from Repairs ORDER BY RepairId DESC LIMIT 1 ";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            String temp = rst.getString(1);
            String[] split = temp.split("R");
            int id = Integer.valueOf(split[1]);
            id++;
            if (id < 10) {
                return "R00" + id;
            } else if (id < 100) {
                return "R0" + id;
            } else {
                return "R" + id;
            }
        } else {
            return "R001";
        }
    }


}

