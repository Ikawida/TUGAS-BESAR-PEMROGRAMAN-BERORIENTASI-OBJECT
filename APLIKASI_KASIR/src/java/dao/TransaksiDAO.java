package dao;

import model.*;
import util.DatabaseUtil;

import java.sql.*;

public class TransaksiDAO {
    public void simpanTransaksi(Transaksi transaksi) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        try {
            conn.setAutoCommit(false);

            String sql1 = "INSERT INTO transaksi (tanggal, total_bayar, bayar, kembalian) VALUES (?, ?, ?, ?)";
            PreparedStatement ps1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            ps1.setDate(1, Date.valueOf(transaksi.getTanggal()));
            ps1.setInt(2, transaksi.hitungTotal());
            ps1.setInt(3, transaksi.getBayar());
            ps1.setInt(4, transaksi.hitungKembalian());
            ps1.executeUpdate();

            ResultSet rs = ps1.getGeneratedKeys();
            rs.next();
            int idTransaksi = rs.getInt(1);

            String sql2 = "INSERT INTO item_transaksi (transaksi_id, kode_produk, jumlah, subtotal) VALUES (?, ?, ?, ?)";
            PreparedStatement ps2 = conn.prepareStatement(sql2);

            for (ItemTransaksi item : transaksi.getItemList()) {
                ps2.setInt(1, idTransaksi);
                ps2.setString(2, item.getProduk().getKodeProduk());
                ps2.setInt(3, item.getJumlah());
                ps2.setInt(4, item.getSubtotal());
                ps2.addBatch();

                // Update stok
                int stokBaru = item.getProduk().getStok() - item.getJumlah();
                new produkdao().updateStok(item.getProduk().getKodeProduk(), stokBaru);
            }

            ps2.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }
}
