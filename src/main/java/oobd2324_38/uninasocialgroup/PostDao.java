package oobd2324_38.uninasocialgroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDao {
    public void insertPost(Post post) throws SQLException {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".insert_post(?, ?, ?)";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, post.getTesto());
            ps.setInt(2, post.getAutore().getIdUtente());
            ps.setInt(3, post.getInGruppo().getIdGruppo());
            ResultSet rs = ps.executeQuery();
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPostLikesNumber(Post post) throws SQLException {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".get_post_likes(?)";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, post.getIdPost());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {return rs.getInt(1);}
            DatabaseConnection.closeConnection();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int getPostCommentsNumber(Post post) throws SQLException {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".get_post_comments(?)";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, post.getIdPost());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {return rs.getInt(1);}
            DatabaseConnection.closeConnection();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public boolean isPostLikedByUser(Post post, Utente utente) throws SQLException {
        String sql = "select 1 from \"SOCIALGROUP_SCHEMA\".\"LIKE\" li\n" +
                "\twhere li.\"ID_UTENTE\" = ? and li.\"ID_POST\" = ?";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, utente.getIdUtente());
            ps.setInt(2, post.getIdPost());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {return true;}
            DatabaseConnection.closeConnection();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
