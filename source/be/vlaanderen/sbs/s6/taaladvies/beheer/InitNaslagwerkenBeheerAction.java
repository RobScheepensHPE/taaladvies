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
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagwerk;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;

public class InitNaslagwerkenBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		NaslagwerkenBeheerForm referenceform = new NaslagwerkenBeheerForm();
		
		if(session.getAttribute("eopro") != null){
		    referenceform.setUseEopro(session.getAttribute("eopro").toString());
		}
		
		if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("0")) {
				referenceform.setNaslagwerk((Naslagwerk)Naslagwerk.findByPK(Integer.parseInt(request.getParameter("id"))));
			}
		}
		
		java.util.ArrayList<Naslagwerk> naslagwerken = Naslagwerk.findAll();
		java.util.ArrayList<ParameterActief> bibliografien = ParameterActief.findAll(Queries.ALL_BIBLIOGRAFIEN);
		
		referenceform.setNaslagwerken(naslagwerken);
		referenceform.setBibliografien(bibliografien);
  		
  		session.setAttribute("NaslagwerkenBeheerForm", referenceform);
		
		
		return(mapping.findForward("success"));
		
	}

}

