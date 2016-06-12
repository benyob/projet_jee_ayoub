/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Local;
import bean.TaxeAnnuel;
import bean.TaxeTrimestriel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    

    public List<TaxeAnnuel> getTaxeAnnuelByRedevable(double id) {
        System.out.println("SELECT TX FROM TaxeAnnuel TX WHERE TX.taxeAnnuel.id=" + id);
        Query query = em.createQuery("SELECT TX FROM TaxeAnnuel TX WHERE TX.redevable.id = :idd");
        query.setParameter("idd", id);
        System.out.println("Ha size " + query.getResultList().size());
        return query.getResultList();
    }
    

    public List<TaxeTrimestriel> getTaxeTrimByTaxeAnnuel(double id) {
        List<TaxeTrimestriel> list = em.createQuery("SELECT TX FROM TaxeTrimestriel TX WHERE TX.taxeAnnuel.id = :id").setParameter("id", id).getResultList();
        System.out.println("Ha size " + list.size());
        return list;
    }
}
