package controller;

import bo.BOFactory;
import bo.custom.SupplierBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.SupplierDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    public TableView<SupplierDTO> tblSupplier;
    public TableColumn clmSupId;
    public TableColumn clmSupName;
    public TableColumn clmContactNo;
    public TableColumn clmEmail;
    public JFXTextField txtSupId;
    public JFXTextField txtSupName;
    public JFXTextField txtContactNo;
    public JFXTextField txtEmail;
    public JFXButton btnViewDetails;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXButton btnAddNew;
    public Label txtHome;
    public ImageView imgHome;
    public VBox rooot;
    public AnchorPane root9;
    public JFXButton btnBack;
    Connection conn;
    SupplierBO bo = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("SId"));
        tblSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("SName"));
        tblSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        tblSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Email"));
        getAllSupplier();
        try {
            txtSupId.setText(bo.getLastSupID());
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtSupId.setDisable(true);
        txtSupName.setDisable(true);
        txtContactNo.setDisable(true);
        txtEmail.setDisable(true);
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);

//        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.5), rooot);
//        translate.setToX(rooot.getMaxHeight() + (root9.getPrefWidth() - rooot.getLayoutX()));
//        translate.play();

        tblSupplier.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierDTO>() {
            @Override
            public void changed(ObservableValue<? extends SupplierDTO> observable, SupplierDTO supplier, SupplierDTO t1) {
                SupplierDTO selectedItem = tblSupplier.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnAdd.setText("Save");
                    btnDelete.setDisable(true);
                    return;
                }
                btnAdd.setText("Update");
                btnAdd.setDisable(false);
                btnDelete.setDisable(false);
                txtSupName.setDisable(false);
                txtContactNo.setDisable(false);
                txtEmail.setDisable(false);
                txtSupId.setText(selectedItem.getSId());
                txtSupName.setText(selectedItem.getSName());
                txtContactNo.setText(String.valueOf(selectedItem.getContactNo()));
                txtEmail.setText(selectedItem.getEmail());

            }
        });

    }

    private void getAllSupplier() {
        try {
            ArrayList<SupplierDTO> allSupplier = bo.getAllSupplier();
            tblSupplier.setItems(FXCollections.observableArrayList(allSupplier));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnAdd_OnAction(ActionEvent actionEvent) throws Exception {
        if (btnAdd.getText().equals("Save")) {
            SupplierDTO dto = new SupplierDTO(
                   txtSupId.getText(),
                    txtSupName.getText(),
                    Integer.valueOf(txtContactNo.getText()),
                    txtEmail.getText()
            );
            boolean b = bo.addSupplier(dto);
            txtSetDisable();
            getAllSupplier();

        } else {
            SupplierDTO dto = new SupplierDTO(
                    txtSupId.getText(),
                    txtSupName.getText(),
                    Integer.valueOf(txtContactNo.getText()),
                    txtEmail.getText()
            );
            boolean b = bo.updateSupplier(dto);
            txtSetDisable();

        }
        getAllSupplier();
    }

    private void txtSetDisable() {
        txtSupName.setText("");
        txtEmail.setText("");
        txtContactNo.setText("");
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) throws Exception {
        bo.deleteSupplier(txtSupId.getText());
        getAllSupplier();
        txtSetDisable();

    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        try {
            txtSupId.setText(bo.getLastSupID());
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtSupName.clear();
        txtContactNo.clear();
        txtEmail.clear();
        tblSupplier.getSelectionModel().clearSelection();
        txtSupName.setDisable(false);
        txtSupName.requestFocus();
        txtContactNo.setDisable(false);
        txtEmail.setDisable(false);
        btnAdd.setDisable(false);
    }


    public void onAction_btnBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/AdminForm.fxml"));
        Stage stage = (Stage) (this.root9.getScene().getWindow());
        Scene scene = new Scene(load.load());
        AdminFormController adminFormController = load.getController();
        stage.setScene(scene);
        stage.show();
    }
}
