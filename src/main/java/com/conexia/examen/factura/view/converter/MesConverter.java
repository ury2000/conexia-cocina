package com.conexia.examen.factura.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.DateFormatSymbols;

@FacesConverter(value = "mesConverter")
public class MesConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return s;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            int month = ((Integer) o);
            String s = DateFormatSymbols.getInstance().getMonths()[month - 1];
            return s;
        } else return "Sin facturar";
    }
}
