/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Donacion;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Owner
 */
@Stateless
public class DonacionFacade extends AbstractFacade<Donacion> implements DonacionFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DonacionFacade() {
        super(Donacion.class);
    }
    
    @Override
    public List<Donacion> findForCombo2(Object id)  {        
        String consulta = "SELECT d.codi_dona, d.codi_empr, d.nomb_dona, d.plaz_dona, d.cant_cuot, d.mont_tot, d.mont_pend, d.fech_dona, d.esta_dona, d.codi_tipo_dona "
                + "FROM donacion d WHERE d.codi_empr = ?1 AND d.esta_dona = 1";
        Query q = getEntityManager().createNativeQuery(consulta, Donacion.class);           
        //Query q = getEntityManager().createNativeQuery("select * from detalle_beca", DetalleBeca.class);           
        q.setParameter(1, id);          
        List resu = q.getResultList();   
        
        
        System.out.println(Arrays.toString(resu.toArray()));
        return resu.isEmpty() ? null : resu;
    }
    
}
