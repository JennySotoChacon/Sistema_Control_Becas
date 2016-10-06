/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Pregunta;
import ejb.PreguntaFacadeLocal;
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
@Named(value = "preguntaBean")
@ViewScoped
public class PreguntaBean implements Serializable{
    @EJB
    private PreguntaFacadeLocal FCDEPreg;
    private Pregunta objePreg;
    private List<Pregunta> listPreg;
    private boolean guardar;        
    public Pregunta getObjePreg() {
        return objePreg;
    }

    public void setObjePreg(Pregunta objePreg) {
        this.objePreg = objePreg;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Pregunta> getListPreg() {
        return listPreg;
    }

    /**
     * Creates a new instance of PreguntaBean
     */
    public PreguntaBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objePreg = new Pregunta();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objePreg = new Pregunta();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEPreg.create(this.objePreg);
            this.listPreg.add(this.objePreg);
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
            this.listPreg.remove(this.objePreg); //Limpia el objeto viejo
            FCDEPreg.edit(this.objePreg);
            this.listPreg.add(this.objePreg); //Agrega el objeto modificado
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
            FCDEPreg.remove(this.objePreg);
            this.listPreg.remove(this.objePreg);
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
            this.listPreg = FCDEPreg.findAll();
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
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara"));
        try
        {
            this.objePreg = FCDEPreg.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objePreg.getDescPreg()) + "')");
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
