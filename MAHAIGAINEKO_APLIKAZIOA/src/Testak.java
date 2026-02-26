import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testak {

    ProduktuakKudeatu kudeatzailea = new ProduktuakKudeatu();

    // =========================================================
    // PK1 - PK5: Produktuak Gehitu
    // =========================================================

    @Test
    public void testPK1_ProduktuaGehitu_Ondo() {
        // Sarrera: 1, Kamiseta, Urdina, 23214, Ez
        String emaitza = kudeatzailea.produktuaGehitu(1, "Kamiseta", "Urdina", 23214, "Ez");
        assertEquals("Kamiseta gehitu egin da", emaitza);
    }

    @Test
    public void testPK2_ProduktuaGehitu_IzenaHutsik() {
        // Sarrera: 1, ---, Urdina, 23214, Ez
        String emaitza = kudeatzailea.produktuaGehitu(1, "---", "Urdina", 23214, "Ez");
        assertEquals("Sartutako produktuaren izena okerra da", emaitza);
    }

    @Test
    public void testPK3_ProduktuaGehitu_DeskribapenaHutsik() {
        // Sarrera: 1, Kamiseta, ---, 23214, Ez
        String emaitza = kudeatzailea.produktuaGehitu(1, "Kamiseta", "---", 23214, "Ez");
        assertEquals("Produktuaren deskribapena sartu behar da", emaitza);
    }

    @Test
    public void testPK4_ProduktuaGehitu_IdNegatiboa() {
        // Sarrera: 1, Kamiseta, Urdina, -23214, Ez
        String emaitza = kudeatzailea.produktuaGehitu(1, "Kamiseta", "Urdina", -23214, "Ez");
        assertEquals("Produktuaren ID-a ezin da negatiboa izan", emaitza);
    }

    @Test
    public void testPK5_MenuOkerra() {
        // Sarrera: 9, Kamiseta, Urdina, 23214, Ez
        String emaitza = kudeatzailea.produktuaGehitu(9, "Kamiseta", "Urdina", 23214, "Ez");
        assertEquals("Mesedez sartu 1 eta 6 arteko zenbaki bat", emaitza);
    }

    // =========================================================
    // PK6 - PK8: Produktuak Eguneratu / Ezabatu
    // =========================================================

    @Test
    public void testPK6_ProduktuaEguneratu_Ondo() {
        // Sarrera: 3, 1, 23214
        String emaitza = kudeatzailea.produktuakEguneratu(3, "1", 23214);
        assertEquals("Produktua zuzenki eguneratu egin da", emaitza);
    }

    @Test
    public void testPK7_ProduktuaEzabatu_Ondo() {
        // Sarrera: 3, 2, 23214
        String emaitza = kudeatzailea.produktuakEguneratu(3, "2", 23214);
        assertEquals("Produktua ezabatu da", emaitza);
    }

    @Test
    public void testPK8_ProduktuaEguneratu_AzpimenuaOkerra() {
        // Sarrera: 3, -, 23214
        String emaitza = kudeatzailea.produktuakEguneratu(3, "-", 23214);
        assertEquals("Produktua eguneratzeko aukera ez da zuzena, 1-tik 3-ra izan behar da", emaitza);
    }

    // =========================================================
    // PK9 - PK12: Produktuak Zerrendatu
    // =========================================================

    @Test
    public void testPK9_Zerrendatu_Prezioa() {
        // Sarrera: 4, 1, 1
        String emaitza = kudeatzailea.produktuakZerrendatu(4, 1, 1);
        assertEquals("Produktuak prezioaren arabera: ", emaitza);
    }

    @Test
    public void testPK10_Zerrendatu_Eskuragarritasuna() {
        // Sarrera: 4, 1, 2
        String emaitza = kudeatzailea.produktuakZerrendatu(4, 1, 2);
        assertEquals("Produktuak eskuragarritasun arabera:", emaitza);
    }

    @Test
    public void testPK11_Zerrendatu_KategoriaEtaPrezioa() {
        // Sarrera: 4, 2, 1
        String emaitza = kudeatzailea.produktuakZerrendatu(4, 2, 1);
        assertEquals("Kategoriaren arabera eta prezioaren arabera:", emaitza);
    }

    @Test
    public void testPK12_Zerrendatu_AukeraOkerra() {
        // Sarrera: 4, 5, 2
        String emaitza = kudeatzailea.produktuakZerrendatu(4, 5, 2);
        assertEquals("Sartu 1 edo 2 zenbakia", emaitza);
    }

    // =========================================================
    // PK13 - PK15: Produktuak Bilatu
    // =========================================================

    @Test
    public void testPK13_Bilatu_Izenarekin() {
        // Sarrera: 5, Kamiseta, Ez
        // Nota: Si usas el mismo método para buscar por nombre o por ID, pásale los
        // parámetros correspondientes.
        String emaitza = kudeatzailea.produktuakBilatu(5, "Kamiseta", "Ez");
        assertEquals("Kamisetaren informazioa honakoa da:", emaitza);
    }

    @Test
    public void testPK14_Bilatu_IdArekin() {
        // Sarrera: 5, 23214, Ez
        String emaitza = kudeatzailea.produktuakBilatu(5, "23214", "Ez");
        assertEquals("Kamisetaren informazioa honakoa da:", emaitza);
    }

    @Test
    public void testPK15_Bilatu_Hutsik() {
        // Sarrera: 5, -, Ez
        String emaitza = kudeatzailea.produktuakBilatu(5, "-", "Ez");
        assertEquals("Mesedez sartu produktuaren informazioa bilaketa egiteko", emaitza);
    }
}
