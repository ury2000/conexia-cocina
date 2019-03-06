package com.conexia.examen.factura.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the mesa database table.
 */
@Entity
@Table(name = "mesa")
@NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m")
@SequenceGenerator(name = "mesa_generator", sequenceName = "mesa_id_mesa_seq")
public class Mesa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mesa_generator")
    @Column(name = "id_mesa", unique = true, nullable = false)
    private Long idMesa;

    @Column(name = "num_max_comensales", nullable = false)
    private Integer numMaxComensales;

    @Column(length = 2147483647)
    private String ubicacion;

    //bi-directional many-to-one association to Factura
    @OneToMany(mappedBy = "mesa")
    private List<Factura> facturas;

    public Mesa() {
    }

    public Long getIdMesa() {
        return this.idMesa;
    }

    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }

    public Integer getNumMaxComensales() {
        return this.numMaxComensales;
    }

    public void setNumMaxComensales(Integer numMaxComensales) {
        this.numMaxComensales = numMaxComensales;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Factura> getFacturas() {
        return this.facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Factura addFactura(Factura factura) {
        getFacturas().add(factura);
        factura.setMesa(this);

        return factura;
    }

    public Factura removeFactura(Factura factura) {
        getFacturas().remove(factura);
        factura.setMesa(null);

        return factura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesa mesa = (Mesa) o;
        return Objects.equals(idMesa, mesa.idMesa) &&
                Objects.equals(numMaxComensales, mesa.numMaxComensales) &&
                Objects.equals(ubicacion, mesa.ubicacion) &&
                Objects.equals(facturas, mesa.facturas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMesa, numMaxComensales, ubicacion, facturas);
    }
}