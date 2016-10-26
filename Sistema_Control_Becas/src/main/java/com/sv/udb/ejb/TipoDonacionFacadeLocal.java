/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.TipoDonacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author eduardo
 */
@Local
public interface TipoDonacionFacadeLocal {

    void create(TipoDonacion tipoDonacion);

    void edit(TipoDonacion tipoDonacion);

    void remove(TipoDonacion tipoDonacion);

    TipoDonacion find(Object id);

    List<TipoDonacion> findAll();

    List<TipoDonacion> findRange(int[] range);

    int count();
    
}
