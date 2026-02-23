import java.util.*;

public class ProduktuakGehitu {

    public void produktuaGehitu(Scanner sc) {

        System.out.println("Sartu produktuaren izena");
        String prodIzena = sc.nextLine();

        System.out.println("Sartu produktuaren deskribapena");
        String prodDeskribapena = sc.nextLine();

        System.out.println("Sartu produktuaren prezioa");
        double prodPrezioa = sc.nextDouble();

        System.out.println("Sartu produktuaren stocka");
        int prodStock = sc.nextInt();

        System.out.println("Sartu produktuaren kategoria");
        String prodKategoria = sc.nextLine();

        System.out.println("Sartu produktuaren irudia");
        String prodIrudia = sc.nextLine();

    }
}
