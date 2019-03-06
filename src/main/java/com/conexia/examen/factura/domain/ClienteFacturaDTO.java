package com.conexia.examen.factura.domain;

import java.math.BigDecimal;

public class ClienteFacturaDTO {
    private String nombre, apellidos;
    private BigDecimal importe;

    public ClienteFacturaDTO(String nombre, String apellidos, BigDecimal importe) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.importe = importe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
}
