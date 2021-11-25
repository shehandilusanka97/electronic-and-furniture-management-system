package dao.custom;

import dao.CrudDAO;
import entity.Login;

public interface LoginDAO extends CrudDAO<Login,String> {
    public String getRoll(String password,String userName) throws Exception;
}
