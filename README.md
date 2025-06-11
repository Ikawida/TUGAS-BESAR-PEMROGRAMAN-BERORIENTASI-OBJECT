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

Website ini merupakan sistem kasir digital yang dirancang untuk membantu proses transaksi penjualan di toko oleh-oleh. Sistem ini memungkinkan pencatatan dan pengelolaan produk, transaksi, dan detail pembelian secara terstruktur dan efisien.

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

## 1. Kelas **Toko** *(Superclass)*

**Deskripsi:**
Mewakili entitas umum dari barang yang dijual di toko, menyimpan atribut dasar seperti kode dan nama.

**Atribut:**

* `kodeProduk : String` — kode unik produk (PK)
* `namaProduk : String` — nama produk

**Metode utama:**

* `getKodeProduk()`, `setKodeProduk()`
* `getNamaProduk()`, `setNamaProduk()`

**Relasi:**

* Menjadi superclass dari **Produk**.
* Menyediakan atribut dasar yang bisa digunakan oleh kelas turunan lain di masa depan (konsep reusable inheritance).


## 2. Kelas **Produk** *(extends Toko)*

**Deskripsi:**
Mewakili produk oleh-oleh yang dijual toko. Kelas ini mewarisi kode dan nama dari `Toko`.

**Atribut tambahan:**

* `harga : int` — harga satuan produk
* `sisaStok : int` — jumlah stok yang tersedia

**Metode utama:**

* `getHarga()`, `setHarga()`
* `getSisaStok()`, `setSisaStok()`
* (Metode dari superclass `Toko` juga dapat digunakan: `getKodeProduk()`, `getNamaProduk()`, dll.)

**Relasi:**

* Digunakan oleh **DetailTransaksi** (relasi many-to-one) — banyak detail transaksi dapat memakai satu produk.


## 3. Kelas **Transaksi** 

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


## 4. Kelas **DetailTransaksi** 

**Deskripsi:**
Mewakili rincian produk yang dibeli dalam satu transaksi.

**Atribut:**

* `idDetail : int` — ID detail transaksi (PK)
* `transaksi : Transaksi` — referensi ke objek Transaksi
* `produk : Produk` — referensi ke objek Produk
* `jumlah : int` — jumlah produk yang dibeli
* `subtotal : int` — harga subtotal (harga produk × jumlah)

**Metode utama:**

* Getter dan setter semua atribut
* `hitungSubtotal()` — menghitung subtotal berdasarkan harga dan jumlah

**Relasi:**

* Berelasi many-to-one dengan **Transaksi**
* Berelasi many-to-one dengan **Produk**


## 5. Kelas **ProdukDAO** 

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
* Digunakan oleh controller (`ProdukServlet`) untuk manajemen produk.


## 6. Kelas **TransaksiDAO** 

**Deskripsi:**
Mengelola data transaksi di database.

**Metode utama:**

* `saveTransaksi(Transaksi t)` — simpan transaksi baru
* `getTransaksiById(int id)` — ambil transaksi berdasarkan ID
* `getAllTransaksi()` — ambil semua transaksi

**Relasi:**

* Berinteraksi langsung dengan tabel transaksi di database.


## 7. Kelas **DetailTransaksiDAO** 

**Deskripsi:**
Mengelola data detail transaksi di database.

**Metode utama:**

* `saveDetail(DetailTransaksi dt)` — simpan detail transaksi
* `getDetailsByTransaksi(Transaksi t)` — ambil semua detail produk dari transaksi tertentu

**Relasi:**

* Berinteraksi langsung dengan tabel detail transaksi.


## 8. Kelas **DatabaseConnection** 

**Deskripsi:**
Membuat dan mengelola koneksi database MySQL.

**Metode utama:**

* `getConnection()` — mengembalikan objek `Connection` untuk akses database.


## 9. **Servlets (Controller)** 

**Deskripsi:**
Menghubungkan antara interface web (JSP) dan logika bisnis aplikasi.

**Daftar Servlet:**

### `ProdukServlet`

Mengatur permintaan terkait produk (lihat, tambah, ubah, hapus).

* `doGet()` — menampilkan daftar produk atau form edit
* `doPost()` — memproses penyimpanan produk (tambah atau update)

### `TransaksiServlet`

Mengelola proses transaksi pelanggan.

* `doGet()` — menampilkan form transaksi atau daftar
* `doPost()` — menyimpan transaksi dan detailnya ke database

### `StrukServlet`

Menampilkan struk transaksi.

* `doGet()` — mengambil data transaksi dan detail lalu kirim ke `struk.jsp`

**Relasi:**

* Memanggil DAO untuk akses data
* Mengelola alur logika sesuai permintaan dari user via browser
* Mengirim data ke JSP untuk ditampilkan ke user


