<a href="?page=kategori&action=tambah" class="btn btn-primary" style="margin-bottom: 5px"><i class="fa fa-plus"></i> Tambah Data</a>

<div class="row">
				<div class="col-md-12">
					<!-- Advanced Tables -->
					<div class="panel panel-default">
						<div class="panel-heading">
							 Data kategori Pelanggaran
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="dataTables-example">
									<thead>
										<tr>
											<th>No</th>
											<th>Kode kategori</th>
											<th>Nama kategori</th>
											<th>Aksi</th>
										</tr>
									</thead>
									<tbody>

										<?php

											$sql = mysql_query("select * from kategori_pelanggaran");
											$i = 1;
											while ($data = mysql_fetch_array($sql)) {
											
										?>

										<tr>
											<td><?php echo $i++; ?></td>
											<td><?php echo $data ["kode_kategori"];?></td>
											<td><?php echo $data ["nama_kategori"];?></td>
											
											<td>
												<a href="?page=kategori&action=ubah&id=<?php echo $data['kode_kategori']; ?>" class="btn btn-info"><i class="fa fa-wrench" aria-hidden="true"></i> Ubah</a>
												<a onclick="return confirm('yakin ingin menghapus ?')" href="?page=kategori&action=hapus&id=<?php echo $data['kode_kategori']; ?>"><button class="btn btn-danger"><i class="fa fa-trash-o" aria-hidden="true"></i> Hapus</button></a>
            

											</td>
										</tr>

										<?php    } ?>
									</tbody>

									</div>
							</div>
						</div>
					</div>
	</div>										
										   