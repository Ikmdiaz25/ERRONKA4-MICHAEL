import java.sql.*;

//Datu basearekin konexioa egiteko klasea
//Separatuta egin dut, ordenatuta egoteko
public class DatuBaseConex {

    public static Connection conectar() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/ERRONKA4";
        String usuario = "root";
        String contrasena = "Pasahitza155";

        return DriverManager.getConnection(url, usuario, contrasena);
    }

}
