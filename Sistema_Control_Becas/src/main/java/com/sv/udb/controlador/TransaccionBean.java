/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.ejb.DetalleBecaFacadeLocal;
import com.sv.udb.ejb.DetalleFacadeLocal;
import com.sv.udb.modelo.Donacion;
import com.sv.udb.modelo.Transaccion;
import com.sv.udb.ejb.DonacionFacadeLocal;
import com.sv.udb.ejb.TransaccionFacadeLocal;
import com.sv.udb.modelo.Detalle;
import com.sv.udb.modelo.DetalleBeca;
import com.sv.udb.modelo.SolicitudBeca;
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
    private DetalleBecaFacadeLocal FCDEDetaBeca;
    @EJB
    private TransaccionFacadeLocal FCDETran;
    @EJB
    private DonacionFacadeLocal FCDEDona;
    @EJB
    private DetalleFacadeLocal FCDEDeta;
    
    private Transaccion objeTran;
    private List<Transaccion> listTran;
    
     //para el combo box
    private SolicitudBeca objeCombPadr;
    private List<DetalleBeca> listHijo;

    public SolicitudBeca getObjeCombPadr() {
        return objeCombPadr;
    }

    public void setObjeCombPadr(SolicitudBeca objeCombPadr) {
        this.objeCombPadr = objeCombPadr;
    }

    public List<DetalleBeca> getListHijo() {
        return listHijo;
    }
    
    public void onDetalleSelect(){
    this.listHijo =  FCDEDetaBeca.findForCombo(this.objeCombPadr.getCodiSoliBeca());
}
    
    //Objeto para guardar ultimo registro de la tabla transaccionesxd
    private Transaccion objeTranTemp;
    private Donacion objeDona;
    
    
    //Para desactivar las transacciones
    boolean conf = false;
    private Transaccion objeTranDesa;
    
    private boolean guardar;
    boolean guarSali; //Usado en el switch
    private boolean guarTran; //Usado en el método de reembolso
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
    
    //Para las salidas
    private DetalleBeca objeDetaBeca;

    public DetalleBeca getObjeDetaBeca() {
        return objeDetaBeca;
    }

    public void setObjeDetaBeca(DetalleBeca objeDetaBeca) {
        this.objeDetaBeca = objeDetaBeca;
    }
    /**
     * Creates a new instance of TransaccionBean
     */
    public TransaccionBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeTranDesa = new Transaccion();
        this.objeDeta = new Detalle();
        this.objeTran = new Transaccion();
        this.objeDona = new Donacion();
        this.guardar = true;
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeTranDesa = new Transaccion();
        this.objeTran = new Transaccion();
        this.objeTran.setFechTran(new Date());
        this.guardar = true;        
    }
    
    public void guarEntr()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            //Primero creamos el objeto de donación, consultandolo a partir de la llave foranea en transacción    
            this.objeDona = FCDEDona.find(this.objeTran.getCodiDona().getCodiDona());
            
            //Seteamos el monto de la transacción, en este caso será, la cantidad $$ cuotas de donación
            if (objeTran.getMontTran()==null) {
                objeTran.setMontTran(objeDona.getCantCuot());
            }
            //tipo de tansacción 1
            objeTran.setTipoTran(1);         
            
            //consultamos el tipo de donación, es de tipo caracter xd
            char recaudacion=  this.objeDona.getCodiTipoDona().getRecaTipoDona();
            //Si el tipo de donación no es del tipo recaudación se restara del monto
            if(recaudacion=='F')
            {
                //Restando del monto pendiente
                BigDecimal resta = objeDona.getMontPend().subtract(objeTran.getMontTran());
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
            //La donación es de tipo recaudación y no cuenta con monto pendiente po
            else{
                this.objeDona.setEstaDona(0);
            }
            //Suma al monto total 
            this.objeTranTemp = FCDETran.findLast();
            
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
        }
        finally
        {
            
        }
    }
    
    public void guarSali(){
        
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
        //Crear el objeto detalle de beca
        this.objeDetaBeca = FCDEDetaBeca.find(objeTran.getCodiDetaBeca().getCodiDetaBeca());
        //Comprobar que la cantidad de meses sea mayor a cero o que el estado sea activo
        //Setear monto de transacción
        this.objeTran.setMontTran(this.objeTran.getCodiDetaBeca().getCodiTipoBeca().getDescTipoBeca());
        //Obtener el monto total disponible en el fondo
        this.objeTranTemp = FCDETran.findLast();
          
        // objeto 1 = monto total, objeto 2 = monto transacción
        // Posibles resultados de la comparación
        // "0" ambos montos son iguales
        // "1" el monto total es mayor al monto de transacción
        // "-1" el monto de transacción es mayor al monto total
        
        switch (this.objeTranTemp.getMontTota().compareTo(this.objeTran.getMontTran())) {
        //Operación normal de resta, fondo queda a 0. Recordar emitir alerta xD
            case 0: 
                //Setear el nuevo monto total
                BigDecimal nuevMont = this.objeTranTemp.getMontTota().subtract(this.objeTran.getMontTran());
                this.objeTran.setMontTota(nuevMont);
                //Modificaciones al detalle de beca, consultar la cantidad de meses que dura el detalle y restarle uno con la operación
                //Si el resultado es 0 desactivar el detalle
                int nuevMese = this.objeDetaBeca.getCantMese() - 1;
                this.objeDetaBeca.setCantMese(nuevMese);
                if (nuevMese == 0) {
                    this.objeDetaBeca.setEstaDetaBeca(0);
                }
                //Cambia el boolean para guardar xd
                this.guarSali = true;
                break;
        //Operación normal
            case 1:
                BigDecimal nuevMont1 = this.objeTranTemp.getMontTota().subtract(this.objeTran.getMontTran());
                this.objeTran.setMontTota(nuevMont1);
                //Modificaciones al detalle de beca, consultar la cantidad de meses que dura el detalle y restarle uno con la operación
                //Si el resultado es 0 desactivar el detalle
                int nuevMese1 = this.objeDetaBeca.getCantMese() - 1;
                this.objeDetaBeca.setCantMese(nuevMese1);
                if (nuevMese1 == 0) {
                    this.objeDetaBeca.setEstaDetaBeca(0);
                }
                //Cambia el boolean para guardar xd
                this.guarSali = true;
                break;
        //Error, el fondo no alcanza para realizar la transacción
            default:     
                //Cambia el boolean para salir del guardar xd
                this.guarSali = false;
                break;     
        }
            if (guarSali) {
                //setear el estado de tran xd
                this.objeTran.setEstaTran(1);
                this.objeTran.setTipoTran(2);
                //Para cuando edite el detalle de beca
                this.FCDEDetaBeca.edit(objeDetaBeca);
                //Para cuando cree la transacción
                FCDETran.create(this.objeTran);

                this.listTran.add(this.objeTran);

                this.limpForm();
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
                log.info("Transaccion guardada");
                
                //Llamada al metodo para enviar la información del detalle
                guarDetalle();
            }
            else{
                 ctx.execute("setMessage('MESS_ERRO', 'Atención', 'El fondo no alcanza para realizar la transacción')");
            }
            
        } catch (Exception ex){
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            log.error(getRootCause(ex).getMessage());
            System.out.println(getRootCause(ex).getMessage());
            
        }
    }
    private Detalle objeDeta;
    private List<Detalle> listDeta;
    public void guarDetalle()
    {
        try
        {
            this.objeTran = this.FCDETran.findLast();
            this.objeDeta.setCodiTran(objeTran);
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
            }
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
    
    /**
     * Este método debe de desactivar una entrega de donación en caso de cualquier error, además debe de restar el monto de la transacción del monto total del fondo
     */
    public void desaEntr()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            //Tomar el código de la donación para sumarle el monto de la transacción al monto pendiente
            this.objeDona = FCDEDona.find(objeTran.getCodiDona().getCodiDona());
            BigDecimal montPendActu = this.objeDona.getMontPend();
            this.objeDona.setMontPend(this.objeTran.getMontTran().add(montPendActu));
            //En caso que la donación ya estuviera como desactivada, y con el nuevo monto pendiente se activa
            if (this.objeDona.getMontPend().compareTo(BigDecimal.ZERO) != 0) {
                this.objeDona.setEstaDona(1);
            }
            
            //Enviar el monto de la transacción al método para restarlo del fondo
            boolean conf2 = this.reem(this.objeTran.getMontTran(), 1);
            
            if (conf2) {
                //Modificar la donación
                FCDEDona.edit(this.objeDona);
                //Proceso normal de modificación de la transacción
                this.listTran.remove(this.objeTran); //Limpia el objeto viejo
                this.objeTran.setEstaTran(0);
                FCDETran.edit(this.objeTran);
                this.listTran.add(this.objeTran); //Agrega el objeto modificado
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
                log.info("Transaccion Modificada");
            }

        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al desactivar la entrada de donación ')");
            log.error(getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
    /**
     * Éste método debe de desactivar una salida como entrega de detalles de beca y sumar el monto de la transacción al fondo total 
     */
    public void desaSali()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            //Tomar el código del detalle de beca
            this.objeDetaBeca = FCDEDetaBeca.find(objeTran.getCodiDetaBeca().getCodiDetaBeca());
            int cantMeseActu = this.objeDetaBeca.getCantMese();
            //Sumarle la cantidad de meses
            this.objeDetaBeca.setCantMese(cantMeseActu+1);
            
            if (this.objeDetaBeca.getCantMese() != 0) {
                this.objeDetaBeca.setEstaDetaBeca(1);
            }
            
            //Desactivar el detalle creado por la transacción
            this.objeDeta = FCDEDeta.findDetaTran(this.objeTran.getCodiTran());
            this.objeDeta.setEstaDeta(0);
            
            System.out.println("Monto que envía al método: " +this.objeTran.getMontTran());
            
            //Enviar al método para sumar el monto
            boolean conf2 = this.reem(this.objeTran.getMontTran(), 2);
            
            if (conf2) {
                System.out.println("Obje tran: " +this.objeTran);
                System.out.println("Obje detalle: "+this.objeDeta);
                //Modificar el detalle de beca
                FCDEDetaBeca.edit(objeDetaBeca);
                //Modificar el detalle de beca
                FCDEDeta.edit(objeDeta);
                //Proceso normal de modificación de la transacción
                this.listTran.remove(this.objeTran); //Limpia el objeto viejo
                this.objeTran.setEstaTran(0);
                FCDETran.edit(this.objeTran);
               //coñoxd
                this.listTran.add(this.objeTran); //Agrega el objeto modificado
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
                log.info("Transaccion Modificada");
            }
            
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al desactivar la entrada de donación ')");
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
    
    //Modificar this para consultar el tipo de transacción y elegir el form a mostrar
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara"));
        try
        {
            this.objeTran = FCDETran.find(codi);
            if(objeTran.getTipoTran()==2)
            {
                this.objeCombPadr = objeTran.getCodiDetaBeca().getCodiBeca().getCodiSoliBeca();
                onDetalleSelect();
            }
            
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
    
    /**
     * Este método sirve para las operaciones de sumar y restar montos de transacciones cuando se desactivan, realiza un nuevo insert a la tabla transacciones como reembolso del dinero
     * @param monto de transacciones que se desea sumar o restar del fondo total
     * @param tipo para decidir si se resta o suma del fondo total (1: restar, 2: sumar)
     * @return 
     */
    public boolean reem(BigDecimal monto, int tipo){
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            System.out.println("Monto a desactivar: "+monto);
            System.out.println("Tipo: "+tipo);
            //Obtener el monto total al que hay que restarle el monto de transacciones
            this.objeTranTemp = FCDETran.findLast();
            this.objeTranDesa.setMontTran(monto);
            //Obtener la fecha del sistema
            Date fecha = new Date();
            this.objeTranDesa.setFechTran(fecha);
            switch (tipo) {
                case 1:
                    //Si el monto total disponible es menor a la cantidad que se desea restar la transacción no puede realizarse
                    //1 el primer monto es mayor al segundo
                    if (this.objeTranTemp.getMontTota().compareTo(monto) == 1 ) {
                        this.objeTranDesa.setMontTota(objeTranTemp.getMontTota().subtract(monto));
                        //Variable para guardar
                        this.guarTran = true;
                    } else {
                        this.guarTran = false;
                    }
                    break;
                case 2:
                    this.objeTranDesa.setMontTota(objeTranTemp.getMontTota().add(monto));
                    this.guarTran = true;
                    break;
                default:
                    this.guarTran = false;
                    break;
            }
            if (guarTran) {
                //setear el estado de tran xd
                this.objeTranDesa.setEstaTran(1);
                this.objeTranDesa.setTipoTran(3);
                //Para cuando cree la transacción
                FCDETran.create(this.objeTranDesa);
                this.listTran.add(this.objeTranDesa);
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
                log.info("Fondo total actualizado");
                conf = true;
            }
            else{
                 ctx.execute("setMessage('MESS_ERRO', 'Atención', 'El fondo no ha podido ser actualizado')");
                 conf = false;
            }
        } catch (Exception e) {
            System.out.println("Errosito:"+e.getMessage());
        }
        return conf;
    }
}
