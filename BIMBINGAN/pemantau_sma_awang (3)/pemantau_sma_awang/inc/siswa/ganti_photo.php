<div class="panel panel-default">
<div class="panel-heading">
		Ganti Photo Anda
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <?php 
                                    $sql = mysql_query("select * from siswa where NIS ='$_SESSION[username]'");
                                    $data = mysql_fetch_array($sql);
                                     ?>
                                    <form method="POST" action="" enctype="multipart/form-data">
                                        
                                        <div class="form-group">
                                            <label>Emaill</label>
                                            <input type="text" name="email" class="form-control" value="<?php echo $data['email'] ?>"> 
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Photo Anda</label>
                                            <input type="file" name="gambar"> 
                                            
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

        $user = $_SESSION['username'];
        $h = mysql_query("select * from siswa where NIS='$user'");
        $o = mysql_fetch_array($h);
        $nis = $o['NIS'];

        $sumber = @$_FILES['gambar']['tmp_name'];
        $target ='image/';
        $nama_gambar = @$_FILES['gambar']['name'];

        $tambah_galeri = @$_POST['simpan'];
        if($tambah_galeri)
        {
            if ($nama_gambar=='') {
                mysql_query("update siswa set email='$_POST[email]' where NIS='$nis'") or die(mysql_error());
                        ?>
                        <script type="text/javascript">
                        alert("data berhasil ditambahkan");
                        window.location.href="media.php";
                        </script>
                        <?php
            }else{
                $pindah = move_uploaded_file($sumber, $target.$nama_gambar);
                    if ($pindah)
                    {
                        mysql_query("update siswa set photo='$nama_gambar' where NIS='$nis'") or die(mysql_error());
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
                            window.location.href="?page=siswa&action=ganti_photo";
                        </script>
                        <?php 
                    }
            }
               
                    
                
        }
     ?>