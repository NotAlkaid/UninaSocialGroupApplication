package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class LoginController {
    @FXML
    private CheckBox MostraPwdCheck;
    @FXML
    private TextField setPasswordClearField;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private TextField setUsernameField;
    @FXML
    private Button LogInButton;
    @FXML
    private Label InvalidCredentialsLabel;
    @FXML
    private Label LogInSuccessLabel;
    @FXML
    private Label EmptyFieldsLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void MostraPasswordOnAction(ActionEvent event) {
        if(MostraPwdCheck.isSelected()) {
            if(setPasswordField.getText().isEmpty()){
                setPasswordField.setVisible(false);
                setPasswordClearField.setVisible(true);
            } else {
                setPasswordClearField.setText(setPasswordField.getText());
                setPasswordField.setText("");
                setPasswordClearField.setVisible(true);
                setPasswordField.setVisible(false);
            }
        } else {
            if(setPasswordClearField.getText().isEmpty()){
                setPasswordClearField.setVisible(false);
                setPasswordField.setVisible(true);
            } else {
                setPasswordField.setText(setPasswordClearField.getText());
                setPasswordClearField.setText("");
                setPasswordField.setVisible(true);
                setPasswordClearField.setVisible(false);
            }
        }
    }

    public void LogInOnAction(ActionEvent event) throws IOException {
        if(MostraPwdCheck.isSelected()) {
            if(setUsernameField.getText().isEmpty() || setPasswordClearField.getText().isEmpty()){
                EmptyFieldsLabel.setVisible(true);
                InvalidCredentialsLabel.setVisible(false);
                LogInSuccessLabel.setVisible(false);
            } else {
                Utente utente = new Utente();
                if(utente.LoginVerification(setUsernameField.getText(), setPasswordClearField.getText()) != null) { //Qui dovro' gestire lo switch page passando l'utente per parametro
                    SwitchToHomeScene(event);
                } else {
                    EmptyFieldsLabel.setVisible(false);
                    InvalidCredentialsLabel.setVisible(true);
                    LogInSuccessLabel.setVisible(false);
                }
            }
        } else {
            if(setUsernameField.getText().isEmpty() || setPasswordField.getText().isEmpty()){
                EmptyFieldsLabel.setVisible(true);
                InvalidCredentialsLabel.setVisible(false);
                LogInSuccessLabel.setVisible(false);
            } else {
                Utente utente = new Utente();
                if(utente.LoginVerification(setUsernameField.getText(), setPasswordField.getText()) != null) {
                    SwitchToHomeScene(event);
                } else {
                    EmptyFieldsLabel.setVisible(false);
                    InvalidCredentialsLabel.setVisible(true);
                    LogInSuccessLabel.setVisible(false);
                }
            }
        }
    }

    public void SwitchToSignInScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void SwitchToHomeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        HomeController controller = loader.getController();
        controller.InitPage(setUsernameField.getText());
    }

    public void setLoginCredentialsAfterSignIn(String username, String password) {
        setUsernameField.setText(username);
        setPasswordField.setText(password);
    }
}