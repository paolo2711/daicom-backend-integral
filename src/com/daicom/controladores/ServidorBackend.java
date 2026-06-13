package com.daicom;

import com.daicom.controladores.CertificadoController;
import com.daicom.controladores.OrdenController;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class ServidorBackend {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
            
            server.createContext("/api/certificates", new CertificadoController());
            server.createContext("/api/orders", new OrdenController());
            
            server.setExecutor(null);
            server.start();
            
            System.out.println("==================================================");
            System.out.println(" SERVIDOR DAICOM - ARQUITECTURA MVC INICIADA");
            System.out.println("==================================================");
            System.out.println("Puerto HTTP: 9000");
            System.out.println("Módulos activos: Certificados, Órdenes, Auditoría Logback");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}