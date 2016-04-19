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

public class GetCategorieForSearchAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		GetCategorieForSearchForm referenceForm = (GetCategorieForSearchForm)form;
		Categorie categorie = referenceForm.getCategorie();
		java.util.ArrayList<Categorie> categorien = referenceForm.getCategorien();
		int id = categorie.getId();

		if (request.getParameter("Naar Subcategorie.x") != null) {
			referenceForm.setCategorie((Categorie)Categorie.findByPK(id));
			categorien = Categorie.findByParent(id);
			if (categorien.size() != 0) {
				Categorie categorieChange = new Categorie();
				for (int i = 0, j = categorien.size(); i < j; i++) {
					categorieChange = categorien.get(i);
					java.util.ArrayList<Categorie> subcategorien = Categorie.findByParent(categorieChange.getId());
					if (subcategorien.size() != 0) {
						categorieChange.setOmschrijving("+ " + categorieChange.getOmschrijving());
					}
					else {
						categorieChange.setOmschrijving("- " + categorieChange.getOmschrijving());
					}
				}
				referenceForm.setCategorien(categorien);
			}

			session.setAttribute("GetCategorieForSearchForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Naar Hoofdcategorie.x") != null) {
			Categorie parent = (Categorie)Categorie.findByPK(categorie.getParentId());
			int parentId = parent.getId();
			categorien = Categorie.findByParent(parentId);

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
			referenceForm.setCategorien(categorien);
			referenceForm.setCategorie((Categorie)Categorie.findByPK(categorie.getParentId()));
			session.setAttribute("GetCategorieForSearchForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Selecteren.x") != null) {
			SearchForm searchForm = (SearchForm)session.getAttribute("SearchForm");
			if(id == 0){
				searchForm.removeCategorie(referenceForm.getWhichCategorie());
			} else {
				searchForm.setCategorie(referenceForm.getWhichCategorie(), (Categorie)Categorie.findByPK(id));
			}

//			if (referenceForm.getWhichCategorie() == 1) {
//				searchForm.setCategorie1((Categorie)Categorie.findByPK(id));
//				searchForm.setCategorie(0, (Categorie)Categorie.findByPK(id));
//			}
//			else {
//				searchForm.setCategorie2((Categorie)Categorie.findByPK(id));
//				searchForm.setCategorie(1, (Categorie)Categorie.findByPK(id));
//			}
			return (mapping.findForward(referenceForm.getPage()));
		}

		if (request.getParameter("Verwijderen") != null) {
			SearchForm searchForm = (SearchForm)session.getAttribute("SearchForm");

			searchForm.removeCategorie(referenceForm.getWhichCategorie());
			return (mapping.findForward(referenceForm.getPage()));
		}

		if (request.getParameter("Annuleren") != null) {
			return (mapping.findForward(referenceForm.getPage()));
		}


		return (mapping.findForward("backtopage"));

	}

}

