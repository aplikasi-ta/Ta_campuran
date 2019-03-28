package com.example.ari.saidata;

/**
 * Created by user on 1/26/2017.
 */

public class KonekDB {
    public static final String URL_SIMPAN_TANGIBLEP = "http://pstiubl.com/saida/tangiblep.php";
    public static final String URL_LIHAT = "http://pstiubl.com/saida/tampilTangibleP.php";
    public  static final String URL_CREATE_STAFF = "http://pstiubl.com/saida/create_staff.php";
    public static final String URL_CREATE_MAHASISWA = "http://pstiubl.com/saida/create_mahasiswa.php";
    public static final String URL_GET_NPM = "http://pstiubl.com/saida/session_log_npm.php?Id_mhs=";

    //=============================================================== NILAI PERSEPSI ==========================================================
    public static final String URL_GET_NILAI_TANG_P = "http://pstiubl.com/saida/getNilaiTangibleP.php?Program_Studi=&Tahun_Ajaran=";
    public static final String URL_GET_NILAI_REAL_P = "http://pstiubl.com/saida/getNilaiReliabilityP.php?Program_Studi=&Tahun_Ajaran=";
    public static final String URL_GET_NILAI_RESPON_P = "http://pstiubl.com/saida/getNilaiResponsivenessP.php?Program_Studi=&Tahun_Ajaran=";
    public static final String URL_GET_NILAI_ASSURANCE_P = "http://pstiubl.com/saida/getNilaiAssuranceP.php?Program_Studi=&Tahun_Ajaran=";
    public static final String URL_GET_NILAI_EMPATHY_P = "http://pstiubl.com/saida/getNilaiEmpathyP.php?Program_Studi=&Tahun_Ajaran=";


    //=============================================================== NILAI HARAPAN ============================================================
    public static final String URL_GET_NILAI_TANG_H = "http://pstiubl.com/saida/getNilaiTangibleH.php?Program_Studi=&Tahun_Ajaran=";
    public static final String URL_GET_NILAI_REAL_H = "http://pstiubl.com/saida/getNilaiReliabilityH.php?Program_Studi=&Tahun_Ajaran=";
    public static final String URL_GET_NILAI_RESPON_H = "http://pstiubl.com/saida/getNilaiResponsivenessH.php?Program_Studi=&Tahun_Ajaran=";
    public static final String URL_GET_NILAI_ASSURANCE_H = "http://pstiubl.com/saida/getNilaiAssuranceH.php?Program_Studi=&Tahun_Ajaran=";
    public static final String URL_GET_NILAI_EMPATHY_H = "http://pstiubl.com/saida/getNilaiEmpathyH.php?Program_Studi=&Tahun_Ajaran=";


    //===========================================================================================================================================



        //Keys that will be used to send the request to php scripts
        public static final String KEY_EMP_IDTANGIBLEP = "Id_TangiblePPrimary";
        public static final String KEY_EMP_TANYATANGIBLEP = "Pertanyaan_TangibleP";
        public static final String KEY_EMP_JAWABTANGIBLEP = "Jawaban_TangibleP";
        public static final String KEY_EMP_PRODI_P1="Program_Studi";
        public static final String KEY_EMP_TA_P1="Tahun_Ajaran";
        public static final String KEY_EMP_NPM_P1="Id_mhs";



    //JSON Tags
    public static final String TAG_JSON_ARRAYTANGIBLEP="result_tangibleP";
    public static final String TAG_IDTANGIBLEP = "Id_TangiblePPrimary";
    public static final String TAG_TANYATANGIBLEP= "Pertanyaan_TangibleP";
    public static final String TAG_JAWABTANGIBLEP= "Jawaban_TangibleP";
    public static final String TAG_PRODI_P1="Program StudiP";
    public static final String TAG_TA_P1="Tahun AjaranP";
    public static final String TAG_NPM_P1="Id_mhsP";

    //employee id to pass with intent
    public static final String OSM_IDTANGIBLE = "tg_id";

