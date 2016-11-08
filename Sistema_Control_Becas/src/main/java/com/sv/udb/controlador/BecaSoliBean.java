/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.ejb.BecaFacadeLocal;
import com.sv.udb.ejb.SolicitudBecaFacadeLocal;
import com.sv.udb.modelo.Beca;
import com.sv.udb.modelo.Grado;
import com.sv.udb.modelo.SolicitudBeca;
import com.sv.udb.modelo.TipoEstado;
import com.sv.udb.utils.AlumnosPojo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author eduardo
 */
@Named(value = "becaSoliBean")
@ViewScoped
public class BecaSoliBean implements Serializable {

    @EJB
    private SolicitudBecaFacadeLocal FCDESoli;
    private SolicitudBeca objeSoli;
    private SolicitudBeca objeSoli2;
    private List<SolicitudBeca> listSoli;
    private List<SolicitudBeca> listSoliH;
    private String filt; //Filotro de búsqueda


    @EJB
    private BecaFacadeLocal FCDEBeca;
    private Beca objeBeca;
    private Beca objeBeca2;
    private List<Beca> listBeca;
    private List<Beca> listBecaH;
    
    
    private boolean guardar; 
    private static Logger log = Logger.getLogger(BecaSoliBean.class);

    public SolicitudBeca getObjeSoli() {
        return objeSoli;
    }

    public void setObjeSoli(SolicitudBeca objeSoli) {
        this.objeSoli = objeSoli;
    }

    public List<SolicitudBeca> getListSoliH() {
        return listSoliH;
    }

    public void setListSoliH(List<SolicitudBeca> listSoliH) {
        this.listSoliH = listSoliH;
    }

    public List<Beca> getListBecaH() {
        return listBecaH;
    }

    public void setListBecaH(List<Beca> listBecaH) {
        this.listBecaH = listBecaH;
    }
    
    

    public List<SolicitudBeca> getListSoli() {
        return listSoli;
    }

    public void setListSoli(List<SolicitudBeca> listSoli) {
        this.listSoli = listSoli;
    }

    public Beca getObjeBeca() {
        return objeBeca;
    }

    public void setObjeBeca(Beca objeBeca) {
        this.objeBeca = objeBeca;
    }

    public List<Beca> getListBeca() {
        return listBeca;
    }

    public void setListBeca(List<Beca> listBeca) {
        this.listBeca = listBeca;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public String getFilt() {
        return filt;
    }

    public void setFilt(String filt) {
        this.filt = filt;
    }
    
    /**
     * Creates a new instance of BecaSoliBean
     */
    public BecaSoliBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeSoli = new SolicitudBeca();
        this.guardar = true;
        this.objeBeca = new Beca();
        this.consTodo();
        this.consTodoH();
    }
    
