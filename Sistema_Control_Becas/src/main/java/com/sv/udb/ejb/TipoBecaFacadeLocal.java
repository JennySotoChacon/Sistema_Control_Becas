/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.TipoBeca;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author eduardo
 */
@Local
public interface TipoBecaFacadeLocal {

    void create(TipoBeca tipoBeca);

    void edit(TipoBeca tipoBeca);

    void remove(TipoBeca tipoBeca);

    TipoBeca find(Object id);

    List<TipoBeca> findAll();

    List<TipoBeca> findRange(int[] range);

    int count();
    
}
