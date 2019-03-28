package dlmbg.pckg.tampil.gambar;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class TampilkanGambarActivity extends Activity {
	
	static String in_judul = "judul";
	static String in_waktu = "tanggal";
	static String in_gambar = "gambar";
	JSONArray str_json = null;
	public String lo_Koneksi,isi ;
	
	ListView list;
    LazyAdapter adapter;
	ArrayList<HashMap<String, String>> data_map = new ArrayList<HashMap<String, String>>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Koneksi lo_Koneksi = new Koneksi();
        isi = lo_Koneksi.isi_koneksi();
        
        String link_url = isi+"berita.php";
		JSONParser jParser = new JSONParser();
		JSONObject json = jParser.AmbilJson(link_url);
		
		try {
			str_json = json.getJSONArray("berita");
			
			for(int i = 0; i < str_json.length(); i++){
				JSONObject ar = str_json.getJSONObject(i);

				String judul = ar.getString("judul");
				String gambar = ar.getString("gambar");
				String waktu = ar.getString("tanggal")+" | "+ar.getString("waktu");
				
				HashMap<String, String> map = new HashMap<String, String>();

				map.put(in_judul, judul);
				map.put(in_gambar, gambar);
				map.put(in_waktu, waktu);

				data_map.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		list=(ListView)findViewById(R.id.list);
        adapter=new LazyAdapter(this, data_map);        
        list.setAdapter(adapter);
    }
}