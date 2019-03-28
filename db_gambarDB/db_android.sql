-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 01. Okt 2012 um 04:59
-- Server Version: 5.5.25a
-- PHP-Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `db_android`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbl_artikel`
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
-- Daten für Tabelle `tbl_artikel`
--

INSERT INTO `tbl_artikel` (`id_artikel`, `judul`, `gambar`, `tanggal`, `waktu`) VALUES
(1, 'Batik Andorid\n', 'baju.png', '2010-07-31', '01:37:28'),
(2, 'Batik Java', 'javadalam.png', '2010-07-31', '01:35:18'),
(3, 'Tutorial Android', 'kaset.png', '2010-10-09', '10:46:00'),
(6, 'Kaos Ubuntu', 'KasoUbuntu.png', '2012-10-1', '10:10:10'),
(4, 'Android Kaos', 'KasoAndroid.png', '2012-10-1', '11:11:00'),
(5, 'Kaos Ios', 'Kasoios.png', '2012-10-1', '10:10:10');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
