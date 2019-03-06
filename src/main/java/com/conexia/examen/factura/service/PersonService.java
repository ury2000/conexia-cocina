package com.conexia.examen.factura.service;

import com.conexia.examen.factura.domain.AbstractPerson;
import com.conexia.examen.factura.repository.PersonRepository;
import com.conexia.examen.factura.utils.PersonException;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
public class PersonService<T extends AbstractPerson> {

    @EJB
    private PersonRepository<T, Long> repository;

    /**
     * @param t
     */
    public void saveOrUpdate(T t) {
        if (t == null) throw new NullPointerException("Persona no puede ser null");

        //----- save
        //------ comparacion basica, solo para el ejemplo
        if (t.getId() == null) {
            t.setId(System.currentTimeMillis());
            repository.persist(t);

        } else {
            //--------- update
            repository.merge(t);
        }
    }

    /**
     * Listado de todos
     *
     * @return
     */
    public List<T> findAll(Class<T> clazz) {
        return repository.findAll(clazz);
    }

    public void eliminar(T c) {
        repository.delete(c);
    }
}
