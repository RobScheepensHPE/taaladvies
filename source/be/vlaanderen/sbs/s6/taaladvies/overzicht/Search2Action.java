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

public class Search2Action extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();
		Search2Form referenceform = (Search2Form)form;
		Oproep oproep = referenceform.getOproep();
		String datum1 = referenceform.getDatum1();
		String datum2 = referenceform.getDatum2();
		String datum3 = referenceform.getDatum3();

		if (request.getParameter("Zoek.x") != null) {
			Result2Form resultform = new Result2Form();
			oproep = Oproep.findByPKAfgehandeld(oproep.getId());
			if (oproep.getId() != 0) {
				if (oproep.getType() == 1) {
					java.util.ArrayList<Oproep> oproepen1 = new java.util.ArrayList<Oproep>();
					oproepen1.add(oproep);
					resultform.setOproepen1(oproepen1);
				}
				else {
					java.util.ArrayList<Oproep> oproepen2 = new java.util.ArrayList<Oproep>();
					oproepen2.add(oproep);
					resultform.setOproepen2(oproepen2);
				}
			}
			session.setAttribute("Result2Form", resultform);
		}

		if (request.getParameter("Zoek2.x") != null) {
			Result2Form resultform = new Result2Form();
			java.util.ArrayList<Oproep> oproepen1 = new java.util.ArrayList<Oproep>();
			java.util.ArrayList<Oproep> oproepen2 = new java.util.ArrayList<Oproep>();


			oproep.setGebruikerId(((Gebruiker)session.getAttribute("Gebruiker")).getId());

			if (referenceform.getEigenTV() && referenceform.getAlleTV()) {
				oproep.setType(1);
				oproepen1 = Oproep.findByAdminSearch(oproep, datum1, datum2, datum3, 1);
				//some code here
			} else {
				if (referenceform.getEigenTV()) {
					oproep.setType(1);
					oproepen1 = Oproep.findByAdminSearch(oproep, datum1, datum2, datum3, 2);
				}
				if (referenceform.getAlleTV()) {
					oproep.setType(1);
					oproepen1 = Oproep.findByAdminSearch(oproep, datum1, datum2, datum3, 3);
				}
			}
			if (referenceform.getEigenTXT() && referenceform.getAlleTXT()) {
				oproep.setType(2);
				oproepen2 = Oproep.findByAdminSearch(oproep, datum1, datum2, datum3, 1);
			} else {
				if (referenceform.getEigenTXT()) {
					oproep.setType(2);
					oproepen2 = Oproep.findByAdminSearch(oproep, datum1, datum2, datum3, 2);
				}
				if (referenceform.getAlleTXT()) {
					oproep.setType(2);
					oproepen2 = Oproep.findByAdminSearch(oproep, datum1, datum2, datum3, 3);
				}
			}

			resultform.setOproepen1(oproepen1);
			resultform.setOproepen2(oproepen2);
			int size1 = (oproepen1.size()/10);
			if (size1*10 >= oproepen1.size()) {
				size1 = size1 - 1;
			}
			resultform.setTotalsize1(size1);
			int size2 = (oproepen2.size()/10);
			if (size2*10 >= oproepen2.size()) {
				size2 = size2 - 1;
			}
			resultform.setTotalsize2(size2);
			session.setAttribute("Result2Form", resultform);
		}

		return(mapping.findForward("success"));

	}

}

