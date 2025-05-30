package model;

public class ItemTransaksi {
    private produk produk;
    private int jumlah;

    public ItemTransaksi(produk produk, int jumlah) {
        this.produk = produk;
        this.jumlah = jumlah;
    }

    public produk getProduk() {
        return produk;
    }

    public void setProduk(produk produk) {
        this.produk = produk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
