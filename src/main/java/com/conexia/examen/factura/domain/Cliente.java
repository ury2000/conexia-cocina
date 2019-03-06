package com.conexia.examen.factura.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the cliente database table.
 */
@Entity
@Table(name = "cliente")
@NamedQuery(name = "Clientes.MasImporte",
        query = "SELECT c.nombre, concat(c.apellido1, concat(' ',c.apellido2)) as apellidos, sum(df.importe) " +
                "FROM Factura f Join f.cliente c Join f.detalleFacturas df " +
                "group by c.nombre, c.apellido1, c.apellido2 having sum(df.importe) > :importe")
public class Cliente extends AbstractPerson implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_cliente", unique = true, nullable = false)
    private Long idCliente;

    @Column
    private String obervaciones;

    //bi-directional many-to-one association to Factura
    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;

    @Override
    @Transient
    public Long getId() {
        return this.getIdCliente();
    }

    @Override
    public void setId(Long id) {
        this.setIdCliente(id);
    }

    public Long getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getObervaciones() {
        return this.obervaciones;
    }

    public void setObervaciones(String obervaciones) {
        this.obervaciones = obervaciones;
    }

    public List<Factura> getFacturas() {
        return this.facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Factura addFactura(Factura factura) {
        getFacturas().add(factura);
        factura.setCliente(this);

        return factura;
    }

    public Factura removeFactura(Factura factura) {
        getFacturas().remove(factura);
        factura.setCliente(null);

        return factura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(idCliente, cliente.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }

//    @Override
//    public String toString() {
//        return String.format("Cliente[%d]", getId());
//    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                '}';
    }
}