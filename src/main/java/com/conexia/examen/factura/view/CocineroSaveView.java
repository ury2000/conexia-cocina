package com.conexia.examen.factura.view;

import com.conexia.examen.factura.domain.Cocinero;
import com.conexia.examen.factura.service.PersonService;
import com.conexia.examen.factura.utils.MessagesUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class CocineroSaveView implements Serializable {

    @EJB
    PersonService<Cocinero> service;

    private Cocinero cocinero;

    @PostConstruct
    public void init() {
        cocinero = new Cocinero();
    }

    public String guardar() {
        try {
            service.saveOrUpdate(cocinero);
            MessagesUtils.addInfoMsg("result", "Éxito");
        } catch (Exception e) {
            MessagesUtils.addErrorMsg("result", e.getLocalizedMessage());
        }
        return null;
    }

    public Cocinero getCocinero() {
        return cocinero;
    }

    public void setCocinero(Cocinero cocinero) {
        this.cocinero = cocinero;
    }

    public String getTittle() {
        return cocinero.getId() == null ? "Adicionar" : "Actualizar";
    }
}
