package controller;

import dao.ProdukDAO;
import model.Produk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/produk")
public class ProdukServlet extends HttpServlet {
    private ProdukDAO produkDAO = new ProdukDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produk> produkList = produkDAO.getAllProduk();
        request.setAttribute("produkList", produkList);

        String kodeEdit = request.getParameter("edit");
        if (kodeEdit != null) {
            Produk produkEdit = produkDAO.getProdukByKode(kodeEdit);
            request.setAttribute("produkEdit", produkEdit);
        }

        request.getRequestDispatcher("produk.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equalsIgnoreCase(action)) {
            handleInsert(request, response);
        } else if ("update".equalsIgnoreCase(action)) {
            handleUpdate(request, response);
        } else if ("updateStok".equalsIgnoreCase(action)) {
            handleUpdateStok(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            handleDelete(request, response);
        } else if ("beli".equalsIgnoreCase(action)) {
            handleTransaksi(request, response);
        } else {
            response.sendRedirect("produk");
        }
    }

    private void handleInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kodeProduk = request.getParameter("kodeProduk");
        String namaProduk = request.getParameter("namaProduk");
        int harga = Integer.parseInt(request.getParameter("harga"));
        int sisaStok = Integer.parseInt(request.getParameter("sisaStok"));

        Produk produk = new Produk(kodeProduk, namaProduk, harga, sisaStok);
        boolean success = produkDAO.insertProduk(produk);

        if (!success) {
            request.setAttribute("errorMessage", "Gagal menambahkan produk.");
            doGet(request, response);
        } else {
            response.sendRedirect("produk");
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kodeProduk = request.getParameter("kodeProduk");
        String namaProduk = request.getParameter("namaProduk");
        int harga = Integer.parseInt(request.getParameter("harga"));
        int sisaStok = Integer.parseInt(request.getParameter("sisaStok"));

        Produk produk = new Produk(kodeProduk, namaProduk, harga, sisaStok);
        boolean success = produkDAO.updateProduk(produk);

        if (!success) {
            request.setAttribute("errorMessage", "Gagal mengupdate produk.");
            doGet(request, response);
        } else {
            response.sendRedirect("produk");
        }
    }

    private void handleUpdateStok(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kodeProduk = request.getParameter("kodeProduk");
        int jumlahTerjual = Integer.parseInt(request.getParameter("jumlahTerjual"));

        boolean success = produkDAO.updateStokProduk(kodeProduk, jumlahTerjual);

        if (!success) {
            request.setAttribute("errorMessage", "Gagal mengupdate stok produk (stok mungkin kurang).");
            doGet(request, response);
        } else {
            response.sendRedirect("produk");
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kodeProduk = request.getParameter("kodeProduk");
        boolean success = produkDAO.deleteProduk(kodeProduk);

        if (!success) {
            request.setAttribute("errorMessage", "Gagal menghapus produk.");
            doGet(request, response);
        } else {
            response.sendRedirect("produk");
        }
    }

    private void handleTransaksi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String kodeProduk = request.getParameter("kodeProduk");
        response.sendRedirect("transaksi.jsp?kodeProduk=" + kodeProduk);
    }
}
