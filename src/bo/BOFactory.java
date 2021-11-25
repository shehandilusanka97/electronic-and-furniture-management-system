package bo;

import bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;

    }

    public enum BOTypes {
        CUSTOMER,CUSTOMER_RETURN, ITEM, EMPLOYEE, PURCHASE_ORDER, REPAIRS, SUPPLIER;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case CUSTOMER_RETURN:
                return new CustomerReturnBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case PURCHASE_ORDER:
                return new PurchaseOrderBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case REPAIRS:
                return new RepairBOImpl();
            default:
                return null;

        }

    }

}
