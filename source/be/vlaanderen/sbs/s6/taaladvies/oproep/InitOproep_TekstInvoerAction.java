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
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;

public class InitOproep_TekstInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		if(request.getParameter("Button") != null && (request.getParameter("Button").equals("EditeerModus"))){
//			System.err.println(request.getParameter("useEopro"));
//			Oproep_TekstInvoerForm otf = (Oproep_TekstInvoerForm) form;
//			System.err.println(otf.getUseEopro());
//		    //session.setAttribute("eopro", request.getParameter("useEopro"));
//			if (request.getParameter("ro") != null) {
//				return(mapping.findForward("readonly"));
//			}
//			else {
//				return(mapping.findForward("success"));
//			}
			Oproep_TekstInvoerForm otf = (Oproep_TekstInvoerForm) form;
			session.setAttribute("eopro", otf.getUseEopro());
		}

		Oproep_TekstInvoerForm referenceform = new Oproep_TekstInvoerForm();
		Oproep oproep = new Oproep();

		Gebruiker gebruiker = (Gebruiker)session.getAttribute("Gebruiker");
		oproep.getDoelgroep().getDoelgroepAlgemeen().setParentId(gebruiker.getDomeinId());

		referenceform.setOproep(oproep);

		if(session.getAttribute("eopro") != null){
		    referenceform.setUseEopro(session.getAttribute("eopro").toString());
		}

		if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("0")) {
				referenceform.setOproep(Oproep.findByPK(Integer.parseInt(request.getParameter("id"))));
				Tekst tekst = Tekst.findByOproep(Integer.parseInt(request.getParameter("id")));
				referenceform.setTekst(tekst);
				if (tekst.getId() != 0) {
					java.util.ArrayList<Tekstblok> tekstblokken = Tekstblok.findByParent(tekst.getId());
					referenceform.setTekstblokken(tekstblokken);
				}
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
		java.util.ArrayList<ParameterActief> relevanties = ParameterActief.findAllActief(Queries.ALL_RELEVANTIES_ACTIEF);


		referenceform.setMedia(media_sorted);
		referenceform.setHerkomsten(herkomsten);
		referenceform.setDomeinen(domeinen);
		referenceform.setStatussen(statussen);
		referenceform.setRelevanties(relevanties);

  		session.setAttribute("Oproep_TekstInvoerForm", referenceform);

		if (request.getParameter("ro") != null) {
			return(mapping.findForward("readonly"));
		}
		else {
			return(mapping.findForward("success"));
		}

	}

}

