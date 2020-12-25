package com.example.valuteinfo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.state.State;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

import Model.Valute;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Valute> valutes;

    RecAdapter(Context context, List<Valute> valutes) {
        this.valutes = valutes;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Valute valute = valutes.get(position);
        holder.nameValute.setText(valute.getNameValuteStr());
        holder.code.setText(valute.getCODE());
        holder.curs.setText(valute.getCURS());
        holder.amount.setText(valute.getAmount());
    }

    @Override
    public int getItemCount() {
        return valutes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        final TextView code, curs, nameValute, amount;
        ViewHolder(View view){
            super(view);
            code = (TextView) view.findViewById(R.id.CODE);
            curs = (TextView) view.findViewById(R.id.CURS);
            nameValute = (TextView) view.findViewById(R.id.NameValute);
            amount = (TextView) view.findViewById(R.id.AMOUNT);

        }
    }
}
