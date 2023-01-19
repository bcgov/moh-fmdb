package fmdbwar.pages.admin;

import ca.bc.gov.moh.fmdb.business.PlaStatusTypesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;

import ca.bc.gov.moh.fmdb.entity.PlaStatusTypes;
import fmdbwar.DropDownListBean;
import fmdbwar.util.SelectItemHelper;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Managed bean for PLAStatusCodes.xhtml
 */
@Named("PLAStatusCodes")
@ViewScoped
public class PLAStatusCodes implements Serializable{

    @EJB
    private PlaStatusTypesFacadeLocal typeFacade;
    private PlaStatusTypes type;
    private List<PlaStatusTypes> allTypes;
    private String selectedType;

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PLAStatusCodes() {
        
    }

    @PostConstruct
    public void init(){
        clear();
        allTypes = typeFacade.findAll();       
    }
    
    public String selectType(ValueChangeEvent event){
        
        selectedType = (String) event.getNewValue();
        if (selectedType!=null){
            type = find(allTypes);
        }else{
            type = new PlaStatusTypes();
        }
        return null;
    }
    
    private PlaStatusTypes find(List<PlaStatusTypes> allTypes) {
        for (PlaStatusTypes type : allTypes) {
            if (type.getPlaStatusCd().equals(selectedType)){
                return type;
            }
        }
        return null;
    }     
    
    public String add(){

        try{
            typeFacade.savePlaStatusCodes(type);
            FacesContext fc = FacesContext.getCurrentInstance();
            DropDownListBean ddl = (DropDownListBean) fc.getApplication().evaluateExpressionGet(fc, "#{DropDownListBean}", DropDownListBean.class);
            ddl.updatePlaStatusTypes();
        } catch (SaveFMDBException ex) {
            Logger.getLogger(PlaStatusTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add successful", null));
        init();

        return null;
    }
    
    public String update(){

        try{
            typeFacade.savePlaStatusCodes(type);
            FacesContext fc = FacesContext.getCurrentInstance();
            DropDownListBean ddl = (DropDownListBean) fc.getApplication().evaluateExpressionGet(fc, "#{DropDownListBean}", DropDownListBean.class);
            ddl.updatePlaStatusTypes();
        } catch (SaveFMDBException ex) {
            Logger.getLogger(PlaStatusTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update successful", null));
        init();
       
        return null;
    }
    
    public String clear(){
        type = new PlaStatusTypes();
        selectedType = null;
        return null;
    }
    
    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public PlaStatusTypes getType() {
        return type;
    }

    public void setType(PlaStatusTypes type) {
        this.type = type;
    }

}

