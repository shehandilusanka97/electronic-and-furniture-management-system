package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean addItem(ItemDTO item) throws Exception {
        return itemDAO.add(new Item(item.getItemCode(),item.getIName(),item.getQtyOnHand(),item.getUnitPrice()));
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDAO.delete(id);
    }

    @Override
    public boolean updateItem(ItemDTO item) throws Exception {
        return itemDAO.update(new Item(item.getIName(),item.getQtyOnHand(),item.getUnitPrice(),item.getItemCode()));
    }

    @Override
    public ItemDTO searchItem(String id) throws Exception {
        Item search = itemDAO.search(id);
        return new ItemDTO(search.getItemCode(),search.getIName(),search.getQtyOnHand(),search.getUnitPrice());
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException, Exception {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItem = new ArrayList<>();
        for (Item i:all) {
            ItemDTO dto= new ItemDTO(i.getItemCode(),i.getIName(),i.getQtyOnHand(),i.getUnitPrice());
            allItem.add(dto);
        }
        return allItem;

    }

    @Override
    public String getLastID() throws Exception {
        return itemDAO.getItemId();
    }

}
