package com.conexia.examen.factura.view;

import com.conexia.examen.factura.domain.Camarero;
import com.conexia.examen.factura.service.PersonService;
import com.conexia.examen.factura.utils.MessagesUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class CamareroSaveView implements Serializable {

    @EJB
    PersonService<Camarero> service;

    private Camarero camarero;

    @PostConstruct
    public void init() {
        camarero = new Camarero();
    }

    public String guardar() {
        try {
            service.saveOrUpdate(camarero);
            MessagesUtils.addInfoMsg("result", "Éxito");
        } catch (Exception e) {
            MessagesUtils.addErrorMsg("result", e.getLocalizedMessage());
        }
        return null;
    }

    public Camarero getCamarero() {
        return camarero;
    }

    public void setCamarero(Camarero camarero) {
        this.camarero = camarero;
    }

    public String getTittle() {
        return camarero.getIdCamarero() == null ? "Adicionar" : "Actualizar";
    }
}