    //=================================================
    public static final String URL_SIMPAN_TANGIBLEH = "http://pstiubl.com/saida/tangibleh.php";
    public static final String URL_LIHAT2 = "http://pstiubl.com/saida/tampilTangibleH.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_IDTANGIBLEH = "Id_TangibleHPrimary";
    public static final String KEY_EMP_TANYATANGIBLEH = "Pertanyaan_TangibleH";
    public static final String KEY_EMP_JAWABTANGIBLEH = "Jawaban_TangibleH";
    public static final String KEY_EMP_PRODI_H2="Program_Studi";
    public static final String KEY_EMP_TA_H2="Tahun_Ajaran";
    public static final String KEY_EMP_NPM_H2="Id_mhs";

    //JSON Tags
    public static final String TAG_JSON_ARRAYTANGIBLEH="result_tangibleH";
    public static final String TAG_IDTANGIBLEH = "Id_TangibleHPrimary";
    public static final String TAG_TANYATANGIBLEH= "Pertanyaan_TangibleH";
    public static final String TAG_JAWABTANGIBLEH= "Jawaban_TangibleH";
    public static final String TAG_PRODI_H2="Program_Studi";
    public static final String TAG_TA_H2="Tahun_Ajaran";
    public static final String TAG_NPM_H2="Id_mhs";

    //employee id to pass with intent
    public static final String OSM_IDTANGIBLEH = "tg_id";

    //=================================================
    public static final String URL_SIMPAN_RELIABILITYP = "http://pstiubl.com/saida/reliabilityp.php";
    public static final String URL_LIHAT3 = "http://pstiubl.com/saida/tampilReliabilityP.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_IDRELIABILITYP = "Id_ReliabilityPPrimary";
    public static final String KEY_EMP_TANYARELIABILITYP = "Pertanyaan_ReliabilityP";
    public static final String KEY_EMP_JAWABRRELIABILITYP = "Jawaban_ReliabilityP";
    public static final String KEY_EMP_PRODI_P3="Program_Studi";
    public static final String KEY_EMP_TA_P3="Tahun_Ajaran";
    public static final String KEY_EMP_NPM_P3="Id_mhs";

    //JSON Tags
    public static final String TAG_JSON_ARRAYRELIABILITYP="result_ReliabilityP";
    public static final String TAG_IDRELIABILITYP = "Id_ReliabilityPPrimary";
    public static final String TAG_TANYARELIABILITYP= "Pertanyaan_ReliabilityP";
    public static final String TAG_JAWABRELIABILITYP= "Jawaban_ReliabilityP";
    public static final String TAG_PRODI_P3="Program StudiP";
    public static final String TAG_TA_P3="Tahun AjaranP";
    public static final String TAG_NPM_P3="Id_mhsP";

    //employee id to pass with intent
    public static final String OSM_IDRELIABILITYP = "tg_id";

    //=================================================
    public static final String URL_SIMPAN_RELIABILITYH = "http://pstiubl.com/saida/reliabilityh.php";
    public static final String URL_LIHAT4 = "http://pstiubl.com/saida/tampilReliabilityH.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_IDRELIABILITYH = "Id_ReliabilityHPrimary";
    public static final String KEY_EMP_TANYARELIABILITYH = "Pertanyaan_ReliabilityH";
    public static final String KEY_EMP_JAWABRELIABILITYH = "Jawaban_ReliabilityH";
    public static final String KEY_EMP_PRODI_H4="Program_Studi";
    public static final String KEY_EMP_TA_H4="Tahun_Ajaran";
    public static final String KEY_EMP_NPM_H4="Id_mhs";

    //JSON Tags
    public static final String TAG_JSON_ARRAYRELIABILITYH="result_ReliabilityH";
    public static final String TAG_IDRELIABILITYH = "Id_ReliabilityHPrimary";
    public static final String TAG_TANYARELIABILITYH= "Pertanyaan_ReliabilityH";
    public static final String TAG_JAWABRELIABILITYH= "Jawaban_ReliabilityH";
    public static final String TAG_PRODI_H4="Program StudiH";
    public static final String TAG_TA_H4="Tahun AjaranH";
    public static final String TAG_NPM_H4="Id_mhsH";

    //employee id to pass with intent
    public static final String OSM_IDRELIABILITYH = "tg_id";

