package org.bank.bankv2.web_service.services.Impl;

import org.bank.bankv2.web_service.authentication.UserAuth;
import org.bank.bankv2.web_service.services.AuthenticationService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Override
    public boolean authenticate(UserAuth userAuth) throws IOException {

        String url = "http://localhost:3000/generate-token";
        String postData = "{\"username\":\"" + userAuth.getUsername() + "\",\"password\":\"" + userAuth.getPassword() + "\"}";
        URL requestUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.writeBytes(postData);
            wr.flush();
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Réponse : " + response.toString());
                return true;
            }
        } else {
            System.out.println("La requête a échoué avec le code : " + responseCode);
            return false;
        }
    }
}

