package bo.custom;

import bo.SuperBO;
import dto.RepairDTO;

import java.util.ArrayList;
import java.util.List;

public interface RepairBO extends SuperBO {
    public boolean addRepair(RepairDTO repairs) throws Exception;

    public boolean deleteRepair(String id) throws Exception;

    public boolean updateRepair(RepairDTO repairs) throws Exception;

    public RepairDTO searchRepair(String id) throws Exception;

    public ArrayList<RepairDTO> getAllRepair() throws Exception;

    public String getLastRepairID() throws Exception;

    public List<String> getAllCustomerId()throws Exception;
}
