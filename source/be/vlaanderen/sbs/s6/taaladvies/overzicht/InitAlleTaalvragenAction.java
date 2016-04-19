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
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;

public class InitAlleTaalvragenAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		if(request.getParameter("Button") != null && (request.getParameter("Button").equals("EditeerModus"))){
		    session.setAttribute("eopro", request.getParameter("useEopro"));
		    System.err.println(session.getAttribute("eopro"));
		    return new ActionForward("/do/initAlleTaalvragen", true);
		}
		
		AlleTaalvragenForm referenceform = new AlleTaalvragenForm();

		Gebruiker gebruiker = (Gebruiker)session.getAttribute("Gebruiker");
		java.util.ArrayList<Oproep> oproepen = Oproep.findAllOpenByTypeAndNotGebruiker(1, gebruiker.getId());

		if (request.getParameter("index") != null) {
			int index = Integer.parseInt(request.getParameter("index"));

			if(oproepen.size() <= index)
				index=index-15;
				
			referenceform.setCurrentindex(index);
			session.setAttribute("andereTlvIndex", String.valueOf(index));
		}
		else if(session.getAttribute("andereTlvIndex") != null)
		{
			int index = Integer.parseInt((String)session.getAttribute("andereTlvIndex"));

			if(oproepen.size() <= index)
				index=index-15;
			
			referenceform.setCurrentindex(index);
		}
		
		session.removeAttribute("eigenTlvIndex");
		session.removeAttribute("eigenTxtIndex");
		session.removeAttribute("andereTxtIndex");

		referenceform.setOproepen(oproepen);
		
		int size = (oproepen.size()/10);
		if (size*10 >= oproepen.size()) {
			size = size - 1;
		}
		referenceform.setTotalsize(size);  		
  		session.setAttribute("AlleTaalvragenForm", referenceform);
		
		return(mapping.findForward("success"));
		
	}

}

