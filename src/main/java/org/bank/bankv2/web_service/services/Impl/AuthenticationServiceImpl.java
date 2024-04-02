package org.bank.bankv2.web_service.services.Impl;

import org.bank.bankv2.web_service.models.authentication.UserAuth;
import org.bank.bankv2.web_service.services.AuthenticationService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Override
    public String authenticate(UserAuth userAuth) throws IOException {

        String url = "http://localhost:3000/auth/login";
        String postData = "{\"email\":\"" + userAuth.getEmail() + "\",\"password\":\"" + userAuth.getPassword() + "\"}";
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
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response : " + response.toString());
                return response.toString();
            } catch (Exception err) {
                System.out.println(err.getMessage());
                System.out.println(Arrays.toString(err.getStackTrace()));
                return "error";
            }
        } else {
            System.out.println("La requête a échoué avec le code : " + responseCode);
            return "error";
        }
    }
}

