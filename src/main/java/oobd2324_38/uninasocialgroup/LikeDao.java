package oobd2324_38.uninasocialgroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDao {

    public void removeLike(Post post, Utente utente) {
        String sql = "delete from \"SOCIALGROUP_SCHEMA\".\"LIKE\" where " +
                     "\"ID_UTENTE\" = ? and \"ID_POST\" = ?";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, utente.getIdUtente());
            ps.setInt(2, post.getIdPost());
            ps.execute();
            DatabaseConnection.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void putLike(Post post, Utente utente) {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".put_like(?, ?)";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, utente.getIdUtente());
            ps.setInt(2, post.getIdPost());
            ResultSet rs = ps.executeQuery();
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
