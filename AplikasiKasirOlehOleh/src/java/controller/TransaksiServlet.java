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
import java.util.Date;

@WebServlet("/transaksi")
public class TransaksiServlet extends HttpServlet {
    private ProdukDAO produkDAO = new ProdukDAO();
    private TransaksiDAO transaksiDAO = new TransaksiDAO();
    private DetailTransaksiDAO detailDAO = new DetailTransaksiDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("produkList", produkDAO.getAllProduk());
        request.getRequestDispatcher("transaksi.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kodeProduk = request.getParameter("kodeProduk");
        int jumlah = Integer.parseInt(request.getParameter("jumlah"));

        Produk produk = produkDAO.getProdukByKode(kodeProduk);

        if (produk == null || produk.getSisaStok() < jumlah) {
            request.setAttribute("errorMessage", "Stok tidak cukup atau produk tidak ditemukan.");
            doGet(request, response);
            return;
        }

        Transaksi transaksi = new Transaksi();
        transaksi.setTanggal(new Date());
        transaksi.setTotalHarga(produk.getHarga() * jumlah);

        int transaksiId = transaksiDAO.insertTransaksi(transaksi);

        if (transaksiId > 0) {
            DetailTransaksi detail = new DetailTransaksi();
            detail.setTransaksiId(transaksiId);
            detail.setKodeProduk(kodeProduk);
            detail.setJumlah(jumlah);
            detail.setSubtotal(produk.getHarga() * jumlah);

            boolean detailSuccess = detailDAO.insertDetailTransaksi(detail);
            boolean stokSuccess = produkDAO.updateStokProduk(kodeProduk, jumlah);

            if (detailSuccess && stokSuccess) {
                response.sendRedirect("struk?transaksiId=" + transaksiId);
                return;
            }
        }

        request.setAttribute("errorMessage", "Gagal memproses transaksi.");
        doGet(request, response);
    }
}
