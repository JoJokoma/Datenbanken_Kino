import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.Integer.parseInt;


public class Aufgabe1 {
    static File filmfile = new File("src/files/1000GreatestFilms (1).csv");
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
            con = DriverManager.getConnection(url, "jwebe001", "");
        } catch (SQLException e) {
            System.out.println("Couldn't connect - aborting");

            System.exit(-1);


        }
        try {
            PreparedStatement filmInsert =  con.prepareStatement("INSERT INTO FILM (TITEL, LAENGE) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
            PreparedStatement genreInsert =  con.prepareStatement("INSERT INTO GENRE (GENRE) VALUES (?)");
            PreparedStatement filmgenre = con.prepareStatement("INSERT INTO GENREFILM (FILM_NUMMER, GENRE)VALUES(?,?)");

            BufferedReader filmreader = new BufferedReader(new FileReader(filmfile.getAbsolutePath()));
            int linecount = 0;
            String infoline = null;
            filmreader.readLine();
            int z = 0;
            while((infoline = filmreader.readLine())!= null ){
                String[] filmdata = infoline.split(";");
                String titel = filmdata[3];
                int length = Integer.valueOf(filmdata[7]);
                String[] geners = filmdata[8].split("-");
                filmInsert.setString(1, titel);
                filmInsert.setInt(2,length);
                filmInsert.executeUpdate();
                ResultSet filmKeys = filmInsert.getGeneratedKeys();
                int size = geners.length;
                if(filmKeys.next()){
                int filmkey = filmKeys.getInt(1);
                for (int i = 0; i<size;i++){
                   try{
                    genreInsert.setString(1,geners[i]);
                    genreInsert.executeUpdate();}
                   catch(SQLException pw){}
                    filmgenre.setInt(1,filmkey);
                    filmgenre.setString(2,geners[i]);
                    filmgenre.executeUpdate();

                }

                }




            }
            filmreader.close();
            /*String s = "";
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM film";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Film: ");
            while (rs.next()) {
                s = "- " + rs.getString("film_nummer");
                s = s + " " + rs.getString("titel");
                s = s + ", " + rs.getString("laenge");
                s = s + ", " + rs.getString("altersbeschraenkung");
                System.exit(-1);


            }
            System.out.println(s);*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}