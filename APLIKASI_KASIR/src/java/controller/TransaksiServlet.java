package controller;

import dao.produkdao;
import dao.TransaksiDAO;
import model.produk;
import model.Transaksi;
import model.ItemTransaksi;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class TransaksiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            produkdao pdao = new produkdao();
            List<produk> produkList = pdao.getAllProduk();
            req.setAttribute("produkList", produkList);
            RequestDispatcher rd = req.getRequestDispatcher("transaksi.jsp");
            rd.forward(req, res);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            produkdao pdao = new produkdao();
            Transaksi transaksi = new Transaksi();

            String[] kodeProduk = req.getParameterValues("kode_produk");
            String[] jumlahBeli = req.getParameterValues("jumlah");

            for (int i = 0; i < kodeProduk.length; i++) {
                produk p = pdao.getrodukByKode(kodeProduk[i]);
                int jumlah = Integer.parseInt(jumlahBeli[i]);
                transaksi.tambahItem(new ItemTransaksi(p, jumlah));
            }

            int bayar = Integer.parseInt(req.getParameter("bayar"));
            transaksi.setBayar(bayar);

            new TransaksiDAO().simpanTransaksi(transaksi);

            req.setAttribute("transaksi", transaksi);
            RequestDispatcher rd = req.getRequestDispatcher("Struk.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
