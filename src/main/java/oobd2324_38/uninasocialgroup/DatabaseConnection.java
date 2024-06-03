package oobd2324_38.uninasocialgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        connection = null;
        String url = "jdbc:postgresql://localhost:5432/UninaSocialGroup?currentSchema=SOCIALGROUP_SCHEMA";
        String username = "postgres";
        String password = "spacehack";

        try{
            connection = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if(connection != null) {
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
