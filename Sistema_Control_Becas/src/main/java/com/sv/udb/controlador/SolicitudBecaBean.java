/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.SolicitudBeca;
import ejb.SolicitudBecaFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ferna
 */
@Named(value = "solicitudBecaBean")
@RequestScoped
public class SolicitudBecaBean {
    @EJB
    private SolicitudBecaFacadeLocal FCDESoli;
    private SolicitudBeca objeAlum;
    private List<SolicitudBeca> listAlum;
    private boolean guardar;        
    public SolicitudBeca getObjeAlum() {
        return objeAlum;
    }

    public void setObjeAlum(SolicitudBeca objeAlum) {
        this.objeAlum = objeAlum;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<SolicitudBeca> getListAlum() {
        return listAlum;
    }

    /**
     * Creates a new instance of SolicitudBecaBean
     */
    public SolicitudBecaBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeAlum = new SolicitudBeca();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeAlum = new SolicitudBeca();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDESoli.create(this.objeAlum);
            this.listAlum.add(this.objeAlum);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listAlum.remove(this.objeAlum); //Limpia el objeto viejo
            FCDESoli.edit(this.objeAlum);
            this.listAlum.add(this.objeAlum); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDESoli.remove(this.objeAlum);
            this.listAlum.remove(this.objeAlum);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listAlum = FCDESoli.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
//    public void cons()
//    {
//        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
//        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiAlumPara"));
//        try
//        {
//            this.objeAlum = FCDESoli.find(codi);
//            this.guardar = false;
//            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
//                    String.format("%s %s", this.objeAlum.getNombAlum(), this.objeAlum.getApelAlum()) + "')");
//        }
//        catch(Exception ex)
//        {
//            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
//        }
//        finally
//        {
//            
//        }
//    }
    
}
