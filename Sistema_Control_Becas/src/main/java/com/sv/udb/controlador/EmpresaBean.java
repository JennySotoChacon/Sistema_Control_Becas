/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Empresa;
import ejb.EmpresaFacadeLocal;
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
@Named(value = "empresaBean")
@RequestScoped
public class EmpresaBean {
    @EJB
    private EmpresaFacadeLocal FCDEEmpr;
    private Empresa objeEmpr;
    private List<Empresa> listEmpr;
    private boolean guardar;        
    public Empresa getObjeEmpr() {
        return objeEmpr;
    }

    public void setObjeAlum(Empresa objeEmpr) {
        this.objeEmpr = objeEmpr;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Empresa> getListAlum() {
        return listEmpr;
    }
    
    /**
     * Creates a new instance of EmpresaBean
     */
    public EmpresaBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeEmpr = new Empresa();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeEmpr = new Empresa();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEEmpr.create(this.objeEmpr);
            this.listEmpr.add(this.objeEmpr);
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
            this.listEmpr.remove(this.objeEmpr); //Limpia el objeto viejo
            FCDEEmpr.edit(this.objeEmpr);
            this.listEmpr.add(this.objeEmpr); //Agrega el objeto modificado
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
            FCDEEmpr.remove(this.objeEmpr);
            this.listEmpr.remove(this.objeEmpr);
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
            this.listEmpr = FCDEEmpr.findAll();
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
            this.objeEmpr = FCDEEmpr.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeEmpr.getNombEmpr()) + "')");
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
