package com.conexia.examen.factura.view;

import com.conexia.examen.factura.domain.Mesa;
import com.conexia.examen.factura.repository.MesaRepository;
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
public class MesaView implements Serializable {

    @EJB
    private MesaRepository repository;

    private List<Mesa> list, filtered;
    private Mesa m;

    @PostConstruct
    public void init() {
        list = new ArrayList<>(repository.findAll());
        m = new Mesa();
    }


    public void deleteAction(Mesa m) {
        try {
            repository.delete(m);
            MessagesUtils.addInfoMsg("result", "Eliminado exitosamente");
            list = repository.findAll();
        } catch (Exception e) {
            MessagesUtils.addErrorMsg("result", e.getLocalizedMessage());
        }
    }

    public void reset(){
        m = new Mesa();
    }

    //-------------------------------------------- gets and setters
    public List<Mesa> getList() {
        return list;
    }

    public List<Mesa> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Mesa> filtered) {
        this.filtered = filtered;
    }

    public Mesa getM() {
        return m;
    }

    public void setM(Mesa m) {
        this.m = m;
    }

    public void guardar() {
        try {
            m = repository.merge(m);
            list = repository.findAll();
            MessagesUtils.addInfoMsg("result", "Éxito");
        } catch (Exception e) {
            MessagesUtils.addErrorMsg("result", e.getLocalizedMessage());
        }
    }

    public String getTittle() {
        return m.getIdMesa() == null ? "Adicionar" : "Actualizar";
    }
}
