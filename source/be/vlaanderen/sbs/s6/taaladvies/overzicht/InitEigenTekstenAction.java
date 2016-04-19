package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;

public class InitEigenTekstenAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		if(request.getParameter("Button") != null && (request.getParameter("Button").equals("EditeerModus"))){
		    session.setAttribute("eopro", request.getParameter("useEopro"));
		    System.err.println(session.getAttribute("eopro"));
		    return new ActionForward("/do/initEigenTeksten", true);
		}
		
		EigenTekstenForm referenceform = new EigenTekstenForm();

		Gebruiker gebruiker = (Gebruiker)session.getAttribute("Gebruiker");
		java.util.ArrayList<Oproep> oproepen = Oproep.findAllOpenByTypeAndGebruiker(2, gebruiker.getId());
					
		if (request.getParameter("index") != null) {
			int index = Integer.parseInt(request.getParameter("index"));

			AppLogger.getInstance().debug("Index before : " + index);
			
			if(oproepen.size() <= index)
				index=index-15;
			
			AppLogger.getInstance().debug("Index after : " + index);
							
			referenceform.setCurrentindex(index);
			session.setAttribute("eigenTxtIndex", String.valueOf(index));
		}
		else if(session.getAttribute("eigenTxtIndex") != null)
		{
			int index = Integer.parseInt((String)session.getAttribute("eigenTxtIndex"));

			AppLogger.getInstance().debug("Index before : " + index);
			
			if(oproepen.size() <= index)
				index=index-15;

			AppLogger.getInstance().debug("Index after : " + index);
						
			referenceform.setCurrentindex(index);
		}
		
		session.removeAttribute("eigenTlvIndex");
		session.removeAttribute("andereTlvIndex");
		session.removeAttribute("andereTxtIndex");
				
		referenceform.setOproepen(oproepen);
		
		int size = (oproepen.size()/15);
		if (size*15 >= oproepen.size()) {
			size = size - 1;
		}
		referenceform.setTotalsize(size); 
  		
  		session.setAttribute("EigenTekstenForm", referenceform);
		
		return(mapping.findForward("success"));
		
	}

}

