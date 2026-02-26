import java.util.*;

public class App {

    // Programaren menu nagusia
    public static void main(String[] args) throws Exception {
        System.out.println("Ongi etorri IndiUsurbil-eko aplikaziora!");
        System.out.println("------MENUA------");
        System.out.println("1. Produktuak gehitu");
        System.out.println("2. CSV fitxategia igo");
        System.out.println("3. Produktuak eguneratu");
        System.out.println("4. Produktuak ezabatu");
        System.out.println("5. Produktuak bilatu");
        System.out.println("6. Informazioa exportatu");
        System.out.println("7. Produktuak zerrendatu");
        System.out.println("8. Irten");

        // Aurreko menuaren aukera gordetzeko
        Scanner sc = new Scanner(System.in);
        int aukera = sc.nextInt();

        // Aukeraren arabera egingo diren ekintzak
        switch (aukera) {
            case 1:

                // Hemen sartuko da produktua gehitzen den kodea
                // Hau da ProduktuakGehitu(); metodo antzeko bat da

                ProduktuakKudeatu.produktuaGehitu();

                break;
            case 2:

                // Hemen sartuko da CSV fitxategia igoen den kodea
                // Hau da CSVigo(); metodo antzeko bat da

                CSVFitxategia.csvIgo();

                break;
            case 3:

                // Hemen sartuko da produktua eguneratzen den kodea
                // Hau da ProduktuakEguneratu(); metodo antzeko bat da

                ProduktuakKudeatu.produktuakEguneratu();

                break;
            case 4:
                // Hemen sartuko da produktua ezabatzen den kodea
                // Hau da ProduktuakEzabatu(); metodo antzeko bat da

                ProduktuakKudeatu.produktuakEzabatu();

                break;
            case 5:

                // Hemen sartuko da produktuak bilatzen den kodea
                // Hau da ProduktuakBilatu(); metodo antzeko bat da

                ProduktuakKudeatu.ProduktuakBilatu();

                break;
            case 6:

                // Hemen sartuko da informazioa exportatzen den kodea
                // Hau da ProduktuakExportatu(); metodo antzeko bat da

                JSONExportatu jsonExportatu = new JSONExportatu();
                jsonExportatu.esportatuDatuak();

                break;

            case 7:

                // Hemen sartuko da produktuak zerrendatzen den kodea
                // Hau da ProduktuakZerrendatu(); metodo antzeko bat da

                ProduktuakKudeatu.produktuakZerrendatu();

                break;
            case 8:

                // Hemen sartuko da aplikazioa itxi den kodea
                System.out.println("Aplikazioa itxi da. Agur!");
                System.exit(0);

                break;

            default:
                System.out.println("Aukera baliogabea");
                break;
        }

    }
}
