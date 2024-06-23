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
                    controller.setNotificationsNumber(Integer.parseInt(NotificationsNumber1));
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
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato.getText());
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
        controller.setUtenteLoggato(UtenteLoggato.getText());
        controller.setNotificationsNumber1(NotificationsNumber.getText());
        controller.setIdUtente(utente.getIdUtente());
        controller.InitPage(HomeSearchBar.getText(), utente.getIdUtente());
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
