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

    public void SwitchToLoginScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void LogOutOnClick() throws IOException {
        SwitchToLoginScene();
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

    public void SwitchToCreaScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Crea.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        CreaController controller = loader.getController();
        controller.setUtenteLoggato(UtenteLoggato.getText());
        controller.setNotificationsNumber(Integer.parseInt(NotificationsNumber.getText()));
        controller.InitPage();
    }

    public void OnCreaButtonClick() throws IOException {
        SwitchToCreaScene();
    }

    public void OnLensClicked() throws IOException {
       SceneController sc = new SceneController();
       sc.setUtenteLoggato(this.UtenteLoggato.getText());
       sc.setHomeSearchBar(this.HomeSearchBar);
       sc.setNotificationsNumber(this.NotificationsNumber.getText());
       sc.LensAction();
    }

    public void SwitchToRichiesteScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RichiesteAccesso.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        RichiesteAccessoController controller = loader.getController();
        controller.setUtenteLoggato1(UtenteLoggato.getText());
        controller.setNotificationsNumber1(Integer.parseInt(NotificationsNumber.getText()));
        controller.InitPage();
    }

    public void OnMenuRequestButtonClick() throws IOException {
        SwitchToRichiesteScene();
    }

    public void SwitchToReportScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReportPage.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        ReportController controller = loader.getController();
        controller.setUtenteLoggato1(UtenteLoggato.getText());
        controller.setNotificationsNumber(NotificationsNumber.getText());
        controller.initPage();
    }

    public void OnMenuReportButtonClick() throws IOException {
        SwitchToReportScene();
    }
}
