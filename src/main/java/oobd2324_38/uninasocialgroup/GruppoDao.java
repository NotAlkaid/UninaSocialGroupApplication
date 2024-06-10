package oobd2324_38.uninasocialgroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Post> getPosts(Gruppo gruppo) {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".\"POST\" where \"ID_GRUPPO\" = ?";
        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, gruppo.getIdGruppo());
            ResultSet rs = ps.executeQuery();
            ArrayList<Post> posts = new ArrayList<>();
            while (rs.next()) {
                Utente utente = new Utente();
                Post post = new Post(rs.getInt(1), rs.getDate(2).toLocalDate(),
                        rs.getTime(3).toLocalTime(),
                        rs.getString(5));
                utente.setIdUtente(rs.getInt(7));
                utente.setUsername(utente.GetUsernameByid());
                post.setAutore(utente);
                post.setInGruppo(gruppo);
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
