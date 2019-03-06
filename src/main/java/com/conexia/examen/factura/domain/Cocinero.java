package com.conexia.examen.factura.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * The persistent class for the cocinero database table.
 */
@Entity
@Table(name = "cocinero")
@NamedQuery(name = "Cocinero.findAll", query = "SELECT c FROM Cocinero c")
public class Cocinero extends AbstractPerson implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_cocinero", unique = true, nullable = false)
    private Long idCocinero;


    @Override
    @Transient
    public Long getId() {
        return getIdCocinero();
    }

    @Override
    public void setId(Long id) {
        this.setIdCocinero(id);
    }

    public Long getIdCocinero() {
        return this.idCocinero;
    }

    public void setIdCocinero(Long idCocinero) {
        this.idCocinero = idCocinero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocinero cocinero = (Cocinero) o;
        return Objects.equals(idCocinero, cocinero.idCocinero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCocinero);
    }

    @Override
    public String toString() {
        return "Cocinero{" +
                "idCocinero=" + idCocinero +
                '}';
    }
}