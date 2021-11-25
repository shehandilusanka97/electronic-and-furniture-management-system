package bo.custom;

import bo.SuperBO;
import dto.EmployeeDTO;

import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public boolean addEmployee(EmployeeDTO employee) throws Exception;

    public boolean deleteEmployee(String id) throws Exception;

    public boolean updateEmployee(EmployeeDTO employee) throws Exception;

    public EmployeeDTO searchEmployee(String id) throws Exception;

    public ArrayList<EmployeeDTO> getAllEmployee() throws Exception;

    public String getLastEmployeeID() throws Exception;
}
