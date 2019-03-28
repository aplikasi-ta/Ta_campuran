
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="author" content="ilmu-detil.blogspot.com">
	<title>Bootstrap Graph Using Highcharts </title>
	<!-- Bagian css -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/ilmudetil.css">
	<script src="assets/js/jquery-1.10.1.min.js"></script>
	<script src="assets/js/highcharts.js"></script>
	<script>
		var chart1; 
		$(document).ready(function() {
			  chart1 = new Highcharts.Chart({
				 chart: {
					renderTo: 'mygraph',
					type: 'column'
				 },   
				 title: {
					text: 'Internet Browser Statistics '
				 },
				 xAxis: {
					categories: ['Browser']
				 },
				 yAxis: {
					title: {
					   text: 'Total Browser Usage'
					}
				 },
					  series:             
					[
						<?php 
						include "connection.php";
						$sql   = "SELECT browsername  FROM browser";
						$query = mysqli_query( $con, $sql )  or die(mysqli_error());
						while( $temp = mysqli_fetch_array( $query ) )
						{
							$trendbrowser=$temp['browsername'];                     
							$sql_total   = "SELECT total FROM browser WHERE browsername='$trendbrowser'";        
							$query_total = mysqli_query($con,$sql_total ) or die(mysql_error());
							while( $data = mysqli_fetch_array( $query_total ) )
							{
								$total = $data['total'];                 
							}             
						?>
							{
							  name: '<?php echo $trendbrowser; ?>',
							  data: [<?php echo $total; ?>]
							},
							<?php 
						} 	?>
						]
			  });
		   });	
	</script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">
			Pusat Ilmu Secara Detil</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-left">
				<li class="clr1 active"><a href="index.html">Home</a></li>
				<li class="clr2"><a href="">Programming</a></li>
				<li class="clr3"><a href="">English</a></li>
			</ul>
		</div>
	</div>
</nav>
</br></br></br></br>
<!--- Bagian Judul-->	
<div class="container" style="margin-top:20px">
	<div class="col-md-7">
		<div class="panel panel-primary">
			<div class="panel-heading">The Graph of Browser Trends January 2015</div>
				<div class="panel-body">
					<div id ="mygraph"></div>
				</div>
		</div>
	</div>
</div>
<div class="navbar navbar-default navbar-fixed-bottom footer-bottom">
   <div class="container text-center">
      <p class="text-center">Copyright &copy; 2016,  DTC. Developed by <a href="https://ilmu-detil.blogspot.com/">Pusat Ilmu</a></p>
   </div>
</div>
</body>
</html>
