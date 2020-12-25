package com.example.valuteinfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import Model.News;
import Model.Valute;

public class NewsActivity extends AppCompatActivity {

    ArrayList<News> newsList = new ArrayList<News>();

    public Elements main, date;
    public ArrayList<String> mainList = new ArrayList<String>();
    private NewsAdapter adapter;
    private RecyclerView lv;

    private String url = "http://www.finmarket.ru/news/?nt=0&pg=1";

    private Button AllNews, CurrNews, PromNews, BonNews, WorldNews;


    private String dateS, titleS, textS;
    private TextView NewsCat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        lv = findViewById(R.id.NewsList);
        new NewThread().execute();
        adapter = new NewsAdapter(NewsActivity.this, newsList);

        AllNews = findViewById(R.id.allNews);
        CurrNews = findViewById(R.id.currNews);
        PromNews = findViewById(R.id.promNews);
        BonNews = findViewById(R.id.bonNews);
        WorldNews = findViewById(R.id.worldNews);

        NewsCat = findViewById(R.id.newsCat);
        NewsCat.setText("All");

        AllNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetNewURL("http://www.finmarket.ru/news/?nt=0&pg=1");
                NewsCat.setText("All");
            }
        });

        CurrNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetNewURL("http://www.finmarket.ru/news/?nt=1&pg=1");
                NewsCat.setText("Currency");
            }
        });

        PromNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetNewURL("http://www.finmarket.ru/news/?nt=2&pg=1");
                NewsCat.setText("Promotions");
            }
        });

        BonNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetNewURL("http://www.finmarket.ru/news/?nt=3&pg=1");
                NewsCat.setText("Bonds");
            }
        });

        WorldNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetNewURL("http://www.finmarket.ru/news/?nt=4&pg=1");
                NewsCat.setText("World News");
            }
        });


    }

    public void GetNewURL(String urlN){
        try {
            newsList.clear();

            url = urlN;
            new NewThread().execute();
            adapter = new NewsAdapter(NewsActivity.this, newsList);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public class NewThread extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg) {
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                main = doc.select("div.ind_article > *");
                mainList.clear();
                for (Element titles : main) {
                    if (titles.select("span").text() != ""){
                        dateS = titles.select("span").text();
                    }
                    if (titles.select("div.title").text() != ""){
                        titleS = titles.select("div.title").text();
                    }
                    if (titles.select("div.lead").text() != ""){
                        textS = titles.select("div.lead").text();
                    }


                    if (dateS != "" && titleS != "" && textS != ""){
                        newsList.add(new News(dateS, titleS, textS));
                        dateS = "";
                        titleS = "";
                        textS = "";
                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            newsList.remove(0);
            lv.setAdapter(adapter);
        }
    }
}