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

    private void RefreshPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RichiesteAccesso.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        RichiesteAccessoController controller = loader.getController();
        controller.setUtenteLoggato1(UtenteLoggato);
        controller.setNotificationsNumber1(Integer.parseInt(NotificationsNumber));
        controller.InitPage();
    }

    public void OnAcceptButtonClick() throws IOException {
        richiesta.accettaRichiesta();
        RefreshPage();
    }

    public void OnRejectButtonClick() throws IOException {
        richiesta.rifiutaRichiesta();
        RefreshPage();
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
