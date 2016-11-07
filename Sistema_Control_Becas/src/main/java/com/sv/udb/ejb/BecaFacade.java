/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Beca;
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
public class BecaFacade extends AbstractFacade<Beca> implements BecaFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BecaFacade() {
        super(Beca.class);
    }
    
    @Override
    public Beca findSoli(Object id)
    {
        Query q = getEntityManager().createNativeQuery("SELECT * FROM beca where codi_soli_beca = ?1", Beca.class);
        q.setParameter(1, id);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : (Beca)resu.get(0);
    }
    
    @Override
    public List<Beca> findAllH()
    {
        Query q = getEntityManager().createNativeQuery("SELECT * FROM beca WHERE codi_tipo_esta != 3", Beca.class);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : resu;
    }
    
    @Override
    public List<Beca> findAllHisto()
    {
        Query q = getEntityManager().createNativeQuery("SELECT * FROM beca WHERE codi_tipo_esta = 3", Beca.class);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : resu;
    }
}
