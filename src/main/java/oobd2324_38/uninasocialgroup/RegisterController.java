package oobd2324_38.uninasocialgroup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ComboBox<Sesso> SignInChoiceBox;
    @FXML
    private Button SignInButton;
    @FXML
    private TextField SignInUsernameField;
    @FXML
    private TextField SignInNameField;
    @FXML
    private TextField SignInSurnameField;
    @FXML
    private TextField SignInPwdField;
    @FXML
    private TextField SignInPhoneField;
    @FXML
    private DatePicker SignInDatePicker;
    @FXML
    private TextArea SignInBiografia;
    @FXML
    private Label UsernameVuotoLabel;
    @FXML
    private Label NomeVuotoLabel;
    @FXML
    private Label CognomeVuotoLabel;
    @FXML
    private Label PwdVuotoLabel;
    @FXML
    private Label SessoVuotoLabel;
    @FXML
    private Label DataNascitaVuotoLabel;
    @FXML
    private Label UserNotAvailableLabel;
    @FXML
    private Label NumberNotValidLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SignInChoiceBox.getItems().addAll(Sesso.Maschio, Sesso.Femmina, Sesso.Altro);
    }

    public void SwitchToLoginScene() throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = Main.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void SwitchToLoginSceneWithCreds() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        root = loader.load();
        stage = Main.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        LoginController controller = loader.getController();
        controller.setLoginCredentialsAfterSignIn(SignInUsernameField.getText(), SignInPwdField.getText());
    }

    public void SignInButtonClicked(ActionEvent event) throws IOException {
        if(SignInUsernameField.getText().isEmpty()) {
            NomeVuotoLabel.setVisible(false);
            UsernameVuotoLabel.setVisible(true);
            CognomeVuotoLabel.setVisible(false);
            PwdVuotoLabel.setVisible(false);
            SessoVuotoLabel.setVisible(false);
            DataNascitaVuotoLabel.setVisible(false);
            UserNotAvailableLabel.setVisible(false);
            NumberNotValidLabel.setVisible(false);
        } else if(SignInNameField.getText().isEmpty()) {
            NomeVuotoLabel.setVisible(true);
            UsernameVuotoLabel.setVisible(false);
            CognomeVuotoLabel.setVisible(false);
            PwdVuotoLabel.setVisible(false);
            SessoVuotoLabel.setVisible(false);
            DataNascitaVuotoLabel.setVisible(false);
            UserNotAvailableLabel.setVisible(false);
            NumberNotValidLabel.setVisible(false);
        } else if(SignInSurnameField.getText().isEmpty()) {
            NomeVuotoLabel.setVisible(false);
            UsernameVuotoLabel.setVisible(false);
            CognomeVuotoLabel.setVisible(true);
            PwdVuotoLabel.setVisible(false);
            SessoVuotoLabel.setVisible(false);
            DataNascitaVuotoLabel.setVisible(false);
            UserNotAvailableLabel.setVisible(false);
            NumberNotValidLabel.setVisible(false);
        } else if(SignInPwdField.getText().isEmpty()) {
            NomeVuotoLabel.setVisible(false);
            UsernameVuotoLabel.setVisible(false);
            CognomeVuotoLabel.setVisible(false);
            PwdVuotoLabel.setVisible(true);
            SessoVuotoLabel.setVisible(false);
            DataNascitaVuotoLabel.setVisible(false);
            UserNotAvailableLabel.setVisible(false);
            NumberNotValidLabel.setVisible(false);
        } else if(SignInChoiceBox.getValue() == null) {
            NomeVuotoLabel.setVisible(false);
            UsernameVuotoLabel.setVisible(false);
            CognomeVuotoLabel.setVisible(false);
            PwdVuotoLabel.setVisible(false);
            SessoVuotoLabel.setVisible(true);
            DataNascitaVuotoLabel.setVisible(false);
            UserNotAvailableLabel.setVisible(false);
            NumberNotValidLabel.setVisible(false);
        } else if(SignInDatePicker.getValue() == null) {
            NomeVuotoLabel.setVisible(false);
            UsernameVuotoLabel.setVisible(false);
            CognomeVuotoLabel.setVisible(false);
            PwdVuotoLabel.setVisible(false);
            SessoVuotoLabel.setVisible(false);
            DataNascitaVuotoLabel.setVisible(true);
            UserNotAvailableLabel.setVisible(false);
            NumberNotValidLabel.setVisible(false);
        } else {
            try{
                if(!CheckPhoneNumber(SignInPhoneField.getText())) {
                    throw new PhoneNumberException();
                }else {
                    Utente newUtente = new Utente(SignInUsernameField.getText(), SignInPwdField.getText(), SignInNameField.getText(), SignInSurnameField.getText(),
                            SignInPhoneField.getText(), SignInBiografia.getText(), SignInDatePicker.getValue(), Sesso.valueOf(String.valueOf(SignInChoiceBox.getValue())));
                    try{
                        if(newUtente.InsertnewUser()) this.SwitchToLoginSceneWithCreds();
                    }catch(SQLException e){
                        if(e.getErrorCode() == 0) {
                            NomeVuotoLabel.setVisible(false);
                            UsernameVuotoLabel.setVisible(false);
                            CognomeVuotoLabel.setVisible(false);
                            PwdVuotoLabel.setVisible(false);
                            SessoVuotoLabel.setVisible(false);
                            DataNascitaVuotoLabel.setVisible(false);
                            UserNotAvailableLabel.setVisible(true);
                            NumberNotValidLabel.setVisible(false);
                        }
                    }

                }
            }catch(PhoneNumberException e){
                NomeVuotoLabel.setVisible(false);
                UsernameVuotoLabel.setVisible(false);
                CognomeVuotoLabel.setVisible(false);
                PwdVuotoLabel.setVisible(false);
                SessoVuotoLabel.setVisible(false);
                DataNascitaVuotoLabel.setVisible(false);
                UserNotAvailableLabel.setVisible(false);
                NumberNotValidLabel.setVisible(true);
            }
        }
    }

    private boolean CheckPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("(\\d{11})");
        Matcher matcher = pattern.matcher(number);
        if((number.startsWith("3") & matcher.matches()) || number.isEmpty()) return true;
        return false;
    }
}
