package com.conexia.examen.factura.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * The persistent class for the camarero database table.
 */
@Entity
@Table(name = "camarero")
@NamedQuery(name = "Camarero.findAll", query = "SELECT c FROM Camarero c")
public class Camarero extends AbstractPerson implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_camarero", unique = true, nullable = false)
    private Long idCamarero;

    public Camarero() {
        this.nombre = "";
        this.apellido1 = "";
        this.apellido2 = "";
    }

    @Override
    public Long getId() {
        return this.getIdCamarero();
    }

    @Override
    public void setId(Long id) {
        this.setIdCamarero(id);
    }

    public Long getIdCamarero() {
        return this.idCamarero;
    }

    public void setIdCamarero(Long idCamarero) {
        this.idCamarero = idCamarero;
    }

    @Override
    public String toString() {
        return "Camarero{" +
                "idCamarero=" + idCamarero +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camarero camarero = (Camarero) o;
        return Objects.equals(idCamarero, camarero.idCamarero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCamarero);
    }
}