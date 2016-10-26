/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.modelo.Empresa;
import com.sv.udb.ejb.EmpresaFacadeLocal;
import java.io.Serializable;
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
@Named(value = "empresaBean")
@ViewScoped
public class EmpresaBean implements Serializable{
    @EJB
    private EmpresaFacadeLocal FCDEEmpr;
    private Empresa objeEmpr;
    private List<Empresa> listEmpr;
    private boolean guardar; 
    private static Logger log = Logger.getLogger(EmpresaBean.class);
    public Empresa getObjeEmpr() {
        return objeEmpr;
    }

    public void setObjeEmpr(Empresa objeEmpr) {
        this.objeEmpr = objeEmpr;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Empresa> getListEmpr() {
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
        this.objeEmpr.setFechEmpr(new Date());
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeEmpr.setEstaEmpr(1);
            FCDEEmpr.create(this.objeEmpr);
            this.listEmpr.add(this.objeEmpr);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Empresa Guardada");
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
            this.listEmpr.remove(this.objeEmpr); //Limpia el objeto viejo
            FCDEEmpr.edit(this.objeEmpr);
            this.listEmpr.add(this.objeEmpr); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Empresa Modificada");
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
            this.listEmpr.remove(this.objeEmpr); //Limpia el objeto viejo
            this.objeEmpr.setEstaEmpr(0);
            FCDEEmpr.edit(this.objeEmpr);
            this.listEmpr.add(this.objeEmpr); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Empresa Eliminada");
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
    
    public void consTodo()
    {
        try
        {
            this.listEmpr = FCDEEmpr.findAll();
            log.info("Empresas Consultadas");
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
            this.objeEmpr = FCDEEmpr.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeEmpr.getNombEmpr()) + "')");
            log.info("Empresa Consultada");
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
