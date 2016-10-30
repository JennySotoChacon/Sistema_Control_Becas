/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Mauricio
 */
@FacesComponent(value = "com.sv.udb.utils.CustomComponent", createTag = true,
        namespace = "http://xmlns.udb.edu.sv/component", tagName = "PdfViewer")
public class CustomComponent extends UIComponentBase {
    
    @Override
    public String getFamily() {
        return "com.sv.udb.components";
    }
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        byte[] value = (byte[]) getAttributes().get("value");
        String width = (String) getAttributes().get("width");
        String id = (String) getAttributes().get("id");
        String height = (String) getAttributes().get("height");
        //String sourcePdf = (String) getAttributes().get("src");
        String sourcePdf = "pdfjs/pdf.js";
        String pathRsrc;
        String PathFile = this.getResourcePath(sourcePdf, context);
        PathFile = this.getFileResource(PathFile);
        ResponseWriter resp = context.getResponseWriter();
        if(sourcePdf != null)
        {
            String viewerPath = this.getResourcePath(sourcePdf, context);
            if(viewerPath != null)
            {
                File htmlPage = null;
                if(value != null)
                {
                    pathRsrc = PathFile;
                    PathFile = String.format("%s%s", PathFile, "viewer.txt");
                    byte[] page = downloadUrl(new URL(this.getServerName(context) + PathFile));
                    String path = this.getFileContent(page).replaceFirst("%DATAX%", this.encodeFile(value)).replaceAll("%DATAR%", pathRsrc);
                    resp.startElement("iframe", this);
                    resp.writeAttribute("width", width, null);
                    resp.writeAttribute("height", height, null);
                    resp.writeAttribute("id", id, null);
                    resp.writeAttribute("srcdoc", path, null);
                    resp.endElement("iframe");
                }
                else
                {
                    PathFile = String.format("%s%s", PathFile, "blank.txt");
                    byte[] page = downloadUrl(new URL(this.getServerName(context) + PathFile));
                    String path = this.getFileContent(page);
                    resp.startElement("iframe", this);
                    resp.writeAttribute("width", width, null);
                    resp.writeAttribute("height", height, null);
                    resp.writeAttribute("id", id, null);
                    resp.writeAttribute("srcdoc", path, null);
                    resp.endElement("iframe");
                }
            }
            else
            {
                resp.write("<div style=\"color: red;\">PdfViewer: recursos PDF no han sido encontrados</div>");
            }
        }
        else
        {
            resp.write("<div style=\"color: red;\">PdfViewer: No se ha encontrado el atributo src</div>");
        }
    }
    
    private String getServerName(FacesContext context)
    {
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        String baseURL = url.substring(0, url.length() - request.getRequestURI().length());
        return baseURL;
    }
    
    private byte[] downloadUrl(URL toDownload)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            byte[] chunk = new byte[4096];
            int bytesRead;
            InputStream stream = toDownload.openStream();

            while ((bytesRead = stream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return outputStream.toByteArray();
    }
    
    private String getResourcePath(String file, FacesContext context)
    {
        String resp = null;
        resp = context.getApplication().evaluateExpressionGet(context, "#{resource['" + file + "']}", String.class);
        resp = resp.isEmpty() ? null : resp;
        return resp;
    }
    
    private String getFileResource(String file)
    {
        String resp = "";
        try
        {
            int pos = file.lastIndexOf("/") + 1;
            resp = file.substring(0, pos);
        }
        catch(Exception ex)
        {
            //ex.printStackTrace();
        }
        return resp;
    }
    
    private String getFileContent(byte[] file)
    {
        String resp = "";
        try
        {
            resp = new String(file);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return resp;
    }
    
    private String encodeFile(byte[] file)
    {
        String resp = null;
        try {
            resp = Base64.encodeBase64String(file);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
}
