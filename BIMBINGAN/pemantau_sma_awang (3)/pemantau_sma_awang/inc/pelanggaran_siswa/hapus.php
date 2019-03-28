<?php 

$id = @$_GET['id'];

mysql_query("delete from laporan_pelanggaran where kode_laporan_pel='$id'")or die (mysql_error());

 ?>
 <script type="text/javascript">
 	window.location.href="?page=pelanggaran_siswa>";
 </script>