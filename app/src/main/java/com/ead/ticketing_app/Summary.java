package com.ead.ticketing_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ead.ticketing_app.api.Services;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Summary extends AppCompatActivity {

    OkHttpClient okHttpClient;
    String postURL = "https://10.0.2.2:7104/api/Booking/addBooking";
//    String getURL = "https://10.0.2.2:7104/api/Booking";
    TextView num_val, duration_val, date_val, trainClass_val, total_val, noOfTickets_val, startFrom_val, endFrom_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Button confirmBtn = (Button) findViewById(R.id.confirmBtn);
        num_val = (TextView) findViewById(R.id.num_val);
        duration_val = (TextView) findViewById(R.id.duration_val);
        date_val = (TextView) findViewById(R.id.date_val);
        trainClass_val = (TextView) findViewById(R.id.trainClass_val);
        total_val = (TextView) findViewById(R.id.price_val);
        noOfTickets_val = (TextView) findViewById(R.id.noOfTickets_val);
        startFrom_val = (TextView) findViewById(R.id.startFrom_val);
        endFrom_val = (TextView) findViewById(R.id.endFrom_val);

        Services services = new Services();
        okHttpClient = services.createCustomOkHttpClient();

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String trainClass = intent.getStringExtra("trainClass");
        String noOfTickets = intent.getStringExtra("noOfTickets");
        String startFrom = intent.getStringExtra("startFrom");
        String endFrom = intent.getStringExtra("endFrom");

        if (date != null && trainClass != null && noOfTickets != null) {
            num_val.setText(String.valueOf(300));
            duration_val.setText("1 day");
            date_val.setText(date.split(": ")[1]);
            trainClass_val.setText(trainClass);
            total_val.setText("LRK " + calculateTotal(trainClass, Integer.parseInt(noOfTickets)));
            noOfTickets_val.setText(noOfTickets);
            startFrom_val.setText(startFrom);
            endFrom_val.setText(endFrom);
        }else{
            Log.d("TEST>>>>", "Something is empty");
        }



        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add Data
                post();
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

//    public void get(){
//        Request request = new Request.Builder().url(getURL).build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            String data = response.body().string();
//                            num_val.setText(data);
//                            Log.d("SUMMARY", "Result: " + data);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                });
//            }
//        });
//    }

//    public void post(){
//        RequestBody requestBody = new FormBody.Builder()
//                .add("fromStation", "Colombo")
//                .add("toStation", "Matara")
//                .add("journeyDate", "15/10/2023")
//                .add("noOfTickets", "12")
//                .add("ticketclass", "Class 1")
//                .build();
//        Request request = new Request.Builder().url(postURL).post(requestBody).build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            num_val.setText(response.body().string());
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                });
//            }
//        });
//    }

    public void post() {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = "{\"fromStation\": \"" +startFrom_val.getText().toString()+ "\", " +
                "\"toStation\": \"" +endFrom_val.getText().toString()+ "\", " +
                "\"journeyDate\": \"" +date_val.getText().toString()+ "\", " +
                "\"noOfTickets\": \"" +noOfTickets_val.getText().toString()+ "\", " +
                "\"ticketclass\": \"" +trainClass_val.getText().toString()+ "\"}";

        RequestBody requestBody = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(postURL)
                .post(requestBody)
                .addHeader("accept", "text/plain")
                .addHeader("Content-Type", "application/json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String responseBody = response.body().string();
//                            num_val.setText(responseBody);
                            Toast.makeText(Summary.this, "Successfully Confirmed!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Summary.this, Main_Menu.class));
                        } catch (IOException e) {
                            Toast.makeText(Summary.this, "Error occurred in Confirmation!", Toast.LENGTH_SHORT).show();
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }

}