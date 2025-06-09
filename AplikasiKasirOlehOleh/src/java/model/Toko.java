package model;

public class Toko {
    private String kodeProduk;
    private String namaProduk;

    public Toko() {}

    public Toko(String kodeProduk, String namaProduk) {
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }
}
