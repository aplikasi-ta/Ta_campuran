<?php 

$id = @$_GET['id'];

mysql_query("delete from kelas where kode_kelas='$id'")or die (mysql_error());

 ?>
 <script type="text/javascript">
 	window.location.href="?page=kelas";
 </script>