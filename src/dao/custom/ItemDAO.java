package dao.custom;

import dao.CrudDAO;
import entity.Item;

import java.sql.SQLException;
import java.util.List;


public interface ItemDAO extends CrudDAO<Item,String> {
    String getItemId()throws SQLException, ClassNotFoundException;
    public boolean update(List<Item> list) throws Exception;

}
