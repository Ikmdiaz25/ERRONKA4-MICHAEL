import java.util.*;

public class App {

    // Programaren menu nagusia
    public static void main(String[] args) throws Exception {

        while (true) {
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
            sc.nextLine();

            // Aukeraren arabera egingo diren ekintzak
            switch (aukera) {
                case 1:
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

                    System.out.println("Sartu produktuaren kategoria kodea");
                    int prodKategoria = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Sartu produktuaren irudia");
                    String prodIrudia = sc.nextLine();

                    String emaitzaGehitu = ProduktuakKudeatu.produktuaGehitu(
                            prodKodea, prodIzena, prodDeskribapena, prodPrezioa,
                            prodSorkuntzaData, prodStock, prodKategoria, prodIrudia);
                    System.out.println(emaitzaGehitu);

                    break;
                case 2:
                    CSVFitxategia.csvIgo();
                    break;
                case 3:
                    System.out.println("\n----- PRODUKTUA BERRITU -----");
                    System.out.print("Sartu eguneratu nahi den produktuaren kodea (Prod_kod): ");
                    int kodeaEguneratu = sc.nextInt();
                    sc.nextLine();

                    System.out.println("\nZer aldatu nahi duzu?");
                    System.out.println("1. Izena");
                    System.out.println("2. Deskribapena");
                    System.out.println("3. Prezioa");
                    System.out.println("4. Stocka");
                    System.out.println("5. Kategoria kodea");
                    System.out.println("6. Irudiaren URLa");
                    System.out.print("Aukeratu zenbaki bat: ");

                    int aukeraEguneratu = sc.nextInt();
                    sc.nextLine();

                    if (aukeraEguneratu < 1 || aukeraEguneratu > 6) {
                        System.out.println("Aukera okerra.");
                        break;
                    }

                    System.out.print("Sartu balio berria: ");
                    String balioBerria = sc.nextLine();

                    String eguneratuEmaitza = ProduktuakKudeatu.produktuakEguneratu(kodeaEguneratu, aukeraEguneratu,
                            balioBerria);
                    System.out.println(eguneratuEmaitza);

                    break;
                case 4:
                    System.out.println("Sartu ezabatu nahi den produktuaren kodea:");
                    int ezabatuKodea = sc.nextInt();
                    sc.nextLine();

                    String ezabatuEmaitza = ProduktuakKudeatu.produktuakEzabatu(ezabatuKodea);
                    System.out.println(ezabatuEmaitza);

                    break;
                case 5:
                    System.out.println("-----PRODUKTUAK BILATU-----");
                    System.out.println("1.Bilatu izenaren arabera");
                    System.out.println("2.Bilatu kodearen arabera");

                    System.out.println("Aukeratu modu bat (1 edo 2): ");
                    int aukeraBilatu = sc.nextInt();
                    sc.nextLine();

                    String balioa = "";
                    if (aukeraBilatu == 1) {
                        System.out.println("Sartu produktuaren izena (edo zati bat):");
                        balioa = sc.nextLine();
                    } else if (aukeraBilatu == 2) {
                        System.out.println("Sartu produktuaren kodea:");
                        balioa = String.valueOf(sc.nextInt());
                        sc.nextLine();
                    }

                    List<String> bilatuEmaitzak = ProduktuakKudeatu.ProduktuakBilatu(aukeraBilatu, balioa);
                    System.out.println("----EMAITZAK----");
                    for (String s : bilatuEmaitzak) {
                        System.out.println(s);
                    }

                    break;
                case 6:
                    JSONExportatu jsonExportatu = new JSONExportatu();
                    jsonExportatu.esportatuDatuak();

                    break;

                case 7:
                    System.out.println("----PRODUKTUAK ZERRENDATU-----");

                    System.out.println("Nola ikusi nahi dituzu produktuak?");
                    System.out.println("1. Kategoriaren arabera");
                    System.out.println("2. Produktu guztiak");
                    int aukera1 = sc.nextInt();
                    sc.nextLine();

                    int kategKodZerrendatu = 0;
                    if (aukera1 == 1) {
                        System.out.println("Sartu kategoriaren kodea");
                        kategKodZerrendatu = sc.nextInt();
                        sc.nextLine();
                    }

                    System.out.println("\nNola ordenatu nahi dituzu emaitzak?");
                    System.out.println("1. Prezioaren arabera (Merkeenetik garestienera)");
                    System.out.println("2. Stockaren arabera (Gutxienetik gehienera)");
                    System.out.println("3. Berezko ordena");
                    System.out.print("Aukeratu zenbaki bat: ");

                    int ordena = sc.nextInt();
                    sc.nextLine();

                    List<String> zerrendatuEmaitzak = ProduktuakKudeatu.produktuakZerrendatu(aukera1,
                            kategKodZerrendatu,
                            ordena);
                    System.out.println("\n--- PRODUKTUEN ZERRENDA ---");
                    for (String s : zerrendatuEmaitzak) {
                        System.out.println(s);
                    }

                    break;
                case 8:
                    System.out.println("Aplikazioa itxi da. Agur!");
                    sc.close();
                    System.exit(0);

                    break;

                default:
                    System.out.println("Aukera baliogabea");
                    break;
            }
        }

    }
}
