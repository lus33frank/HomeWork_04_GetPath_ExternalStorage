package tw.com.frankchang.houli.homework_04_getpath_externalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private TextView tvshow_01, tvshow_02;
    private ListView listview;

    private final String KEY_NAME = "filename";
    private final String KEY_TYPE = "type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviewer();
    }

    private void findviewer() {
        tvshow_01 = (TextView) findViewById(R.id.textView);
        tvshow_02 = (TextView) findViewById(R.id.textView2);
        listview = (ListView) findViewById(R.id.listView);
    }

    private void setTruePath(){
        tvshow_01.setText(new File("storage/sdcard1").getAbsolutePath());
    }

    public void getPathOnClick(View view) {
        File sdcard = new File("sdcard1");
        File[] files = sdcard.listFiles();

        tvshow_02.setText(sdcard.getAbsolutePath());
        setTruePath();

        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        if (files != null) {
            for (int i = 0; i < files.length; i++){
                HashMap<String, Object> items = new HashMap<>();
                items.put(KEY_NAME, files[i].getName());
                if(files[i].isDirectory()){
                    items.put(KEY_TYPE, getString(R.string.main_folder));
                }
                else {
                    items.put(KEY_TYPE, getString(R.string.main_file));
                }
                data.add(items);
            }
        }
        else{
            HashMap<String, Object> items = new HashMap<>();
            items.put(KEY_NAME, getString(R.string.main_null));
            items.put(KEY_TYPE, getString(R.string.main_null));
            data.add(items);
        }

        SimpleAdapter sAdt = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2,
                new String[]{KEY_NAME, KEY_TYPE},
                new int[]{android.R.id.text1, android.R.id.text2});
        listview.setAdapter(sAdt);
    }
}
