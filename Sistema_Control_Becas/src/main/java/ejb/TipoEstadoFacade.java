/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import com.sv.udb.modelo.TipoEstado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ferna
 */
@Stateless
public class TipoEstadoFacade extends AbstractFacade<TipoEstado> implements TipoEstadoFacadeLocal {
    @PersistenceContext(unitName = "BecasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEstadoFacade() {
        super(TipoEstado.class);
    }
    
}
