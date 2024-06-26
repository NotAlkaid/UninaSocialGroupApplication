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

    public void SwitchToGroupStatsPageScene(String mese) throws IOException, SQLException {
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato1);
        utente.setIdUtente(utente.getIdByUsername());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GroupStatsPage.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        GroupStatsController controller = loader.getController();
        controller.setUtenteLoggato1(UtenteLoggato1);
        controller.setNotificationsNumber1(NotificationsNumber1);
        controller.setMostLiked(gruppo.getMostLikedPost(getMonthFormat(mese)));
        controller.setMostCommented(gruppo.getMostCommentedPost(getMonthFormat(mese)));
        controller.setLessLiked(gruppo.getLessLikedPost(getMonthFormat(mese)));
        controller.setLessCommented(gruppo.getLessCommentedPost(getMonthFormat(mese)));
        controller.setAvgPostNum(Gruppo.getAverageSharedPosts(utente, getMonthFormat(mese)));
        controller.initPage(mese);
    }

    public void OnJanuaryButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Gennaio");
    }

    public void OnFebruaryButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Febbraio");
    }

    public void OnMarchButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Marzo");
    }

    public void OnAprilButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Aprile");
    }

    public void OnMayButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Maggio");
    }

    public void OnJuneButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Giugno");
    }

    public void OnJulyButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Luglio");
    }

    public void OnAugustButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Agosto");
    }

    public void OnSeptemberButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Settembre");
    }

    public void OnOctoberButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Ottobre");
    }

    public void OnNovemberButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Novembre");
    }

    public void OnDecemberButtonClick() throws IOException, SQLException {
        SwitchToGroupStatsPageScene("Dicembre");
    }

    private String getMonthFormat(String mese) {
        switch (mese) {
            case "Gennaio":
                return "01";
            case "Febbraio":
                return "02";
            case "Marzo":
                return "03";
            case "Aprile":
                return "04";
            case "Maggio":
                return "05";
            case "Giugno":
                return "06";
            case "Luglio":
                return "07";
            case "Agosto":
                return "08";
            case "Settembre":
                return "09";
            case "Ottobre":
                return "10";
            case "Novembre":
                return "11";
            case "Dicembre":
                return "12";
        }
        return "";
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
