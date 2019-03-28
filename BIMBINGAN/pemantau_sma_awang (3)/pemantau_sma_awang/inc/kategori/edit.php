<?php 
    $id = @$_GET['id'];
    $dt = mysql_query("select * from kategori_pelanggaran where kode_kategori='$id'");
    $data = mysql_fetch_array($dt);
 ?>
<div class="panel panel-default">
<div class="panel-heading">
		Update Data kategori pelanggaran
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                                          <div class="form-group">
                                            <label>Kode kategori</label>
                                            <input class="form-control" name="kode_kategori" value="<?php echo $data['kode_kategori'] ?>" />
                                            
                                        </div>
                                          <div class="form-group">
                                            <label>Nama kategori</label>
                                            <input class="form-control" name="nama_kategori" value="<?php echo $data['nama_kategori'] ?>" />
                                            
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
    $kode = $_POST['kode_kategori'];
	$nama_kategori = $_POST['nama_kategori'];

	
	
	$ubah = $_POST['ubah'];


	if ($ubah) 
    {
		if($kode==""  || $nama_kategori=="")
        {
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
                    </script>
                    <?php
        }
        else
        {
            
                 mysql_query("update kategori_pelanggaran set
                                             kode_kategori='$kode',
                                             nama_kategori='$nama_kategori'
                                             where kode_kategori='$id'");
                                            ?> 
                                            <script type="text/javascript">
                                            
                                            alert ("Data Berhasil Diubah");
                                            window.location.href="?page=kategori";
                                            </script>
                                               <?php
             
        }
	}
?>