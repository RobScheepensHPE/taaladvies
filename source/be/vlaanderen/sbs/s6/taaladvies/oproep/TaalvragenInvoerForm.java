package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Distributie;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;


public class TaalvragenInvoerForm extends ActionForm {
    /**
     *
     */
    private static final long serialVersionUID = -4621780427438674044L;
    Taalvraag taalvraag = new Taalvraag();
    Oproep oproep = new Oproep();
    boolean adm = false;
    String useEopro = "false";

    public String getUseEopro() {
        return useEopro;
    }

    public void setUseEopro(String useEopro) {
        this.useEopro = useEopro;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if (request.getParameter("Annuleren.x") == null) {
            /*if (taalvraag.getVraagHTML().equals("") || taalvraag.getVraagHTML().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
                   errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("taalvraag.ontbreken.vraag"));
               }*/
            if (taalvraag.getVraag().equals("") || taalvraag.getVraag() == null) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("taalvraag.ontbreken.vraag"));
            }
        } else {
            java.util.ArrayList<Taalvraag> taalvragen = Taalvraag.findAllByOproep(oproep.getId());
            //boolean check;
            if (taalvragen == null || taalvragen.size() == 0) {

                /*check = */Oproep.delete(oproep.getId());
                /*check = */Distributie.delete(oproep.getDistributieId());
            }
        }

        return errors;
    }


    public void reset(ActionMapping mapping, HttpServletRequest request) {
        taalvraag = new Taalvraag();
        //HttpSession session = request.getSession();

    }


    /**
     * Gets the taalvraag
     *
     * @return Returns a Taalvraag
     */
    public Taalvraag getTaalvraag() {
        return taalvraag;
    }

    /**
     * Sets the taalvraag
     *
     * @param taalvraag The taalvraag to set
     */
    public void setTaalvraag(Taalvraag taalvraag) {
        this.taalvraag = taalvraag;
    }


    /**
     * Gets the oproep
     *
     * @return Returns a Oproep
     */
    public Oproep getOproep() {
        return oproep;
    }

    /**
     * Sets the oproep
     *
     * @param oproep The oproep to set
     */
    public void setOproep(Oproep oproep) {
        this.oproep = oproep;
    }


    /**
     * Gets the adm
     *
     * @return Returns a boolean
     */
    public boolean getAdm() {
        return adm;
    }

    /**
     * Sets the adm
     *
     * @param adm The adm to set
     */
    public void setAdm(boolean adm) {
        this.adm = adm;
    }


}


