/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author MarouaneKH
 */
@Entity
public class TaxeTrimestriel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal chiffreAffaire;
    private BigDecimal montantRetardTotal;
    private int numeroTrimestre;
    private BigDecimal retardMois;
    private BigDecimal retardAutresMois;
    private int nbrMoisRetard;

    @ManyToOne
    private Local local;

    @ManyToOne
    User user;

    @ManyToOne
    TaxeAnnuel taxeAnnuel;

    @ManyToOne
    Redevable redevable;

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaxeAnnuel getTaxeAnnuel() {
        return taxeAnnuel;
    }

    public void setTaxeAnnuel(TaxeAnnuel taxeAnnuel) {
        this.taxeAnnuel = taxeAnnuel;
    }

    public Redevable getRedevable() {
        return redevable;
    }

    public void setRedevable(Redevable redevable) {
        this.redevable = redevable;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getChiffreAffaire() {
        return chiffreAffaire;
    }

    public void setChiffreAffaire(BigDecimal chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }

    public BigDecimal getMontantRetardTotal() {
        return montantRetardTotal;
    }

    public void setMontantRetardTotal(BigDecimal montantRetardTotal) {
        this.montantRetardTotal = montantRetardTotal;
    }

    public int getNumeroTrimestre() {
        return numeroTrimestre;
    }

    public void setNumeroTrimestre(int numeroTrimestre) {
        this.numeroTrimestre = numeroTrimestre;
    }

    public BigDecimal getRetardMois() {
        return retardMois;
    }

    public void setRetardMois(BigDecimal retardMois) {
        this.retardMois = retardMois;
    }

    public BigDecimal getRetardAutresMois() {
        return retardAutresMois;
    }

    public void setRetardAutresMois(BigDecimal retardAutresMois) {
        this.retardAutresMois = retardAutresMois;
    }

    public int getNbrMoisRetard() {
        return nbrMoisRetard;
    }

    public void setNbrMoisRetard(int nbrMoisRetard) {
        this.nbrMoisRetard = nbrMoisRetard;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxeTrimestriel)) {
            return false;
        }
        TaxeTrimestriel other = (TaxeTrimestriel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.TaxeTrimestriel[ id=" + id + " ]";
    }

}
