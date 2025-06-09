package model;

public class DetailTransaksi {
    private int id;
    private int transaksiId;
    private String kodeProduk;
    private String namaProduk;  
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

    public String getNamaProduk() {   // getter namaProduk
        return namaProduk;
    }
    public void setNamaProduk(String namaProduk) {  // setter namaProduk
        this.namaProduk = namaProduk;
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

    public void setTransaksi(Transaksi transaksi) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setProduk(Produk produk) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
