package controller;


import bo.BOFactory;
import bo.custom.RepairBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.RepairDTO;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RepairFormController implements Initializable {
    public JFXButton btnBack;
    public JFXTextField txtRepairId;
    public JFXButton btnSave;
    public TableView<RepairDTO> tblRepair;
    public JFXButton btnDelete;
    public TableColumn clmRepairId;
    public AnchorPane root3;
    public JFXButton btnAddNewRepair;
    public JFXTextField txtItemName;
    public TableColumn clmItemName;
    public TableColumn clmDate;
    public JFXTextField txtDescription;
    public TableColumn clmDescription;
    public TableColumn clmCId;
    public JFXDatePicker DPRepairDate;
    public JFXComboBox cmbCId;
    public JFXButton btnPrint;
    RepairBO bo = (RepairBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REPAIRS);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblRepair.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("RepairId"));
        tblRepair.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        tblRepair.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Description"));
        tblRepair.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("CId"));
        tblRepair.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("RepairDate"));
        getAllRepair();
        loadCustomerId();
        txtRepairId.setDisable(true);
        txtItemName.setDisable(true);
        txtDescription.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        try {
            txtRepairId.setText(bo.getLastRepairID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllRepair() {
        try {
            ArrayList<RepairDTO> allRepair = bo.getAllRepair();
            tblRepair.setItems(FXCollections.observableArrayList(allRepair));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onAction_btnSave(ActionEvent actionEvent) throws Exception {
        if (btnSave.getText().equals("Save")) {
            RepairDTO dto = new RepairDTO(
                   txtRepairId.getText(),
                    cmbCId.getSelectionModel().getSelectedItem().toString(),
                    txtItemName.getText(),
                    txtDescription.getText(),
                    DPRepairDate.getValue().toString()
            );
            boolean b = bo.addRepair(dto);


        } else {
            RepairDTO dto = new RepairDTO(
                    txtRepairId.getText(),
                    cmbCId.getSelectionModel().getSelectedItem().toString(),
                    txtItemName.getText(),
                    txtDescription.getText(),
                    DPRepairDate.getValue().toString());
            boolean b = bo.addRepair(dto);
        }
        getAllRepair();
    }

    public void onAction_btnDelete(ActionEvent actionEvent) throws Exception {
        bo.deleteRepair(txtRepairId.getText());
        getAllRepair();

    }



    public void onAction_btnAddNewRepair(ActionEvent actionEvent) throws Exception {
        txtRepairId.setText(bo.getLastRepairID());
        txtItemName.clear();
        txtDescription.clear();
        tblRepair.getSelectionModel().clearSelection();
        txtItemName.setDisable(false);
        txtItemName.requestFocus();
        txtDescription.setDisable(false);
        btnSave.setDisable(false);
    }

    private void loadCustomerId() {
        try {
            List<String> allCustomerId = bo.getAllCustomerId();
            cmbCId.setItems(FXCollections.observableArrayList(allCustomerId));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onAction_btnBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/ShopKeeperForm.fxml"));
        Stage stage = (Stage) (this.root3.getScene().getWindow());
        Scene scene = new Scene(load.load());
        ShopKeeperFormController shopKeeperFormController = load.getController();
        stage.setScene(scene);
        stage.show();
    }

    public void cmbCId_OnAction(ActionEvent actionEvent) {
    }

    public void tblRepair_OnMouseClicked(MouseEvent mouseEvent) {
        RepairDTO selectedItem = tblRepair.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            btnSave.setText("Save");
            btnDelete.setDisable(true);
            return;
        }
        btnSave.setText("Update");
        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        txtItemName.setDisable(false);
        txtDescription.setDisable(false);

        txtRepairId.setText(selectedItem.getRepairId());
        txtItemName.setText(selectedItem.getItemName());
        txtDescription.setText(selectedItem.getDescription());
        DPRepairDate.setValue(LocalDate.parse(selectedItem.getRepairDate(),DateTimeFormatter.ISO_DATE));

    }

    public void onAction_btnPrint(ActionEvent actionEvent) {
    }
}



