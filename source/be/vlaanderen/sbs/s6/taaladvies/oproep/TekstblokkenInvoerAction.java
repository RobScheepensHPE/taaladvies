package be.vlaanderen.sbs.s6.taaladvies.oproep;

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
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class TekstblokkenInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean /*check = */false;
		TekstblokkenInvoerForm referenceForm = (TekstblokkenInvoerForm)form;
		Tekstblok tekstblok = referenceForm.getTekstblok();
		Tekst tekst = referenceForm.getTekst();
		//int id = tekstblok.getId();
		//int type = 0;

		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(tekstblok);
		}

		if (request.getParameter("Wijzigen.x") != null) {

			/*check = */Tekstblok.update(tekstblok);
			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + tekst.getOproepId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {

			tekstblok.setTekstId(tekst.getId());
			/*id = */Tekstblok.insert(tekstblok);
			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + tekst.getOproepId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Nieuw Tekstblok Update.x")!= null) {

			/*check = */Tekstblok.update(tekstblok);
			return (new ActionForward("/do/initTekstblokkenInvoer?parentId=" + tekst.getId(), true));
		}

		if (request.getParameter("Nieuw Tekstblok Save.x")!= null) {

			tekstblok.setTekstId(tekst.getId());
			/*id = */Tekstblok.insert(tekstblok);
			return (new ActionForward("/do/initTekstblokkenInvoer?parentId=" + tekst.getId(), true));
		}

		if (request.getParameter("Annuleren.x") != null) {

			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + tekst.getOproepId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Wijzigen")) {
			/*check = */Tekstblok.update(tekstblok);
			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + tekst.getOproepId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Toevoegen")) {
			tekstblok.setTekstId(tekst.getId());
			/*id = */Tekstblok.insert(tekstblok);
			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + tekst.getOproepId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		return (mapping.findForward("blabla"));

	}

    /**
     * @param tekstblok de te formateren tekstblok
     */
    private void formatHTMLVelden(Tekstblok tekstblok) {
        tekstblok.setTekstblokHTML(Util.formatNewLinesToBr(tekstblok.getTekstblokHTML()));
        tekstblok.setTitelHTML(Util.formatNewLinesToBr(tekstblok.getTitelHTML()));
    }


}