---
# Relasi Antar Kelas
---

| **Kelas 1**          | **Jenis Relasi**  | **Kelas 2**                                       | **Keterangan**                                                      |
| -------------------- | ----------------- | ------------------------------------------------- | ------------------------------------------------------------------- |
| `Toko`               | Inheritance       | `Produk`                                          | `Produk` mewarisi atribut dasar `kodeProduk` dan `namaProduk`       |
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

![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/1fcebeeb855198f94050b8aa872224be01515437/SCREENSHOOT/Kelas%20Diagram.png)


---
# Konsep OOP
---

Berikut adalah prinsip oop yang ada didalam aplikasi kasir toko oleh-oleh:
| **Prinsip OOP**                 | **Penjelasan Konsep**                                                                        | **Penerapan Rinci dalam Proyek**                                                                                                                                                                          | **Lokasi/File Terkait**                                                       | **Manfaat Utama**                                                                           |
| ------------------------------- | -------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| **Encapsulation**               | Menyembunyikan atribut (biasanya `private`) dan memberikan akses lewat `getter`/`setter`.    | - Kelas `Produk` menyimpan atribut seperti `kodeProduk`, `namaProduk`, `harga` sebagai `private`.<br>- Akses dilakukan melalui method `getKodeProduk()`, `setHarga()`, dll.                               | `model/Produk.java`, `model/Transaksi.java`, `model/DetailTransaksi.java`     | Melindungi data agar tidak dimanipulasi langsung dari luar kelas; menjaga integritas objek. |
| **Abstraction**                 | Menyembunyikan detail implementasi dan hanya menampilkan interface penting.                  | - Method `getAllProduk()`, `addProduk()` di `ProdukDAO` menyembunyikan proses SQL dari controller.<br>- Controller cukup memanggil method tanpa tahu detail query.                                        | `dao/ProdukDAO.java`, `dao/TransaksiDAO.java`, `util/DatabaseConnection.java` | Menyederhanakan penggunaan kelas kompleks, memperjelas alur kerja antar komponen.           |
| **Inheritance**                 | Subclass mewarisi atribut dan method dari superclass.                                        | - `ProdukServlet`, `TransaksiServlet`, dan `StrukServlet` mewarisi dari `HttpServlet`.<br>- Kelas `Produk` dan `Transaksi` bisa mewarisi dari `Toko` sebagai superclass.                                  | `controller/*.java`, `model/Produk.java`                                      | Menghindari duplikasi kode, menyusun hierarki logis antar kelas.                            |
| **Polymorphism**                | Method dengan nama sama memiliki implementasi berbeda pada kelas yang berbeda.               | - Method `doPost()` di setiap servlet (`ProdukServlet`, `TransaksiServlet`, `StrukServlet`) punya isi berbeda meski nama sama.<br>- `toString()` bisa dioverride jika diperlukan untuk menampilkan objek. | `controller/ProdukServlet.java`, `TransaksiServlet.java`, `StrukServlet.java` | Memberikan fleksibilitas dalam implementasi logika berbeda sesuai tugas masing-masing.      |
| **Constructor**                 | Method khusus untuk inisialisasi objek dengan parameter.                                     | - Pembuatan objek `Produk`, `Transaksi`, dan `DetailTransaksi` menggunakan constructor:<br>`new Produk(kode, nama, harga)`                                                                                | `ProdukDAO.java`, `TransaksiDAO.java`                                         | Memudahkan pembuatan objek yang telah terisi data dengan rapi.                              |
| **Association**                 | Hubungan antar objek yang longgar (bisa saling referensi).                                   | - `DetailTransaksi` menyimpan referensi ke objek `Produk` dan `Transaksi`.<br>`private Produk produk; private Transaksi transaksi;`                                                                       | `model/DetailTransaksi.java`                                                  | Membangun hubungan antar entitas dalam sistem.                                              |
| **Composition**                 | Satu objek adalah bagian dari objek lain secara kuat (jika induk dihapus, anak ikut hilang). | - `Transaksi` memiliki `List<DetailTransaksi>` sebagai bagian dari transaksinya.<br>`private List<DetailTransaksi> detailList;`                                                                           | `model/Transaksi.java`, `dao/TransaksiDAO.java`                               | Menyusun objek kompleks dari bagian-bagian yang saling bergantung.                          |
| **Error Handling**              | Penanganan kesalahan agar aplikasi tetap berjalan meski terjadi error.                       | - Penggunaan `try-catch` untuk menangani error SQL:<br>`catch (SQLException e) { e.printStackTrace(); }`<br>- Penanganan error seperti `Duplicate entry` saat insert.                                     | `ProdukDAO.java`, `TransaksiDAO.java`, seluruh `Servlet`                      | Meningkatkan keandalan sistem dan membantu dalam proses debugging.                          |
| **Interface (via JSP)**         | Antarmuka pengguna dipisahkan dari logika program menggunakan JSP (View).                    | - File `produk.jsp`, `transaksi.jsp` menampilkan data dari controller via scriptlet JSP:<br>`<%= produk.getNamaProduk() %>`                                                                               | `web/jsp/produk.jsp`, `transaksi.jsp`, `struk.jsp`                            | Menerapkan prinsip MVC, memisahkan logic dari tampilan, memudahkan pengembangan UI.         |
| **Modularization** *(Tambahan)* | Memisahkan kode ke dalam modul/folder berdasarkan tanggung jawab.                            | - Folder `model` untuk entitas, `dao` untuk data access, `controller` untuk servlet, `util` untuk tools umum.                                                                                             | Struktur folder proyek                                                        | Kode lebih terstruktur, mudah dipahami dan dikelola oleh tim.                               |
---
# Error Handling 
---


