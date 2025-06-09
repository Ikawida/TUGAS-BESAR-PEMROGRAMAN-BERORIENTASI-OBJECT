package dao;

import model.Transaksi;
import java.sql.*;

public class TransaksiDAO {
    private Connection conn;

    public TransaksiDAO(Connection conn) {
        this.conn = conn;
    }

    public int tambahTransaksi(Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO transaksi (tanggal, total_bayar, bayar, kembalian) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // Simpan tanggal lengkap dengan jam menggunakan Timestamp
        stmt.setTimestamp(1, new java.sql.Timestamp(transaksi.getTanggal().getTime()));

        stmt.setInt(2, transaksi.getTotalBayar());
        stmt.setInt(3, transaksi.getBayar());
        stmt.setInt(4, transaksi.getKembalian());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // id transaksi yang baru
        }
        return -1;
    }

    public Transaksi getTransaksiById(int id) throws SQLException {
        String sql = "SELECT * FROM transaksi WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Transaksi t = new Transaksi();

            t.setId(rs.getInt("id"));

            t.setTanggal(rs.getTimestamp("tanggal")); // Ambil tanggal lengkap dengan jam menggunakan Timestamp
            t.setTotalBayar(rs.getInt("total_bayar"));
            t.setBayar(rs.getInt("bayar"));
            t.setKembalian(rs.getInt("kembalian"));

            return t;
        }
        return null;
    }
}
