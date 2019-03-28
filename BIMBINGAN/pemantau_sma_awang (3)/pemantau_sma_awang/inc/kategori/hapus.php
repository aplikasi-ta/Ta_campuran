<?php 

$id = @$_GET['id'];

mysql_query("delete from kategori_pelanggaran where kode_kategori='$id'")or die (mysql_error());

 ?>
 <script type="text/javascript">
 	window.location.href="?page=kategori";
 </script>