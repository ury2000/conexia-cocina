package com.conexia.examen.factura.domain;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Immutable
@Table(name = "v_camareros_facturas_mes", schema = "public", catalog = "factura")
@NamedQuery(name = "VCamarerosMes.findAll", query = "SELECT v FROM VCamarerosFacturasMes v")
public class VCamarerosFacturasMes implements Serializable {
    private String fullName;
    private Integer mes;
    private BigDecimal sum = BigDecimal.ZERO;
    private Long rowNumber;

    @Id
    @Column(name = "row_number")
    public Long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "mes")
    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    @Basic
    @Column(name = "sum")
    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VCamarerosFacturasMes that = (VCamarerosFacturasMes) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(mes, that.mes) &&
                Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, mes, sum);
    }
}
