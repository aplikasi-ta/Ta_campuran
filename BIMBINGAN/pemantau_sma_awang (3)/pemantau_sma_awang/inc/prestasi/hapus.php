<?php 

$id = @$_GET['id'];

mysql_query("delete from point_prestasi where kode_prestasi='$id'")or die (mysql_error());

 ?>
 <script type="text/javascript">
 	window.location.href="?page=prestasi";
 </script>