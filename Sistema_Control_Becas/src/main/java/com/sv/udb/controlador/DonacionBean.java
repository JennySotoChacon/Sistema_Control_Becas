/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.modelo.Donacion;
import ejb.DonacionFacadeLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Named(value = "donacionBean")
@ViewScoped
public class DonacionBean implements Serializable{
    @EJB
    private DonacionFacadeLocal FCDEDona;
    private Donacion objeDona;
    private List<Donacion> listDona;
    private boolean guardar;    
    private static Logger log = Logger.getLogger(DonacionBean.class);
    public Donacion getObjeDona() {
        return objeDona;
    }

    public void setObjeDona(Donacion objeDona) {
        this.objeDona = objeDona;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Donacion> getListDona() {
        return listDona;
    }

    /**
     * Creates a new instance of DonacionBean
     */
    public DonacionBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeDona = new Donacion();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeDona = new Donacion();        
        this.objeDona.setFechDona(new Date());
        this.objeDona.setMontTot(BigDecimal.ZERO);
        this.objeDona.setMontPend(BigDecimal.ZERO);
        this.objeDona.setCantCuot(BigDecimal.ZERO);        
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            BigDecimal total = this.objeDona.getCantCuot().multiply(BigDecimal.valueOf(objeDona.getPlazDona()));
            this.objeDona.setMontTot(total);
            if(this.objeDona.getMontPend().compareTo(BigDecimal.valueOf(0))==0 ||this.objeDona.getMontPend().compareTo(BigDecimal.valueOf(0.00))==0 )
            {
                this.objeDona.setMontPend(total);
            }            
            this.objeDona.setEstaDona(1);
            FCDEDona.create(this.objeDona);
            this.listDona.add(this.objeDona);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Donacion guardada");
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
            this.listDona.remove(this.objeDona); //Limpia el objeto viejo
            if(this.objeDona.getMontPend().compareTo(BigDecimal.valueOf(0))!=0 ||this.objeDona.getMontPend().compareTo(BigDecimal.valueOf(0.00))!=0 )
            {
                 this.objeDona.setEstaDona(1);
            }  
            FCDEDona.edit(this.objeDona);
            this.listDona.add(this.objeDona); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Donacion Modificada");
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
            this.objeDona.setEstaDona(0);
            this.listDona.remove(this.objeDona); //Limpia el objeto viejo
            FCDEDona.edit(this.objeDona);
            this.listDona.add(this.objeDona); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            log.info("Donacion Eliminada");
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
            this.listDona = FCDEDona.findAll();
            log.info("Donaciones Consultadas");
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
            this.objeDona = FCDEDona.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeDona.getMontTot()) + "')");
            log.info("Donacion Consultada");
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
