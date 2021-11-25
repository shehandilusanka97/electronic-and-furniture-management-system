package dao.custom;

import dao.CrudDAO;
import entity.Repairs;

import java.sql.SQLException;

public interface RepairDAO extends CrudDAO<Repairs,String> {

    String getLastRepairId() throws SQLException, ClassNotFoundException;


}
