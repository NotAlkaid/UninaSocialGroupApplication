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

public class SceneController {
    private String UtenteLoggato;
    private String NotificationsNumber;
    @FXML private TextField HomeSearchBar;
    @FXML private GridPane GridGroups;
    @FXML private ScrollPane NotificationsPane;
    @FXML private GridPane NotificationsGrid;
    @FXML private TextField setUsernameField;
    @FXML private TextField SignInUsernameField;
    @FXML private TextField SignInPwdField;
    @FXML private Label NomeGruppo;
    private Gruppo gruppo;
    private int idGruppo;
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

    public void SwitchToSignInScene() throws IOException {
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = Main.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
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

    public void SwitchToLoginScene() throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = Main.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void SwitchToLoginSceneWithCreds() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        root = loader.load();
        stage = Main.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        LoginController controller = loader.getController();
        controller.setLoginCredentialsAfterSignIn(SignInUsernameField.getText(), SignInPwdField.getText());
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
        controller.setUtenteLoggato(UtenteLoggato);
        controller.setNotificationsNumber(NotificationsNumber);
        controller.InitPage();
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
        controller.setNotificationsNumber(NotificationsNumber);
        controller.initPage();
    }

    public void SwitchToGroupPageScene() throws IOException, SQLException {
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
        controller.setNotificationsNumber1(Integer.parseInt(NotificationsNumber));
        controller.setIdGruppo(idGruppo);
        controller.initPage();
    }

    public void SwitchToCreaPost() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CreaPost.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        CreaPostController controller = loader.getController();
        controller.setUtenteLoggato(UtenteLoggato);
        controller.setNotificationsNumber(Integer.parseInt(NotificationsNumber));
        controller.setIdGruppo(idGruppo);
        controller.initPage();
    }

    private String getMonthFormat(String mese) {
        switch (mese) {
            case "Gennaio":
                return "01";
            case "Febbraio":
                return "02";
            case "Marzo":
                return "03";
            case "Aprile":
                return "04";
            case "Maggio":
                return "05";
            case "Giugno":
                return "06";
            case "Luglio":
                return "07";
            case "Agosto":
                return "08";
            case "Settembre":
                return "09";
            case "Ottobre":
                return "10";
            case "Novembre":
                return "11";
            case "Dicembre":
                return "12";
        }
        return "";
    }

    public void SwitchToGroupStatsPageScene(String mese) throws IOException, SQLException {
        Utente utente = new Utente();
        utente.setUsername(UtenteLoggato);
        utente.setIdUtente(utente.getIdByUsername());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GroupStatsPage.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        GroupStatsController controller = loader.getController();
        controller.setUtenteLoggato1(UtenteLoggato);
        controller.setNotificationsNumber1(NotificationsNumber);
        controller.setMostLiked(gruppo.getMostLikedPost(getMonthFormat(mese)));
        controller.setMostCommented(gruppo.getMostCommentedPost(getMonthFormat(mese)));
        controller.setLessLiked(gruppo.getLessLikedPost(getMonthFormat(mese)));
        controller.setLessCommented(gruppo.getLessCommentedPost(getMonthFormat(mese)));
        controller.setAvgPostNum(Gruppo.getAverageSharedPosts(utente, getMonthFormat(mese)));
        controller.initPage(mese);
    }

    public void SwitchToFunctionalReportScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FunctionalReport.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = Main.stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        FunctionalReportController controller = loader.getController();
        controller.setUtenteLoggato1(UtenteLoggato);
        controller.setNotificationsNumber1(String.valueOf(NotificationsNumber));
        Gruppo gruppo = new Gruppo();
        gruppo.setIdGruppo(idGruppo);
        gruppo.setNome(NomeGruppo.getText());
        controller.setGruppo(gruppo);
        controller.initPage();
    }

    public void RefreshPage() throws IOException {
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

    public TextField getSignInUsernameField() {return SignInUsernameField;}

    public void setSignInUsernameField(TextField signInUsernameField) {SignInUsernameField = signInUsernameField;}

    public TextField getSignInPwdField() {return SignInPwdField;}

    public void setSignInPwdField(TextField signInPwdField) {SignInPwdField = signInPwdField;}

    public String getUtenteLoggato() {
        return UtenteLoggato;
    }

    public String getNotificationsNumber() {
        return NotificationsNumber;
    }

    public TextField getSetUsernameField() {
        return setUsernameField;
    }

    public void setSetUsernameField(TextField setUsernameField) {
        this.setUsernameField = setUsernameField;
    }

    public int getIdGruppo() {
        return idGruppo;
    }

    public void setIdGruppo(int idGruppo) {
        this.idGruppo = idGruppo;
    }

    public Label getNomeGruppo() {
        return NomeGruppo;
    }

    public void setNomeGruppo(Label nomeGruppo) {
        NomeGruppo = nomeGruppo;
    }

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
    }
}
