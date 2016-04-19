package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.SearchCache;
import be.vlaanderen.sbs.s6.taaladvies.model.SearchCacheTLV;
import be.vlaanderen.sbs.s6.taaladvies.model.SearchCacheTXT;

public class SearchAction extends BaseAction {

    public ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.getSession().removeAttribute("cacheTLV");
        request.getSession().removeAttribute("cacheTXT");

        SearchForm searchForm = (SearchForm) form;

        SearchCache cacheTLV = null;
        SearchCache cacheTXT = null;

        if (!searchForm.getOwnerIrrelevant()) {
            if (!searchForm.getEigenTV() || !searchForm.getAlleTV()) {
                searchForm.setUserId(((Gebruiker) request.getSession().getAttribute("Gebruiker")).getId());
            } else {
                searchForm.setUserId(0);
            }
        }

        //Search Taalvragen
        if (searchForm.getEigenTV() || searchForm.getAlleTV()) {
            cacheTLV = new SearchCacheTLV(searchForm);

            // Only retrieve data if we actually found records that conform to the search criteria
            if (cacheTLV.getMaxCount() > 0) {
                cacheTLV.loadFirstRecords();
            }
        }
        if (!searchForm.getOwnerIrrelevant()) {
            if (!searchForm.getEigenTXT() || !searchForm.getAlleTXT()) {
                searchForm.setUserId(((Gebruiker) request.getSession().getAttribute("Gebruiker")).getId());
            } else {
                searchForm.setUserId(0);
            }
        }

        // Search Teksten
        if (searchForm.getEigenTXT() || searchForm.getAlleTXT()) {
            cacheTXT = new SearchCacheTXT(searchForm);

            // Only retrieve data if we actually found records that conform to the search criteria
            if (cacheTXT.getMaxCount() > 0) {
                cacheTXT.loadFirstRecords();
            }
        }

        if (searchForm.getEigenTV() || searchForm.getAlleTV()) {
            request.getSession().setAttribute("cacheTLV", cacheTLV);
        }

        if (searchForm.getEigenTXT() || searchForm.getAlleTXT()) {
            request.getSession().setAttribute("cacheTXT", cacheTXT);
        }
        return (mapping.findForward("success"));
    }
}	