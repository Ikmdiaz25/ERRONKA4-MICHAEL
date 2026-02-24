import java.util.*;
import java.sql.*;

public class ProduktuakBilatu {

    public void ProduktuakBilatu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("-----PRODUKTUAK BILATU-----");
        System.out.println("1.Bilatu izenaren arabera");
        System.out.println("2.Bilatu kodearen arabera");

        System.out.println("Aukeratu modu bat (1 edo 2): ");
        int aukera = sc.nextInt();
        sc.nextLine();

        String sql = "";
        String bilatzailea = "";

        if (aukera == 1) {

            System.out.println("Sartu produktuaren izena (edo zati bat):");

            sql = "SELECT * FROM produktuak WHERE Prod_izena LIKE ?";

            bilatzailea = "%" + sc.nextLine() + "%";

        } else if (aukera == 2) {

            System.out.println("Sartu produktuaren kodea:");
            int kodea = sc.nextInt();
            sql = "SELECT * FROM produktuak WHERE Prod_kod = ?";
            bilatzailea = String.valueOf(kodea);

        }

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, bilatzailea);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("----EMAITZAK----");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Izena: " + rs.getString("izena") +
                        " | Deskribapena: " + rs.getString("deskribapena") +
                        " | Prezioa: " + rs.getDouble("prezioa") + "€" +
                        " | Stock: " + rs.getInt("stock"));

            }

        } catch (SQLException e) {
            System.out.println("Errorea produktua bilatzean");
        }

    }
}
