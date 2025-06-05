package controller;

import dao.DetailTransaksiDAO;
import dao.ProdukDAO;
import dao.TransaksiDAO;
import model.DetailTransaksi;
import model.Produk;
import model.Transaksi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/transaksi")
public class TransaksiServlet extends HttpServlet {

    private Connection conn;
    private ProdukDAO produkDAO;
    private TransaksiDAO transaksiDAO;
    private DetailTransaksiDAO detailTransaksiDAO;

    @Override
    public void init() throws ServletException {
        try {
            // Ganti sesuai config DB kamu
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/toko", "root", ""); 

            produkDAO = new ProdukDAO(conn);
            transaksiDAO = new TransaksiDAO(conn);
            detailTransaksiDAO = new DetailTransaksiDAO(conn);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Produk> produkList = produkDAO.getAllProduk();
            request.setAttribute("produkList", produkList);
            request.getRequestDispatcher("transaksi.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Ambil semua produk dulu
            List<Produk> produkList = produkDAO.getAllProduk();

            // Parse input jumlah per produk
            Map<String, Integer> mapJumlah = new HashMap<>();
            int totalBayar = 0;

            for (Produk p : produkList) {
                String param = request.getParameter("jumlah_" + p.getKodeProduk());
                int jumlah = 0;
                if (param != null && !param.isEmpty()) {
                    try {
                        jumlah = Integer.parseInt(param);
                        if (jumlah < 0 || jumlah > p.getSisaStok()) jumlah = 0; // validasi stok
                    } catch (NumberFormatException ex) {
                        jumlah = 0;
                    }
                }
                if (jumlah > 0) {
                    mapJumlah.put(p.getKodeProduk(), jumlah);
                    totalBayar += jumlah * p.getHarga();
                }
            }

            if (mapJumlah.isEmpty()) {
                request.setAttribute("error", "Pilih minimal 1 produk dengan jumlah > 0");
                request.setAttribute("produkList", produkList);
                request.getRequestDispatcher("transaksi.jsp").forward(request, response);
                return;
            }

            int bayar = 0;
            try {
                bayar = Integer.parseInt(request.getParameter("bayar"));
            } catch (NumberFormatException e) {
                bayar = 0;
            }

            if (bayar < totalBayar) {
                request.setAttribute("error", "Uang bayar kurang dari total harga");
                request.setAttribute("produkList", produkList);
                request.getRequestDispatcher("transaksi.jsp").forward(request, response);
                return;
            }

            int kembalian = bayar - totalBayar;

            // Simpan transaksi
            Transaksi transaksi = new Transaksi();
            transaksi.setTanggal(new java.util.Date());
            transaksi.setTotalBayar(totalBayar);
            transaksi.setBayar(bayar);
            transaksi.setKembalian(kembalian);

            int transaksiId = transaksiDAO.tambahTransaksi(transaksi);

            // Simpan detail transaksi
            for (Map.Entry<String, Integer> entry : mapJumlah.entrySet()) {
                String kodeProduk = entry.getKey();
                int jumlah = entry.getValue();
                Produk produk = produkDAO.getProdukByKode(kodeProduk);

                DetailTransaksi detail = new DetailTransaksi();
                detail.setTransaksiId(transaksiId);
                detail.setKodeProduk(kodeProduk);
                detail.setJumlah(jumlah);
                detail.setSubtotal(jumlah * produk.getHarga());

                detailTransaksiDAO.tambahDetailTransaksi(detail);

                // Update stok produk
                produk.setSisaStok(produk.getSisaStok() - jumlah);
                produkDAO.updateProduk(produk);
            }

            // Redirect ke struk, bawa id transaksi
            response.sendRedirect("struk?id=" + transaksiId);

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
