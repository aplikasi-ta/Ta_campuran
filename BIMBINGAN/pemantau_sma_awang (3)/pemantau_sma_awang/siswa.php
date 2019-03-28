<b>Biodata Siswa</b>
<?php 
	$user = $_SESSION['username'];
	$sql = mysql_query("select * from siswa,kelas where siswa.kode_kelas=kelas.kode_kelas and siswa.NIS='$user'");
	$data = mysql_fetch_array($sql);
 ?>
<div class="row">
	<div class="col-md-8">
		<table class="table">
			<tr>
				<td>Nama Siswa</td>
				<td>:</td>
				<td><?php echo $data['nama_siswa'] ?></td>
			</tr>
			<tr>
				<td>Kelas</td>
				<td>:</td>
				<td><?php echo $data['nama_kelas'] ?></td>
			</tr>
			<tr>
				<td>Jenis Kelamin</td>
				<td>:</td>
				<td><?php echo $data['jenis_kelamin'] ?></td>
			</tr>
			<tr>
				<td>Alamat</td>
				<td>:</td>
				<td><?php echo $data['alamat'] ?></td>
			</tr>
			<tr>
				<td>Agama</td>
				<td>:</td>
				<td><?php echo $data['agama'] ?></td>
			</tr>
			<tr>
				<td>Nomor Handphone</td>
				<td>:</td>
				<td><?php echo $data['no_hp'] ?></td>
			</tr>
			<tr>
				<td>Email</td>
				<td>:</td>
				<td><?php echo $data['email'] ?></td>
			</tr>
		</table>
	</div>
	<div class="col-md-4">
		
		
		<div class="row">
       
          <!-- Widget: user widget style 1 -->
          <div class="box box-widget widget-user-2">
            <!-- Add the bg color to the header using any of the bg-* classes -->
            <div class="widget-user-header bg-yellow">
              <div class="widget-user-image">
                <?php 
		$photo = $data['photo'];
		if ($photo=='') {
			echo "<img class='img-circle' src='image/avatar.png' class='img-circle'>";
		}else{
			echo "<img class='img-circle' src='image/$data[photo]' class='img-circle'>";
		}
		 ?>
              </div>
              <!-- /.widget-user-image -->
              <h3 class="widget-user-username"><?php echo $data['nama_siswa'] ?></h3>
              <h5 class="widget-user-desc">Kelas <?php echo $data['nama_kelas'] ?></h5>
            </div>
            <div class="box-footer no-padding">
              <ul class="nav nav-stacked">
              	<?php 
              	$ri = mysql_query("select * from laporan_prestasi where NIS='$_SESSION[username]'");
              	$uy = mysql_num_rows($ri);
              	 ?>
                <li><a href="media.php?page=prestasi_siswa&action=lihat_point&id=<?php echo $_SESSION[username] ?>">Point Prestasi <span class="pull-right badge bg-aqua"><?php echo $uy ?></span></a></li>
                <?php 
              	$ro = mysql_query("select * from laporan_pelanggaran where NIS='$_SESSION[username]'");
              	$up = mysql_num_rows($ro);
              	 ?>
                <li><a href="media.php?page=pelanggaran_siswa&action=lihat_point&id=<?php echo $_SESSION[username] ?>">Point Pelanggaran <span class="pull-right badge bg-red"><?php echo $up ?></span></a></li>
               
              </ul>
            </div>
          </div>
          <!-- /.widget-user -->
        </div>

	</div>
</div>