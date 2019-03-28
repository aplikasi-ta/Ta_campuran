package com.example.ari.crowdgamifikasi;

/**
 * Created by Ari on 1/15/2017.
 */

public class Koneksi {
    public static final String URL_GET_ALL = "http://probalam.com/probal_server/profil.php";
    public static final String URL_GET_BERANDA = "http://probalam.com/probal_server/beranda.php";
    public static final String URL_GET_B_SHARE = "http://probalam.com/probal_server/b_share.php";
    public static final String URL_GET_LOGIN = "http://probalam.com/probal_server/login.php";



    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id_user";
    public static final String KEY_EMP_NAME = "nama_pengguna";
    public static final String KEY_EMP_LAT = "badg";
    public static final String KEY_EMP_LONGI= "like";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id_user";
    public static final String TAG_NAMA= "nama_pengguna";
    public static final String TAG_LAT= "badg";
    public static final String TAG_LONGI = "like";

    //employee id to pass with intent
    public static final String OSM_ID = "osm_id";

    //================================================ BERANDA =================================================
    /*"nama_lokasi"=>$row['nama_lokasi'],
            "latitude"=>$row['latitude'],
            "longitude"=>$row['longitude'],
            "alamat"=>$row['alamat'],
            "data_parameter"=>$row['data_parameter'],
            "photo"=>$row['photo']*/
    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_IDB = "Id_lokasi";
    public static final String KEY_EMP_NAMA = "nama_lokasi";
    public static final String KEY_EMP_LATB = "latitude";
    public static final String    KEY_EMP_LONGIB= "longitude";
    public static final String KEY_EMP_ALAMAT= "alamat";
    public static final String KEY_EMP_PARAMB ="data_parameter";
    public static final String KEY_EMP_PHOTO ="photo";

    //JSON Tags
    public static final String TAG_JSON_ARRAYB="result";
    public static final String TAG_IDB = "Id_lokasi";
    public static final String TAG_NAMAB= "nama_pengguna";
    public static final String TAG_LATB= "badg";
    public static final String TAG_LONGIB = "like";

    //employee id to pass with intent
    public static final String ID_BERANDA = "osm_id";

    //======================================================== BADGES SHARE LOKASI ==========================================
    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_B_SHARE = "badges";

    //JSON Tags
    public static final String TAG_JSON_ARRAY3="result_b_share";
    public static final String TAG_B_SHARE= "badges";

    //========================================================= LOGIN APLIKASI ==============================================
    public static final String KEY_EMP_USER = "username";
    public static final String KEY_EMP_PASS = "pass";

    //JSON Tags
    public static final String TAG_JSON_ARRAY_LOGIN="result_login";
    public static final String TAG_USERNAME= "username";
    public static final String TAG_B_PASS= "pass";

}
