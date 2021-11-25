package controller;

import bo.BOFactory;
import bo.custom.CustomerReturnBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerReturnDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerReturnFormController implements Initializable {

    public JFXTextField txtReturnId;
    public JFXTextField txtReason;
    public JFXDatePicker dpReturnDate;
    public JFXComboBox cmboOrderId;
    public JFXComboBox cmboItemCode;
    public JFXButton btnSave;
    public JFXButton btnAddNewRepair;
    public JFXButton btnDelete;
    public JFXButton btnBack;
    public JFXButton btnPrint;
    public TableView<CustomerReturnDTO> tblCustomerReturn;
    public TableColumn clmReturnId;
    public TableColumn clmOrderId;
    public TableColumn clmItemCode;
    public TableColumn clmReturnDate;
    public TableColumn clmReason;
    public AnchorPane root12;
    CustomerReturnBO bo = (CustomerReturnBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER_RETURN);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblCustomerReturn.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ReturnId"));
        tblCustomerReturn.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        tblCustomerReturn.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        tblCustomerReturn.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
        tblCustomerReturn.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Reason"));

        getAllReturn();
        getAllOrderId();
        getAllItemCode();

        txtReturnId.setDisable(true);
        txtReason.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        try {
            txtReturnId.setText(bo.getCustReturnId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void getAllReturn(){
        try {
        ArrayList<CustomerReturnDTO> allReturns = bo.getAllCustomerReturn();
        tblCustomerReturn.setItems(FXCollections.observableArrayList(allReturns));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllOrderId(){
        try {
            List<String>  allOrderId = bo.getAllOrderId();
            cmboOrderId.setItems(FXCollections.observableArrayList(allOrderId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllItemCode(){
        try {
            List<String> allItemCode = bo.getAllItemCode();
            cmboItemCode.setItems(FXCollections.observableArrayList(allItemCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAction_btnSave(ActionEvent actionEvent) throws Exception {
        if (btnSave.getText().equals("Save")) {
            CustomerReturnDTO dto = new CustomerReturnDTO(
                    txtReturnId.getText(),
                    cmboOrderId.getSelectionModel().getSelectedItem().toString(),
                    cmboItemCode.getSelectionModel().getSelectedItem().toString(),
                    dpReturnDate.getValue().toString(),
                    txtReason.getText()
            );
            boolean b = bo.addCustomerReturn(dto);

        } else {
            CustomerReturnDTO dto = new CustomerReturnDTO(
                    txtReturnId.getText(),
                    cmboOrderId.getSelectionModel().getSelectedItem().toString(),
                    cmboItemCode.getSelectionModel().getSelectedItem().toString(),
                    dpReturnDate.getValue().toString(),
                    txtReason.getText()
            );
            boolean b =  bo.addCustomerReturn(dto);
        }
        getAllReturn();
    }

    public void onAction_btnAddNewRepair(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtReturnId.setText(bo.getCustReturnId());
        txtReason.clear();
        tblCustomerReturn.getSelectionModel().clearSelection();
        txtReason.setDisable(false);
        btnSave.setDisable(false);
    }

    public void onAction_btnDelete(ActionEvent actionEvent) throws Exception {
        bo.deleteCustomerReturn(txtReturnId.getText());
        getAllReturn();

    }

    public void onAction_btnBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/AdminForm.fxml"));
        Stage stage = (Stage) (this.root12.getScene().getWindow());
        Scene scene = new Scene(load.load());
        AdminFormController adminFormController = load.getController();
        stage.setScene(scene);
        stage.show();
    }

    public void onAction_btnPrint(ActionEvent actionEvent) {
    }


    public void tblReturn_OnMouseClicked(MouseEvent mouseEvent) {
        CustomerReturnDTO selectedItem = tblCustomerReturn.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            btnSave.setText("Save");
            btnDelete.setDisable(true);
            return;
        }
        btnSave.setText("Update");
        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        txtReason.setDisable(false);

        txtReturnId.setText(selectedItem.getReturnId());
        dpReturnDate.setValue(LocalDate.parse(selectedItem.getReturnDate(),DateTimeFormatter.ISO_DATE));
        txtReason.setText(selectedItem.getReason());

    }
}
