package be.vlaanderen.sbs.s6.taaladvies;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;

public class VerwijderOpTeLadenOproepenAction extends Action
{
    public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	HttpServletResponse response) throws IOException, ServletException
    {
	ActionErrors errors = new ActionErrors();
	VerwijderOpTeLadenOproepenForm referenceForm = (VerwijderOpTeLadenOproepenForm) form;
	String id = referenceForm.getId();
	try {
	    Integer.parseInt(id);
	} catch (NumberFormatException ex) {
	    errors.add("system_error", new ActionError("verwijderopteladenoproepen.geennummer"));
	    saveErrors(request, errors);
	    return (new ActionForward(mapping.getInput()));
	}
	if (!Oproep.removeFromTaalunieSendQueue(Integer.parseInt(id)))
	{
	    AppLogger.getInstance().error("Taalvraag " + id + " kon niet worden verwijderd.");
	    errors.add("system_error", new ActionError("verwijderopteladenoproepen.foutbijverwijderen",id));
	    saveErrors(request, errors);
	    return (new ActionForward(mapping.getInput()));
	} else
	{
	    AppLogger.getInstance().error("Taalvraag " + id + " werd verwijderd.");
	    errors.add("system_error", new ActionError("verwijderopteladenoproepen.succesvolverwijderd",id));
	    saveErrors(request, errors);
	    return (new ActionForward(mapping.getInput()));
	}
    }
}
