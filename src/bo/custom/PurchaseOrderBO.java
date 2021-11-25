package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import dto.OrderDTO;

import java.util.List;

public interface PurchaseOrderBO extends SuperBO {

   /* public ArrayList<CustomerDTO> getAllCustomer() throws Exception;

    public ArrayList<ItemDTO> getAllItem() throws Exception;

    public CustomerDTO searchCustomer(String id) throws Exception;

    public ItemDTO searchItem(String id) throws Exception;*/

    public boolean placeOrder(OrderDTO dto,List<ItemDTO> list) throws Exception;

    public List<String> getAllCustomerId() throws Exception;

    public List<String> getAllItemId()throws Exception;

    public List<String> getAllOrderId()throws Exception;



}
