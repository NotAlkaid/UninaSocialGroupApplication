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

public class SceneController {
    private String UtenteLoggato;
    private String NotificationsNumber;
    @FXML private TextField HomeSearchBar;
    @FXML private GridPane GridGroups;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void NotificationAction() {
        if(!NotificationsPane.isVisible()) {
            Utente utente = new Utente();
            utente.setUsername(UtenteLoggato);
            utente.setIdUtente(utente.getIdByUsername());
            ArrayList<String> NotificationsList = utente.getAllNotifications();
            if(NotificationsList != null) {
                NotificationsPane.setVisible(true);
                int rows = 1;
                for(int i = 0; i < NotificationsList.size(); i++) {
                    Label Notification = new Label();
                    Notification.setPrefWidth(1300);
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

    public void LensAction() throws IOException {
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato);
        utente.setIdUtente(utente.getIdByUsername());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchGroupPage.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        SearchGroupController controller = loader.getController();
        controller.setUtenteLoggato(UtenteLoggato);
        controller.setNotificationsNumber1(NotificationsNumber);
        controller.setIdUtente(utente.getIdUtente());
        controller.InitPage(HomeSearchBar.getText(), utente.getIdUtente());
    }

    public TextField getHomeSearchBar() {
        return HomeSearchBar;
    }

    public void setHomeSearchBar(TextField homeSearchBar) {
        HomeSearchBar = homeSearchBar;
    }

    public GridPane getGridGroups() {
        return GridGroups;
    }

    public void setGridGroups(GridPane gridGroups) {
        GridGroups = gridGroups;
    }

    public ScrollPane getNotificationsPane() {
        return NotificationsPane;
    }

    public void setNotificationsPane(ScrollPane notificationsPane) {
        NotificationsPane = notificationsPane;
    }

    public GridPane getNotificationsGrid() {
        return NotificationsGrid;
    }

    public void setNotificationsGrid(GridPane notificationsGrid) {
        NotificationsGrid = notificationsGrid;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setUtenteLoggato(String utenteLoggato) {
        UtenteLoggato = utenteLoggato;
    }

    public void setNotificationsNumber(String notificationsNumber) {
        NotificationsNumber = notificationsNumber;
    }
}
