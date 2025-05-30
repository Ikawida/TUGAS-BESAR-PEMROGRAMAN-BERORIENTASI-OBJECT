<%@ page import="model.Transaksi" %>
<%@ page import="model.ItemTransaksi" %>
<html>
<head><title>Struk Transaksi</title></head>
<body>
<h2>Struk Transaksi</h2>
<%
    Transaksi transaksi = (Transaksi) request.getAttribute("transaksi");
    if (transaksi == null) {
%>
    <p>Data transaksi tidak tersedia.</p>
<%
    } else {
%>
<table border="1">
<tr><th>Nama Produk</th><th>Harga</th><th>Jumlah</th><th>Subtotal</th></tr>
<%
        int total = 0;
        for (ItemTransaksi item : transaksi.getItems()) {
            int subtotal = item.getProduk().getHarga() * item.getJumlah();
            total += subtotal;
%>
<tr>
    <td><%= item.getProduk().getNamaProduk() %></td>
    <td><%= item.getProduk().getHarga() %></td>
    <td><%= item.getJumlah() %></td>
    <td><%= subtotal %></td>
</tr>
<% } %>
<tr>
    <td colspan="3">Total</td>
    <td><%= total %></td>
</tr>
<tr>
    <td colspan="3">Bayar</td>
    <td><%= transaksi.getBayar() %></td>
</tr>
<tr>
    <td colspan="3">Kembali</td>
    <td><%= transaksi.getBayar() - total %></td>
</tr>
</table>
<% } %>
</body>
</html>
