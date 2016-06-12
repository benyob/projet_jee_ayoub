/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author MarouaneKH
 */
@Entity
public class Redevable implements Serializable {

    @OneToMany(mappedBy = "redevable")
    private List<TaxeTrimestriel> taxeTrimestriels;

    @OneToMany(mappedBy = "redevable")
    private List<TaxeAnnuel> taxeAnnuels;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tel;
    private String email;
    private String adresse;
    private String nom;
    private String prenom;
    private String cin;
    private String rc;
    private Boolean personnePhysique;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TaxeTrimestriel> getTaxeTrimestriels() {
        return taxeTrimestriels;
    }

    public void setTaxeTrimestriels(List<TaxeTrimestriel> taxeTrimestriels) {
        this.taxeTrimestriels = taxeTrimestriels;
    }

    public List<TaxeAnnuel> getTaxeAnnuels() {
        return taxeAnnuels;
    }

    public void setTaxeAnnuels(List<TaxeAnnuel> taxeAnnuels) {
        this.taxeAnnuels = taxeAnnuels;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public Boolean getPersonnePhysique() {
        return personnePhysique;
    }

    public void setPersonnePhysique(Boolean personnePhysique) {
        this.personnePhysique = personnePhysique;
    }

    public Long getId() {
        return id;
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
        if (!(object instanceof Redevable)) {
            return false;
        }
        Redevable other = (Redevable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

}
