package com.conexia.examen.factura.service;

import com.conexia.examen.factura.domain.*;
import com.conexia.examen.factura.repository.FacturaRepository;
import com.conexia.examen.factura.repository.MesaRepository;
import com.conexia.examen.factura.repository.PersonRepository;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class FacturaService {

    @EJB
    FacturaRepository repository;

    @EJB
    PersonRepository<AbstractPerson, Long> personRepository;

    @EJB
    MesaRepository mesaRepository;

    public List<Cliente> allClientes() {
        return personRepository.findAll(Cliente.class);
    }

    public List<Cocinero> allCocineros() {
        return personRepository.findAll(Cocinero.class);
    }

    public List<Camarero> allCamareros() {
        return personRepository.findAll(Camarero.class);
    }

    public List<Mesa> allMesas() {
        return mesaRepository.findAll();
    }

    public void saveFactura(Factura factura) {
        repository.persist(factura);
    }

    /**
     * Listado de suma del total facturado en el mes por cada camarero
     *
     * @return
     */
    public List<VCamarerosFacturasMes> facturasPorMes() {
        return repository.findFacturasMesByNamedQuery();
    }

    public List<ClienteFacturaDTO> clientesMayorImporte(BigDecimal importe) {
        List<?> resultSet = repository.clientesMayorImporte(importe);
        List<ClienteFacturaDTO> facturaDTOS = new ArrayList<>();
        resultSet.forEach(e -> {
            Object[] row = (Object[]) e;
            /**
             * row -- 0  ---> nombre
             * row -- 1  ---> apellidos
             * row -- 3  ---> importe
             */
            facturaDTOS.add(new ClienteFacturaDTO(row[0].toString(),
                    row[1].toString(), convertToBigDecimal(row[2])));
        });
        return facturaDTOS;
    }

    /**
     * Convertir Object a Bigdecimal
     *
     * @param value
     * @return
     */
    private BigDecimal convertToBigDecimal(Object value) {

        BigDecimal bgd = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                bgd = (BigDecimal) value;
            } else if (value instanceof String) {
                bgd = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                bgd = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                bgd = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return bgd;
    }
}
