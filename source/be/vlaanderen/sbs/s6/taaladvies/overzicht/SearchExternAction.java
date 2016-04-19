package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.SearchCache;
import be.vlaanderen.sbs.s6.taaladvies.model.SearchCacheTLV;
import be.vlaanderen.sbs.s6.taaladvies.model.SearchCacheTXT;

public class SearchExternAction extends BaseAction {
	
	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {			
		
	    request.getSession().removeAttribute("cacheTLV");
		request.getSession().removeAttribute("cacheTXT");
	    
		SearchForm referenceForm = (SearchForm) form;
				
		SearchCache cacheTLV = null;
		SearchCache cacheTXT = null;		
		
		if(referenceForm.getAlleTV())
		{													
			cacheTLV = new SearchCacheTLV(referenceForm);							
			
			// Only retrieve data if we actually found records that conform to the search criteria 
			
			if(cacheTLV.getMaxCount() > 0)
				cacheTLV.loadFirstRecords();
		}

		
		// Do search part for texts
		if(referenceForm.getAlleTXT())			
		{																	
			cacheTXT = new SearchCacheTXT(referenceForm);									

			// Only retrieve data if we actually found records that conform to the search criteria 
			
			if(cacheTXT.getMaxCount() > 0)
				cacheTXT.loadFirstRecords();				
		}

		
		if(referenceForm.getEigenTV() || referenceForm.getAlleTV())			
			request.getSession().setAttribute("cacheTLV",cacheTLV);			
			
		if(referenceForm.getEigenTXT() || referenceForm.getAlleTXT())			
			request.getSession().setAttribute("cacheTXT",cacheTXT);			
	
		return(mapping.findForward("success"));		
	}	
}	

