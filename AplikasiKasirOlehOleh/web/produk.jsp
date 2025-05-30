<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Produk" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manajemen Produk</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f2f6fc;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 50px;
            background: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }
        h1 {
            color: #2c3e50;
            margin-bottom: 30px;
        }
        .btn + .btn {
            margin-left: 10px;
        }
        table {
            margin-top: 30px;
        }
        .table td, .table th {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Manajemen Produk</h1>

    <% String errorMessage = (String) request.getAttribute("errorMessage");
       if (errorMessage != null) { %>
        <div class="alert alert-danger">
            <%= errorMessage %>
        </div>
    <% } %>

    <% Produk produkEdit = (Produk) request.getAttribute("produkEdit");
       if (produkEdit != null) { %>
        <h3>Edit Produk</h3>
        <form method="post" action="produk">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="kodeProduk" value="<%= produkEdit.getKodeProduk() %>">
            <div class="mb-3">
                <label>Nama Produk</label>
                <input type="text" name="namaProduk" class="form-control" value="<%= produkEdit.getNamaProduk() %>" required>
            </div>
            <div class="mb-3">
                <label>Harga</label>
                <input type="number" name="harga" class="form-control" value="<%= produkEdit.getHarga() %>" required>
            </div>
            <div class="mb-3">
                <label>Sisa Stok</label>
                <input type="number" name="sisaStok" class="form-control" value="<%= produkEdit.getSisaStok() %>" required>
            </div>
            <button type="submit" class="btn btn-warning">Update</button>
            <a href="produk" class="btn btn-secondary">Batal</a>
        </form>
    <% } else { %>
        <h3>Tambah Produk</h3>
        <form method="post" action="produk">
            <input type="hidden" name="action" value="insert">
            <div class="mb-3">
                <label>Kode Produk</label>
                <input type="text" name="kodeProduk" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Nama Produk</label>
                <input type="text" name="namaProduk" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Harga</label>
                <input type="number" name="harga" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Sisa Stok</label>
                <input type="number" name="sisaStok" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Tambah</button>
        </form>
    <% } %>

    <h3 class="mt-5">Daftar Produk</h3>
    <table class="table table-striped">
        <thead class="table-dark">
            <tr>
                <th>Kode</th>
                <th>Nama</th>
                <th>Harga</th>
                <th>Sisa Stok</th>
                <th>Aksi</th>
            </tr>
        </thead>
        <tbody>
        <% List<Produk> produkList = (List<Produk>) request.getAttribute("produkList");
           if (produkList != null) {
               for (Produk p : produkList) { %>
            <tr>
                <td><%= p.getKodeProduk() %></td>
                <td><%= p.getNamaProduk() %></td>
                <td><%= String.format("%,.0f", (double)p.getHarga()) %></td>
                <td><%= p.getSisaStok() %></td>
                <td>
                    <a href="produk?action=edit&kodeProduk=<%= p.getKodeProduk() %>" class="btn btn-sm btn-warning">Edit</a>
                    <form method="post" action="produk" class="d-inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="kodeProduk" value="<%= p.getKodeProduk() %>">
                        <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Yakin ingin menghapus produk ini?')">Hapus</button>
                    </form>
                    <form method="post" action="produk" class="d-inline">
                        <input type="hidden" name="action" value="addStock">
                        <input type="hidden" name="kodeProduk" value="<%= p.getKodeProduk() %>">
                        <input type="number" name="stokTambah" placeholder="Tambah" style="width: 70px;" required>
                        <button type="submit" class="btn btn-sm btn-info">Tambah Stok</button>
                    </form>
                    <a href="transaksi.jsp?kodeProduk=<%= p.getKodeProduk() %>" class="btn btn-sm btn-success">Transaksi</a>
                </td>
            </tr>
        <% } } else { %>
            <tr><td colspan="5" class="text-center">Belum ada data produk.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
