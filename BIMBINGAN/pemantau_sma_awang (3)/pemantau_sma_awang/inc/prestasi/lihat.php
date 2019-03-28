<a href="?page=prestasi&action=tambah" class="btn btn-primary" style="margin-bottom: 5px"><i class="fa fa-plus"></i> Tambah Data</a>

<div class="row">
				<div class="col-md-12">
					<!-- Advanced Tables -->
					<div class="panel panel-default">
						<div class="panel-heading">
							 Data Point Prestasi Siswa
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="dataTables-example">
									<thead>
										<tr>
											<th>No</th>
											<th>Jenis prestasi</th>
											<th>Nama prestasi</th>
											<th>Point</th>
										
											<th>Aksi</th>
										</tr>
									</thead>
									<tbody>

										<?php

											$sql = mysql_query("select * from point_prestasi,kategori_prestasi where point_prestasi.kode_kat=kategori_prestasi.kode_kat");
											$i = 1;
											while ($data = mysql_fetch_array($sql)) {
											
										?>

										<tr>
											<td><?php echo $i++; ?></td>
											<td><?php echo $data ["nama_kat"];?></td>
											<td><?php echo $data ["nama_prestasi"];?></td>
											<td><?php echo $data ["point"];?></td>
										
											<td>
												<a href="?page=prestasi&action=ubah&id=<?php echo $data['kode_prestasi']; ?>" class="btn btn-info"><i class="fa fa-wrench" aria-hidden="true"></i> Ubah</a>
												<a onclick="return confirm('yakin ingin menghapus ?')" href="?page=prestasi&action=hapus&id=<?php echo $data['kode_prestasi']; ?>"><button class="btn btn-danger"><i class="fa fa-trash-o" aria-hidden="true"></i> Hapus</button></a>
            

											</td>
										</tr>

										<?php    } ?>
									</tbody>

									</div>
							</div>
						</div>
					</div>
	</div>										
										   