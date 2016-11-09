/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.DetalleBeca;
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
public class DetalleBecaFacade extends AbstractFacade<DetalleBeca> implements DetalleBecaFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<DetalleBeca> findForCombo(Object id)  {
              
        System.out.println("del facade: "+id);
        
        String consulta = "select d.codi_deta_beca,d.codi_beca,d.codi_tipo_beca,d.cant_mese,d.esta_deta_beca "
                + "from detalle_beca d inner JOIN beca b on  d.codi_beca = b.codi_beca "
                + "inner JOIN solicitud_beca s on  b.codi_soli_beca = s.codi_soli_beca  where s.codi_soli_beca =?1 and d.esta_deta_beca = 1";
        Query q = getEntityManager().createNativeQuery(consulta, DetalleBeca.class);           
        //Query q = getEntityManager().createNativeQuery("select * from detalle_beca", DetalleBeca.class);           
        q.setParameter(1, id);          
        List resu = q.getResultList();   
        
        
        System.out.println(Arrays.toString(resu.toArray()));
        return resu.isEmpty() ? null : resu;
    }
    

    public DetalleBecaFacade() {
        super(DetalleBeca.class);
    }
    
}
