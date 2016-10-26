/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.modelo.Opcion;
import com.sv.udb.ejb.OpcionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ferna
 */
@Named(value = "opcionBean")
@ViewScoped
public class OpcionBean implements Serializable{
    @EJB
    private OpcionFacadeLocal FCDEOpci;
    private Opcion objeOpci;
    private List<Opcion> listOpci;
    private boolean guardar;   
    private static Logger log = Logger.getLogger(OpcionBean.class);
    public Opcion getObjeOpci() {
        return objeOpci;
    }

    public void setObjeOpci(Opcion objeOpci) {
        this.objeOpci = objeOpci;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Opcion> getListOpci() {
        return listOpci;
    }

    /**
     * Creates a new instance of OpcionBean
     */
    public OpcionBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeOpci = new Opcion();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeOpci = new Opcion();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEOpci.create(this.objeOpci);
            this.listOpci.add(this.objeOpci);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Opcion Guardada");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            log.error(getRootCause(ex).getMessage());
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
            this.listOpci.remove(this.objeOpci); //Limpia el objeto viejo
            FCDEOpci.edit(this.objeOpci);
            this.listOpci.add(this.objeOpci); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Opcion Modificada");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
            log.error(getRootCause(ex).getMessage());
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
            FCDEOpci.remove(this.objeOpci);
            this.listOpci.remove(this.objeOpci);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            log.info("Opcion Eliminada");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
            log.error(getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listOpci = FCDEOpci.findAll();
            log.info("Opciones Consultadas");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            log.error(getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara"));
        try
        {
            this.objeOpci = FCDEOpci.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeOpci.getDescOpci()) + "')");
            log.info("Opcion Consultada");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
            log.error(getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
}
