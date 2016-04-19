/*
 * Created on Dec 10, 2004
 */
package be.vlaanderen.sbs.s6.taaladvies.oproep;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.overzicht.BinnengekomenTaalvragenForm;

public class VeranderDomeinAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

	    BinnengekomenTaalvragenForm referenceForm = (BinnengekomenTaalvragenForm)form;
		Oproep oproep = ((Oproep)referenceForm.getOproepen().get(Integer.parseInt(request.getParameter("index"))));
		oproep.setDomeinId(1);
		Oproep.update(oproep);
		
		return mapping.findForward("succes");
	}

}


