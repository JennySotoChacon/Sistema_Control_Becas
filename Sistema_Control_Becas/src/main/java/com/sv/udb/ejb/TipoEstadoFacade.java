/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.TipoEstado;
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
public class TipoEstadoFacade extends AbstractFacade<TipoEstado> implements TipoEstadoFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEstadoFacade() {
        super(TipoEstado.class);
    }
    
    @Override
    public List<TipoEstado> findAllIna() {
        Query q = getEntityManager().createNativeQuery("SELECT * FROM tipo_estado WHERE codi_tipo_esta != 2", TipoEstado.class);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : resu;
    }
    
    @Override
    public List<TipoEstado> findAllN() {
        Query q = getEntityManager().createNativeQuery("SELECT * FROM tipo_estado WHERE codi_tipo_esta != 1 and codi_tipo_esta != 2 and codi_tipo_esta != 3", TipoEstado.class);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : resu;
    }
    
}
