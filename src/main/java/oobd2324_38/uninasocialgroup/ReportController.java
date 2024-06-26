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
import java.util.ArrayList;

public class ReportController {
    private String UtenteLoggato1;
    @FXML private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    @FXML private GridPane GridGroups;
    @FXML private ScrollPane GroupsScrollPane;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    @FXML private TextField HomeSearchBar;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initPage() {
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato1);
        utente.setIdUtente(utente.getIdByUsername());
        ArrayList<Gruppo> GruppiUtente = utente.GetOwnedGroups();

        UtenteLoggato.setText(UtenteLoggato1);
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
                    controller.setSwitchFrom("FunctionalReport");
                    controller.InitializeTag(GruppiUtente.get(i));
                    if (cols == 3) {
                        cols = 0;
                        rows++;
                    }
                    GridGroups.add(GroupBox, cols, rows);
                    GridPane.setMargin(GroupBox, new Insets(10, 10, 10, 10));
                    cols++;
                }
            } else {
                GroupsScrollPane.setVisible(false);
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
        sc.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sc.setHomeSearchBar(this.HomeSearchBar);
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

    public void SwitchToHomePageScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        HomeController controller  = loader.getController();
        controller.InitPage(UtenteLoggato.getText());
    }

    public void OnHomeButtonClick() throws IOException {
        SwitchToHomePageScene();
    }

    public String getUtenteLoggato1() {
        return UtenteLoggato1;
    }

    public void setUtenteLoggato1(String utenteLoggato1) {
        UtenteLoggato1 = utenteLoggato1;
    }

    public Label getNotificationsNumber() {
        return NotificationsNumber;
    }

    public void setNotificationsNumber(String notificationsNumber) {
        NotificationsNumber.setText(notificationsNumber);
    }


}
