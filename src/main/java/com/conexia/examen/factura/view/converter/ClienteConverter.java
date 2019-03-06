package com.conexia.examen.factura.view.converter;

import com.conexia.examen.factura.domain.Cliente;
import com.conexia.examen.factura.repository.PersonRepository;
import com.conexia.examen.factura.utils.MessagesUtils;
import com.conexia.examen.factura.utils.PersonException;
import org.omnifaces.util.Messages;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * EN JSF 2.2.x no funciona la Inyeccion del EJB ni de CDI @Inject
 */
@Deprecated
@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

    @EJB
    PersonRepository<Cliente, Long> service;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Messages.addInfo("result", "Converter currently used: {0}", this);
        Messages.addInfo("result", "EJB injected in converter: {0}", service);

        return new Cliente();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
//        Long id = (Long) o;
//        try {
//            Cliente c = service.findByPK(Cliente.class, id);
//            return c.getNombre();
//        } catch (PersonException e) {
//            e.printStackTrace();
//            MessagesUtils.addErrorMsg("result", e.getLocalizedMessage());
//        }
        return value != null ? value.toString() : "";
    }
}
