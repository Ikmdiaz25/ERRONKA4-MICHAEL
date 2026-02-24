import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class ProduktuakGehitu {

    public void produktuaGehitu(Scanner sc) {

        System.out.println("Sartu produktuaren kodea");
        int prodKodea = sc.nextInt();

        sc.nextLine();

        System.out.println("Sartu produktuaren izena");
        String prodIzena = sc.nextLine();

        System.out.println("Sartu produktuaren deskribapena");
        String prodDeskribapena = sc.nextLine();

        System.out.println("Sartu produktuaren prezioa");
        double prodPrezioa = sc.nextDouble();

        sc.nextLine();

        System.out.println("Sartu produktuaren sorkuntza data");
        String prodSorkuntzaData = sc.nextLine();

        System.out.println("Sartu produktuaren stocka");
        int prodStock = sc.nextInt();

        sc.nextLine();

        System.out.println("Sartu produktuaren kategoria");
        String prodKategoria = sc.nextLine();

        System.out.println("Sartu produktuaren irudia");
        String prodIrudia = sc.nextLine();

        String sql = "INSERT INTO produktuak (Prod_kod, Prod_Izena, Prod_Deskribapena, Prod_Prezioa, Prod_SorkuntzaData, Prod_Stock, Prod_Irudia, Kateg_kod) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, prodKodea);
            pstmt.setString(2, prodIzena);
            pstmt.setString(3, prodDeskribapena);
            pstmt.setDouble(4, prodPrezioa);
            pstmt.setString(5, prodSorkuntzaData);
            pstmt.setInt(6, prodStock);
            pstmt.setString(7, prodIrudia);
            pstmt.setString(8, prodKategoria);

            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Produktua ondo gehitu da datu-basean!");
            }

        } catch (SQLException e) {
            System.out.println("Errorea produktua gehitzean");
        }

    }
}
