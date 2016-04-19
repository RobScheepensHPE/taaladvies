package be.vlaanderen.sbs.s6.taaladvies.oproep;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;

public class InitTaalvragenInvoerAction extends BaseAction {

    public ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        TaalvragenInvoerForm referenceform = new TaalvragenInvoerForm();
        Taalvraag taalvraag = new Taalvraag();

        if (session.getAttribute("eopro") != null) {
            referenceform.setUseEopro(session.getAttribute("eopro").toString());
        }

        if (request.getParameter("adm") != null) {
            referenceform.setAdm(true);
        }

        if (request.getParameter("id") != null) {
            if (!request.getParameter("id").equals("0")) {
                taalvraag = Taalvraag.findByPK(Integer.parseInt(request.getParameter("id")));
                referenceform.setTaalvraag(taalvraag);
                referenceform.setOproep(Oproep.findByPK(taalvraag.getOproepId()));
            } else {
                referenceform.setOproep(Oproep.findByPK(Integer.parseInt(request.getParameter("parentId"))));
            }
        } else {
            Oproep oproep = Oproep.findByPK(Integer.parseInt(request.getParameter("parentId")));
            int maxvolgnummer = Taalvraag.findMaxVolgnummer(oproep.getId());
            if (maxvolgnummer == 0) {
                maxvolgnummer = 1;
                taalvraag.setVolgnummer(maxvolgnummer);
            } else {
                taalvraag.setVolgnummer(maxvolgnummer + 1);
            }
            referenceform.setOproep(oproep);
            referenceform.setTaalvraag(taalvraag);
        }

        session.setAttribute("TaalvragenInvoerForm", referenceform);
        
        return (request.getParameter("ro") != null) ? mapping.findForward("readonly") : mapping.findForward("success");
    }
}

