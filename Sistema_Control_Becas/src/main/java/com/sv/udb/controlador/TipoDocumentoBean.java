/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.modelo.TipoDocumento;
import ejb.TipoDocumentoFacadeLocal;
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
@Named(value = "tipoDocumentoBean")
@ViewScoped
public class TipoDocumentoBean implements Serializable{
    @EJB
    private TipoDocumentoFacadeLocal FCDETipo;
    private TipoDocumento objeTipo;
    private List<TipoDocumento> listTipo;
    private boolean guardar;   
    private static Logger log = Logger.getLogger(TipoDocumentoBean.class);
    public TipoDocumento getObjeTipo() {
        return objeTipo;
    }

    public void setObjeTipo(TipoDocumento objeTipo) {
        this.objeTipo = objeTipo;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<TipoDocumento> getListTipo() {
        return listTipo;
    }

    /**
     * Creates a new instance of TipoDocumentoBean
     */
    public TipoDocumentoBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeTipo = new TipoDocumento();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeTipo = new TipoDocumento();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeTipo.setEstaTipoDocu(1);
            FCDETipo.create(this.objeTipo);
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
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listTipo.remove(this.objeTipo); //Limpia el objeto viejo
            FCDETipo.edit(this.objeTipo);
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
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listTipo.remove(this.objeTipo); //Limpia el objeto viejo
            this.objeTipo.setEstaTipoDocu(0);
            FCDETipo.edit(this.objeTipo);
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
    
    public void consTodo()
    {
        try
        {
            this.listTipo = FCDETipo.findAll();
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
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara"));
        try
        {
            this.objeTipo = FCDETipo.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeTipo.getNombTipoDocu()) + "')");
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
