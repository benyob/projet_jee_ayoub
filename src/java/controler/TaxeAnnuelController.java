package controler;

import bean.Local;
import bean.Redevable;
import bean.TauxTaxeRetardTrimestriel;
import bean.TauxTaxeTrimestriel;
import bean.TaxeAnnuel;
import bean.TaxeTrimestriel;
import controler.util.JsfUtil;
import controler.util.JsfUtil.PersistAction;
import controler.util.SessionUtil;
import service.TaxeAnnuelFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.LocalFacade;
import service.RedevableFacade;
import service.TauxTaxeRetardTrimestrielFacade;
import service.TauxTaxeTrimestrielFacade;
import service.TaxeTrimestrielFacade;

@Named("taxeAnnuelController")
@SessionScoped
public class TaxeAnnuelController implements Serializable {

    @EJB
    private service.TaxeAnnuelFacade ejbFacade;

    private List<TaxeAnnuel> items = null;
    private List<TaxeAnnuel> liste = null;
    private List<TaxeTrimestriel> listeTrim = null;

    private TaxeAnnuel selected;

    @EJB
    private service.TaxeTrimestrielFacade taxeTrimestrielFacade;
    @EJB
    private service.RedevableFacade redevableFacade;
    @EJB
    private service.LocalFacade localFacade;
    @EJB
    private service.TauxTaxeTrimestrielFacade tauxTaxeTrimestrielFacade;
    @EJB
    private service.TauxTaxeRetardTrimestrielFacade tauxTaxeRetardTrimestrielFacade;

    private TaxeTrimestriel taxeTrimestriel;

    private boolean dejaPaye;

    private Redevable monRedevable;
    private Local monLocal;

    public Local getMonLocal() {
        return monLocal;
    }

    public void setMonLocal() {
        System.out.println("seeterr local");
        this.monLocal = ejbFacade.getTaxeAnnuelByRedevable(getMonRedevable().getId()).get(0).getLocal();
    }

    public Redevable getMonRedevable() {
        return monRedevable;
    }

    public void setMonRedevable() {
        this.monRedevable = SessionUtil.getConnectedRedevable();
    }

