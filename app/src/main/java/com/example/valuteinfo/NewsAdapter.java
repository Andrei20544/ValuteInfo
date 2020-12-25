package com.example.valuteinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.News;
import Model.Valute;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<News> newsList;

    Context con;

    NewsAdapter(Context context, List<News> newsList) {
        this.newsList = newsList;
        this.inflater = LayoutInflater.from(context);

        con = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News newsS = newsList.get(position);
        holder.date.setText(newsS.getDATA());
        holder.title.setText(newsS.getTITLE());
        holder.text.setText(newsS.getTEXT());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        final TextView date, title, text;
        ViewHolder(View view){
            super(view);
            date = (TextView) view.findViewById(R.id.dateTxt);
            title = (TextView) view.findViewById(R.id.titleTxt);
            text = (TextView) view.findViewById(R.id.textTxt);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    Toast.makeText(con, "Element " + position + " is Clicked!", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
