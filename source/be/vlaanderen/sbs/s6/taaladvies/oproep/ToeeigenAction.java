package be.vlaanderen.sbs.s6.taaladvies.oproep;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.html.Constants;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.overzicht.AlleTaalvragenForm;

public class ToeeigenAction extends BaseAction
{
    public ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	HttpServletResponse response) throws IOException, ServletException
    {
	HttpSession session = request.getSession();
	int id = Integer.parseInt(request.getParameter("id"));
	int taalvraagId = 0;
	if (request.getParameter("taalvraagid") != null)
	{
	    taalvraagId = Integer.parseInt(request.getParameter("taalvraagid"));
	}
	// Gebruiker gebruiker = (Gebruiker)session.getAttribute("Gebruiker");
	// nakijken of oproep ondertussen niet is toegeëigend door een andere taaladviseur
	Oproep oproep = Oproep.findByPK(id);
	Gebruiker taaladviseur = oproep.getGebruiker();
	if (taaladviseur.getId() != 0)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.toeeigenen.altoegeeigend"));
	    saveErrors(request, errors);
	    AlleTaalvragenForm referenceform = new AlleTaalvragenForm();
	    // zoek alle taalunievragen die nog niet toegeëigend zijn
	    ArrayList<Oproep> oproepen = Oproep.findAllNietToegeeigendeOfTaalunieversum();
	    if (request.getParameter("index") != null)
	    {
		int index = Integer.parseInt(request.getParameter("index"));
		if (oproepen.size() <= index)
		    index = index - 15;
		referenceform.setCurrentindex(index);
		session.setAttribute("tuTlvIndex", String.valueOf(index));
	    } else if (session.getAttribute("tuTlvIndex") != null)
	    {
		int index = Integer.parseInt((String) session.getAttribute("tuTlvIndex"));
		if (oproepen.size() <= index)
		    index = index - 15;
		referenceform.setCurrentindex(index);
	    }
	    session.removeAttribute("eigenTlvIndex");
	    session.removeAttribute("eigenTxtIndex");
	    session.removeAttribute("andereTxtIndex");
	    referenceform.setOproepen(oproepen);
	    int size = (oproepen.size() / 10);
	    if (size * 10 >= oproepen.size())
	    {
		size = size - 1;
	    }
	    referenceform.setTotalsize(size);
	    session.setAttribute("BinnengekomenTaalvragenForm", referenceform);
	    return (new ActionForward(mapping.getInput()));
	} else
	{
	    try
	    {
		Gebruiker gebruiker = (Gebruiker) session.getAttribute("Gebruiker");
		boolean check = Oproep.overname(id, gebruiker.getId());
		if (check)
		{
		    oproep = Oproep.findByPK(id);
		} else
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.toeeigenen.overname.gefaald"));
			saveErrors(request, errors);
			AlleTaalvragenForm referenceform = new AlleTaalvragenForm();
			// zoek alle taalunievragen die nog niet toegeëigend zijn
			ArrayList<Oproep> oproepen = Oproep.findAllNietToegeeigendeOfTaalunieversum();
			if (request.getParameter("index") != null)
			{
			    int index = Integer.parseInt(request.getParameter("index"));
			    if (oproepen.size() <= index)
				index = index - 15;
			    referenceform.setCurrentindex(index);
			    session.setAttribute("tuTlvIndex", String.valueOf(index));
			} else if (session.getAttribute("tuTlvIndex") != null)
			{
			    int index = Integer.parseInt((String) session.getAttribute("tuTlvIndex"));
			    if (oproepen.size() <= index)
				index = index - 15;
			    referenceform.setCurrentindex(index);
			}
			session.removeAttribute("eigenTlvIndex");
			session.removeAttribute("eigenTxtIndex");
			session.removeAttribute("andereTxtIndex");
			referenceform.setOproepen(oproepen);
			int size = (oproepen.size() / 10);
			if (size * 10 >= oproepen.size())
			{
			    size = size - 1;
			}
			referenceform.setTotalsize(size);
			session.setAttribute("BinnengekomenTaalvragenForm", referenceform);
			return (new ActionForward(mapping.getInput()));
		}
	    } catch (Exception e)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.toeeigenen.gebruiker.onbestaand"));
		saveErrors(request, errors);
		AlleTaalvragenForm referenceform = new AlleTaalvragenForm();
		// zoek alle taalunievragen die nog niet toegeëigend zijn
		ArrayList<Oproep> oproepen = Oproep.findAllNietToegeeigendeOfTaalunieversum();
		if (request.getParameter("index") != null)
		{
		    int index = Integer.parseInt(request.getParameter("index"));
		    if (oproepen.size() <= index)
			index = index - 15;
		    referenceform.setCurrentindex(index);
		    session.setAttribute("tuTlvIndex", String.valueOf(index));
		} else if (session.getAttribute("tuTlvIndex") != null)
		{
		    int index = Integer.parseInt((String) session.getAttribute("tuTlvIndex"));
		    if (oproepen.size() <= index)
			index = index - 15;
		    referenceform.setCurrentindex(index);
		}
		session.removeAttribute("eigenTlvIndex");
		session.removeAttribute("eigenTxtIndex");
		session.removeAttribute("andereTxtIndex");
		referenceform.setOproepen(oproepen);
		int size = (oproepen.size() / 10);
		if (size * 10 >= oproepen.size())
		{
		    size = size - 1;
		}
		referenceform.setTotalsize(size);
		session.setAttribute("BinnengekomenTaalvragenForm", referenceform);
		return (new ActionForward(mapping.getInput()));
	    }
	}
	if (request.getParameter("edit") != null)
	{
	    return (new ActionForward("/do/initTaalvragenInvoer?parentId=" + id + "&id=" + taalvraagId + "&"
		+ Constants.TOKEN_KEY + "=" + (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
	} else
	{
	    return (new ActionForward("/do/initBinnengekomenTaalvragen?" + "&" + Constants.TOKEN_KEY + "="
		+ (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
	}
    }
}
