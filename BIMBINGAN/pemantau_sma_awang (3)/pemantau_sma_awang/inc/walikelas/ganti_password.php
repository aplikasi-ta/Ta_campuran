<div class="panel panel-default">
<div class="panel-heading">
		Ganti Password Anda
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                                       
                                        <div class="form-group">
                                            <label>Masukan Password</label>
                                            <input class="form-control" type="password" name="pass" placeholder="Masukan password baru..."/>
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Ulangi Password</label>
                                            <input class="form-control" type="password" name="pass2" placeholder="Ulangi password baru..."/>
                                            
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
	$pass = $_POST['pass'];
	$pass2 = $_POST['pass2'];
	$simpan = $_POST['simpan'];

    $user = $_SESSION['username'];
    $h = mysql_query("select * from user where username='$_SESSION[username]'");
    $o = mysql_fetch_array($h);
    $id_user = $o['id_user'];
	if ($simpan) 
    {
		if($pass=="" || $pass2==""){
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh kosong");
                    </script>
                    <?php
        }
        else if ($pass==$pass2) {
             mysql_query("update user set password=md5('$pass') where id_user='$id_user'");

            ?> 

                <script type="text/javascript">
                    
                    alert ("Data Berhasil Disimpan");
                    window.location.href="media.php";
                </script>
            <?php
        }
        else
        {
          ?> 

                <script type="text/javascript">
                    
                    alert ("password tidak sama");
                    window.location.href="?page=siswa&action=ganti_password";
                </script>
            <?php 
        }
	}
?>