    public void limpForm()
    {
        this.objeBeca = new Beca();  
        this.objeSoli = new SolicitudBeca();
        this.objeSoli.setFechSoliBeca(new Date());
        this.guardar = true; 
        this.showBusc = false;
        this.beca = false;
        this.elim = false;
        this.empresa = false;
        this.filt = "";
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            TipoEstado a = new TipoEstado();
            a.setCodiTipoEsta(1);
            this.objeSoli.setEstaSoliBeca(1);
            FCDESoli.create(objeSoli);
            this.objeSoli2 = FCDESoli.findLast();
            this.objeBeca.setCodiSoliBeca(objeSoli);
            this.objeBeca.setCodiTipoEsta(a);
            this.objeBeca.setFechInic(new Date());
            this.FCDEBeca.create(objeBeca);
            this.listSoli.add(this.objeSoli);
            System.out.println(this.objeBeca);
            this.listBeca.add(objeBeca);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Beca Guardada");
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
            //this.listSoli.remove(this.objeSoli); //Limpia el objeto viejo
            System.out.println(this.objeSoli);
            System.out.println(this.objeSoli.getNombAlum());
            objeSoli2 = FCDESoli.find(this.objeSoli.getCodiSoliBeca());
            System.out.println(this.objeSoli2.getNombAlum());
            this.objeBeca = FCDEBeca.findSoli(this.objeSoli.getCodiSoliBeca());
            this.objeBeca2 = FCDEBeca.findSoli(this.objeSoli2.getCodiSoliBeca());
            this.listSoli.remove(this.objeSoli);
            this.objeSoli2.setEstaSoliBeca(3);
            FCDESoli.edit(objeSoli2);
            this.listSoli.add(objeSoli2);
            this.objeSoli.setEstaSoliBeca(1);
            FCDESoli.create(objeSoli);
            this.listSoli.add(objeSoli);
            this.listBeca.remove(this.objeBeca2);
            TipoEstado es = new TipoEstado();
            es.setCodiTipoEsta(3);
            this.objeBeca2.setCodiTipoEsta(es);
            this.objeBeca2.setFechBaja(new Date());
            FCDEBeca.edit(objeBeca2);
            //this.listBeca.add(objeBeca2);
            this.objeSoli = FCDESoli.findLast();
            System.out.println(this.objeSoli.getCodiSoliBeca());
            this.objeBeca.setCodiSoliBeca(objeSoli);
            FCDEBeca.create(objeBeca);
            this.listBeca.add(objeBeca);
            //FCDESoli.edit(this.objeSoli);
            //this.listSoli.add(this.objeSoli); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Solicitud Modificada");
            
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
    
    public void reActi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            /*Busca el objeto viejo le setea el estado 3 de historial y lo modifica*/
            this.objeBeca2 = FCDEBeca.findSoli(this.objeBeca.getCodiSoliBeca().getCodiSoliBeca());
            this.listBeca.remove(this.objeBeca2);
            TipoEstado esta = new TipoEstado();
            esta.setCodiTipoEsta(3);
            this.objeBeca.setCodiTipoEsta(esta);
            FCDEBeca.edit(this.objeBeca2);
            this.listBeca.add(this.objeBeca2); //Agrega el objeto modificado
            
            /**/
            this.listSoli.remove(this.objeSoli);
            objeSoli2 = FCDESoli.find(this.objeSoli.getCodiSoliBeca());
            objeSoli2.setEstaSoliBeca(3);
            FCDESoli.edit(objeSoli2);
            this.listSoli.add(objeSoli2);
            
            /**/
            FCDESoli.create(objeSoli);
            this.listSoli.add(objeSoli);
            
            
            /*Setea la informacion ingresada en el objeto e inserta el nuevo objeto en la base*/
            TipoEstado esta2 = new TipoEstado();
            esta2.setCodiTipoEsta(1);
            this.objeBeca.setCodiTipoEsta(esta2);
            this.objeBeca.setRetiBeca(null);
            this.objeBeca.setCodiReti(null);
            this.objeSoli = FCDESoli.findLast();
            this.objeBeca.setCodiSoliBeca(objeSoli);
            this.objeBeca.setFechBaja(null);
            FCDEBeca.create(this.objeBeca);
            this.listBeca.add(this.objeBeca);
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Beca Reactivada')");
            log.info("Beca reactivada");
            this.consTodo();
            
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
            log.error(getRootCause(ex).getMessage());
            System.out.println("AQUI "+ex);
        }
        finally
        {
            
        }
    }
    
    public void desa()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listBeca.remove(this.objeBeca); //Limpia el objeto viejo
            TipoEstado esta = new TipoEstado();
            esta.setCodiTipoEsta(2);
            this.objeBeca.setCodiTipoEsta(esta);
            this.objeBeca.setFechBaja(new Date());
            FCDEBeca.edit(this.objeBeca);
            this.listBeca.add(this.objeBeca); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Beca Reactivada')");
            log.info("Beca reactivada");
            this.consTodo();
            this.showBusc = false;
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
            log.error(getRootCause(ex).getMessage());
            System.out.println("AQUI "+ex);
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
            this.showBusc = false;
            this.objeSoli = FCDESoli.find(codi);
            this.objeBeca = FCDEBeca.findSoli(objeSoli.getCodiSoliBeca());
            this.guardar = false;
            this.filt = objeSoli.getCarnAlum();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeBeca.getCodiSoliBeca().getNombAlum()) + "')");
            log.info("Beca Consultada");
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
    
    public void consTodo()
    {
        try
        {
            this.listSoli = FCDESoli.findAll();
            this.listBeca = FCDEBeca.findAllH();
            log.info("Beca Consultadas");
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
    
    public void consTodoH()
    {
        try
        {
            this.listSoliH = FCDESoli.findAll();
            this.listBecaH = FCDEBeca.findAllHisto();
            log.info("Beca Consultadas");
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
    public void consW()
    {
        RequestContext ctx = RequestContext.getCurrentInstance();
        Client client = ClientBuilder.newClient();
        String url = String.format("http://www.opensv.tk:8080/WebService/MiServicio/consAlum/%s", this.filt);
        WebTarget resource = client.target(url);
        Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        Response response = request.get();
        if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL)
        {
            AlumnosPojo resp = response.readEntity(AlumnosPojo.class); //La respuesta de captura en un pojo que esta en el paquete utils
            this.objeSoli.setCarnAlum(filt);
            this.objeSoli.setNombAlum(resp.getNomb());
            System.out.println(this.objeSoli.getNombAlum());
            Grado grad = new Grado();
            String cortado= resp.getGrad().substring(0,1);
            grad.setCodiGrad(Integer.parseInt(cortado));
            this.objeSoli.setCodiGrad(grad);
            System.out.println(grad.getCodiGrad());
            if(resp.getNomb() == null || resp.getNomb().equals(""))
            {
                ctx.execute("setMessage('MESS_WARN', 'Atención', 'Alumno no encontrado.')");
            }
        }
        else
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar alumno')");
        }
    }
    
    //Lógica slider
    private boolean showBusc = false;
    private boolean empresa = false;
    private boolean beca = false;
    private boolean elim = false;
    private boolean estado = false;
    private boolean detalle = false;
    private boolean grado = false;
    private boolean historia = false;

    public boolean isGrado() {
        return grado;
    }

    public boolean isHistoria() {
        return historia;
    }
    
    public boolean isDetalle() {
        return detalle;
    }
    
    public boolean isEstado() {
        return estado;
    }
    

    public boolean isBeca() {
        return beca;
    }

    public boolean isElim() {
        return elim;
    }

    public boolean isEmpresa() {
        return empresa;
    }
    
    public boolean isShowBusc() {
        return showBusc;
    }
    
    public void toogBusc()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.showBusc = !this.showBusc;
    }
    
    public void empr()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        EmpresaBean asd = new EmpresaBean();
        asd.limpForm();
        this.empresa = !this.empresa;
        this.beca = !this.beca;
    }
    
    public void esta()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.estado = !this.estado;
        this.beca = !this.beca;
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.elim = !this.elim;
        this.beca = !this.beca;
    }
    
    public void beca()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.beca = !this.beca;
    }
    
    public void deta()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.detalle = !this.detalle;
        this.beca = !this.beca;
    }
    
    public void grad()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.grado = !this.grado;
        this.beca = !this.beca;
    }
    
    public void hist()
    {
        System.out.println("historial"+this.historia);
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.historia = !this.historia;
        System.out.println("Hostiral: "+this.historia);
    }
}
