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
import javax.persistence.Query;

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
    


    public List<TaxeTrimestriel> getTaxeTrimByRedevable(double id) {
        System.out.println("SELECT TX FROM TaxeTrimestriel TX WHERE TX.taxeAnnuel.id=" + id);
        Query query = em.createQuery("SELECT TX FROM TaxeTrimestriel TX WHERE TX.redevable.id = :idd");
        query.setParameter("idd", id);
        System.out.println("Ha size " + query.getResultList().size());
        return query.getResultList();
    }
    public List<TaxeTrimestriel> getTaxeTrimByRedevableNonPayee(double id) {
        System.out.println("SELECT TX FROM TaxeTrimestriel TX WHERE TX.taxeAnnuel.id=" + id);
        Query query = em.createQuery("SELECT TX FROM TaxeTrimestriel TX WHERE TX.redevable.id = :idd");
        query.setParameter("idd", id);
        List<TaxeTrimestriel> liste=query.getResultList();
        TaxeTrimestriel dernieretaxe=liste.get(liste.size()-1);
        if(dernieretaxe.getNumeroTrimestre()==1)
        return query.getResultList();
        return null;
    }

}
