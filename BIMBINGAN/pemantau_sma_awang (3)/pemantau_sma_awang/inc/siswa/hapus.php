<?php 

$id = @$_GET['id'];

mysql_query("delete from anggota where nik='$id'")or die (mysql_error());

 ?>
 <script type="text/javascript">
 	window.location.href="?page=anggota";
 </script>