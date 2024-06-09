package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GroupPageController {
    @FXML private Label NomeGruppoLabel;
    @FXML private Label TemiLabel;
    @FXML private Button GoBackButton;
    @FXML private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    private String UtenteLoggato1;
    private int NotificationsNumber1;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initPage(int IdGruppo) {
        Gruppo gruppo = new Gruppo();
        gruppo.setIdGruppo(IdGruppo);
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato1);
        utente.setIdUtente(utente.getIdByUsername());
        ArrayList<Gruppo> GruppiUtente = utente.getGruppi();

        NomeGruppoLabel.setText(gruppo.getNomeById(gruppo.getIdGruppo()));
        TemiLabel.setText(gruppo.GetTemi());

        UtenteLoggato.setText(UtenteLoggato1);
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumber.setText(Integer.toString(NotificationsNumber1));
            NotificationsNumber.setVisible(true);
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
}
