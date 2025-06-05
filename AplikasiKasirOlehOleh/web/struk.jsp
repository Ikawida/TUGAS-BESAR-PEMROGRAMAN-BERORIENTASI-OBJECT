<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Transaksi" %>
<%@ page import="model.DetailTransaksi" %>
<%@ page import="java.util.List" %>
<%
    Transaksi transaksi = (Transaksi) request.getAttribute("transaksi");
    List<DetailTransaksi> details = (List<DetailTransaksi>) request.getAttribute("details");
%>
<html>
<head>
    <title>Struk Transaksi</title>
    <style>
        body {
            background-color: #e6f4ff;
            font-family: 'Courier New', Courier, monospace;
            padding: 20px;
            max-width: 420px;
            margin: 40px auto;
            border: 3px solid #4da6ff;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(77, 166, 255, 0.4);
            color: #003366;
        }
        h2 {
            text-align: center;
            color: #1a75ff;
            font-weight: 700;
            margin-bottom: 25px;
            letter-spacing: 1.2px;
        }
        .info-header {
            margin-bottom: 20px;
            font-size: 14px;
            line-height: 1.4;
            border-bottom: 1.5px dashed #3399ff;
            padding-bottom: 12px;
        }
        .info-header strong {
            color: #0059b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 14px;
            margin-bottom: 20px;
        }
        th, td {
            padding: 8px 10px;
            border-bottom: 1px solid #99c2ff;
        }
        th {
            text-align: left;
            color: #004080;
            border-bottom: 2px solid #3399ff;
        }
        th:nth-child(3),
        td:nth-child(3),
        th:nth-child(4),
        td:nth-child(4) {
            text-align: right;
            font-feature-settings: "tnum";
            font-variant-numeric: tabular-nums;
            padding-right: 15px;
            white-space: nowrap;
        }
        td:nth-child(2) {
            white-space: nowrap;
        }
        tbody tr:hover {
            background-color: #cce6ff;
        }
        .total {
            font-weight: 700;
            font-size: 16px;
            border-top: 2px solid #3399ff;
            padding-top: 15px;
            text-align: right;
            color: #003366;
        }
        .total div {
            margin-bottom: 8px;
        }
        .footer {
            margin-top: 25px;
            font-size: 13px;
            color: #3366cc;
            text-align: center;
            font-style: italic;
            border-top: 1.5px dashed #3399ff;
            padding-top: 12px;
        }
        @media print {
            body {
                margin: 0;
                border: none;
                box-shadow: none;
                max-width: 100%;
            }
            .footer {
                font-style: normal;
            }
        }
    </style>
</head>
<body>
    <h2>STRUK TRANSAKSI</h2>

    <div class="info-header">
        <div><strong>Tanggal:</strong> <%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(transaksi.getTanggal()) %></div>
        <div><strong>ID Transaksi:</strong> <%= transaksi.getId() %></div>
    </div>

    <table>
        <thead>
            <tr>
                <th>Kode Produk</th>
                <th>Nama Produk</th>
                <th>Jumlah</th>
                <th>Subtotal (Rp)</th>
            </tr>
        </thead>
        <tbody>
            <% for (DetailTransaksi d : details) { %>
            <tr>
                <td><%= d.getKodeProduk() %></td>
                <td><%= d.getNamaProduk() != null ? d.getNamaProduk() : "-" %></td>
                <td><%= d.getJumlah() %></td>
                <td><%= String.format("%,d", d.getSubtotal()).replace(',', '.') %></td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <div class="total">
        <div>Total Bayar: Rp <%= String.format("%,d", transaksi.getTotalBayar()).replace(',', '.') %></div>
        <div>Bayar: Rp <%= String.format("%,d", transaksi.getBayar()).replace(',', '.') %></div>
        <div>Kembalian: Rp <%= String.format("%,d", transaksi.getKembalian()).replace(',', '.') %></div>
    </div>

    <div class="footer">
        Terima kasih sudah berbelanja di Toko Kami!<br>
        Semoga hari Anda menyenangkan 😊
    </div>
</body>
</html>
