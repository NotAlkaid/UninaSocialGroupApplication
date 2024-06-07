package oobd2324_38.uninasocialgroup;

import org.postgresql.util.PSQLException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Utente {
    private int IdUtente;
    private String Username;
    private String Nome;
    private String Cognome;
    private String Password;
    private String NumeroTelefono;
    private String Biografia;
    private Sesso Sesso;
    private LocalDate DataNascita;

    public Utente(String Username, String Password, String Nome, String Cognome,
                  String NumeroTelefono, String Biografia, LocalDate DataNascita, Sesso Sesso) {
        this.Username = Username;
        this.Password = Password;
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.NumeroTelefono = NumeroTelefono;
        this.Biografia = Biografia;
        this.DataNascita = DataNascita;
        this.Sesso = Sesso;
    }

    public Utente(){}

    public int getIdUtente() {
        return IdUtente;
    }

    public LocalDate getDataNascita() {
        return DataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        DataNascita = dataNascita;
    }

    public void setIdUtente(int idUtente) {
        IdUtente = idUtente;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNumeroTelefono() {
        if(Objects.equals(this.NumeroTelefono, "")){return null;}
        return NumeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        if(numeroTelefono == null){NumeroTelefono = "";}
        else if(numeroTelefono.isEmpty()) NumeroTelefono = null;
        else NumeroTelefono = numeroTelefono;
    }

    public String getBiografia() {
        if(Objects.equals(this.Biografia, "")){return null;}
        return Biografia;
    }

    public void setBiografia(String biografia) {
        if(Biografia == null){Biografia = "";}
        else if(biografia.isEmpty()) {
            Biografia = null;
        }else{
            Biografia = biografia;
        }
    }

    public Sesso getSesso() {
        return Sesso;
    }

    public void setSesso(Sesso sesso) {
        Sesso = sesso;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "Username='" + Username + '\'' +
                ", Nome='" + Nome + '\'' +
                ", Cognome='" + Cognome + '\'' +
                ", Password='" + Password + '\'' +
                ", NumeroTelefono='" + NumeroTelefono + '\'' +
                ", Biografia='" + Biografia + '\'' +
                ", Sesso=" + Sesso +
                '}';
    }

    public Utente LoginVerification(String username, String password) {
        UtenteDao utenteDao = new UtenteDao();

        Utente utente = utenteDao.LogInVerification(username, password);
        if(utente != null) return utente;
        return null;
    }

    public boolean InsertnewUser() throws SQLException {
        UtenteDao utenteDao = new UtenteDao();

        if(utenteDao.InsertNewUtente(this)) return true;
        return false;
    }

    public int getNotificationsNumber() {
        UtenteDao utenteDao = new UtenteDao();
        ArrayList<String> ausiliar = utenteDao.getAllNotifications(this);
        return ausiliar.size();
    }

    public int getIdByUsername() {
        UtenteDao utenteDao = new UtenteDao();

        if(utenteDao.getIdByUsername(this) != -1) return utenteDao.getIdByUsername(this);
        return -1;
    }
}
