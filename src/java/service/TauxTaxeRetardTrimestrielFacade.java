/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.TauxTaxeRetardTrimestriel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc
 */
@Stateless
public class TauxTaxeRetardTrimestrielFacade extends AbstractFacade<TauxTaxeRetardTrimestriel> {

    @PersistenceContext(unitName = "projet_jeePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TauxTaxeRetardTrimestrielFacade() {
        super(TauxTaxeRetardTrimestriel.class);
    }
    
}
