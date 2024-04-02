//package org.bank.bankv2.web_service.services.Impl;
//
//import org.bank.bankv2.web_service.models.authentication.User;
//import org.bank.bankv2.web_service.services.UserService;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Arrays;
//
//public class UserServiceImple implements UserService {
//    @Override
//    public boolean create(User userCreate) throws IOException {
//        String url = "http://localhost:3000/users";
//        String postData = "{ \"name\":\"" + userCreate.getName() + "\", \"email\":\"" + userCreate.getEmail() + "\",\"password\":\"" + userCreate.getPassword() + "\"}";
//        URL requestUrl = new URL(url);
//        HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
//
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setDoOutput(true);
//
//        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
//            wr.writeBytes(postData);
//            wr.flush();
//        }
//
//
//        int responseCode = conn.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_CREATED) {
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//                System.out.println("Réponse : " + response.toString());
//                return true;
//            } catch (Exception err) {
//                System.out.println(err.getMessage());
//                System.out.println(Arrays.toString(err.getStackTrace()));
//                return false;
//            }
//        } else {
//            System.out.println("La requête a échoué avec le code : " + responseCode);
//            return false;
//        }    }
//}
