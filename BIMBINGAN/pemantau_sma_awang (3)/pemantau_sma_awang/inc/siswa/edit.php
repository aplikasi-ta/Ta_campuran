<?php 
    $id = @$_GET['id'];
    $dt = mysql_query("select * from siswa where NIS='$id'");
    $data = mysql_fetch_array($dt);
 ?>
<div class="panel panel-default">
<div class="panel-heading">
		Update Data Siswa
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                                        <div class="form-group">
                                            <label>NIS</label>
                                            <input class="form-control" name="nik" value="<?php echo $data['NIS'] ?>"/>
                                            
                                        </div>

                                         <div class="form-group">
                                            <label>Nama Siswa</label>
                                            <input class="form-control" name="nama" value="<?php echo $data['nama_siswa'] ?>"/>
                                            
                                        </div>

                                         <small>**Kosongkan jika tidak ingin merubah password</small>
                                         <div class="form-group">
                                            <label>Password Baru</label>
                                            <input class="form-control" type="password" name="pass" />
                                            
                                        </div>
                                         <div class="form-group">
                                            <label>Ulangi Password Baru</label>
                                            <input class="form-control" type="password2" name="pass2" />
                                            
                                        </div>

                                        <div class="form-group">
                                            <label>No Handphone</label>
                                            <input class="form-control" name="hp" value="<?php echo $data['no_hp'] ?>"/>
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Agama</label>
                                            <select name="agama" class="form-control">
                                                <option value="<?php echo $data['agama'] ?>"><?php echo $data['agama'] ?></option>
                                                <option value="Islam">Islam</option>
                                                <option value="Kristen">Kristen</option>
                                                <option value="Hindu">Hindu</option>
                                                <option value="Budha">Budha</option>
                                            </select>
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Alamat</label>
                                            <textarea name="alamat" class="form-control"><?php echo $data['alamat'] ?></textarea>
                                            
                                        </div>


                                         <div class="form-group">
                                            <label>Jenis Kelamin</label><br/>
                                            <label class="checkbox-inline">
                                                <input type="checkbox" value="Laki-laki" name="jk" <?php if($data['jenis_kelamin'] =='Laki-laki'){echo 'checked';}?>/> Laki-laki
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox" value="Perempuan" name="jk" <?php if($data['jenis_kelamin'] =='Perempuan'){echo 'checked';}?>/> Wanita 
                                            </label>
                                         </div>
                                               
                                     

 										
                                        <div class="form-group">
											
											<input type="submit" name="ubah" value="Ubah" class="btn btn-primary">        
                                        </div>
                                  </div>

                                 </form>
                               </div>
</div>
</div>
</div>

<?php
	$id = @$_GET['id'];
	$nik = $_POST['nik'];
	$nama_lengkap = $_POST['nama'];
    $username = $_POST['user'];
    $password = $_POST['pass'];
    $password2 = $_POST['pass2'];
    $no_hp = $_POST['hp'];
    $alamat = $_POST['alamat'];
    $jk = $_POST['jk'];
    $level = $_POST['level'];
	
	
	$ubah = $_POST['ubah'];


	if ($ubah) 
    {
		if($nik==""  || $nama_lengkap=="" || $username=="" ||  $no_hp=="" || $alamat=="" || $jk=="" || $level=="")
        {
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
                    </script>
                    <?php
        }
        else
        {
             if ($password=="") 
               {
                 mysql_query("update anggota set
                                            nik='$nik',
                                             nama_lengkap='$nama_lengkap',
                                             username='$username',
                                             no_hp='$no_hp',
                                             alamat='$alamat',
                                             jk='$jk', 
                                             level='$level' where nik='$id'");
                                            ?> 
                                            <script type="text/javascript">
                                            
                                            alert ("Data Berhasil Disimpan");
                                            window.location.href="?page=anggota";
                                            </script>
                                               <?php
                }
            else{
                if ($password!=$password2) 
                {
                    ?>
                    <script type="text/javascript">
                    alert("Password baru anda tidak sama");
                    </script>
                    <?php
                }
                else{
                    mysql_query("update anggota set
                                            nik='$nik',
                                             nama_lengkap='$nama_lengkap',
                                             username='$username',
                                             password=md5('$password'),
                                             no_hp='$no_hp',
                                             alamat='$alamat',
                                             jk='$jk', 
                                             level='$level' where nik='$id'");
                                            ?> 
                                            <script type="text/javascript">
                                            
                                            alert ("Data Berhasil Disimpan");
                                            window.location.href="?page=anggota";
                                            </script>
                                               <?php
                }
            }
        }
	}
?>