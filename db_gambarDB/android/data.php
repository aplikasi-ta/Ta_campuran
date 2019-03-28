<?php
include("koneksi.php");
$q = mysql_query("SELECT * FROM tbl_artikel");
$v = '{"berita" : [';
while($r=mysql_fetch_array($q))
{
	if(strlen($v)<15)
	{
		$v .= '{"judul" : "'.strip_tags($r['judul']).'", "gambar" : "http://10.0.2.2/android/gb_berita/'.strip_tags($r['gambar']).'", "tanggal" : "'.$r['tanggal'].'", "waktu" : "'.$r['waktu'].'"}';
	}
	else
	{
		$v .= ',{"judul" : "'.strip_tags($r['judul']).'", "gambar" : "http://10.0.2.2/android/gb_berita/'.strip_tags($r['gambar']).'", "tanggal" : "'.$r['tanggal'].'", "waktu" : "'.$r['waktu'].'"}';
	}

}
$v .= ']}';
	echo $v;
?>