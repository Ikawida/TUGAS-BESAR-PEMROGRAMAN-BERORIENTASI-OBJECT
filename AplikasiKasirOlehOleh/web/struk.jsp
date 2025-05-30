<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Transaksi, model.DetailTransaksi" %>
<html>
<head>
    <title>Struk Transaksi</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background: #f5f5f5; }
        .container { background: white; padding: 20px; border-radius: 10px; max-width: 600px; margin: auto; }
        h2 { text-align: center; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background-color: #f59e0b; color: white; }
        .total { font-weight: bold; }
    </style>
</head>
<body>

<%
    Transaksi transaksi = (Transaksi) request.getAttribute("transaksi");
    List<DetailTransaksi> detailList = (List<DetailTransaksi>) request.getAttribute("detailList");
%>

<div class="container">
    <h2>Struk Transaksi</h2>
    <p><strong>ID Transaksi:</strong> <%= transaksi.getId() %></p>
    <p><strong>Tanggal:</strong> <%= transaksi.getTanggal() %></p>

    <table>
        <thead>
            <tr>
                <th>Kode Produk</th>
                <th>Jumlah</th>
                <th>Subtotal (Rp)</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (DetailTransaksi detail : detailList) {
            %>
            <tr>
                <td><%= detail.getKodeProduk() %></td>
                <td><%= detail.getJumlah() %></td>
                <td><%= detail.getSubtotal() %></td>
            </tr>
            <%
                }
            %>
            <tr>
                <td colspan="2" class="total">Total Harga</td>
                <td class="total"><%= transaksi.getTotalHarga() %></td>
            </tr>
        </tbody>
    </table>
</div>

</body>
</html>
