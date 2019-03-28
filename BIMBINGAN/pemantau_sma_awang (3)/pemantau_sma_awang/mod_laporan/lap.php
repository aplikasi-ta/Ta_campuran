<script language="javascript">
javascript:print();
</script>


<?php include"../koneksi.php" ?>

<table border='0' align='LEFT'>
<tr>
<th>
<img src="../assets/img/logo.png"  align="left" width='100' height='100px' >
</th>
<th width="20">
</th>
<th width="900px" align="left">
<h3> <center> LAPORAN ASET PERUSAHAAN PT.SUCI KARYA BADINUSA<br> </center><center> Jl.Dr.Harun II no. 08 Kota Baru Bandar Lampung </center></h3>
<center><small>Tlp:(0721) 263186,266644,Ext,104,105 || Email:Subanuspusat@gmail.com || Fax:(0721) 262546</small></center>

</th>
</tr>
</table>
<hr style="height:8px;" />

<br>
<h3 style="text-align:center;"> Aset Perusahaan </h3>


<table cellspacing="0" cellpadding="5" border="1">
              
                     <tr>
                      <th>No</th>
                      <th>Nama Perusahaan</th>
                      <th>Nama Aset</th>
                      <th>Jenis Aset</th>
                      <th>Jumlah</th>
                      <th>Harga</th>
                      <th>Total</th>
                      <th>Keterangan</th>
                     </tr>
                     <?php 
                     $i=1;
                     $sql = mysql_query("select * from `aset`,`perusahaan` where aset.no_perusahaan=perusahaan.no_perusahaan and aset.status='ACC'");
                     while ($data = mysql_fetch_array($sql)) {
                       ?> 
                       <tr>
                        <td><?php echo $i++ ?></td>
                        <td><?php echo $data['nama_aset'] ?></td>
                        <td><?php echo $data['nama_perusahaan'] ?></td>
                        <td><?php echo $data['jenis_aset'] ?></td>
                        <td><?php echo $data['jumlah'] ?></td>
                        <td><?php echo $data['harga'] ?></td>
                        <td><?php echo $data['total'] ?></td>
                        <td><?php echo $data['status'] ?></td>
                       </tr>
                        <?php
                     }
                      ?>
                       
                   

                  
                    
</table>
                      
                                    <br> <br>
                      
                      <br>
        <br>
        <br> <br> <br> <br>
                      
                      <br>
        <br>
        <br> <br>
                      
                      <br>
                      
                      <br>
        <br>
        <table border='0' align='center' width="650">
<tr>
<th><br><br><br>
                      Keuangan
<br>
<br>
<br>
<br>
Yunita Tamrin S.E.
</th>
<th><p align="right">
                       Bandar Lampung, <?php 
                      echo $tanggal =date('Y-m-d');
                      ?></p><br>Manager
<br>
<br>
<br>
<br>
Dian Maysuri S.E
</th>
</tr>
</table>