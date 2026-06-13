package com.daicom.servicios;

import com.daicom.dao.OrdenDAO;
import com.daicom.modelos.Orden;
import com.daicom.utilidades.AuditoriaLog;
import java.util.List;

public class OrdenService {
    
    private final OrdenDAO ordenDAO;

    public OrdenService() {
        this.ordenDAO = new OrdenDAO();
    }

    public List<Orden> obtenerOrdenes() {
        AuditoriaLog.registrarAccion("Sistema", "LECTURA", "Consulta general de órdenes");
        return ordenDAO.listarOrdenes();
    }
}