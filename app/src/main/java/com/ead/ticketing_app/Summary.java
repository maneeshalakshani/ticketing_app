package com.ead.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Button confirmBtn = (Button) findViewById(R.id.confirmBtn);
        TextView num_val = (TextView) findViewById(R.id.num_val);
        TextView duration_val = (TextView) findViewById(R.id.duration_val);
        TextView date_val = (TextView) findViewById(R.id.date_val);
        TextView trainClass_val = (TextView) findViewById(R.id.trainClass_val);
        TextView total_val = (TextView) findViewById(R.id.price_val);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String trainClass = intent.getStringExtra("trainClass");
        String noOfTickets = intent.getStringExtra("noOfTickets");

        if (date != null && trainClass != null && noOfTickets != null) {
            num_val.setText(String.valueOf(300));
            duration_val.setText("1 day");
            date_val.setText(date.split(": ")[1]);
            trainClass_val.setText(trainClass);
            total_val.setText("LRK " + calculateTotal(trainClass, Integer.parseInt(noOfTickets)));
        }else{
            Log.d("TEST>>>>", "Something is empty");
        }



        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Summary.this, "Successfully Confirmed!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Summary.this, Main_Menu.class));
            }
        });
    }

    String calculateTotal(String trainClass, int noOfDays) {
        double paymentPerDay = 0;
        switch (trainClass.toLowerCase()){
            case "class 1": paymentPerDay = 1000;
                            break;
            case "class 2": paymentPerDay = 600;
                break;
            case "class 3": paymentPerDay = 300;
                break;
        }
        return String.valueOf(paymentPerDay * noOfDays);
    }
}