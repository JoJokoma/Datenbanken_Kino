import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Aufgabe1 {
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver did not load – aborting");
            System.exit(-1);
        }
        Connection con = null;
        try {
            String url = "jdbc:postgresql://localhost:9001/jpors001_kino";
            con = DriverManager.getConnection(url, "jwebe001", "Pasword");
        } catch (SQLException e) {
            System.out.println("Couldn't connect - aborting");

            System.exit(-1);


        }
        try {

            Statement stmt = con.createStatement();
            String query = "SELECT * FROM film";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Film: ");
            while (rs.next()) {
                String s = "- " + rs.getString("film_nummer");
                s = s + " " + rs.getString("datum");
                s = s + ", " + rs.getString("uhrzeit");
                s = s + ", " + rs.getString("alters");
                System.exit(-1);


            }


        } catch (SQLException en) {
        }
    }
}