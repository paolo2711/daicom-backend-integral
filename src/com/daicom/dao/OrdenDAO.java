package com.daicom.dao;

import com.daicom.modelos.Certificado;
import com.daicom.modelos.Cliente;
import com.daicom.modelos.Orden;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdenDAO {

    public List<Orden> listarOrdenes() {
        List<Orden> lista = new ArrayList<>();
        String sql = "SELECT o.id, o.order_number, o.order_type, o.status, o.billed, " +
                     "cl.id as client_id, cl.name as client_name " +
                     "FROM orders_order o " +
                     "LEFT JOIN clients_client cl ON o.client_id = cl.id " +
                     "WHERE o.deleted = 0 ORDER BY o.id DESC LIMIT 50";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Orden orden = new Orden();
                orden.setId(rs.getInt("id"));
                orden.setOrderNumber(rs.getString("order_number"));
                orden.setOrderType(rs.getInt("order_type"));
                orden.setStatus(rs.getInt("status"));
                orden.setBilled(rs.getBoolean("billed"));

                if (rs.getObject("client_id") != null) {
                    orden.setClient(new Cliente(rs.getInt("client_id"), rs.getString("client_name")));
                }

                orden.setCertificates(obtenerCertificadosPorOrden(orden.getId(), conn));
                lista.add(orden);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    private List<Certificado> obtenerCertificadosPorOrden(int orderId, Connection conn) {
        List<Certificado> certificados = new ArrayList<>();
        String sql = "SELECT id, status FROM certificates_certificate WHERE deleted = 0 AND order_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Certificado cert = new Certificado();
                    cert.setId(rs.getInt("id"));
                    cert.setStatus(rs.getInt("status"));
                    certificados.add(cert);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certificados;
    }
}