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
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;
import be.vlaanderen.sbs.s6.taaladvies.model.Toegangsrecht;

public class InitGebruikersBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		GebruikersBeheerForm referenceform = new GebruikersBeheerForm();
		if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("0")) {
				referenceform.setGebruiker(Gebruiker.findByPK(Integer.parseInt(request.getParameter("id"))));
			}
		}
		
		java.util.ArrayList<Gebruiker> gebruikers = Gebruiker.findAll();
		java.util.ArrayList<Toegangsrecht> toegangsrechten = Toegangsrecht.findAll();
		java.util.ArrayList<ParameterBase> domeinen = ParameterBase.findAll(Queries.ALL_DOMEINEN);
		
		referenceform.setGebruikers(gebruikers);
  		referenceform.setToegangsrechten(toegangsrechten);
  		referenceform.setDomeinen(domeinen);
  				
  		session.setAttribute("GebruikersBeheerForm", referenceform);
		
		
		return(mapping.findForward("success"));
		
	}


}

