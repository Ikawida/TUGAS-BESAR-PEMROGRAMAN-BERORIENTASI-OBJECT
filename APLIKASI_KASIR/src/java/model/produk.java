package model;

public class produk {
    private String kodeProduk;
    private String namaProduk;
    private int harga;
    private int stok;

    public produk(String kodeProduk, String namaProduk, int harga, int stok) {
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKodeProduk() { return kodeProduk; }
    public String getNamaProduk() { return namaProduk; }
    public int getHarga() { return harga; }
    public int getStok() { return stok; }
}
