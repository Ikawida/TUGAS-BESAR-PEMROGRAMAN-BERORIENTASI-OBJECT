package controller;

import dao.produkdao;
import model.produk;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class produkServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            produkdao dao = new produkdao();
            List<produk> produkList = dao.getAllProduk();
            request.setAttribute("produkList", produkList);
            RequestDispatcher rd = request.getRequestDispatcher("produk.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

