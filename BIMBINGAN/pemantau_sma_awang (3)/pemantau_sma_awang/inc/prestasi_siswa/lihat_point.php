

<div class="row">
	<div class="col-md-8">
		<b>Prestasi yang telah didapat</b>
		<hr>

		<table class="table table-striped table-bordered table-hover" id="dataTables-example">
			<thead>
			<tr>
				<th>No.</th>
				<th>Jenis Prestasi</th>
				<th>Nama prestasi</th>
				<th>Tanggal</th>
				<th>Point</th>
				<?php 
				if ($_SESSION['level']=='admin') {
					?> <th>Aksi</th> <?php
				}else{
					?> <?php
				}
				 ?>
				
			</tr>
		</thead>
		<tbody>
			<?php 
		$nis = $_GET['id'];
		$no=1;
		$r = mysql_query("SELECT * 
FROM point_prestasi, kategori_prestasi, laporan_prestasi
WHERE point_prestasi.kode_kat = kategori_prestasi.kode_kat
AND laporan_prestasi.kode_prestasi = point_prestasi.kode_prestasi
AND laporan_prestasi.NIS =  '$nis'");
		while($hu = mysql_fetch_array($r)){
			?> 
			<tr>
				<th><?php echo $no++ ?></th>
				<td><?php echo $hu['nama_kat'] ?></td>
				<td><?php echo $hu['nama_prestasi'] ?></td>
				<td><?php echo $hu['tanggal_laporan'] ?></td>
				<td><?php echo $hu['point'] ?></td>
				<?php 
				if ($_SESSION['level']=='admin') {
					?> <th><a onclick="return confirm('yakin ingin menghapus ?')" href="?page=prestasi_siswa&action=hapus&id=<?php echo $hu['kode_laporan_pres']; ?>"><button class="btn btn-danger"><i class="fa fa-trash-o" aria-hidden="true"></i> Hapus</button></a>
            </th> <?php
				}else{
					?> <?php
				}
				 ?>
				
			</tr>
			 <?php
		}
		 ?>
		</tbody>
		</table>
		
	</div>
	<div class="col-md-4">
	
		<br>
		<?php 
	$user = $_GET['id'];
	$sql = mysql_query("select * from siswa,kelas where siswa.kode_kelas=kelas.kode_kelas and siswa.NIS='$user'");
	$data = mysql_fetch_array($sql);
 ?>
		<?php 
		$photo = $data['photo'];
		if ($photo=='') {
			echo "<center><img src='image/find_user.png' width='100'></center>";
		}else{
			echo "<center><img src='image/$data[photo]' width='100'></center>";
		}
		 ?><hr>
		 <b>Biodata</b><br>
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
		</table>
	</div>
</div>