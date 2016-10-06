/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Detalle;
import ejb.DetalleFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ferna
 */
@Named(value = "detalleBean")
@ViewScoped
public class DetalleBean implements Serializable{
    @EJB
    private DetalleFacadeLocal FCDEDeta;
    private Detalle objeDeta;
    private List<Detalle> listDeta;
    private boolean guardar;        
    public Detalle getObjeDeta() {
        return objeDeta;
    }

    public void setObjeDeta(Detalle objeDeta) {
        this.objeDeta = objeDeta;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Detalle> getListDeta() {
        return listDeta;
    }

    /**
     * Creates a new instance of DetalleBean
     */
    public DetalleBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeDeta = new Detalle();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeDeta = new Detalle();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEDeta.create(this.objeDeta);
            this.listDeta.add(this.objeDeta);
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
            this.listDeta.remove(this.objeDeta); //Limpia el objeto viejo
            FCDEDeta.edit(this.objeDeta);
            this.listDeta.add(this.objeDeta); //Agrega el objeto modificado
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
            FCDEDeta.remove(this.objeDeta);
            this.listDeta.remove(this.objeDeta);
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
            this.listDeta = FCDEDeta.findAll();
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
            this.objeDeta = FCDEDeta.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeDeta.getMontAlum()) + "')");
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
