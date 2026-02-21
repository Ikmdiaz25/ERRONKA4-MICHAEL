import java.util.*;

public class App {

    // Programaren menu nagusia
    public static void main(String[] args) throws Exception {
        System.out.println("Ongi etorri IndiUsurbil-eko aplikaziora!");
        System.out.println("------MENUA------");
        System.out.println("1. Produktuak gehitu");
        System.out.println("2. CSV fitxategia igo");
        System.out.println("3. Produktuak eguneratu");
        System.out.println("4. Produktuak zerrendatu");
        System.out.println("5. Produktuak bilatu");
        System.out.println("6. Irten");

        // Aurreko menuaren aukera gordetzeko
        Scanner sc = new Scanner(System.in);
        int aukera = sc.nextInt();

        // Aukeraren arabera egingo diren ekintzak
        switch (aukera) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            default:
                System.out.println("Aukera baliogabea");
                break;
        }

    }
}
