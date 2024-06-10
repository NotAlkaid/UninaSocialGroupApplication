package oobd2324_38.uninasocialgroup;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class PostController {
    @FXML private Label NomeUtenteLabel;
    @FXML private TextArea PostContentArea;
    @FXML private Label DateLabel;
    @FXML private Button LikeButton;
    @FXML private Button CommentButton;


    public void initializePost(Post post) {
        NomeUtenteLabel.setText(post.getAutore().getUsername());
        PostContentArea.setText(post.getTesto());
        DateLabel.setText(post.getDataPubblicazione().toString());
    }
}
