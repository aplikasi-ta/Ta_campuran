<?php
error_reporting(0);
include "koneksi.php";
session_start();
?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sistem Pemantau Siswa</title>
  <!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
   <link rel="stylesheet" href="assets/css/AdminLTE.min.css">
   <link rel="stylesheet" href="assets/css/skins/_all-skins.min.css">
    <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />

    <link rel="stylesheet" href="grap/assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="grap/assets/css/ilmudetil.css">
  <script src="grap/assets/js/jquery-1.10.1.min.js"></script>
  <script src="grap/assets/js/highcharts.js"></script>

 <script language='javascript'>

function showPres()

{

<?php

// membaca semua kategori

$query = "SELECT * FROM kategori_prestasi ORDER BY kode_kat ASC";

$hasil = mysql_query($query);

// membuat if untuk masing-masing pilihan kategori beserta isi option untuk combobox kedua

while ($data = mysql_fetch_array($hasil))

{

$kat = $data['kode_kat'];

// membuat IF untuk masing-masing propinsi

echo "if (document.form2.kategoripres.value == \"".$kat."\")";

echo "{";

// membuat option kota untuk masing-masing propinsi

$query2 = "SELECT * from point_prestasi where kode_kat='$kat'
ORDER BY kode_kat ASC ";

$hasil2 = mysql_query($query2);
                  
$content = "document.getElementById('pres').innerHTML = \"";

while ($data2 = mysql_fetch_array($hasil2))

{

$content .= "<option value='".$data2['kode_prestasi']."'> ".$data2['nama_prestasi']."</option>";

}

$content .= "\"";

echo $content;

echo "}\n";

}

?>

}

</script>



 <script language='javascript'>

function showKab()

{

<?php

// membaca semua kategori

$query = "SELECT * FROM kategori_pelanggaran ORDER BY kode_kategori ASC";

$hasil = mysql_query($query);

// membuat if untuk masing-masing pilihan kategori beserta isi option untuk combobox kedua

while ($data = mysql_fetch_array($hasil))

{

$kat = $data['kode_kategori'];

// membuat IF untuk masing-masing propinsi

echo "if (document.form1.kategori.value == \"".$kat."\")";

echo "{";

// membuat option kota untuk masing-masing propinsi

$query2 = "SELECT * from point_pelanggaran where kode_kategori='$kat'
ORDER BY kode_kategori ASC ";

$hasil2 = mysql_query($query2);

$content = "document.getElementById('pelang').innerHTML = \"";

while ($data2 = mysql_fetch_array($hasil2))

{

$content .= "<option value='".$data2['kode_pelanggaran']."'> ".$data2['nama_pelanggaran']."</option>";

}

$content .= "\"";

echo $content;

echo "}\n";

}

?>

}

</script>
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">Sistem Pemantau</a> 
            </div>
  <div style="color: white;
padding: 15px 50px 5px 50px;
float: right;
font-size: 16px;"> Selamat datang <?php echo $_SESSION['level'] ?>|| <a href="logout.php" class="btn btn-danger square-btn-adjust">Logout</a> </div>
        </nav>   
           <!-- /. NAV TOP  -->
                <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
        
        
          
                    <li>
                        <a  href="media.php"><i class="fa fa-dashboard"></i> Dashboard</a>
                    </li>
                   
                    <?php 
 $level = ($_SESSION['level']);
