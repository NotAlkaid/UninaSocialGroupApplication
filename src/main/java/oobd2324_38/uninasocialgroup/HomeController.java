package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {
    @FXML private Label UtenteLoggato;
    @FXML private Label NotificationsNumber;
    @FXML private TextField HomeSearchBar;
    @FXML private GridPane GridGroups;

    public void InitPage(String username) {
        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setIdUtente(utente.getIdByUsername());
        ArrayList<Gruppo> GruppiUtente = utente.getGruppi();

        UtenteLoggato.setText(username);
        if(utente.getNotificationsNumber() != 0) {
            NotificationsNumber.setText(String.valueOf(utente.getNotificationsNumber()));
        } else {
            NotificationsNumber.setVisible(false);
        }
        int rows = 1;
        int cols = 0;

        try {
            for(int i = 0; i < GruppiUtente.size(); i++){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("GroupTag.fxml"));
                GridPane GroupBox = loader.load();
                GruppoController controller = loader.getController();
                controller.InitializeTag(GruppiUtente.get(i).getNome());
                System.out.println(GruppiUtente.get(i).getNome());
                if(cols == 3) {
                    cols = 0;
                    rows++;
                }
                GridGroups.add(GroupBox, cols, rows);
                GridPane.setMargin(GroupBox, new Insets(10, 10, 10, 10));
                cols++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
