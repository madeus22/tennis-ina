/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ina.tennis;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amadeus
 */
@Stateless
public class SessionTypeFacade extends AbstractFacade<SessionType> {
    @PersistenceContext(unitName = "fr.ina_tennis-ina_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SessionTypeFacade() {
        super(SessionType.class);
    }
    
}
