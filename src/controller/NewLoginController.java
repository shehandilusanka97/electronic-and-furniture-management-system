package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewLoginController implements Initializable {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXTextField txtNewUserName;
    public JFXPasswordField txtNewPassword;
    public JFXPasswordField txtConfirmPass;
    public AnchorPane mainRoot;
    public JFXButton btnLogin;
    @FXML
    private VBox root1;

    @FXML
    private VBox root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root1.setVisible(false);

    }

    public void usernameEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtPassword.requestFocus();
        }
    }
    

    public void ComfirmPassEnter(KeyEvent keyEvent) {
    }

    public void signUp(ActionEvent actionEvent) {
        root1.setVisible(true);
        TranslateTransition translate=new TranslateTransition(Duration.seconds(0.7),root);
        translate.setToX(root.getLayoutX() + (mainRoot.getPrefWidth()-root.getSpacing()));
        translate.play();
    }

    public void signInAgain(ActionEvent actionEvent) {
        root1.setVisible(false);
        TranslateTransition translate=new TranslateTransition(Duration.seconds(0.7),root);
        translate.setToX(root.getLayoutX());
        translate.play();
    }

    public void login(ActionEvent actionEvent) {

    }

    public void forgetPassword(MouseEvent mouseEvent) {
        root1.setVisible(true);
        TranslateTransition translate=new TranslateTransition(Duration.seconds(0.7),root);
        translate.setToX(root.getLayoutX() + (mainRoot.getPrefWidth()-root.getSpacing()));
        translate.play();
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
    }


    public void newUsernameEnter(KeyEvent keyEvent) {
    }

    public void newPasswordEnter(KeyEvent keyEvent) {
    }
    public void passwordEnter(KeyEvent keyEvent) {
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        if (userName.length() > 0 && password.length() > 0) {

            if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("1234")) {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminForm.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
//                scene.setFill(Color.TRANSPARENT);
                stage.setResizable(false);
                stage.show();
                Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
                primaryStage.close();

            }else if (userName.equalsIgnoreCase("shopkeeper") && password.equalsIgnoreCase("1234")) {
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/ShopKeeperForm.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
//                scene.setFill(Color.TRANSPARENT);
                stage.show();
                stage.setResizable(false);
                Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
                primaryStage.close();

            }else {
                new Alert(Alert.AlertType.WARNING, "Wrong User name Or Password", ButtonType.OK).show();
                txtUserName.clear();
                txtPassword.clear();

            }

        } else {
            new Alert(Alert.AlertType.WARNING, "User name Or Pass word empty", ButtonType.OK).show();
            txtUserName.clear();
            txtPassword.clear();

        }
    }
}