    //=================================================
    public static final String URL_SIMPAN_RESPONSIVENESSP= "http://pstiubl.com/saida/responsivenessp.php";
    public static final String URL_LIHAT5 = "http://pstiubl.com/saida/tampilResponsivenessP.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_IDRESPONSIVENESSP = "Id_ResponsivenessPPrimary";
    public static final String KEY_EMP_TANYARESPONSIVENESSP = "Pertanyaan_ResponsivenessP";
    public static final String KEY_EMP_JAWABRESPONSIVENESSP = "Jawaban_ResponsivenessP";
    public static final String KEY_EMP_PRODI_P5="Program_Studi";
    public static final String KEY_EMP_TA_P5="Tahun_Ajaran";
    public static final String KEY_EMP_NPM_P5="Id_mhs";

    //JSON Tags
    public static final String TAG_JSON_ARRAYRESPONSIVENESSP="result_ResponsivenessP";
    public static final String TAG_IDRESPONSIVENESSP = "Id_ResponsivenessPPrimary";
    public static final String TAG_TANYARESPONSIVENESSP= "Pertanyaan_ResponsivenessP";
    public static final String TAG_JAWABRESPONSIVENESSP= "Jawaban_ResponsivenessP";
    public static final String TAG_PRODI_P5="Program StudiP";
    public static final String TAG_TA_P5="Tahun AjaranP";
    public static final String TAG_NPM_P5="Id_mhsP";

    //employee id to pass with intent
    public static final String OSM_IDRESPONSIVENESSP = "tg_id";

    //=================================================
    public static final String URL_SIMPAN_RESPONSIVENESSH= "http://pstiubl.com/saida/responsivenessh.php";
    public static final String URL_LIHAT6 = "http://pstiubl.com/saida/tampilResponsivenessH.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_IDRESPONSIVENESSH = "Id_ResponsivenessPPrimary";
    public static final String KEY_EMP_TANYARESPONSIVENESSH = "Pertanyaan_ResponsivenessH";
    public static final String KEY_EMP_JAWABRESPONSIVENESSH = "Jawaban_ResponsivenessH";
    public static final String KEY_EMP_PRODI_H6="Program_Studi";
    public static final String KEY_EMP_TA_H6="Tahun_Ajaran";
    public static final String KEY_EMP_NPM_H6="Id_mhs";

    //JSON Tags
    public static final String TAG_JSON_ARRAYRESPONSIVENESSH="result_ResponsivenessH";
    public static final String TAG_IDRESPONSIVENESSH = "Id_ResponsivenessHPrimary";
    public static final String TAG_TANYARESPONSIVENESSH= "Pertanyaan_ResponsivenessH";
    public static final String TAG_JAWABRESPONSIVENESSH= "Jawaban_ResponsivenessH";
    public static final String TAG_EMP_PRODI_H6="Program StudiH";
    public static final String TAG_EMP_TA_H6="Tahun AjaranH";
    public static final String TAG_EMP_NPM_H6="Id_mhsH";

    //employee id to pass with intent
    public static final String OSM_IDRESPONSIVENESSH = "tg_id";

    //=================================================
    public static final String URL_SIMPAN_ASSURANCEP= "http://pstiubl.com/saida/assurancep.php";
    public static final String URL_LIHAT7 = "http://pstiubl.com/saida/tampilAssuranceP.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_IDASSURANCEP = "Id_AssurancePPrimary";
    public static final String KEY_EMP_TANYAASSURANCEP = "Pertanyaan_AssuranceP";
    public static final String KEY_EMP_JAWABASSURANCEP = "Jawaban_AssuranceP";
    public static final String KEY_EMP_PRODI_P7="Program_Studi";
    public static final String KEY_EMP_TA_P7="Tahun_Ajaran";
    public static final String KEY_EMP_NPM_P7="Id_mhs";

    //JSON Tags
    public static final String TAG_JSON_ARRAYASSURANCEP="result_AssuranceP";
    public static final String TAG_IDASSURANCEP = "Id_AssurancePPrimary";
    public static final String TAG_TANYAASSURANCEP= "Pertanyaan_AssuranceP";
    public static final String TAG_JAWABASSURANCEP= "Jawaban_AssuranceP";
    public static final String TAG_PRODI_P7="Program StudiP";
    public static final String TAG_TA_P7="Tahun AjaranP";
    public static final String TAG_NPM_P7="Id_mhsP";

