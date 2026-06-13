package com.daicom.modelos;

public class Certificado {
    private int id;
    private String registryCode;
    private int certificateType;
    private String equipment;
    private int status;
    private Cliente client;
    private Laboratorio lab;
    private String orderNumber;

    // Constructor vacío
    public Certificado() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRegistryCode() { return registryCode; }
    public void setRegistryCode(String registryCode) { this.registryCode = registryCode; }

    public int getCertificateType() { return certificateType; }
    public void setCertificateType(int certificateType) { this.certificateType = certificateType; }

    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public Cliente getClient() { return client; }
    public void setClient(Cliente client) { this.client = client; }

    public Laboratorio getLab() { return lab; }
    public void setLab(Laboratorio lab) { this.lab = lab; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
}