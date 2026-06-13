package com.daicom.controladores;

import com.daicom.dao.LogDAO;
import com.daicom.modelos.Log;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class LogController implements HttpHandler {
    
    private final LogDAO logDAO;

    public LogController() {
        this.logDAO = new LogDAO();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        List<Log> lista = logDAO.listarLogs();
        String jsonResponse = convertirAJson(lista);

        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        byte[] responseBytes = jsonResponse.getBytes("UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

    private String convertirAJson(List<Log> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"count\":").append(lista.size()).append(",\"results\":[");
        for (int i = 0; i < lista.size(); i++) {
            Log l = lista.get(i);
            sb.append("{")
              .append("\"id\":").append(l.getId()).append(",")
              .append("\"action\":\"").append(l.getAction()).append("\",")
              .append("\"description\":\"").append(l.getDescription()).append("\",")
              .append("\"log_date\":\"").append(l.getLog_date()).append("\",")
              .append("\"log_time\":\"").append(l.getLog_time()).append("\",")
              .append("\"user_data\":{\"username\":\"").append(l.getUsername()).append("\"}")
              .append("}");
            if (i < lista.size() - 1) sb.append(",");
        }
        sb.append("]}");
        return sb.toString();
    }
}