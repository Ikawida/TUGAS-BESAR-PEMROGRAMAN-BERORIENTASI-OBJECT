---

# Deskripsi Aplikasi (Website)

---

Aplikasi ini merupakan sistem kasir digital yang dirancang untuk membantu proses transaksi penjualan di toko oleh-oleh. Sistem ini memungkinkan pencatatan dan pengelolaan produk, transaksi, dan detail pembelian secara terstruktur dan efisien.

* Fitur Utama:
Manajemen Produk: Menyimpan data produk seperti kode, nama, harga, dan jumlah stok yang tersedia.

* Pencatatan Transaksi: Setiap transaksi disimpan lengkap dengan tanggal, total pembayaran, uang yang dibayarkan pelanggan, serta kembalian.

* Detail Transaksi: Mencatat daftar barang yang dibeli dalam satu transaksi, termasuk jumlah dan subtotal per produk.

* Cetak Struk: Struk belanja otomatis dihasilkan setelah transaksi selesai sebagai bukti pembayaran.

* Pengelolaan Data Berbasis DAO: Menggunakan pola Data Access Object agar proses pengambilan dan penyimpanan data lebih terstruktur dan mudah dikelola.

* Integrasi Servlet: Memungkinkan aplikasi ini diakses melalui antarmuka web menggunakan servlet seperti ProdukServlet, TransaksiServlet, dan StrukServlet.

---

# Detail Kelas dan Relasi Aplikasi Kasir Toko Oleh-Oleh

---

## 1. Kelas **Produk**

**Deskripsi:**
Mewakili produk oleh-oleh yang dijual toko.

**Atribut:**

* `kodeProduk : String` — kode unik produk (PK)
* `namaProduk : String` — nama produk
* `harga : int` — harga satuan produk
* `sisaStok : int` — jumlah stok yang tersedia

**Metode utama:**

* `getKodeProduk()`, `setKodeProduk()`
* `getNamaProduk()`, `setNamaProduk()`
* `getHarga()`, `setHarga()`
* `getSisaStok()`, `setSisaStok()`

**Relasi:**

* Digunakan oleh **DetailTransaksi** (relasi many-to-one) — banyak detail transaksi dapat memakai satu produk.



## 2. Kelas **Transaksi**

**Deskripsi:**
Mewakili transaksi pembelian oleh pelanggan.

**Atribut:**

* `idTransaksi : int` — ID unik transaksi (PK)
* `tanggal : Date` — tanggal transaksi
* `totalBayar : int` — total harga semua produk
* `bayar : int` — jumlah uang yang dibayar pelanggan
* `kembalian : int` — uang kembali setelah pembayaran

**Metode utama:**

* Getter dan setter semua atribut
* `hitungKembalian()` — menghitung kembalian secara otomatis

**Relasi:**

* Memiliki banyak **DetailTransaksi** (relasi one-to-many) — satu transaksi punya banyak item pembelian.


## 3. Kelas **DetailTransaksi**

**Deskripsi:**
Mewakili rincian produk yang dibeli dalam satu transaksi.

**Atribut:**

* `idDetail : int` — ID detail transaksi (PK)
* `transaksi : Transaksi` — referensi ke objek Transaksi
* `produk : Produk` — referensi ke objek Produk
* `jumlah : int` — jumlah produk yang dibeli
* `subtotal : int` — harga subtotal (harga produk x jumlah)

**Metode utama:**

* Getter dan setter semua atribut
* `hitungSubtotal()` — menghitung subtotal berdasarkan produk dan jumlah

**Relasi:**

* Berelasi many-to-one dengan **Transaksi**
* Berelasi many-to-one dengan **Produk**


## 4. Kelas **ProdukDAO**

**Deskripsi:**
Kelas untuk akses dan manipulasi data produk di database.

**Metode utama:**

* `getAllProduk()` — mengambil semua produk
* `getProdukByKode(String kode)` — cari produk berdasarkan kode
* `updateStok(String kode, int stokBaru)` — update stok produk
* `insertProduk(Produk p)` — tambah produk baru
* `deleteProduk(String kode)` — hapus produk

**Relasi:**

* Berinteraksi langsung dengan tabel produk di database.
* Digunakan oleh controller untuk manajemen produk.


## 5. Kelas **TransaksiDAO**

**Deskripsi:**
Mengelola data transaksi di database.

**Metode utama:**

* `saveTransaksi(Transaksi t)` — simpan transaksi baru
* `getTransaksiById(int id)` — ambil transaksi berdasarkan ID
* `getAllTransaksi()` — ambil semua transaksi

**Relasi:**

* Berinteraksi dengan tabel transaksi di database.

## 6. Kelas **DetailTransaksiDAO**

**Deskripsi:**
Mengelola data detail transaksi di database.

**Metode utama:**

* `saveDetail(DetailTransaksi dt)` — simpan detail transaksi
* `getDetailsByTransaksi(Transaksi t)` — ambil semua detail produk dari transaksi tertentu

**Relasi:**

* Berinteraksi dengan tabel detail transaksi.


## 7. Kelas **DatabaseConnection**

**Deskripsi:**
Membuat dan mengelola koneksi database MySQL.

**Metode utama:**

* `getConnection()` — mengembalikan objek `Connection` untuk akses database.

## 8. **Servlets (Controller)**

**Deskripsi:**
Menghubungkan antara interface web (JSP) dan logika bisnis.

**Contoh:**

* `ProdukServlet` — mengatur request produk (lihat, tambah, update)
* `TransaksiServlet` — mengatur transaksi baru, hitung total, simpan data
* `StrukServlet` — menampilkan atau mencetak struk setelah transaksi

**Relasi:**

* Memanggil DAO dan model sesuai kebutuhan.
* Mengirim data ke JSP untuk ditampilkan ke user.



# Relasi Antar Kelas

| Dari Kelas                                        | Ke Kelas           | Jenis Relasi       | Deskripsi                                       |
| ------------------------------------------------- | ------------------ | ------------------ | ----------------------------------------------- |
| Transaksi                                         | DetailTransaksi    | One to Many (1\:N) | Satu transaksi dapat punya banyak detail produk |
| DetailTransaksi                                   | Produk             | Many to One (N:1)  | Banyak detail transaksi merujuk ke satu produk  |
| DAO (ProdukDAO, TransaksiDAO, DetailTransaksiDAO) | DatabaseConnection | Association        | Semua DAO menggunakan koneksi database          |
| Servlet                                           | DAO                | Association        | Servlet menggunakan DAO untuk operasi database  |

# Kelas Diagram 
Berikut adalah class diagram dari aplikasi kasir toko oleh-oleh:

![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/21e2267facfba1d317b09ff5eb70afc6d5b7377f/kelas%20diagram.png?raw=true)


# Screenshoot Tampilan (masih kasaran)
Berikut adalah tampilan dari aplikasi kasir toko oleh-oleh untuk beberapa bagian:

![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/fc00c4ad10116241e114e1096331f15774af4bd4/SCREENSHOOT/Screenshot%202025-06-05%20090028.png)

![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/a99973246354b816f74c9638ea4f1370171dbdee/SCREENSHOOT/Screenshot%202025-06-05%20090211.png)

![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/2ca3e7cb6a26e0ec6c4c9a2845b5d8c69ab46197/Screenshot%202025-05-30%20172732.png?raw=true)

![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/2ca3e7cb6a26e0ec6c4c9a2845b5d8c69ab46197/Screenshot%202025-05-30%20172732.png?raw=true)



