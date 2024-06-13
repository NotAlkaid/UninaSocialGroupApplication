package oobd2324_38.uninasocialgroup;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class PostController {
    @FXML private Label NomeUtenteLabel;
    @FXML private TextArea PostContentArea;
    @FXML private Label DateLabel;
    @FXML private Button LikeButton;
    @FXML private Button CommentButton;
    @FXML private FontAwesomeIcon FontLike;
    @FXML private FontAwesomeIcon FontComment;
    private String Utenteloggato;
    private Utente utente = new Utente();
    private Post post1 = new Post();
    private int idGruppo;
    private int NotificationsNumber;
    private int LikeNumber;
    private int CommentNumber;
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void initializePost(Post post) throws SQLException {
        utente.setUsername(Utenteloggato);
        utente.setIdUtente(utente.getIdByUsername());
        post1 = post;
        NomeUtenteLabel.setText(post.getAutore().getUsername());
        PostContentArea.setText(post.getTesto());
        DateLabel.setText(post.getDataPubblicazione().toString());
        LikeButton.setText(String.valueOf(LikeNumber));
        CommentButton.setText(String.valueOf(CommentNumber));
        if(post.isPostLikedByUser(utente)){FontLike.setFill(Paint.valueOf("#ff0000"));}
        else{FontLike.setFill(Paint.valueOf("#000000"));}
    }

    public void onLikeButtonClick() throws SQLException, IOException {
        if(post1.isPostLikedByUser(utente)) {
            FontLike.setFill(Paint.valueOf("#000000"));
            LikeNumber--;
            Like.removeLike(post1, utente);
        }else{
            Like.putLike(post1, utente);
            LikeNumber++;
            FontLike.setFill(Paint.valueOf("#ff0000"));
        }
        Refresh();
    }

    public void onCommentButtonClick() {

    }

    public int getLikeNumber() {
        return LikeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        LikeNumber = likeNumber;
    }

    public int getCommentNumber() {
        return CommentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        CommentNumber = commentNumber;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public int getIdGruppo() {
        return idGruppo;
    }

    public void setIdGruppo(int idGruppo) {
        this.idGruppo = idGruppo;
    }

    public int getNotificationsNumber() {
        return NotificationsNumber;
    }

    public void setNotificationsNumber(int notificationsNumber) {
        NotificationsNumber = notificationsNumber;
    }

    public String getUtenteloggato() {
        return Utenteloggato;
    }

    public void setUtenteloggato(String utenteloggato) {
        Utenteloggato = utenteloggato;
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
        controller.setUtenteLoggato1(this.Utenteloggato);
        controller.setNotificationsNumber1(this.NotificationsNumber);
        controller.setIdGruppo(this.idGruppo);
        controller.initPage();
    }

    public void Refresh() throws SQLException, IOException {
        SwitchToGroupPageScene();
    }
}
