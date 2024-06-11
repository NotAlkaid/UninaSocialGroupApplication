package oobd2324_38.uninasocialgroup;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Gruppo {
    private int IdGruppo;
    private String Nome;
    private LocalDate DataCreazione;
    private Utente CreatoreGruppo;
    private String Tema;

    public Gruppo(int IdGruppo, String Nome, LocalDate DataCreazione, Utente CreatoreGruppo, String Tema) {
        this.IdGruppo = IdGruppo;
        this.Nome = Nome;
        this.DataCreazione = DataCreazione;
        this.CreatoreGruppo = CreatoreGruppo;
        this.Tema = Tema;
    }

    public Gruppo(){}

    public int getIdGruppo() {
        return IdGruppo;
    }

    public void setIdGruppo(int idGruppo) {
        IdGruppo = idGruppo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public LocalDate getDataCreazione() {
        return DataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        DataCreazione = dataCreazione;
    }

    public Utente getCreatoreGruppo() {
        return CreatoreGruppo;
    }

    public void setCreatoreGruppo(Utente creatoreGruppo) {
        CreatoreGruppo = creatoreGruppo;
    }

    public String getTema() {
        return Tema;
    }

    public void setTema(String tema) {
        Tema = tema;
    }

    public String getNomeById(int idGruppo) {
        GruppoDao gruppoDao = new GruppoDao();
        String nome = gruppoDao.getNomeById(idGruppo);

        return nome;
    }

    public String GetTemi() {
        GruppoDao gruppoDao = new GruppoDao();
        return gruppoDao.getTemi(this);
    }

    public ArrayList<Post> getAllPosts() {
        GruppoDao gruppoDao = new GruppoDao();
        return gruppoDao.getPosts(this);
    }

    public void insertGroup() throws SQLException {
        GruppoDao gruppoDao = new GruppoDao();
        gruppoDao.insertGruppo(this);
    }
}
