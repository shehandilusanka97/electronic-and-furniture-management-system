package controller;


import animatefx.animation.Shake;
import bo.BOFactory;
import bo.custom.EmployeeBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.EmployeeDTO;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class EmployeeFormController implements Initializable {
    public JFXButton btnBack;
    EmployeeBO bo = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
    public JFXTextField txtEmployeeId;
    public JFXTextField txtEmployeeName;
    public JFXTextField txtAddress;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXTextField txtContactNo;
    public TableView <EmployeeDTO> tblEmployee;
    public TableColumn clmEmployeeId;
    public TableColumn clmEmployeeName;
    public TableColumn clmAddress;
    public TableColumn clmContactNo;
    public AnchorPane root5;
    public JFXButton btnAddNewEmp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtEmployeeId.setDisable(true);
        txtEmployeeName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        Connection conn;

        btnSave.setDisable(true);
        btnDelete.setDisable(true);


        tblEmployee.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EmployeeDTO>() {
            @Override
            public void changed(ObservableValue<? extends EmployeeDTO> observableValue, EmployeeDTO employee, EmployeeDTO t1) {
                EmployeeDTO selectedItem = tblEmployee.getSelectionModel().getSelectedItem();

                if (selectedItem == null){
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    return;
                }
                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtEmployeeName.setDisable(false);
                txtAddress.setDisable(false);
                txtContactNo.setDisable(false);
                txtEmployeeId.setText(selectedItem.getEmpId());
                txtEmployeeName.setText(selectedItem.getEmpName());
                txtAddress.setText(selectedItem.getAddress());
                txtContactNo.setText(String.valueOf(selectedItem.getContactNo()));
            }
        });
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("EmpId"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("EmpName"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ContactNo"));

        getAllEmployee();
        try {
           txtEmployeeId.setText(bo.getLastEmployeeID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllEmployee() {
        try {
            ArrayList<EmployeeDTO> allEmployee  =  bo.getAllEmployee();
            tblEmployee.setItems(FXCollections.observableArrayList(allEmployee));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAction_btnSave(ActionEvent actionEvent) throws Exception {
        if (txtEmployeeName.getText().matches("^[A-z]{2,}$")){

        }else {
            txtEmployeeName.setUnFocusColor(Paint.valueOf("Red"));
            new Shake(txtEmployeeName).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
            txtEmployeeName.setUnFocusColor(Paint.valueOf("Blue"));
            txtEmployeeName.setText("");
            txtEmployeeName.requestFocus();
        }

        if (btnSave.getText().equals("Save")) {
            EmployeeDTO dto = new EmployeeDTO(
                    bo.getLastEmployeeID(),
                    txtEmployeeName.getText(),
                    txtAddress.getText(),
                    Integer.valueOf(txtContactNo.getText())
            );
            boolean b = bo.addEmployee(dto);
            txtEmployeeId.setText(bo.getLastEmployeeID());
            txtSetDisable();

        }else{
            EmployeeDTO dto = new EmployeeDTO(
                    txtEmployeeId.getText(),
                    txtEmployeeName.getText(),
                    txtAddress.getText(),
                    Integer.valueOf(txtContactNo.getText())
            );
            boolean b = bo.updateEmployee(dto);
            txtSetDisable();
        }
        getAllEmployee();
    }

    private void txtSetDisable() {
        txtEmployeeName.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");
    }


    public void onAction_btnDelete(ActionEvent actionEvent) throws Exception {
        bo.deleteEmployee(txtEmployeeId.getText());
        getAllEmployee();
        txtSetDisable();
    }

    public void onAction_btnAddNewEmp(ActionEvent actionEvent) throws Exception {
        txtEmployeeId.setText(bo.getLastEmployeeID());
        txtEmployeeName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        tblEmployee.getSelectionModel().clearSelection();
        tblEmployee.setDisable(false);
        txtEmployeeName.setDisable(false);
        txtEmployeeName.requestFocus();
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        txtEmployeeId.requestFocus();
        btnSave.setDisable(false);


    }

    public void onAction_btnBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/AdminForm.fxml"));
        Stage stage = (Stage) (this.root5.getScene().getWindow());
        Scene scene = new Scene(load.load());
        AdminFormController adminFormController = load.getController();
        stage.setScene(scene);
        stage.show();
    }
}
