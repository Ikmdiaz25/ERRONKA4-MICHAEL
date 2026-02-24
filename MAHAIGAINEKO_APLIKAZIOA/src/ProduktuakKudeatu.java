import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProduktuakKudeatu {

    public static final Scanner sc = new Scanner(System.in);

    /*----PRODUKTUAK GEHITZEKO FUNTZIOA */
    public void produktuaGehitu(Scanner sc) {

        /* Produktuaren informazioa sartu, datu guztiak. */

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

        /* Produktuaren informazioa gehitu datu-basean */
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

            pstmt.executeUpdate();
            System.out.println("Gorde da!");

        }

        catch (SQLException e) {
            System.err.println("SQL Errorea: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Errorea: Sartutako datu mota ez da zuzena (zenbakia espero zen).");
            sc.nextLine();
        }

    }

    /*----PRODUKTUAK BILATZEKO FUNTZIOA */

    public void ProduktuakBilatu() {

        /* Produktuak bilatzeako menua */
        System.out.println("-----PRODUKTUAK BILATU-----");
        System.out.println("1.Bilatu izenaren arabera");
        System.out.println("2.Bilatu kodearen arabera");

        System.out.println("Aukeratu modu bat (1 edo 2): ");
        int aukera = sc.nextInt();
        sc.nextLine();

        String sql = "";
        String bilatzailea = "";

        /* Bilatu produktuaren izenarekin */
        if (aukera == 1) {

            System.out.println("Sartu produktuaren izena (edo zati bat):");

            sql = "SELECT * FROM produktuak WHERE Prod_izena LIKE ?";

            bilatzailea = "%" + sc.nextLine() + "%";

            /* Bilatu produktuaren kodearekin */

        } else if (aukera == 2) {

            System.out.println("Sartu produktuaren kodea:");
            int kodea = sc.nextInt();
            sql = "SELECT * FROM produktuak WHERE Prod_kod = ?";
            bilatzailea = String.valueOf(kodea);

        }

        /* Datu basearekin konektatu eta produktuak bilatu */
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

    public void produktuakEzabatu() {

        System.out.println("Sartu ezabatu nahi den produktuaren kodea:");

        int kodea = sc.nextInt();

        String sql = "DELETE FROM produktuak WHERE Prod_kod = ?";

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, kodea);

            pstmt.executeUpdate();
            System.out.println("Ezabatu da!");

        } catch (SQLException e) {
            System.out.println("Errorea produktua ezabatzean");
        }

    }

}
