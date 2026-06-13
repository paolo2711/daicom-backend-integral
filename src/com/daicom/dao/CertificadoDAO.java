package com.daicom.dao;

import com.daicom.modelos.Certificado;
import com.daicom.modelos.Cliente;
import com.daicom.modelos.Laboratorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CertificadoDAO {

    public List<Certificado> listarCertificados() {
        List<Certificado> lista = new ArrayList<>();
        String sql = "SELECT c.id, c.correlative, c.certificate_type, c.equipment, c.status, " +
                     "cl.id as client_id, cl.name as client_name, " +
                     "l.id as lab_id, l.name as lab_name, " +
                     "o.order_number " +
                     "FROM certificates_certificate c " +
                     "LEFT JOIN clients_client cl ON c.client_id = cl.id " +
                     "LEFT JOIN labs_lab l ON c.lab_id = l.id " +
                     "LEFT JOIN orders_order o ON c.order_id = o.id " +
                     "WHERE c.deleted = 0 ORDER BY c.id DESC LIMIT 50";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Certificado cert = new Certificado();
                cert.setId(rs.getInt("id"));
                
                String correlative = rs.getString("correlative");
                String registryCode = "2026-" + String.format("%08d", Integer.parseInt(correlative != null ? correlative : "0"));
                cert.setRegistryCode(registryCode);
                
                cert.setCertificateType(rs.getInt("certificate_type"));
                cert.setEquipment(rs.getString("equipment"));
                cert.setStatus(rs.getInt("status"));
                cert.setOrderNumber(rs.getString("order_number"));

                if (rs.getObject("client_id") != null) {
                    cert.setClient(new Cliente(rs.getInt("client_id"), rs.getString("client_name")));
                }

                if (rs.getObject("lab_id") != null) {
                    cert.setLab(new Laboratorio(rs.getInt("lab_id"), rs.getString("lab_name")));
                }

                lista.add(cert);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}