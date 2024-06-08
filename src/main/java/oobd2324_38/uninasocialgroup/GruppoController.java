package oobd2324_38.uninasocialgroup;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import java.util.Random;
import java.util.regex.Pattern;

public class GruppoController {
    @FXML private FontAwesomeIcon TagIcon;
    @FXML private Label NomeGruppo;

    public void InitializeTag(String nomeGruppo) {
        NomeGruppo.setText(nomeGruppo);
        TagIcon.fillProperty().setValue(Paint.valueOf(GetRandomColor()));
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
}
