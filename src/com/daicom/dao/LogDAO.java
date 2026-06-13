package com.daicom.dao;

import com.daicom.modelos.Log;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LogDAO {

    public void insertarLog(String username, String action, String description) {
       
        
        System.out.println("--> [BD SIMULADA] INSERT INTO logs_log: " + username + " | " + action + " | " + description);
    }

    public List<Log> listarLogs() {
        List<Log> lista = new ArrayList<>();
        
        String fechaHoy = LocalDate.now().toString();
        String horaActual = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        
        Log l1 = new Log();
        l1.setId(101);
        l1.setAction("ACCESO");
        l1.setDescription("Inicio de sesión en el sistema");
        l1.setLog_date(fechaHoy);
        l1.setLog_time(horaActual);
        l1.setUsername("admin");
        
        lista.add(l1);
        return lista;
    }
}