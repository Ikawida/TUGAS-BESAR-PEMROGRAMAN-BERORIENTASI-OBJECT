+----------------+        +-------------------+
|    Produk      |        |   ItemTransaksi   |
+----------------+        +-------------------+
| kodeProduk     |◄───────| produk: Produk     |
| namaProduk     |        | jumlah             |
| harga          |        | subtotal()         |
| sisaStok       |        +-------------------+
+----------------+
       ▲
       |
       | uses
+----------------+
| Transaksi      |
+----------------+
| idTransaksi    |
| tanggal        |
| total          |
| daftarItem[]   |
+----------------+
| tambahItem()   |
| hitungTotal()  |
+----------------+

+----------------+             +----------------+
|   ProdukDAO    |◄────────────|   Koneksi      |
+----------------+             +----------------+

+----------------+             +-------------------+
| KasirServlet   |◄────────────| TransaksiDAO      |
+----------------+             +-------------------+
