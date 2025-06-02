package dao;

import model.Produk;
import java.sql.*;
import java.util.*;

public class ProdukDAO {
    private Connection conn;

    public ProdukDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Produk> getAllProduk() throws SQLException {
        List<Produk> list = new ArrayList<>();
        String sql = "SELECT * FROM produk";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Produk p = new Produk(
                rs.getString("kode_produk"),
                rs.getString("nama_produk"),
                rs.getInt("harga"),
                rs.getInt("sisa_stok")
            );
            list.add(p);
        }
        return list;
    }

    public void addProduk(Produk produk) throws SQLException {
        String sql = "INSERT INTO produk (kode_produk, nama_produk, harga, sisa_stok) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produk.getKodeProduk());
        stmt.setString(2, produk.getNamaProduk());
        stmt.setInt(3, produk.getHarga());
        stmt.setInt(4, produk.getSisaStok());
        stmt.executeUpdate();
    }

    public void updateProduk(Produk produk) throws SQLException {
        String sql = "UPDATE produk SET nama_produk=?, harga=?, sisa_stok=? WHERE kode_produk=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produk.getNamaProduk());
        stmt.setInt(2, produk.getHarga());
        stmt.setInt(3, produk.getSisaStok());
        stmt.setString(4, produk.getKodeProduk());
        stmt.executeUpdate();
    }

    public void deleteProduk(String kodeProduk) throws SQLException {
        String sql = "DELETE FROM produk WHERE kode_produk=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, kodeProduk);
        stmt.executeUpdate();
    }

    public Produk getProdukByKode(String kodeProduk) throws SQLException {
        String sql = "SELECT * FROM produk WHERE kode_produk=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, kodeProduk);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Produk(
                rs.getString("kode_produk"),
                rs.getString("nama_produk"),
                rs.getInt("harga"),
                rs.getInt("sisa_stok")
            );
        }
        return null;
    }
}
