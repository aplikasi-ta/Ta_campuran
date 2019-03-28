<?php 
    $id = @$_GET['id'];
    $dt = mysql_query("select * from point_pelanggaran,kategori_pelanggaran
     where point_pelanggaran.kode_kategori=kategori_pelanggaran.kode_kategori and
     point_pelanggaran.kode_pelanggaran='$id'");
    $data = mysql_fetch_array($dt);
 ?>
<div class="panel panel-default">
<div class="panel-heading">
		Update Data pelanggaran
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                                        <div class="form-group">
                                            <label>Kategori Pelanggaran</label>
                                            <select name="kategori" class="form-control">
                                                <option value="<?php echo $data['kode_kategori'] ?>">-<?php echo $data['nama_kategori'] ?>-</option>
                                        <?php 
                                        $sq = mysql_query("select * from kategori_pelanggaran");
                                        while ($dat= mysql_fetch_array($sq)) {
                                            ?> <option value="<?php echo $dat['kode_kategori'] ?>"><?php echo $dat['nama_kategori'] ?></option> <?php
                                        }
                                         ?>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Nama Pelanggaran</label>
                                            <input class="form-control" name="nama" value="<?php echo $data['nama_pelanggaran'] ?>"/>
                                            
                                        </div>

                                         <div class="form-group">
                                            <label>Point</label>
                                            <input class="form-control" name="point" value="<?php echo $data['point'] ?>"/>
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Sanksi</label>
                                            <input class="form-control" name="sanksi" value="<?php echo $data['sanksi'] ?>" />
                                            
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
    $sanksi = $_POST['sanksi'];
	
	
	$ubah = $_POST['ubah'];


	if ($ubah) 
    {
		if($kategori==""  || $nama=="" || $point=="" ||  $sanksi=="")
        {
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
                    </script>
                    <?php
        }
        else
        {
             
                 mysql_query("update point_pelanggaran set
                                            kode_kategori='$kategori',
                                             nama_pelanggaran='$nama',
                                             point='$point',
                                             sanksi='$sanksi' where kode_pelanggaran='$id'");
                                            ?> 
                                            <script type="text/javascript">
                                            
                                            alert ("Data Berhasil Disimpan");
                                            window.location.href="?page=pelanggaran";
                                            </script>
                                               <?php
               
            
        }
	}
?>