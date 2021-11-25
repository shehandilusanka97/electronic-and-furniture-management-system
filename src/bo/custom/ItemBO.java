package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public boolean addItem(ItemDTO item) throws Exception;

    public boolean deleteItem(String ItemCode) throws Exception;

    public boolean updateItem(ItemDTO item) throws Exception;

    public ItemDTO searchItem(String ItemCode) throws Exception;

    public ArrayList<ItemDTO> getAllItem() throws Exception;

    public String getLastID() throws Exception;
}