    //employee id to pass with intent
    public static final String OSM_IDASSURANCEP = "tg_id";

    //=================================================
    public static final String URL_SIMPAN_ASSURANCEH= "http://pstiubl.com/saida/assuranceh.php";
    public static final String URL_LIHAT8 = "http://pstiubl.com/saida/tampilAssuranceH.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_IDASSURANCEH = "Id_AssuranceHPrimary";
    public static final String KEY_EMP_TANYAASSURANCEH = "Pertanyaan_AssuranceH";
    public static final String KEY_EMP_JAWABASSURANCEH = "Jawaban_AssuranceH";
    public static final String KEY_EMP_PRODI_H8="Program_Studi";
    public static final String KEY_EMP_TA_H8="Tahun_Ajaran";
    public static final String KEY_EMP_NPM_H8="Id_mhs";

    //JSON Tags
    public static final String TAG_JSON_ARRAYASSURANCEH="result_AssuranceH";
    public static final String TAG_IDASSURANCEH = "Id_AssuranceHPrimary";
    public static final String TAG_TANYAASSURANCEH= "Pertanyaan_AssuranceH";
    public static final String TAG_JAWABASSURANCEH= "Jawaban_AssuranceH";
    public static final String TAG_PRODI_H8="Program StudiH";
    public static final String TAG_TA_H8="Tahun AjaranH";
    public static final String TAG_NPM_H8="Id_mhsH";

    //employee id to pass with intent
    public static final String OSM_IDASSURANCEH = "tg_id";

    //=================================================
    public static final String URL_SIMPAN_EMPATHYP= "http://pstiubl.com/saida/empathyp.php";
    public static final String URL_LIHAT9 = "http://pstiubl.com/saida/tampilEmpathyP.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_IDEMPATHYP = "Id_EmpathyPPrimary";
    public static final String KEY_EMP_TANYAEMPATHYP = "Pertanyaan_EmpathyP";
    public static final String KEY_EMP_JAWABEMPATHYP = "Jawaban_EmpathyP";
    public static final String KEY_EMP_PRODI_P9="Program_Studi";
    public static final String KEY_EMP_TA_P9="Tahun_Ajaran";
    public static final String KEY_EMP_NPM_P9="Id_mhs";


    //JSON Tags
    public static final String TAG_JSON_ARRAYEMPATHYP="result_EmpathyP";
    public static final String TAG_IDEMPATHYP = "Id_EmpathyPPrimary";
    public static final String TAG_TANYAEMPATHYP= "Pertanyaan_EmpathyP";
    public static final String TAG_JAWABEMPATHYP= "Jawaban_EmpathyP";
    public static final String TAG_PRODI_P9="Program StudiP";
    public static final String TAG_TA_P9="Tahun AjaranP";
    public static final String TAG_NPM_P9="Id_mhsP";

    //employee id to pass with intent
    public static final String OSM_IDEMPATHYP = "tg_id";


    //=================================================
    public static final String URL_SIMPAN_EMPATHYH= "http://pstiubl.com/saida/empathyh.php";
    public static final String URL_LIHAT10 = "http://pstiubl.com/saida/tampilEmpathyH.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_IDEMPATHYH = "Id_EmpathyHPrimary";
    public static final String KEY_EMP_TANYAEMPATHYH = "Pertanyaan_EmpathyH";
    public static final String KEY_EMP_JAWABEMPATHYH = "Jawaban_EmpathyH";
    public static final String KEY_EMP_PRODI_H10="Program_Studi";
    public static final String KEY_EMP_TA_H10="Tahun_Ajaran";
    public static final String KEY_EMP_NPM_H10="Id_mhs";

    //JSON Tags
    public static final String TAG_JSON_ARRAYEMPATHYH="result_EmpathyH";
    public static final String TAG_IDEMPATHYH = "Id_EmpathyHPrimary";
    public static final String TAG_TANYAEMPATHYH= "Pertanyaan_EmpathyH";
    public static final String TAG_JAWABEMPATHYH= "Jawaban_EmpathyH";
    public static final String TAG_PRODI_H10="Program_Studi";
    public static final String TAG_TA_H10="Tahun_Ajaran";
    public static final String TAG_NPM_H10="Id_mhs";

