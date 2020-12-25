package com.example.valuteinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Model.Valute;

public class MainActivity extends AppCompatActivity {

    ArrayList<Valute> valutes = new ArrayList<Valute>();

    public Elements title;
    public ArrayList<String> titleList = new ArrayList<String>();
    private RecAdapter adapter;
    private RecyclerView lv;

    private Button btn;
    private EditText DAY, MONTH, YEAR;

    private String url = "http://www.finmarket.ru/currency/rates/?id=10148&pv=0&bd=25&bm=12&by=2020&x=61&y=9#archive";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btn = findViewById(R.id.butt);
        DAY = findViewById(R.id.day);
        MONTH = findViewById(R.id.month);
        YEAR = findViewById(R.id.year);


        lv = findViewById(R.id.valuteList);
        new NewThread().execute();
        adapter = new RecAdapter(this, valutes);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    valutes.clear();

                    url = "http://www.finmarket.ru/currency/rates/?id=10148&pv=0&bd="+ DAY.getText() +"&bm="+ MONTH.getText() +"&by="+ YEAR.getText() +"&x=61&y=9#archive";
                    new NewThread().execute();
                    adapter = new RecAdapter(MainActivity.this, valutes);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    public class NewThread extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg) {
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                title = doc.select("tbody > tr");
                titleList.clear();
                for (Element titles : title) {
                        valutes.add(new Valute (titles.text().split(" ")[1] + " " + titles.text().split(" ")[2], titles.text().split(" ")[0],
                                titles.text().split(" ")[4], titles.text().split(" ")[3]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            valutes.remove(0);
            lv.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.news:
                Intent i = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}