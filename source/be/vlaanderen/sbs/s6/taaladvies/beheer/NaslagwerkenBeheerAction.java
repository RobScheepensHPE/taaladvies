package be.vlaanderen.sbs.s6.taaladvies.beheer;

import java.io.IOException;
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
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagwerk;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class NaslagwerkenBeheerAction extends BaseAction {

    public ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        //boolean check = false;
        NaslagwerkenBeheerForm referenceForm = (NaslagwerkenBeheerForm) form;
        Naslagwerk naslagwerk = referenceForm.getNaslagwerk();
        int id = naslagwerk.getId();

        System.err.println("plain:" + naslagwerk.getTitelVoluit());
        System.err.println("html:" + naslagwerk.getTitelVoluitHTML());

        if (session.getAttribute("eopro") == null || (session.getAttribute("eopro").equals("false"))) {
            formatHTMLVelden(naslagwerk);
        }
        System.err.println("html2:" + naslagwerk.getTitelVoluitHTML());

        session.setAttribute("eopro", referenceForm.getUseEopro());

        if (request.getParameter("Wijzigen.x") != null) {
            /*check = */Naslagwerk.update(naslagwerk);
            return (new ActionForward("/do/initNaslagwerkenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
        }

        if (request.getParameter("Toevoegen.x") != null) {
            id = Naslagwerk.insert(naslagwerk);
            return (new ActionForward("/do/initNaslagwerkenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
        }

        if (request.getParameter("Button").equals("EditeerModus")) {
            if (request.getParameter("U") != null) {
                /*check = */Naslagwerk.update(naslagwerk);
                return (new ActionForward("/do/initNaslagwerkenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
            } else {
                id = Naslagwerk.insert(naslagwerk);
                return (new ActionForward("/do/initNaslagwerkenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
            }
        }

        return (mapping.findForward("backtopage"));

    }

    /**
     * @param sjabloon
     */
    private void formatHTMLVelden(Naslagwerk naslagwerk) {
        naslagwerk.setOmschrijvingHTML(Util.formatNewLinesToBr(naslagwerk.getOmschrijvingHTML()));
        naslagwerk.setTitelVoluitHTML(Util.formatNewLinesToBr(naslagwerk.getTitelVoluitHTML()));
    }

}

