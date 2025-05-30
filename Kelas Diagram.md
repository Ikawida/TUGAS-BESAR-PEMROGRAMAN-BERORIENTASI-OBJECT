# Aplikasi Kasir Toko Oleh-oleh

## Deskripsi Singkat
Aplikasi kasir berbasis Java (Servlet & JSP) yang terhubung ke database MySQL.  
Fungsi utama aplikasi ini adalah untuk mengelola produk oleh-oleh, memproses transaksi penjualan, dan mencetak struk pembayaran.

## Fitur
- Menampilkan daftar produk
- Menambahkan produk ke keranjang
- Memproses pembayaran dengan perhitungan kembalian otomatis
- Menyimpan data transaksi ke database

## Struktur Kelas

- **Produk**: Menyimpan data produk (kode, nama, harga, stok)
- **Transaksi**: Menyimpan ringkasan transaksi (total, pembayaran, kembalian)
- **DetailTransaksi**: Menyimpan rincian setiap produk yang dibeli dalam sebuah transaksi
- **DAO**: Kelas akses database untuk Produk, Transaksi, dan DetailTransaksi

## Kelas Diagram

![Class Diagram](images/class-diagram.png)

## Cara Menjalankan Aplikasi

1. Siapkan database MySQL dan import skrip berikut:

```sql
CREATE TABLE produk (
    kode_produk VARCHAR(10) PRIMARY KEY,
    nama_produk VARCHAR(50),
    harga INT,
    sisa_stok INT
);

-- Masukkan data awal produk
INSERT INTO produk VALUES
('382A', 'Keripik tempe', 10000, 25),
('947B', 'Jenang wijen', 15000, 30),
('016C', 'Teng teng', 10000, 28),
('728D', 'Keripik bawang', 8000, 15),
('394E', 'Keripik tenggiri', 15000, 20),
('145F', 'Nopia cokelat', 13000, 18),
('201G', 'Kue wijem', 15000, 24),
('889H', 'Rengginang', 10000, 25),
('320J', 'Keripik tahu', 10000, 29),
('563K', 'Stik sukun', 13000, 30),
('478L', 'Lanting', 10000, 9),
('633M', 'Utir utir', 10000, 17),
('251N', 'Rempeyek kacang', 12000, 12),
('908P', 'Bolu kering', 14000, 23),
('777Q', 'Sarang madu', 10000, 26);
