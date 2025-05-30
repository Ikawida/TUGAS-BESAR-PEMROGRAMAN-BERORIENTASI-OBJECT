package dao;

import model.DetailTransaksi;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailTransaksiDAO {

    public boolean insertDetailTransaksi(DetailTransaksi detail) {
        String sql = "INSERT INTO detail_transaksi (transaksi_id, kode_produk, jumlah, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detail.getTransaksiId());
            ps.setString(2, detail.getKodeProduk());
            ps.setInt(3, detail.getJumlah());
            ps.setInt(4, detail.getSubtotal());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<DetailTransaksi> getDetailsByTransaksiId(int transaksiId) {
        List<DetailTransaksi> detailList = new ArrayList<>();
        String sql = "SELECT * FROM detail_transaksi WHERE transaksi_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, transaksiId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetailTransaksi detail = new DetailTransaksi();
                    detail.setId(rs.getInt("id"));
                    detail.setTransaksiId(rs.getInt("transaksi_id"));
                    detail.setKodeProduk(rs.getString("kode_produk"));
                    detail.setJumlah(rs.getInt("jumlah"));
                    detail.setSubtotal(rs.getInt("subtotal"));
                    detailList.add(detail);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detailList;
    }

    public List<DetailTransaksi> getDetailByTransaksiId(int transaksiId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
