package com.example.ari.proball;

/**
 * Created by user on 1/15/2017.
 */
public class Koneksi {
    public static final String URL_GET_hotel = "http://probalam.com/probal_server/tampil_hotel.php";
    public static final String URL_GET_B_SHARE = "http://probalam.com/probal_server/b_share.php";


    public static final String URL_GET_HASIL_LIKE = "http://probalam.com/probal_server/b_like.php";

    public static final String URL_GET_beranda = "http://probalam.com/probal_server/beranda.php";
    public static final String URL_GET_likess = "http://probalam.com/probal_server/like.php";
    //pasar dan mall
    public static final String URL_GET_passmall = "http://probalam.com/probal_server/tampil_passmall.php";
    // Obyek Wisata
   public static final String URL_GET_wisata = "http://probalam.com/probal_server/tampil_wisata.php";
    // Pendidikan
    public static final String URL_GET_pendidikan = "http://probalam.com/probal_server/tampil_pendidikan.php";
    //Rumah Sakit
   public static final String URL_GET_rumahsakit = "http://probalam.com/probal_server/tampil_RS.php";
    //Rumah Makan
    public static final String URL_GET_rumahmakan = "http://probalam.com/probal_server/tampil_RMakan.php";

  //  public static final String URL_GET_OSM7 = "http://probalam.com/probal_server/getEmp.php?nama_lokasi=";
  public static final String URL_GET_ALL = "http://probalam.com/probal_server/tampil_hotel.php";
 public static final String URL_GET_OSM = "http://probalam.com/probal_server/get_data.php?nama_lokasi=";



    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id_lokasi";
    public static final String KEY_EMP_NAME = "nama_lokasi";
    public static final String KEY_EMP_LAT = "latitude";
    public static final String KEY_EMP_LONGI= "longitude";
    public static final String KEY_EMP_ALAMAT= "alamat";
    public static final String KEY_EMP_PARAM= "param";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id_lokasi";
    public static final String TAG_NAMA= "nama_lokasi";
    public static final String TAG_LAT= "latitude";
    public static final String TAG_LONGI = "longitude";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_PARAM = "param";

    //employee id to pass with intent
    public static final String OSM_ID = "osm_id";




    //========================================================= LOGIN APLIKASI ==============================================
    public static final String URL_GET_LOGIN = "http://probalam.com/probal_server/login.php";
    public static final String KEY_EMP_USER = "username";
    public static final String KEY_EMP_PASS = "pass";

    //JSON Tags
    public static final String TAG_JSON_ARRAY_BERANDA="result";
    public static final String TAG_ALAMATS= "alamat";
    public static final String TAG_LATS= "latitude";
    public static final String TAG_LONGS= "longitude";
    public static final String TAG_B_GAMBAR= "photo";

    public static final String KEY_EMP_ALAMATS = "alamat";
    public static final String KEY_EMP_LATS = "latitude";
    public static final String KEY_EMP_LONGS = "longitude";
    public static final String KEY_EMP_GAMBAR = "photo";

    public static final String TAG_JSON_ARRAY_LIKES="result";
    public static final String TAG_LIKE= "like";

    public static final String KEY_EMP_LIKES = "like";



    public static final String KEY_EMP_B_SHARE = "badges";

    //JSON Tags
    public static final String TAG_JSON_ARRAY3="result_b_share";
    public static final String TAG_B_SHARE= "badges";





    public static final String KEY_EMP_B_LIKE = "likes";

    //JSON Tags
    public static final String TAG_JSON_ARRAY_LIKE="result_b_like";
    public static final String TAG_B_LIKE= "likes";

}
