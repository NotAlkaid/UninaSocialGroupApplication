package oobd2324_38.uninasocialgroup;

import java.time.LocalDate;
import java.time.LocalTime;

public class Richiesta {
    private int idRichiesta;
    private LocalDate DataInvio;
    private LocalTime OraInvio;
    private boolean Esito;
    private Utente Utente;
    private Gruppo Gruppo;

    public Richiesta(boolean Esito, Utente Utente, Gruppo Gruppo, int idRichiesta) {
        this.Esito = Esito;
        this.Utente = Utente;
        this.Gruppo = Gruppo;
        this.idRichiesta = idRichiesta;
    }

    public Richiesta(boolean Esito, Utente Utente, Gruppo Gruppo) {
        this.Esito = Esito;
        this.Utente = Utente;
        this.Gruppo = Gruppo;
    }

    public oobd2324_38.uninasocialgroup.Utente getUtente() {
        return Utente;
    }

    public void setUtente(oobd2324_38.uninasocialgroup.Utente utente) {
        Utente = utente;
    }

    public oobd2324_38.uninasocialgroup.Gruppo getGruppo() {
        return Gruppo;
    }

    public void setGruppo(oobd2324_38.uninasocialgroup.Gruppo gruppo) {
        Gruppo = gruppo;
    }

    public boolean isEsito() {
        return Esito;
    }

    public void setEsito(boolean esito) {
        Esito = esito;
    }

    public int getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(int idRichiesta) {
        this.idRichiesta = idRichiesta;
    }

    public boolean insertRequest() {
        RichiestaDao richiestaDao = new RichiestaDao();
        return richiestaDao.insertRichiesta(this);
    }

    public void accettaRichiesta() {
        RichiestaDao richiestaDao = new RichiestaDao();
        richiestaDao.AccettaRichiesta(this);
    }

    public void rifiutaRichiesta() {
        RichiestaDao richiestaDao = new RichiestaDao();
        richiestaDao.RifiutaRichiesta(this);
    }
}
