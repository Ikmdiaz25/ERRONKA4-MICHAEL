import java.sql.*;
import java.util.*;

public class ProduktuakKudeatu {

    /*-----------PRODUKTUAK GEHITZEKO FUNTZIOA----------- */

    public static String produktuaGehitu(int prodKodea, String prodIzena, String prodDeskribapena, double prodPrezioa,
            String prodSorkuntzaData, int prodStock, int prodKategoria, String prodIrudia) {

        if (prodKodea < 0)
            return "Produktuaren ID-a ezin da negatiboa izan";
        if (prodIzena == null || prodIzena.trim().isEmpty() || prodIzena.equals("---"))
            return "Sartutako produktuaren izena okerra da";
        if (prodDeskribapena == null || prodDeskribapena.trim().isEmpty() || prodDeskribapena.equals("---"))
            return "Produktuaren deskribapena sartu behar da";

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
            pstmt.setInt(8, prodKategoria);

            pstmt.executeUpdate();
            return "Gorde da!";

        } catch (SQLException e) {
            return "SQL Errorea: " + e.getMessage();
        }
    }

    /*-----------PRODUKTUAK BILATZEKO FUNTZIOA------------ */

    public static List<String> ProduktuakBilatu(int aukera, String balioa) {
        List<String> emaitzak = new ArrayList<>();
        String sql = "";
        String bilatzailea = "";

        if (aukera == 1) {
            sql = "SELECT * FROM produktuak WHERE Prod_izena LIKE ?";
            bilatzailea = "%" + balioa + "%";
        } else if (aukera == 2) {
            sql = "SELECT * FROM produktuak WHERE Prod_kod = ?";
            bilatzailea = balioa;
        } else {
            emaitzak.add("Aukera okerra.");
            return emaitzak;
        }

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, bilatzailea);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String emaitza = "ID: " + rs.getInt("Prod_kod") +
                        " | Izena: " + rs.getString("Prod_izena") +
                        " | Deskribapena: " + rs.getString("Prod_deskribapena") +
                        " | Prezioa: " + rs.getDouble("Prod_prezioa") + "€" +
                        " | Stock: " + rs.getInt("Prod_stock");
                emaitzak.add(emaitza);
            }

            if (emaitzak.isEmpty()) {
                emaitzak.add("Ez da produkturik aurkitu.");
            }

        } catch (SQLException e) {
            emaitzak.add("Errorea produktua bilatzean:" + e.getMessage());
        }

        return emaitzak;
    }

    /*-----------PRODUKTUAK EZABATZEKO FUNTZIOA------------- */

    public static String produktuakEzabatu(int kodea) {
        String sql = "DELETE FROM produktuak WHERE Prod_kod = ?";

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, kodea);
            int eragindakoLerroak = pstmt.executeUpdate();

            if (eragindakoLerroak > 0) {
                return "Ezabatu da!";
            } else {
                return "Ez da produkturik aurkitu kode horrekin.";
            }

        } catch (SQLException e) {
            return "Errorea produktua ezabatzean:" + e.getMessage();
        }
    }

    /*-----------PRODUKTUAK EGUNERATZEKO FUNTZIOA----------- */

    public static String produktuakEguneratu(int kodea, int zerAldatu, String balioBerriaStr) {
        String sql = "";

        switch (zerAldatu) {
            case 1:
                sql = "UPDATE produktuak SET Prod_Izena = ? WHERE Prod_kod = ?";
                break;
            case 2:
                sql = "UPDATE produktuak SET Prod_Deskribapena = ? WHERE Prod_kod = ?";
                break;
            case 3:
                sql = "UPDATE produktuak SET Prod_Prezioa = ? WHERE Prod_kod = ?";
                break;
            case 4:
                sql = "UPDATE produktuak SET Prod_Stock = ? WHERE Prod_kod = ?";
                break;
            case 5:
                sql = "UPDATE produktuak SET Kateg_kod = ? WHERE Prod_kod = ?";
                break;
            case 6:
                sql = "UPDATE produktuak SET Prod_Irudia = ? WHERE Prod_kod = ?";
                break;
            default:
                return "Aukera okerra.";
        }

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            if (zerAldatu == 1 || zerAldatu == 2 || zerAldatu == 6) {
                pstmt.setString(1, balioBerriaStr);
            } else if (zerAldatu == 3) {
                pstmt.setDouble(1, Double.parseDouble(balioBerriaStr));
            } else if (zerAldatu == 4 || zerAldatu == 5) {
                pstmt.setInt(1, Integer.parseInt(balioBerriaStr));
            }

            pstmt.setInt(2, kodea);
            int eragindakoLerroak = pstmt.executeUpdate();

            if (eragindakoLerroak > 0) {
                return "Produktua ondo eguneratu da!";
            } else {
                return "Ez da produkturik aurkitu kode horrekin.";
            }

        } catch (SQLException e) {
            return "SQL Errorea produktua eguneratzean." + e.getMessage();
        } catch (NumberFormatException e) {
            return "Sartutako datua ez da formatu egokian.";
        }
    }

    /*-----------PRODUKTUAK ZERRENDATZEKO FUNTZIOA----------- */

    public static List<String> produktuakZerrendatu(int aukeraKategoria, int kategKod, int ordena) {
        List<String> emaitzak = new ArrayList<>();
        String where = "";
        String orderBy = "";
        String sql = "";

        if (aukeraKategoria == 1) {
            where = " WHERE Kateg_kod= ?";
        }

        if (ordena == 1) {
            orderBy = " ORDER BY Prod_Prezioa ASC;";
        } else if (ordena == 2) {
            orderBy = " ORDER BY Prod_Stock ASC;";
        } else {
            emaitzak.add("Berezko ordenan erakutsiko dira.");
        }

        sql = "SELECT * FROM produktuak" + where + orderBy;

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            if (aukeraKategoria == 1) {
                pstmt.setInt(1, kategKod);
            }

            ResultSet rs = pstmt.executeQuery();
            boolean badaude = false;

            while (rs.next()) {
                badaude = true;
                String prod = "Kodea: " + rs.getInt("Prod_kod") +
                        " | Izena: " + rs.getString("Prod_Izena") +
                        " | Prezioa: " + rs.getDouble("Prod_Prezioa") + "€" +
                        " | Stock: " + rs.getInt("Prod_Stock") +
                        " | Kategoria: " + rs.getInt("Kateg_kod");
                emaitzak.add(prod);
            }

            if (!badaude) {
                emaitzak.add("Ez da produkturik aurkitu irizpide horiekin.");
            }

        } catch (SQLException e) {
            emaitzak.add("SQL Errorea produktuak zerrendatzean" + e.getMessage());
        }

        return emaitzak;
    }
}