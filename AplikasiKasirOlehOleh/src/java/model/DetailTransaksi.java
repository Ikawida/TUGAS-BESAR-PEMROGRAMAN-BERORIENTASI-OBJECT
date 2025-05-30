package model;

public class DetailTransaksi {
    private int id;
    private int transaksiId;
    private String kodeProduk;
    private int jumlah;
    private int subtotal;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getTransaksiId() {
        return transaksiId;
    }
    public void setTransaksiId(int transaksiId) {
        this.transaksiId = transaksiId;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }
    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
