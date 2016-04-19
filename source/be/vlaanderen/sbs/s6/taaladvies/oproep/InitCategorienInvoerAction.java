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
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;

public class InitCategorienInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		CategorienInvoerForm referenceform = new CategorienInvoerForm();
		
		if (request.getParameter("adm") != null) {
			referenceform.setAdm(true);
		}		
		
		Categorie categorie = new Categorie();
		java.util.ArrayList<Categorie> categorienLinked = new java.util.ArrayList<Categorie>();
		java.util.ArrayList<Categorie> categorien = new java.util.ArrayList<Categorie>();
		int taalvraagId = 0;
		int tekstId = 0;
		int id = Integer.parseInt(request.getParameter("id"));
		int type = Integer.parseInt(request.getParameter("type"));
		if (type == 1) {
			taalvraagId = id;
			Taalvraag taalvraag = Taalvraag.findByPK(id);
			referenceform.setTaalvraag(taalvraag);
			categorienLinked = Categorie.findByTaalvraagId(taalvraagId);			
		}
		else {
			tekstId = id;
			Tekst tekst = Tekst.findByPK(id);
			referenceform.setTekst(tekst);
			categorienLinked = Categorie.findByTekstId(tekstId);
		}
		
		categorien = Categorie.findByParentActief(0);
		Categorie categorieChange = new Categorie();
		for (int i = 0, j = categorien.size(); i < j; i++) {
			categorieChange = categorien.get(i);
			java.util.ArrayList<Categorie> subcategorien = Categorie.findByParentActief(categorieChange.getId());
			if (subcategorien.size() != 0) {
				categorieChange.setOmschrijving("+ " + categorieChange.getOmschrijving());
			}
			else {
				categorieChange.setOmschrijving("- " + categorieChange.getOmschrijving());
			}
		}
		referenceform.setCategorien(categorien);
		referenceform.setCategorie(categorie);
		referenceform.setCategorienLinked(categorienLinked);
  		
  		session.setAttribute("CategorienInvoerForm", referenceform);
		
		if (request.getParameter("ro") != null) {
			return(mapping.findForward("readonly"));
		}
		else {
			return(mapping.findForward("success"));
		}
		
	}

}

