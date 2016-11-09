/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Donacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Owner
 */
@Local
public interface DonacionFacadeLocal {

    void create(Donacion donacion);

    void edit(Donacion donacion);

    void remove(Donacion donacion);

    Donacion find(Object id);

    List<Donacion> findAll();

    List<Donacion> findRange(int[] range);
    
    List<Donacion> findForCombo2(Object id);

    int count();
    
}
