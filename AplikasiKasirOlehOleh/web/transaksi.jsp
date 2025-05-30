<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Produk" %>
<html>
<head>
    <title>Transaksi Produk</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #fddb92 0%, #d1fdff 100%);
            margin: 0; padding: 20px; color: #333;
        }
        h2, h3 {
            text-align: center;
            color: #333;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 6px 15px rgba(0,0,0,0.1);
        }
        label {
            font-weight: 600;
            display: block;
            margin-bottom: 6px;
            color: #222;
        }
        select, input[type=number] {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 14px;
            box-sizing: border-box;
            transition: border-color 0.3s ease;
        }
        select:focus, input[type=number]:focus {
            border-color: #f59e0b;
            outline: none;
        }
        input[type=submit] {
            background: #f59e0b;
            color: white;
            border: none;
            padding: 12px;
            font-size: 16px;
            border-radius: 10px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
            width: 100%;
        }
        input[type=submit]:hover {
            background: #b45309;
        }
        .error-message {
            text-align: center;
            color: #d14343;
            font-weight: 700;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<%
    List<Produk> produkList = (List<Produk>) request.getAttribute("produkList");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<h2>Form Transaksi Produk</h2>

<form action="transaksi" method="post">
    <label for="kodeProduk">Pilih Produk:</label>
    <select name="kodeProduk" id="kodeProduk" required>
        <option value="" disabled selected>-- Pilih Produk --</option>
        <%
            if (produkList != null) {
                for (Produk produk : produkList) {
        %>
            <option value="<%= produk.getKodeProduk() %>">
                <%= produk.getNamaProduk() %> (Stok: <%= produk.getSisaStok() %>) - Rp <%= produk.getHarga() %>
            </option>
        <%
                }
            }
        %>
    </select>

    <label for="jumlah">Jumlah:</label>
    <input type="number" name="jumlah" id="jumlah" min="1" required placeholder="Masukkan jumlah">

    <input type="submit" value="Proses Transaksi">
</form>

<%
    if (errorMessage != null && !errorMessage.isEmpty()) {
%>
    <p class="error-message"><%= errorMessage %></p>
<%
    }
%>

</body>
</html>
