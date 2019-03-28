<?php 
    $id = @$_GET['id'];
    $dt = mysql_query("select * from user where id_user='$id'");
    $data = mysql_fetch_array($dt);
 ?>
<div class="panel panel-default">
<div class="panel-heading">
		Update Data Siswa
	</div>
<div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    
                                    <form method="POST" action="">
                                        <div class="form-group">
                                            <label>Username</label>
                                            <input class="form-control" name="user" value="<?php echo $data['username'] ?>"/>
                                            
                                        </div>
                                         <small>**Kosongkan jika tidak ingin merubah password</small>
                                         <div class="form-group">
                                            <label>Password Baru</label>
                                            <input class="form-control" type="password" name="pass" />
                                            
                                        </div>
                                         <div class="form-group">
                                            <label>Ulangi Password Baru</label>
                                            <input class="form-control" type="password2" name="pass2" />
                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Level</label>
                                            <select name="level" class="form-control">
                                                <option value="<?php echo $data['level'] ?>">-<?php echo $data['level'] ?>-</option>
                                                <option value="admin">Admin</option>
                                                <option value="walikelas">Wali Kelas</option>
                                                <option value="siswa">Siswa</option>
                                            </select>
                                            
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
	$user = $_POST['user'];
    $pass = $_POST['pass'];
    $pass2 = $_POST['pass2'];
    $level = $_POST['level'];
	
	
	$ubah = $_POST['ubah'];


	if ($ubah) 
    {
		if($user==""  || $level=="")
        {
                    ?>
                    <script type="text/javascript">
                    alert("input tidak boleh ada yang kosong");
                    </script>
                    <?php
        }
        else
        {
             if ($password=="") 
               {
                 mysql_query("update user set
                                            username='$user', 
                                             level='$level' where id_user='$id'");
                                            ?> 
                                            <script type="text/javascript">
                                            
                                            alert ("Data Berhasil Disimpan");
                                            window.location.href="?page=user";
                                            </script>
                                               <?php
                }
            else{
                if ($password!=$password2) 
                {
                    ?>
                    <script type="text/javascript">
                    alert("Password baru anda tidak sama");
                    </script>
                    <?php
                }
                else{
                    mysql_query("update user set
                                            username='$user',password=md5('$pass'), 
                                             level='$level' where id_user='$id'");
                                            ?> 
                                            <script type="text/javascript">
                                            
                                            alert ("Data Berhasil Disimpan");
                                            window.location.href="?page=user";
                                            </script>
                                               <?php
                }
            }
        }
	}
?>