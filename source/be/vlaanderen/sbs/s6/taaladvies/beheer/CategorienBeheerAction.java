package be.vlaanderen.sbs.s6.taaladvies.beheer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.html.Constants;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;

public class CategorienBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean /*check = */false;
		CategorienBeheerForm referenceForm = (CategorienBeheerForm)form;
		Categorie categorie = referenceForm.getCategorie();
		Categorie parentCategorie = referenceForm.getParentCategorie();
		int id = categorie.getId();

		if (request.getParameter("Naar Hoofdcategorie.x") != null) {
			if (id != 0)
			{
				Categorie parent = (Categorie)Categorie.findByPK(categorie.getParentId());
				int parentId = parent.getParentId();
				return (new ActionForward("/do/initCategorienBeheer?parentId=" + parentId + "&id=" + categorie.getParentId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			}
			else
			{
				int parentId = parentCategorie.getParentId();
				return (new ActionForward("/do/initCategorienBeheer?parentId=" + parentId + "&id=" + parentCategorie.getId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			}
		}
		if (request.getParameter("Naar Subcategorie.x") != null) {
			return (new ActionForward("/do/initCategorienBeheer?parentId=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}
		if (request.getParameter("Wijzigen.x") != null)
		{
			String newNummer = categorie.getParentNummer() + categorie.getOwnNummer();

			if(!categorie.getNummer().equals(newNummer))
			{
				if(Categorie.isNumberAvailable(newNummer))
				{
					String oldNummer = categorie.getNummer();
					categorie.setNummer(newNummer);
					/*check = */Categorie.update(categorie);
					/*check = */Categorie.updateByParent(oldNummer, newNummer);
				}
				else
				{
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("beheer.categorien.bestaand.nummer"));
					saveErrors(request, errors);
					return (new ActionForward(mapping.getInput()));
				}
			}
			else
			{
				/*check = */Categorie.update(categorie);
			}

			return (new ActionForward("/do/initCategorienBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			categorie.setParentId(parentCategorie.getId());

			if(parentCategorie.getId() > 0)
			{
				categorie.setParentNummer(parentCategorie.getNummer().trim()+".");
				categorie.setNummer(categorie.getParentNummer() + categorie.getOwnNummer());
			}
			else
			{
				categorie.setNummer(categorie.getOwnNummer());
			}

			if(Categorie.isNumberAvailable(categorie.getNummer()))
			{
				id = Categorie.insert(categorie);
			}
			else
			{
				org.apache.struts.action.ActionErrors errors = new org.apache.struts.action.ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("beheer.categorien.bestaand.nummer"));
				saveErrors(request, errors);
				return (new ActionForward(mapping.getInput()));
			}

			return (new ActionForward("/do/initCategorienBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		return (mapping.findForward("backtopage"));

	}

}

