import java.sql.*;

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
            String url = "jdbc:postgresql://db.intern.mi.hs-rm.de:5432/jpors001_kino";
            con = DriverManager.getConnection(url, "jwebe001", "");
        } catch (SQLException e) {
            System.out.println("Couldn't connect - aborting");
            System.exit(-1);
        }
     try {
         Statement stmt = con.createStatement();
         String query = "SELECT * FROM kinoprogram";
         ResultSet rs = stmt.executeQuery(query);
         System.out.println("Studierende: ");
         while (rs.next()) {
             String s = "- " + rs.getString("film");
             s = s + " " + rs.getString("datum");
             s = s + ", " + rs.getString("uhrzeit");
             System.exit(-1);


         }



    } catch(SQLException e){
     }
    }
}