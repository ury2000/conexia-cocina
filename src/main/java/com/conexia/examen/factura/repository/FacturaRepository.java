package com.conexia.examen.factura.repository;

import com.conexia.examen.factura.domain.Factura;
import com.conexia.examen.factura.domain.Mesa;
import com.conexia.examen.factura.domain.VCamarerosFacturasMes;
import com.conexia.examen.factura.utils.FacturaException;
import com.conexia.examen.factura.utils.MesaException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class FacturaRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @param f
     */
    public void persist(Factura f) {

        em.persist(f);
    }

    /**
     *
     * @param entity
     */
    public Factura merge(Factura entity) {
        return em.merge(entity);
    }

    /**
     *
     * @param id
     * @return
     * @throws com.conexia.examen.factura.utils.FacturaException
     */
    public Factura findByPK(Long id) throws FacturaException {
        // TODO Auto-generated method stub
        if (id == null) {
            throw new FacturaException("Factura no puede estar vacio");
        }
        return em.find(Factura.class, id);
    }

    /**
     *
     * @param entity
     */
    public void delete(Factura entity) {
        // TODO Auto-generated method stub
        // em.merge(entity);
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    /**
     *
     * @return
     */
    public List<Factura> findAll(){
        //List<Mesa> m =  em.createNamedQuery("Mesa.findAll").getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Factura> c = cb.createQuery(Factura.class);
        c.select(c.from(Factura.class));
        return em.createQuery(c).getResultList();
    }

    /**
     * Listado de suma del total facturado por cada camarero
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<VCamarerosFacturasMes> findFacturasMesByNamedQuery(){
        return em.createNamedQuery("VCamarerosMes.findAll").getResultList();
    }

    /**
     * Consulta que muestre los clientes con más de cien
     * mil pesos gastados en el restaurante
     *
     * @param importe
     * @return
     */
    public List<?> clientesMayorImporte(BigDecimal importe){
        return em.createNamedQuery("Clientes.MasImporte").
                setParameter("importe",importe).getResultList();
    }
}
