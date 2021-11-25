package bo.custom;

import dto.LoginDTO;

import java.util.ArrayList;

public interface LoginBO {
    public boolean saveLogin(LoginDTO dto)throws Exception;
    public boolean updateLogin(LoginDTO dto)throws Exception;
    public boolean deleteLogin(String id) throws Exception;
    public LoginDTO getLogin(String id) throws Exception;
    public ArrayList<LoginDTO> getAllLogin() throws Exception;
    public String getRoll(String password, String userName) throws Exception;
}
