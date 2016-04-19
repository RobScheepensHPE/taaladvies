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
import be.vlaanderen.sbs.s6.taaladvies.model.Sjabloon;

public class InitSjablonenBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		SjablonenBeheerForm referenceform = new SjablonenBeheerForm();
		
		if(session.getAttribute("eopro") != null){
		    referenceform.setUseEopro(session.getAttribute("eopro").toString());
		}
		
		if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("0")) {
				referenceform.setSjabloon((Sjabloon)Sjabloon.findByPK(Integer.parseInt(request.getParameter("id"))));
			}
		}
		
		java.util.ArrayList<Sjabloon> sjablonen = Sjabloon.findAll();
		
		referenceform.setSjablonen(sjablonen);
  		
  		session.setAttribute("SjablonenBeheerForm", referenceform);
		
		
		return(mapping.findForward("success"));
		
	}

}

