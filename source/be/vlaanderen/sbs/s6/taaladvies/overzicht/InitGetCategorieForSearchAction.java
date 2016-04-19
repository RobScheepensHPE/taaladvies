package be.vlaanderen.sbs.s6.taaladvies.overzicht;

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

public class InitGetCategorieForSearchAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		SearchForm formToSave = (SearchForm)form;

		session.setAttribute("SearchForm", formToSave);
		GetCategorieForSearchForm referenceform = new GetCategorieForSearchForm();
		Categorie categorie = new Categorie();
		java.util.ArrayList<Categorie> categorien = new java.util.ArrayList<Categorie>();
		String page = request.getParameter("page");
		int whichCategorie = Integer.parseInt(request.getParameter("whichCategorie"));
		referenceform.setPage(page);
		referenceform.setWhichCategorie(whichCategorie);
		categorien = Categorie.findByParent(0);
		Categorie categorieChange = new Categorie();
		for (int i = 0, j = categorien.size(); i < j; i++) {
			categorieChange = (Categorie)categorien.get(i);
			java.util.ArrayList<Categorie> subcategorien = Categorie.findByParent(categorieChange.getId());
			if (subcategorien.size() != 0) {
				categorieChange.setOmschrijving("+ " + categorieChange.getOmschrijving());
			}
			else {
				categorieChange.setOmschrijving("- " + categorieChange.getOmschrijving());
			}
		}
		referenceform.setCategorien(categorien);
		referenceform.setCategorie(categorie);
  		session.setAttribute("GetCategorieForSearchForm", referenceform);
		return(mapping.findForward("success"));
		
	}

}

