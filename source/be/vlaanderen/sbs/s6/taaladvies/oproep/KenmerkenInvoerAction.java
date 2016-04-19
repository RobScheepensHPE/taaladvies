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
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class KenmerkenInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		KenmerkenInvoerForm referenceForm = (KenmerkenInvoerForm)form;
		Taalvraag taalvraag = referenceForm.getTaalvraag();
		int id = taalvraag.getId();

		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(taalvraag);
		}

		if (request.getParameter("Button") != null) {
			/*check = */Taalvraag.updateKenmerken(taalvraag);
			return (new ActionForward("/do/initTaalvragenInvoer?id=" + id +	"&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Annuleren.x") != null) {
			return (new ActionForward("/do/initTaalvragenInvoer?id=" + id +	"&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		return (mapping.findForward("backtopage"));

	}

	/**
     * vervangt de new lines in dit veld door br - html tags in niet-eopro editeer modus
     * @param taalvraag de te formateren taalvraag
     */
    private void formatHTMLVelden(Taalvraag taalvraag) {
       taalvraag.setHerformuleringHTML(Util.formatNewLinesToBr(taalvraag.getHerformuleringHTML()));
       taalvraag.setInformatieHTML(Util.formatNewLinesToBr(taalvraag.getInformatieHTML()));
    }
}

