package oobd2324_38.uninasocialgroup;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDao {
    public Utente LogInVerification(String username, String password) {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".\"UTENTE\" where \"Username\" = ? and \"Password\" = ?";
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente utente = new Utente();
                utente.setIdUtente(rs.getInt("ID_UTENTE"));
                utente.setUsername(rs.getString("Username"));
                utente.setPassword(rs.getString("Password"));
                utente.setNome(rs.getString("Nome"));
                utente.setCognome(rs.getString("Cognome"));
                utente.setNumeroTelefono(rs.getString("Telefono"));
                utente.setBiografia(rs.getString("Biografia"));
                utente.setDataNascita(Date.valueOf(rs.getDate("Data_nascita").toLocalDate()).toLocalDate());
                utente.setSesso(Sesso.valueOf(rs.getString("Sesso")));
                return utente;
            }
            DatabaseConnection.closeConnection();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean InsertNewUtente(Utente utente) throws SQLException {
        String sql = "insert into \"SOCIALGROUP_SCHEMA\".\"UTENTE\"(\"Username\", \"Password\", \"Nome\", \"Cognome\", \"Telefono\", \"Biografia\", \"Data_nascita\", \"Sesso\") values(?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getPassword());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(5, utente.getNumeroTelefono());
            ps.setString(6, utente.getBiografia());
            ps.setDate(7, Date.valueOf(utente.getDataNascita()));
            ps.setObject(8, utente.getSesso(), java.sql.Types.OTHER);
            ps.executeUpdate();
            DatabaseConnection.closeConnection();
            return true;
        }catch(SQLException e){
            throw new SQLException();
        }
    }

    public ArrayList<String> getAllNotifications(Utente utente) {
        ArrayList<String> notifications = new ArrayList<>();
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".get_all_notifications(?)";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, utente.getIdUtente());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                notifications.add(rs.getString("messaggio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConnection.closeConnection();
        return notifications;
    }

    public int getIdByUsername(Utente utente) {
        String sql = "select \"ID_UTENTE\" from \"SOCIALGROUP_SCHEMA\".\"UTENTE\" where \"Username\" = ?";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, utente.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DatabaseConnection.closeConnection();
                return rs.getInt("ID_UTENTE");
            }
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public ArrayList<Gruppo> GetAllGroups(Utente utente) {
        String sql = "select \"ID_GRUPPO\" from \"SOCIALGROUP_SCHEMA\".\"PARTECIPA\" where \"ID_UTENTE\" = ?";
        ArrayList<Gruppo> groups = new ArrayList<>();

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, utente.getIdUtente());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Gruppo gruppo = new Gruppo();
                gruppo.setIdGruppo(rs.getInt(1));
                groups.add(gruppo);
            }
            DatabaseConnection.closeConnection();
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String GetUsernameById(Utente utente) {
        String sql = "select \"Username\" from \"SOCIALGROUP_SCHEMA\".\"UTENTE\" where \"ID_UTENTE\" = ?";

        try{
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, utente.getIdUtente());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {return rs.getString("Username");}
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Gruppo> getOwnedGroups(Utente utente) {
        ArrayList<Gruppo> groups = new ArrayList<>();
        String sql = "select \"ID_GRUPPO\" from \"SOCIALGROUP_SCHEMA\".\"GRUPPO\" " +
                     "where \"ID_CREATORE\" = ?";
        try{
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, utente.getIdUtente());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Gruppo gruppo = new Gruppo();
                gruppo.setIdGruppo(rs.getInt(1));
                gruppo.setNome(gruppo.getNomeById(gruppo.getIdGruppo()));
                groups.add(gruppo);
            }
            DatabaseConnection.closeConnection();
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Richiesta> getGroupRequests(Utente utente) {
        ArrayList<Richiesta> richieste = new ArrayList<>();
        ArrayList<Gruppo> groups = getOwnedGroups(utente);
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".get_group_requests(?)";

        if (!groups.isEmpty()) {
            for (Gruppo gruppo : groups) {
                try {
                    PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
                    ps.setInt(1, gruppo.getIdGruppo());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Utente utenteRichiedente = new Utente();
                        utenteRichiedente.setUsername(rs.getString(2));
                        utenteRichiedente.setIdUtente(utente.getIdByUsername());
                        Richiesta richiesta = new Richiesta(false, utenteRichiedente, gruppo, rs.getInt(1));
                        richieste.add(richiesta);
                    }
                    DatabaseConnection.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            return richieste;
        }
        return null;
    }
}
