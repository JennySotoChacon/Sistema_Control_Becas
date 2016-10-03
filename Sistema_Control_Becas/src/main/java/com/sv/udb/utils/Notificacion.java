/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utils;

/**
 *
 * @author Mauricio
 */
public class Notificacion {
    private String desc; //Descripción
    private boolean leid; //Leido

    public Notificacion(String desc, boolean leid) {
        this.desc = desc;
        this.leid = leid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isLeid() {
        return leid;
    }

    public void setLeid(boolean leid) {
        this.leid = leid;
    }
    
}
