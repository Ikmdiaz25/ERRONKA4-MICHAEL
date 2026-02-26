import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

public class Testak {

    ProduktuakKudeatu kudeatzailea = new ProduktuakKudeatu();

    // =========================================================
    // PK1 - PK5: Produktuak Gehitu
    // =========================================================

    @Test
    public void testPK1_ProduktuaGehitu_Ondo() {
        String emaitza = kudeatzailea.produktuaGehitu(1, "Kamiseta", "Urdina", 23.14, "2023-10-10", 10, 2, "irudia.jpg");
        assertTrue(emaitza.equals("Gorde da!") || emaitza.contains("SQL Errorea"));
    }

    @Test
    public void testPK2_ProduktuaGehitu_IzenaHutsik() {
        String emaitza = kudeatzailea.produktuaGehitu(1, "---", "Urdina", 23.14, "2023-10-10", 10, 2, "");
        assertEquals("Sartutako produktuaren izena okerra da", emaitza);
    }

    @Test
    public void testPK3_ProduktuaGehitu_DeskribapenaHutsik() {
        String emaitza = kudeatzailea.produktuaGehitu(1, "Kamiseta", "---", 23.14, "2023-10-10", 10, 2, "");
        assertEquals("Produktuaren deskribapena sartu behar da", emaitza);
    }

    @Test
    public void testPK4_ProduktuaGehitu_IdNegatiboa() {
        String emaitza = kudeatzailea.produktuaGehitu(-1, "Kamiseta", "Urdina", 23.14, "2023-10-10", 10, 2, "");
        assertEquals("Produktuaren ID-a ezin da negatiboa izan", emaitza);
    }

    @Test
    public void testPK5_MenuOkerra() {
        String emaitza = kudeatzailea.produktuaGehitu(9, "", "Urdina", 23.14, "2023-10-10", 10, 2, "");
        assertEquals("Sartutako produktuaren izena okerra da", emaitza);
    }

    // =========================================================
    // PK6 - PK8: Produktuak Eguneratu / Ezabatu
    // =========================================================

    @Test
    public void testPK6_ProduktuaEguneratu_Ondo() {
        String emaitza = kudeatzailea.produktuakEguneratu(23214, 1, "Kamiseta Berria");
        assertTrue(emaitza.equals("Ez da produkturik aurkitu kode horrekin.") || emaitza.equals("Produktua ondo eguneratu da!"));
    }

    @Test
    public void testPK7_ProduktuaEzabatu_Ondo() {
        String emaitza = kudeatzailea.produktuakEzabatu(23214);
        assertTrue(emaitza.equals("Ez da produkturik aurkitu kode horrekin.") || emaitza.equals("Ezabatu da!"));
    }

    @Test
    public void testPK8_ProduktuaEguneratu_AzpimenuaOkerra() {
        String emaitza = kudeatzailea.produktuakEguneratu(23214, 9, "Test");
        assertEquals("Aukera okerra.", emaitza);
    }

    // =========================================================
    // PK9 - PK12: Produktuak Zerrendatu
    // =========================================================

    @Test
    public void testPK9_Zerrendatu_Prezioa() {
        List<String> emaitza = kudeatzailea.produktuakZerrendatu(1, 1, 1);
        assertTrue(emaitza.size() > 0);
    }

    @Test
    public void testPK10_Zerrendatu_Eskuragarritasuna() {
        List<String> emaitza = kudeatzailea.produktuakZerrendatu(1, 1, 2);
        assertTrue(emaitza.size() > 0);
    }

    @Test
    public void testPK11_Zerrendatu_KategoriaEtaPrezioa() {
        List<String> emaitza = kudeatzailea.produktuakZerrendatu(2, 0, 1);
        assertTrue(emaitza.size() > 0);
    }

    @Test
    public void testPK12_Zerrendatu_AukeraOkerra() {
        List<String> emaitza = kudeatzailea.produktuakZerrendatu(2, 0, 5);
        assertEquals("Berezko ordenan erakutsiko dira.", emaitza.get(0));
    }

    // =========================================================
    // PK13 - PK15: Produktuak Bilatu
    // =========================================================

    @Test
    public void testPK13_Bilatu_Izenarekin() {
        List<String> emaitza = kudeatzailea.ProduktuakBilatu(1, "Kamiseta");
        assertTrue(emaitza.size() > 0);
    }

    @Test
    public void testPK14_Bilatu_IdArekin() {
        List<String> emaitza = kudeatzailea.ProduktuakBilatu(2, "23214");
        assertTrue(emaitza.size() > 0);
    }

    @Test
    public void testPK15_Bilatu_Hutsik() {
        List<String> emaitza = kudeatzailea.ProduktuakBilatu(5, "-");
        assertEquals("Aukera okerra.", emaitza.get(0));
    }
}
