/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;
//
//import com.sv.udb.ejb.UsuariosFacadeLocal;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Mauricio
 */
@Named(value = "globalAppBean")
@ApplicationScoped
public class GlobalAppBean {
//    @EJB
//    private UsuariosFacadeLocal FCDEUsua;   
    @Inject
//    private LoginBean logiBean; //Bean de session

    /**
     * Creates a new instance of GlobalAppBean
     */
    public GlobalAppBean() {
    }
        
    public String getUrl(String page)
    {
        String resp;
        HttpServletRequest requ = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        resp = String.format("%s%s/%s", requ.getContextPath(), requ.getServletPath(), page);
        return resp;
    }
    
    public String getResourcePath(String file)
    {
        String resp;      
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        String resoPath = facsCtxt.getExternalContext().getInitParameter("javax.faces.WEBAPP_RESOURCES_DIRECTORY");
        resp = String.format("%s/%s", resoPath, file);
        resp = facsCtxt.getExternalContext().getRealPath(resp);
        return resp;
    }
    
    public StreamedContent getImage(String imag) throws IOException {
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        if (facsCtxt.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            return new DefaultStreamedContent(new FileInputStream(getResourcePath(imag)));
        }
    }
    
//    public boolean getEstaPermByPage(String usua, String page)
//    {
//        /*
//        * page tiene que ser enviada en el siguiente formato: /Plantilla/poo/modulo/pagina.xhtml
//        */
//        try
//        {
//            return FCDEUsua.findPermByAcceAndRole(usua, page);
//        }
//        catch(Exception ex)
//        {
//            return false;
//        }
//    }
    
//    public boolean getEstaPermByRole(String role)
//    {
//        this.logiBean = this.logiBean != null ? this.logiBean : new LoginBean();
//        if(logiBean.isLoge())
//        {
//            return getEstaPermByRole(logiBean.getObjeUsua().getAcceUsua(), role);
//        }
//        else
//        {
//            return false;
//        }
//    }
    
//    public boolean getEstaPermByRole(String usua, String role)
//    {
//        /*
//        * role tiene que ser enviado según el dire_role de la tabla roles
//        */
//        try
//        {
//            HttpServletRequest requ = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//            String page = String.format("%s%s%s", requ.getContextPath(), requ.getServletPath(), role);
//            return FCDEUsua.findPermByAcceAndRole(usua, page);
//        }
//        catch(Exception ex)
//        {
//            return false;
//        }
//    }
    
    public String getAppName(String page) // Ejemplo: /modulo/pagina.xhtml
    {
        String resp = "";
        try
        {
            HttpServletRequest requ = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            resp = String.format("%s%s%s", requ.getContextPath(), requ.getServletPath(), page); //Retorna /Plantilla/poo/modulo/pagina.xhtml
//            resp = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath(); // /Plantilla
//            resp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI(); // /Plantilla/poo/modulo2/demo.xhtml
//            resp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServletPath(); // /poo
//            resp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath(); // /Plantilla
//            resp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getHeader("referer"); //
            
//            System.err.println("Name: " + resp);
        }
        catch(Exception ex)
        {
            System.err.println("Error: " + ex.getMessage());
        }
        return resp;
    }
}
