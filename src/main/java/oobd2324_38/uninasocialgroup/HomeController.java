package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController {
    @FXML private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;


    public void InitPage(String username) {
        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setIdUtente(utente.getIdByUsername());

        UtenteLoggato.setText(username);
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumber.setText(String.valueOf(utente.getNotificationsNumber()));
        } else {
            NotificationsNumber.setVisible(false);
        }
    }

}
