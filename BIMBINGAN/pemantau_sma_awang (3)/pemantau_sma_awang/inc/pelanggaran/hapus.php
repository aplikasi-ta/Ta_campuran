<?php 

$id = @$_GET['id'];

mysql_query("delete from point_pelanggaran where kode_pelanggaran='$id'")or die (mysql_error());

 ?>
 <script type="text/javascript">
 	window.location.href="?page=pelanggaran";
 </script>