<%@ page import="java.util.List" %>
<%@ page import="model.produk" %>
<html>
<head><title>Transaksi</title></head>
<body>
<h2>Transaksi Pembelian</h2>
<form action="transaksi" method="post">
<table border="1">
<tr>
    <th>Pilih</th><th>Nama</th><th>Harga</th><th>Stok</th><th>Jumlah Beli</th>
</tr>
<%
    List<produk> produkList = (List<produk>) request.getAttribute("produkList");
    for (produk p : produkList) {
%>
<tr>
    <td><input type="checkbox" name="kode_produk" value="<%= p.getKodeProduk() %>"></td>
    <td><%= p.getNamaProduk() %></td>
    <td><%= p.getHarga() %></td>
    <td><%= p.getStok() %></td>
    <td><input type="number" name="jumlah" min="1" max="<%= p.getStok() %>" value="1"></td>
</tr>
<% } %>
</table>
<br>
Bayar: <input type="number" name="bayar" required>
<br><br>
<input type="submit" value="Proses Transaksi">
</form>
</body>
</html>
