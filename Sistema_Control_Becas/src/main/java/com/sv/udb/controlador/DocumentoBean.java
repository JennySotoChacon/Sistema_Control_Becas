/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.modelo.Documento;
import com.sv.udb.ejb.DocumentoFacadeLocal;
import com.sv.udb.utils.Archivo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ferna
 */
@Named(value = "documentoBean")
@ViewScoped
public class DocumentoBean implements Serializable{
    @EJB
    private DocumentoFacadeLocal FCDEDocu;
    private Documento objeDocu;
    private List<Documento> listDocu;
    private boolean guardar;
    private static Logger log = Logger.getLogger(DocumentoBean.class);
    private String rutaC;
    public Documento getObjeDocu() {
        return objeDocu;
    }

    public void setObjeDocu(Documento objeDocu) {
        this.objeDocu = objeDocu;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Documento> getListDocu() {
        return listDocu;
    }

    /**
     * Creates a new instance of DocumentoBean
     */
    public DocumentoBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeDocu = new Documento();
        this.guardar = true;
        this.consTodo();
        this.objeDocu.setFechDocu(new Date());
        this.inicializar();
    }
    
    public void limpForm()
    {
        this.objeDocu = new Documento();
        this.guardar = true;  
        this.objeDocu.setFechDocu(new Date());
    }
    
