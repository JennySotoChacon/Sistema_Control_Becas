/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.utils.Archivo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Mauricio
 */
@Named(value = "uploadBean")
@ViewScoped
public class UploadBean implements Serializable {
    private Part file;
    private String carnet;
    
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
    
    public UploadBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.listNombFile = new ArrayList<>();
        this.consTodo();
    }
    
    public void uploFile()
    {
        String Path = "C:/Users/Ariel/Desktop/becas/";
        try
        {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
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
            
        }
    }
    
    private void moveFilePart(Part item,String path) throws IOException
    {
        if(item.getName().equals(file.getName()))
        {
            this.listNombFile.add(new Archivo(item.getSubmittedFileName(), item.getInputStream(), item.getContentType()));
            this.processFilePart(item, String.format("%s%s",path, item.getSubmittedFileName()));

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
            System.err.println("DDDD> " + filename);
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
    
    public void consTodo()
    {
        try
        {
            File directory = new File("C:/Users/Ariel/Desktop/becas/");
            //get all the files from a directory
            File[] fList = directory.listFiles();
            for (File file : fList){
                InputStream targetStream = new FileInputStream(file);
                 this.listNombFile.add(new Archivo(file.getName().split(".").toString().substring(0), targetStream, file.getName()));
                System.out.println(file.getName());
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
           
        }
        finally
        {
            
        }
    }
    
}
