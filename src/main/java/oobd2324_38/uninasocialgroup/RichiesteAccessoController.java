package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RichiesteAccessoController {
    @FXML
    private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    @FXML private TextField HomeSearchBar;
    @FXML private GridPane GridRequests;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    private String UtenteLoggato1;
    private String NotificationsNumber1;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void InitPage() {
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato1);
        utente.setIdUtente(utente.getIdByUsername());
        ArrayList<Richiesta> Richieste = utente.GetAllGroupRequests();

        UtenteLoggato.setText(UtenteLoggato1);
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumber.setText(String.valueOf(utente.getNotificationsNumber()));
            NotificationsNumber.setVisible(true);
        }
        int rows = 1;

        if(Richieste != null) {
            try {
                for (int i = 0; i < Richieste.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("RichiestaTag.fxml"));
                    GridPane richiesteGrid = fxmlLoader.load();
                    RichiestaTagController controller = fxmlLoader.getController();
                    StringBuilder messaggio = new StringBuilder();
                    messaggio.append(Richieste.get(i).getUtente().getUsername());
                    messaggio.append(" ha richiesto di entrare nel gruppo \"");
                    messaggio.append(Richieste.get(i).getGruppo().getNome());
                    messaggio.append("\"");
                    controller.setRichiesta(Richieste.get(i));
                    controller.setUtenteLoggato(UtenteLoggato1);
                    controller.setNotificationsNumber(NotificationsNumber1);
                    controller.InitializeTag(messaggio);
                    GridRequests.add(richiesteGrid, 0, rows);
                    GridPane.setMargin(richiesteGrid, new Insets(10, 10, 10, 10));
                    rows++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getUtenteLoggato1() {
        return UtenteLoggato1;
    }

    public void setUtenteLoggato1(String utenteLoggato1) {
        UtenteLoggato1 = utenteLoggato1;
    }

    public String getNotificationsNumber1() {
        return NotificationsNumber1;
    }

    public void setNotificationsNumber1(String notificationsNumber1) {
        NotificationsNumber1 = notificationsNumber1;
    }

    public void OnHomeButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber1));
        sceneController.SwitchToHomeScene();
    }

    public void LogOutOnClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToLoginScene();
    }

    public void OnCreaButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber1));
        sceneController.SwitchToCreaScene();
    }

    public void OnLensClicked() throws IOException {
        SceneController sc = new SceneController();
        sc.setUtenteLoggato(this.UtenteLoggato.getText());
        sc.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sc.setHomeSearchBar(this.HomeSearchBar);
        sc.LensAction();
    }

    public void OnNotificationButtonClick() {
        SceneController sc = new SceneController();
        sc.setNotificationsGrid(this.NotificationsGrid);
        sc.setNotificationsPane(this.NotificationsPane);
        sc.setNotificationsGrid(this.NotificationsGrid);
        sc.setUtenteLoggato(this.UtenteLoggato.getText());
        sc.NotificationAction();
    }

    public void OnMenuReportButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber1));
        sceneController.SwitchToReportScene();
    }
}
