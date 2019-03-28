<?php 
    $id = @$_GET['id'];
    $dt = mysql_query("select * from wali_kelas where NIP='$id'");
    $data = mysql_fetch_array($dt);
 ?>
<div class="panel panel-default">
<div class="panel-heading">
		Update Data Siswa
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label>NIP</label>
                                            <input class="form-control" readonly name="nik" value="<?php echo $data['NIP'] ?>"/>
                                            
                                        </div>

                                         <div class="form-group">
                                            <label>Nama Lengkap</label>
                                            <input class="form-control" readonly name="nama" value="<?php echo $data['nama_walikelas'] ?>"/>
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Nomor Handphone</label>
                                            <input class="form-control" name="no_hp" value="<?php echo $data['no_hp'] ?>"/>
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Email</label>
                                            <input class="form-control" name="email" value="<?php echo $data['email'] ?>"/>
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Foto Anda</label>
                                            <input class="form-control" name="gambar" type="file"/>
                                            <img src="image/<?php echo $data['poto_wali'] ?>">
                                            
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
	 $sumber = @$_FILES['gambar']['tmp_name'];
        $target ='image/';
        $nama_gambar = @$_FILES['gambar']['name'];
	
	
	$ubah = $_POST['ubah'];


	if ($ubah) 
    {
        if ($nama_gambar=='') {
            mysql_query("update wali_kelas set email='$_POST[email]',no_hp='$_POST[no_hp]' where NIP='$id'") or die(mysql_error());
                        ?>
                        <script type="text/javascript">
                        alert("data berhasil diubah");
                        window.location.href="media.php";
                        </script>
                        <?php
        }else{
             $pindah = move_uploaded_file($sumber, $target.$nama_gambar);
                    if ($pindah)
                    {
                        mysql_query("update wali_kelas set email='$_POST[email]',no_hp='$_POST[no_hp]',poto_wali='$nama_gambar' where NIP='$id'") or die(mysql_error());
                        ?>
                        <script type="text/javascript">
                        alert("data berhasil ditambahkan");
                        window.location.href="media.php";
                        </script>
                        <?php
                    }
                    else
                    {
                        ?><script type="text/javascript" >
                            alert("gambar gagal diupload");
                            window.location.href="media.php";
                        </script>
                        <?php 
                    }
        }
               
              
        
	}
?>