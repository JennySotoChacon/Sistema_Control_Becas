/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.TipoRetiro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author eduardo
 */
@Local
public interface TipoRetiroFacadeLocal {

    void create(TipoRetiro tipoRetiro);

    void edit(TipoRetiro tipoRetiro);

    void remove(TipoRetiro tipoRetiro);

    TipoRetiro find(Object id);

    List<TipoRetiro> findAll();

    List<TipoRetiro> findRange(int[] range);

    int count();
    
}
