<a href="?page=walikelas&action=tambah" class="btn btn-primary" style="margin-bottom: 5px"><i class="fa fa-plus"></i> Tambah Data</a>

<div class="row">
				<div class="col-md-12">
					<!-- Advanced Tables -->
					<div class="panel panel-default">
						<div class="panel-heading">
							 Data Wali Kelas
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="dataTables-example">
									<thead>
										<tr>
											<th>No</th>
											<th>NIP</th>
											<th>Nama</th>
											<th>No HP</th>
											<th>Agama</th>
											<th>Alamat</th>

											<th>Aksi</th>
										</tr>
									</thead>
									<tbody>

										<?php

											$sql = mysql_query("select * from wali_kelas");
											$i = 1;
											while ($data = mysql_fetch_array($sql)) {
											
										?>

										<tr>
											<td><?php echo $i++; ?></td>
											<td><?php echo $data ["NIP"];?></td>
											<td><?php echo $data ["nama_walikelas"];?></td>
											<td><?php echo $data ["no_hp"];?></td>
											<td><?php echo $data ["agama"];?></td>
											<td><?php echo $data ["alamat"];?></td>
											
											<td>
												<a href="?page=siswa&action=ubah&id=<?php echo $data['NIP']; ?>" class="btn btn-info"><i class="fa fa-wrench" aria-hidden="true"></i> Ubah</a>
												<a onclick="return confirm('yakin ingin menghapus ?')" href="?page=siswa&action=hapus&id=<?php echo $data['NIP']; ?>"><button class="btn btn-danger"><i class="fa fa-trash-o" aria-hidden="true"></i> Hapus</button></a>
            

											</td>
										</tr>

										<?php    } ?>
									</tbody>

									</div>
							</div>
						</div>
					</div>
	</div>										
										   