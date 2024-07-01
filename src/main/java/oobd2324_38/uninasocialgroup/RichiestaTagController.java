package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class RichiestaTagController {
    @FXML private Label MessageLabel;
    private Richiesta richiesta;
    private String UtenteLoggato;
    private String NotificationsNumber;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void InitializeTag(StringBuilder messaggio) {
        MessageLabel.setText(messaggio.toString());
    }

    public void OnAcceptButtonClick() throws IOException {
        richiesta.accettaRichiesta();
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato);
        sceneController.setNotificationsNumber(this.NotificationsNumber);
        sceneController.RefreshPage();
    }

    public void OnRejectButtonClick() throws IOException {
        richiesta.rifiutaRichiesta();
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato);
        sceneController.setNotificationsNumber(this.NotificationsNumber);
        sceneController.RefreshPage();
    }

    public Richiesta getRichiesta() {
        return richiesta;
    }

    public void setRichiesta(Richiesta richiesta) {
        this.richiesta = richiesta;
    }

    public String getUtenteLoggato() {
        return UtenteLoggato;
    }

    public void setUtenteLoggato(String utenteLoggato) {
        UtenteLoggato = utenteLoggato;
    }

    public String getNotificationsNumber() {
        return NotificationsNumber;
    }

    public void setNotificationsNumber(String notificationsNumber) {
        NotificationsNumber = notificationsNumber;
    }
}
