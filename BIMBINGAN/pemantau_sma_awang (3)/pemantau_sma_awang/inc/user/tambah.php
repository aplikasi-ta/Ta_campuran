<div class="panel panel-default">
<div class="panel-heading">
		Tambah Data User
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                                     
                                        <div class="form-group">
                                            <label>Username</label>
                                            <input class="form-control" name="user" />
                                            
                                        </div>

                                         <div class="form-group">
                                            <label>Password</label>
                                            <input class="form-control" type="password" name="pass" />
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Ulangi Password</label>
                                            <input class="form-control" type="password" name="pass2" />
                                            
                                        </div>

                                       
                                        <div class="form-group">
                                            <label>Level</label>
                                            <select name="level" class="form-control">
                                                <option value="">-Pilih level-</option>
                                                <option value="admin">Admin</option>
                                                <option value="walikelas">Wali Kelas</option>
                                                <option value="siswa">Siswa</option>
                                            </select>
                                            
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
	$user = $_POST['user'];
    $pass = $_POST['pass'];
    $pass2 = $_POST['pass2'];
	$level = $_POST['level'];
  
   
    
	$simpan = $_POST['simpan'];


	if ($simpan) 
    {
		if($user==""  || $level=="" || $pass=="" || $pass2=="")
        {
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
                    </script>
                    <?php
        }
        else
        {
            if ($pass == $pass2) 
            {
                mysql_query("insert into user(id_user,username,password,level)value(null,'$user',md5('$pass'),'$level')");
            }
            else
            {
                ?>
                    <script type="text/javascript">
                    alert("password tidak sama");
                    </script>
                    <?php        
            }
            

            ?> 

                <script type="text/javascript">
                    
                    alert ("Data Berhasil Disimpan");
                    window.location.href="?page=user";
                </script>
            <?php
        }
	}
?>