package oobd2324_38.uninasocialgroup;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Post {
    private int idPost;
    private LocalDate DataPubblicazione;
    private LocalTime OraPubblicazione;
    private String Testo;
    private Utente Autore;
    private Gruppo inGruppo;

    public Post(int IdPost, LocalDate DataPubblicazione, LocalTime OraPubblicazione, String Testo) {
        this.idPost = IdPost;
        this.DataPubblicazione = DataPubblicazione;
        this.OraPubblicazione = OraPubblicazione;
        this.Testo = Testo;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public LocalDate getDataPubblicazione() {
        return DataPubblicazione;
    }

    public void setDataPubblicazione(LocalDate dataPubblicazione) {
        DataPubblicazione = dataPubblicazione;
    }

    public LocalTime getOraPubblicazione() {
        return OraPubblicazione;
    }

    public void setOraPubblicazione(LocalTime oraPubblicazione) {
        OraPubblicazione = oraPubblicazione;
    }

    public String getTesto() {
        return Testo;
    }

    public void setTesto(String testo) {
        Testo = testo;
    }

    public Utente getAutore() {
        return Autore;
    }

    public void setAutore(Utente autore) {
        Autore = autore;
    }

    public Gruppo getInGruppo() {
        return inGruppo;
    }

    public void setInGruppo(Gruppo inGruppo) {
        this.inGruppo = inGruppo;
    }
}
