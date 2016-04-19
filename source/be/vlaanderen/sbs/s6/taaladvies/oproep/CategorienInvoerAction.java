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
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;

public class CategorienInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		CategorienInvoerForm referenceForm = (CategorienInvoerForm)form;
		Categorie categorie = referenceForm.getCategorie();
		java.util.ArrayList<Categorie> categorien = referenceForm.getCategorien();
		int id = categorie.getId();
		int type = 0;
		int taalvraagId = 0;
		int tekstId = 0;
		int changeId = 0;

		if (referenceForm.getTaalvraag() != null) {
			type = 1;
			taalvraagId = referenceForm.getTaalvraag().getId();
			changeId = taalvraagId;
		}
		if (referenceForm.getTekst() != null) {
			type = 2;
			tekstId = referenceForm.getTekst().getId();
			changeId = tekstId;
		}

		if (request.getParameter("Naar Hoofdcategorie.x") != null) {
			Categorie parent = (Categorie)Categorie.findByPK(categorie.getParentId());
			int parentId = parent.getId();
			categorien = Categorie.findByParentActief(parentId);

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
			referenceForm.setCategorien(categorien);
			referenceForm.setCategorie((Categorie)Categorie.findByPK(categorie.getParentId()));
			session.setAttribute("CategorienInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Toevoegen aan Taalvraag.x") != null) {
			Categorie.insertForTaalvraag(taalvraagId, id);
			return (new ActionForward("/do/initCategorienInvoer?id=" + taalvraagId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen aan Tekst.x") != null) {
			Categorie.insertForTekst(tekstId, id);
			return (new ActionForward("/do/initCategorienInvoer?id=" + tekstId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Change")) {
			referenceForm.setCategorie((Categorie)Categorie.findByPK(id));
			categorien = Categorie.findByParentActief(id);
			if (categorien.size() != 0) {
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
				referenceForm.setCategorien(categorien);
			}
			else {
				if (type == 1) {
					Categorie.insertForTaalvraag(taalvraagId, id);
					return (new ActionForward("/do/initCategorienInvoer?id=" + taalvraagId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
				}
				else {
					Categorie.insertForTekst(tekstId, id);
					return (new ActionForward("/do/initCategorienInvoer?id=" + tekstId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
				}
			}
			session.setAttribute("CategorienInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}


		if (request.getParameter("Button").equals("Verwijder")) {
			id = Integer.parseInt(request.getParameter("id"));
			if (type == 1) {
				/*check = */Categorie.deleteForTaalvraag(changeId, id);
			}
			else {
				/*check = */Categorie.deleteForTekst(changeId, id);
			}
			return (new ActionForward("/do/initCategorienInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		return (mapping.findForward("backtopage"));

	}

}

