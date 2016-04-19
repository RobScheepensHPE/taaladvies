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
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.model.Gemeente;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;

public class InitSearch2Action extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		Search2Form referenceform = new Search2Form();
		referenceform.getOproep().setMediumId(0);
		referenceform.getOproep().getDistributie().setMediumId(0);
		
		java.util.ArrayList<ParameterBase> gemeenten = Gemeente.findAll(Queries.ALL_GEMEENTEN); 
		java.util.ArrayList<ParameterBase> landen = ParameterBase.findAll(Queries.ALL_LANDEN);
		java.util.ArrayList<ParameterActief> media = ParameterActief.findAllActief(Queries.ALL_MEDIA_ACTIEF);
		java.util.ArrayList<ParameterActief> herkomsten = ParameterActief.findAllActief(Queries.ALL_HERKOMSTEN_ACTIEF);

		referenceform.setGemeenten(gemeenten);
		referenceform.setLanden(landen);
		referenceform.setMedia(media);
		referenceform.setHerkomsten(herkomsten);
		
  		session.setAttribute("Search2Form", referenceform);
		
		return(mapping.findForward("success"));
		
	}

}

