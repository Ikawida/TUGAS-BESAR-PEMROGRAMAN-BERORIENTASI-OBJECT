<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Produk" %>
<%@ page import="java.util.List" %>
<%
    Produk produkEdit = (Produk) request.getAttribute("produkEdit");
    List<Produk> produkList = (List<Produk>) request.getAttribute("produkList");
    boolean isEdit = (produkEdit != null);
%>
<html>
<head>
    <title>Produk - Kasir Toko Oleh-Oleh</title>
    <style>
        body {
            background-color: #e6f2ff;
            font-family: monospace, Courier New, monospace;
            margin: 0;
            padding: 0;
            color: #003366;
        }
        header {
            background-color: #4da6ff;
            padding: 25px 0;
            text-align: center;
            color: #fff;
            font-size: 28px;
            font-weight: 800;
            letter-spacing: 1.2px;
            box-shadow: 0 4px 8px rgba(77, 166, 255, 0.5);
            user-select: none;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 12px;
            font-family: monospace, Courier New, monospace;
        }
        header .icon {
            font-size: 32px;
        }
        .container {
            max-width: 900px;
            margin: 30px auto 50px auto;
            padding: 0 20px;
        }
        .form-section, .list-section {
            background: white;
            padding: 25px 30px;
            margin-bottom: 30px;
            border-radius: 12px;
            box-shadow: 0 6px 18px rgba(51, 153, 255, 0.15);
        }
        h2 {
            color: #0066cc;
            margin-bottom: 20px;
            font-weight: 700;
            text-align: center;
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: 600;
            color: #004080;
        }
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px 12px;
            margin-top: 5px;
            border: 2px solid #99ccff;
            border-radius: 8px;
            font-size: 15px;
            color: #003366;
            transition: border-color 0.3s ease;
            box-sizing: border-box;
            font-family: monospace, Courier New, monospace;
        }
        input[type="text"]:focus,
        input[type="number"]:focus {
            border-color: #3399ff;
            outline: none;
            box-shadow: 0 0 8px #3399ff66;
        }
        button.btn {
            margin-top: 25px;
            background-color: #3399ff;
            border: none;
            color: white;
            padding: 14px 28px;
            font-size: 16px;
            font-weight: 700;
            border-radius: 10px;
            cursor: pointer;
            width: 100%;
            max-width: 220px;
            display: block;
            margin-left: auto;
            margin-right: auto;
            box-shadow: 0 6px 15px rgba(51, 153, 255, 0.5);
            transition: background-color 0.3s ease;
            text-align: center;
            text-decoration: none;
            font-family: monospace, Courier New, monospace;
        }
        button.btn:hover, a.btn:hover {
            background-color: #2673cc;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 15px;
            font-family: monospace, Courier New, monospace;
        }
        th, td {
            border: 1px solid #99ccff;
            padding: 12px 15px;
            text-align: center;
            color: #003366;
        }
        th {
            background-color: #cce6ff;
            font-weight: 700;
        }
        tr:nth-child(even) td {
            background-color: #f2fbff;
        }
        a.btn {
            display: inline-block;
            padding: 10px 20px;
            border-radius: 10px;
            font-weight: 700;
            cursor: pointer;
            box-shadow: 0 6px 15px rgba(51, 153, 255, 0.5);
            transition: background-color 0.3s ease;
            text-align: center;
            text-decoration: none;
            margin-top: 20px;
            font-size: 14px;
            color: white !important;
            background-color: #3399ff;
            font-family: monospace, Courier New, monospace;
        }
        a.btn:hover {
            background-color: #2673cc;
        }

        .btn-edit {
            background-color: #6c757d;
            box-shadow: 0 3px 8px rgba(108, 117, 125, 0.6);
            padding: 6px 14px;
            font-size: 14px;
            margin: 0 4px;
            border-radius: 10px;
            color: white !important;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 6px;
            font-weight: 700;
            cursor: pointer;
            transition: background-color 0.3s ease;
            user-select: none;
            font-family: monospace, Courier New, monospace;
        }
        .btn-edit:hover {
            background-color: #5a6268;
        }

        .btn-delete {
            background-color: #dc3545;
            box-shadow: 0 3px 8px rgba(220, 53, 69, 0.6);
            padding: 6px 14px;
            font-size: 14px;
            margin: 0 4px;
            border-radius: 10px;
            color: white !important;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 6px;
            font-weight: 700;
            cursor: pointer;
            transition: background-color 0.3s ease;
            user-select: none;
            font-family: monospace, Courier New, monospace;
        }
        .btn-delete:hover {
            background-color: #c82333;
        }

        .btn-icon {
            font-size: 16px;
            line-height: 1;
            display: inline-block;
            user-select: none;
        }

        .actions {
            white-space: nowrap;
        }
    </style>
</head>
<body>
<header>
    <span class="icon">🛒</span> Kasir Toko Oleh-Oleh
</header>
<div class="container">

    <div class="form-section">
        <h2><%= isEdit ? "Edit Produk" : "Tambah Produk" %></h2>
        <form method="post" action="produk">
            <input type="hidden" name="isUpdate" value="<%= isEdit %>" />
            <label for="kodeProduk">Kode Produk:</label>
            <input id="kodeProduk" type="text" name="kodeProduk" value="<%= isEdit ? produkEdit.getKodeProduk() : "" %>" <%= isEdit ? "readonly" : "" %> required />

            <label for="namaProduk">Nama Produk:</label>
            <input id="namaProduk" type="text" name="namaProduk" value="<%= isEdit ? produkEdit.getNamaProduk() : "" %>" required />

            <label for="harga">Harga:</label>
            <input id="harga" type="number" name="harga" value="<%= isEdit ? produkEdit.getHarga() : "" %>" required />

            <label for="sisaStok">Sisa Stok:</label>
            <input id="sisaStok" type="number" name="sisaStok" value="<%= isEdit ? produkEdit.getSisaStok() : "" %>" required />

            <button type="submit" class="btn"><%= isEdit ? "Update" : "Tambah" %></button>
        </form>
    </div>

    <div class="list-section">
        <h2>Daftar Produk</h2>
        <table>
            <thead>
                <tr>
                    <th>Kode</th>
                    <th>Nama</th>
                    <th>Harga (Rp)</th>
                    <th>Stok</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (Produk p : produkList) {
            %>
                <tr>
                    <td><%= p.getKodeProduk() %></td>
                    <td><%= p.getNamaProduk() %></td>
                    <td><%= p.getHarga() %></td>
                    <td><%= p.getSisaStok() %></td>
                    <td class="actions">
                        <a href="produk?action=edit&kode=<%= p.getKodeProduk() %>" class="btn-edit">
                            <span class="btn-icon">🖊</span> Edit
                        </a>
                        <a href="produk?action=delete&kode=<%= p.getKodeProduk() %>" class="btn-delete" onclick="return confirm('Hapus produk ini?')">
                            <span class="btn-icon">❌</span> Hapus
                        </a>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>

        <a href="transaksi" class="btn">Lanjut ke Transaksi</a>
    </div>

</div>
</body>
</html>
