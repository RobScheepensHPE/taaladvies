package be.vlaanderen.sbs.s6.taaladvies.beheer;

import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
//import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;

public class OproepOpenenAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		//HttpSession session = request.getSession();

		OproepOpenenForm referenceForm = (OproepOpenenForm)form;
		int id = referenceForm.getId();

		/*boolean check = */Oproep.open(id);

		//deleteTaalvragenFromVerity(id);

		return (mapping.findForward("success"));

	}

    /**
     * verwijder alle taalvragen uit verity
     * @param oproepId de oproep id
     */
    /*private void deleteTaalvragenFromVerity(int oproepId) {
        ArrayList list = Taalvraag.findAllByOproep(oproepId);
		for (Iterator iter = list.iterator(); iter.hasNext();) {
            Taalvraag taalvraag = (Taalvraag) iter.next();
            Taalvraag.deleteVerityEntry(taalvraag.getId());
        }
    }*/

}

