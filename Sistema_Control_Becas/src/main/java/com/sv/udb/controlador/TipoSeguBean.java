/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.ejb.TipoSeguimientoFacadeLocal;
import com.sv.udb.modelo.TipoSeguimiento;
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
 * @author Morenita
 */
@Named(value = "tipoSeguBean")
@ViewScoped
public class TipoSeguBean implements Serializable{
    @EJB
    private TipoSeguimientoFacadeLocal FCDETipoSegu;
    private TipoSeguimiento objeTipo;
    private List<TipoSeguimiento> listTipo;
    private boolean guardar;   
    private static Logger log = Logger.getLogger(TipoSeguBean.class);
    public TipoSeguimiento getObjeTipo() {
        return objeTipo;
    }

    public void setObjeTipo(TipoSeguimiento objeTipo) {
        this.objeTipo = objeTipo;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<TipoSeguimiento> getListTipo() {
        return listTipo;
    }
    
    /**
     * Creates a new instance of tipoSeguBean
     */
    public TipoSeguBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeTipo = new TipoSeguimiento();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeTipo = new TipoSeguimiento();
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
            this.objeTipo.setEstaTipoSegui(1);
            FCDETipoSegu.create(this.objeTipo);
            this.listTipo.add(this.objeTipo);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Tipo Seguimiento Guardado");
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
            FCDETipoSegu.edit(this.objeTipo);
            this.listTipo.add(this.objeTipo); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Tipo Seguimiento Modificado");
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
            this.objeTipo.setEstaTipoSegui(0);
            FCDETipoSegu.edit(this.objeTipo);
            this.listTipo.add(this.objeTipo); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Tipo Seguimiento Eliminado");
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
            this.listTipo = FCDETipoSegu.findAll();
            log.info("Tipos de Seguimientos Consultados");
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
            this.objeTipo = FCDETipoSegu.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeTipo.getNombTipoSegui()) + "')");
            log.info("Tipo Seguimiento Consultado");
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
