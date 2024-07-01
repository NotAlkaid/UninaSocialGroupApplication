package oobd2324_38.uninasocialgroup;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
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
import java.util.ArrayList;

public class HomeController {
    @FXML private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    @FXML private TextField HomeSearchBar;
    @FXML private GridPane GridGroups;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void InitPage(String username) {
        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setIdUtente(utente.getIdByUsername());
        ArrayList<Gruppo> GruppiUtente = utente.getGruppi();

        UtenteLoggato.setText(username);
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumber.setText(String.valueOf(utente.getNotificationsNumber()));
            NotificationsNumber.setVisible(true);
        }
        int rows = 1;
        int cols = 0;

        try {
            if (GruppiUtente != null) {
                for (int i = 0; i < GruppiUtente.size(); i++) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("GroupTag.fxml"));
                    GridPane GroupBox = loader.load();
                    GruppoController controller = loader.getController();
                    controller.setUtenteLoggato(UtenteLoggato.getText());
                    controller.setIdGruppo(GruppiUtente.get(i).getIdGruppo());
                    controller.setNotificationsNumber(Integer.parseInt(NotificationsNumber.getText()));
                    controller.InitializeTag(GruppiUtente.get(i));
                    if (cols == 3) {
                        cols = 0;
                        rows++;
                    }
                    GridGroups.add(GroupBox, cols, rows);
                    GridPane.setMargin(GroupBox, new Insets(10, 10, 10, 10));
                    cols++;
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void LogOutOnClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToLoginScene();
    }

    public void OnNotificationButtonClick() {
        SceneController sc = new SceneController();
        sc.setGridGroups(this.GridGroups);
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
       sc.setHomeSearchBar(this.HomeSearchBar);
       sc.setNotificationsNumber(this.NotificationsNumber.getText());
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
}
