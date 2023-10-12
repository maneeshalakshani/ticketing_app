package com.ead.ticketing_app.api;

import com.ead.ticketing_app.custom_classes.CustomTrustManager;

import java.io.IOException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Services {

    //initializing api url
    private static final String API_URL = "https://10.0.2.2:7104/api/";

    public OkHttpClient createCustomOkHttpClient() {
        try {
            // Create a custom TrustManager that accepts all certificates
            X509TrustManager customTrustManager = new CustomTrustManager();

            // Initialize SSLContext with the custom TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{customTrustManager}, null);

            // Create an OkHttpClient that uses the custom TrustManager
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory(), customTrustManager)
                    .hostnameVerifier((hostname, session) -> true);

            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return new OkHttpClient(); // Return a default client if an error occurs
        }
    }


    public String fetchData() {
        OkHttpClient client = createCustomOkHttpClient();

        //generate request
        Request request = new Request.Builder()
                .url(API_URL + "Booking")
                .build();

        try {
            //getting responses
            Response response = client.newCall(request).execute();

            //check responses
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                // Handle the error response
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public void addBooking() {
//        OkHttpClient client = createCustomOkHttpClient();
//
//        // Define the POST request body (e.g., form data)
//        RequestBody requestBody = new FormBody.Builder()
//                .add("fromStation", "Colombo")
//                .add("toStation", "Matara")
//                .add("journeyDate", "12/06/2023")
//                .add("noOfTickets", "12")
//                .add("ticketclass", "class 1")
//                .build();
//
//        Request request = new Request.Builder()
//                .url(API_URL + "Booking/addBooking/")
//                .post(requestBody)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String responseBody = response.body().string();
//                    // Handle the successful response
//                    Log.d("Response:", responseBody);
//                } else {
//                    Log.e("Error:", "Failed to send POST request");
//                }
//            }
//        });
//    }

    public String addBooking() throws IOException {
        OkHttpClient client = createCustomOkHttpClient();

        // Define the JSON request body
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonBody = "{"
                + "\"fromStation\":\"Colombo\","
                + "\"toStation\":\"Matara\","
                + "\"journeyDate\":\"12/06/2023\","
                + "\"noOfTickets\":12,"
                + "\"ticketclass\":\"1\""
                + "}";
        RequestBody requestBody = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url("https://10.0.2.2:7104/api/Booking/addBooking")
                .addHeader("accept", "text/plain")
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String responseBody = response.body().string();
//                    // Handle the successful response
//                    Log.d("Response:", responseBody);
//                } else {
//                    Log.e("Error:", "Failed to send POST request");
//                }
//            }
//        });
    }

}
