package com.conexia.examen.factura.repository;

import com.conexia.examen.factura.domain.AbstractPerson;
import com.conexia.examen.factura.domain.Mesa;
import com.conexia.examen.factura.utils.MesaException;
import com.conexia.examen.factura.utils.PersonException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
public class MesaRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @param m
     */
    public void persist(Mesa m) {

        em.persist(m);
    }

    /**
     *
     * @param entity
     */
    public Mesa merge(Mesa entity) {
        return em.merge(entity);
    }

    /**
     *
     * @param id
     * @return
     * @throws MesaException
     */
    public Mesa findByPK(Long id) throws MesaException {
        // TODO Auto-generated method stub
        if (id == null) {
            throw new MesaException("Mesa no puede estar vacio");
        }
        return em.find(Mesa.class, id);
    }

    /**
     *
     * @param entity
     */
    public void delete(Mesa entity) {
        // TODO Auto-generated method stub
        // em.merge(entity);
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    /**
     *
     * @return
     */
    public List<Mesa> findAll(){
        //List<Mesa> m =  em.createNamedQuery("Mesa.findAll").getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Mesa> c = cb.createQuery(Mesa.class);
        c.select(c.from(Mesa.class));
        return em.createQuery(c).getResultList();
    }
}
