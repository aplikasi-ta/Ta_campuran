package com.example.ari.proba;

/**
 * Created by Ari on 12/2/2016.
 */

public class config {
    //Alamat URL tempat kita meletakkan script PHP di PC Server
    // Link untuk INSERT Data
    public static final String URL_ADD="http://192.168.43.100/prola_server/create.php";

    // Filed yang digunakan untuk dikirimkan ke Database, sesuaikan saja dengan Field di Tabel Mahasiswa
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_LOKASI = "nama_lokasi";
    public static final String KEY_EMP_LAT = "lat";
    public static final String KEY_EMP_LONG = "long";

    // Tags Format JSON
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_LOKASI = "nama_lokasi";
    public static final String TAG_LAT = "lat";
    public static final String TAG_LONG = "long";
}
