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
import be.vlaanderen.sbs.s6.taaladvies.model.SearchCacheTXT;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;

public class KoppelTekstenAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check;
		//int size = Integer.parseInt(request.getParameter("amount"));
		TaalvragenInvoerForm oldform = (TaalvragenInvoerForm)session.getAttribute("TaalvragenInvoerForm");
		Taalvraag taalvraag = oldform.getTaalvraag();
		int id = taalvraag.getId();


		SearchCacheTXT searchCache = (SearchCacheTXT)session.getAttribute("cacheTXT");
		Tekst[] records = (Tekst[])searchCache.getRecords();

		for (int i = 0, j = records.length; i < j; i++) {
			Tekst tekst = records[i];
			if (request.getParameter("toKoppel_" + tekst.getId()) != null) {
				/*check = */Taalvraag.insertTekst(id, tekst.getId());
			}
		}

		return (new ActionForward("/do/initTaalvragenInvoer?id=" + id + "&parentId=" + taalvraag.getOproepId(), true));

	}

}

