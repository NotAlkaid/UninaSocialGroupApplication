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
import java.util.HashSet;
import java.util.Set;

public class SearchGroupController {
    @FXML private GridPane GridGroups;
    @FXML private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    @FXML private TextField HomeSearchBar;
    private String UtenteLoggato1;
    private String NotificationsNumber1;
    private int idUtente;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void InitPage(String titolo, int idUtente) {
        int cols = 0;
        int rows = 1;
        ArrayList<Gruppo> gruppi = Gruppo.getNamedGroups(titolo, idUtente);
        gruppi.addAll(Gruppo.getThemeGroups(titolo, idUtente));
        gruppi = removeDuplicatesGroups(gruppi);
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato1);
        utente.setIdUtente(utente.getIdByUsername());

        NotificationsNumber.setVisible(false);
        UtenteLoggato.setText(UtenteLoggato1);
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumber.setText(String.valueOf(utente.getNotificationsNumber()));
            NotificationsNumber.setVisible(true);
        }
        try {
            if (gruppi != null) {
                for (int i = 0; i < gruppi.size(); i++) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("GroupTagSearch.fxml"));
                    GridPane GroupBox = loader.load();
                    GroupTagSearchController controller = loader.getController();
                    controller.setUtenteLoggato(UtenteLoggato1);
                    controller.setIdGruppo(gruppi.get(i).getIdGruppo());
                    controller.InitializeTag(gruppi.get(i), utente);
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUtenteLoggato() {
        return UtenteLoggato1;
    }

    public void setUtenteLoggato(String utenteLoggato) {
        UtenteLoggato1 = utenteLoggato;
    }

    public String getNotificationsNumber1() {
        return NotificationsNumber1;
    }

    public void setNotificationsNumber1(String notificationsNumber1) {
        NotificationsNumber1 = notificationsNumber1;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public ArrayList<Gruppo> removeDuplicatesGroups(ArrayList<Gruppo> gruppi) {
        for (int i = 0; i < gruppi.size(); i++) {
            for (int j = i + 1; j < gruppi.size(); j++) {
                if(gruppi.get(i).getNome().equals(gruppi.get(j).getNome())) {
                    gruppi.remove(j);
                }
            }
        }
        return gruppi;
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
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(this.NotificationsNumber1);
        sceneController.SwitchToCreaScene();
    }

    public void OnLensClicked() throws IOException {
        SceneController sc = new SceneController();
        sc.setUtenteLoggato(this.UtenteLoggato.getText());
        sc.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sc.setHomeSearchBar(this.HomeSearchBar);
        sc.LensAction();
    }

    public void OnHomeButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.SwitchToHomeScene();
    }

    public void OnMenuRequestButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(this.NotificationsNumber1);
        sceneController.SwitchToRichiesteScene();
    }

    public void OnMenuReportButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(this.NotificationsNumber1);
        sceneController.SwitchToReportScene();
    }
}
