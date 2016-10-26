/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Respuesta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author eduardo
 */
@Local
public interface RespuestaFacadeLocal {

    void create(Respuesta respuesta);

    void edit(Respuesta respuesta);

    void remove(Respuesta respuesta);

    Respuesta find(Object id);

    List<Respuesta> findAll();

    List<Respuesta> findRange(int[] range);

    int count();
    
}
