package be.vlaanderen.sbs.s6.taaladvies.oproep;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;

public class InitTekstblokkenInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		TekstblokkenInvoerForm referenceform = new TekstblokkenInvoerForm();
		Tekstblok tekstblok = new Tekstblok();

		if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("0")) {
				tekstblok = Tekstblok.findByPK(Integer.parseInt(request.getParameter("id")));				
				referenceform.setTekst(Tekst.findByPK(Integer.parseInt(request.getParameter("parentId"))));
				referenceform.setTekstblok(tekstblok);
			}
			else {
				referenceform.setTekst(Tekst.findByPK(Integer.parseInt(request.getParameter("parentId"))));
			}
		}
		else {
			Tekst tekst = Tekst.findByPK(Integer.parseInt(request.getParameter("parentId")));
			int maxvolgnummer = Tekstblok.findMaxVolgnummer(tekst.getId());
			if (maxvolgnummer == 0) {
				maxvolgnummer = 1;
				tekstblok.setVolgnummer(maxvolgnummer);
			}
			else {
				tekstblok.setVolgnummer(maxvolgnummer + 1);
			}
			referenceform.setTekstblok(tekstblok);
			referenceform.setTekst(tekst);
		}	
		
   		session.setAttribute("TekstblokkenInvoerForm", referenceform);
		
		if (request.getParameter("ro") != null) {
			return(mapping.findForward("readonly"));
		}
		else {
			return(mapping.findForward("success"));
		}
		
	}

}

