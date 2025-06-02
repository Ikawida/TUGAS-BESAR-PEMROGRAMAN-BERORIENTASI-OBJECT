package model;

public class Produk {
    private String kodeProduk;
    private String namaProduk;
    private int harga;
    private int sisaStok;

    public Produk() {}

    public Produk(String kodeProduk, String namaProduk, int harga, int sisaStok) {
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.sisaStok = sisaStok;
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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getSisaStok() {
        return sisaStok;
    }

    public void setSisaStok(int sisaStok) {
        this.sisaStok = sisaStok;
    }
}
