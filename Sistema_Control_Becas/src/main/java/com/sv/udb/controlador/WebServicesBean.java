/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.utils.AlumnosPojo;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import org.primefaces.context.RequestContext;

/**
 *
 * @author REGISTRO
 */
@Named(value = "webServicesBean")
@ViewScoped
public class WebServicesBean implements Serializable{

    private static final long serialVersionUID = 1L;
    private String nombUsua;
    private String filt; //Filotro de búsqueda

    public String getNombUsua() {
        return nombUsua;
    }

    public void setNombUsua(String nombUsua) {
        this.nombUsua = nombUsua;
    }

    public String getFilt() {
        return filt;
    }

    public void setFilt(String filt) {
        this.filt = filt;
    }
    
    /**
     * Creates a new instance of WebServicesBean
     */
    
    public WebServicesBean() {
    }
    
    public void nuev()
    {
        this.nombUsua = "";
    }
    
    public void consWebServ()
    {
        Client client = ClientBuilder.newClient();
        String url = String.format("http://localhost:8080/WebService/poo/MiServicio/%s", this.filt);
        WebTarget resource = client.target(url);
        Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        Response response = request.get();
        if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL)
        {
            AlumnosPojo resp = response.readEntity(AlumnosPojo.class); //La respuesta de captura en un pojo que esta en el paquete utils
            this.nombUsua = resp.getNomb();
        }
        else
        {
            this.nombUsua = "No hay nada";
        }
    }
    
    //Lógica slider
    private boolean showBusc = false;

    public boolean isShowBusc() {
        return showBusc;
    }
    
    public void abri()
    {
        
    }
    
    public void limpForm()
    {
        
    }
    /*
    * Toogle buscador, cambia el valor del buscador
    */
    public void toogBusc()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.showBusc = !this.showBusc;
    }
    
}
