/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.TipoSeguimiento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Morenita
 */
@Local
public interface TipoSeguimientoFacadeLocal {

    void create(TipoSeguimiento tipoSeguimiento);

    void edit(TipoSeguimiento tipoSeguimiento);

    void remove(TipoSeguimiento tipoSeguimiento);

    TipoSeguimiento find(Object id);

    List<TipoSeguimiento> findAll();

    List<TipoSeguimiento> findRange(int[] range);

    int count();
    
}
