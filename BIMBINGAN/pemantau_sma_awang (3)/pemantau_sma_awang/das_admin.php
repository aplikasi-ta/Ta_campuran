 <section class="content">
  <img src="image/banner.png" width="100%">
      <!-- Small boxes (Stat box) -->
      <div class="row">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-aqua">
            <div class="inner">
              <?php 
              $r = mysql_query("select * from wali_kelas");
              $y = mysql_num_rows($r);
               ?>
              <h3><?php echo $y ?></h3>

              <p>Wali Kelas</p>
            </div>
            <div class="icon">
              <i class="fa fa-user"></i>
            </div>
            <a href="?page=walikelas" class="small-box-footer">Lihat <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="row">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-red">
            <div class="inner">
              <?php 
              $h = mysql_query("select * from siswa");
              $i = mysql_num_rows($h);
               ?>
              <h3><?php echo $i ?></h3>

              <p>Siswa</p>
            </div>
            <div class="icon">
              <i class="fa fa-users"></i>
            </div>
            <a href="?page=siswa" class="small-box-footer">Lihat <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="row">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
              <?php 
              $w = mysql_query("select * from siswa");
              $a = mysql_num_rows($w);
               ?>
              <h3><?php echo $a ?></h3>

              <p>Kelas</p>
            </div>
            <div class="icon">
              <i class="fa fa-bell"></i>
            </div>
            <a href="?page=kelas" class="small-box-footer">Lihat <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="row">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-green">
            <div class="inner">
              <?php 
              $o = mysql_query("select * from user");
              $p = mysql_num_rows($o);
               ?>
              <h3><?php echo $p ?></h3>

              <p>User</p>
            </div>
            <div class="icon">
              <i class="fa fa-user"></i>
            </div>
            <a href="?page=user" class="small-box-footer">Lihat <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
</section>