package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController {
    @FXML private Label UtenteLoggato;

    public void InitPage(String username) {
        UtenteLoggato.setText(username);
    }
}
