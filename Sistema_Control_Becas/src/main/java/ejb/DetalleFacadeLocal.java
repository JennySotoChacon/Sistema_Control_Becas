/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import com.sv.udb.modelo.Detalle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ariel
 */
@Local
public interface DetalleFacadeLocal {

    void create(Detalle detalle);

    void edit(Detalle detalle);

    void remove(Detalle detalle);

    Detalle find(Object id);

    List<Detalle> findAll();

    List<Detalle> findRange(int[] range);

    int count();
    
}
