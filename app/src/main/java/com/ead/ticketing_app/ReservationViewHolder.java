package com.ead.ticketing_app;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ead.ticketing_app.custom_classes.ReservationClass;

public class ReservationViewHolder extends RecyclerView.ViewHolder {
    private TextView startFromView;
    private TextView endFromView;
    private TextView noOfTicketsView;
    private TextView trainClassView;
    private TextView dateView;
    private TextView priceView;

    //view holder
    public ReservationViewHolder(@NonNull View itemView) {
        super(itemView);
        startFromView = itemView.findViewById(R.id.startFrom);
        endFromView = itemView.findViewById(R.id.endFrom);
        noOfTicketsView = itemView.findViewById(R.id.noOfTickets);
        trainClassView = itemView.findViewById(R.id.trainClass);
        dateView = itemView.findViewById(R.id.date);
        priceView = itemView.findViewById(R.id.price);
    }

    //binding data
    public void bind(ReservationClass reservation) {
        startFromView.setText(reservation.getStartFrom());
        endFromView.setText(reservation.getEndFrom());
        noOfTicketsView.setText(reservation.getNoOfTickets());
        trainClassView.setText(reservation.getTrainClass());
        dateView.setText(reservation.getDate());
        priceView.setText(reservation.getPrice());
    }
}
