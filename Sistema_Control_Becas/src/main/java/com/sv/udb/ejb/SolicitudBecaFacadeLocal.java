/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.SolicitudBeca;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author eduardo
 */
@Local
public interface SolicitudBecaFacadeLocal {

    void create(SolicitudBeca solicitudBeca);

    void edit(SolicitudBeca solicitudBeca);

    void remove(SolicitudBeca solicitudBeca);

    SolicitudBeca find(Object id);

    List<SolicitudBeca> findAll();

    List<SolicitudBeca> findRange(int[] range);
    
    SolicitudBeca findLast();

    int count();
    
}
