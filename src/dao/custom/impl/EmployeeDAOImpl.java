package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.EmployeeDAO;
import entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean add(Employee employee) throws Exception {
        String sql = "Insert into Employee values(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,employee.getEmpId(),employee.getEmpName(),employee.getAddress(),employee.getContactNo());

    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "Delete from Employee Where EmpId =? ";
        return CrudUtil.executeUpdate(sql,id);
    }

    @Override
    public boolean update(Employee employee) throws Exception {
        String sql = "Update Employee set EmpName=?,Address=?,ContactNo=? Where EmpId = ? ";
        return CrudUtil.executeUpdate(sql,employee.getEmpName(),employee.getAddress(),employee.getContactNo(),employee.getEmpId());
    }

    @Override
    public Employee search(String id) throws Exception {
        String sql = "Select * Form Employee Where EmpId=?";
        ResultSet rst = CrudUtil.executeQuery(sql,id);
        if (rst.next()){
            return new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getInt(4));
        }
        return null;
    }

    @Override
    public ArrayList<Employee> getAll() throws Exception {
       String sql = "Select * from Employee";
       ResultSet rst = CrudUtil.executeQuery(sql);
       ArrayList<Employee> allEmployee = new ArrayList<>();
       while (rst.next()){
           allEmployee.add(new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getInt(4)));
       }
       return allEmployee;
    }

    @Override
    public String getEmpLastId() throws SQLException, ClassNotFoundException {
        String sql = "Select EmpId from Employee ORDER BY EmpId DESC LIMIT 1 ";
        ResultSet rst =CrudUtil.executeQuery(sql);
        if (rst.next()){
            String temp = rst.getString(1);
            String[] split = temp.split("E");
            int id = Integer.valueOf(split[1]);
            id++;
            if(id<10){
                return "E00"+id;
            }else if(id<100){
                return "E0"+id;
            }else{
                return "E"+id;
            }
        }else{
            return "E001";
        }
    }
}
