package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.PurchaseOrderBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.TM.OrderTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    public ImageView txtCustomerId;
    public JFXTextField txtOrderId;
    public JFXButton btnAddToCart;
    public TableView<OrderTM> tblOrder;
    public TableColumn clmDescription;
    public TableColumn clmQty;
    public TableColumn clmUnitPrice;
    public TableColumn clmTotal;
    public JFXTextField txtDescription;
    public JFXComboBox cmbCustomerId;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXTextField txtQtyOnHand;
    public JFXButton btnPlaceOrder;
    public Label txtTotal;
    public AnchorPane root7;
    public TableColumn clmItemCode;
    public TableColumn clmItemDescription;
    public JFXDatePicker txtDate;
    public JFXTextField txtCustName;
    public JFXComboBox cmbItemCode;
    public JFXButton btnRemove;
    public JFXTextField txtBalance;
    public JFXButton btnBack;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField colRemove;
    public TableColumn clmRemove;
    PurchaseOrderBO bo = (PurchaseOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PURCHASE_ORDER);
    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    ObservableList<OrderTM> list = FXCollections.observableArrayList();
    private ArrayList<OrderDetailDTO> getAllTblData;
    private ArrayList<ItemDTO> getAllItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set table column

        clmItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        clmItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmRemove.setCellValueFactory(new PropertyValueFactory<>("button"));

        //set Date
        txtDate.setValue(LocalDate.now());
        try {
            loadAllCustomer();
            loadAllItemId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtOrderId.setDisable(true);

        try {
            txtOrderId.setText(String.valueOf(bo.getAllOrderId()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void loadAllCustomer() throws Exception {
        List<String> allCustomerId = bo.getAllCustomerId();
        cmbCustomerId.setItems(FXCollections.observableArrayList(allCustomerId));


    }

    private void loadAllItemId() throws Exception {
        List<String> allItemId = bo.getAllItemId();
        cmbItemCode.setItems(FXCollections.observableArrayList(allItemId));
    }


    public void Navigate(MouseEvent mouseEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/ShopKeeperForm.fxml"));
        Stage stage = (Stage) (this.root7.getScene().getWindow());
        Scene scene = new Scene(load.load());
        ShopKeeperFormController shopKeeperFormController = load.getController();
        stage.setScene(scene);
        stage.show();
    }

    public void onAction_btnAddToCart(ActionEvent actionEvent) {
        JFXButton btn = new JFXButton("Remove");
        OrderTM orderTM = new OrderTM(cmbItemCode.getValue() + "", txtDescription.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQty.getText()), btn);
        list.add(orderTM);
        tblOrder.setItems(list);
        btnOnAction(btn);

    }

    public void onAction_cmbCustomerId(ActionEvent actionEvent) {
        try {
            CustomerDTO dto = customerBO.searchCustomer(String.valueOf(cmbCustomerId.getValue()));
            txtContactNo.setText(String.valueOf(dto.getContactNo()));
            txtAddress.setText(dto.getAddress());
            txtCustName.setText(dto.getCustomerName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void btnOnAction(JFXButton btn) {
        btn.setOnAction((e) -> {
            try {
                ButtonType ok = new ButtonType("OK",
                        ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO",
                        ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are You Sure ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    int index = tblOrder.getSelectionModel().getFocusedIndex();
                    list.remove(index);
                    tblOrder.refresh();
                } else {
                    //no
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
    }

    public void onAction_btnPlaceOrder(ActionEvent actionEvent) {
        LocalDate value = txtDate.getValue();
        Date date = Date.valueOf(value);

        OrderDTO order = new OrderDTO(txtOrderId.getText(), date + "", cmbCustomerId.getValue() + "", getOrderDetails());
        try {
            boolean b = bo.placeOrder(order,getItem());
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Saved!").show();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Failed!").show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<OrderDetailDTO> getOrderDetails() {
        getAllTblData = new ArrayList<>();
        int dataFilledrowCount = clmItemCode.getTableView().getItems().size();

        for (int i = 0; i < dataFilledrowCount; i++) {

            String ItemID = clmItemCode.getCellData(i) + "";
            int qty = (int) clmQty.getCellData(i);

            OrderDetailDTO ord = new OrderDetailDTO(txtOrderId.getText(), ItemID, qty, 0);

            getAllTblData.add(ord);

        }
        return getAllTblData;
    }

    private ArrayList<ItemDTO> getItem() {
        try {
            getAllItem = new ArrayList<>();
            int dataFilledrowCount = clmItemCode.getTableView().getItems().size();

            for (int i = 0; i < dataFilledrowCount; i++) {

                String ItemID = clmItemCode.getCellData(i) + "";
                int qty = (int) clmQty.getCellData(i);

                ItemDTO dto = itemBO.searchItem(ItemID);
                int newQty=dto.getQtyOnHand()-qty;

                ItemDTO itemDTO=new ItemDTO(dto.getItemCode(),dto.getIName(),newQty,dto.getUnitPrice());
                getAllItem.add(itemDTO);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getAllItem;

    }

    public void onAction_cmbItemCode(ActionEvent actionEvent) {
        try {
            ItemDTO dto = itemBO.searchItem(cmbItemCode.getValue() + "");
            txtDescription.setText(dto.getIName());
            txtUnitPrice.setText(dto.getUnitPrice() + "");
            txtQtyOnHand.setText(dto.getQtyOnHand() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAction_btnRemove(ActionEvent actionEvent) {
    }

    private void calculateTotal() {
//        int rowCount = dtm.getRowCount();
//        double tot = 0;
//        for (int i = 0; i < rowCount; i++) {
//            tot += (Double) dtm.getValueAt(i, 4);
//        }
//        txtTotal.setText(String.valueOf(tot));
    }

    public void onAction_btnBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/ShopKeeperForm.fxml"));
        Stage stage = (Stage) (this.root7.getScene().getWindow());
        Scene scene = new Scene(load.load());
        ShopKeeperFormController shopKeeperFormController = load.getController();
        stage.setScene(scene);
        stage.show();
    }


}
