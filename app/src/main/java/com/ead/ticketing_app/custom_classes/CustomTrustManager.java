package com.ead.ticketing_app.custom_classes;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class CustomTrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
        // Accept all client certificates
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        // Accept all server certificates
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
