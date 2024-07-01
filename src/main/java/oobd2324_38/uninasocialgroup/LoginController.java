package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
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

    public void onSignInClicked() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToSignInScene();
    }

    public void setLoginCredentialsAfterSignIn(String username, String password) {
        setUsernameField.setText(username);
        setPasswordField.setText(password);
    }

    public void LoginChecks() throws IOException {
        if(MostraPwdCheck.isSelected()) {
            if(setUsernameField.getText().isEmpty() || setPasswordClearField.getText().isEmpty()){
                EmptyFieldsLabel.setVisible(true);
                InvalidCredentialsLabel.setVisible(false);
                LogInSuccessLabel.setVisible(false);
            } else {
                Utente utente = new Utente();
                if(utente.LoginVerification(setUsernameField.getText(), setPasswordClearField.getText()) != null) {
                    SceneController sceneController = new SceneController();
                    sceneController.setUtenteLoggato(this.setUsernameField.getText());
                    sceneController.SwitchToHomeScene();
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
                    SceneController sceneController = new SceneController();
                    sceneController.setUtenteLoggato(this.setUsernameField.getText());
                    sceneController.SwitchToHomeScene();
                } else {
                    EmptyFieldsLabel.setVisible(false);
                    InvalidCredentialsLabel.setVisible(true);
                    LogInSuccessLabel.setVisible(false);
                }
            }
        }
    }

    public void LogInOnClick() throws IOException {
        LoginChecks();
    }

    public void LogInOnEnter(KeyEvent event) throws IOException {
        if((event).getCode() == KeyCode.ENTER) {
            LoginChecks();
        }
    }
}