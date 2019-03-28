package com.polda.ari.abdi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MenuApps extends AppCompatActivity {
    SessionSharePreference session;
    NiftyDialogBuilder dialogs;
    String contents;
    public static TextView txt_nips, txt_jams, txt_termin, txt_hari;
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    ImageButton im_absen, im_logout, im_riwayat, im_tentang;
    public static final int NOTIFICATION_ID = 1;
    DigitalClock dc;
    ProgressDialog loading;
    private MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_apps);
        im_absen = (ImageButton) findViewById(R.id.btn_absen);
        im_logout = (ImageButton) findViewById(R.id.btn_logout);
        im_riwayat = (ImageButton) findViewById(R.id.btn_riwayat);
        im_tentang = (ImageButton) findViewById(R.id.btn_tentang);
        txt_jams = (TextView) findViewById(R.id.txt_jamku);
        txt_termin = (TextView) findViewById(R.id.txt_termin);
        txt_hari = (TextView) findViewById(R.id.txt_hari);
        //tampilNotification();

        //Toast.makeText(getApplication(),"Data "+txt_termin.getText().toString().trim()+"\n"+txt_hari.getText().toString().trim(),Toast.LENGTH_LONG).show();


        session = new SessionSharePreference(MenuApps.this.getApplicationContext());
        dialogs = NiftyDialogBuilder.getInstance(this);
        txt_nips = (TextView) findViewById(R.id.txt_nip);
        dc = (DigitalClock) findViewById(R.id.digitalClock1);

         //session.setNama(null);
        String nama = session.getNama();
        txt_nips.setText(nama);


        im_absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(ACTION_SCAN);
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    startActivityForResult(intent, 0);
                } catch (ActivityNotFoundException anfe) {
                    showDialog(MenuApps.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
                }
            }
        });

        im_riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuApps.this, Md_pilihan.class);
                startActivity(intent);
            }
        });

        im_tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogs
                        .withTitle("Informasi")
                        .withMessage("Aplikasi ABDI (Absensi Digital, digunakan untuk karyawan/staff marketing Universitas Bandar Lampung " +
                                "Pengkodean aplikasi oleh : Mas Ari | Kontak : lumenrobot@gmail.com")
                        .withDialogColor("#16a085")
                        .withButton1Text("OK")
                        .withEffect(Effectstype.Fall);
                dialogs.isCancelableOnTouchOutside(true);
                dialogs.setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogs.dismiss();
                    }
                });
                dialogs.show();
            }
        });

        im_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setNama(null);
                Intent intent = new Intent(MenuApps.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Thread myThread = null;

        Runnable runnable = new CountDownRunner();
        myThread= new Thread(runnable);
        myThread.start();

    }

    public void startDua(View view) { startActivity(new Intent(this, Md_Ijin.class)); }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()){
            case R.id.ijins:
                msg="Ijin";
                break;
            case R.id.jadwals:
                msg="Jadwal";
                break;
        }
        if(msg.equals("Ijin")){
            Intent intent = new Intent(MenuApps.this, Md_Ijin.class);
            startActivity(intent);
        }else if(msg.equals("Jadwal")){

            dialogs
                    .withTitle("Informasi")
                    .withMessage("Jadwal Absensi / Ijin : \n" +
                            "1. Hari Senin :\n" +
                            "\t- Pagi : 07.30 - 08.15 WIB\n" +
                            "\t- Siang : 12.00 - 13.30 WIB\n" +
                            "\t- Sore : 16.30 - 17.30 WIB\n" +
                            "========================\n" +
                            "2. Hari Selasa :\n" +
                            "\t- Pagi : 07.30 - 08.15 WIB\n" +
                            "\t- Siang : 12.00 - 13.30 WIB\n" +
                            "\t- Sore : 16.30 - 17.30 WIB\n" +
                            "========================\n" +
                            "3. Hari Rabu :\n" +
                            "\t- Pagi : 07.30 - 08.15 WIB\n" +
                            "\t- Siang : 12.00 - 13.30 WIB\n" +
                            "\t- Sore : 16.30 - 17.30 WIB\n" +
                            "========================\n" +
                            "4. Hari Kamis :\n" +
                            "\t- Pagi : 07.30 - 08.15 WIB\n" +
                            "\t- Siang : 12.00 - 13.30 WIB\n" +
                            "\t- Sore : 16.30 - 17.30 WIB\n" +
                            "========================\n" +
                            "5. Hari Jumat :\n" +
                            "\t- Pagi : 07.30 - 08.15 WIB\n" +
                            "\t- Siang : 12.00 - 13.30 WIB\n" +
                            "\t- Sore : 16.30 - 17.30 WIB\n" +
                            "========================\n" +
                            "6. Hari Sabtu :\n" +
                            "\t- Pagi : 07.30 - 08.15 WIB\n" +
                            "\t- Siang : 12.00 - 15.00 WIB\n")
                    .withDialogColor("#16a085")
                    .withButton1Text("OK")
                    .withEffect(Effectstype.Fall);
            dialogs.isCancelableOnTouchOutside(true);
            dialogs.setButton1Click(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogs.dismiss();
                }
            });
            dialogs.show();

        }
        return super.onOptionsItemSelected(item);
    }

    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try{
                    // TextView txtCurrentTime= (TextView)findViewById(R.id.lbltime);
                    Date dt = new Date();
                    int hours = dt.getHours();
                    int minutes = dt.getMinutes();
                    int seconds = dt.getSeconds();
                    //String curTime = hours + ":" + minutes + ":" + seconds;
                    String curTime = hours + "." + minutes;
                    txt_jams.setText(curTime);
                    getData(curTime);

                }catch (Exception e) {}
            }
        });

    }


    class CountDownRunner implements Runnable {
        // @Override
        public void run() {
            Thread.currentThread().isInterrupted();
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }

        }
    }

    /*class CountDownRunner implements Runnable {
        // @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }*/


    // dilalog untuk menampilkan peringantan jika belum nginstall aplikasi sncanner com.google.zxing.client.android
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(act);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });

        dialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return dialog.show();
    }

    // untuk menampilkan hasil scanner
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                contents = intent.getStringExtra("SCAN_RESULT");
                //format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                if(txt_termin.getText().toString().trim().equals("TextView") || txt_hari.getText().toString().trim().equals("TextView")){
                    dialogs
                            .withTitle("Informasi")
                            .withMessage("Waktu ABSEN sudah habis !")
                            .withDialogColor("#c0392b")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Shake);
                    dialogs.isCancelableOnTouchOutside(true);
                    dialogs.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                        }
                    });
                    dialogs.show();
                }else {
                    simpanAbsensi(txt_nips.getText().toString().trim(), txt_termin.getText().toString().trim(), contents);
                }

            }
        }
    }


    private void getData(final String Jam){

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = "http://ppikubl.com/api_absensi/jadwal.php?jam="+Jam;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showDetail(response);
                // Tampikkan data
                //Toast.makeText(getApplication(),"Data Jadwal "+response,Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MenuApps.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void showDetail(String json){

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);

                String jam  = c.getString(config.TAG_JAM);
                String termin  = c.getString(config.TAG_TERMIN);
                String hari  = c.getString(config.TAG_HARI);
                //Toast.makeText(getApplication(),"JAM "+termin,Toast.LENGTH_LONG).show();
                txt_termin.setText(termin);
                txt_hari.setText(hari);
            }

            if(txt_termin.getText().toString().trim().equals("TextView")){
                onPause();
            }else{
                playSound();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplication(),"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }


    public void simpanAbsensi(final String Nip, final String Termin, final String Status){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("nip", Nip));
                nameValuePairs.add(new BasicNameValuePair("termin", Termin));
                nameValuePairs.add(new BasicNameValuePair("status", Status));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://ppikubl.com/api_absensi/absen.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result.equalsIgnoreCase("success")){
                    dialogs
                            .withTitle("Informasi")
                            .withMessage("Terima kasih telah hadir tepat waktu")
                            .withDialogColor("#3da8c9")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Fall);
                    dialogs.isCancelableOnTouchOutside(true);
                    dialogs.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                        }
                    });
                    dialogs.show();

                }else{
                    Toast.makeText(getApplication(),"Terjadi kesalahan Sistem",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(Nip, Termin, Status);

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(MenuApps.this, MenuApps.class);
        startActivity(intent);
    }

    public void tampilNotification() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.okedroid.com/"));
        //menginisialiasasi intent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //untuk memanggil activity di Notification
        /*
Menmbangun atau mensetup Notification dengan NotificationCompat.Builder
 */
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher) //ikon notification
                .setContentTitle("Waktunya Absen "+txt_termin.getText().toString().trim())//judul konten
                .setContentIntent(pendingIntent)//memanggil object pending intent
                .setAutoCancel(true)//untuk menswipe atau menghapus notification
                .setContentText("Absensi Digital Karyawan"); //isi text

/*
Kemudian kita harus menambahkan Notification dengan menggunakan NotificationManager
 */

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, builder.build()
        );
    }


    @Override
    public void onPause() {
        try{
            super.onPause();
            player.pause();
        }catch (Exception e){

        }
    }

    private void playSound(){
        try{
            if (player.isPlaying()) {
                player.stop();
                player.release();
            }
        }catch(Exception e){
            Toast.makeText(this, " Masuk Exception", Toast.LENGTH_LONG).show();
        }
        player = MediaPlayer.create(this, R.raw.notif_abdi);
        player.setLooping(false); // Set looping
        player.start();
    }



}
