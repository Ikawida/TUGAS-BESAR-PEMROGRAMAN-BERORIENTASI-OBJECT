package dao;

import model.produk;
import util.DatabaseUtil;

import java.sql.*;
import java.util.*;

public class produkdao {
    private Connection conn;

    public produkdao() throws SQLException {
        this.conn = DatabaseUtil.getConnection();
    }

    // Mengambil semua data produk dari database
    public List<produk> getAllProduk() throws SQLException {
        List<produk> list = new ArrayList<>();
        String sql = "SELECT * FROM produk";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            produk p = new produk(
                rs.getString("kode_produk"),
                rs.getString("nama_produk"),
                rs.getInt("harga"),
                rs.getInt("sisa_stok")
            );
            list.add(p);
        }

        return list;
    }

    // Mengambil satu produk berdasarkan kode_produk
    public produk getrodukByKode(String kodeProduk) throws SQLException {
        produk p = null;
        String sql = "SELECT * FROM produk WHERE kode_produk = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, kodeProduk);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            p = new produk(
                rs.getString("kode_produk"),
                rs.getString("nama_produk"),
                rs.getInt("harga"),
                rs.getInt("sisa_stok")
            );
        }

        return p;
    }

    // Update stok produk berdasarkan kode
    public void updateStok(String kodeProduk, int stokBaru) throws SQLException {
        String sql = "UPDATE produk SET sisa_stok = ? WHERE kode_produk = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, stokBaru);
        ps.setString(2, kodeProduk);
        ps.executeUpdate();
    }
}
