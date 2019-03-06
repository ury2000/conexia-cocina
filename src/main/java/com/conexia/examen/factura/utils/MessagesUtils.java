package com.conexia.examen.factura.utils;

import org.primefaces.context.PrimeFacesContext;

import javax.faces.application.FacesMessage;

public class MessagesUtils {

    /**
     * Crear mesajes. Para este ejemplo, solo usare el summary.
     * @param id
     * @param summary
     */
    public static void addInfoMsg(String id, String summary){
        PrimeFacesContext.getCurrentInstance().addMessage(id, new FacesMessage(summary));
    }
    public static void addErrorMsg(String id, String summary){
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,summary);
        PrimeFacesContext.getCurrentInstance().addMessage(id, fm);
    }
}
