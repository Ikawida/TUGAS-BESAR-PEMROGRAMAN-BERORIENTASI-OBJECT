package dao;

import model.Produk;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdukDAO {

    public List<Produk> getAllProduk() {
        List<Produk> produkList = new ArrayList<>();
        String sql = "SELECT * FROM produk";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produk p = new Produk();
                p.setKodeProduk(rs.getString("kodeProduk"));
                p.setNamaProduk(rs.getString("namaProduk"));
                p.setHarga(rs.getInt("harga"));
                p.setSisaStok(rs.getInt("sisaStok"));
                produkList.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produkList;
    }

    public Produk getProdukByKode(String kodeProduk) {
        Produk produk = null;
        String sql = "SELECT * FROM produk WHERE kodeProduk = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kodeProduk);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                produk = new Produk();
                produk.setKodeProduk(rs.getString("kodeProduk"));
                produk.setNamaProduk(rs.getString("namaProduk"));
                produk.setHarga(rs.getInt("harga"));
                produk.setSisaStok(rs.getInt("sisaStok"));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produk;
    }

    public boolean insertProduk(Produk produk) {
        String sql = "INSERT INTO produk (kodeProduk, namaProduk, harga, sisaStok) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, produk.getKodeProduk());
            ps.setString(2, produk.getNamaProduk());
            ps.setInt(3, produk.getHarga());
            ps.setInt(4, produk.getSisaStok());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduk(Produk produk) {
        String sql = "UPDATE produk SET namaProduk = ?, harga = ?, sisaStok = ? WHERE kodeProduk = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, produk.getNamaProduk());
            ps.setInt(2, produk.getHarga());
            ps.setInt(3, produk.getSisaStok());
            ps.setString(4, produk.getKodeProduk());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduk(String kodeProduk) {
        String sql = "DELETE FROM produk WHERE kodeProduk = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kodeProduk);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method baru untuk update stok produk setelah penjualan
    public boolean updateStokProduk(String kodeProduk, int jumlahTerjual) {
        String sql = "UPDATE produk SET sisaStok = sisaStok - ? WHERE kodeProduk = ? AND sisaStok >= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jumlahTerjual);
            ps.setString(2, kodeProduk);
            ps.setInt(3, jumlahTerjual);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
