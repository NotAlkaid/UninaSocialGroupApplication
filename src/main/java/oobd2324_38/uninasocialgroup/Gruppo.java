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

    public static ArrayList<Gruppo> getNamedGroups(String titolo, int idUtente) {
        GruppoDao gruppoDao = new GruppoDao();
        return gruppoDao.getNamedGroups(titolo, idUtente);
    }

    public static ArrayList<Gruppo> getThemeGroups(String tema, int idUtente) {
        GruppoDao gruppoDao = new GruppoDao();
        return gruppoDao.getThemeGroups(tema, idUtente);
    }

    public boolean isGroupAlreadyRequested(Utente utente) {
        GruppoDao gruppoDao = new GruppoDao();
        return gruppoDao.isGroupAlreadyRequested(utente, this);
    }

    public Post getMostLikedPost(String mese) throws SQLException {
        GruppoDao gruppoDao = new GruppoDao();
        ArrayList<Post> groupPosts = gruppoDao.getPostsInMonth(this, mese);
        if(groupPosts != null) {
            if(!groupPosts.isEmpty()) {
                Post MaxPost = groupPosts.getFirst();
                for(int i = 0; i < groupPosts.size(); i++) {
                    for(int j = i+1; j < groupPosts.size(); j++) {
                        if(MaxPost.getLikes() < groupPosts.get(i).getLikes()) {
                            MaxPost = groupPosts.get(i);
                        }
                    }
                }
                if(MaxPost.getLikes() == 0) return null;
                return MaxPost;
            }
        }
        return null;
    }

    public Post getMostCommentedPost(String mese) throws SQLException {
        GruppoDao gruppoDao = new GruppoDao();
        ArrayList<Post> groupPosts = gruppoDao.getPostsInMonth(this, mese);
        if(groupPosts != null) {
            if(!groupPosts.isEmpty()) {
                Post MaxPost = groupPosts.getFirst();
                for(int i = 0; i < groupPosts.size(); i++) {
                    for(int j = i+1; j < groupPosts.size(); j++) {
                        if(MaxPost.getComments() < groupPosts.get(i).getComments()) {
                            MaxPost = groupPosts.get(i);
                        }
                    }
                }
                if(MaxPost.getComments() == 0) return null;
                return MaxPost;
            }
        }
        return null;
    }

    public int getMinimumSharedPostsNum(String mese) throws SQLException {
        GruppoDao gruppoDao = new GruppoDao();
        ArrayList<Post> groupPosts = gruppoDao.getPostsInMonth(this, mese);
        if(groupPosts != null) {return 1;}
        else {return 0;}
    }

    public int getAverageSharedPostsNum(String mese) throws SQLException {
        GruppoDao gruppoDao = new GruppoDao();
        int sum = 0;
        int counter = 0;
        ArrayList<Post> groupPosts = gruppoDao.getPostsInMonth(this, mese);
        if(groupPosts != null) {
            for(int i = 0; i < groupPosts.size(); i++) {
                sum ++;
                counter++;
            }
            return sum/counter;
        }
        else {return 0;}
    }
}
