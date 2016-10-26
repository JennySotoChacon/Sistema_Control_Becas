/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Estructura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author eduardo
 */
@Local
public interface EstructuraFacadeLocal {

    void create(Estructura estructura);

    void edit(Estructura estructura);

    void remove(Estructura estructura);

    Estructura find(Object id);

    List<Estructura> findAll();

    List<Estructura> findRange(int[] range);

    int count();
    
}
