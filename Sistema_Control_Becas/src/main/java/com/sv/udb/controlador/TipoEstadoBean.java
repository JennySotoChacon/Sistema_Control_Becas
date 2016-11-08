/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.modelo.TipoEstado;
import com.sv.udb.ejb.TipoEstadoFacadeLocal;
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
@Named(value = "tipoEstadoBean")
@ViewScoped
public class TipoEstadoBean implements Serializable{
    @EJB
    private TipoEstadoFacadeLocal FCDETipo;
    private TipoEstado objeTipoEsta;
    private List<TipoEstado> listTipoEsta;
    private List<TipoEstado> listTipoEstaIna;
    private boolean guardar;   
    private static Logger log = Logger.getLogger(TipoEstadoBean.class);
    public TipoEstado getObjeTipoEsta() {
        return objeTipoEsta;
    }

    public void setObjeTipoEsta(TipoEstado objeTipoEsta) {
        this.objeTipoEsta = objeTipoEsta;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<TipoEstado> getListTipoEsta() {
        return listTipoEsta;
    }
    
    public List<TipoEstado> getListTipoEstaIna() {
        return listTipoEstaIna;
    }

    /**
     * Creates a new instance of TipoEstadoBean
     */
    public TipoEstadoBean() {
    }
    
    @PostConstruct
    public void init()
    {
//        this.objeTipoEsta = new TipoEstado();
//        this.guardar = true;
        limpForm();
        this.consTodo();
        this.consTodoIna();
    }
    
    public void limpForm()
    {
        this.objeTipoEsta = new TipoEstado();
        this.guardar = true;        
    }
    
    /**
     * Metodo que guarda un registro en la tabla Tipo Estado
     */
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeTipoEsta.setEstaTipoEsta(1);
            FCDETipo.create(this.objeTipoEsta);
            this.listTipoEsta.add(this.objeTipoEsta);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Tipo Estado Guardado");
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
    
    /**
     * Metodo que modifica un registro en la tabla Tipo Estado
     */
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listTipoEsta.remove(this.objeTipoEsta); //Limpia el objeto viejo
            FCDETipo.edit(this.objeTipoEsta);
            this.listTipoEsta.add(this.objeTipoEsta); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Tipo Estado Modificado");
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
    
    /**
     * Metodo que elimina un registro de la tabla Tipo Estado
     */
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listTipoEsta.remove(this.objeTipoEsta); //Limpia el objeto viejo
            this.objeTipoEsta.setEstaTipoEsta(0);
            FCDETipo.edit(this.objeTipoEsta);
            this.listTipoEsta.add(this.objeTipoEsta); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Tipo Estado Eliminado");
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
    
    /**
     * Metodo que consulta todos los registros de la tabla Tipo Estado
     */
    public void consTodo()
    {
        try
        {
            this.listTipoEsta = FCDETipo.findAllN();
            log.info("Tipo Estados Consultados");
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
    
    /**
     * Metodo que consulta todos los registros de la tabla Tipo Estado
     */
    public void consTodoIna()
    {
        try
        {
            this.listTipoEstaIna = FCDETipo.findAllIna();
            log.info("Tipo Estados Consultados");
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
    
    /**
     * Metodo que consulta un registro de la tabla Tipo Estado
     */
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiTipoEstaPara"));
        
        try
        {
            this.objeTipoEsta = FCDETipo.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeTipoEsta.getNombTipoEsta()) + "')");
            log.info("Tipo Estado Consultado");
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
