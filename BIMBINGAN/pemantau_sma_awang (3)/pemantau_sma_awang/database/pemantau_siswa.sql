-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 30, 2018 at 04:12 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pemantau_siswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `kategori_pelanggaran`
--

CREATE TABLE IF NOT EXISTS `kategori_pelanggaran` (
  `kode_kategori` varchar(10) NOT NULL,
  `nama_kategori` varchar(100) NOT NULL,
  PRIMARY KEY (`kode_kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori_pelanggaran`
--

INSERT INTO `kategori_pelanggaran` (`kode_kategori`, `nama_kategori`) VALUES
('K01', 'Terlambat'),
('K02', 'Absen Tidak Hadir'),
('K03', 'Pakaian Seragam atau Tata Tertib'),
('K04', 'Pemalsuan'),
('K05', 'Kebersihan'),
('K06', 'Sikap, Tindakan dan Ucapan'),
('K07', 'Rambut'),
('K08', 'Merokok'),
('K09', 'Perjudian'),
('K10', 'Kriminalitas');

-- --------------------------------------------------------

--
-- Table structure for table `kategori_prestasi`
--

CREATE TABLE IF NOT EXISTS `kategori_prestasi` (
  `kode_kat` varchar(10) NOT NULL,
  `nama_kat` varchar(40) NOT NULL,
  PRIMARY KEY (`kode_kat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori_prestasi`
--

INSERT INTO `kategori_prestasi` (`kode_kat`, `nama_kat`) VALUES
('PR01', 'Kedisiplinan Sekolah'),
('PR02', 'Prestasi Akademik dan Non Akademik'),
('PR03', 'Aktif dalam Kepengurusan');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE IF NOT EXISTS `kelas` (
  `kode_kelas` varchar(5) NOT NULL,
  `nama_kelas` varchar(10) NOT NULL,
  PRIMARY KEY (`kode_kelas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`kode_kelas`, `nama_kelas`) VALUES
('KL11', 'X 1'),
('KL12', 'X 2'),
('KL13', 'X 3');

-- --------------------------------------------------------

--
-- Table structure for table `laporan_pelanggaran`
--

CREATE TABLE IF NOT EXISTS `laporan_pelanggaran` (
  `kode_laporan_pel` int(5) NOT NULL AUTO_INCREMENT,
  `NIS` int(10) NOT NULL,
  `kode_pelanggaran` varchar(10) NOT NULL,
  `tanggal_pelanggaran` date NOT NULL,
  `keterangan` varchar(20) NOT NULL,
  PRIMARY KEY (`kode_laporan_pel`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `laporan_pelanggaran`
--

INSERT INTO `laporan_pelanggaran` (`kode_laporan_pel`, `NIS`, `kode_pelanggaran`, `tanggal_pelanggaran`, `keterangan`) VALUES
(1, 121627, '6', '2018-04-30', 'Sudah diberi sanksi'),
(3, 121627, '7', '2018-04-30', 'Sudah diberi sanksi'),
(4, 121627, '11', '2018-04-30', 'Sudah diberi sanksi'),
(5, 1234567, '8', '2018-05-02', 'Sudah diberi sanksi'),
(6, 121627, '5', '2018-05-02', 'Sudah diberi sanksi');

-- --------------------------------------------------------

--
-- Table structure for table `laporan_prestasi`
--

CREATE TABLE IF NOT EXISTS `laporan_prestasi` (
  `kode_laporan_pres` int(5) NOT NULL AUTO_INCREMENT,
  `NIS` int(13) NOT NULL,
  `kode_prestasi` varchar(10) NOT NULL,
  `tanggal_laporan` date NOT NULL,
  PRIMARY KEY (`kode_laporan_pres`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `laporan_prestasi`
--

INSERT INTO `laporan_prestasi` (`kode_laporan_pres`, `NIS`, `kode_prestasi`, `tanggal_laporan`) VALUES
(1, 1234567, '8', '2018-05-02'),
(2, 121627, '7', '2018-07-27');

-- --------------------------------------------------------

--
-- Table structure for table `point_pelanggaran`
--

CREATE TABLE IF NOT EXISTS `point_pelanggaran` (
  `kode_pelanggaran` int(10) NOT NULL AUTO_INCREMENT,
  `kode_kategori` varchar(10) NOT NULL,
  `nama_pelanggaran` varchar(100) NOT NULL,
  `point` int(10) NOT NULL,
  `sanksi` text NOT NULL,
  PRIMARY KEY (`kode_pelanggaran`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `point_pelanggaran`
--

INSERT INTO `point_pelanggaran` (`kode_pelanggaran`, `kode_kategori`, `nama_pelanggaran`, `point`, `sanksi`) VALUES
(3, 'K01', 'Siswa terlambat pada jam I', 2, 'Dilarang mengikuti pelajaran pada jam I, siswa diberi sanksi ketertiban seperti baris berbaris, lari'),
(4, 'K02', 'Alpa', 5, 'Pembinaan oleh BK dan wali kelas'),
(5, 'K02', 'Membolos', 5, 'Pembinaan'),
(6, 'K03', 'Memakai seragam tidak rapi', 2, 'Diperingatkan dan disuruh melengkapi'),
(7, 'K04', 'Memalsukan Surat Izin', 5, 'Diperingatkan dan dibina'),
(8, 'K05', 'Membuang Sampah didalam kelas atau sembarang tempat', 1, 'Diperingatkan dan dibina'),
(9, 'K06', 'Bertindak/berucap tidak sopan pada teman/berkata jorok', 5, 'Diperingatkan oleh guru/petugas yang mengetahui'),
(10, 'K07', 'Peserta didik laki-laki berambut gondrong/tidak rapih', 3, 'dipotong oleh guru/petugas yang mengetahui dan dirapihkan dirumah sendiri'),
(11, 'K08', 'Membawa rokok ke sekolah', 15, 'Diperingatkan, barang disita dan orang tua diberi tau'),
(12, 'K09', 'Taruhan dengan sesama teman', 10, 'Diperingatkan, barang disita dan orang tua diberi tahu'),
(13, 'K10', 'Merusak barang milik orang lain dengan tidak bertanggung jawab', 20, 'Diperingatkan dan dibina');

-- --------------------------------------------------------

--
-- Table structure for table `point_prestasi`
--

CREATE TABLE IF NOT EXISTS `point_prestasi` (
  `kode_prestasi` int(10) NOT NULL AUTO_INCREMENT,
  `kode_kat` varchar(5) NOT NULL,
  `nama_prestasi` varchar(100) NOT NULL,
  `point` int(10) NOT NULL,
  PRIMARY KEY (`kode_prestasi`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `point_prestasi`
--

INSERT INTO `point_prestasi` (`kode_prestasi`, `kode_kat`, `nama_prestasi`, `point`) VALUES
(5, 'PR01', 'Tidak Melanggar', 10),
(6, 'PR02', 'Juara kelas 1,2,3', 10),
(7, 'PR02', 'Juara kecamatan 1,2,3', 20),
(8, 'PR03', 'Kepengurusan OSIS sebagai ketua', 10),
(9, 'PR02', 'Juara Nasional 1,2,3', 30);

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE IF NOT EXISTS `siswa` (
  `NIS` varchar(10) NOT NULL,
  `nama_siswa` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `agama` varchar(10) NOT NULL,
  `alamat` text NOT NULL,
  `no_hp` varchar(12) NOT NULL,
  `email` varchar(30) NOT NULL,
  `photo` text NOT NULL,
  `kode_kelas` varchar(5) NOT NULL,
  PRIMARY KEY (`NIS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`NIS`, `nama_siswa`, `jenis_kelamin`, `agama`, `alamat`, `no_hp`, `email`, `photo`, `kode_kelas`) VALUES
('121627', 'Muhammad Dani Ramanda', 'Laki-laki', 'Islam', 'bandar', '089798', 'awang@gmail.com', 'Foto-Terbaru-Fuleco-Maskot-Piala-Dunia-2014.jpg', 'KL11'),
('1234', 'Kusnadi', 'Laki-laki', 'islam', 'bandar lampung', '08436274687', 'kusnadi@gmail.com', 'Foto-Maskot-Piala-Dunia-2014-Fuleco.jpg', 'KL12'),
('1234567', 'Dedi Kusnandar', 'Laki-laki', 'Islam', 'Bandar lampung', '08923826387', 'dedi@gmail.com', '', 'KL13');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  `level` varchar(20) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `level`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin'),
(3, '121627', '49c398866547eeb99da6b99c7c6ae7cc', 'siswa'),
(4, '1234', '81dc9bdb52d04dc20036dbd8313ed055', 'siswa'),
(5, '1234567', 'fcea920f7412b5da7be0cf42b8c93759', 'siswa'),
(6, '8989', 'b66dc44cd9882859d84670604ae276e6', 'walikelas');

-- --------------------------------------------------------

--
-- Table structure for table `wali_kelas`
--

CREATE TABLE IF NOT EXISTS `wali_kelas` (
  `NIP` varchar(10) NOT NULL,
  `kode_kelas` varchar(5) NOT NULL,
  `nama_walikelas` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `agama` varchar(30) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_hp` varchar(12) NOT NULL,
  `email` varchar(40) NOT NULL,
  `poto_wali` text NOT NULL,
  PRIMARY KEY (`NIP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `wali_kelas`
--

INSERT INTO `wali_kelas` (`NIP`, `kode_kelas`, `nama_walikelas`, `jenis_kelamin`, `agama`, `alamat`, `no_hp`, `email`, `poto_wali`) VALUES
('8989', 'KL11', 'Junaidi', 'Laki-laki', 'Islam', 'Bandar Lampung', '08323647634', 'junaidi@gmail.com', 'avatar.png');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
