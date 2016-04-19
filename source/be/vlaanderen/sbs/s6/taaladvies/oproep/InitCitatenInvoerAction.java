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
import be.vlaanderen.sbs.s6.taaladvies.model.Citaat;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;

public class InitCitatenInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		CitatenInvoerForm referenceform = new CitatenInvoerForm();
		
		if (request.getParameter("adm") != null) {
			referenceform.setAdm(true);
		}		
		 
		Citaat citaat = new Citaat();
		int taalvraagId = 0;
		int tekstId = 0;
		int id = Integer.parseInt(request.getParameter("id"));
		int type = Integer.parseInt(request.getParameter("type"));
		if (type == 1) {
			taalvraagId = id;
			Taalvraag taalvraag = Taalvraag.findByPK(id);
			referenceform.setTaalvraag(taalvraag);
		}
		else {
			tekstId = id;
			Tekst tekst = Tekst.findByPK(id);
			referenceform.setTekst(tekst);
		}
		
		java.util.ArrayList<Citaat> citaten = Citaat.findAllByParent(taalvraagId, tekstId);
		java.util.ArrayList<ParameterActief> zoekomgevingen = ParameterActief.findAllActief(Queries.ALL_ZOEKOMGEVINGEN_ACTIEF);
		
		referenceform.setCitaat(citaat);
		referenceform.setCitaten(citaten);
		referenceform.setZoekomgevingen(zoekomgevingen);

  		session.setAttribute("CitatenInvoerForm", referenceform);
		
		if (request.getParameter("ro") != null) {
			return(mapping.findForward("readonly"));
		}
		else {
			return(mapping.findForward("success"));
		}
		
	}

}

