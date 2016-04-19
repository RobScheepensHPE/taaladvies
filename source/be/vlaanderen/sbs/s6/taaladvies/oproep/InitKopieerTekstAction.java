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

public class InitKopieerTekstAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		KopieerTekstForm referenceform = new KopieerTekstForm();

		//Integer oproepId = (Integer)session.getAttribute("OproepId");


		Tekst tekst = Tekst.findByPKFull(Integer.parseInt(request.getParameter("id")));

		referenceform.setTekst(tekst);
		referenceform.setTekstblokken(tekst.getTekstblokken());

  		session.setAttribute("KopieerTekstForm", referenceform);

		return(mapping.findForward("success"));


	}

}

