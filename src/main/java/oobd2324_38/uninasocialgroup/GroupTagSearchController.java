package oobd2324_38.uninasocialgroup;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.sql.SQLException;

public class GroupTagSearchController {
    @FXML
    private FontAwesomeIcon TagIcon;
    @FXML private Label NomeGruppo;
    @FXML private Label TemiLabel;
    @FXML private Button NomeGruppoButton;
    @FXML private GridPane GroupGrid;
    private int NotificationsNumber;
    private String UtenteLoggato;
    private int IdGruppo;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void InitializeTag(Gruppo gruppo, Utente utente) throws SQLException {
        NomeGruppo.setText(gruppo.getNomeById(gruppo.getIdGruppo()));
        TemiLabel.setText(getTemi(gruppo));
        if(gruppo.isGroupAlreadyRequested(utente)) {
            GroupGrid.setBackground(Background.fill(Paint.valueOf("#00ff00")));
        }else{
            GroupGrid.setBackground(Background.fill(Paint.valueOf("#ffffff")));
        }
    }

    private String getTemi(Gruppo gruppo) {
        return gruppo.GetTemi();
    }

    public Label getNomeGruppo() {
        return NomeGruppo;
    }

    public void setNomeGruppo(Label nomeGruppo) {
        NomeGruppo = nomeGruppo;
    }

    public Label getTemiLabel() {
        return TemiLabel;
    }

    public void setTemiLabel(Label temiLabel) {
        TemiLabel = temiLabel;
    }

    public int getNotificationsNumber() {
        return NotificationsNumber;
    }

    public void setNotificationsNumber(int notificationsNumber) {
        NotificationsNumber = notificationsNumber;
    }

    public String getUtenteLoggato() {
        return UtenteLoggato;
    }

    public void setUtenteLoggato(String utenteLoggato) {
        UtenteLoggato = utenteLoggato;
    }

    public int getIdGruppo() {
        return IdGruppo;
    }

    public void setIdGruppo(int idGruppo) {
        IdGruppo = idGruppo;
    }

    public void OnGroupTagClick() {
        Gruppo gruppo = new Gruppo();
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato);
        utente.setIdUtente(utente.getIdByUsername());
        gruppo.setIdGruppo(IdGruppo);
        gruppo.setNome(NomeGruppo.getText());

        if(!gruppo.isGroupAlreadyRequested(utente)) {
            Richiesta richiesta = new Richiesta(false, utente, gruppo);

            if(richiesta.insertRequest()) System.out.println("si");
            GroupGrid.setBackground(Background.fill(Paint.valueOf("#00ff00")));
        }
    }
}
