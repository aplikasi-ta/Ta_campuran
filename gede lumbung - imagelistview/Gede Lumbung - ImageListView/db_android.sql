-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 16, 2012 at 01:54 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_android`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_artikel`
--

CREATE TABLE IF NOT EXISTS `tbl_artikel` (
  `id_artikel` int(5) NOT NULL AUTO_INCREMENT,
  `judul` varchar(200) NOT NULL,
  `gambar` varchar(100) NOT NULL,
  `tanggal` varchar(50) NOT NULL,
  `waktu` varchar(50) NOT NULL,
  PRIMARY KEY (`id_artikel`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `tbl_artikel`
--

INSERT INTO `tbl_artikel` (`id_artikel`, `judul`, `gambar`, `tanggal`, `waktu`) VALUES
(1, '2011, Lembaga Pemerintah Sudah Harus Cicipi Open Source', 'nexus.jpg', '2010-07-31', '01:37:28'),
(2, 'Siapa Jawara Open Source Indonesia?  ', 'ldk.jpg', '2010-07-31', '01:35:18'),
(3, 'Kenapa Menggunakan Framework PHP Lebih Baik Dari Coding Manual?', 'degraf.jpg', '2010-10-09', '10:46:00'),
(4, 'Membelah Web Dengan PHP Function', 'kloso.jpg', '2010-09-11', '10:56:18'),
(5, 'Acara Latihan Dasar Kepemimpinan (LDK) UKM Kamera dan Kloso', 'ldk.jpg', '2010-07-31', '10:56:18'),
(6, 'UKM Kloso : Workshop Virtual OS di Linux', 'nexus.jpg', '2010-09-11', '01:40:18'),
(7, 'Mengembalikan Panel di Gnome Linux', 'kloso.jpg', '2010-10-09', '10:46:00'),
(8, 'Pengumuman UAS Semester Ganjil 2010/2011 dan Perpanjangan Batas Akhir Pengumpulan Surat Kerja', 'degraf.jpg', '2011-01-03', '10:00:18'),
(15, 'Install PHP, Apache, dan MySQL di Ubuntu', 'ldk.jpg', ' 2011-02-20', '10:04:am'),
(16, 'Aria2, Download Manager Untuk Linux', 'kloso.jpg', ' 2011-02-20', '10:18:am'),
(17, 'Pelatihan Desain Grafis Tingkat SMTA Se-Banyuwangi', 'ldk.jpg', ' 2011-02-20', '11:01:am'),
(18, 'Membuat Sendiri Link Share ke Sosial Network', 'degraf.jpg', ' 2011-02-20', '11:05:am');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
