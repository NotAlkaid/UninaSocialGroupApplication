package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GroupPageController {
    @FXML private Label NomeGruppoLabel;
    @FXML private Label TemiLabel;
    @FXML private Button GoBackButton;
    @FXML private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    @FXML private GridPane GridPosts;
    private String UtenteLoggato1;
    private int NotificationsNumber1;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initPage(int IdGruppo) throws IOException {
        Gruppo gruppo = new Gruppo();
        gruppo.setIdGruppo(IdGruppo);
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato1);
        utente.setIdUtente(utente.getIdByUsername());
        ArrayList<Gruppo> GruppiUtente = utente.getGruppi();
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
                    controller.initializePost(allPosts.get(i));
                    GridPosts.add(PostBox, 0, rows);
                    GridPane.setMargin(GridPosts, new Insets(10, 10, 10, 10));
                    rows++;
                    GridPosts.add(separator, 0, rows);
                    rows++;
            }
        }
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

    public void OnGoBackButtonClicked() throws IOException {
        SwitchToHomePageScene();
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
        if(!NotificationsPane.isVisible()) {
            Utente utente = new Utente();
            utente.setUsername(UtenteLoggato.getText());
            utente.setIdUtente(utente.getIdByUsername());
            ArrayList<String> NotificationsList = utente.getAllNotifications();
            if(NotificationsList != null) {
                NotificationsPane.setVisible(true);
                int rows = 1;
                for(int i = 0; i < NotificationsList.size(); i++) {
                    Label Notification = new Label();
                    Notification.setText(NotificationsList.get(i));
                    Separator separator = new Separator();
                    separator.setOrientation(Orientation.HORIZONTAL);
                    NotificationsGrid.add(Notification, 0, rows);
                    GridPane.setMargin(Notification, new Insets(10, 10, 10, 10));
                    rows++;
                    NotificationsGrid.add(separator, 0, rows);
                    rows++;
                }
            }
        } else {NotificationsPane.setVisible(false);}
    }
}
