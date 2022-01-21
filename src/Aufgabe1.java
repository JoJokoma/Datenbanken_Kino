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
            String url = "jdbc:postgresql://db.intern.mi.hs-rm.de:5432/database";
            con = DriverManager.getConnection(url, "jwebe001", "password");
        } catch (SQLException e) {
            System.out.println("Couldn't connect - aborting");
            System.exit(-1);
        }
     try {
         Statement stmt = con.createStatement();
         String query = "SELECT * FROM studierende";
         ResultSet rs = stmt.executeQuery(query);
         System.out.println("Studierende: ");
         while (rs.next()) {
             String s = "- " + rs.getString("vorname");
             s = s + " " + rs.getString("name");
             s = s + ", " + rs.getString("matnr");
             System.exit(-1);


         }



    } catch(SQLException e){
     }
    }
}