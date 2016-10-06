/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Estructura;
import ejb.EstructuraFacadeLocal;
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
@Named(value = "estructuraBean")
@ViewScoped
public class EstructuraBean implements Serializable{
    @EJB
    private EstructuraFacadeLocal FCDEEstr;
    private Estructura objeEstr;
    private List<Estructura> listEstr;
    private boolean guardar;        
    public Estructura getObjeEstr() {
        return objeEstr;
    }

    public void setObjeEstr(Estructura objeEstr) {
        this.objeEstr = objeEstr;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Estructura> getListEstr() {
        return listEstr;
    }

    /**
     * Creates a new instance of EstructuraBean
     */
    public EstructuraBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeEstr = new Estructura();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeEstr = new Estructura();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEEstr.create(this.objeEstr);
            this.listEstr.add(this.objeEstr);
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
            this.listEstr.remove(this.objeEstr); //Limpia el objeto viejo
            FCDEEstr.edit(this.objeEstr);
            this.listEstr.add(this.objeEstr); //Agrega el objeto modificado
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
            FCDEEstr.remove(this.objeEstr);
            this.listEstr.remove(this.objeEstr);
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
            this.listEstr = FCDEEstr.findAll();
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
            this.objeEstr = FCDEEstr.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeEstr.getTipoEstr()) + "')");
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
