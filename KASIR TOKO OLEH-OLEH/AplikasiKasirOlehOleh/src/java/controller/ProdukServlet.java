package controller;

import dao.ProdukDAO;
import model.Produk;
import util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/produk")
public class ProdukServlet extends HttpServlet {
    private ProdukDAO produkDAO;

    public void init() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            produkDAO = new ProdukDAO(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ProdukServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String kode = request.getParameter("kode");

        try {
            Produk produkEdit = null;

            if ("edit".equals(action) && kode != null) {
                produkEdit = produkDAO.getProdukByKode(kode);
                request.setAttribute("produkEdit", produkEdit);
            } else if ("delete".equals(action) && kode != null) {
                produkDAO.deleteProduk(kode);
                response.sendRedirect("produk");
                return;
            }

            List<Produk> produkList = produkDAO.getAllProduk();
            request.setAttribute("produkList", produkList);
            request.getRequestDispatcher("produk.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kode = request.getParameter("kodeProduk");
        String nama = request.getParameter("namaProduk");
        int harga = Integer.parseInt(request.getParameter("harga"));
        int stok = Integer.parseInt(request.getParameter("sisaStok"));
        Produk produk = new Produk(kode, nama, harga, stok);

        try {
            String isUpdate = request.getParameter("isUpdate");
            if ("true".equals(isUpdate)) {
                produkDAO.updateProduk(produk);
            } else {
                produkDAO.addProduk(produk);
            }
            response.sendRedirect("produk");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
