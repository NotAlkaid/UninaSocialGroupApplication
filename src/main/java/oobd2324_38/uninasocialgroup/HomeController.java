package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {
    @FXML private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    @FXML private TextField HomeSearchBar;
    @FXML private GridPane GridGroups;
    @FXML private Button NomeGruppoButton;
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
                    controller.InitializeTag(GruppiUtente.get(i), UtenteLoggato.getText());
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
}
