/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Redevable;
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
public class RedevableFacade extends AbstractFacade<Redevable> {

    @PersistenceContext(unitName = "projet_jeePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RedevableFacade() {
        super(Redevable.class);
    }

    public List<TaxeTrimestriel> getMyTaxeTrimstriel(double id) {
        List<TaxeTrimestriel> liste = em.createQuery("SELECT * FROM TaxeTrimestriel WHERE redevable_id ="+id).getResultList();
        System.out.println("la taille est :"+liste.size());
        return liste;
    }

    public List<TaxeAnnuel> getMyTaxeAnnuel(double id) {
        List<TaxeAnnuel> liste = em.createQuery("SELECT * FROM TaxeAnnuel WHERE redevable_id = :id").setParameter("id", id).getResultList();
        return liste;
    }
    
        public Redevable chercherRedevable(String login,String password) {
        List<Redevable> liste = em.createQuery("SELECT RD FROM Redevable RD WHERE RD.login ='"+login+"' AND RD.password='"+password+"'").getResultList();
        return liste.get(0);
    }
}
