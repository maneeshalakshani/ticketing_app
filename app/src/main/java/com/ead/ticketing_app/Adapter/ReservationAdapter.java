package com.ead.ticketing_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ead.ticketing_app.R;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {
    private Context context;
    private ArrayList item_name, item_id, price_per_day, no_of_days, status;

    //ReservationAdapter constructor
    public ReservationAdapter(Context context,
                       ArrayList item_id,
                       ArrayList item_name,
                       ArrayList price_per_day,
                       ArrayList no_of_days,
                       ArrayList status) {
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.no_of_days = no_of_days;
        this.price_per_day = price_per_day;
        this.status = status;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    //binding to the viewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item_id_txt.setText(String.valueOf(item_id.get(position)));
        holder.item_name_txt.setText(String.valueOf(item_name.get(position)));
        holder.no_of_days_txt.setText(String.valueOf(no_of_days.get(position)));
        holder.price_per_day_txt.setText(String.valueOf(price_per_day.get(position)));
        holder.status_txt.setText(String.valueOf(status.get(position)));
    }

    //getting fetched item count
    @Override
    public int getItemCount() {
        return item_id.size();
    }

    //MyViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_name_txt, item_id_txt, price_per_day_txt, no_of_days_txt, status_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name_txt = itemView.findViewById(R.id.item_name);
            item_id_txt = itemView.findViewById(R.id.item_id);
            price_per_day_txt = itemView.findViewById(R.id.item_price_per_day);
//            no_of_days_txt = itemView.findViewById(R.id.);
//            status_txt = itemView.findViewById(R.id.sta);
        }
    }

}
