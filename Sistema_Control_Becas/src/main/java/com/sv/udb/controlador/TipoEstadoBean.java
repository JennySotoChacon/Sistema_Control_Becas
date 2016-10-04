/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.TipoEstado;
import ejb.TipoEstadoFacadeLocal;
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
@Named(value = "tipoEstadoBean")
@ViewScoped
public class TipoEstadoBean implements Serializable{
    @EJB
    private TipoEstadoFacadeLocal FCDETipo;
    private TipoEstado objeTipoEsta;
    private List<TipoEstado> listTipoEsta;
    private boolean guardar;        
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
    }
    
    public void limpForm()
    {
        this.objeTipoEsta = new TipoEstado();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDETipo.create(this.objeTipoEsta);
            this.listTipoEsta.add(this.objeTipoEsta);
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
            this.listTipoEsta.remove(this.objeTipoEsta); //Limpia el objeto viejo
            FCDETipo.edit(this.objeTipoEsta);
            this.listTipoEsta.add(this.objeTipoEsta); //Agrega el objeto modificado
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
            System.out.println("SOY ESPECIAL: "+this.objeTipoEsta);
            FCDETipo.remove(this.objeTipoEsta);
            this.listTipoEsta.remove(this.objeTipoEsta);
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
            this.listTipoEsta = FCDETipo.findAll();
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
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiTipoEstaPara"));
        
        try
        {
            this.objeTipoEsta = FCDETipo.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeTipoEsta.getNombTipoEsta()) + "')");
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
