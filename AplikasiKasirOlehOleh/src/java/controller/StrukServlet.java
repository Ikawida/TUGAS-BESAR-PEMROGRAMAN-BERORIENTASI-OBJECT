package controller;

import dao.TransaksiDAO;
import dao.DetailTransaksiDAO;
import model.Transaksi;
import model.DetailTransaksi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/struk")
public class StrukServlet extends HttpServlet {
    private TransaksiDAO transaksiDAO = new TransaksiDAO();
    private DetailTransaksiDAO detailDAO = new DetailTransaksiDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("transaksiId");
        if (idParam == null) {
            response.sendRedirect("transaksi");
            return;
        }

        int transaksiId = Integer.parseInt(idParam);
        Transaksi transaksi = transaksiDAO.getTransaksiById(transaksiId);
        if (transaksi == null) {
            response.sendRedirect("transaksi");
            return;
        }

        List<DetailTransaksi> details = detailDAO.getDetailByTransaksiId(transaksiId);

        request.setAttribute("transaksi", transaksi);
        request.setAttribute("detailList", details);

        request.getRequestDispatcher("struk.jsp").forward(request, response);
    }
}
