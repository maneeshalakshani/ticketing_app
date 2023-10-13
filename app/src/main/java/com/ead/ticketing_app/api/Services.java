package com.ead.ticketing_app.api;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.ead.ticketing_app.custom_classes.CustomTrustManager;

import java.io.IOException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
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
}
