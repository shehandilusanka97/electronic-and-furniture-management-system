package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopKeeperFormController implements Initializable {
    public AnchorPane root;
    public ImageView imgCustomer;
    public ImageView imgElectronic;
    public ImageView imgFurniture;
    public ImageView imgRepair;
    public ImageView imgPurchase;
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgEmployee;
    public JFXButton btnLogOut;
    public ImageView imgOrder;


    public void initialize(URL url, ResourceBundle rb){
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

    }
    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();

            switch(icon.getId()){
                case "imgCustomer":
                    lblMenu.setText("Customers Form");
                    lblDescription.setText("Click to add, edit, delete, search or view customers");
                    break;
                case "imgFurniture":
                    lblMenu.setText("Item Form");
                    lblDescription.setText("Click to add, edit, delete, search or view Item");
                    break;
                case "imgElectronic":
                    lblMenu.setText("Item Form");
                    lblDescription.setText("Click to add, edit, delete, search or view  Items");
                    break;
                case "imgRepair":
                    lblMenu.setText("Repairs Form");
                    lblDescription.setText("Click to add, edit, delete, search or view Repairs");
                    break;
                case "imgOrder":
                    lblMenu.setText("Order Form");
                    lblDescription.setText("Click to place order, Generate report or View Order Details");
                    break;
            }

            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.RED);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch(icon.getId()){
                case "imgCustomer":
                    root = FXMLLoader.load(this.getClass().getResource("/view/CustomerForm.fxml"));
                    break;
                case "imgFurniture":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ItemForm.fxml"));
                    break;
                case "imgRepair":
                    root = FXMLLoader.load(this.getClass().getResource("/view/RepairForm.fxml"));
                    break;

                case "imgOrder":
                    root = FXMLLoader.load(this.getClass().getResource("/view/OrderForm.fxml"));
                    break;
            }

            if (root != null){
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();

                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
                TranslateTransition tt = new TranslateTransition(Duration.millis(500), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void btnLogOut_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("../view/NewLogin.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
//        scene.setFill(Color.TRANSPARENT);
        stage.show();
        Stage primaryStage = (Stage) btnLogOut.getScene().getWindow();
        primaryStage.close();


    }
}
