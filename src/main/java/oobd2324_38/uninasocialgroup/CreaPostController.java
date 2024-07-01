package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreaPostController {
    private int idGruppo;
    private String UtenteLoggato;
    private int NotificationsNumber;
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    @FXML private Label UtenteLoggatoLabel;
    @FXML private Label NotificationsNumberLabel;
    @FXML private TextArea PostField;
    @FXML private Label CampoVuotoLabel;
    @FXML private TextField HomeSearchBar;


    public void initPage() {
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato);
        utente.setIdUtente(utente.getIdByUsername());

        UtenteLoggatoLabel.setText(UtenteLoggato);
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumberLabel.setText(Integer.toString(NotificationsNumber));
            NotificationsNumberLabel.setVisible(true);
        }
    }


    public void OnNotificationButtonClick() {
        SceneController sc = new SceneController();
        sc.setNotificationsGrid(this.NotificationsGrid);
        sc.setNotificationsPane(this.NotificationsPane);
        sc.setNotificationsGrid(this.NotificationsGrid);
        sc.setUtenteLoggato(this.UtenteLoggato);
        sc.NotificationAction();
    }

    public void LogOutOnClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToLoginScene();
    }

    public int getNotificationsNumber() {
        return NotificationsNumber;
    }

    public void setNotificationsNumber(int notificationsNumber) {
        NotificationsNumber = notificationsNumber;
    }

    public String getUtenteLoggato() {
        return UtenteLoggato;
    }

    public void setUtenteLoggato(String utenteLoggato) {
        UtenteLoggato = utenteLoggato;
    }

    public int getIdGruppo() {
        return idGruppo;
    }

    public void setIdGruppo(int idGruppo) {
        this.idGruppo = idGruppo;
    }


    public void OnCondividiButtonClick() throws IOException, SQLException {
        if(PostField.getText().isEmpty()) {
            CampoVuotoLabel.setVisible(true);
        } else {
            Utente utente = new Utente();
            utente.setUsername(UtenteLoggato);
            utente.setIdUtente(utente.getIdByUsername());
            Gruppo gruppo = new Gruppo();
            gruppo.setIdGruppo(idGruppo);
            Post post = new Post(PostField.getText(), gruppo, utente);
            try{
                post.insertPost();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            SceneController sceneController = new SceneController();
            sceneController.setUtenteLoggato(this.UtenteLoggato);
            sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
            sceneController.setIdGruppo(this.idGruppo);
            sceneController.SwitchToGroupPageScene();
        }
    }

    public void OnCreaButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sceneController.SwitchToCreaScene();
    }

    public void OnHomeButtonClick() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sceneController.setIdGruppo(this.idGruppo);
        sceneController.SwitchToGroupPageScene();
    }

    public void OnGoBackButtonClicked() throws IOException, SQLException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sceneController.setIdGruppo(this.idGruppo);
        sceneController.SwitchToGroupPageScene();
    }

    public void OnMenuRequestButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sceneController.SwitchToRichiesteScene();
    }

    public void OnMenuReportButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sceneController.SwitchToReportScene();
    }

    public void OnLensClicked() throws IOException {
        SceneController sc = new SceneController();
        sc.setUtenteLoggato(this.UtenteLoggato);
        sc.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sc.setHomeSearchBar(this.HomeSearchBar);
        sc.LensAction();
    }
}