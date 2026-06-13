package com.daicom.modelos;

public class Log {
    private int id;
    private String action;
    private String description;
    private String log_date;
    private String log_time;
    private String username; 

    // Constructor vacío
    public Log() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLog_date() { return log_date; }
    public void setLog_date(String log_date) { this.log_date = log_date; }

    public String getLog_time() { return log_time; }
    public void setLog_time(String log_time) { this.log_time = log_time; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}