Berikut adalah penerapan error handling dalam website kasir toko oleh-oleh: 

| **Jenis Error**                              | **Skenario Terjadi**                                                     | **Cara Penanganan**                                                                                                                             | **Manfaat Penanganan**                                                     |                                 |                                                            |
| -------------------------------------------- | ------------------------------------------------------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------- | ------------------------------- | ---------------------------------------------------------- |
| **SQLIntegrityConstraintViolationException** | Insert produk dengan `kodeProduk` yang sudah ada (primary key duplikat). | Try-catch SQL dan tampilkan pesan kesalahan:<br>`catch (SQLIntegrityConstraintViolationException e)`<br>Log `e.getMessage()` atau kirim ke JSP. | Memberi tahu user bahwa kode produk harus unik; mencegah aplikasi crash.   |                                 |                                                            |
| **SQLException**                             | Kesalahan koneksi database, query gagal, dsb.                            | Tangkap dalam blok `try-catch`:<br>`catch (SQLException e) { e.printStackTrace(); }`                                                            | Mendeteksi dan melacak kesalahan database; tidak memutuskan alur aplikasi. |                                 |                                                            |
| **NumberFormatException**                    | User input angka (misalnya harga atau jumlah) tapi input huruf.          | Validasi sebelum parsing:<br>`Integer.parseInt(request.getParameter("jumlah"))` dibungkus try-catch.                                            | Menjaga kestabilan aplikasi saat user salah input.                         |                                 |                                                            |
| **NullPointerException**                     | Data dari database tidak ditemukan tapi tetap diakses.                   | Cek null sebelum akses:<br>`if (produk != null) { ... }`                                                                                        | Mencegah error fatal ketika objek kosong digunakan.                        |                                 |                                                            |
| **ClassNotFoundException**                   | Driver JDBC tidak tersedia.                                              | Tangkap dan log saat koneksi:<br>`Class.forName("com.mysql.cj.jdbc.Driver")` dalam try-catch.                                                   | Memastikan driver database sudah ada; mudah dideteksi saat deploy.         |                                 |                                                            |
| **404 Not Found**                            | JSP atau servlet tidak ditemukan saat diakses.                           | Atur di `web.xml` atau mapping servlet dengan benar. Buat halaman error custom JSP.                                                             | Memberikan pesan error yang ramah ke user.                                 |                                 |                                                            |
| **500 Internal Server Error**                | Error runtime saat servlet eksekusi (umum).                              | Log stack trace dan tampilkan error page.<br>Gunakan try-catch di `doGet` / `doPost`.                                                           | Memudahkan debugging dan menjaga tampilan user tetap rapi.                 |                                 |                                                            |
| **Input Kosong / Validasi Form**             | Field wajib kosong saat disubmit.                                        | Validasi sebelum insert/update:<br>\`if(namaProduk == null                                                                                      |                                                                            | namaProduk.trim().equals(""))\` | Meningkatkan kualitas data yang masuk dan user experience. |
| **JSP Runtime Error**                        | Ekspresi Java di JSP error karena null atau typo.                        | Gunakan EL (Expression Language) yang lebih aman dari NPE:<br>`${produk.nama}`                                                                  | Mencegah error JSP yang memutus alur halaman.                              |                                 |                                                            |


---
# Struktur Database
---

Berikut adalah struktur database dari aplikasi kasir toko oleh-oleh: 

![alt text](https://github.com/Ikawida/TUGAS-BESAR-PEMROGRAMAN-BERORIENTASI-OBJECT/blob/b0fbeb6ccc9c2528353a8403fed1cf51a0a97509/SCREENSHOOT/Struktur%20Database.png)

1. Tabel produk
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



