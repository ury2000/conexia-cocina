package com.conexia.examen.factura.view;

import com.conexia.examen.factura.domain.Cliente;
import com.conexia.examen.factura.service.PersonService;
import com.conexia.examen.factura.utils.MessagesUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ClienteSaveView implements Serializable {

    @EJB
    PersonService<Cliente> service;

    private Cliente cliente;

    @PostConstruct
    public void init() {
        cliente = new Cliente();
    }

    public String guardar() {
        try {
            service.saveOrUpdate(cliente);
            MessagesUtils.addInfoMsg("result", "Éxito");
        } catch (Exception e) {
            MessagesUtils.addErrorMsg("result", e.getLocalizedMessage());
        }
        return null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.cliente = Cliente;
    }

    public String getTittle() {
        return cliente.getId() == null ? "Adicionar" : "Actualizar";
    }
}
