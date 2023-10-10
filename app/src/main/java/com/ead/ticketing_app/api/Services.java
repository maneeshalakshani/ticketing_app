package com.ead.ticketing_app.api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Services {

    //initializing api url
    private static final String API_URL = "https://jsonplaceholder.typicode.com/todos/";

    public String fetchData() {
        OkHttpClient client = new OkHttpClient();

        //generate request
        Request request = new Request.Builder()
                .url(API_URL)
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
}