    //employee id to pass with intent
    public static final String OSM_IDEMPATHYH = "tg_id";

    //=================================== DATA STAFF =================================
    public static final String KEY_EMP_NPM = "Id_StafAdmin";
    public static final String KEY_EMP_USER = "Username";
    public static final String KEY_EMP_MAIL = "Email";
    public static final String KEY_EMP_PASS = "Password";

    public static final String TAG_JSON_ARRAY_STAFF = "result";

    public static final String TAG_NPM = "Id_StafAdmin";
    public static final String TAG_USER = "Username";
    public static final String TAG_MAIL = "Email";
    public static final String TAG_PASS = "Password";

    //================================== DATA MAHASISWA =============================
    public static final String KEY_EMP_NPM_MHS = "Id_mhs";
    public static final String KEY_EMP_MAIL_MHS = "Email";
    public static final String KEY_EMP_PRODI = "ProgramStudi";
    public static final String KEY_EMP_PASS_MHS = "Password";

    public static final String TAG_NPM_MHS = "Id_mhs";
    public static final String TAG_MAIL_MHS = "Email";
    public static final String TAG_PRODI = "ProgramStudi";
    public static final String TAG_PASS_MHS = "Password";

    //================================= DATA SESSION LOG MAHASISWA ==================
    public static final String TAG_JSON_ARRAY_NPM = "result";
    public static final String KEY_LOG_NPM = "Id_mhs";
    public static final String KEY_LOG_PRODI= "ProgramStudi";

    public static final String TAG_LOG_NPM = "Id_mhs";
    public static final String TAG_LOG_PRODI = "ProgramStudi";


    //================================ DATA NILAI TANGIBLE P ========================
    public static final String TAG_JSON_ARRAY_HITUNG_TANGIBLE_P = "result";

    public static final String TAG_JUMLAH_TANGIBLE_P = "jumlah_quis";
    public static final String TAG_HASIL_TANGIBLE_P = "hasil";

    public static final String KEY_PRODI_TANGIBLE_P = "Program_Studi";
    public static final String KEY_TA_TANGIBLE_P = "Tahun_Ajaran";

    public static final String KEY_JUMLAH_TANGIBLE_P = "jumlah_quis";
    public static final String KEY_HASIL_TANGIBLE_P = "hasil";

    //================================ DATA NILAI TANGIBLE H ========================
    public static final String TAG_JSON_ARRAY_HITUNG_TANGIBLE_H = "result";

    public static final String TAG_JUMLAH_TANGIBLE_H = "jumlah_quis";
    public static final String TAG_HASIL_TANGIBLE_H = "hasil";

    public static final String KEY_PRODI_TANGIBLE_H = "Program_Studi";
    public static final String KEY_TA_TANGIBLE_H = "Tahun_Ajaran";

    public static final String KEY_JUMLAH_TANGIBLE_H = "jumlah_quis";
    public static final String KEY_HASIL_TANGIBLE_H_ = "hasil";

    //================================ DATA NILAI RELIABILITY P ========================
    public static final String TAG_JSON_ARRAY_HITUNG_RELIABILITY_P = "result";

    public static final String TAG_JUMLAH_RELIABILITY_P = "jumlah_quis";
    public static final String TAG_HASIL_RELIABILITY_P = "hasil";

    public static final String KEY_PRODI_RELIABILITY_P = "Program_Studi";
    public static final String KEY_TA_RELIABILITY_P = "Tahun_Ajaran";

    public static final String KEY_JUMLAH_RELIABILITY_P = "jumlah_quis";
    public static final String KEY_HASIL_RELIABILITY_P = "hasil";

    //================================ DATA NILAI RELIABILITY H ========================
    public static final String TAG_JSON_ARRAY_HITUNG_RELIABILITY_H = "result";

    public static final String TAG_JUMLAH_RELIABILITY_H = "jumlah_quis";
    public static final String TAG_HASIL_RELIABILITY_H = "hasil";

    public static final String KEY_PRODI_RELIABILITY_H = "Program_Studi";
    public static final String KEY_TA_RELIABILITY_H = "Tahun_Ajaran";

