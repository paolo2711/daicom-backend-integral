package com.daicom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB { 
    
    // Cambia el nombre de la BD si en tu PC se llama distinto (ej. "daicom_db")
    private static final String URL = "jdbc:mysql://localhost:3306/daicom_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Pon tu clave de MySQL aquí

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Carga el driver que descargaste (mysql-connector-j.jar)
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos de DAICOM.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
        }
        return connection;
    }
}