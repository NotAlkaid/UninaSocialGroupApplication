package oobd2324_38.uninasocialgroup;

import java.time.LocalDate;

public class Gruppo {
    private int IdGruppo;
    private String Nome;
    private LocalDate DataCreazione;
    private Utente CreatoreGruppo;
    private String[] Temi;

    public Gruppo(int IdGruppo, String Nome, LocalDate DataCreazione, Utente CreatoreGruppo, String[] Temi) {
        this.IdGruppo = IdGruppo;
        this.Nome = Nome;
        this.DataCreazione = DataCreazione;
        this.CreatoreGruppo = CreatoreGruppo;
        this.Temi = Temi;
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

    public String[] getTemi() {
        return Temi;
    }

    public void setTemi(String[] temi) {
        Temi = temi;
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
}
