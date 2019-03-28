<?php 

$id = @$_GET['id'];

mysql_query("delete from laporan_prestasi where kode_laporan_pres='$id'")or die (mysql_error());

 ?>
 <script type="text/javascript">
 	window.location.href="?page=prestasi_siswa";
 </script>