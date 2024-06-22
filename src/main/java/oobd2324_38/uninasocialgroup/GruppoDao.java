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
            DatabaseConnection.closeConnection();
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

    public void insertGruppo(Gruppo gruppo) throws SQLException {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".insert_group(?, ?, ?)";

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, gruppo.getNome());
            ps.setString(2, gruppo.getTema());
            ps.setInt(3, gruppo.getCreatoreGruppo().getIdUtente());
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public ArrayList<Gruppo> getNamedGroups(String titolo, int IdUtente) {
        String sql = "select gru.\"Nome\", gru.\"ID_GRUPPO\" from (select gr.\"ID_GRUPPO\" from \"SOCIALGROUP_SCHEMA\".\"GRUPPO\" gr\n" +
                "\t\texcept (select pa.\"ID_GRUPPO\" from \n" +
                "\t\t\"SOCIALGROUP_SCHEMA\".\"PARTECIPA\" pa\n" +
                "\t\twhere pa.\"ID_UTENTE\" = ?)) as x natural join \n" +
                "\t\t\"SOCIALGROUP_SCHEMA\".\"GRUPPO\" gru\n" +
                "\t\twhere gru.\"Nome\" LIKE \'%" + titolo + "%\'";
        ArrayList<Gruppo> gruppi = new ArrayList<>();

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, IdUtente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Gruppo gruppo = new Gruppo();
                gruppo.setNome(rs.getString(1));
                gruppo.setIdGruppo(rs.getInt(2));
                gruppo.setTema(gruppo.getTema());
                gruppi.add(gruppo);
            }
            DatabaseConnection.closeConnection();
            return gruppi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Gruppo> getThemeGroups(String tema, int IdUtente) {
        String sql = "select gru.\"Nome\", gru.\"ID_GRUPPO\" from (select gr.\"ID_GRUPPO\" from \n" +
                "\t\"SOCIALGROUP_SCHEMA\".\"GRUPPO\" gr\n" +
                "\t\texcept (select pa.\"ID_GRUPPO\" from \n" +
                "\t\t\"SOCIALGROUP_SCHEMA\".\"PARTECIPA\" pa\n" +
                "\t\twhere pa.\"ID_UTENTE\" = ?)) as x natural join \n" +
                "\t\t\"SOCIALGROUP_SCHEMA\".\"GRUPPO\" gru natural join\n" +
                "\t\t\"SOCIALGROUP_SCHEMA\".\"TEMA\" te\n" +
                "\t\twhere te.\"Titolo\" LIKE \'%" + tema + "%\'";
        ArrayList<Gruppo> gruppi = new ArrayList<>();

        try {
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, IdUtente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Gruppo gruppo = new Gruppo();
                gruppo.setNome(rs.getString(1));
                gruppo.setIdGruppo(rs.getInt(2));
                gruppo.setTema(gruppo.getTema());
                gruppi.add(gruppo);
            }
            DatabaseConnection.closeConnection();
            return gruppi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isGroupAlreadyRequested(Utente utente, Gruppo gruppo) {
        String sql = "select 1 from \"SOCIALGROUP_SCHEMA\".\"RICHIESTA\" where exists(select " +
                     "ri.\"ID_RICHIESTA\" from \"SOCIALGROUP_SCHEMA\".\"RICHIESTA\" ri where ri.\"ID_UTENTE\" " +
                     "= ? and ri.\"ID_GRUPPO\" = ?)";
        try{
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, utente.getIdUtente());
            ps.setInt(2, gruppo.getIdGruppo());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {return true;}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public ArrayList<Post> getPostsInMonth(Gruppo gruppo, String mese) {
        String sql = "select * from \"SOCIALGROUP_SCHEMA\".\"POST\" where \"ID_GRUPPO\" = ?" +
                     " and EXTRACT(MONTH FROM \"Data_pubblicazione\") = " + mese;
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
