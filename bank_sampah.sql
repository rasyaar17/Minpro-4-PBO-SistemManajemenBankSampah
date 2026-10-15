-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 15, 2026 at 12:28 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank_sampah`
--

-- --------------------------------------------------------

--
-- Table structure for table `penyetor`
--

CREATE TABLE `penyetor` (
  `id_penyetor` int NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `penyetor`
--

INSERT INTO `penyetor` (`id_penyetor`, `nama`, `alamat`) VALUES
(1, 'Budi Santoso', 'Jl. Mawar No. 5'),
(2, 'Siti Aminah', 'Jl. Melati No. 12');

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `id_petugas` int NOT NULL,
  `nama` varchar(30) NOT NULL,
  `jabatan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`id_petugas`, `nama`, `jabatan`) VALUES
(1, 'Andi Pratama', 'Petugas Penimbang'),
(2, 'Dewi Lestari', 'Petugas Pencatat');

-- --------------------------------------------------------

--
-- Table structure for table `sampah`
--

CREATE TABLE `sampah` (
  `id_sampah` int NOT NULL,
  `jenis` varchar(10) NOT NULL,
  `berat` decimal(6,2) NOT NULL,
  `id_penyetor` int NOT NULL,
  `id_petugas` int NOT NULL,
  `mudah_terurai` varchar(5) DEFAULT NULL,
  `estimasi_hari_kompos` int DEFAULT NULL,
  `dapat_didaur_ulang` varchar(5) DEFAULT NULL,
  `jenis_bahan` varchar(10) DEFAULT NULL
) ;

--
-- Dumping data for table `sampah`
--

INSERT INTO `sampah` (`id_sampah`, `jenis`, `berat`, `id_penyetor`, `id_petugas`, `mudah_terurai`, `estimasi_hari_kompos`, `dapat_didaur_ulang`, `jenis_bahan`) VALUES
(1, 'Organik', '5.00', 1, 1, 'Ya', 30, NULL, NULL),
(2, 'Anorganik', '3.50', 2, 2, NULL, NULL, 'Ya', 'Plastik'),
(3, 'Organik', '2.00', 2, 1, 'Ya', 45, NULL, NULL),
(4, 'Anorganik', '2.50', 1, 2, NULL, NULL, 'Ya', 'Logam'),
(5, 'Organik', '3.50', 1, 2, 'Ya', 2, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `penyetor`
--
ALTER TABLE `penyetor`
  ADD PRIMARY KEY (`id_penyetor`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indexes for table `sampah`
--
ALTER TABLE `sampah`
  ADD PRIMARY KEY (`id_sampah`),
  ADD KEY `id_penyetor` (`id_penyetor`),
  ADD KEY `id_petugas` (`id_petugas`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `penyetor`
--
ALTER TABLE `penyetor`
  MODIFY `id_penyetor` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `petugas`
--
ALTER TABLE `petugas`
  MODIFY `id_petugas` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sampah`
--
ALTER TABLE `sampah`
  MODIFY `id_sampah` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sampah`
--
ALTER TABLE `sampah`
  ADD CONSTRAINT `sampah_ibfk_1` FOREIGN KEY (`id_penyetor`) REFERENCES `penyetor` (`id_penyetor`),
  ADD CONSTRAINT `sampah_ibfk_2` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
