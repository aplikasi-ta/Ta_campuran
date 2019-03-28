<?php
include"inc/mod_laporan/fungsi_combobox.php ";
include"inc/mod_laporan/fungsi_indotgl.php ";
include"inc/mod_laporan/library.php";
   echo "<h2>Laporan</h2>
          <input type=button value='Laporan Hari Ini' 
          onclick=\"window.location.href='inc/mod_laporan/pdf_toko_sekarang.php';\">

          <form method=POST action='inc/mod_laporan/pdf_toko.php'>
          <table>
          <tr><td colspan=2><b>Laporan Per Periode</b></td></tr>
          <tr><td>Dari Tanggal</td><td> : ";        
          combotgl(1,31,'tgl_mulai',$tgl_skrg);
          combonamabln(1,12,'bln_mulai',$bln_sekarang);
          combothn(2000,$thn_sekarang,'thn_mulai',$thn_sekarang);

    echo "</td></tr>
          <tr><td>s/d Tanggal</td><td> : ";
          combotgl(1,31,'tgl_selesai',$tgl_skrg);
          combonamabln(1,12,'bln_selesai',$bln_sekarang);
          combothn(2000,$thn_sekarang,'thn_selesai',$thn_sekarang);

    echo "</td></tr>
          <tr><td colspan=2><input type=submit value=Proses></td></tr>
          </table>
          </form>";

?>
