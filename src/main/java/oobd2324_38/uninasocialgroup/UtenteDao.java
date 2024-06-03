package oobd2324_38.uninasocialgroup;

import javafx.util.StringConverter;
import org.postgresql.util.PSQLException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
}
