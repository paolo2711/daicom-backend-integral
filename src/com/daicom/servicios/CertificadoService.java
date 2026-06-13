package com.daicom.servicios;

import com.daicom.dao.CertificadoDAO;
import com.daicom.modelos.Certificado;
import com.daicom.utilidades.AuditoriaLog;
import java.util.List;
import java.util.stream.Collectors;

public class CertificadoService {
    
    private final CertificadoDAO certificadoDAO;

    public CertificadoService() {
        this.certificadoDAO = new CertificadoDAO();
    }

    public List<Certificado> obtenerCertificados() {
        AuditoriaLog.registrarAccion("Sistema", "LECTURA", "Consulta general de certificados");
        return certificadoDAO.listarCertificados();
    }

    public List<Certificado> filtrarPorEstado(int estado) {
        List<Certificado> todos = certificadoDAO.listarCertificados();
        return todos.stream()
                    .filter(c -> c.getStatus() == estado)
                    .collect(Collectors.toList());
    }
}