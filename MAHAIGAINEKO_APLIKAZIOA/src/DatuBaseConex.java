import java.sql.*;

//Datu basearekin konexioa egiteko klasea
//Separatuta egin dut, ordenatuta egoteko

public class DatuBaseConex {

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/erronka4";
            String usuario = "root";
            String contrasena = "Pasahitza155";

            return DriverManager.getConnection(url, usuario, contrasena);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driverra ez da aurkitu: " + e.getMessage());
        }
    }
}