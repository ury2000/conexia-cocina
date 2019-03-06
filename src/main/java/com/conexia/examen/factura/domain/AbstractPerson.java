package com.conexia.examen.factura.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractPerson implements Serializable, IPerson {

    protected String nombre, apellido1, apellido2;

    @NotNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @NotNull
    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    @NotNull
    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    @Transient
    public String getApellidos() {
        return this.getApellido1() + " " + this.getApellido2();
    }

    @Transient
    public String getFullName() {
        return this.getNombre() + " " + getApellidos();
    }
}
