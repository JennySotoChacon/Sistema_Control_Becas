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

    public Archivo(String nomb, InputStream file, String tipo) {
        this.nomb = nomb;
        this.file = file;
        this.tipo = tipo;
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
