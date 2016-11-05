/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.DetalleBeca;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Owner
 */
@Local
public interface DetalleBecaFacadeLocal {

    void create(DetalleBeca detalleBeca);

    void edit(DetalleBeca detalleBeca);

    void remove(DetalleBeca detalleBeca);

    DetalleBeca find(Object id);

    List<DetalleBeca> findAll();
    
    List<DetalleBeca> findForCombo(Object id);

    List<DetalleBeca> findRange(int[] range);

    int count();
    
}
