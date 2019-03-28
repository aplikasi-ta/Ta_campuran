<div class="panel panel-default">
<div class="panel-heading">
		Tambah Data kategori Prestasi
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                 
                                        <div class="form-group">
                                            <label>Kode kategori</label>
                                            <input class="form-control" name="kode" />
                                            
                                        </div>
                                         <div class="form-group">
                                            <label>Nama kategori</label>
                                            <input class="form-control" name="nama_kategori" />
                                            
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
    $nama_kategori = $_POST['nama_kategori'];
	
	$simpan = $_POST['simpan'];


	if ($simpan) {
		if($kode==""  || $nama_kategori=="")
        {
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
                    </script>
                    <?php
        }
        else{
             mysql_query("insert into kategori_prestasi(kode_kat,nama_kat)values('$kode','$nama_kategori')");
            ?> 
                <script type="text/javascript">
                    
                    alert ("Data Berhasil Disimpan");
                    window.location.href="?page=kategori_pres";
                </script>
            <?php
        }
	}
?>