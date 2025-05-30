<%@page import="model.produk"%>
<%@ page import="model.produk" %>
<%@ page import="java.util.List" %>
<html>
<head><title>Daftar Produk</title></head>
<body>
<h2>Daftar Produk</h2>
<table border="1">
<tr><th>Kode</th><th>Nama</th><th>Harga</th><th>Stok</th></tr>
<%
    List<produk> produkList = (List<produk>) request.getAttribute("produkList");
    for (produk p : produkList) {
%>
<tr>
    <td><%= p.getKodeProduk() %></td>
    <td><%= p.getNamaProduk() %></td>
    <td><%= p.getHarga() %></td>
    <td><%= p.getStok() %></td>
</tr>
<% } %>
</table>

<p><a href="transaksi">Mulai Transaksi</a></p>

</body>
</html>
