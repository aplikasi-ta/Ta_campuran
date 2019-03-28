<div class="panel panel-default">
<div class="panel-heading">
		Tambah Data Kelas
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                 
                                        <div class="form-group">
                                            <label>Kode Kelas</label>
                                            <input class="form-control" name="kode" />
                                            
                                        </div>
                                         <div class="form-group">
                                            <label>Nama Kelas</label>
                                            <input class="form-control" name="nama_kelas" />
                                            
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
    $nama_kelas = $_POST['nama_kelas'];
	
	$simpan = $_POST['simpan'];


	if ($simpan) {
		if($kode==""  || $nama_kelas=="")
        {
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
                    </script>
                    <?php
        }
        else{
             mysql_query("insert into kelas(kode_kelas,nama_kelas)values('$kode','$nama_kelas')");
            ?> 
                <script type="text/javascript">
                    
                    alert ("Data Berhasil Disimpan");
                    window.location.href="?page=kelas";
                </script>
            <?php
        }
	}
?>