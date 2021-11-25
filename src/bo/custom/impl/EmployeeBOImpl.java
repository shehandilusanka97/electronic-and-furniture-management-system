package bo.custom.impl;

import bo.custom.EmployeeBO;
import dao.DAOFactory;
import dao.custom.EmployeeDAO;
import dto.EmployeeDTO;
import entity.Employee;

import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);


    @Override
    public boolean addEmployee(EmployeeDTO employee) throws Exception {
        return employeeDAO.add(new Employee(employee.getEmpId(),employee.getEmpName(), employee.getAddress(),employee.getContactNo()));
    }

    @Override
    public boolean deleteEmployee(String id) throws Exception {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee) throws Exception {
        return employeeDAO.update(new Employee(employee.getEmpId(),employee.getEmpName(),employee.getAddress(),employee.getContactNo()));
    }


    @Override
    public EmployeeDTO searchEmployee(String id) throws Exception {
        Employee search = employeeDAO.search(id);
        return new EmployeeDTO(search.getEmpId(),search.getEmpName(),search.getAddress(),search.getContactNo());
    }



    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws Exception {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDTO> allEmployee = new ArrayList<>();
        for (Employee e: all) {
            EmployeeDTO dto = new EmployeeDTO(e.getEmpId(),e.getEmpName(),e.getAddress(),e.getContactNo());
            allEmployee.add(dto);

        }
        return allEmployee;
    }

    @Override
    public String getLastEmployeeID() throws Exception {
        return employeeDAO.getEmpLastId();
    }
}
