package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.CustomerReturn;

import java.sql.SQLException;

public interface CustomerReturnDAO extends CrudDAO<CustomerReturn ,String> {
    String getCustReturnId() throws SQLException, ClassNotFoundException;

}
