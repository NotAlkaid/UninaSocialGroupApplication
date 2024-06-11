package oobd2324_38.uninasocialgroup;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

public class GruppoController {
    @FXML private FontAwesomeIcon TagIcon;
    @FXML private Label NomeGruppo;
    @FXML private Label TemiLabel;
    @FXML private Button NomeGruppoButton;
    private int NotificationsNumber;
    private String UtenteLoggato;
    private int IdGruppo;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void InitializeTag(Gruppo gruppo) {
        NomeGruppo.setText(gruppo.getNomeById(gruppo.getIdGruppo()));
        TagIcon.fillProperty().setValue(Paint.valueOf(GetRandomColor()));
        TemiLabel.setText(getTemi(gruppo));
    }

    private String GetRandomColor() {
        Random random = new Random();
        String color = "#";

        for (int i = 0; i < 6; i++) {
            color += Integer.toHexString(random.nextInt(16));
        }
        if(color.equals("#ffffff")) {
            color = "#000000";
        }
        return color;
    }

    private String getTemi(Gruppo gruppo) {
        return gruppo.GetTemi();
    }

    public void SwitchToGroupPageScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GroupPage.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        GroupPageController controller = loader.getController();
        controller.setUtenteLoggato1(UtenteLoggato);
        controller.setNotificationsNumber1(NotificationsNumber);
        controller.initPage(IdGruppo);
    }

    public void OnGroupTagClicked() throws IOException {
        SwitchToGroupPageScene();
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

    public int getIdGruppo() {
        return IdGruppo;
    }

    public void setIdGruppo(int idGruppo) {
        IdGruppo = idGruppo;
    }
}
