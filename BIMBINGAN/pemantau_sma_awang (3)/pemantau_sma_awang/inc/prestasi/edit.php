<?php 
    $id = @$_GET['id'];
    $dt = mysql_query("select * from point_prestasi,kategori_prestasi
     where point_prestasi.kode_kat=kategori_prestasi.kode_kat and
     point_prestasi.kode_prestasi='$id'");
    $data = mysql_fetch_array($dt);
 ?>
<div class="panel panel-default">
<div class="panel-heading">
		Update Data prestasi
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                                        <div class="form-group">
                                            <label>Kategori prestasi</label>
                                            <select name="kategori" class="form-control">
                                                <option value="<?php echo $data['kode_kat'] ?>">-<?php echo $data['nama_kat'] ?>-</option>
                                        <?php 
                                        $sq = mysql_query("select * from kategori_prestasi");
                                        while ($dat= mysql_fetch_array($sq)) {
                                            ?> <option value="<?php echo $dat['kode_kat'] ?>"><?php echo $dat['nama_kat'] ?></option> <?php
                                        }
                                         ?>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Nama prestasi</label>
                                            <input class="form-control" name="nama" value="<?php echo $data['nama_prestasi'] ?>"/>
                                            
                                        </div>

                                         <div class="form-group">
                                            <label>Point</label>
                                            <input class="form-control" name="point" value="<?php echo $data['point'] ?>"/>
                                            
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
	$kategori = $_POST['kategori']; 
    $nama = $_POST['nama'];
    $point = $_POST['point'];

	
	
	$ubah = $_POST['ubah'];


	if ($ubah) 
    {
		if($kategori==""  || $nama=="" || $point=="")
        {
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
                    </script>
                    <?php
        }
        else
        {
             
                 mysql_query("update point_prestasi set
                                            kode_kat='$kategori',
                                             nama_prestasi='$nama',
                                             point='$point'
                                              where kode_prestasi='$id'");
                                            ?> 
                                            <script type="text/javascript">
                                            
                                            alert ("Data Berhasil Disimpan");
                                            window.location.href="?page=prestasi";
                                            </script>
                                               <?php
               
            
        }
	}
?>