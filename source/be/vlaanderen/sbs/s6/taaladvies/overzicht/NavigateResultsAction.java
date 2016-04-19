package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseDispatchAction;
import be.vlaanderen.sbs.s6.taaladvies.model.SearchCache;

public class NavigateResultsAction extends BaseDispatchAction {
    public ActionForward nextTLV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        SearchCache cache = getCacheFromSession(request, SESSION_KEY_CACHE_TLV);

        cache.loadNextRecords();

        return (mapping.findForward("success"));
    }

    public ActionForward previousTLV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        SearchCache cache = getCacheFromSession(request, SESSION_KEY_CACHE_TLV);

        cache.loadPreviousRecords();

        return (mapping.findForward("success"));
    }

    public ActionForward nextTXT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        SearchCache cache = getCacheFromSession(request, SESSION_KEY_CACHE_TXT);

        cache.loadNextRecords();

        return (mapping.findForward("success"));
    }

    public ActionForward previousTXT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        SearchCache cache = getCacheFromSession(request, SESSION_KEY_CACHE_TXT);

        cache.loadPreviousRecords();

        return (mapping.findForward("success"));
    }


    private SearchCache getCacheFromSession(HttpServletRequest request, String sessionKey) {
        return (SearchCache) request.getSession().getAttribute(sessionKey);
    }
}

