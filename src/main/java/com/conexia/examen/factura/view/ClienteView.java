package com.conexia.examen.factura.view;

import com.conexia.examen.factura.domain.Cliente;
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
public class ClienteView implements Serializable {

    @EJB
    private PersonService<Cliente> service;

    private List<Cliente> list, filtered;
    private Cliente c;

    @PostConstruct
    public void init() {
        list = new ArrayList<>(service.findAll(Cliente.class));
    }


    public String deleteAction() {
        try {
            service.eliminar(c);
            MessagesUtils.addInfoMsg("result", "Eliminado exitosamente");
            list = service.findAll(Cliente.class);
        } catch (Exception e) {
            MessagesUtils.addErrorMsg("result", e.getLocalizedMessage());
        }
        return null;
    }

    //-------------------------------------------- gets and setters
    public List<Cliente> getList() {
        return list;
    }

    public List<Cliente> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Cliente> filtered) {
        this.filtered = filtered;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }
}
