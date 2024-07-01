package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GroupPageController {
    @FXML private Label NomeGruppoLabel;
    @FXML private Label TemiLabel;
    @FXML private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    @FXML private GridPane GridPosts;
    @FXML private TextField HomeSearchBar;
    private int idGruppo;
    private String UtenteLoggato1;
    private int NotificationsNumber1;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initPage() throws IOException, SQLException {
        Gruppo gruppo = new Gruppo();
        gruppo.setIdGruppo(idGruppo);
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato1);
        utente.setIdUtente(utente.getIdByUsername());
        ArrayList<Post> allPosts = gruppo.getAllPosts();

        NomeGruppoLabel.setText(gruppo.getNomeById(gruppo.getIdGruppo()));
        TemiLabel.setText(gruppo.GetTemi());

        UtenteLoggato.setText(UtenteLoggato1);
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumber.setText(Integer.toString(NotificationsNumber1));
            NotificationsNumber.setVisible(true);
        }
        if(allPosts != null) {
            int rows = 1;
            for(int i = 0; i < allPosts.size(); i++) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("PostTag.fxml"));
                    GridPane PostBox = loader.load();
                    Separator separator = new Separator();
                    separator.setOrientation(Orientation.HORIZONTAL);
                    PostController controller = loader.getController();
                    controller.setLikeNumber(allPosts.get(i).getLikes());
                    controller.setUtenteloggato(UtenteLoggato1);
                    controller.setCommentNumber(allPosts.get(i).getComments());
                    controller.setIdGruppo(idGruppo);
                    controller.setNotificationsNumber(NotificationsNumber1);
                    controller.initializePost(allPosts.get(i));
                    GridPosts.add(PostBox, 0, rows);
                    GridPane.setMargin(GridPosts, new Insets(10, 10, 10, 10));
                    rows++;
                    GridPosts.add(separator, 0, rows);
                    rows++;
            }
        }
    }

    public void OnGoBackButtonClicked() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.SwitchToHomeScene();
    }

    public String getUtenteLoggato1() {
        return UtenteLoggato1;
    }

    public void setUtenteLoggato1(String utenteLoggato1) {
        UtenteLoggato1 = utenteLoggato1;
    }

    public int getNotificationsNumber1() {
        return NotificationsNumber1;
    }

    public void setNotificationsNumber1(int notificationsNumber1) {
        NotificationsNumber1 = notificationsNumber1;
    }

    public int getIdGruppo() {
        return idGruppo;
    }

    public void setIdGruppo(int idGruppo) {
        this.idGruppo = idGruppo;
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

    public void OnCreaPostButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber1));
        sceneController.SwitchToCreaPost();
    }

    public void OnHomeButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.SwitchToHomeScene();
    }

    public void OnCreaButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber1));
        sceneController.SwitchToCreaScene();
    }

    public void OnMenuRequestButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber1));
        sceneController.SwitchToRichiesteScene();
    }

    public void OnMenuReportButtonClick() throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.setUtenteLoggato(this.UtenteLoggato1);
        sceneController.setNotificationsNumber(String.valueOf(this.NotificationsNumber1));
        sceneController.SwitchToReportScene();
    }

    public void OnLensClicked() throws IOException {
        SceneController sc = new SceneController();
        sc.setUtenteLoggato(this.UtenteLoggato.getText());
        sc.setNotificationsNumber(String.valueOf(this.NotificationsNumber));
        sc.setHomeSearchBar(this.HomeSearchBar);
        sc.LensAction();
    }
}
