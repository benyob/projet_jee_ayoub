/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.TaxeAnnuel;
import bean.TaxeTrimestriel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MarouaneKH
 */
@Stateless
public class TaxeTrimestrielFacade extends AbstractFacade<TaxeTrimestriel> {

    @PersistenceContext(unitName = "projet_jeePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaxeTrimestrielFacade() {
        super(TaxeTrimestriel.class);
    }

    public List<TaxeTrimestriel> getTaxeTrimByTaxeAnnuel(TaxeAnnuel taxeAnnuel) {
        System.out.println("SELECT TX FROM TaxeTrimestriel TX WHERE TX.taxeAnnuel.id=" + taxeAnnuel.getId());
        List<TaxeTrimestriel> list = em.createQuery("SELECT TX FROM TaxeTrimestriel TX WHERE TX.taxeAnnuel.id = :id").setParameter("id", taxeAnnuel.getId()).getResultList();;
        System.out.println("Ha size " + list.size());
        return list;

    }

}
