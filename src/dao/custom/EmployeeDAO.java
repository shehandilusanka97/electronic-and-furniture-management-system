package dao.custom;

import dao.CrudDAO;
import entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    String getEmpLastId() throws SQLException, ClassNotFoundException;
}
