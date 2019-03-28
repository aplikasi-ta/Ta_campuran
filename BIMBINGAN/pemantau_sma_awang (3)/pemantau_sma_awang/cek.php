  <?php
error_reporting(0);

include "koneksi.php";
  $user = @$_POST['username'];
  $pass = @$_POST['password'];

$login=mysql_query("select * from user where username='$user' and password = md5('$pass')");
$ketemu=mysql_num_rows($login);
$r=mysql_fetch_array($login);

// Apabila username dan password ditemukan
if ($ketemu > 0){
  session_start();
  
  // inisialisasi session /////////
  
  ("username");
  ("password");
  ("level");
  
  

  $_SESSION[username]     = $r[username];
  $_SESSION[password]     = $r[password];
  $_SESSION[level]       = $r[level];
 
 
  
  
  header('location:media.php');
}
else{
  echo "<SCRIPT language=Javascript>
  alert('Login Anda Gagal,  username dan password tidak valid. Silahkan Hubungi Admin')
  </script>";
  echo "
  location:location: /pemantau_sma_awang/login.php";
}
?>