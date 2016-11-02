/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.modelo.Detalle;
import com.sv.udb.ejb.DetalleFacadeLocal;
import com.sv.udb.ejb.TransaccionFacadeLocal;
import com.sv.udb.modelo.Transaccion;
import java.io.Serializable;
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
@Named(value = "detalleBean")
@ViewScoped
public class DetalleBean implements Serializable{

    @EJB
    private TransaccionFacadeLocal FCDETran;
    @EJB
    private DetalleFacadeLocal FCDEDeta;
    
    private Detalle objeDeta;
    private List<Detalle> listDeta;
    private boolean guardar;
    private static Logger log = Logger.getLogger(DetalleBean.class);
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

    //Para obtener la transacción
    private Transaccion objeTran;
    
    /**
     * Creates a new instance of DetalleBean
     */
    public DetalleBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeTran = new Transaccion();
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
        try
        {
            System.out.println("cancer begins");
//            this.objeTran = 
           this.FCDETran.findLast();
           /* System.out.println("Registro al que le insertaremos el detalle: "+objeTran.getCodiTran());
            this.objeDeta.setCodiTran(objeTran);
            System.out.println("Transacción: "+objeDeta.getCodiDeta());
            //Obtener lo que el tipo de beca cubre
            //1 = Matricula
            //2 = Mensualidad
            switch (objeTran.getCodiDetaBeca().getCodiTipoBeca().getTipoTipoBeca()) {
                case 1:
                    //Obtener el monto que pagó el alumno
                    this.objeDeta.setMontAlum(objeTran.getCodiDetaBeca().getCodiBeca().getCodiSoliBeca().getCodiGrad().getMatrGrac().subtract(objeTran.getMontTran()));
                    this.guardar = true;
                    break;
                case 2:
                    this.objeDeta.setMontAlum(objeTran.getCodiDetaBeca().getCodiBeca().getCodiSoliBeca().getCodiGrad().getMensGrad().subtract(objeTran.getMontTran()));
                    this.guardar = true;
                    break;
                default:
                    this.guardar = false;
                    break;
            }
            if (guardar) {
                this.objeDeta.setFechDeta(this.objeTran.getFechTran());
                this.objeDeta.setEstaDeta(1);
                //Proceso normal para guardar
                FCDEDeta.create(this.objeDeta);
                this.listDeta.add(this.objeDeta);
                this.limpForm();
                log.info("Detalle Guardado");
            }*/
           System.out.println("Holas");
        }
        catch(Exception ex)
        {
            log.error(getRootCause(ex).getMessage());
            System.out.println(ex.getMessage());
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
            log.info("Detalle Modificado");
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
            FCDEDeta.remove(this.objeDeta);
            this.listDeta.remove(this.objeDeta);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            log.info("Detalle Eliminado");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
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
            this.listDeta = FCDEDeta.findAll();
            log.info("Detalles Consultados");
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
            this.objeDeta = FCDEDeta.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeDeta.getMontAlum()) + "')");
            log.info("Detalle Consultado");
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
