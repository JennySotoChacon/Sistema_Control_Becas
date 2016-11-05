/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Detalle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author eduardo
 */
@Stateless
public class DetalleFacade extends AbstractFacade<Detalle> implements DetalleFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleFacade() {
        super(Detalle.class);
    }
    
    @Override
    public Detalle findDetaTran(Object id){
        Query q = getEntityManager().createNativeQuery("SELECT * FROM detalle WHERE codi_tran = ?1", Detalle.class);
        q.setParameter(1, id);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : (Detalle)resu.get(0);
    }
    
}
