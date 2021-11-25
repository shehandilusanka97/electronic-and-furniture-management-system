package bo.custom.impl;

import bo.custom.RepairBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.OrderDAO;
import dao.custom.RepairDAO;
import dto.RepairDTO;
import entity.Customer;
import entity.Repairs;


import java.util.ArrayList;
import java.util.List;

public class RepairBOImpl implements RepairBO {
    RepairDAO repairDAO = (RepairDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REPAIR);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);




    @Override
    public boolean addRepair(RepairDTO repairs) throws Exception {
        return repairDAO.add(new Repairs(repairs.getRepairId(), repairs.getCId(), repairs.getItemName(), repairs.getDescription(), repairs.getRepairDate()));
    }

    @Override
    public boolean deleteRepair(String id) throws Exception {
        return repairDAO.delete(id);
    }

    @Override
    public boolean updateRepair(RepairDTO repairs) throws Exception {
        return repairDAO.update(new Repairs(repairs.getRepairId(), repairs.getCId(), repairs.getItemName(), repairs.getDescription(), repairs.getRepairDate()));
    }

    @Override
    public RepairDTO searchRepair(String id) throws Exception {
        Repairs search = repairDAO.search(id);
        return new RepairDTO(search.getRepairId(),search.getCId(),search.getItemName(),search.getDescription(),search.getRepairDate());
    }

    @Override
    public ArrayList<RepairDTO> getAllRepair() throws Exception {
        ArrayList<Repairs> all= repairDAO.getAll();
        ArrayList<RepairDTO> allRepair = new ArrayList<>();
        for (Repairs r:all) {
            RepairDTO dto = new RepairDTO(r.getRepairId(),r.getCId(),r.getItemName(),r.getDescription(),r.getRepairDate());
            allRepair.add(dto);

        }
        return allRepair;
    }

    @Override
    public String getLastRepairID() throws Exception {
       return repairDAO.getLastRepairId();
    }

    @Override
    public List<String> getAllCustomerId() throws Exception {
        ArrayList<Customer> all = customerDAO.getAll();
        List<String> list = new ArrayList<>();
        for (Customer customer : all) {
            list.add(customer.getCustomerId());

        }
        return list;
    }


}
