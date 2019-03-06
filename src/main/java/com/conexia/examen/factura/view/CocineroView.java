package com.conexia.examen.factura.view;

import com.conexia.examen.factura.domain.Cocinero;
import com.conexia.examen.factura.service.PersonService;
import com.conexia.examen.factura.utils.MessagesUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CocineroView implements Serializable {

    @EJB
    private PersonService<Cocinero> service;

    private List<Cocinero> list, filtered;
    private Cocinero c;

    @PostConstruct
    public void init() {
        list = new ArrayList<>(service.findAll(Cocinero.class));
    }


    public String deleteAction() {
        try {
            service.eliminar(c);
            MessagesUtils.addInfoMsg("result", "Eliminado exitosamente");
            list = service.findAll(Cocinero.class);
        } catch (Exception e) {
            MessagesUtils.addErrorMsg("result", e.getLocalizedMessage());
        }
        return null;
    }

    //-------------------------------------------- gets and setters
    public List<Cocinero> getList() {
        return list;
    }

    public List<Cocinero> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Cocinero> filtered) {
        this.filtered = filtered;
    }

    public Cocinero getC() {
        return c;
    }

    public void setC(Cocinero c) {
        this.c = c;
    }
}
