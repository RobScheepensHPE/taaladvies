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
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;

public class InitKopieerTaalvraagAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		KopieerTaalvraagForm referenceform = new KopieerTaalvraagForm();

		//Integer taalvraagId = (Integer)session.getAttribute("TaalvraagId");
		//Integer oproepId = (Integer)session.getAttribute("OproepId");


		referenceform.setTaalvraag(Taalvraag.findByPK(Integer.parseInt(request.getParameter("id"))));


  		session.setAttribute("KopieerTaalvraagForm", referenceform);

		return(mapping.findForward("success"));


	}

}

