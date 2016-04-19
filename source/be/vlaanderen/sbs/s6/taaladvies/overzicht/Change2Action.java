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
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;

public class Change2Action extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		Result2Form referenceform = (Result2Form)form;
		
		if (request.getParameter("Change1") != null) {
			java.util.ArrayList<Oproep> oproepen1 = referenceform.getOproepen1();
			referenceform.setCurrentindex1(Integer.parseInt(request.getParameter("index")));
			referenceform.setOproepen1(oproepen1);
			int size = (oproepen1.size()/10);
			if (size*10 >= oproepen1.size()) {
				size = size - 1;
			}
			referenceform.setTotalsize1(size);  		
  			session.setAttribute("Result2Form", referenceform);		
		}

		if (request.getParameter("Change2") != null) {
			java.util.ArrayList<Oproep> oproepen2 = referenceform.getOproepen2();
			referenceform.setCurrentindex2(Integer.parseInt(request.getParameter("index")));
			referenceform.setOproepen2(oproepen2);
			int size = (oproepen2.size()/10);
			if (size*10 >= oproepen2.size()) {
				size = size - 1;
			}
			referenceform.setTotalsize2(size);  		
  			session.setAttribute("Result2Form", referenceform);		
		}

		return(mapping.findForward("success"));
		
	}

}

