package com.conexia.examen.factura.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * The primary key class for the detalle_factura database table.
 */
@Embeddable
public class DetalleFacturaPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;


    //----------------------- equals used only
//    @Column(name = "id_factura", insertable = false, updatable = false, unique = true, nullable = false)
//    private Integer idFactura;

    //bi-directional many-to-one association to Factura
    @ManyToOne(cascade = CascadeType.ALL)
    private Factura factura;

    @Column(name = "id_detalle_factura", nullable = false)
    private Long idDetalleFactura;

    public DetalleFacturaPK() {
        factura = new Factura();
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }


//    public Integer getIdFactura() {
//        return this.idFactura;
//    }
//
//    public void setIdFactura(Integer idFactura) {
//        this.idFactura = idFactura;
//    }

    public Long getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(Long idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleFacturaPK that = (DetalleFacturaPK) o;
        return Objects.equals(factura, that.factura) &&
                Objects.equals(idDetalleFactura, that.idDetalleFactura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factura, idDetalleFactura);
    }
}