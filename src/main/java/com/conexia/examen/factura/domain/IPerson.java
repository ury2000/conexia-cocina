package com.conexia.examen.factura.domain;

import java.beans.Transient;

public interface IPerson {

    @Transient
    Long getId();

    void setId(Long id);
}
