package oobd2324_38.uninasocialgroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GruppoDao {

    public String getNomeById(int idGruppo) {
        String sql = "select \"Nome\" from \"SOCIALGROUP_SCHEMA\".\"GRUPPO\" where \"ID_GRUPPO\" = ?";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, idGruppo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DatabaseConnection.closeConnection();
                return rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getTemi(Gruppo gruppo) {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".get_group_categories(?)";
        StringBuilder result = new StringBuilder();

        try{
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, gruppo.getIdGruppo());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.append(rs.getString(2)).append(", ");
            }
            result.delete(result.length() - 2, result.length());
            return result.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
