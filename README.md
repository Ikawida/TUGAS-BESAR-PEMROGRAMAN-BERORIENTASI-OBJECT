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
# Konsep OOP
---

Berikut adalah prinsip oop yang ada didalam aplikasi kasir toko oleh-oleh:

| **Prinsip PBO**   | **Penjelasan**                                                                     | **Contoh Kelas**                                                              | **Manfaat**                                              |
| ----------------- | ---------------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | -------------------------------------------------------- |
| **Encapsulation** | Menyembunyikan atribut dalam kelas dan membukanya melalui getter/setter.           | `Produk`, `Transaksi`, `DetailTransaksi`                                      | Melindungi data, mencegah manipulasi langsung            |
| **Abstraction**   | Menyembunyikan detail implementasi dan hanya menampilkan method penting.           | `ProdukDAO`, `TransaksiDAO`, `DatabaseConnection`                             | Memudahkan penggunaan, mengurangi kompleksitas           |
| **Inheritance**   | Pewarisan sifat dari superclass ke subclass, memungkinkan penggunaan ulang method. | `ProdukServlet`, `TransaksiServlet`, `StrukServlet` (me-warisi `HttpServlet`) | Reusabilitas kode, efisiensi                             |
| **Polymorphism**  | Method dengan nama sama tetapi implementasi berbeda sesuai konteks.                | `doGet()` dan `doPost()` pada semua `Servlet`                                 | Fleksibilitas implementasi sesuai kebutuhan tiap servlet |


---

# Kelas Diagram 

---

Berikut adalah class diagram dari aplikasi kasir toko oleh-oleh:

![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/ac2d158f13a6714a592cca15185144c62269d4ec/SCREENSHOOT/Kelas%20Diagram.png)



---
# Struktur Database
---

Berikut adalah struktur database dari aplikasi kasir toko oleh-oleh: 

![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/b0fbeb6ccc9c2528353a8403fed1cf51a0a97509/SCREENSHOOT/Struktur%20Database.png)

1. Kelas produk
   Menyimpan informasi tentang daftar produk yang dijual di toko.
   (Diacu oleh tabel item_transaksi melalui kolom kode_produk)

  | Kolom         | Tipe Data     | Keterangan                         |
  | ------------- | ------------- | ---------------------------------- |
  | `kode_produk` | `varchar(10)` | **Primary key** – Kode unik produk |
  | `nama_produk` | `varchar(50)` | Nama produk                        |
  | `harga`       | `int(11)`     | Harga satuan produk                |
  | `sisa_stok`   | `int(11)`     | Jumlah stok yang tersedia          |

2. Tabel transaksi
   Menyimpan informasi utama dari setiap transaksi (penjualan).
   (Diacu oleh tabel item_transaksi melalui kolom transaksi_id)

  | Kolom         | Tipe Data  | Keterangan                               |
  | ------------- | ---------- | ---------------------------------------- |
  | `id`          | `int(11)`  | **Primary key** – ID transaksi           |
  | `tanggal`     | `datetime` | Tanggal dan waktu transaksi              |
  | `total_bayar` | `int(11)`  | Total harga semua produk dalam transaksi |
  | `bayar`       | `int(11)`  | Uang yang dibayarkan oleh pembeli        |
  | `kembalian`   | `int(11)`  | Uang kembalian ke pembeli                |

3. Tabel  item_transaksi
   Merupakan tabel relasi antara transaksi dan produk, yang mencatat detail setiap item yang dibeli dalam suatu transaksi.

  | Kolom          | Tipe Data     | Keterangan                                   |
  | -------------- | ------------- | -------------------------------------------- |
  | `id`           | `int(11)`     | **Primary key** – ID item transaksi          |
  | `transaksi_id` | `int(11)`     | **Foreign key** ke `transaksi(id)`           |
  | `kode_produk`  | `varchar(20)` | **Foreign key** ke `produk(kode_produk)`     |
  | `jumlah`       | `int(11)`     | Jumlah produk yang dibeli                    |
  | `subtotal`     | `int(11)`     | Total harga dari produk ini (jumlah × harga) |

4. Relasi Antar Tabel
   * transaksi → item_transaksi
     Satu transaksi dapat memiliki banyak item transaksi (one-to-many).
   * produk → item_transaksi
     Satu produk dapat muncul di banyak item transaksi (one-to-many).
   
---

# Screenshoot Tampilan 

---

Berikut adalah tampilan dari aplikasi kasir toko oleh-oleh untuk beberapa bagian:

1. Tampilan bagian tambah produk
![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/5648469b308d376b3a3646615b6926b55567c436/SCREENSHOOT/Tampilan%20Bagian%20Tambah%20Produk.png)


2. Tampilan bagian daftar produk
![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/e5a422e913adad418f12670782a268a7a1d93836/SCREENSHOOT/Tampilan%20Bagian%20Daftar%20Produk.png)


3. Tampilan bagian transaksi
![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/e5a422e913adad418f12670782a268a7a1d93836/SCREENSHOOT/Tampilan%20Bagian%20Transaksi.png)


4. Tampilan bagian struk 
![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/e5a422e913adad418f12670782a268a7a1d93836/SCREENSHOOT/Tampilan%20Bagian%20Struk.png)



