package com.daicom.modelos;
import java.util.List;

public class Orden {
    private int id;
    private String orderNumber;
    private int orderType;
    private int status;
    private boolean billed;
    private Cliente client;
    private List<Certificado> certificates;

    public Orden() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public int getOrderType() { return orderType; }
    public void setOrderType(int orderType) { this.orderType = orderType; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public boolean isBilled() { return billed; }
    public void setBilled(boolean billed) { this.billed = billed; }

    public Cliente getClient() { return client; }
    public void setClient(Cliente client) { this.client = client; }

    public List<Certificado> getCertificates() { return certificates; }
    public void setCertificates(List<Certificado> certificates) { this.certificates = certificates; }
}