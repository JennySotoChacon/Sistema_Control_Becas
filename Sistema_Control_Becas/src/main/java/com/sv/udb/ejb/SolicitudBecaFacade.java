/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.SolicitudBeca;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author eduardo
 */
@Stateless
public class SolicitudBecaFacade extends AbstractFacade<SolicitudBeca> implements SolicitudBecaFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudBecaFacade() {
        super(SolicitudBeca.class);
    }
    
    @Override
    public SolicitudBeca findLast()
    {
        Query q = getEntityManager().createNativeQuery("select * from solicitud_beca order by fech_soli_beca DESC limit 1", SolicitudBeca.class);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : (SolicitudBeca)resu.get(0);
    }
}
