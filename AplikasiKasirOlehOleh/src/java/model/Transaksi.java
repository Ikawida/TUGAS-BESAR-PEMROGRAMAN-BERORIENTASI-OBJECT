package model;

import java.util.Date;

public class Transaksi {
    private int id;
    private Date tanggal;
    private int totalBayar;
    private int bayar;
    private int kembalian;

    // getter setter untuk semua atribut
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }

    public int getTotalBayar() { return totalBayar; }
    public void setTotalBayar(int totalBayar) { this.totalBayar = totalBayar; }

    public int getBayar() { return bayar; }
    public void setBayar(int bayar) { this.bayar = bayar; }

    public int getKembalian() { return kembalian; }
    public void setKembalian(int kembalian) { this.kembalian = kembalian; }
}
