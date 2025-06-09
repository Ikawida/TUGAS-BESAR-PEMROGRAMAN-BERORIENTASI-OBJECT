-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2025 at 04:05 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko`
--

-- --------------------------------------------------------

--
-- Table structure for table `item_transaksi`
--

CREATE TABLE `item_transaksi` (
  `id` int(11) NOT NULL,
  `transaksi_id` int(11) DEFAULT NULL,
  `kode_produk` varchar(20) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `subtotal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item_transaksi`
--

INSERT INTO `item_transaksi` (`id`, `transaksi_id`, `kode_produk`, `jumlah`, `subtotal`) VALUES
(1, 1, '016C', 1, 10000),
(2, 2, '016C', 1, 10000),
(3, 3, '016C', 6, 72000),
(4, 4, '016C', 2, 24000),
(5, 5, '016C', 8, 96000),
(6, 6, '016C', 1, 12000),
(7, 6, '382A', 3, 30000),
(8, 6, '394E', 7, 105000),
(9, 7, '251N', 6, 72000),
(10, 7, '016C', 9, 108000),
(11, 8, '016C', 8, 96000),
(12, 9, '908P', 20, 280000),
(13, 10, '145F', 6, 78000),
(14, 11, '728D', 5, 40000),
(15, 12, '947B', 5, 75000),
(16, 13, '201G', 3, 45000),
(17, 14, '320J', 5, 50000),
(18, 15, '320J', 3, 30000),
(19, 16, '382A', 3, 30000),
(20, 17, '320J', 5, 50000),
(21, 18, '777Q', 2, 20000),
(22, 19, '633M', 2, 20000),
(23, 20, '251N', 5, 60000),
(24, 20, '016C', 1, 12000),
(25, 20, '633M', 7, 70000),
(26, 21, '777Q', 5, 50000),
(27, 21, '889H', 4, 40000),
(28, 21, '6JK', 7, 105000),
(29, 21, '728D', 6, 48000),
(30, 22, '908P', 3, 42000),
(31, 23, '908P', 2, 28000),
(32, 23, '016C', 1, 12000),
(33, 23, '728D', 1, 8000),
(34, 24, '016C', 6, 90000),
(35, 24, '394E', 3, 45000),
(36, 25, '6JK', 5, 75000),
(37, 26, '563K', 1, 13000),
(38, 27, '145F', 1, 13000),
(39, 28, '097A', 4, 32000),
(40, 29, '097A', 5, 50000);

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `kode_produk` varchar(10) NOT NULL,
  `nama_produk` varchar(50) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `sisa_stok` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`kode_produk`, `nama_produk`, `harga`, `sisa_stok`) VALUES
('016C', 'Teng teng', 15000, 27),
('097A', 'Keripik Pisang', 10000, 16),
('145F', 'Nopia cokelat', 13000, 29),
('201G', 'Kue wijem', 15000, 21),
('251N', 'Rempeyek kacang', 12000, 20),
('320J', 'Keripik tahu', 10000, 26),
('382A', 'Keripik tempe', 10000, 19),
('394E', 'Keripik tenggiri', 15000, 20),
('478L', 'Lanting', 10000, 19),
('563K', 'Stik sukun', 13000, 29),
('633M', 'Untir-untir', 10000, 26),
('6JK', 'keripik lele', 15000, 20),
('728D', 'Keripik bawang', 8000, 33),
('777Q', 'Sarang madu', 10000, 19),
('889H', 'Rengginang', 10000, 21),
('908P', 'Bolu kering', 14000, 28),
('947B', 'Jenang wijen', 15000, 25);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `tanggal` datetime DEFAULT NULL,
  `total_bayar` int(11) DEFAULT NULL,
  `bayar` int(11) DEFAULT NULL,
  `kembalian` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `tanggal`, `total_bayar`, `bayar`, `kembalian`) VALUES
(1, '2025-05-30 00:00:00', 10000, 10000, 0),
(2, '2025-05-30 00:00:00', 10000, 10000, 0),
(3, '2025-06-03 00:00:00', 72000, 100000, 28000),
(4, '2025-06-03 00:00:00', 24000, 50000, 26000),
(5, '2025-06-03 00:00:00', 96000, 100000, 4000),
(6, '2025-06-03 00:00:00', 147000, 150000, 3000),
(7, '2025-06-03 00:00:00', 180000, 200000, 20000),
(8, '2025-06-03 00:00:00', 96000, 100000, 4000),
(9, '2025-06-03 00:00:00', 280000, 300000, 20000),
(10, '2025-06-03 00:00:00', 78000, 100000, 22000),
(11, '2025-06-03 00:00:00', 40000, 50000, 10000),
(12, '2025-06-03 00:00:00', 75000, 80000, 5000),
(13, '2025-06-03 00:00:00', 45000, 50000, 5000),
(14, '2025-06-03 00:00:00', 50000, 50000, 0),
(15, '2025-06-03 00:00:00', 30000, 50000, 20000),
(16, '2025-06-03 00:00:00', 30000, 50000, 20000),
(17, '2025-06-03 00:00:00', 50000, 50000, 0),
(18, '2025-06-03 06:21:41', 20000, 20000, 0),
(19, '2025-06-03 06:24:59', 20000, 50000, 30000),
(20, '2025-06-03 12:09:10', 142000, 150000, 8000),
(21, '2025-06-03 12:14:22', 243000, 250000, 7000),
(22, '2025-06-05 08:39:52', 42000, 50000, 8000),
(23, '2025-06-05 09:05:54', 48000, 100000, 52000),
(24, '2025-06-05 09:17:37', 135000, 150000, 15000),
(25, '2025-06-05 10:19:56', 75000, 75000, 0),
(26, '2025-06-06 16:23:34', 13000, 100000, 87000),
(27, '2025-06-09 16:30:03', 13000, 15000, 2000),
(28, '2025-06-09 20:45:58', 32000, 50000, 18000),
(29, '2025-06-09 20:55:49', 50000, 50000, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `item_transaksi`
--
ALTER TABLE `item_transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaksi_id` (`transaksi_id`),
  ADD KEY `kode_produk` (`kode_produk`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`kode_produk`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `item_transaksi`
--
ALTER TABLE `item_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item_transaksi`
--
ALTER TABLE `item_transaksi`
  ADD CONSTRAINT `item_transaksi_ibfk_1` FOREIGN KEY (`transaksi_id`) REFERENCES `transaksi` (`id`),
  ADD CONSTRAINT `item_transaksi_ibfk_2` FOREIGN KEY (`kode_produk`) REFERENCES `produk` (`kode_produk`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
