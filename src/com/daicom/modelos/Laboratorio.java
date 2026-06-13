package com.daicom.modelos;

public class Laboratorio {
    private int id;
    private String name;

    public Laboratorio(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
}