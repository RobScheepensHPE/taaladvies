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
//import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;

public class InitVolledigeTekstViewAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		VolledigeTekstViewForm referenceform = new VolledigeTekstViewForm();

		//Gebruiker gebruiker = (Gebruiker)session.getAttribute("Gebruiker");

		int id = Integer.parseInt(request.getParameter("id"));

		Oproep oproep = Oproep.findByPK(id);
		Tekst tekst = Tekst.findByOproep(id);
		java.util.ArrayList<Tekstblok> tekstblokken = Tekstblok.findByParent(tekst.getId());

		referenceform.setOproep(oproep);
		referenceform.setTekst(tekst);
		referenceform.setTekstblokken(tekstblokken);

  		session.setAttribute("VolledigeTekstViewForm", referenceform);

		if (request.getParameter("ro") != null) {
			return(mapping.findForward("readonly"));
		}
		else {
			return(mapping.findForward("success"));
		}

	}

}

