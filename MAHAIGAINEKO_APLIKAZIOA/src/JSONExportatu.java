import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class JSONExportatu {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void esportatuDatuak() {
        System.out.println("\n----- JSON FITXATEGIAK SORTZEN (GSON + OBJEKTUAK) -----");

        esportatuProduktuGuztiak();
        esportatuEstatistikak();
        esportatuGehienSaldutakoak();

        System.out.println("Prozesua amaituta! Fitxategiak ondo sortu dira.");
    }

    /* ----------------- 1. PRODUKTU GUZTIAK ESPORTATU ----------------- */
    private void esportatuProduktuGuztiak() {
        String sql = "SELECT * FROM produktuak";

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                FileWriter fw = new FileWriter("produktu_guztiak.json")) {

            /* Java lista bat sortzen dugu objektuak gordetzeko */
            List<produktuak> produktuZerrenda = new ArrayList<>();

            while (rs.next()) {
                /*
                 * Objetuaren instantzia sortu eta datuak sartu
                 */
                produktuak p = new produktuak(0, sql, sql, 0, 0, sql, sql, 0);

                /* Datuak sartu setters erabiliz */
                p.setKod(rs.getInt("Prod_kod"));
                p.setIzena(rs.getString("Prod_Izena"));
                p.setDeskribapena(rs.getString("Prod_Deskribapena"));
                p.setPrezioa(rs.getDouble("Prod_Prezioa"));
                p.setStock(rs.getInt("Prod_Stock"));
                p.setSorkuntzaData(rs.getString("Prod_SorkuntzaData"));
                p.setIrudia(rs.getString("Prod_Irudia"));
                p.setKategoria_Kod(rs.getInt("Kateg_Kod"));

                /* Objetua listari gehitzen diogu */
                produktuZerrenda.add(p);
            }

            /* Objetu lista osoa JSON bihurtzen du eta gordetzen du */
            gson.toJson(produktuZerrenda, fw);
            System.out.println(" - 'produktu_guztiak.json' ondo sortu da.");

        } catch (SQLException | IOException e) {
            System.out.println("Errorea produktuak esportatzean: " + e.getMessage());
        }
    }

    /* ----------------- 2. ESTATISTIKAK ESPORTATU ----------------- */
    private void esportatuEstatistikak() {
        String sql = "SELECT COUNT(*) AS totala, AVG(Prod_Prezioa) AS bataz_bestekoa, SUM(Prod_Stock) AS stock_totala FROM produktuak";

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                FileWriter fw = new FileWriter("estatistikak.json")) {

            if (rs.next()) {
                JsonObject statsObj = new JsonObject();
                statsObj.addProperty("produktu_kopurua", rs.getInt("totala"));
                statsObj.addProperty("bataz_besteko_prezioa",
                        Math.round(rs.getDouble("bataz_bestekoa") * 100.0) / 100.0);
                statsObj.addProperty("stock_totala", rs.getInt("stock_totala"));

                gson.toJson(statsObj, fw);
                System.out.println(" - 'estatistikak.json' ondo sortu da.");
            }

        } catch (SQLException | IOException e) {
            System.out.println("Errorea estatistikak esportatzean: " + e.getMessage());
        }
    }

    /* ----------------- 3. GEHIEN SALDUTAKOAK ESPORTATU ----------------- */
    private void esportatuGehienSaldutakoak() {
        String sql = "SELECT * FROM produktuak ORDER BY Prod_Stock ASC LIMIT 5";

        try (Connection con = DatuBaseConex.conectar();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                FileWriter fw = new FileWriter("gehien_saldutakoak.json")) {

            List<produktuak> salduenakZerrenda = new ArrayList<>();

            while (rs.next()) {
                /* Objetu bat sortzen dugu, oraingoan datu batzuk besterik ez ditugu behar */
                produktuak p = new produktuak(0, sql, sql, 0, 0, sql, sql, 0);
                p.setKod(rs.getInt("Prod_kod"));
                p.setIzena(rs.getString("Prod_Izena"));
                p.setStock(rs.getInt("Prod_Stock"));

                salduenakZerrenda.add(p);
            }

            gson.toJson(salduenakZerrenda, fw);
            System.out.println(" - 'gehien_saldutakoak.json' ondo sortu da.");

        } catch (SQLException | IOException e) {
            System.out.println("Errorea salduenak esportatzean: " + e.getMessage());
        }
    }

}
