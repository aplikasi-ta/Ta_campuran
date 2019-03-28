<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
                
                $nama = $_POST['nama_lengkap'];
                $username = $_POST['username'];
                $passw = $_POST['pass'];		
		$image = $_POST['image'];
		
		require_once('dbConnect.php');
		
		$sql ="SELECT id FROM tb_user ORDER BY id_user ASC";
		
		$res = mysqli_query($con,$sql);
		
		$id = 0;
		
		while($row = mysqli_fetch_array($res)){
				$id = $row['id'];
		}
		
		$path = "uploads/$id.png";
		
		$actualpath = "http://simplifiedcoding.16mb.com/PhotoUploadWithText/$path";
		
		$sql = "INSERT INTO tb_user (id_user, nama_lengkap, username, pass, photo) VALUES (NULL, '$nama', '$username', '$passw', '$actualpath')";
		
		if(mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($image));
			echo "Successfully Uploaded";
		}
		
		mysqli_close($con);
	}else{
		echo "Error";
	}
?>