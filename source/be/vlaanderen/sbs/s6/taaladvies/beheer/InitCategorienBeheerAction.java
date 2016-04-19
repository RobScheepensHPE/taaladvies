package be.vlaanderen.sbs.s6.taaladvies.beheer;

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

public class InitCategorienBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		CategorienBeheerForm referenceform = new CategorienBeheerForm();
		if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("0")) {
				referenceform.setCategorie((Categorie)Categorie.findByPK(Integer.parseInt(request.getParameter("id"))));
			}
		}

		java.util.ArrayList<Categorie> categorien = new java.util.ArrayList<Categorie>();
		if (request.getParameter("parentId") != null) {
			categorien = Categorie.findByParent(Integer.parseInt(request.getParameter("parentId")));		
			referenceform.setParentCategorie((Categorie)Categorie.findByPK(Integer.parseInt(request.getParameter("parentId"))));
/*			
			Categorie categorie = new Categorie();
			categorie.setParentId(Integer.parseInt(request.getParameter("parentId")));
			referenceform.setCategorie(categorie);
*/		
		} else if (referenceform.getCategorie() != null) {
			categorien = Categorie.findByParent(referenceform.getCategorie().getParentId());
		} else {
			categorien = Categorie.findByParent(0);
		}
				
		referenceform.setCategorien(categorien);
  		
  		session.setAttribute("CategorienBeheerForm", referenceform);
		
		
		return(mapping.findForward("success"));
		
	}

}

