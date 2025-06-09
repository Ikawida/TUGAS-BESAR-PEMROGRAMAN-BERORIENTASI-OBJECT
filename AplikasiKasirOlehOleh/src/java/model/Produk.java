package model;

public class Produk extends Toko {
    private int harga;
    private int sisaStok;

    public Produk() {}

    public Produk(String kodeProduk, String namaProduk, int harga, int sisaStok) {
        super(kodeProduk, namaProduk);
        this.harga = harga;
        this.sisaStok = sisaStok;
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
