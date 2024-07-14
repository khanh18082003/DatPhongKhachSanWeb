package com.webspringmvc.controller.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PaymentHistoryChecker {

    private static final String API_URL = "https://my.sepay.vn/userapi/transactions/list";
    private static final String ACCOUNT_NUMBER = "0396441431";
    private static final int LIMIT = 20;
    private static final String AUTHORIZATION_TOKEN = "Bearer CJPN4H68I7XSWHVPGNJ5CYU6H3UVZR24WS5NDT97EV0CXBFUXPFTLGACOM9IIQ1A";

    public boolean checkPaymentStatus() throws IOException {
        // Xây dựng URL với các tham số cần thiết
        String apiUrl = API_URL + "?account_number=" + ACCOUNT_NUMBER + "&limit=" + LIMIT;
        URL url = new URL(apiUrl);

        // Mở kết nối HTTP
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", AUTHORIZATION_TOKEN);
        connection.setRequestProperty("Content-Type", "application/json");

        // Đọc dữ liệu trả về
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String jsonResponse = response.toString();
        
        System.out.println(jsonResponse);
        return isPaymentSuccessful(jsonResponse); 
    }

    private boolean isPaymentSuccessful(String jsonResponse) {
        return jsonResponse.contains(BookRoomController.content);
    }

    public static void main(String[] args) throws IOException {
        PaymentHistoryChecker checker = new PaymentHistoryChecker();
        boolean paymentSuccessful = checker.checkPaymentStatus();
        System.out.println("Is payment successful? " + paymentSuccessful);
    }
}

