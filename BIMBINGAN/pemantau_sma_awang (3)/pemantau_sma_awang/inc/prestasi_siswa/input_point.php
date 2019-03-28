<b>Point prestasi Siswa Terkait</b>

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
		<form name='form2' action="" method="post" id="form-combo">
			<div class="form-group">
                                            <label>Kategori prestasi</label>
                                            <select name="kategoripres" onchange='showPres()' class="form-control">
                                                <option value="">-Pilih Kategori-</option>
                                        <?php 
                                        $sql = mysql_query("select * from kategori_prestasi");
                                        while ($data= mysql_fetch_array($sql)) {
                                            ?> <option value="<?php echo $data['kode_kat'] ?>"><?php echo $data['nama_kat'] ?></option> <?php
                                        }
                                         ?>
                                            </select>
             </div>
             <div class="form-group">
                                            <label>prestasi</label>
                                            <select name="prestasi" id="pres" class="form-control">
                                            	<option value='0'>Pilih prestasi</option>
                                            </select>
             </div>
            
             <div class="form-group">
											
											<input type="submit" name="simpan" value="simpan" class="btn btn-primary">        
                                        </div>
             <?php 
             $nis = $_GET['id'];
             $tanggal = date('Y-m-d');
             $kategori = $_POST['kategoripres'];
             $prestasi = $_POST['prestasi'];
             $simpan = $_POST['simpan'];
             if ($simpan) 
             {
             	if ($kategori=='' || $prestasi=='') 
             	{
             		?> 
             		<script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
             		</script>
             		 <?php
             	}else
             	{
             		mysql_query("insert into laporan_prestasi(kode_laporan_pres,NIS,kode_prestasi,tanggal_laporan)
             			values(null,'$nis','$prestasi','$tanggal')");
             		?> 
             		<script type="text/javascript">
                    alert("data berhasil disimpan");
                    window.location.href="?page=prestasi_siswa&action=lihat_point&id=<?php echo $nis ?>";
             		</script>
             		 <?php
             	}
             }
             ?>
		</form>
	</div>
	
</div>