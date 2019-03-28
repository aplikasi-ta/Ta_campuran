<?php 
    $id = @$_GET['id'];
    $dt = mysql_query("select * from kelas where kode_kelas='$id'");
    $data = mysql_fetch_array($dt);
 ?>
<div class="panel panel-default">
<div class="panel-heading">
		Update Data kelas
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                                          <div class="form-group">
                                            <label>Kode kelas</label>
                                            <input class="form-control" name="kode_kelas" value="<?php echo $data['kode_kelas'] ?>" />
                                            
                                        </div>
                                          <div class="form-group">
                                            <label>Nama kelas</label>
                                            <input class="form-control" name="nama_kelas" value="<?php echo $data['nama_kelas'] ?>" />
                                            
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
    $kode = $_POST['kode_kelas'];
	$nama_kelas = $_POST['nama_kelas'];

	
	
	$ubah = $_POST['ubah'];


	if ($ubah) 
    {
		if($kode==""  || $nama_kelas=="")
        {
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
                    </script>
                    <?php
        }
        else
        {
            
                 mysql_query("update kelas set
                                             kode_kelas='$kode',
                                             nama_kelas='$nama_kelas'
                                             where kode_kelas='$id'");
                                            ?> 
                                            <script type="text/javascript">
                                            
                                            alert ("Data Berhasil Diubah");
                                            window.location.href="?page=kelas";
                                            </script>
                                               <?php
             
        }
	}
?>