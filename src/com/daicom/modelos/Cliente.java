package com.daicom.modelos;

public class Cliente {
    private int id;
    private String name;

    public Cliente(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
}