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
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;

public class InitOproep_TaalvragenInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();


		Oproep_TaalvragenInvoerForm referenceform = new Oproep_TaalvragenInvoerForm();
		Oproep oproep = new Oproep();

		if(session.getAttribute("eopro") != null){
		    referenceform.setUseEopro(session.getAttribute("eopro").toString());
		}

		Gebruiker gebruiker = (Gebruiker)session.getAttribute("Gebruiker");
		oproep.getDoelgroep().getDoelgroepAlgemeen().setParentId(gebruiker.getDomeinId());

		referenceform.setOproep(oproep);

		if (request.getParameter("adm") != null) {
			referenceform.setAdm(true);
		}

		if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("0")) {
				referenceform.setOproep(Oproep.findByPK(Integer.parseInt(request.getParameter("id"))));
				referenceform.setTaalvragen(Taalvraag.findAllByOproep(Integer.parseInt(request.getParameter("id"))));
			}
		}

		java.util.ArrayList<ParameterActief> media = ParameterActief.findAllActief(Queries.ALL_MEDIA_ACTIEF);
		java.util.ArrayList<ParameterActief> media_sorted = new java.util.ArrayList<ParameterActief>();
		for (int i = 0, j = media.size(); i < j; i++) {
			ParameterActief medium = (ParameterActief)media.get(i);
			if (medium.getOmschrijving().trim().equals("E-mail")) {
				media_sorted.add(0, medium);
			}
			else if (medium.getOmschrijving().trim().equals("Telefoon")) {
				media_sorted.add(0, medium);
			}
			else {
				media_sorted.add(medium);
			}
		}
		java.util.ArrayList<ParameterActief> herkomsten = ParameterActief.findAllActief(Queries.ALL_HERKOMSTEN_ACTIEF);
		java.util.ArrayList<ParameterBase> domeinen = ParameterBase.findAll(Queries.ALL_DOMEINEN);
		java.util.ArrayList<ParameterActief> statussen = ParameterActief.findAllActief(Queries.ALL_STATUSSEN_ACTIEF);


		referenceform.setMedia(media_sorted);
		referenceform.setHerkomsten(herkomsten);
		referenceform.setDomeinen(domeinen);
		referenceform.setStatussen(statussen);

  		session.setAttribute("Oproep_TaalvragenInvoerForm", referenceform);

  		if (request.getParameter("Button") !=  null && (request.getParameter("Button").equals("EditeerModus"))){
  		  if(request.getParameter("useEopro") != null)
  		      session.setAttribute("eopro", request.getParameter("useEopro"));
  		      referenceform.setUseEopro(request.getParameter("useEopro"));
		}


		if (request.getParameter("binnengekomen") != null){
		    return(mapping.findForward("binnengekomen"));
		}else if (request.getParameter("ro") != null) {
			return(mapping.findForward("readonly"));
		}
		else {
			return(mapping.findForward("success"));
		}

	}

}

