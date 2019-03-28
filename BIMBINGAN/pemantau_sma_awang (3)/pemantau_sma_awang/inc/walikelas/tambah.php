<div class="panel panel-default">
<div class="panel-heading">
		Tambah Data siswa
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                                        <div class="form-group">
                                            <label>Kelas</label>
                                            <select name="kelas" class="form-control">
                                                <option value="">-Pilih Kelas-</option>
                                        <?php 
                                        $sql = mysql_query("select * from kelas");
                                        while ($data= mysql_fetch_array($sql)) {
                                            ?> <option value="<?php echo $data['kode_kelas'] ?>"><?php echo $data['nama_kelas'] ?></option> <?php
                                        }
                                         ?>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>NIP</label>
                                            <input class="form-control" name="nip" />
                                            
                                        </div>

                                         <div class="form-group">
                                            <label>Nama Lengkap Wali Kelas</label>
                                            <input class="form-control" name="nama" />
                                            
                                        </div>

                                       
                                        <div class="form-group">
                                            <label>Agama</label>
                                            <select name="agama" class="form-control">
                                                <option value="">-Pilih agama-</option>
                                                <option value="Islam">Islam</option>
                                                <option value="Kristen">Kristen</option>
                                                <option value="Budha">Budha</option>
                                                <option value="Hindu">Hindu</option>
                                            </select>
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>No Handphone</label>
                                            <input class="form-control" name="hp" />
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Alamat</label>
                                            <textarea name="alamat" class="form-control"></textarea>
                                            
                                        </div>


                                         <div class="form-group">
                                            <label>Jenis Kelamin</label><br/>
                                            <label class="checkbox-inline">
                                                <input type="checkbox" value="Laki-laki" name="jk" /> Laki-laki
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox" value="Perempuan" name="jk" /> Wanita 
                                            </label>
                                         </div>
                                             
                                         

 										
                                        <div class="form-group">
											
											<input type="submit" name="simpan" value="simpan" class="btn btn-primary">        
                                        </div>
                                  </div>

                                 </form>
                               </div>
</div>
</div>
</div>

<?php
	$kode = $_POST['kode'];
	$nip = $_POST['nip'];
    $pass = md5($_POST['nis']);
	$nama_lengkap = $_POST['nama'];
    $agama = $_POST['agama'];
    $no_hp = $_POST['hp'];
    $alamat = $_POST['alamat'];
    $jk = $_POST['jk'];
    $kelas = $_POST['kelas'];
   
    
	$simpan = $_POST['simpan'];


	if ($simpan) 
    {
		if($nip==""  || $nama_lengkap=="" || $no_hp=="" || $alamat=="" || $jk=="" || $level=="")
        {
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
                    </script>
                    <?php
        }
        else
        {
             mysql_query("insert into user(id_user,username,password,level)value(null,'$nip','$pass','walikelas')");
             mysql_query("insert into wali_kelas
                                            (NIP,
                                             kode_kelas,
                                             nama_walikelas,
                                             jenis_kelamin,
                                             agama,
                                             alamat,
                                             no_hp)values
                                            ('$nip',
                                             '$kelas',
                                             '$nama_lengkap',
                                             '$jk',
                                             '$agama',
                                             '$alamat',
                                             '$no_hp')");

            ?> 

                <script type="text/javascript">
                    
                    alert ("Data Berhasil Disimpan");
                    window.location.href="?page=walikelas";
                </script>
            <?php
        }
	}
?>