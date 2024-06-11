package oobd2324_38.uninasocialgroup;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PostDao {
    public void insertPost(Post post) throws SQLException {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".insert_post(?, ?, ?)";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, post.getTesto());
            ps.setInt(2, post.getAutore().getIdUtente());
            ps.setInt(3, post.getInGruppo().getIdGruppo());
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
