

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


## 8. Kelas **StrukPrinter**

**Deskripsi:**
Menghasilkan output struk transaksi.

**Metode utama:**

* `generateStruk(Transaksi t, List<DetailTransaksi> details)` — menghasilkan string struk transaksi untuk ditampilkan atau dicetak.
  

## 9. **Servlets (Controller)**

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


