/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.ejb.TipoDonacionFacadeLocal;
import com.sv.udb.modelo.TipoDonacion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ariel
 */
@Named(value = "tipoTipoDonacionBean")
@ViewScoped
public class TipoTipoDonacionBean implements Serializable {

    
        @EJB
    private TipoDonacionFacadeLocal FCDETipoDona;
    private TipoDonacion objeTipo;
    private List<TipoDonacion> listTipo;
    private boolean guardar;   
    private static Logger log = Logger.getLogger(TipoTipoDonacionBean.class);
    public TipoDonacion getObjeTipo() {
        return objeTipo;
    }

    public void setObjeTipo(TipoDonacion objeTipo) {
        this.objeTipo = objeTipo;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<TipoDonacion> getListTipo() {
        return listTipo;
    }

    /**
     * Creates a new instance of tipoDonacionBean
     */
    public TipoTipoDonacionBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeTipo = new TipoDonacion();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeTipo = new TipoDonacion();
        this.guardar = true;        
    }
    
    /**
     * Metodo que guarda un registro en la tabla Tipo Documento
     */
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeTipo.setEstaDona(1);
            FCDETipoDona.create(this.objeTipo);
            this.listTipo.add(this.objeTipo);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Tipo Documento Guardado");
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
     * Metodo que modifica un registro de la tabla Tipo Documento
     */
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listTipo.remove(this.objeTipo); //Limpia el objeto viejo
            FCDETipoDona.edit(this.objeTipo);
            this.listTipo.add(this.objeTipo); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Tipo Documento Modificado");
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
     * Metodo que elimina un registro de la tabla Tipo Documento
     */
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listTipo.remove(this.objeTipo); //Limpia el objeto viejo
            this.objeTipo.setEstaDona(0);
            FCDETipoDona.edit(this.objeTipo);
            this.listTipo.add(this.objeTipo); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Tipo Documento Eliminado");
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
     * Metodo que consulta todos los registros de la tabla Tipo Documento
     */
    public void consTodo()
    {
        try
        {
            this.listTipo = FCDETipoDona.findAll();
            log.info("Tipos de Documentos Consultados");
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
     * Metodo que consulta un registro de la tabla Tipo Documento
     */
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara"));
        try
        {
            this.objeTipo = FCDETipoDona.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeTipo.getNombTipoDona()) + "')");
            log.info("Tipo Documento Consultado");
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