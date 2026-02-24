import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProduktuakKudeatu {

    public static final Scanner sc = new Scanner(System.in);

    /*-----------PRODUKTUAK GEHITZEKO FUNTZIOA----------- */
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

    /*-----------PRODUKTUAK BILATZEKO FUNTZIOA------------ */

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

    /*-----------PRODUKTUAK EZABATZEKO FUNTZIOA------------- */

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

    /*-----------PRODUKTUAK EGUNERATZEKO FUNTZIOA----------- */
    public void produktuakEguneratu() {
        try {
            System.out.println("\n----- PRODUKTUA BERRITU -----");
            System.out.print("Sartu eguneratu nahi den produktuaren kodea (Prod_kod): ");
            int kodea = sc.nextInt();
            sc.nextLine();

            System.out.println("\nZer aldatu nahi duzu?");
            System.out.println("1. Izena");
            System.out.println("2. Deskribapena");
            System.out.println("3. Prezioa");
            System.out.println("4. Stocka");
            System.out.println("5. Kategoria kodea");
            System.out.println("6. Irudiaren URLa");
            System.out.print("Aukeratu zenbaki bat: ");

            int aukera = sc.nextInt();
            sc.nextLine();

            String sql = "";
            PreparedStatement pstmt = null;
            Connection con = DatuBaseConex.conectar();

            switch (aukera) {
                case 1:
                    System.out.print("Sartu izen berria: ");
                    String izenBerria = sc.nextLine();
                    sql = "UPDATE produktuak SET Prod_Izena = ? WHERE Prod_kod = ?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, izenBerria);
                    break;
                case 2:
                    System.out.print("Sartu deskribapen berria: ");
                    String deskBerria = sc.nextLine();
                    sql = "UPDATE produktuak SET Prod_Deskribapena = ? WHERE Prod_kod = ?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, deskBerria);
                    break;
                case 3:
                    System.out.print("Sartu prezio berria: ");
                    double prezioBerria = sc.nextDouble();
                    sql = "UPDATE produktuak SET Prod_Prezioa = ? WHERE Prod_kod = ?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setDouble(1, prezioBerria);
                    break;
                case 4:
                    System.out.print("Sartu stock berria: ");
                    int stockBerria = sc.nextInt();
                    sql = "UPDATE produktuak SET Prod_Stock = ? WHERE Prod_kod = ?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, stockBerria);
                    break;
                case 5:
                    System.out.print("Sartu kategoria kode berria: ");
                    String kategBerria = sc.nextLine();
                    sql = "UPDATE produktuak SET Kateg_kod = ? WHERE Prod_kod = ?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, kategBerria);
                    break;
                case 6:
                    System.out.print("Sartu irudiaren URL berria: ");
                    String irudiBerria = sc.nextLine();
                    sql = "UPDATE produktuak SET Prod_Irudia = ? WHERE Prod_kod = ?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, irudiBerria);
                    break;
                default:
                    System.out.println("Aukera okerra.");
                    con.close();
                    return;
            }

            pstmt.setInt(2, kodea);

            int eragindakoLerroak = pstmt.executeUpdate();

            if (eragindakoLerroak > 0) {
                System.out.println("Produktua ondo eguneratu da!");
            } else {
                System.out.println("Ez da produkturik aurkitu kode horrekin.");
            }

            pstmt.close();
            con.close();

        } catch (SQLException e) {
            System.err.println("SQL Errorea produktua eguneratzean.");

        }

    }

    /*-----------PRODUKTUAK ZERRENDATZEKO FUNTZIOA----------- */
    public void produktuakZerrendatu() {

        try {

            System.out.println("----PRODUKTUAK ZERRENDATU-----");

            System.out.println("Nola ikusi nahi dituzu produktuak?");
            System.out.println("1. Kategoriaren arabera");
            System.out.println("2. Produktu guztiak");

            int aukera1 = sc.nextInt();

            sc.nextLine();

            /*
             * Kategoria kodea hasieratzen dut eta gero, where eta orderBy bi aldagaiak
             * sortzen ditut, izan ere, SQL kontsultaren arabera aldatuko dira.
             * Horrela, ez dut hainbat SQL kontsulta idatzi beharrik izango
             */

            int kategKod = 0;
            String where = "";
            String orderBy = "";
            String sql = "";

            /* KATEGORIAREN ARABERA AUKERATZEN BADU */

            if (aukera1 == 1) {

                System.out.println("Sartu kategoriaren kodea");
                kategKod = sc.nextInt();
                sc.nextLine();
                where = "WHERE Kateg_kod= ?";

            }

            /*------------------ */
            System.out.println("\nNola ordenatu nahi dituzu emaitzak?");
            System.out.println("1. Prezioaren arabera (Merkeenetik garestienera)");
            System.out.println("2. Stockaren arabera (Gutxienetik gehienera)");
            System.out.print("Aukeratu zenbaki bat: ");

            int ordena = sc.nextInt();
            sc.nextLine();

            /* PREZIOAREN ARABERA AUKERATZEN BADU */

            if (ordena == 1) {
                orderBy = " ORDER BY Prod_Prezioa ASC";
            }
            /* STOCKAREN ARABERA AUKERATZEN BADU */
            else if (ordena == 2) {
                orderBy = " ORDER BY Prod_Stock ASC";

            }
            /* AUKERA OKERRA */
            else {
                System.out.println("Aukera okerra. Berezko ordenan erakutsiko dira.");
            }

            /*
             * Orduan hemengo sql aldagaian, aukeraren arabera, WHERE eta ORDER BY egongo
             * dira. Adibidez, 1 eta 1 aukeratuz gero, sql aldagaian
             * "SELECT * FROM produktuak WHERE Kateg_kod= ? ORDER BY Prod_Prezioa ASC"
             * egongo da
             */

            sql = "SELECT * FROM produktuak" + where + orderBy;

            /*
             * Datu basearekin konexioa sortzen dut eta ondoren, prepared statement bat
             * sortzen dut
             */

            Connection con = DatuBaseConex.conectar();
            PreparedStatement pstmt = con.prepareStatement(sql);

            if (aukera1 == 1) {
                pstmt.setInt(1, kategKod);
            }

            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- PRODUKTUEN ZERRENDA ---");
            boolean badaude = false;

            while (rs.next()) {
                badaude = true;
                System.out.println("Kodea: " + rs.getInt("Prod_kod") +
                        " | Izena: " + rs.getString("Prod_Izena") +
                        " | Prezioa: " + rs.getDouble("Prod_Prezioa") + "€" +
                        " | Stock: " + rs.getInt("Prod_Stock") +
                        " | Kategoria: " + rs.getInt("Kateg_kod"));
            }

            if (!badaude) {
                System.out.println("Ez da produkturik aurkitu irizpide horiekin.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Errorea produktuak zerrendatzean");

        }
    }

}
