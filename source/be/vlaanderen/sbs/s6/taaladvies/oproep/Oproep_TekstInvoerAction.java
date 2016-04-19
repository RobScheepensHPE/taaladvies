package be.vlaanderen.sbs.s6.taaladvies.oproep;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.html.Constants;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import be.vlaanderen.sbs.s6.taaladvies.distributie.DistributiekeuzeForm;
import be.vlaanderen.sbs.s6.taaladvies.model.Bron;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Citaat;
import be.vlaanderen.sbs.s6.taaladvies.model.Distributie;
import be.vlaanderen.sbs.s6.taaladvies.model.Frequentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagreferentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Notitie;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Sjabloon;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;
import be.vlaanderen.sbs.s6.taaladvies.model.Webreferentie;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class Oproep_TekstInvoerAction extends BaseAction {

    public ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        //boolean /*check = */false;
        Oproep_TekstInvoerForm referenceForm = (Oproep_TekstInvoerForm) form;
        Oproep oproep = referenceForm.getOproep();
        Tekst tekst = referenceForm.getTekst();
        int tekstId = tekst.getId();
        int oproepId = oproep.getId();
        //int type = 0;

        if (session.getAttribute("eopro") == null || (session.getAttribute("eopro").equals("false"))) {
            formatHTMLVelden(tekst);
        }

        session.setAttribute("eopro", referenceForm.getUseEopro());

        Gebruiker gebruiker = (Gebruiker) session.getAttribute("Gebruiker");

        if (request.getParameter("Herschikken.x") != null) {

            /*check = */this.wijzigen(referenceForm, gebruiker);
            /*check = */this.tekstWijzigen(referenceForm, gebruiker);
            java.util.ArrayList<Tekstblok> tekstblokken = referenceForm.getTekstblokken();
            for (int i = 0, j = tekstblokken.size(); i < j; i++) {
                Tekstblok tekstblok = tekstblokken.get(i);
                int volgnummer = Integer.parseInt(request.getParameter("toChange_" + (tekstblok.getId())));
                if (tekstblok.getVolgnummer() != volgnummer) {
                    tekstblok.setVolgnummer(volgnummer);
                    /*check = */Tekstblok.updateVolgnummer(tekstblok);
                }
            }
            tekst.setTekstblokken(null);
            tekstblokken = tekst.getTekstblokken();
            for (int i = 0, j = tekstblokken.size(); i < j; i++) {
                Tekstblok tekstblok = tekstblokken.get(i);
                tekstblok.setVolgnummer(i + 1);
                /*check = */Tekstblok.updateVolgnummer(tekstblok);
            }
            return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
        }

        if (request.getParameter("Oproep Afsluiten.x") != null) {

            /*check = */this.wijzigen(referenceForm, gebruiker);
            /*check = */this.tekstWijzigen(referenceForm, gebruiker);
            /*check = */Oproep.close(oproepId);

            return (new ActionForward("/do/initEigenTeksten", true));
        }

        if (request.getParameter("Distributie.x") != null) {
            if (request.getParameter("ro") == null) {
                /*check = */this.wijzigen(referenceForm, gebruiker);
                /*check = */this.tekstWijzigen(referenceForm, gebruiker);
            }
            DistributiekeuzeForm distributiekeuzeform = new DistributiekeuzeForm();
            distributiekeuzeform.setOproep(Oproep.findByPK(oproepId));
            if (distributiekeuzeform.getOproep().getDistributie() == null) {
                distributiekeuzeform.getOproep().setDistributie(new Distributie());
            }

            if (distributiekeuzeform.getOproep().getDomeinId() == 1) {
                distributiekeuzeform.getOproep().getDistributie().setBcc(AppConf.getResource("mail.fromIntern"));
            } else {
                distributiekeuzeform.getOproep().getDistributie().setBcc(AppConf.getResource("mail.fromExtern"));
            }

            distributiekeuzeform.setTekst(Tekst.findByOproep(oproepId));
            distributiekeuzeform.setSjablonen(Sjabloon.findAllActief());
            if (request.getParameter("ro") != null) {
                distributiekeuzeform.setRo(true);
            }
            session.setAttribute("DistributiekeuzeForm", distributiekeuzeform);
            return (mapping.findForward("distributie"));
        }

        if (request.getParameter("Volledige Tekst.x") != null) {

            /*check = */this.wijzigen(referenceForm, gebruiker);
            /*check = */this.tekstWijzigen(referenceForm, gebruiker);
            return (new ActionForward("/do/initVolledigeTekstView?id=" + oproepId, true));
        }

        if (request.getParameter("In Behandeling.x") != null) {
            /*check = */this.wijzigen(referenceForm, gebruiker);
            /*check = */this.tekstWijzigen(referenceForm, gebruiker);
            return (new ActionForward("/do/initEigenTeksten", true));
        }
        if (request.getParameter("Nieuwe Oproep Taalvragen.x") != null) {
            /*check = */this.wijzigen(referenceForm, gebruiker);
            /*check = */this.tekstWijzigen(referenceForm, gebruiker);
            return (new ActionForward("/do/initOproep_TaalvragenInvoer", true));
        }
        if (request.getParameter("Nieuwe Oproep Tekst.x") != null) {
            /*check = */this.wijzigen(referenceForm, gebruiker);
            /*check = */this.tekstWijzigen(referenceForm, gebruiker);
            return (new ActionForward("/do/initOproep_TekstInvoer", true));
        }
        if (request.getParameter("Zoeken.x") != null) {
            /*check = */this.wijzigen(referenceForm, gebruiker);
            /*check = */this.tekstWijzigen(referenceForm, gebruiker);
            return (new ActionForward("/do/initZoeken", true));
        }
        if (request.getParameter("Profielen.x") != null) {
            /*check = */this.wijzigen(referenceForm, gebruiker);
            /*check = */this.tekstWijzigen(referenceForm, gebruiker);
            return (new ActionForward("/profielen.jsp", true));
        }
        if (request.getParameter("In Behandeling Alles.x") != null) {
            oproepId = this.toevoegen(referenceForm, gebruiker);
            tekstId = this.tekstInvoeren(referenceForm, gebruiker);
            return (new ActionForward("/do/initEigenTeksten", true));
        }
        if (request.getParameter("Nieuwe Oproep Taalvragen Alles.x") != null) {
            oproepId = this.toevoegen(referenceForm, gebruiker);
            tekstId = this.tekstInvoeren(referenceForm, gebruiker);
            return (new ActionForward("/do/initOproep_TaalvragenInvoer", true));
        }
        if (request.getParameter("Nieuwe Oproep Tekst Alles.x") != null) {
            oproepId = this.toevoegen(referenceForm, gebruiker);
            tekstId = this.tekstInvoeren(referenceForm, gebruiker);
            return (new ActionForward("/do/initOproep_TekstInvoer", true));
        }
        if (request.getParameter("Zoeken Alles.x") != null) {
            oproepId = this.toevoegen(referenceForm, gebruiker);
            tekstId = this.tekstInvoeren(referenceForm, gebruiker);
            return (new ActionForward("/do/initZoeken", true));
        }
        if (request.getParameter("Profielen Alles.x") != null) {
            oproepId = this.toevoegen(referenceForm, gebruiker);
            tekstId = this.tekstInvoeren(referenceForm, gebruiker);
            return (new ActionForward("/profielen.jsp", true));
        }


        if (request.getParameter("Wijzigen.x") != null) {

            /*check = */this.wijzigen(referenceForm, gebruiker);
            return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
        }

        if (request.getParameter("Toevoegen.x") != null) {

            oproepId = this.toevoegen(referenceForm, gebruiker);
            return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
        }

        if (request.getParameter("Tekst wijzigen.x") != null) {

            /*check = */this.wijzigen(referenceForm, gebruiker);
            /*check = */this.tekstWijzigen(referenceForm, gebruiker);
            return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
        }


        if (request.getParameter("Kopieer.x") != null) {
            /*check = */saveTekst(referenceForm, gebruiker);

            session.setAttribute("OproepId", new Integer(referenceForm.getOproep().getId()));
            session.setAttribute("TekstId", new Integer(referenceForm.getTekst().getId()));
            return (new ActionForward("/do/initZoekenKopieerTXT", true));
        }

        if (request.getParameter("Nieuw Tekstblok.x") != null) {
            /*check = */saveTekst(referenceForm, gebruiker);

            return (new ActionForward("/do/initTekstblokkenInvoer?parentId=" + referenceForm.getTekst().getId(), true));
        }

        if (request.getParameter("Klant.x") != null) {
            /*check = */saveTekst(referenceForm, gebruiker);
            return (new ActionForward("/do/initKlantenGegevensInvoer?id=" + referenceForm.getOproep().getId(), true));
        }

        if (request.getParameter("Button2") != null) {


            int idToDelete = Integer.parseInt(request.getParameter("idToDelete"));
            /*check = */Tekstblok.delete(idToDelete);
            tekst.setTekstblokken(null);
            java.util.ArrayList<Tekstblok> tekstblokken = tekst.getTekstblokken();
            if (tekstblokken != null) {
                for (int i = 0, j = tekstblokken.size(); i < j; i++) {
                    Tekstblok tekstblok = tekstblokken.get(i);
                    tekstblok.setVolgnummer(i + 1);
                    /*check = */Tekstblok.updateVolgnummer(tekstblok);
                }
                return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId, true));
            } else {
                /*check = */Categorie.deleteForTekst(tekstId);
                /*check = */Bron.deleteForTekst(tekstId);
                /*check = */Citaat.deleteForTekst(tekstId);
                /*check = */Frequentie.deleteForTekst(tekstId);
                /*check = */Naslagreferentie.deleteForTekst(tekstId);
                /*check = */Notitie.deleteForTekst(tekstId);
                /*check = */Webreferentie.deleteForTekst(tekstId);
                /*check = *///Tekst.deleteVerityEntry(tekst.getId());

                /*check = */Tekst.delete(tekstId);

                /*check = */Oproep.delete(oproep.getId());
                /*check = */Distributie.delete(oproep.getDistributieId());
                return (new ActionForward("/do/initOproep_TekstInvoer", true));
            }

        }

        if (request.getParameter("Button").equals("AllesToevoegen")) {

            oproepId = this.toevoegen(referenceForm, gebruiker);
            tekstId = this.tekstInvoeren(referenceForm, gebruiker);
            return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
        }

        if (request.getParameter("Button").equals("Categorie")) {
            /*check = */saveTekst(referenceForm, gebruiker);
            return (new ActionForward("/do/initCategorienInvoer?type=2&id=" + referenceForm.getTekst().getId(), true));
        }

        if (request.getParameter("Button").equals("Naslagwerk")) {
            /*check = */saveTekst(referenceForm, gebruiker);
            return (new ActionForward("/do/initNaslagreferentiesInvoer?type=2&id=" + referenceForm.getTekst().getId(), true));
        }

        if (request.getParameter("Button").equals("Bron")) {
            /*check = */saveTekst(referenceForm, gebruiker);
            return (new ActionForward("/do/initBronnenInvoer?type=2&id=" + referenceForm.getTekst().getId(), true));
        }

        if (request.getParameter("Button").equals("Citaat")) {
            /*check = */saveTekst(referenceForm, gebruiker);
            return (new ActionForward("/do/initCitatenInvoer?type=2&id=" + referenceForm.getTekst().getId(), true));
        }

        if (request.getParameter("Button").equals("Frequentie")) {
            /*check = */saveTekst(referenceForm, gebruiker);
            return (new ActionForward("/do/initFrequentiesInvoer?type=2&id=" + referenceForm.getTekst().getId(), true));
        }

        if (request.getParameter("Button").equals("Koppeling")) {
            /*check = */saveTekst(referenceForm, gebruiker);
            return (new ActionForward("/do/initWebreferentiesInvoer?type=2&id=" + referenceForm.getTekst().getId(), true));
        }

        if (request.getParameter("Button").equals("Notitie")) {
            /*check = */saveTekst(referenceForm, gebruiker);
            return (new ActionForward("/do/initNotitiesInvoer?type=2&id=" + referenceForm.getTekst().getId(), true));
        }

        if (request.getParameter("Button").equals("EditeerModus")) {
            saveTekst(referenceForm, gebruiker);
            return new ActionForward("/do/initOproep_TekstInvoer?id=" + referenceForm.getOproep().getId() + "&Button=EditeerModus" + "&" + Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true);
        }

        return (mapping.findForward("blabla"));

    }

    /**
     * @param tekst de te formateren tekst
     */
    private void formatHTMLVelden(Tekst tekst) {
        tekst.setTitelHTML(Util.formatNewLinesToBr(tekst.getTitelHTML()));
    }

    public boolean wijzigen(Oproep_TekstInvoerForm referenceForm, Gebruiker gebruiker) {
        boolean check = false;

        Oproep oproep = referenceForm.getOproep();

        oproep.setType(2);
        oproep.setGebruikerId(gebruiker.getId());
        /*check = */Oproep.update(oproep);
        Distributie distributie = new Distributie();
        distributie.setId(oproep.getDistributieId());
        distributie.setMediumId(oproep.getDistributieAsIs().getMediumId());
        distributie.setDistributiedatum(new Date(new java.util.Date().getTime()));
        /*check = */Distributie.update(distributie);
        return check;
    }

    public int toevoegen(Oproep_TekstInvoerForm referenceForm, Gebruiker gebruiker) {

        Oproep oproep = referenceForm.getOproep();

        if (oproep.getNaam().equals("")) {
            oproep.setNaam("Onbekend");
        }
        oproep.setType(2);
        oproep.setGebruikerId(gebruiker.getId());
        Distributie distributie = new Distributie();
        distributie.setMediumId(oproep.getDistributieAsIs().getMediumId());
        distributie.setDistributiedatum(new Date(new java.util.Date().getTime()));
        int distributieId = Distributie.insert(distributie);
        oproep.setDistributieId(distributieId);
        int id = Oproep.insert(oproep);
        return id;
    }

    public boolean tekstWijzigen(Oproep_TekstInvoerForm referenceForm, Gebruiker gebruiker) {

        Tekst tekst = referenceForm.getTekst();

        tekst.setOproepId(referenceForm.getOproep().getId());
        boolean check = Tekst.update(tekst);
        return check;
    }

    public int tekstInvoeren(Oproep_TekstInvoerForm referenceForm, Gebruiker gebruiker) {

        Tekst tekst = referenceForm.getTekst();

        tekst.setOproepId(referenceForm.getOproep().getId());
        int tekstId = Tekst.insert(tekst);
        return tekstId;
    }

    private boolean saveTekst(Oproep_TekstInvoerForm referenceForm, Gebruiker gebruiker) {
        boolean check = true;
        if (referenceForm.getOproep().getId() == 0) {
            int oproepId = this.toevoegen(referenceForm, gebruiker);
            referenceForm.getOproep().setId(oproepId);
        } else {
            check = this.wijzigen(referenceForm, gebruiker);
        }

        if (referenceForm.getTekst().getId() == 0) {
            int tekstId = this.tekstInvoeren(referenceForm, gebruiker);
            referenceForm.getTekst().setId(tekstId);
        } else {
            check = this.tekstWijzigen(referenceForm, gebruiker);
        }
        return check;
    }
}

