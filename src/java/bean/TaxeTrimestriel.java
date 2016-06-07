/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

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

    private BigDecimal taxe;
    private BigDecimal retard;
    private BigDecimal totalTaxe;

    private int numeroTrimestre;
    private int nbrMoisRetard;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePresentation;

    @ManyToOne
    User user;

    @ManyToOne
    TaxeAnnuel taxeAnnuel;

    @ManyToOne
    Redevable redevable;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTaxe() {
        return taxe;
    }

    public void setTaxe(BigDecimal taxe) {
        this.taxe = taxe;
    }

    public BigDecimal getRetard() {
        return retard;
    }

    public void setRetard(BigDecimal retard) {
        this.retard = retard;
    }

    public BigDecimal getTotalTaxe() {
        return totalTaxe;
    }

    public void setTotalTaxe(BigDecimal totalTaxe) {
        this.totalTaxe = totalTaxe;
    }

    public Date getDatePresentation() {
        return datePresentation;
    }

    public void setDatePresentation(Date datePresentation) {
        this.datePresentation = datePresentation;
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

    public int getNumeroTrimestre() {
        return numeroTrimestre;
    }

    public void setNumeroTrimestre(int numeroTrimestre) {
        this.numeroTrimestre = numeroTrimestre;
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
