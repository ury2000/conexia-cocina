package com.conexia.examen.factura.view;

import com.conexia.examen.factura.domain.*;
import com.conexia.examen.factura.service.FacturaService;
import com.conexia.examen.factura.utils.MessagesUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@ViewScoped
public class FacturaView implements Serializable {

    @EJB
    FacturaService service;

    private List<Cliente> clientes;
    private List<Camarero> camareros;
    private List<Cocinero> cocineros;
    private List<Mesa> mesas;
    private Factura factura;
    private DetalleFactura detalleFactura;
    private List<VCamarerosFacturasMes> facturasMes, filteredFalturaMES;
    private List<ClienteFacturaDTO> facturaDTOs, filteredFacturaDTOs;

    @PostConstruct
    public void init() {
        clientes = service.allClientes();
        camareros = service.allCamareros();
        cocineros = service.allCocineros();
        mesas = service.allMesas();
        factura = new Factura();
        detalleFactura = new DetalleFactura();
        facturasMes = service.facturasPorMes();
        facturaDTOs = service.clientesMayorImporte(new BigDecimal(100000));
    }

    public void addPlato() {
        factura.addDetalleFactura(detalleFactura);
        detalleFactura = new DetalleFactura();
    }

    public void removePlato(DetalleFactura d) {

        factura.removeDetalleFactura(d);
    }

    public void guardar() {
        try {
            if (!this.factura.getDetalleFacturas().isEmpty()) {
                service.saveFactura(factura);
                System.out.println(factura);
                detalleFactura = new DetalleFactura();
                factura = new Factura();
                MessagesUtils.addInfoMsg("result", "Factura guardada exitosamente");
            } else {
                MessagesUtils.addErrorMsg("result", "Se deben tener al menos un plato digilenciado");
            }
        } catch (Exception e) {
            MessagesUtils.addErrorMsg("result", e.getLocalizedMessage());
        }

    }

    //--------------------------------------- getters
    public List<ClienteFacturaDTO> getFacturaDTOs() {
        return facturaDTOs;
    }

    public List<ClienteFacturaDTO> getFilteredFacturaDTOs() {
        return filteredFacturaDTOs;
    }

    public void setFilteredFacturaDTOs(List<ClienteFacturaDTO> filteredFacturaDTOs) {
        this.filteredFacturaDTOs = filteredFacturaDTOs;
    }

    public List<VCamarerosFacturasMes> getFilteredFalturaMES() {
        return filteredFalturaMES;
    }

    public void setFilteredFalturaMES(List<VCamarerosFacturasMes> filteredFalturaMES) {
        this.filteredFalturaMES = filteredFalturaMES;
    }

    public List<VCamarerosFacturasMes> getFacturasMes() {
        return facturasMes;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Camarero> getCamareros() {
        return camareros;
    }

    public List<Cocinero> getCocineros() {
        return cocineros;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }
}
