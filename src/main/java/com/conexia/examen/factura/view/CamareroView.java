package com.conexia.examen.factura.view;

import com.conexia.examen.factura.domain.Camarero;
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
public class CamareroView implements Serializable {

    @EJB
    private PersonService<Camarero> service;

    private List<Camarero> list, filtered;
    private Camarero c;

    @PostConstruct
    public void init() {
        list = new ArrayList<>(service.findAll(Camarero.class));
    }


    public String deleteAction() {
        try {
            service.eliminar(c);
            MessagesUtils.addInfoMsg("result", "Eliminado exitosamente");
            list = service.findAll(Camarero.class);
        } catch (Exception e) {
            MessagesUtils.addErrorMsg("result", e.getLocalizedMessage());
        }
        return null;
    }

    //-------------------------------------------- gets and setters
    public List<Camarero> getList() {
        return list;
    }

    public List<Camarero> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Camarero> filtered) {
        this.filtered = filtered;
    }

    public Camarero getC() {
        return c;
    }

    public void setC(Camarero c) {
        this.c = c;
    }
}
