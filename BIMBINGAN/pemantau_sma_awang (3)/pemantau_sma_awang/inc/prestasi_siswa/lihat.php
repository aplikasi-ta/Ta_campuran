
<div class="row">
				<div class="col-md-12">
					<!-- Advanced Tables -->
					<div class="panel panel-default">
						<div class="panel-heading">
							 Data Prestasi Siswa
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="dataTables-example">
									<thead>
										<tr>
											<th>No</th>
											<th>NIS</th>
											<th>Nama</th>
											<th>Kelas</th>
											<th>No HP</th>
											<th>Agama</th>
											
										
											<th>Aksi</th>
										</tr>
									</thead>
									<tbody>

										<?php 
$nip = $_SESSION['username'];
$u= mysql_query("select * from wali_kelas where NIP='$nip'");
$o = mysql_fetch_array($u);
$kode = $o['kode_kelas'];


	$level = $_SESSION['level'];
	if ($level=='walikelas') 
	{
		$sql = mysql_query("select * from siswa,kelas where siswa.kode_kelas=kelas.kode_kelas and siswa.kode_kelas='$kode'");
	}else{
		$sql = mysql_query("select * from siswa,kelas where siswa.kode_kelas=kelas.kode_kelas");
	}
											
											$i = 1;
											while ($data = mysql_fetch_array($sql)) {
											
										?>

										<tr>
											<td><?php echo $i++; ?></td>
											<td><?php echo $data ["NIS"];?></td>
											<td><?php echo $data ["nama_siswa"];?></td>
											<td><?php echo $data ["nama_kelas"];?></td>
											<td><?php echo $data ["no_hp"];?></td>
											<td><?php echo $data ["agama"];?></td>
											
											
											<td>
												<a href="?page=prestasi_siswa&action=lihat_point&id=<?php echo $data['NIS']; ?>" class="btn btn-success"><i class="fa fa-pens" aria-hidden="true"></i> Lihat Point</a>
												
												<a href="?page=prestasi_siswa&action=input_point&id=<?php echo $data['NIS']; ?>" class="btn btn-info"><i class="fa fa-pens" aria-hidden="true"></i> Input Point</a>
												

											</td>
										</tr>

										<?php    } ?>
									</tbody>

									</div>
							</div>
						</div>
					</div>
	</div>										
										   