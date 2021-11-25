package bo.custom.impl;

import bo.custom.LoginBO;
import dao.DAOFactory;
import dao.custom.LoginDAO;
import dto.LoginDTO;
import entity.Customer;
import entity.Login;

import java.util.ArrayList;
import java.util.List;

public class LoginBOImpl implements LoginBO {

    LoginDAO loginDAO = (LoginDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LOGIN);
    @Override
    public boolean saveLogin(LoginDTO dto) throws Exception {
        return loginDAO.add(new Login(dto.getUserName(),dto.getPassword(),dto.getRoll()));
    }

    @Override
    public boolean updateLogin(LoginDTO dto) throws Exception {
        return loginDAO.update(new Login(dto.getUserName(),dto.getPassword(),dto.getRoll()));
    }

    @Override
    public boolean deleteLogin(String id) throws Exception {
        return loginDAO.delete(id);
    }

    @Override
    public LoginDTO getLogin(String id) throws Exception {
        Login login = loginDAO.search(id);
        return new LoginDTO(login.getUserName(),login.getPassword(),login.getRoll());
    }

    @Override
    public ArrayList<LoginDTO> getAllLogin() throws Exception {
        List<Login> all = loginDAO.getAll();
        ArrayList<LoginDTO> allLogin = new ArrayList<>();
        for (Login login:all) {
            allLogin.add(new LoginDTO(login.getUserName(),login.getPassword(),login.getRoll()));
        }
        return allLogin;
    }

    @Override
    public String getRoll(String password, String userName) throws Exception {
        return loginDAO.getRoll(password,userName);
    }
}
