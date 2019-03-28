<?php 

$id = @$_GET['id'];

mysql_query("delete from user where id_user='$id'")or die (mysql_error());

 ?>
 <script type="text/javascript">
 	window.location.href="?page=user";
 </script>