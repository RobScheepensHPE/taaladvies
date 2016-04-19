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
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.model.Doelgroep;
import be.vlaanderen.sbs.s6.taaladvies.model.DoelgroepAlgemeen;
//import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Gemeente;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;

public class InitKlantenGegevensInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		KlantenGegevensInvoerForm referenceform = new KlantenGegevensInvoerForm();

		if (request.getParameter("adm") != null) {
			referenceform.setAdm(true);
		}

		Oproep oproep = new Oproep();

		//Gebruiker gebruiker = (Gebruiker)session.getAttribute("Gebruiker");

		oproep = Oproep.findByPK(Integer.parseInt(request.getParameter("id")));
		referenceform.setOproep(oproep);
		referenceform.setDoelgroepenAlgemeen(DoelgroepAlgemeen.findAllByParentActief(oproep.getDomeinId()));
		referenceform.setDoelgroepen(Doelgroep.findAllByDomeinActief(oproep.getDomeinId()));
		referenceform.setLanden(ParameterBase.findAll(Queries.ALL_LANDEN));
		referenceform.setDomeinen(ParameterBase.findAll(Queries.ALL_DOMEINEN));
		referenceform.setHerkomsten(ParameterActief.findAll(Queries.ALL_HERKOMSTEN));
		referenceform.setGemeenten(Gemeente.findAll());

  		session.setAttribute("KlantenGegevensInvoerForm", referenceform);

		if (request.getParameter("ro") != null) {
			return(mapping.findForward("readonly"));
		}
		else {
			return(mapping.findForward("success"));
		}

	}

}

