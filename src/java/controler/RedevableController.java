package controler;

import bean.Redevable;
import bean.TaxeAnnuel;
import bean.TaxeTrimestriel;
import controler.util.JsfUtil;
import controler.util.JsfUtil.PersistAction;
import controler.util.SessionUtil;
import java.io.IOException;
import service.RedevableFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("redevableController")
@SessionScoped
public class RedevableController implements Serializable {

    @EJB
    private service.RedevableFacade ejbFacade;
    private List<Redevable> items = null;
    private List<TaxeTrimestriel> listeTaxesTrimestriels = null;
    private List<TaxeAnnuel> listeTaxesAnnuels;
    private Redevable selected;
    private String login;
    private String password;

    public void setItems(List<Redevable> items) {
        this.items = items;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    ////////////////////////////////////////////////////////////////
    public void init() {
        listeTaxesTrimestriels = ejbFacade.getMyTaxeTrimstriel(1);
        listeTaxesAnnuels = ejbFacade.getMyTaxeAnnuel(1);
    }

    public List<TaxeTrimestriel> getMyTaxeTrimstriel() {
        return listeTaxesTrimestriels;
    }

    public List<TaxeAnnuel> getMyTaxeAnnuel(int id) {
        return listeTaxesAnnuels;
    }

    public void chercherRedevable() {
        selected = ejbFacade.chercherRedevable(login, password);
        if (selected == null) {
            JsfUtil.addErrorMessage("Login ou Passwrod errone");
        } else {
            SessionUtil.registerRedevable(selected);
            try {
                SessionUtil.redirect("/projet_jeemaster/faces/view/menu");
            } catch (IOException ex) {
                Logger.getLogger(RedevableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("login " + selected.getLogin() + " password " + selected.getPassword());
    }

    public Redevable getConnectedRedevable() {
        Redevable redevable = SessionUtil.getConnectedRedevable();
        if (redevable == null) {
            return new Redevable();
        }
        return redevable;
    }

    public void deconnexion() {
        SessionUtil.deconnexion();
    }

///////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////// getters & setters
    public RedevableFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(RedevableFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<TaxeTrimestriel> getListeTaxesTrimestriels() {
        if (listeTaxesTrimestriels == null) {
            listeTaxesTrimestriels = getFacade().getMyTaxeTrimstriel(1);
        }
        return listeTaxesTrimestriels;
    }

    public void setListeTaxesTrimestriels(List<TaxeTrimestriel> listeTaxesTrimestriels) {
        this.listeTaxesTrimestriels = listeTaxesTrimestriels;
    }

    public List<TaxeAnnuel> getListeTaxesAnnuels() {
        return listeTaxesAnnuels;
    }

    public void setListeTaxesAnnuels(List<TaxeAnnuel> listeTaxesAnnuels) {
        this.listeTaxesAnnuels = listeTaxesAnnuels;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public RedevableController() {
    }

    public Redevable getSelected() {
        if (selected == null) {
            selected = new Redevable();
        }
        return selected;
    }

    public void setSelected(Redevable selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RedevableFacade getFacade() {
        return ejbFacade;
    }

    public Redevable prepareCreate() {
        selected = new Redevable();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RedevableCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RedevableUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RedevableDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Redevable> getItems() {
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
                    getFacade().edit(selected);
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

    public Redevable getRedevable(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Redevable> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Redevable> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Redevable.class)
    public static class RedevableControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RedevableController controller = (RedevableController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "redevableController");
            return controller.getRedevable(getKey(value));
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
            if (object instanceof Redevable) {
                Redevable o = (Redevable) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Redevable.class.getName()});
                return null;
            }
        }

    }

}
