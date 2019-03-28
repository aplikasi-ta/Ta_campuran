<b>Point Pelanggaran Siswa Terkait</b>

<div class="row">
	<div class="col-md-5">
	
		<br>
		<?php 
	$user = $_GET['id'];
	$sql = mysql_query("select * from siswa,kelas where siswa.kode_kelas=kelas.kode_kelas and siswa.NIS='$user'");
	$data = mysql_fetch_array($sql);
 ?>
		<?php 
		$photo = $data['photo'];
		if ($photo=='') {
			echo "<img src='image/find_user.png' width='150'>";
		}else{
			echo "<img src='image/$data[photo]' width='150'>";
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
	<div class="col-md-7">
		<b>Input Point</b>
		<hr>
		<form name='form1' action="" method="post" id="form-combo">
			<div class="form-group">
                                            <label>Kategori Pelanggaran</label>
                                            <select name="kategori" onchange='showKab()' class="form-control">
                                                <option value="">-Pilih Kategori-</option>
                                        <?php 
                                        $sql = mysql_query("select * from kategori_pelanggaran");
                                        while ($data= mysql_fetch_array($sql)) {
                                            ?> <option value="<?php echo $data['kode_kategori'] ?>"><?php echo $data['nama_kategori'] ?></option> <?php
                                        }
                                         ?>
                                            </select>
             </div>
             <div class="form-group">
                                            <label>Pelanggaran</label>
                                            <select name="pelanggaran" id="pelang" class="form-control">
                                            	<option value='0'>Pilih pelanggaran</option>
                                            </select>
             </div>
             <div class="form-group">
                                            <label>Sanksi</label>
                                            <select name="sanksi" class="form-control">
                                            	<option value='0'>Pilih</option>
                                            	<option value="Sudah diberi sanksi">Sudah diberi sanksi</option>
                                            	<option value="Belum diberi sanksi">Belum diberi sanksi</option>
                                            </select>
             </div>
             <div class="form-group">
											
											<input type="submit" name="simpan" value="simpan" class="btn btn-primary">        
                                        </div>
             <?php 
             $nis = $_GET['id'];
             $tanggal = date('Y-m-d');
             $sanksi = $_POST['sanksi'];
             $kategori = $_POST['kategori'];
             $pelanggaran = $_POST['pelanggaran'];
             $simpan = $_POST['simpan'];
             if ($simpan) 
             {
             	if ($kategori=='' || $pelanggaran=='' || $sanksi=='') 
             	{
             		?> 
             		<script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
             		</script>
             		 <?php
             	}else
             	{
             		mysql_query("insert into laporan_pelanggaran(kode_laporan_pel,NIS,kode_pelanggaran,tanggal_pelanggaran,keterangan)
             			values(null,'$nis','$pelanggaran','$tanggal','$sanksi')");
             		?> 
             		<script type="text/javascript">
                    alert("data berhasil disimpan");
                    window.location.href="?page=pelanggaran_siswa&action=lihat_point&id=<?php echo $nis ?>";
             		</script>
             		 <?php
             	}
             }
             ?>
		</form>
	</div>
	
</div>