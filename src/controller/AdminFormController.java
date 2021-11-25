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

public class AdminFormController implements Initializable {
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgEmployee;
    public ImageView imgSupplier;
    public ImageView imgOrder;
    public ImageView imgCustReturn;
    public AnchorPane root8;
    public JFXButton btnLogOut;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root8);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "imgEmployee":
                    lblMenu.setText("Employee Form");
                    lblDescription.setText("Click to Add, Update, Delete or View Employee Details");
                    break;
                case"imgOrder":
                    lblMenu.setText("Order Form");
                    lblDescription.setText("Click to place order, Generate report or View Order Details");
                    break;
                case "imgCustReturn":
                    lblMenu.setText("Customer Return Form");
                    lblDescription.setText("Click to Add, Update, Delete or View Customer return Details");
                    break;
                case "imgSupplier":
                    lblMenu.setText("Supplier Form");
                    lblDescription.setText("Click to Add, Update, Delete or View Supplier Details");
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

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root8 = null;
            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "imgEmployee":
                    root8 = FXMLLoader.load(this.getClass().getResource("/view/EmployeeForm.fxml"));
                    break;
                case "imgOrder":
                    root8 = FXMLLoader.load(this.getClass().getResource("/view/OrderForm.fxml"));
                    break;
                case "imgSupplier":
                    root8 = FXMLLoader.load(this.getClass().getResource("/view/SupplierForm.fxml"));
                    break;
                case "imgCustReturn":
                    root8 = FXMLLoader.load(this.getClass().getResource("/view/CustomerReturnForm.fxml"));



            }
            if (root8 != null) {
                Scene subScene = new Scene(root8);
                Stage primaryStage = (Stage) this.root8.getScene().getWindow();

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
