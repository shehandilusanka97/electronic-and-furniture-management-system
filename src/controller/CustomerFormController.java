package controller;

import animatefx.animation.Shake;
import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.CustomerDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CustomerFormController implements Initializable {
    public JFXButton btnBack;
    public JFXButton btnReport;
    public AnchorPane root1;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<CustomerDTO> tblCustomer;
    public TableColumn clmCustomerId;
    public TableColumn clmCustomerName;
    public TableColumn clmAddress;
    public TableColumn clmContactNo;
    public JFXButton btnAddNewCustomer;
    CustomerBO bo = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtCustomerId.setDisable(true);
        txtCustomerName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerDTO>() {
            @Override
            public void changed(ObservableValue<? extends CustomerDTO> observable, CustomerDTO oldValue, CustomerDTO newValue) {
                CustomerDTO selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    return;
                }
                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtCustomerName.setDisable(false);
                txtAddress.setDisable(false);
                txtContactNo.setDisable(false);
                txtCustomerId.setText(selectedItem.getCustomerId());
                txtCustomerName.setText(selectedItem.getCustomerName());
                txtAddress.setText(selectedItem.getAddress());
                txtContactNo.setText(String.valueOf(selectedItem.getContactNo()));

            }


        });

        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ContactNo"));

        loadAllCustomers();
        try {
            txtCustomerId.setText(bo.getLastID());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadAllCustomers() {
        try {
            ArrayList<CustomerDTO> allCustomer = bo.getAllCustomer();
            tblCustomer.setItems(FXCollections.observableArrayList(allCustomer));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onAction_btnSave(ActionEvent actionEvent) {
        if (txtCustomerName.getText().matches("^[A-z]{2,}$")) {
            if (txtAddress.getText().matches("^^[^@*$!#.][A-z|0-9|,/| ]{1,}$")) {
                if (txtContactNo.getText().matches("^[\\d]{10}$")) {
                    if (btnSave.getText().equals("Save")) {
                        try {
                            CustomerDTO dto = new CustomerDTO(
                                    bo.getLastID(),
                                    txtCustomerName.getText(),
                                    txtAddress.getText(),
                                    Integer.valueOf(txtContactNo.getText())
                            );

                            boolean b = bo.addCustomer(dto);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        txtSetDisable();

                    } else {
                        try {
                            CustomerDTO dto = new CustomerDTO(
                                    txtCustomerId.getText(),
                                    txtCustomerName.getText(),
                                    txtAddress.getText(),
                                    Integer.valueOf(txtContactNo.getText())
                            );

                            boolean b = bo.updateCustomer(dto);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        txtSetDisable();
                    }
                } else {
                    new Shake(txtContactNo).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
                    txtContactNo.setUnFocusColor(Paint.valueOf("Red"));
                    txtContactNo.requestFocus();
                    txtContactNo.setUnFocusColor(Paint.valueOf("Blue"));
                    txtContactNo.setText("");
                }
            } else {

                txtAddress.setUnFocusColor(Paint.valueOf("Red"));
                new Shake(txtAddress).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
                txtAddress.requestFocus();
                txtAddress.setUnFocusColor(Paint.valueOf("Blue"));
                txtAddress.setText("");
            }
        } else {

            txtCustomerName.setUnFocusColor(Paint.valueOf("Red"));
            new Shake(txtCustomerName).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
            txtCustomerName.setUnFocusColor(Paint.valueOf("Blue"));
            txtCustomerName.setText("");
            txtCustomerName.requestFocus();


        }

        loadAllCustomers();

    }

    public void onAction_btnDelete(ActionEvent actionEvent) throws Exception {
        bo.deleteCustomer(txtCustomerId.getText());
        loadAllCustomers();
        txtSetDisable();


    }

    public void onAction_AddNewCustomer(ActionEvent actionEvent) {
        try {
            txtCustomerId.setText(bo.getLastID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtCustomerName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        tblCustomer.getSelectionModel().clearSelection();
        txtCustomerName.setDisable(false);
        txtCustomerName.requestFocus();
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        btnSave.setDisable(false);

    }

    public void txtCustomerIdOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        txtCustomerName.requestFocus();
    }

    public void txtSetDisable() {
        txtCustomerName.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");


    }

    public void onAction_btnBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/ShopKeeperForm.fxml"));
        Stage stage = (Stage) (this.root1.getScene().getWindow());
        Scene scene = new Scene(load.load());
        ShopKeeperFormController shopKeeperFormController = load.getController();
        stage.setScene(scene);
        stage.show();
    }

    public void txtNameEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtAddress.requestFocus();
        }
    }

    public void txtAddressEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtContactNo.requestFocus();
        }
    }

    public void txtContactNoEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            btnSave.requestFocus();
        }
    }

    public void onAction_btnReport(ActionEvent actionEvent) {

        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/jReports/AdikariCustomer.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(inputStream);
            JasperPrint js = JasperFillManager.fillReport(jr, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(js);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

