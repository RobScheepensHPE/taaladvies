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
import be.vlaanderen.sbs.s6.taaladvies.model.Sjabloon;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class SjablonenBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		SjablonenBeheerForm referenceForm = (SjablonenBeheerForm)form;
		Sjabloon sjabloon = (Sjabloon)referenceForm.getSjabloon();
		int id = sjabloon.getId();

		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(sjabloon);
		}

		session.setAttribute("eopro", referenceForm.getUseEopro());

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */Sjabloon.update(sjabloon);
			return (new ActionForward("/do/initSjablonenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			id = Sjabloon.insert(sjabloon);
			return (new ActionForward("/do/initSjablonenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("EditeerModus")){
		    if (request.getParameter("U") != null){
		        /*check = */Sjabloon.update(sjabloon);
		        return (new ActionForward("/do/initSjablonenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		    }else{
		        id = Sjabloon.insert(sjabloon);
		        return (new ActionForward("/do/initSjablonenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		    }
		}

		return (mapping.findForward("backtopage"));

	}

    /**
     * @param sjabloon
     */
    private void formatHTMLVelden(Sjabloon sjabloon) {
        sjabloon.setHandtekeningHTML(Util.formatNewLinesToBr(sjabloon.getHandtekeningHTML()));
        sjabloon.setInhoudHTML(Util.formatNewLinesToBr(sjabloon.getInhoudHTML()));
        sjabloon.setSlotgroetHTML(Util.formatNewLinesToBr(sjabloon.getSlotgroetHTML()));
    }

}

