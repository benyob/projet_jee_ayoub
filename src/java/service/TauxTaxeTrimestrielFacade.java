/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.TauxTaxeTrimestriel;
import bean.TaxeTrimestriel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MarouaneKH
 */
@Stateless
public class TauxTaxeTrimestrielFacade extends AbstractFacade<TauxTaxeTrimestriel> {

    @PersistenceContext(unitName = "projet_jeePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TauxTaxeTrimestrielFacade() {
        super(TauxTaxeTrimestriel.class);
    }

    public TauxTaxeTrimestriel getTauxApplicable(int annee, int numTrimestre) {
        Date dateTrimetre = null;

        String dateString = annee + "-";

        if (numTrimestre == 1) {
            dateString += "1-1";
        } else if (numTrimestre == 2) {
            dateString += "4-1";
        } else if (numTrimestre == 3) {
            dateString += "7-1";
        } else if (numTrimestre == 4) {
            dateString += "10-1";
        } else {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            dateTrimetre = sdf.parse(dateString);
        } catch (ParseException ex) {
            Logger.getLogger(TauxTaxeTrimestrielFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.sql.Date dateSql = new java.sql.Date(dateTrimetre.getTime());
        System.out.println("------------ha sql " + dateSql);

        List<TauxTaxeTrimestriel> res = em.createQuery("SELECT TTT FROM TauxTaxeTrimestriel TTT WHERE TTT.dateApplication < :dateTrim ORDER BY TTT.dateApplication DESC").setParameter("dateTrim", dateSql).getResultList();

        if (res.isEmpty()) {
            return null;
        } else {
            return res.get(0);
        }
    }

}
