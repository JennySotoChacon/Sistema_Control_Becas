/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Beca;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author eduardo
 */
@Local
public interface BecaFacadeLocal {

    void create(Beca beca);

    void edit(Beca beca);

    void remove(Beca beca);

    Beca find(Object id);
    
    Beca findSoli(Object id);

    List<Beca> findAll();
    
    List<Beca> findAllH();
    
    List<Beca> findAllHisto();

    List<Beca> findRange(int[] range);

    int count();
    
}
