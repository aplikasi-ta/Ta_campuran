<b>Biodata Walikelas</b>
<?php 
	$user = $_SESSION['username'];
	$sql = mysql_query("select * from wali_kelas,kelas where wali_kelas.kode_kelas=kelas.kode_kelas and wali_kelas.NIP='$user'");
	$data = mysql_fetch_array($sql);
 ?>
<div class="row">
	<div class="col-md-8">
		<table class="table">
			<tr>
				<td>Nama Wali kelas</td>
				<td>:</td>
				<td><?php echo $data['nama_walikelas'] ?></td>
			</tr>
			<tr>
				<td>Wali dari Kelas</td>
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
		$photo = $data['poto_wali'];
		if ($photo=='') {
			echo "<img class='img-circle' src='image/find_user.png' class='img-circle'>";
		}else{
			echo "<img class='img-circle' src='image/$data[poto_wali]' class='img-circle'>";
		}
		 ?>
              </div>
              <!-- /.widget-user-image -->
              <h3 class="widget-user-username"><?php echo $data['nama_walikelas'] ?></h3>
              <h5 class="widget-user-desc">Wali Kelas <?php echo $data['nama_kelas'] ?></h5>
            </div>
            
          </div>
          <!-- /.widget-user -->
        </div>

	</div>
</div>