package dao.custom;

import dao.CrudDAO;
import entity.Supplier;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier,String> {
    String getSupLastId() throws SQLException, ClassNotFoundException;
}
