<div class="panel panel-default">
<div class="panel-heading">
		Tambah Data prestasi
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">

                                        <div class="form-group">
                                            <label>Kategori prestasi</label>
                                            <select name="kategori" class="form-control">
                                                <option value="">-Pilih Kategori-</option>
                                        <?php 
                                        $sql = mysql_query("select * from kategori_prestasi");
                                        while ($data= mysql_fetch_array($sql)) {
                                            ?> <option value="<?php echo $data['kode_kat'] ?>"><?php echo $data['nama_kat'] ?></option> <?php
                                        }
                                         ?>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Nama prestasi</label>
                                            <input class="form-control" name="nama"/>
                                            
                                        </div>

                                         <div class="form-group">
                                            <label>Point</label>
                                            <input class="form-control" name="point" />
                                            
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
$kategori = $_POST['kategori']; 
	$nama = $_POST['nama'];
	$point = $_POST['point'];
   
    
	$simpan = $_POST['simpan'];


	if ($simpan) 
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
           
             mysql_query("INSERT INTO  `pemantau_siswa`.`point_prestasi` (
`kode_prestasi` ,
`kode_kat` ,
`nama_prestasi` ,
`point` 
)
VALUES (
NULL ,  '$kategori',  '$nama',  '$point'
);");
            ?> 
                <script type="text/javascript">
                    
                    alert ("Data Berhasil Disimpan");
                    window.location.href="?page=prestasi";
                </script>
            <?php
        }
	}
?>