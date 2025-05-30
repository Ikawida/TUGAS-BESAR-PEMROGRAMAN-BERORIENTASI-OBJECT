package dao;

import model.Transaksi;
import util.DatabaseConnection;

import java.sql.*;
import java.util.Date;

public class TransaksiDAO {

    public int insertTransaksi(Transaksi transaksi) {
        int generatedId = -1;
        String sql = "INSERT INTO transaksi (tanggal, total_harga) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setTimestamp(1, new Timestamp(transaksi.getTanggal().getTime()));
            ps.setInt(2, transaksi.getTotalHarga());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Insert transaksi gagal, tidak ada baris yang terpengaruh.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Insert transaksi gagal, ID tidak didapatkan.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public Transaksi getTransaksiById(int id) {
        Transaksi transaksi = null;
        String sql = "SELECT * FROM transaksi WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    transaksi = new Transaksi();
                    transaksi.setId(rs.getInt("id"));
                    transaksi.setTanggal(rs.getTimestamp("tanggal"));
                    transaksi.setTotalHarga(rs.getInt("total_harga"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaksi;
    }
}
