package dao;

import model.DetailTransaksi;
import java.sql.*;
import java.util.*;

public class DetailTransaksiDAO {
    private Connection conn;

    public DetailTransaksiDAO(Connection conn) {
        this.conn = conn;
    }

    public void tambahDetailTransaksi(DetailTransaksi detail) throws SQLException {
        String sql = "INSERT INTO item_transaksi (transaksi_id, kode_produk, jumlah, subtotal) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, detail.getTransaksiId());
        stmt.setString(2, detail.getKodeProduk());
        stmt.setInt(3, detail.getJumlah());
        stmt.setInt(4, detail.getSubtotal());
        stmt.executeUpdate();
    }

    public List<DetailTransaksi> getDetailsByTransaksiId(int transaksiId) throws SQLException {
        List<DetailTransaksi> list = new ArrayList<>();
        String sql = "SELECT i.id, i.transaksi_id, i.kode_produk, i.jumlah, i.subtotal, p.nama_produk " +
                     "FROM item_transaksi i " +
                     "JOIN produk p ON i.kode_produk = p.kode_produk " +
                     "WHERE i.transaksi_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, transaksiId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            DetailTransaksi detail = new DetailTransaksi();
            detail.setId(rs.getInt("id"));
            detail.setTransaksiId(rs.getInt("transaksi_id"));
            detail.setKodeProduk(rs.getString("kode_produk"));
            detail.setJumlah(rs.getInt("jumlah"));
            detail.setSubtotal(rs.getInt("subtotal"));
            detail.setNamaProduk(rs.getString("nama_produk"));
            list.add(detail);
        }
        return list;
    }
}
