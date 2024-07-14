package com.webspringmvc.controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/PaymentStatusServlet")
public class PaymentUpdatesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final List<PrintWriter> clients = new CopyOnWriteArrayList<>();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();
        clients.add(out);

        while (true) {
            try {
                PaymentHistoryChecker checker = new PaymentHistoryChecker();
                boolean paymentSuccess = checker.checkPaymentStatus();
                if (paymentSuccess) {
                	JSONObject json = new JSONObject();
                    json.put("status", "success");
                    out.print(json.toString());
                    out.flush();
                    System.out.println("Thành công");
                    break;
                }
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        clients.removeIf(out -> out.checkError());
    }
}
