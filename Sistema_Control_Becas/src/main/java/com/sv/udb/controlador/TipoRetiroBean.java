/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.ejb.TipoRetiroFacadeLocal;
import com.sv.udb.modelo.TipoRetiro;
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
 * @author eduardo
 */
@Named(value = "tipoRetiBean")
@ViewScoped
public class TipoRetiroBean implements Serializable{

    @EJB
    private TipoRetiroFacadeLocal FCDETipoReti;

    private TipoRetiro objeTipoReti;
    private List<TipoRetiro> listTipoReti;
    private boolean guardar;        
    private static Logger log = Logger.getLogger(TipoRetiroBean.class);

    public TipoRetiro getObjeTipoReti() {
        return objeTipoReti;
    }

    public void setObjeTipoReti(TipoRetiro objeTipoReti) {
        this.objeTipoReti = objeTipoReti;
    }

    public List<TipoRetiro> getListTipoReti() {
        return listTipoReti;
    }

    public void setListTipoReti(List<TipoRetiro> listTipoReti) {
        this.listTipoReti = listTipoReti;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

        
    @PostConstruct
    public void init()
    {
        this.objeTipoReti = new TipoRetiro();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeTipoReti = new TipoRetiro();
        this.guardar = true;        
    }
    
    /**
     * Creates a new instance of TipoRetiBean
     */
    public TipoRetiroBean() {
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            
            this.objeTipoReti.setEstaReti(1);
            FCDETipoReti.create(this.objeTipoReti);
            this.listTipoReti.add(this.objeTipoReti);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Tipo Beca Guardado");
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
     * Metodo que modifia un registro de la tabla Tipo Beca
     */
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listTipoReti.remove(this.objeTipoReti); //Limpia el objeto viejo
            FCDETipoReti.edit(this.objeTipoReti);
            this.listTipoReti.add(this.objeTipoReti); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Tipo Beca Modificado");
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
     * Metodo que elimina un registro de la tabla Tipo Beca
     */
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listTipoReti.remove(this.objeTipoReti); //Limpia el objeto viejo
            this.objeTipoReti.setEstaReti(0);
            FCDETipoReti.edit(this.objeTipoReti);
            this.listTipoReti.add(this.objeTipoReti); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Tipo Beca Eliminado");
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
     * Metodo que consulta todos los registros de la tabla Tipo Beca
     */
    public void consTodo()
    {
        try
        {
            this.listTipoReti = FCDETipoReti.findAll();
            log.info("Tipos de Becas Consultados");
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
     * Metodo que consulta un registro de la tabla Tipo Beca
     */
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara"));
        try
        {
            this.objeTipoReti = FCDETipoReti.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeTipoReti.getNombReti()) + "')");
            log.info("Tipo Beca Consultado");
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
