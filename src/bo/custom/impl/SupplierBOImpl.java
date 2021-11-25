package bo.custom.impl;

import bo.custom.SupplierBO;
import dao.DAOFactory;
import dao.custom.SupplierDAO;
import dto.SupplierDTO;
import entity.Supplier;

import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public boolean addSupplier(SupplierDTO supplier) throws Exception {
        return supplierDAO.add(new Supplier(supplier.getSId(),supplier.getSName(),supplier.getContactNo(),supplier.getEmail()));
    }

    @Override
    public boolean deleteSupplier(String id) throws Exception {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws Exception {
        return supplierDAO.update(new Supplier(supplier.getSId(),supplier.getSName(),supplier.getContactNo(),supplier.getEmail()));
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws Exception {
        Supplier search = supplierDAO.search(id);
        return new SupplierDTO(search.getSId(), search.getSName(), search.getContactNo(), search.getEmail());
    }

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws Exception {
        ArrayList<Supplier> all  = supplierDAO.getAll();
        ArrayList<SupplierDTO> allSupplier = new ArrayList<>();
        for (Supplier s: all) {
            SupplierDTO dto = new SupplierDTO(s.getSId(),s.getSName(),s.getContactNo(),s.getEmail());
            allSupplier.add(dto);

        }
        return allSupplier;
    }

    @Override
    public String getLastSupID() throws Exception {
        return supplierDAO.getSupLastId();
    }
}
