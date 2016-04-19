package be.vlaanderen.sbs.s6.taaladvies;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import netscape.ldap.LDAPException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import be.vlaanderen.sbs.s6.taaladvies.ldap.LDAPQuerier;
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Toegangsrecht;

public class LoginAction extends Action {

    public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        ActionErrors errors = new ActionErrors();

        if (request.getParameter("Button") != null && (request.getParameter("Button").equals("EditeerModus"))) {
            session.setAttribute("eopro", request.getParameter("useEopro"));
        }

        //boolean check = false;
        LoginForm referenceForm = (LoginForm) form;

        LDAPQuerier ldap = new LDAPQuerier(AppConf.getResource("ldap.url"), Integer.parseInt(AppConf.getResource("ldap.port")), AppConf.getResource("ldap.baseDN"));

        String login = referenceForm.getLogin();
        String paswoord = referenceForm.getPaswoord();

        boolean ldapValidated = false;


        try {
            ldapValidated = ldap.authenticate(login, paswoord);
        } catch (LDAPException ex) {
            AppLogger.getInstance().error("Fout in LDAP : " + ex.getMessage());

            errors.add("system_error", new ActionError("global.error.system.loginerror"));
            saveErrors(request, errors);

            return (new ActionForward(mapping.getInput()));
        }

        /*ldapValidated = true;*/

        if (ldapValidated) {
            Gebruiker gebruiker = Gebruiker.findByLogin(login);
            java.util.ArrayList<Toegangsrecht> toegangsrechten = Toegangsrecht.findByGebruiker(gebruiker.getId());

            session.setAttribute("Gebruiker", gebruiker);
            session.setAttribute("Toegangsrechten", toegangsrechten);
            if (session.getAttribute("eopro") == null) {
                session.setAttribute("eopro", "true");
            }

            if (gebruiker.getActief()) {
                if (toegangsrechten.size() == 1) {
                    if (toegangsrechten.get(0).getId() == 1) {
                        return (mapping.findForward("eigentaalvragen"));
                    } else if (toegangsrechten.get(0).getId() == 2) {
                        return (mapping.findForward("beheerdermenu"));
                    } else {
                        return (mapping.findForward("externeraadpleger"));
                    }
                } else if (toegangsrechten.size() > 0) {
                    return (mapping.findForward("success"));
                } else {
                    errors.add("access_denied", new ActionError("global.login.notauthorized"));
                }
            } else {
                errors.add("not active", new ActionError("global.login.notactive"));
            }
        } else {
            errors.add("no_login", new ActionError("global.login.notauthenticated"));
        }

        saveErrors(request, errors);

        AppLogger.getInstance().debug("No access granted to the application");

        return (new ActionForward(mapping.getInput()));
    }

}

