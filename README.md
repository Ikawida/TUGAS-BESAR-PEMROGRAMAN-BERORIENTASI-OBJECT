# TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT

<pre>
Nama Anggota Kelompok: 
  1. Ika Wida Nuragustin (2311110001)
  2. Indy Aurelia Az Zahra (2311110020)
  3. Dill Thafa Jausha (2311110048)
</pre>


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

**Berikut kelas dari servlet:**

* `ProdukServlet` — mengatur request produk (lihat, tambah, update)/CRUD via website. Methodnya ada 2 yaitu: doGet() untuk Menampilkan daftar atau form produk dan doPost() untuk memproses penyimpanan atau pembaruan produk.
* `TransaksiServlet` — mengatur transaksi baru, hitung total, simpan data (Servlet untuk menangani proses transaksi). Methodnya ada 2 yaitu: doGet() untuk menampilkan form transaksi atau detail dan doPost() untuk menyimpan transaksi dan detail ke database.
* `StrukServlet` — menampilkan atau mencetak struk setelah transaksi (Servlet untuk menampilkan struk berdasarkan transaksi). Methodnya yaitu: doGet() untuk mengambil data transaksi dan detail, tampilkan di struk.jsp.

**Relasi:**

* Memanggil DAO dan model sesuai kebutuhan.
* Mengirim data ke JSP untuk menampilkan data lengkap struk.


---

# Relasi Antar Kelas

---

| **Kelas 1**          | **Jenis Relasi**  | **Kelas 2**                                       | **Keterangan**                                                      |
| -------------------- | ----------------- | ------------------------------------------------- | ------------------------------------------------------------------- |
| `Produk`             | One-to-Many       | `DetailTransaksi`                                 | Satu produk dapat muncul di banyak detail transaksi                 |
| `Transaksi`          | One-to-Many       | `DetailTransaksi`                                 | Satu transaksi dapat memiliki banyak item produk (detail transaksi) |
| `ProdukDAO`          | Dependency (Uses) | `Produk`                                          | DAO ini memanipulasi data entitas Produk                            |
| `TransaksiDAO`       | Dependency (Uses) | `Transaksi`                                       | DAO ini menyimpan dan mengambil data transaksi                      |
| `DetailTransaksiDAO` | Dependency (Uses) | `DetailTransaksi`                                 | DAO ini menyimpan dan mengambil data detail transaksi               |
| `ProdukServlet`      | Dependency (Uses) | `ProdukDAO`                                       | Servlet ini menggunakan DAO untuk CRUD produk                       |
| `TransaksiServlet`   | Dependency (Uses) | `TransaksiDAO`, `DetailTransaksiDAO`              | Servlet ini mengatur penyimpanan transaksi dan item                 |
| `StrukServlet`       | Dependency (Uses) | `TransaksiDAO`, `DetailTransaksiDAO`, `ProdukDAO` | Servlet ini menampilkan data lengkap struk                          |
| Semua `DAO`          | Dependency (Uses) | `DatabaseConnection`                              | Semua DAO mengambil koneksi dari kelas util ini                     |


---

# Kelas Diagram 

---

Berikut adalah class diagram dari aplikasi kasir toko oleh-oleh:

![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/ac2d158f13a6714a592cca15185144c62269d4ec/SCREENSHOOT/Kelas%20Diagram.png)


---

# Screenshoot Tampilan 

---

Berikut adalah tampilan dari aplikasi kasir toko oleh-oleh untuk beberapa bagian:

1. Tampilan bagian tambah produk
![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/fc00c4ad10116241e114e1096331f15774af4bd4/SCREENSHOOT/Screenshot%202025-06-05%20090028.png)


2. Tampilan bagian daftar produk
![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/b8ad8f669d9f58cdf0c2748320af141865e36464/SCREENSHOOT/Screenshot%202025-06-05%20112840.png)


3. Tampilan bagian transaksi
![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/7cbe21f3970c4e081ef7c0bf5f281e680c0fe620/SCREENSHOOT/Screenshot%202025-06-05%20090327.png)


4. Tampilan bagian struk 
![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/0e25a7e9fb3d5dcd1ab3cd2b2d6c1292c13860a8/SCREENSHOOT/Screenshot%202025-06-05%20090615.png)



