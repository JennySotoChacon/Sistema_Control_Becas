/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.modelo.DetalleBeca;
import com.sv.udb.ejb.DetalleBecaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author eduardo
 */
@Named(value = "detalleBecaBean")
@ViewScoped
public class DetalleBecaBean implements Serializable{

    @EJB
    private DetalleBecaFacadeLocal FCDEDetaBeca;
    private DetalleBeca objeDetaBeca;
    private List<DetalleBeca> listDetaBeca;
    private boolean guardar;      
    private static Logger log = Logger.getLogger(DetalleBean.class);

    public DetalleBeca getObjeDetaBeca() {
        return objeDetaBeca;
    }

    public void setObjeDetaBeca(DetalleBeca objeDetaBecaBeca) {
        this.objeDetaBeca = objeDetaBecaBeca;
    }

    public List<DetalleBeca> getListDetaBeca() {
        return listDetaBeca;
    }

    public void setListDetaBeca(List<DetalleBeca> listDetaBeca) {
        this.listDetaBeca = listDetaBeca;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    
    /**
     * Creates a new instance of DetalleBecaBean
     */
    public DetalleBecaBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeDetaBeca = new DetalleBeca();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeDetaBeca = new DetalleBeca();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeDetaBeca.setEstaDetaBeca(1);
            FCDEDetaBeca.create(this.objeDetaBeca);
            this.listDetaBeca.add(this.objeDetaBeca);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Detalle Guardado");
            
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
            this.listDetaBeca.remove(this.objeDetaBeca); //Limpia el objeto viejo
            FCDEDetaBeca.edit(this.objeDetaBeca);
            this.listDetaBeca.add(this.objeDetaBeca); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Detalle Modificado");
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
            this.objeDetaBeca.setEstaDetaBeca(0);
            this.listDetaBeca.remove(this.objeDetaBeca); //Limpia el objeto viejo
            FCDEDetaBeca.edit(this.objeDetaBeca);
            this.listDetaBeca.add(this.objeDetaBeca); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Detalle Modificado");
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
            this.listDetaBeca = FCDEDetaBeca.findAll();
            log.info("Detalles Consultados");
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
            this.objeDetaBeca = FCDEDetaBeca.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeDetaBeca.getCantMese()) + "')");
            log.info("Detalle Consultado");
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
