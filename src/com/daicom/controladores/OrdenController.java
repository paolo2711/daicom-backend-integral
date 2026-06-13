package com.daicom.controladores;

import com.daicom.modelos.Certificado;
import com.daicom.modelos.Orden;
import com.daicom.servicios.OrdenService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class OrdenController implements HttpHandler {
    
    private final OrdenService service;

    public OrdenController() {
        this.service = new OrdenService();
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

        List<Orden> lista = service.obtenerOrdenes();
        String jsonResponse = convertirAJson(lista);

        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        byte[] responseBytes = jsonResponse.getBytes("UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

    private String convertirAJson(List<Orden> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lista.size(); i++) {
            Orden o = lista.get(i);
            sb.append("{")
              .append("\"id\":").append(o.getId()).append(",")
              .append("\"order_number\":\"").append(o.getOrderNumber() != null ? o.getOrderNumber() : "").append("\",")
              .append("\"order_type\":").append(o.getOrderType()).append(",")
              .append("\"status\":").append(o.getStatus()).append(",")
              .append("\"billed\":").append(o.isBilled()).append(",");

            if (o.getClient() != null) {
                sb.append("\"client_data\":{\"id\":").append(o.getClient().getId())
                  .append(",\"name\":\"").append(o.getClient().getName().replace("\"", "\\\"")).append("\"},");
            } else {
                sb.append("\"client_data\":null,");
            }

            sb.append("\"certificates\":[");
            List<Certificado> certs = o.getCertificates();
            if (certs != null) {
                for (int j = 0; j < certs.size(); j++) {
                    sb.append("{\"id\":").append(certs.get(j).getId())
                      .append(",\"status\":").append(certs.get(j).getStatus()).append("}");
                    if (j < certs.size() - 1) sb.append(",");
                }
            }
            sb.append("],\"payments\":[],\"rentals\":[]}");
            
            if (i < lista.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}