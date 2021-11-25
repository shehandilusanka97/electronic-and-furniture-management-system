package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.LoginDAO;
import entity.Customer;
import entity.Login;

import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean add(Login login) throws Exception {
        String sql = "Insert Into Login Values(?,?,?)";
        return CrudUtil.executeUpdate(sql,login.getUserName(),login.getPassword(),login.getRoll());
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "Delete from Login Where Password=?";
        return CrudUtil.executeUpdate(id);
    }

    @Override
    public boolean update(Login login) throws Exception {
        String sql = "Update Login set UserName=?,Roll=? Where Password=?";
        return CrudUtil.executeUpdate(sql,login.getUserName(),login.getRoll(),login.getPassword());
    }

    @Override
    public Login search(String id) throws Exception {
        String sql = "Select * From Login Where Password=?";
        ResultSet rs = CrudUtil.executeQuery(sql,id);
        if (rs.next()){
            return new Login(rs.getString(1),rs.getString(2),rs.getString(3));
        }
        return null;
    }

    @Override
    public ArrayList<Login> getAll() throws Exception {
        String sql = "Select * from Login";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Login> allLogin = new ArrayList<>();
        while (rst.next()) {
            allLogin.add(new Login(rst.getString(1),rst.getString(2),rst.getString(3)   ));
        }
        return allLogin;
    }
    @Override
    public String getRoll(String password, String userName) throws Exception {
        String sql = "SELECT Roll FROM Login WHERE Password=? and UserName=?";
        ResultSet rst = CrudUtil.executeQuery(sql,password,userName);
        String roll=null;
        if (rst.next()){
            roll=rst.getString(1);
        }
        return roll;
    }

}
