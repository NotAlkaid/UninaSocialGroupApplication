package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.sql.SQLException;
import java.util.ArrayList;

public class FunctionalReportController {
    private String UtenteLoggato1;
    private String NotificationsNumber1;
    @FXML private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    @FXML private TextField HomeSearchBar;
    private Gruppo gruppo;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initPage() {
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato1);
        utente.setIdUtente(utente.getIdByUsername());

        UtenteLoggato.setText(UtenteLoggato1);
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumber.setText(String.valueOf(utente.getNotificationsNumber()));
            NotificationsNumber.setVisible(true);
        }
    }

    public void LogOutOnClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToLoginScene();
    }

    public void OnNotificationButtonClick() {
        SceneController sc = new SceneController();
        sc.setNotificationsGrid(this.NotificationsGrid);
        sc.setNotificationsPane(this.NotificationsPane);
        sc.setNotificationsGrid(this.NotificationsGrid);
        sc.setUtenteLoggato(this.UtenteLoggato.getText());
        sc.NotificationAction();
    }

    public void OnCreaButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.SwitchToCreaScene();
    }

    public void OnLensClicked() throws IOException {
        SceneController sc = new SceneController();
        sc.setUtenteLoggato(this.UtenteLoggato.getText());
        sc.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sc.setHomeSearchBar(this.HomeSearchBar);
        sc.LensAction();
    }

    public void OnMenuRequestButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.SwitchToRichiesteScene();
    }

    public void OnMenuReportButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.SwitchToReportScene();
    }

    public void OnHomeButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.SwitchToHomeScene();
    }

    public void OnJanuaryButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Gennaio");
    }

    public void OnFebruaryButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Febbraio");
    }

    public void OnMarchButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Marzo");
    }

    public void OnAprilButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Aprile");
    }

    public void OnMayButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Maggio");
    }

    public void OnJuneButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Giugno");
    }

    public void OnJulyButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Luglio");
    }

    public void OnAugustButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Agosto");
    }

    public void OnSeptemberButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Settembre");
    }

    public void OnOctoberButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Ottobre");
    }

    public void OnNovemberButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Novembre");
    }

    public void OnDecemberButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato.getText());
        sceneController.setNotificationsNumber(this.NotificationsNumber.getText());
        sceneController.setGruppo(this.gruppo);
        sceneController.SwitchToGroupStatsPageScene("Dicembre");
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

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
    }
}