if ($level == "admin") {
  ?> 
   <li>
      <a  href="?page=pelanggaran_siswa"><i class="fa fa-book"></i>Pelanggaran Siswa</a>
  </li>
  <li>
      <a  href="?page=prestasi_siswa"><i class="fa fa-book"></i>Prestasi Siswa</a>
  </li>
 
 
 
  <li>
      <a  href="?page=kategori"><i class="fa fa-user"></i>Kategori Pelanggaran</a>
  </li>
   <li>
      <a  href="?page=kategori_pres"><i class="fa fa-user"></i>Kategori Prestasi</a>
  </li>
   <li>
      <a  href="?page=pelanggaran"><i class="fa fa-user"></i>Point Pelanggaran</a>
  </li>
  <li>
      <a  href="?page=prestasi"><i class="fa fa-user"></i>Point Prestasi</a>
  </li>
                    
  <?php
}
elseif ($level=='walikelas') {
  ?>
  <li>
      <a  href="?page=pelanggaran_siswa"><i class="fa fa-book"></i>Pelanggaran Siswa</a>
  </li>
  <li>
      <a  href="?page=prestasi_siswa"><i class="fa fa-book"></i>Prestasi Siswa</a>
  </li>
  <li>
      <a  href="?page=walikelas&action=ubah&id=<?php echo $_SESSION['username'] ?>"><i class="fa fa-book"></i>Edit Biodata</a>
  </li>
  <?php
}
elseif ($level=='siswa') {
    ?> 
    <li>
                        <a  href="?page=pelanggaran_siswa&action=lihat_point&id=<?php echo $_SESSION[username]; ?>"><i class="fa fa-clipboard"></i> Pelanggaran</a>
                    </li>
    <li>
                        <a  href="?page=prestasi_siswa&action=lihat_point&id=<?php echo $_SESSION[username]; ?>"><i class="fa fa-clipboard"></i> Prestasi</a>
                    </li>
    <li>
                        <a  href="?page=siswa&action=ganti_password"><i class="fa fa-clipboard"></i> Ganti Password</a>
                    </li>
    <li>
                        <a  href="?page=siswa&action=ganti_photo"><i class="fa fa-clipboard"></i> Upload Photo & Email</a>
                    </li>
     <?php
}
else{
  ?> 
  
   <?php
}
 ?>
                     
                  


                     

                     
                </ul>
               
            </div>
            
        </nav>  
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">

                       <?php 
       $page = @$_GET["page"];
      $action =@$_GET["action"];
      if ($page == "siswa") 
      {
        if($action =="tambah")
        {
          include "inc/siswa/tambah.php";
        }
        else if ($action =="ubah") 
        {
          include "inc/siswa/edit.php";
        }
        else if ($action =="hapus") 
        {
          include "inc/siswa/hapus.php";
        }
        else if ($action =="ganti_password") 
        {
          include "inc/siswa/ganti_password.php";
        }
        else if ($action =="ganti_photo") 
        {
          include "inc/siswa/ganti_photo.php";
        }
        else
        {
          include"inc/siswa/lihat.php";
        }
      }
     
    
      elseif ($page == "kelas") 
      {
        if($action =="tambah")
        {
          include "inc/kelas/tambah.php";
        }
        else if ($action =="ubah") 
        {
          include "inc/kelas/edit.php";
        }
        
        else if ($action =="hapus") 
        {
          include "inc/kelas/hapus.php";
        }
        else
        {
          include"inc/kelas/lihat.php";
        }
      }
      elseif ($page == "walikelas") 
      {
        if($action =="tambah")
        {
          include "inc/walikelas/tambah.php";
        }
        else if ($action =="ubah") 
        {
          include "inc/walikelas/edit.php";
        }
        
        else if ($action =="hapus") 
        {
          include "inc/walikelas/hapus.php";
        }
        else
        {
          include"inc/walikelas/lihat.php";
        }
      }
      elseif ($page == "pelanggaran_siswa") 
      {
        if($action =="lihat_point")
        {
          include "inc/pelanggaran_siswa/lihat_point.php";
        }
        else if ($action =="input_point") 
        {
          include "inc/pelanggaran_siswa/input_point.php";
        }
        
        else if ($action =="hapus") 
        {
          include "inc/pelanggaran_siswa/hapus.php";
        }
        else
        {
          include"inc/pelanggaran_siswa/lihat.php";
        }
      }
       elseif ($page == "prestasi_siswa") 
      {
        if($action =="lihat_point")
        {
          include "inc/prestasi_siswa/lihat_point.php";
        }
        else if ($action =="input_point") 
        {
          include "inc/prestasi_siswa/input_point.php";
        }
        
        else if ($action =="hapus") 
        {
          include "inc/prestasi_siswa/hapus.php";
        }
        else
        {
          include"inc/prestasi_siswa/lihat.php";
        }
      }
      elseif ($page == "pelanggaran") 
      {
        if($action =="tambah")
        {
          include "inc/pelanggaran/tambah.php";
        }
        else if ($action =="ubah") 
        {
          include "inc/pelanggaran/edit.php";
        }
        
        else if ($action =="hapus") 
        {
          include "inc/pelanggaran/hapus.php";
        }
        else
        {
          include"inc/pelanggaran/lihat.php";
        }
      }

      elseif ($page == "prestasi") 
      {
        if($action =="tambah")
        {
          include "inc/prestasi/tambah.php";
        }
        else if ($action =="ubah") 
        {
          include "inc/prestasi/edit.php";
        }
        
        else if ($action =="hapus") 
        {
          include "inc/prestasi/hapus.php";
        }
        else
        {
          include"inc/prestasi/lihat.php";
        }
      }
      
      elseif ($page == "kategori") 
      {
        if($action =="tambah")
        {
          include "inc/kategori/tambah.php";
        }
        else if ($action =="ubah") 
        {
          include "inc/kategori/edit.php";
        }
        
        else if ($action =="hapus") 
        {
          include "inc/kategori/hapus.php";
        }
        else
        {
          include"inc/kategori/lihat.php";
        }
      } 
      elseif ($page == "user") 
      {
        if($action =="tambah")
        {
          include "inc/user/tambah.php";
        }
        else if ($action =="ubah") 
        {
          include "inc/user/edit.php";
        }
        
        else if ($action =="hapus") 
        {
          include "inc/user/hapus.php";
        }
        else
        {
          include"inc/user/lihat.php";
        }
      } 
      elseif ($page == "kategori_pres") 
      {
        if($action =="tambah")
        {
          include "inc/kategori_pres/tambah.php";
        }
        else if ($action =="ubah") 
        {
          include "inc/kategori_pres/edit.php";
        }
        
        else if ($action =="hapus") 
        {
          include "inc/kategori_pres/hapus.php";
        }
        else
        {
          include"inc/kategori_pres/lihat.php";
        }
      }    

      else if($page =="pesan")
      {
        if($action =="hapus")
        {
          include"inc/kontak/hapus.php";
        }
        else if ($action =="detail") 
        {
          include "inc/kontak/detail.php";
        }
        else
        {
          include"inc/kontak/lihat.php";
        }
      }
      else if ($page == "")
      {
        include"admin.php";
      }
      else
      {
        echo "halaman tidak ditemukan guys";
      }
   ?>

                    </div>
                 </div>
                 <!-- /. ROW  -->
                 <hr />
               
    </div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="assets/js/jquery-1.10.2.js"></script>
      <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="assets/js/jquery.metisMenu.js"></script>
      <!-- CUSTOM SCRIPTS -->
    

    <script src="assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
    </script>
         <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>
    <script src="assets/js/adminlte.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="assets/js/dashboard.js"></script>
    
   
</body>
</html>
