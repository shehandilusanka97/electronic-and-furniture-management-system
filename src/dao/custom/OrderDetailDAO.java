package dao.custom;

import dao.CrudDAO;
import entity.OrderDetail;

import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail,String> {
    public boolean add(List<OrderDetail> t) throws Exception;

}
