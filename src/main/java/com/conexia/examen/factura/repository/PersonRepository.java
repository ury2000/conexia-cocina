package com.conexia.examen.factura.repository;

import com.conexia.examen.factura.domain.AbstractPerson;
import com.conexia.examen.factura.utils.PersonException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
public class PersonRepository<T extends AbstractPerson, I extends Serializable> {

    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @param entity
     */
    public void persist(T entity) {

        em.persist(entity);
    }

    /**
     *
     * @param entity
     */
    public void merge(T entity) {
        em.merge(entity);
    }

    /**
     *
     * @param type
     * @param id
     * @return
     * @throws PersonException
     */
    public T findByPK(Class<T> type, I id) throws PersonException {
        // TODO Auto-generated method stub
        if (id == null) {
            throw new PersonException("Persona no puede estar vacia");
        }
        return em.find(type, id);
    }

    /**
     *
     * @param entity
     */
    public void delete(T entity) {
        // TODO Auto-generated method stub
        // em.merge(entity);
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    /**
     *
     * @param clazz
     * @return
     */
    public <K extends AbstractPerson> List<K> findAll(Class<K> clazz){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<K> c = cb.createQuery(clazz);
//        Root<T> p= c.from(clazz);
        c.select(c.from(clazz));
        return em.createQuery(c).getResultList();
    }
}
