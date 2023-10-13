package com.ead.ticketing_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ead.ticketing_app.api.Services;
import com.ead.ticketing_app.custom_classes.ReservationClass;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Your_Reservations extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReservationAdapter adapter;
    private List<ReservationClass> reservations;
    Services services = new Services();
    OkHttpClient okHttpClient = services.createCustomOkHttpClient();

    String GET_URL = "https://10.0.2.2:7104/api/Booking";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_reservations);

        recyclerView = findViewById(R.id.reservationList);
        reservations = new ArrayList<>();

        // Initialize and set up the adapter
        adapter = new ReservationAdapter(this, reservations);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Fetch data using the GET request and update the RecyclerView when data is available
        fetchDataFromServer();
    }

    private void fetchDataFromServer() {
        Request request = new Request.Builder().url(GET_URL).build();
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
                            String data = response.body().string();
                            Log.d("SUMMARY", "Result: " + data);

                            // Parse the data and create ReservationClass objects
                            List<ReservationClass> parsedReservations = parseData(data);

                            // Update the 'reservations' list and notify the adapter
                            reservations.clear();
                            reservations.addAll(parsedReservations);
                            adapter.notifyDataSetChanged();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }

    // Parse the data and create ReservationClass objects
    private List<ReservationClass> parseData(String data) {
        List<ReservationClass> parsedReservations = new ArrayList<>();

        try {
            // Create a Gson instance
            Gson gson = new Gson();

            // Parse the JSON response as an array
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(data).getAsJsonArray();

            // Loop through the JSON array and create ReservationClass objects
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String startFrom = jsonObject.get("fromStation").getAsString();
                String endFrom = jsonObject.get("toStation").getAsString();
                String date = jsonObject.get("journeyDate").getAsString();
                String noOfTickets = jsonObject.get("noOfTickets").getAsString();
                String trainClass = jsonObject.get("ticketclass").getAsString();

                Log.d("SUMMARY", "fromStation: " + startFrom);
                Log.d("SUMMARY", "endFrom: " + endFrom);
                Log.d("SUMMARY", "date: " + date);
                Log.d("SUMMARY", "noOfTickets: " + noOfTickets);
                Log.d("SUMMARY", "trainClass: " + trainClass);

                // Calculate the 'price' based on 'ticketclass' value
                int numTickets = Integer.parseInt(noOfTickets);
                int price;
                switch (trainClass) {
                    case "Class 1":
                        price = 1000 * numTickets;
                        break;
                    case "Class 2":
                        price = 600 * numTickets;
                        break;
                    case "Class 3":
                        price = 300 * numTickets;
                        break;
                    default:
                        price = 0; // Handle unknown ticket classes here
                }

                // Create a ReservationClass object with the extracted data
                ReservationClass reservation = new ReservationClass(startFrom, endFrom, date, noOfTickets, trainClass, String.valueOf(price));

                parsedReservations.add(reservation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parsedReservations;
    }
}