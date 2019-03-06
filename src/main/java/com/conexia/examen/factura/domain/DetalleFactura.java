package com.conexia.examen.factura.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle_factura database table.
 */
@Entity
@Table(name = "detalle_factura")
@AssociationOverrides({
        @AssociationOverride(name = "id.factura",
                joinColumns = @JoinColumn(name = "id_factura"))})
@NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d")
public class DetalleFactura implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DetalleFacturaPK id;

    @Column(nullable = false, precision = 131089)
    private BigDecimal importe;

    @Column(nullable = false, length = 2147483647)
    private String plato;

    //bi-directional many-to-one association to Cocinero
    @ManyToOne
    @JoinColumn(name = "id_cocinero", nullable = false)
    private Cocinero cocinero;

    public DetalleFactura() {
        importe = BigDecimal.ZERO;
        this.id = new DetalleFacturaPK();
    }

    public DetalleFacturaPK getId() {
        return this.id;
    }

    public void setId(DetalleFacturaPK id) {
        this.id = id;
    }

    public BigDecimal getImporte() {
        return this.importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getPlato() {
        return this.plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public Cocinero getCocinero() {
        return this.cocinero;
    }

    public void setCocinero(Cocinero cocinero) {
        this.cocinero = cocinero;
    }

    public Factura getFactura() {
        return this.id.getFactura();
    }

    public void setFactura(Factura factura) {
        this.id.setFactura(factura);
    }

    @PrePersist
    public void generateID(){
        this.getId().setIdDetalleFactura(System.currentTimeMillis());
    }

}