    public static final String KEY_JUMLAH_RELIABILITY_H = "jumlah_quis";
    public static final String KEY_HASIL_RELIABILITY_H= "hasil";

    //================================ DATA NILAI RESPONSIVENESS P ========================
    public static final String TAG_JSON_ARRAY_HITUNG_RESPONSIVENESS_P = "result";

    public static final String TAG_JUMLAH_RESPONSIVENESS_P = "jumlah_quis";
    public static final String TAG_HASIL_RESPONSIVENESS_P = "hasil";

    public static final String KEY_PRODI_RESPONSIVENESS_P = "Program_Studi";
    public static final String KEY_TA_RESPONSIVENESS_P = "Tahun_Ajaran";

    public static final String KEY_JUMLAH_RESPONSIVENESS_P = "jumlah_quis";
    public static final String KEY_HASIL_RESPONSIVENESS_P = "hasil";

    //================================ DATA NILAI RESPONSIVENESS H ========================
    public static final String TAG_JSON_ARRAY_HITUNG_RESPONSIVENESS_H = "result";

    public static final String TAG_JUMLAH_RESPONSIVENESS_H = "jumlah_quis";
    public static final String TAG_HASIL_RESPONSIVENESS_H = "hasil";

    public static final String KEY_PRODI_RESPONSIVENESS_H = "Program_Studi";
    public static final String KEY_TA_RESPONSIVENESS_H = "Tahun_Ajaran";

    public static final String KEY_JUMLAH_RESPONSIVENESS_H = "jumlah_quis";
    public static final String KEY_HASIL_RESPONSIVENESS_H = "hasil";

    //================================ DATA NILAI ASSURANCE P ========================
    public static final String TAG_JSON_ARRAY_HITUNG_ASSURANCE_P = "result";

    public static final String TAG_JUMLAH_ASSURANCE_P = "jumlah_quis";
    public static final String TAG_HASIL_ASSURANCE_P = "hasil";

    public static final String KEY_PRODI_ASSURANCE_P = "Program_Studi";
    public static final String KEY_TA_ASSURANCE_P = "Tahun_Ajaran";

    public static final String KEY_JUMLAH_ASSURANCE_P = "jumlah_quis";
    public static final String KEY_HASIL_ASSURANCE_P = "hasil";

    //================================ DATA NILAI ASSURANCE H ========================
    public static final String TAG_JSON_ARRAY_HITUNG_ASSURANCE_H = "result";

    public static final String TAG_JUMLAH_ASSURANCE_H = "jumlah_quis";
    public static final String TAG_HASIL_ASSURANCE_H = "hasil";

    public static final String KEY_PRODI_ASSURANCE_H = "Program_Studi";
    public static final String KEY_TA_ASSURANCE_H = "Tahun_Ajaran";

    public static final String KEY_JUMLAH_ASSURANCE_H = "jumlah_quis";
    public static final String KEY_HASIL_ASSURANCE_H = "hasil";

    //================================ DATA NILAI EMPATHY P ========================
    public static final String TAG_JSON_ARRAY_HITUNG_EMPATHY_P = "result";

    public static final String TAG_JUMLAH_EMPATHY_P = "jumlah_quis";
    public static final String TAG_HASIL_EMPATHY_P = "hasil";

    public static final String KEY_PRODI_EMPATHY_P = "Program_Studi";
    public static final String KEY_TA_EMPATHY_P = "Tahun_Ajaran";

    public static final String KEY_JUMLAH_EMPATHY_P = "jumlah_quis";
    public static final String KEY_HASIL_EMPATHY_P = "hasil";

    //================================ DATA NILAI EMPATHY H ========================
    public static final String TAG_JSON_ARRAY_HITUNG_EMPATHY_H = "result";

    public static final String TAG_JUMLAH_EMPATHY_H = "jumlah_quis";
    public static final String TAG_HASIL_EMPATHY_H = "hasil";

    public static final String KEY_PRODI_EMPATHY_H = "Program_Studi";
    public static final String KEY_TA_EMPATHY_H = "Tahun_Ajaran";

    public static final String KEY_JUMLAH_EMPATHY_H = "jumlah_quis";
    public static final String KEY_HASIL_EMPATHY_H = "hasil";

}