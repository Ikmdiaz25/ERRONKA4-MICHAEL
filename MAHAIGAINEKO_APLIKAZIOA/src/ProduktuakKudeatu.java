import java.sql.*;
import java.util.*;
import java.io.*;
import com.google.gson.*;

public class ProduktuakKudeatu {

    public static final Scanner sc = new Scanner(System.in);

    /*-----------PRODUKTUAK GEHITZEKO FUNTZIOA----------- */

    public static void produktuaGehitu() {
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

        String emaitza = produktuaGehitu(1, prodIzena, prodDeskribapena, prodKodea, prodKategoria);
        System.out.println(emaitza);
    }

    public static String produktuaGehitu(int menu, String izena, String deskripzioa, int id, String kategoria) {
        if (menu < 1 || menu > 6) {
            return "Mesedez sartu 1 eta 6 arteko zenbaki bat";
        }
        if (izena.equals("---")) {
            return "Sartutako produktuaren izena okerra da";
        }
        if (deskripzioa.equals("---")) {
            return "Produktuaren deskribapena sartu behar da";
        }
        if (id < 0) {
            return "Produktuaren ID-a ezin da negatiboa izan";
        }

        String sql = "INSERT INTO produktuak (Prod_kod, Prod_Izena, Prod_Deskribapena, Prod_Prezioa, Prod_SorkuntzaData, Prod_Stock, Prod_Irudia, Kateg_kod) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, izena);
            pstmt.setString(3, deskripzioa);
            pstmt.setDouble(4, 0.0);
            pstmt.setString(5, "");
            pstmt.setInt(6, 0);
            pstmt.setString(7, "");
            pstmt.setString(8, kategoria);

            pstmt.executeUpdate();
            return izena + " gehitu egin da";
        } catch (SQLException e) {
            return "SQL Errorea: " + e.getMessage();
        }
    }

    /*-----------PRODUKTUAK BILATZEKO FUNTZIOA------------ */

    public static void produktuakBilatu() {
        System.out.println("-----PRODUKTUAK BILATU-----");
        System.out.println("1.Bilatu izenaren arabera");
        System.out.println("2.Bilatu kodearen arabera");
        System.out.println("Aukeratu modu bat (1 edo 2): ");
        int aukera = sc.nextInt();
        sc.nextLine();

        System.out.println("Sartu bilatzeko testua:");
        String testua = sc.nextLine();

        String emaitza = produktuakBilatu(5, testua, "Ez");
        System.out.println(emaitza);
    }

    public static String produktuakBilatu(int menu, String info, String extra) {
        if (info.equals("-")) {
            return "Mesedez sartu produktuaren informazioa bilaketa egiteko";
        }

        String sql = "";
        boolean isNumeric = info.chars().allMatch(Character::isDigit);

        if (isNumeric) {
            sql = "SELECT * FROM produktuak WHERE Prod_kod = ?";
        } else {
            sql = "SELECT * FROM produktuak WHERE Prod_izena LIKE ?";
            info = "%" + info + "%";
        }

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, info);
            ResultSet rs = pstmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            if (rs.next()) {
                sb.append(rs.getString("Prod_izena")).append("ren informazioa honakoa da:");
                // Could add more details here
            } else {
                return "Ez da produkturik aurkitu.";
            }
            return sb.toString();
        } catch (SQLException e) {
            return "Errorea produktua bilatzean:" + e.getMessage();
        }
    }

    /*-----------PRODUKTUAK EZABATZEKO FUNTZIOA------------- */

    public static void produktuakEzabatu() {

        System.out.println("Sartu ezabatu nahi den produktuaren kodea:");

        int kodea = sc.nextInt();

        String sql = "DELETE FROM produktuak WHERE Prod_kod = ?";

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, kodea);

            pstmt.executeUpdate();
            System.out.println("Ezabatu da!");

        } catch (SQLException e) {
            System.out.println("Errorea produktua ezabatzean:" + e.getMessage());
        }

    }

    /*-----------PRODUKTUAK EGUNERATZEKO FUNTZIOA----------- */
    public static void produktuakEguneratu() {
        System.out.print("Sartu produktuaren kodea: ");
        int kodea = sc.nextInt();
        sc.nextLine();
        System.out.println("1. Eguneratu | 2. Ezabatu");
        String aukera = sc.nextLine();

        String emaitza = produktuakEguneratu(3, aukera, kodea);
        System.out.println(emaitza);
    }

    public static String produktuakEguneratu(int menu, String aukera, int kodea) {
        if (!aukera.equals("1") && !aukera.equals("2")) {
            return "Produktua eguneratzeko aukera ez da zuzena, 1-tik 3-ra izan behar da";
        }

        String sql = "";
        if (aukera.equals("1")) {
            sql = "UPDATE produktuak SET Prod_Stock = Prod_Stock + 1 WHERE Prod_kod = ?"; // Placeholder logic
        } else {
            sql = "DELETE FROM produktuak WHERE Prod_kod = ?";
        }

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, kodea);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                return aukera.equals("1") ? "Produktua zuzenki eguneratu egin da" : "Produktua ezabatu da";
            } else {
                return "Ez da produkturik aurkitu.";
            }
        } catch (SQLException e) {
            return "Errorea: " + e.getMessage();
        }
    }

    /*-----------PRODUKTUAK ZERRENDATZEKO FUNTZIOA----------- */
    public static void produktuakZerrendatu() {
        System.out.println("1. Kategoria | 2. Guztiak");
        int aukera1 = sc.nextInt();
        System.out.println("1. Prezioa | 2. Stocka");
        int ordena = sc.nextInt();

        String emaitza = produktuakZerrendatu(4, aukera1, ordena);
        System.out.println(emaitza);
    }

    public static String produktuakZerrendatu(int menu, int aukera1, int ordena) {
        if (aukera1 != 1 && aukera1 != 2) {
            return "Sartu 1 edo 2 zenbakia";
        }

        if (aukera1 == 1 && ordena == 1)
            return "Kategoriaren arabera eta prezioaren arabera:";
        if (ordena == 1)
            return "Produktuak prezioaren arabera: ";
        if (ordena == 2)
            return "Produktuak eskuragarritasun arabera:";

        return "Zerrenda:";
    }

}
