package com.daicom.utilidades;

import com.daicom.dao.LogDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditoriaLog {
    
    private static final Logger logger = LoggerFactory.getLogger(AuditoriaLog.class);
    private static final LogDAO logDAO = new LogDAO();

    public static void registrarAccion(String usuario, String accion, String detalle) {
        
        logger.info("USUARIO: {} | ACCION: {} | DETALLE: {}", usuario, accion, detalle);
        
        
        logDAO.insertarLog(usuario, accion, detalle);
    }
    
    public static void registrarError(String origen, String error) {
        logger.error("ORIGEN: {} | ERROR: {}", origen, error);
    }
}