    public void esta()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeDocu.setEstaDocu(1);                   
            this.objeDocu.setRutaDocu("mis heuvos");
            FCDEDocu.create(this.objeDocu);
            this.listDocu.add(this.objeDocu);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Documento Consultado");
        }
        catch (Exception e) 
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            log.error(getRootCause(e).getMessage());
        }
        finally
        {
            
        }
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.carnet = objeDocu.getCodiSoliBeca().getCarnAlum();
            this.uploFile();
            this.objeDocu.setEstaDocu(1);           
            FCDEDocu.create(this.objeDocu);
            this.listDocu.add(this.objeDocu);
            this.limpForm();
            //this.carnet = objeDocu.getCodiSoliBeca().getCarnAlum();
            //this.uploFile();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Documento Consultado");
        }
        catch (Exception e) 
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            log.error(getRootCause(e).getMessage());
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
            this.listDocu.remove(this.objeDocu); //Limpia el objeto viejo
            FCDEDocu.edit(this.objeDocu);
            this.listDocu.add(this.objeDocu); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Documento Modificado");
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
            this.listDocu.remove(this.objeDocu); //Limpia el objeto viejo
            this.objeDocu.setEstaDocu(0);
            FCDEDocu.edit(this.objeDocu);
            this.listDocu.add(this.objeDocu); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Documento Modificado");
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
            this.listDocu = FCDEDocu.findAll();
            log.info("Documentos Consultados");
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
            this.objeDocu = FCDEDocu.find(codi);
            String h = this.rutas.get(0);
            //System.out.println(h + this.objeDocu.getRutaDocu());
            rutaC = h + this.objeDocu.getRutaDocu();
            //this.objeDocu.setRutaDocu(h + this.objeDocu.getRutaDocu());
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeDocu.getRutaDocu()) + "')");
            log.info("Documento Consultado");
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
    
    
    
    /*para archivos xd*/
    
    private Part file;
    private String carnet;    
    List<String> rutas;
    int DireActuInde;    
    List<Archivo> listNombFile;

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public List<Archivo> getListNombFile() {
        return listNombFile;
    }
    
    /**
     * Creates a new instance of UploadBean
     */
    
    public void inicializar()
    {
        try{
            
            this.listNombFile = new ArrayList<>();
            this.rutas = new ArrayList<>();
            //String ruta ="C:/Users/Ariel/Desktop/becas/";    
            String ruta = "/home/eduardo/Escritorio/asd/";
           rutas.add(ruta);
           DireActuInde = 0;
          //this.consTodo("");
           this.carnet = "";
        }
        catch(Exception e)
        {
            System.out.println(getRootCause(e).getMessage());
        }   
    }    
    public void RegresarRuta()
    {

        rutas.remove(rutas.size() - 1);
        DireActuInde--;
    }
    
    public void uploFile()
    {
        String Path = this.rutas.get(DireActuInde);
        try
        {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            System.out.println(request);
            for(Part item : request.getParts())
            {
                if(this.carnet.trim().length()==0)
                {
                   moveFilePart(item, Path);
                    
                }
                else
                {
                    String newPath= Path+this.carnet+"/";
                    File theDir = new File(newPath);

                    // if the directory does not exist, create it
                    if (!theDir.exists()) {
                        
                        boolean result = false;

                        try{
                            theDir.mkdir();
                            result = true;
                            this.listNombFile.add(new Archivo(carnet,"folder"  ));
                        } 
                        catch(SecurityException se){
                            //handle it
                        }        
                        if(result) {    
                            moveFilePart(item, newPath);
                        }
                    }
                    else
                    {
                        
                        moveFilePart(item, newPath);
                    }
                }
               
            }
            
        }
        catch(Exception ex)
        {
            System.out.println("Error en uploFile"+ex.getMessage());
        }
    }
    
    
    public static byte[] readFully(InputStream input) throws IOException
    {
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = input.read(buffer)) != -1)
        {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }
    
    
    private void moveFilePart(Part item,String path) throws IOException
    {
        try {
            if(item.getName().equals(file.getName()))
            {
                
                System.out.println(item.getSubmittedFileName());
                
                                System.out.println(item.getContentType());
                                
                                
                 this.listNombFile.add(new Archivo(
                        item.getSubmittedFileName(),
                        item.getInputStream(),
                        item.getContentType(),
                        readFully(item.getInputStream())
                ));

               // System.out.println(item.getSubmittedFileName() +" "+item.getInputStream()+" "+ item.getContentType());
                System.out.println("RUTA:" + item.getSubmittedFileName());//Aqui esta la ruta :3 
                
                this.processFilePart(item, String.format("%s%s",path, item.getSubmittedFileName()));
                this.objeDocu.setRutaDocu(this.objeDocu.getCodiSoliBeca().getCarnAlum() + "/" + item.getSubmittedFileName());
             }
        } catch (Exception e) {
            System.out.println("Error en moveFilePart"+e.getMessage());
        }
        
    }
    private void processFilePart(Part part, String filename) throws IOException
    {
        int DEFAULT_BUFFER_SIZE = 2048;
        InputStream input = null;
        OutputStream output = null;
        try
        {
            input = new BufferedInputStream(part.getInputStream(), DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(new FileOutputStream(filename), DEFAULT_BUFFER_SIZE);
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            for (int length = 0; ((length = input.read(buffer)) > 0);)
            {
                output.write(buffer, 0, length);
            }
            
        }
        finally
        {
            if (output != null)
                try
                {
                    output.close();
                }
                catch (IOException logOrIgnore)
                {
                }
            if (input != null)
                try
                {
                    input.close();
                }
                catch (IOException logOrIgnore)
                {
                }
        }

        // how to get the result

        part.delete();
    }
    
     public void consFiles()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        String  ruta = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara");
        try
        {
            System.out.println(ruta);
            if(ruta.equals("Subir"))
            {
                this.RegresarRuta();
                consTodo(ruta);
            }
            else
            {
                String rutaAnte = this.rutas.get(DireActuInde);
                String rutaNuev = rutaAnte +ruta.trim()+"/";
                this.rutas.add(rutaNuev);
                this.DireActuInde++;
                 
                 //consTodo(ruta.trim()+"/");
            }
           
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
            
        }
        finally
        {
            
        }
    }
    
    public void consTodo(String parametro)
    {
        System.out.println("Directorio" + this.DireActuInde);
        System.out.println("Parametro" + parametro.trim());
        System.out.println("Directorio " + this.DireActuInde);
        try
        {
           
           if(parametro.equals("Subir"))
           {
               this.listNombFile.clear();
               parametro="";
           }
           if(parametro.trim().length()!=0)
           {
               
               this.listNombFile.clear();
               this.listNombFile.add(new Archivo("Subir","folder"  ));
           }
           if(this.DireActuInde != 0)
           {
                 this.listNombFile.clear();
               this.listNombFile.add(new Archivo("Subir","folder"  ));
           }
           
            //tags file java investigar
            File directory = new File(this.rutas.get(DireActuInde));
            //System.out.println(this.ruta+parametro);
            //get all the files from a directory
            File[] fList = directory.listFiles();
            for (File file : fList){
                if (file.isFile()) {
                    InputStream targetStream = new FileInputStream(file);                
                    String[] tokens = file.getName().split("\\.(?=[^\\.]+$)");
                    this.listNombFile.add(new Archivo(
                            parametro+file.getName(), 
                            targetStream, 
                            file.getName(),
                            file.getAbsolutePath(),
                            tokens[1],
                             readFully(targetStream)
                    ));
                    //System.out.println(file.getName());
                } else if (file.isDirectory()) {
                    this.listNombFile.add(new Archivo(file.getName(),"folder"  ));
                    
                }
                
            }
            
        }
        catch(Exception ex)
        {
            System.out.println("Error en cons todo"+ex.getMessage());
           
        }
        finally
        {
            
        }
    }
    
    
    //Lógica slider para docuemntos
    private  boolean showDocu = false;

    public boolean isShowDocu() {
        return showDocu;
    }
    
    
    public void toogDocu()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.showDocu = !this.showDocu;
    }
    
     //Lógica slider para imagenes
    private  boolean showImag = false;

    public boolean isShowImag() {
        return showImag;
    }
    
    
    public void toogImag()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.showImag = !this.showImag;
    }
}
