package controller;

import dao.DetailTransaksiDAO;
import dao.TransaksiDAO;
import dao.ProdukDAO;    // <-- Import ProdukDAO
import model.DetailTransaksi;
import model.Transaksi;
import model.Produk;     // <-- Import Produk

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/struk")
public class StrukServlet extends HttpServlet {

    private Connection conn;
    private TransaksiDAO transaksiDAO;
    private DetailTransaksiDAO detailTransaksiDAO;
    private ProdukDAO produkDAO;    // <-- Tambah field ProdukDAO

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/toko", "root", "");

            transaksiDAO = new TransaksiDAO(conn);
            detailTransaksiDAO = new DetailTransaksiDAO(conn);
            produkDAO = new ProdukDAO(conn);    // <-- Inisialisasi ProdukDAO
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            response.sendRedirect("transaksi");
            return;
        }

        try {
            Transaksi transaksi = transaksiDAO.getTransaksiById(id);
            if (transaksi == null) {
                response.sendRedirect("transaksi");
                return;
            }

            List<DetailTransaksi> details = detailTransaksiDAO.getDetailsByTransaksiId(id);

            // Tambahkan nama produk ke tiap DetailTransaksi
            for (DetailTransaksi d : details) {
                Produk produk = produkDAO.getProdukByKode(d.getKodeProduk());
                if (produk != null) {
                    d.setNamaProduk(produk.getNamaProduk());
                } else {
                    d.setNamaProduk("-");
                }
            }

            request.setAttribute("transaksi", transaksi);
            request.setAttribute("details", details);
            request.getRequestDispatcher("struk.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    @Override
    public void destroy() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
