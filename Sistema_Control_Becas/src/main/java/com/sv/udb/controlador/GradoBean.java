/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.ejb.GradoFacadeLocal;
import com.sv.udb.modelo.Grado;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author eduardo
 */
@Named(value = "gradoBean")
@ViewScoped
public class GradoBean implements Serializable{

    @EJB
    private GradoFacadeLocal FCDEGrad;
    
    
    private Grado objeGrad;
    private List<Grado> listGrad;
    private boolean guardar;        
    private static Logger log = Logger.getLogger(GradoBean.class);

    public Grado getObjeGrad() {
        return objeGrad;
    }

    public void setObjeGrad(Grado objeGrad) {
        this.objeGrad = objeGrad;
    }

    public List<Grado> getListGrad() {
        return listGrad;
    }

    public void setListGrad(List<Grado> listGrad) {
        this.listGrad = listGrad;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

        
    @PostConstruct
    public void init()
    {
        this.objeGrad = new Grado();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeGrad = new Grado();
        this.guardar = true;        
    }
    
    /**
     * Creates a new instance of GradoBean
     */
    public GradoBean() {
    }
    
    /**
     * Metodo que guarda un registro en la tabla Tipo Beca
     */
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            
            this.objeGrad.setEstaGrad(1);
            System.out.println("asereje "+this.objeGrad.getNombGrad());
            System.out.println("asereje "+this.objeGrad.getMensGrad());
            System.out.println("asereje "+this.objeGrad.getEstaGrad());
            FCDEGrad.create(this.objeGrad);
            this.listGrad.add(this.objeGrad);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Tipo Beca Guardado");
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
    
    /**
     * Metodo que modifia un registro de la tabla Tipo Beca
     */
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listGrad.remove(this.objeGrad); //Limpia el objeto viejo
            FCDEGrad.edit(this.objeGrad);
            this.listGrad.add(this.objeGrad); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Tipo Beca Modificado");
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
    
    /**
     * Metodo que elimina un registro de la tabla Tipo Beca
     */
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listGrad.remove(this.objeGrad); //Limpia el objeto viejo
            this.objeGrad.setEstaGrad(0);
            FCDEGrad.edit(this.objeGrad);
            this.listGrad.add(this.objeGrad); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Tipo Beca Eliminado");
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
    
    /**
     * Metodo que consulta todos los registros de la tabla Tipo Beca
     */
    public void consTodo()
    {
        try
        {
            this.listGrad = FCDEGrad.findAll();
            log.info("Tipos de Becas Consultados");
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
    
    /**
     * Metodo que consulta un registro de la tabla Tipo Beca
     */
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara"));
        try
        {
            this.objeGrad = FCDEGrad.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeGrad.getNombGrad()) + "')");
            log.info("Tipo Beca Consultado");
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
