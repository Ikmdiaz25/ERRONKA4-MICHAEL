public class produktuak {
    private int kodea;
    private String izena;
    private String deskribapena;
    private double prezioa;
    private int stock;
    private String sokuntzaData;
    private String irudia;

    public produktuak(int kodea, String izena, String deskribapena, double prezioa, int stock, String sokuntzaData,
            String irudia) {
        this.kodea = kodea;
        this.izena = izena;
        this.deskribapena = deskribapena;
        this.prezioa = prezioa;
        this.stock = stock;
        this.sokuntzaData = sokuntzaData;
        this.irudia = irudia;
    }

    public String getSorkuntzaData() {
        return sokuntzaData;
    }

    public void setSorkuntzaData(String sokuntzaData) {
        this.sokuntzaData = sokuntzaData;
    }

    public int getKodea() {
        return kodea;
    }

    public String getIzena() {
        return izena;
    }

    public String getDeskribapena() {
        return deskribapena;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public int getStock() {
        return stock;
    }

    public String getIrudia() {
        return irudia;
    }

    public void setKod(int kodea) {
        this.kodea = kodea;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    public void setPrezioa(double prezioa) {
        this.prezioa = prezioa;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setIrudia(String irudia) {
        this.irudia = irudia;
    }

}
