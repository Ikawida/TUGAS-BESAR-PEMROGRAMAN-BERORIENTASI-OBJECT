<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Produk" %>
<%@ page import="java.util.List" %>
<%
    List<Produk> produkList = (List<Produk>) request.getAttribute("produkList");
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <title>Transaksi | Kasir Toko Oleh-Oleh</title>
    <style>
        body {
            background-color: #e6f2ff;  /* biru muda lembut */
            font-family: monospace, Courier New, monospace;
            margin: 0;
            padding: 20px;
            color: #003366;  /* biru tua untuk teks */
        }
        .container {
            max-width: 900px;
            margin: auto;
            background: #ffffff;
            padding: 25px 30px;
            border-radius: 12px;
            box-shadow: 0 6px 15px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #0066cc;
            margin-bottom: 25px;
            font-weight: bold;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            font-size: 14px;
        }
        th, td {
            border: 1px solid #99ccff;
            padding: 10px 12px;
            text-align: center;
        }
        th {
            background-color: #cce6ff;
            color: #004080;
            font-weight: 700;
        }
        td {
            background-color: #f2fbff;
        }
        .input-jumlah {
            width: 60px;
            padding: 5px;
            border: 1px solid #99ccff;
            border-radius: 6px;
            text-align: center;
            font-size: 14px;
            color: #003366;
            font-weight: 600;
        }
        #totalHarga {
            font-weight: bold;
            font-size: 18px;
            color: #004080;
        }
        label strong {
            color: #004080;
            font-size: 16px;
        }
        input#bayar {
            width: 180px;
            padding: 7px 10px;
            border: 1px solid #99ccff;
            border-radius: 8px;
            font-size: 15px;
            font-weight: 600;
            color: #003366;
        }
        button.btn {
            background-color: #3399ff;
            border: none;
            color: white;
            padding: 12px 25px;
            font-size: 16px;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-weight: 700;
            margin-top: 15px;
            display: block;
            width: 100%;
            max-width: 220px;
            margin-left: auto;
            margin-right: auto;
            box-shadow: 0 4px 12px rgba(51,153,255,0.4);
        }
        button.btn:hover {
            background-color: #2673cc;
        }
        .error {
            background-color: #ffd6d6;
            color: #cc0000;
            padding: 10px 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            font-weight: 600;
            text-align: center;
            border: 1px solid #cc0000;
        }
        a.back-link {
            display: inline-block;
            margin-top: 25px;
            color: #3399ff;
            font-weight: 600;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        a.back-link:hover {
            color: #2673cc;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Transaksi Pembelian</h2>

    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form action="transaksi" method="post" id="formTransaksi">
        <table>
            <thead>
                <tr>
                    <th>Kode Produk</th>
                    <th>Nama Produk</th>
                    <th>Harga (Rp)</th>
                    <th>Stok</th>
                    <th>Jumlah Beli</th>
                    <th>Subtotal (Rp)</th>
                </tr>
            </thead>
            <tbody>
            <% for (Produk p : produkList) { %>
                <tr>
                    <td><%= p.getKodeProduk() %></td>
                    <td><%= p.getNamaProduk() %></td>
                    <td class="harga" data-kode="<%= p.getKodeProduk() %>"><%= p.getHarga() %></td>
                    <td><%= p.getSisaStok() %></td>
                    <td>
                        <input type="number" name="jumlah_<%= p.getKodeProduk() %>" min="0" max="<%= p.getSisaStok() %>" value="0" class="input-jumlah" data-kode="<%= p.getKodeProduk() %>" />
                    </td>
                    <td class="subtotal" id="subtotal_<%= p.getKodeProduk() %>">0</td>
                </tr>
            <% } %>
            </tbody>
        </table>

        <h3>Total Harga: Rp <span id="totalHarga">0</span></h3>

        <label for="bayar"><strong>Uang Bayar (Rp):</strong></label><br>
        <input type="number" name="bayar" id="bayar" placeholder="Contoh: 100000" required /><br><br>

        <button type="submit" class="btn">Proses Transaksi</button>
    </form>

    <a href="produk" class="back-link">← Kembali ke Produk</a>
</div>

<script>
    const inputJumlahList = document.querySelectorAll('.input-jumlah');
    const totalHargaElem = document.getElementById('totalHarga');

    function formatRupiah(angka) {
        return angka.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }

    function hitungTotal() {
        let total = 0;

        inputJumlahList.forEach(input => {
            const kode = input.getAttribute('data-kode');
            const jumlah = parseInt(input.value) || 0;
            const hargaElem = document.querySelector('.harga[data-kode="'+kode+'"]');
            const harga = parseInt(hargaElem.textContent) || 0;

            const subtotal = jumlah * harga;
            total += subtotal;

            const subtotalElem = document.getElementById('subtotal_' + kode);
            subtotalElem.textContent = formatRupiah(subtotal);
        });

        totalHargaElem.textContent = formatRupiah(total);
    }

    inputJumlahList.forEach(input => {
        input.addEventListener('input', hitungTotal);
    });

    hitungTotal();
</script>

</body>
</html>
