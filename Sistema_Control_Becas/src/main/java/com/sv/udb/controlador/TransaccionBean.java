/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.modelo.Donacion;
import com.sv.udb.modelo.Transaccion;
import com.sv.udb.ejb.DonacionFacadeLocal;
import com.sv.udb.ejb.TransaccionFacadeLocal;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Named(value = "transaccionBean")
@ViewScoped
public class TransaccionBean implements Serializable{
    @EJB
    private TransaccionFacadeLocal FCDETran;
    private Transaccion objeTran;
    //Objeto para guardar ultimo registro de la tabla transaccionesxd
    private Transaccion objeTranTemp;
    @EJB
    private DonacionFacadeLocal FCDEDona;
    private Donacion objeDona;
    private List<Transaccion> listTran;
    private boolean guardar;
    private static Logger log = Logger.getLogger(TransaccionBean.class);
    public Transaccion getObjeTran() {
        return objeTran;
    }

    public void setObjeTran(Transaccion objeTran) {
        this.objeTran = objeTran;
    }
    public void setObjeDona(Donacion objeDona) {
        this.objeDona = objeDona;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Transaccion> getListTran() {
        return listTran;
    }

    /**
     * Creates a new instance of TransaccionBean
     */
    public TransaccionBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeTran = new Transaccion();
          this.objeDona = new Donacion();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeTran = new Transaccion();
         this.objeTran.setFechTran(new Date());
        this.guardar = true;        
    }
    
    public void guarEntrada()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            //Primero creamos el objeto de donación, consultandolo a partir de la llave foranaea en transacción    
            this.objeDona = FCDEDona.find(this.objeTran.getCodiDona().getCodiDona());
            
            //Seteamos el monto de la transacción, en este caso será, la cantidad $$ cuotas de donación
            objeTran.setMontTran(objeDona.getCantCuot());
            //tipo de tansacción 1
            objeTran.setTipoTran(1);         
            
            //consultamos el tipo de donación, es de tipo caracter xd
            char recaudacion=  this.objeDona.getCodiTipoDona().getRecaTipoDona();
            //Si el tipo de donación no es del tipo recaudación se restara del monto
            if(recaudacion=='F')
            {
                 //Restando del monto pendiente
                BigDecimal resta = objeDona.getMontPend().subtract(objeDona.getCantCuot());
                objeDona.setMontPend(resta);
                //Comprobando si la resta es igual a cero para desactivar la donción
                
                /*
                comparación con big decimal(unicos y especiales:c)
                la resta se compara a (compareTo) otro numero big decimal, pero el cero es entero
                no es big decimal, así que se debe de convertir a big decimal con:
                BigDecimal.valueof(NumeroEntero)
                resta.SeComparaA(ConvertirABigDecimal(cero))
                
                las compàraciones de big decimal pueden devolver tres numeros
                0,1 y 2
                0= son iguales
                1=es menor
                2= es mayor
                *esta en el javadoc*
                como queremos comprobar que la comparaciones es igual a cero
                
                resta.SeComparaA(ConvertirABigDecimal(cero)) == son iguales
                resta.SeComparaA(ConvertirABigDecimal(cero)) == 0
                */
                 if(resta.compareTo(BigDecimal.valueOf(0))==0 ||resta.compareTo(BigDecimal.valueOf(0.00))==0 )
                {
                   objeDona.setEstaDona(0);
                }
            }
            //Suma al monto total 
            this.objeTranTemp = FCDETran.findLast();
            
            System.out.println("nooooooooooo" +objeTranTemp);
            if(this.objeTranTemp==null)
            {
                this.objeTran.setMontTota(this.objeTran.getMontTran());
            }
            else
            {
                this.objeTran.setMontTota(this.objeTranTemp.getMontTota().add(this.objeTran.getMontTran()));
            }
            
            //setear el estado de tran xd
            
            this.objeTran.setEstaTran(1);
            //Para cuando edite la donación
            FCDEDona.edit(this.objeDona);
            //Para cuando cree la transacción
            FCDETran.create(this.objeTran);                     
            
            this.listTran.add(this.objeTran);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Transaccion guardada");
        }
        
        
        
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            log.error(getRootCause(ex).getMessage());
            System.out.println(getRootCause(ex).getMessage());
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
            this.listTran.remove(this.objeTran); //Limpia el objeto viejo
            FCDETran.edit(this.objeTran);
            this.listTran.add(this.objeTran); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Transaccion Modificada");
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
            FCDETran.remove(this.objeTran);
            this.listTran.remove(this.objeTran);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            log.info("Transaccion Eliminada");
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
            this.listTran = FCDETran.findAll();
            log.info("Transacciones Consultadas");
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
            this.objeTran = FCDETran.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeTran.getFechTran()) + "')");
            log.info("Transaccion Consultada");
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
