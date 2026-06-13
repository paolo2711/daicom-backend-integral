package com.daicom.controladores;

import com.daicom.modelos.Certificado;
import com.daicom.servicios.CertificadoService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CertificadoController implements HttpHandler {
    
    private final CertificadoService service;

    public CertificadoController() {
        this.service = new CertificadoService();
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

        List<Certificado> lista = service.obtenerCertificados();
        String jsonResponse = convertirAJson(lista);

        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        byte[] responseBytes = jsonResponse.getBytes("UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

    private String convertirAJson(List<Certificado> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lista.size(); i++) {
            Certificado c = lista.get(i);
            sb.append("{")
              .append("\"id\":").append(c.getId()).append(",")
              .append("\"registry_code\":\"").append(c.getRegistryCode() != null ? c.getRegistryCode() : "").append("\",")
              .append("\"certificate_type\":").append(c.getCertificateType()).append(",")
              .append("\"equipment\":\"").append(c.getEquipment() != null ? c.getEquipment().replace("\"", "\\\"") : "").append("\",")
              .append("\"status\":").append(c.getStatus()).append(",");

            if (c.getClient() != null) {
                sb.append("\"client_data\":{\"id\":").append(c.getClient().getId())
                  .append(",\"name\":\"").append(c.getClient().getName().replace("\"", "\\\"")).append("\"},");
            } else {
                sb.append("\"client_data\":null,");
            }

            if (c.getLab() != null) {
                sb.append("\"lab_data\":{\"id\":").append(c.getLab().getId())
                  .append(",\"name\":\"").append(c.getLab().getName().replace("\"", "\\\"")).append("\"},");
            } else {
                sb.append("\"lab_data\":null,");
            }

            sb.append("\"order_number\":").append(c.getOrderNumber() != null ? "\"" + c.getOrderNumber() + "\"" : "null");
            sb.append("}");
            
            if (i < lista.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}