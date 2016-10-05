/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.TipoBeca;
import ejb.TipoBecaFacadeLocal;
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
@Named(value = "tipoBecaBean")
@RequestScoped
public class TipoBecaBean {
    @EJB
    private TipoBecaFacadeLocal FCDETipo;
    private TipoBeca objeTipo;
    private List<TipoBeca> listTipo;
    private boolean guardar;        
    public TipoBeca getObjeTipo() {
        return objeTipo;
    }

    public void setObjeAlum(TipoBeca objeTipo) {
        this.objeTipo = objeTipo;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<TipoBeca> getListAlum() {
        return listTipo;
    }

    /**
     * Creates a new instance of TipoBecaBean
     */
    public TipoBecaBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeTipo = new TipoBeca();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeTipo = new TipoBeca();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDETipo.create(this.objeTipo);
            this.listTipo.add(this.objeTipo);
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
            this.listTipo.remove(this.objeTipo); //Limpia el objeto viejo
            FCDETipo.edit(this.objeTipo);
            this.listTipo.add(this.objeTipo); //Agrega el objeto modificado
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
            FCDETipo.remove(this.objeTipo);
            this.listTipo.remove(this.objeTipo);
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
            this.listTipo = FCDETipo.findAll();
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
            this.objeTipo = FCDETipo.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeTipo.getNombTipoBeca()) + "')");
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
