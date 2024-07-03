package oobd2324_38.uninasocialgroup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("UninaSocialGroup");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        Image image = new Image("https://us.123rf.com/450wm/tpgraphic/tpgraphic1706/tpgraphic170600135/80557929-icona-uomo-in-cerchio-blu-su-sfondo-blu-logo-simboli-attivit%C3%A0-commerciale-incontro-capo.jpg");
        Main.stage = stage;
        Main.stage.getIcons().add(image);


    }

    public static void main(String[] args) {launch(args);}
}