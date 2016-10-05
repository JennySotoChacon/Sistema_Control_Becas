/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Seccion;
import ejb.SeccionFacadeLocal;
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
@Named(value = "seccionBean")
@RequestScoped
public class SeccionBean {
    @EJB
    private SeccionFacadeLocal FCDESecc;
    private Seccion objeSecc;
    private List<Seccion> listSecc;
    private boolean guardar;        
    public Seccion getObjeSecc() {
        return objeSecc;
    }

    public void setObjeAlum(Seccion objeSecc) {
        this.objeSecc = objeSecc;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Seccion> getListAlum() {
        return listSecc;
    }

    /**
     * Creates a new instance of SeccionBean
     */
    public SeccionBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeSecc = new Seccion();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeSecc = new Seccion();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDESecc.create(this.objeSecc);
            this.listSecc.add(this.objeSecc);
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
            this.listSecc.remove(this.objeSecc); //Limpia el objeto viejo
            FCDESecc.edit(this.objeSecc);
            this.listSecc.add(this.objeSecc); //Agrega el objeto modificado
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
            FCDESecc.remove(this.objeSecc);
            this.listSecc.remove(this.objeSecc);
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
            this.listSecc = FCDESecc.findAll();
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
            this.objeSecc = FCDESecc.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeSecc.getNombSecc()) + "')");
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
