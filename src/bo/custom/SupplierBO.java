package bo.custom;

import bo.SuperBO;
import dto.SupplierDTO;

import java.util.ArrayList;

public interface SupplierBO extends SuperBO {

    public boolean addSupplier(SupplierDTO supplier) throws Exception;

    public boolean deleteSupplier(String id) throws Exception;

    public boolean updateSupplier(SupplierDTO supplier) throws Exception;

    public SupplierDTO searchSupplier(String id) throws Exception;

    public ArrayList<SupplierDTO> getAllSupplier() throws Exception;

    public String getLastSupID() throws Exception;
}
