/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Seguimiento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author eduardo
 */
@Stateless
public class SeguimientoFacade extends AbstractFacade<Seguimiento> implements SeguimientoFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguimientoFacade() {
        super(Seguimiento.class);
    }
    
    @Override
    public List<Seguimiento> findByEstaSegu() {
        TypedQuery<Seguimiento> q = (TypedQuery<Seguimiento>) getEntityManager().createQuery("SELECT s FROM Seguimiento s WHERE s.estaSegu = 1 ");
        List resu = q.getResultList();
        return (resu.isEmpty()) ? new ArrayList<Seguimiento>() : resu;
    }
    
    @Override
    public Seguimiento findByCodiSegu(Seguimiento codi) {
        TypedQuery<Seguimiento> q = (TypedQuery<Seguimiento>) getEntityManager().createQuery("SELECT s FROM Seguimiento s WHERE s.codiSegu = :codi_segu ORDER BY s.fechInicio desc, s.fechFin desc ").setMaxResults(1);    
        q.setParameter("codi_segu", codi.getCodiSegu());
        Seguimiento resu = q.getSingleResult();
        return (resu == null) ? null : resu;
    }
    
}
