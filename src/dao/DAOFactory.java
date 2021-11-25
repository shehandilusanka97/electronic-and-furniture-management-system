package dao;
import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {

    }
    public static DAOFactory getInstance() {
       return (daoFactory==null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        CUSTOMER, CUSTOMER_RETURN, EMPLOYEE, ITEM, ORDER, ORDER_DETAIL, QUERY, REPAIR, SUPPLIER , LOGIN
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case CUSTOMER_RETURN:
                return new CustomerReturnDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case REPAIR:
                return new RepairDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case QUERY:
                return  new QueryDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }

}
