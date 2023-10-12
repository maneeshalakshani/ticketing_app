package com.ead.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.ead.ticketing_app.api.Services;

public class Your_Reservations extends AppCompatActivity {

    String DataResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_reservations);

        // Execute the network request asynchronously
//        new FetchDataAsyncTask().execute();
    }

//    private class FetchDataAsyncTask extends AsyncTask<Void, Void, String> {
//        @Override
//        protected String doInBackground(Void... voids) {
//            Services api = new Services();
//            return api.fetchData();
//        }
//
//        @Override
//        protected void onPostExecute(String response) {
//            if (response != null) {
//                // Handle the successful result here
//                DataResponse = response;
//                Log.d("TEST>>>>", response);
//            } else {
//                // Handle the error case here
//                Log.e("ERROR>>>>", "Failed to fetch data");
//            }
//        }
//    }
}