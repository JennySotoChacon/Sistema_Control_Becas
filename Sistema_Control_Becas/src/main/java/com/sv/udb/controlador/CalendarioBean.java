/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.primefaces.model.DefaultScheduleEvent;
import com.sv.udb.controlador.SeguimientoBean;
import com.sv.udb.ejb.SeguimientoFacade;
import com.sv.udb.ejb.SeguimientoFacadeLocal;
import com.sv.udb.modelo.Seguimiento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author REGISTRO
 */
@Named(value = "calendarioBean")
@ViewScoped
public class CalendarioBean implements Serializable{
    private static final long serialVersionUID = 6527333208194203406L;
    private ScheduleModel objeCale;
    private List<Seguimiento> listSegu;
    @EJB
    private SeguimientoFacadeLocal FCDESegu;
    /**
     * Creates a new instance of CalendarioBean
     */
    public CalendarioBean() {
    }

    @PostConstruct
    public void init()
    {
        objeCale = new DefaultScheduleModel();
        listSegu = new ArrayList<Seguimiento>();
        getEvenCale();
                
    }
    
    public ScheduleModel getObjeCale() {
        return objeCale;
    }
    
    public void conListSeguProg()
    {
        try
        {
            this.listSegu = FCDESegu.findByEstaSegu();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void getEvenCale(){
        System.out.println("Entra a getEvenCale");
        try{
            conListSeguProg();//se consultan los seguimientos
            if(listSegu == null) listSegu= new ArrayList<Seguimiento>();
            for(Seguimiento obj: listSegu){//--> recorrer lista
                Seguimiento  objeSegu = getSegu(obj);
                if(objeSegu == null)objeSegu = new Seguimiento();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                String FechFina = sdf.format(objeSegu.getFechFin());
                String FechInic = sdf.format(objeSegu.getFechInicio());
                DefaultScheduleEvent evt = new DefaultScheduleEvent();
                evt.setEndDate(sdf.parse(FechFina));
                evt.setStartDate(sdf.parse(FechInic));
                //evt.setTitle(objeSegu.getNombSegu());
                evt.setTitle(obj.getDescSegu()+ " " + "\nHora inicio: " + objeSegu.getFechInicio()+ "\nHora final: " + objeSegu.getFechFin());
                evt.setData(obj.getCodiSegu());
                evt.setDescription(obj.getDescSegu());
                objeCale.addEvent(evt);
             }
        }catch(Exception e){
            System.out.println("CatchxdXD");
            e.printStackTrace();
        }  
     }
    
    public Seguimiento getSegu(Seguimiento Segu)
    {
        Seguimiento objeSegu = null;
        try
        {
            objeSegu = FCDESegu.findByCodiSegu(Segu);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return objeSegu;
    }
    
    private Date getFecha(String date) 
    {
        Date fecha = null;
        if (date != null){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                fecha = sdf.parse(date);
            } catch (Exception e) {
                fecha = null;
            }
        }
        return fecha;
    }
    
}
