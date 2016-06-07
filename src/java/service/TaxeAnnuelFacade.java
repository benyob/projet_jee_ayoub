/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Local;
import bean.TaxeAnnuel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MarouaneKH
 */
@Stateless
public class TaxeAnnuelFacade extends AbstractFacade<TaxeAnnuel> {

    @PersistenceContext(unitName = "projet_jeePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaxeAnnuelFacade() {
        super(TaxeAnnuel.class);
    }

    public TaxeAnnuel getDernierTaxeTrimestriel(Local local) {
        List<TaxeAnnuel> res = em.createQuery("SELECT TA from TaxeAnnuel TA WHERE TA.local.id = :id order by TA.annee DESC").setParameter("id", local.getId()).getResultList();

        if (res.isEmpty()) {
            return null;
        }
        return res.get(0);
    }
}
