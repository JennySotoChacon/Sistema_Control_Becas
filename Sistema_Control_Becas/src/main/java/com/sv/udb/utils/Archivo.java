/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utils;

import java.io.InputStream;

/**
 *
 * @author REGISTRO
 */
public class Archivo {
    private String nomb;
    private InputStream file;
    private String tipo;
    private String url;
    private String exte;
    private byte[] fileInbyte;

    public Archivo(String nomb, InputStream file, String tipo, String url) {
        this.nomb = nomb;
        this.file = file;
        this.tipo = tipo;
        this.url = url;
    }

    public Archivo(String nomb, String exte) {
        this.nomb = nomb;
        this.exte = exte;
    }

    public Archivo(String nomb, InputStream file, String tipo, String url, String exte) {
        this.nomb = nomb;
        this.file = file;
        this.tipo = tipo;
        this.url = url;
        this.exte = exte;
    }
    public Archivo(String nomb, InputStream file, String tipo) {
        this.nomb = nomb;
        this.file = file;
        this.tipo = tipo;       
    }

    public Archivo(String nomb, InputStream file, String tipo, String url, String exte, byte[] fileInbyte) {
        this.nomb = nomb;
        this.file = file;
        this.tipo = tipo;
        this.url = url;
        this.exte = exte;
        this.fileInbyte = fileInbyte;
    }

    public Archivo(String nomb, InputStream file, String tipo, byte[] fileInbyte) {
        this.nomb = nomb;
        this.file = file;
        this.tipo = tipo;
        this.fileInbyte = fileInbyte;
    }
    
    public byte[] getFileInbyte() {
        return fileInbyte;
    }

    public void setFileInbyte(byte[] fileInbyte) {
        this.fileInbyte = fileInbyte;
    }

    public String getUrl() {
        return url;
    }

    public String getExte() {
        return exte;
    }

    public void setExte(String exte) {
        this.exte = exte;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
