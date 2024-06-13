package oobd2324_38.uninasocialgroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RichiestaDao {

    public boolean insertRichiesta(Richiesta richiesta) {
        String sql = "insert into \"SOCIALGROUP_SCHEMA\".\"RICHIESTA\"(\"ID_UTENTE\", " +
                     "\"ID_GRUPPO\") values(?, ?)";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, richiesta.getUtente().getIdUtente());
            ps.setInt(2, richiesta.getGruppo().getIdGruppo());
            int res = ps.executeUpdate();
            if (res > 0) {return true;}
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void AccettaRichiesta(Richiesta richiesta) {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".accept_request(?)";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, richiesta.getIdRichiesta());
            ResultSet rs = ps.executeQuery();
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void RifiutaRichiesta(Richiesta richiesta) {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".reject_request(?)";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, richiesta.getIdRichiesta());
            ResultSet rs = ps.executeQuery();
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