    public TaxeAnnuelFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(TaxeAnnuelFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<TaxeAnnuel> getListe() {
        if (liste == null) {
            liste = getFacade().getTaxeAnnuelByRedevable(monRedevable.getId());
        }
        return liste;
    }

    public List<TaxeTrimestriel> getListeTrim() {
        if (listeTrim == null) {
            listeTrim = getFacade().getTaxeTrimByTaxeAnnuel(1);
        }
        return listeTrim;
    }

    public void setListeTrim(List<TaxeTrimestriel> listeTrim) {
        this.listeTrim = listeTrim;
    }

    public void setListe(List<TaxeAnnuel> liste) {
        this.liste = liste;
    }

    public TauxTaxeRetardTrimestrielFacade getTauxTaxeRetardTrimestrielFacade() {
        return tauxTaxeRetardTrimestrielFacade;
    }

    public void setTauxTaxeRetardTrimestrielFacade(TauxTaxeRetardTrimestrielFacade tauxTaxeRetardTrimestrielFacade) {
        this.tauxTaxeRetardTrimestrielFacade = tauxTaxeRetardTrimestrielFacade;
    }

    public TauxTaxeTrimestrielFacade getTauxTaxeTrimestrielFacade() {
        return tauxTaxeTrimestrielFacade;
    }

    public void setTauxTaxeTrimestrielFacade(TauxTaxeTrimestrielFacade tauxTaxeTrimestrielFacade) {
        this.tauxTaxeTrimestrielFacade = tauxTaxeTrimestrielFacade;
    }

    public LocalFacade getLocalFacade() {
        return localFacade;
    }

    public void setLocalFacade(LocalFacade localFacade) {
        this.localFacade = localFacade;
    }

    public boolean isDejaPaye() {
        return dejaPaye;
    }

    public void setDejaPaye(boolean dejaPaye) {
        this.dejaPaye = dejaPaye;
    }

    public TaxeAnnuelController() {
    }

    public void testDate() {
        System.out.println("tessst");
        LocalDate d1 = LocalDate.of(2016, 6, 07);
        LocalDate d2 = LocalDate.of(2017, 6, 07);

        System.out.println("Month  :  " + d1.getMonthValue());
    }

    public void getDernierTaxeTrimestriel() {
        // setRedevable();
        System.out.println("ouups");
        /*        if (selected.getLocal().getIdDernierTaxeTrimestrielPaye() == -1) {

            dejaPaye = false;

            Local local = selected.getLocal();
            Redevable redevable = selected.getRedevable();

            selected = new TaxeAnnuel();
            selected.setLocal(local);
            selected.setChiffreAffaireTotal(new BigDecimal(0));
            selected.setRedevable(redevable);
            selected.setTotalTaxes(new BigDecimal(0));

            taxeTrimestriel = new TaxeTrimestriel();
            taxeTrimestriel.setRedevable(local.getRedevable());
            taxeTrimestriel.setTaxeAnnuel(selected);

            System.out.println("rah nulllllllll");
        } else {
         */
        TaxeAnnuel annuel = ejbFacade.getDernierTaxeTrimestriel(getMonLocal());

        dejaPaye = true;

        if (getMonLocal().getIdDernierTaxeTrimestrielPaye() < 4) {
            selected = annuel;
            taxeTrimestriel.setNumeroTrimestre(annuel.getLocal().getIdDernierTaxeTrimestrielPaye() + 1);
            taxeTrimestriel.setRedevable(selected.getLocal().getRedevable());
            taxeTrimestriel.setTaxeAnnuel(selected);

            System.out.println("AVAAAANT ++++++++++" + selected.getLocal().getIdDernierTaxeTrimestrielPaye());
            selected.getLocal().setIdDernierTaxeTrimestrielPaye(taxeTrimestriel.getNumeroTrimestre());
            System.out.println("APREEEES ++++++++++" + selected.getLocal().getIdDernierTaxeTrimestrielPaye());

        } else if (annuel.getLocal().getIdDernierTaxeTrimestrielPaye() == 4) {
            selected = new TaxeAnnuel();

            selected.setAnnee(annuel.getAnnee() + 1);
            selected.setRedevable(annuel.getLocal().getRedevable());
            selected.setChiffreAffaireTotal(new BigDecimal(0));
            selected.setTotalTaxes(new BigDecimal(0));

            selected.setLocal(annuel.getLocal());
            selected.getLocal().setIdDernierTaxeTrimestrielPaye(1);

            taxeTrimestriel.setNumeroTrimestre(1);
            taxeTrimestriel.setRedevable(selected.getRedevable());
            taxeTrimestriel.setTaxeAnnuel(selected);
        }

        //taxeTrimestriel.setNumeroTrimestre(annuel.getTaxeTrimestriels().get(0).getNumeroTrimestre());
        System.out.println("machi nulllllll");
        appliqueTauxTaxe();
        
        afficherTaxeTrim(taxeTrimestriel);

    }

    public void appliqueTauxTaxe() {
        System.out.println("anneee "+selected.getAnnee());
        TauxTaxeTrimestriel ttt = tauxTaxeTrimestrielFacade.getTauxApplicable(selected.getAnnee(), taxeTrimestriel.getNumeroTrimestre());
        if (ttt == null) {
            System.out.println("Taux nuuuuuuuul");
        } else {
            BigDecimal taxe = taxeTrimestriel.getChiffreAffaire().multiply(ttt.getTauxTaxe().divide(new BigDecimal(100)));
            BigDecimal retard = new BigDecimal(0);

            System.out.println("CA : " + taxeTrimestriel.getChiffreAffaire() + " taux : " + ttt.getTauxTaxe() + " = " + taxe);

            int nbrMois = getMoisRetard(taxeTrimestriel.getNumeroTrimestre(), selected.getAnnee(), taxeTrimestriel.getDatePresentation(), 1);
            System.out.println("RETARD **********" + nbrMois);

            if (nbrMois > 0) {
                BigDecimal tauxRetard = getTauxRetard();
                if (tauxRetard != null) {
                    System.out.println("TAUX RETARD APPLIQUE //////***** " + tauxRetard);
                    retard = taxe.multiply(tauxRetard.divide(new BigDecimal(100)).multiply(new BigDecimal(nbrMois)));
                }
            } else {
                nbrMois = 0;
            }
            BigDecimal totalTaxe = taxe.add(retard);

            //selected.setChiffreAffaireTotal(selected.getChiffreAffaireTotal().add(taxeTrimestriel.getChiffreAffaire()));
            //selected.setTotalTaxes(selected.getTotalTaxes().add(totalTaxe));

            taxeTrimestriel.setNbrMoisRetard(nbrMois);

            taxeTrimestriel.setTaxe(taxe);
            taxeTrimestriel.setRetard(retard);
            taxeTrimestriel.setTotalTaxe(totalTaxe);

            //annuel CA total ete total taxe
            //private BigDecimal taxe;
            //private BigDecimal retard;
            //private BigDecimal totalTaxe;
            //private int numeroTrimestre;
            //private int nbrMoisRetard;
            System.out.println("Ha taxe applicable : " + ttt.getTauxTaxe());
        }
    }

    public BigDecimal getTauxRetard() {
        TauxTaxeRetardTrimestriel ttrt = tauxTaxeRetardTrimestrielFacade.getTauxRetardApplicable(selected.getAnnee(), taxeTrimestriel.getNumeroTrimestre());

        if (ttrt == null) {
            System.out.println("RETAAAARD NULLL");
        } else {
            //System.out.println("Ha RETARD Applique " + ttrt.getTauxRetardPremierMois());
        }
        return ttrt.getTauxrRetard();
    }

    public void getTauxApplicable() {

    }

    public void setRedevable() {

        selected.setRedevable(redevableFacade.find(null));

    }

    public TaxeAnnuel getSelected() {
        if (selected == null) {
            selected = new TaxeAnnuel();
        }
        //System.out.println("--->" + selected.getLocal().getTel());
        return selected;
    }

    public void getTaxeTrimByTaxeAnnuel(TaxeAnnuel annuel) {
        System.out.println("mmmmmm");
        getSelected().setTaxeTrimestriels(taxeTrimestrielFacade.getTaxeTrimByTaxeAnnuel(annuel));

    }

    public TaxeTrimestrielFacade getTaxeTrimestrielFacade() {
        return taxeTrimestrielFacade;
    }

    public void setTaxeTrimestrielFacade(TaxeTrimestrielFacade taxeTrimestrielFacade) {
        this.taxeTrimestrielFacade = taxeTrimestrielFacade;
    }

    public RedevableFacade getRedevableFacade() {
        return redevableFacade;
    }

    public void setRedevableFacade(RedevableFacade redevableFacade) {
        this.redevableFacade = redevableFacade;
    }

    public void afficherTaxeTrim(TaxeTrimestriel taxeTrimestriel) {
        System.out.println(" numero:" + taxeTrimestriel.getNumeroTrimestre());
        System.out.println(" date presentation:" + taxeTrimestriel.getDatePresentation());
                System.out.println(" chiffre affaire:" + taxeTrimestriel.getChiffreAffaire());

        System.out.println(" total:" + taxeTrimestriel.getTotalTaxe());

    }

    public TaxeTrimestriel getTaxeTrimestriel() {
        if (taxeTrimestriel == null) {
            //getDernierTaxeTrimestriel();
            taxeTrimestriel = new TaxeTrimestriel();
        }
        return taxeTrimestriel;
    }

    public void setTaxeTrimestriel(TaxeTrimestriel taxeTrimestriel) {

        this.taxeTrimestriel = taxeTrimestriel;
    }

    public void setSelected(TaxeAnnuel selected) {
        System.out.println(" hahaha " + selected.getAnnee());
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TaxeAnnuelFacade getFacade() {
        return ejbFacade;
    }

    public TaxeAnnuel prepareCreate() {
        selected = new TaxeAnnuel();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TaxeAnnuelCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TaxeAnnuelUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TaxeAnnuelDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TaxeAnnuel> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {

                    appliqueTauxTaxe();

                    getFacade().edit(selected);
                    taxeTrimestriel.setTaxeAnnuel(ejbFacade.getDernierTaxeTrimestriel(selected.getLocal()));

                    taxeTrimestrielFacade.create(taxeTrimestriel);

                    selected.getLocal().setIdDernierTaxeTrimestrielPaye(taxeTrimestriel.getNumeroTrimestre());

                    System.out.println("haaaaaaaaaaaaaaaaaaa local id :: " + selected.getLocal().getId());

                    localFacade.edit(selected.getLocal());

                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TaxeAnnuel getTaxeAnnuel(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<TaxeAnnuel> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TaxeAnnuel> getItemsAvailableSelectOne() {
        return getFacade().findAll();

    }

    @FacesConverter(forClass = TaxeAnnuel.class)
    public static class TaxeAnnuelControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TaxeAnnuelController controller = (TaxeAnnuelController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "taxeAnnuelController");
            return controller.getTaxeAnnuel(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TaxeAnnuel) {
                TaxeAnnuel o = (TaxeAnnuel) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TaxeAnnuel.class.getName()});
                return null;
            }
        }

    }

    public Date getMyDate(int numeroTrimistre, int anne) {
        Date date = null;
        try {
            String mydate = "";
            if (numeroTrimistre == 1) {
                mydate = "01/04/" + anne;

            } else if (numeroTrimistre == 2) {
                mydate = "01/07/" + anne;
            } else if (numeroTrimistre == 3) {
                mydate = "01/10/" + anne;
            } else if (numeroTrimistre == 4) {
                anne++;
                mydate = "01/01/" + anne;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = simpleDateFormat.parse(mydate);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return date;

        }

    }

    public int getMoisRetard(int numeroTrimestre, int anne, Date datePresentation, int nbrmoisignorer) {

        int moisRetard = 0;
        Date myDate = getMyDate(numeroTrimestre, anne);

        Calendar trimestriel = Calendar.getInstance();
        Calendar presentation = Calendar.getInstance();
        trimestriel.setTime(myDate);
        presentation.setTime(datePresentation);

        int presantationYear = presentation.get(Calendar.YEAR);
        int presentationMonth = presentation.get(Calendar.MONTH);
        int trimestreYear = trimestriel.get(Calendar.YEAR);
        int trimestreMonth = trimestriel.get(Calendar.MONTH);
        if (presantationYear - trimestreYear == 0) {

            moisRetard = presentationMonth - trimestreMonth;

        } else {
            moisRetard = presentationMonth;
            presantationYear--;
            while (presantationYear - trimestreYear > 0) {
                moisRetard += 12;
                presantationYear--;

            }
            int retatd = 12 - trimestreMonth;
            moisRetard += retatd;

        }

        return moisRetard - nbrmoisignorer;
    }

}
