package controller;
import bo.BOFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import entity.Item;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    public ObservableList<Item> itemList;
    ItemBO bo = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    Connection conn;
    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtQtyOnHand;
    public TableColumn clmItemCode;
    public TableColumn clmItemName;
    public TableColumn clmQty;
    public TableColumn clmUnitPrice;
    public JFXButton btnBack;
    public JFXTextField txtEid;
    public JFXTextField txtEName;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<ItemDTO> tblElectronic;
    public JFXTextField txtQty;
    public JFXTextField txtPrice;
    public AnchorPane root4;
    public JFXButton btnAddNewItem;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtItemCode.setDisable(true);
        txtItemName.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtPrice.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        tblElectronic.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemDTO>() {
            @Override
            public void changed(ObservableValue<? extends ItemDTO> observable, ItemDTO oldValue, ItemDTO newValue) {
                ItemDTO selectedItem = tblElectronic.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    return;
                }
                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtItemName.setDisable(false);
                txtQtyOnHand.setDisable(false);
                txtPrice.setDisable(false);
                txtItemCode.setText(selectedItem.getItemCode());
                txtItemName.setText(selectedItem.getIName());
                txtQtyOnHand.setText(String.valueOf(selectedItem.getQtyOnHand()));
                txtPrice.setText(String.valueOf(selectedItem.getUnitPrice()));
            }
        });

        tblElectronic.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        tblElectronic.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("IName"));
        tblElectronic.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        tblElectronic.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        loadAllItem();
        try {
            txtItemCode.setText(bo.getLastID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllItem() {
        try {
            ArrayList<ItemDTO> allItem = bo.getAllItem();
            tblElectronic.setItems(FXCollections.observableArrayList(allItem));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAction_btnSave(ActionEvent actionEvent) throws Exception {
        if (btnSave.getText().equals("Save")) {
            ItemDTO dto = new ItemDTO(
                    bo.getLastID(),
                    txtItemName.getText(),
                    Integer.valueOf(txtQtyOnHand.getText()),
                    Double.valueOf(txtPrice.getText()));

            boolean b = bo.addItem(dto);
            txtSetDisable();
        } else {
            ItemDTO dto = new ItemDTO(
                    txtItemCode.getText(),
                    txtItemName.getText(),
                    Integer.valueOf(txtQtyOnHand.getText()),
                    Double.valueOf(txtPrice.getText()));

            boolean b = bo.updateItem(dto);
            txtSetDisable();
        }
       loadAllItem();
    }

    private void txtSetDisable() {
        txtItemName.setText("");
        txtQtyOnHand.setText("");
        txtPrice.setText("");
    }

    public void onAction_btnDelete(ActionEvent actionEvent) throws Exception {
        bo.deleteItem(txtItemCode.getText());
        loadAllItem();
        txtSetDisable();
    }

    public void onAction_btnAddNewItem(ActionEvent actionEvent) {
        try {
            txtItemCode.setText(bo.getLastID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtItemName.clear();
        txtQtyOnHand.clear();
        txtPrice.clear();
        tblElectronic.getSelectionModel().clearSelection();
        txtItemName.setDisable(false);
        txtItemName.requestFocus();
        txtQtyOnHand.setDisable(false);
        txtPrice.setDisable(false);
        btnSave.setDisable(false);
    }

    public void onAction_btnBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/ShopKeeperForm.fxml"));
        Stage stage = (Stage) (this.root4.getScene().getWindow());
        Scene scene = new Scene(load.load());
        ShopKeeperFormController shopKeeperFormController = load.getController();
        stage.setScene(scene);
        stage.show();

    }
}
