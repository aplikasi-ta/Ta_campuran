<?php 
 $level = ($_SESSION['level']);
if ($level == "admin") {
  include"das_admin.php";
}
elseif ($level == "siswa") {
  include"siswa.php";
}
elseif ($level == "walikelas") {
  include"walikelas.php";
}else{
  echo"";
}