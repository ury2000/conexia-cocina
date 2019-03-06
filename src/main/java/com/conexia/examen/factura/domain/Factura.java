package com.conexia.examen.factura.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the i18n database table.
 */
@Entity
@Table(name = "factura")
@NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_factura", unique = true, nullable = false)
    @SequenceGenerator(name = "factura_seq_generator", sequenceName = "factura_id_factura_seq",allocationSize = 100)
    @GeneratedValue(generator = "factura_seq_generator", strategy = GenerationType.SEQUENCE)
    private Integer idFactura;

    @Temporal(TemporalType.DATE)
    @Past
    @NotNull
    @Column(name = "fecha_factura", nullable = false)
    private Date fechaFactura;

    //bi-directional many-to-one association to DetalleFactura
    @NotEmpty
    @OneToMany(mappedBy = "id.factura", cascade = CascadeType.ALL)
    private List<DetalleFactura> detalleFacturas;

    //bi-directional many-to-one association to Camarero
    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_camarero", nullable = false)
    private Camarero camarero;

    //bi-directional many-to-one association to Cliente
    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    //bi-directional many-to-one association to Mesa
    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;

    public Factura() {
        this.cliente = new Cliente();
        this.mesa = new Mesa();
        this.camarero = new Camarero();
        this.detalleFacturas = new ArrayList<>();
        this.fechaFactura = new Date();
    }

    public Integer getIdFactura() {
        return this.idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaFactura() {
        return this.fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public List<DetalleFactura> getDetalleFacturas() {
        return this.detalleFacturas;
    }

    public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
        this.detalleFacturas = detalleFacturas;
    }

    public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
        getDetalleFacturas().add(detalleFactura);
        detalleFactura.setFactura(this);

        return detalleFactura;
    }

    public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
        getDetalleFacturas().remove(detalleFactura);
        detalleFactura.setFactura(null);

        return detalleFactura;
    }

    public Camarero getCamarero() {
        return this.camarero;
    }

    public void setCamarero(Camarero camarero) {
        this.camarero = camarero;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return this.mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return Objects.equals(idFactura, factura.idFactura) &&
                Objects.equals(fechaFactura, factura.fechaFactura) &&
                Objects.equals(camarero, factura.camarero) &&
                Objects.equals(cliente, factura.cliente) &&
                Objects.equals(mesa, factura.mesa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFactura, fechaFactura, camarero, cliente, mesa);
    }
}