package oobd2324_38.uninasocialgroup;

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
import java.sql.SQLException;
import java.util.ArrayList;

public class CreaController {
    private String UtenteLoggato;
    private int NotificationsNumber;
    @FXML private Label UtenteLoggatoLabel;
    @FXML private Label NotificationsNumberLabel;
    @FXML private Button HomeButton;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    @FXML private TextField NomeGruppoField;
    @FXML private TextField TemaGruppoField;
    @FXML private Label TemaVuotoLabel;
    @FXML private Label NomeGruppoVuotoLabel;
    @FXML private Label NameEqualsThemeLabel;
    @FXML private TextField HomeSearchBar;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void InitPage() {
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato);
        utente.setIdUtente(utente.getIdByUsername());

        UtenteLoggatoLabel.setText(utente.getUsername());
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumberLabel.setText(Integer.toString(NotificationsNumber));
            NotificationsNumberLabel.setVisible(true);
        }
    }

    public String getUtenteLoggato() {
        return UtenteLoggato;
    }

    public void setUtenteLoggato(String utenteLoggato) {
        UtenteLoggato = utenteLoggato;
    }

    public int getNotificationsNumber() {
        return NotificationsNumber;
    }

    public void setNotificationsNumber(int notificationsNumber) {
        NotificationsNumber = notificationsNumber;
    }

    public void SwitchToHomeScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        stage = Main.stage;
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        HomeController controller = loader.getController();
        controller.InitPage(UtenteLoggato);
    }

    public void OnHomeButtonClick() throws IOException {
        SwitchToHomeScene();
    }

    public void OnNotificationButtonClick() {
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

    public void OnCreaButtonClick() {
        if(NomeGruppoField.getText().isEmpty()) {
            NomeGruppoVuotoLabel.setVisible(true);
            TemaVuotoLabel.setVisible(false);
            NameEqualsThemeLabel.setVisible(false);
        }else if(TemaGruppoField.getText().isEmpty()) {
            TemaVuotoLabel.setVisible(true);
            NomeGruppoVuotoLabel.setVisible(false);
            NameEqualsThemeLabel.setVisible(false);
        }else if(NomeGruppoField.getText().equals(TemaGruppoField.getText())) {
            TemaVuotoLabel.setVisible(false);
            NomeGruppoVuotoLabel.setVisible(false);
            NameEqualsThemeLabel.setVisible(true);
        }
        else {
           Gruppo gruppo = new Gruppo();
           Utente utente = new Utente();

           gruppo.setNome(NomeGruppoField.getText());
           gruppo.setTema(TemaGruppoField.getText());
           utente.setUsername(UtenteLoggato);
           utente.setIdUtente(utente.getIdByUsername());
           gruppo.setCreatoreGruppo(utente);
           try {
               gruppo.insertGroup();
               SwitchToHomeScene();
           }catch (SQLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        }
    }

    public void OnLensClicked() throws IOException {
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
        controller.setNotificationsNumber1(String.valueOf(NotificationsNumber));
        controller.setIdUtente(utente.getIdUtente());
        controller.InitPage(HomeSearchBar.getText(), utente.getIdUtente());
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
        controller.setUtenteLoggato1(UtenteLoggato);
        controller.setNotificationsNumber1(NotificationsNumber);
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
        controller.setUtenteLoggato1(UtenteLoggato);
        controller.setNotificationsNumber(String.valueOf(NotificationsNumber));
        controller.initPage();
    }

    public void OnMenuReportButtonClick() throws IOException {
        SwitchToReportScene();
    }

}
