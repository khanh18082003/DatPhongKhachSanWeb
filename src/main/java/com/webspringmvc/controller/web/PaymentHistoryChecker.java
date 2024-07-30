package com.webspringmvc.controller.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.webspringmvc.entity.Setting;

public class PaymentHistoryChecker {

    private static final String API_URL = "https://my.sepay.vn/userapi/transactions/list?";
    // https://my.sepay.vn/userapi/transactions/list?transaction_date_min=2023-04-30 08:00:00
    private static final String AUTHORIZATION_TOKEN = "Bearer CJPN4H68I7XSWHVPGNJ5CYU6H3UVZR24WS5NDT97EV0CXBFUXPFTLGACOM9IIQ1A";
    @Autowired
    Setting setting;
    public boolean checkPaymentStatus() throws IOException {
        // Xây dựng URL với các tham số cần thiết
    	// Lấy ngày  hiện tại
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);
        // + "?account_number=" + setting.getAccountNumber()
        String apiUrl = API_URL + "transaction_date_min=" + formattedDate;
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
        
     // Chuyển đổi chuỗi JSON thành đối tượng JSONObject
        JSONObject jsonObject = new JSONObject(jsonResponse);

        JSONArray transactions = jsonObject.getJSONArray("transactions");

        JSONObject transaction;
        String transactionContent;
        String amountIn;
     // Duyệt qua tất cả các giao dịch
        for (int i = 0; i < transactions.length(); i++) {
        	transaction = transactions.getJSONObject(i);

        	transactionContent = transaction.getString("transaction_content");
        	amountIn = transaction.getString("amount_in");

            System.out.println(transactionContent + " " + amountIn);
			if (isPaymentSuccessful(jsonResponse, (int)Float.parseFloat(amountIn))) return true;
        }
        return false;
    }

    private boolean isPaymentSuccessful(String jsonResponse, int amountIn) {
        return jsonResponse.contains(BookRoomController.content) && amountIn == BookRoomController.amount;
    }

    public static void main(String[] args) throws IOException {
        PaymentHistoryChecker checker = new PaymentHistoryChecker();
        boolean paymentSuccessful = checker.checkPaymentStatus();
        System.out.println("Is payment successful? " + paymentSuccessful);
    }
}

