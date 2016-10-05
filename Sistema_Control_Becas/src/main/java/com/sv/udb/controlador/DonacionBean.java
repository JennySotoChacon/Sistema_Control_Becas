/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Donacion;
import ejb.DonacionFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ferna
 */
@Named(value = "donacionBean")
@RequestScoped
public class DonacionBean {
    @EJB
    private DonacionFacadeLocal FCDEDona;
    private Donacion objeDona;
    private List<Donacion> listDona;
    private boolean guardar;        
    public Donacion getObjeDona() {
        return objeDona;
    }

    public void setObjeAlum(Donacion objeDona) {
        this.objeDona = objeDona;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Donacion> getListAlum() {
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
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEDona.create(this.objeDona);
            this.listDona.add(this.objeDona);
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
            this.listDona.remove(this.objeDona); //Limpia el objeto viejo
            FCDEDona.edit(this.objeDona);
            this.listDona.add(this.objeDona); //Agrega el objeto modificado
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
            FCDEDona.remove(this.objeDona);
            this.listDona.remove(this.objeDona);
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
            this.listDona = FCDEDona.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiAlumPara"));
        try
        {
            this.objeDona = FCDEDona.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeDona.getMontTot()) + "')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
        finally
        {
            
        }
    }
    
}
