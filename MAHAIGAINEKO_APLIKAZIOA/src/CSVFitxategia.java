import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class CSVFitxategia {

    public static void csvIgo() {

        System.out.println("Java fitxategiak bilatzen ari da hemen: ");
        System.out.println(new java.io.File(".").getAbsolutePath());

        Scanner sc = new Scanner(System.in);
        System.out.println("\n----- CSV FITXATEGIA IGO -----");
        System.out.print("Sartu CSV fitxategiaren izena edo bidea (adibidez: produktuak.csv): ");
        String fitxategia = sc.nextLine();

        /* SQL kontsulta */
        String sql = "INSERT INTO produktuak (Prod_kod, Prod_Izena, Prod_Deskribapena, Prod_Prezioa, Prod_SorkuntzaData, Prod_Stock, Prod_Irudia, Kateg_kod) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        /* Buffered reader fitxategia irakurtzeko eta datu basearekin konekzioa */
        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia));
                Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            String lerroa;
            int sartutakoak = 0;
            int lerroKop = 0;

            System.out.println("Fitxategia irakurtzen...");

            /* Lerroak irakurtzeko buklea */
            while ((lerroa = br.readLine()) != null) {
                lerroKop++;

                /*
                 * Beheko hau lehenengo lerroa (goiburua) ez sartzeko da, eta beraz ez du hori
                 * kontuan izango, bestela zenbaki bezala hartuko du eta errora emango zuen.
                 * Niri errorea eman dit eta horregatik hau nola zuzendu bilatu izan dut.
                 */
                if (lerroKop == 1) {
                    continue;
                }

                /* Koma bidez datuak banatzen dira */
                String[] datuak = lerroa.split(",");

                /* 8 zutabe dauden konprobatzen da */
                if (datuak.length == 8) {

                    /*
                     * Hemen .trim() erabiltzen da datuak ondo sartzeko, hasierako eta
                     * bukaerako hutsuneak kentzeko: adibidez " 1 " -> "1" Modu honetan
                     * erroreak ekiditen dira. Bestalde, javarantzako fitxategi baten datu guztiak
                     * string motakoak direnez, orduan batzuk int-era eta besteak double-era
                     * bihurtzen dira Integer.parseInt() eta Double.parseDouble() erabiliz. Azkenik
                     * "datuak" izeneko array batean gordetzen dira.
                     */

                    pstmt.setInt(1, Integer.parseInt(datuak[0].trim())); // Prod_kod
                    pstmt.setString(2, datuak[1].trim()); // Prod_Izena
                    pstmt.setString(3, datuak[2].trim()); // Prod_Deskribapena
                    pstmt.setDouble(4, Double.parseDouble(datuak[3].trim())); // Prod_Prezioa
                    pstmt.setString(5, datuak[4].trim()); // Prod_Sorkuntza_data (YYYY-MM-DD)
                    pstmt.setInt(6, Integer.parseInt(datuak[5].trim())); // Prod_Stock
                    pstmt.setString(7, datuak[6].trim()); // Prod_Irudia
                    pstmt.setInt(8, Integer.parseInt(datuak[7].trim())); // Kateg_kod

                    pstmt.executeUpdate();
                    sartutakoak++;
                }

                else {
                    System.out.println("Errorea " + lerroKop + ". lerroan: Ez daude 8 zutabe.");
                }

                System.out.println("\nProzesua amaituta. " + sartutakoak + " produktu berri sartu dira datu-basean.");

            }
        } catch (IOException e) {
            System.err.println(
                    "Errorea fitxategia irakurtzean. Ziurtatu izena eta tokia zuzenak direla");
        } catch (SQLException e) {
            System.err.println("Errorea datu-basearekin konektatzean: " + e.getMessage());
            e.printStackTrace(); // Esto nos imprimirá el rastro exacto del error en rojo
        }

    }
}
