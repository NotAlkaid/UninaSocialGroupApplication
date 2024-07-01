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

public class GroupStatsController {
    private String UtenteLoggato1;
    private String NotificationsNumber1;
    @FXML
    private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    @FXML private TextField HomeSearchBar;
    @FXML private Label MeseLabel;
    @FXML private Label MostLikedLabel;
    @FXML private Label MostCommentedLabel;
    @FXML private Label LessLikedLabel;
    @FXML private Label LessCommentedLabel;
    @FXML private Label AvgPostLabel;
    private int AvgPostNum;
    private Post MostLiked;
    private Post MostCommented;
    private Post LessLiked;
    private Post LessCommented;
    private Stage stage;
    private Parent root;
    private Scene scene;


    public void initPage(String mese) {
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato1);
        utente.setIdUtente(utente.getIdByUsername());

        UtenteLoggato.setText(UtenteLoggato1);
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumber.setText(String.valueOf(utente.getNotificationsNumber()));
            NotificationsNumber.setVisible(true);
        }
        MeseLabel.setText("Nel mese di " + mese + ":");
        if(MostLiked != null) {
            MostLikedLabel.setText(" " + MostLiked.getAutore().getUsername() + ": " + MostLiked.getTesto());
        } else {
            MostLikedLabel.setText(" Non ci sono post che hanno avuto likes");
        }
        if(MostCommented != null) {
            MostCommentedLabel.setText(" " + MostCommented.getAutore().getUsername() + ": " + MostCommented.getTesto());
        } else {
            MostCommentedLabel.setText(" Non ci sono post che hanno avuto commenti");
        }
        if(LessLiked != null) {
            LessLikedLabel.setText(" " + LessLiked.getAutore().getUsername() + ": " + LessLiked.getTesto());
        } else {
            LessLikedLabel.setText(" Non ci sono post che hanno avuto likes");
        }
        if(LessCommented != null) {
            LessCommentedLabel.setText(" " + LessCommented.getAutore().getUsername() + ": " + LessCommented.getTesto());
        } else {
            LessCommentedLabel.setText(" Non ci sono post che hanno avuto commenti");
        }
        AvgPostLabel.setText(" " + AvgPostNum);
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
        sceneController.SwitchToHomeScene();
    }

    public void OnCreaButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(this.NotificationsNumber1);
        sceneController.SwitchToCreaScene();
    }

    public void OnMenuRequestButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(this.NotificationsNumber1);
        sceneController.SwitchToRichiesteScene();
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

    public void LogOutOnClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.SwitchToLoginScene();
    }

    public void OnMenuReportButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(this.NotificationsNumber1);
        sceneController.SwitchToReportScene();
    }

    public Post getMostLiked() {
        return MostLiked;
    }

    public void setMostLiked(Post mostLiked) {
        MostLiked = mostLiked;
    }

    public void setMostCommented(Post mostCommented) {
        MostCommented = mostCommented;
    }

    public int getAvgPostNum() {
        return AvgPostNum;
    }

    public void setAvgPostNum(int avgPostNum) {
        AvgPostNum = avgPostNum;
    }

    public Post getLessLiked() {
        return LessLiked;
    }

    public void setLessLiked(Post lessLiked) {
        LessLiked = lessLiked;
    }

    public Post getLessCommented() {
        return LessCommented;
    }

    public void setLessCommented(Post lessCommented) {
        LessCommented = lessCommented;
    }
}
