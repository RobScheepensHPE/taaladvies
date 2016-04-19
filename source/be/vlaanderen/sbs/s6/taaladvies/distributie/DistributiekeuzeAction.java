package be.vlaanderen.sbs.s6.taaladvies.distributie;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Distributie;
//import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Sjabloon;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class DistributiekeuzeAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		DistributiekeuzeForm referenceForm = (DistributiekeuzeForm)form;
		Oproep oproep = referenceForm.getOproep();
		//int id = oproep.getId();
		//Gebruiker gebruiker = (Gebruiker)session.getAttribute("Gebruiker");
		java.util.ArrayList<Taalvraag> taalvragen = referenceForm.getTaalvragen();

		String realpath = getServlet().getServletContext().getRealPath(File.separator);
		String filePath = new String(realpath+"mailtemp"+File.separator);

		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(oproep.getDistributie().getSjabloon());
		}

		if (request.getParameter("E-mail.x") != null) {

			DistributieMailing.startMail(referenceForm, filePath);

			for (int i = 0, j = taalvragen.size(); i < j; i++) {
				Taalvraag distributietaalvraag = (Taalvraag)taalvragen.get(i);
				distributietaalvraag.setDistributiedatum(new java.sql.Date(new java.util.Date().getTime()));
				/*check = */Taalvraag.updateDistributiedatum(distributietaalvraag);
			}
			return (mapping.findForward("bevestiging"));

		}

		if (request.getParameter("Bekijk.x") != null){
			return (new ActionForward("/distributie/distributiebekijk.jsp", false));
		}

		if (request.getParameter("Selectie bevestigen.x") != null) {

			if (oproep.getType() == 1) {
				int index = referenceForm.getIndex();
				Taalvraag taalvraag = (Taalvraag)referenceForm.getTaalvragen().get(index);

				if(request.getParameter("check_titel") == null){ taalvraag.setCheck_titel(false); }
				else {taalvraag.setCheck_titel(true); }
				if(request.getParameter("check_vraag") == null){ taalvraag.setCheck_vraag(false); }
				else {taalvraag.setCheck_vraag(true); }
				if(request.getParameter("check_antwoord") == null){ taalvraag.setCheck_antwoord(false); }
				else {taalvraag.setCheck_antwoord(true); }
				if(request.getParameter("check_toelichting") == null){ taalvraag.setCheck_toelichting(false); }
				else {taalvraag.setCheck_toelichting(true); }
				if(request.getParameter("check_bijzonderheid") == null){ taalvraag.setCheck_bijzonderheid(false); }
				else {taalvraag.setCheck_bijzonderheid(true); }
				if(request.getParameter("check_categorien") == null){ taalvraag.setCheck_categorien(false); }
				else {taalvraag.setCheck_categorien(true); }
				if(request.getParameter("check_naslagreferenties") == null){ taalvraag.setCheck_naslagreferenties(false); }
				else {taalvraag.setCheck_naslagreferenties(true); }
				if(request.getParameter("check_bronnen") == null){ taalvraag.setCheck_bronnen(false); }
				else {taalvraag.setCheck_bronnen(true); }
				if(request.getParameter("check_citaten") == null){ taalvraag.setCheck_citaten(false); }
				else {taalvraag.setCheck_citaten(true); }
				if(request.getParameter("check_frequenties") == null){ taalvraag.setCheck_frequenties(false); }
				else {taalvraag.setCheck_frequenties(true); }
				if(request.getParameter("check_webreferenties") == null){ taalvraag.setCheck_webreferenties(false); }
				else {taalvraag.setCheck_webreferenties(true); }
				if(request.getParameter("check_notities") == null){ taalvraag.setCheck_notities(false); }
				else {taalvraag.setCheck_notities(true); }
				if(request.getParameter("check_teksten") == null) {taalvraag.setCheck_teksten(false);}
				else {taalvraag.setCheck_teksten(true); }
				return (mapping.findForward("success"));
			}
			else {

				Tekst tekst = referenceForm.getTekst();
				//java.util.ArrayList tekstblokken = tekst.getTekstblokken();

				if(request.getParameter("tekst.check_titel") == null) { tekst.setCheck_titel(false); }
				else {tekst.setCheck_titel(true); }
				if(request.getParameter("tekst.check_categorien") == null){ tekst.setCheck_categorien(false); }
				else {tekst.setCheck_categorien(true); }
				if(request.getParameter("tekst.check_naslagreferenties") == null){ tekst.setCheck_naslagreferenties(false); }
				else {tekst.setCheck_naslagreferenties(true); }
				if(request.getParameter("tekst.check_bronnen") == null){ tekst.setCheck_bronnen(false); }
				else {tekst.setCheck_bronnen(true); }
				if(request.getParameter("tekst.check_citaten") == null){ tekst.setCheck_citaten(false); }
				else {tekst.setCheck_citaten(true); }
				if(request.getParameter("tekst.check_frequenties") == null){ tekst.setCheck_frequenties(false); }
				else {tekst.setCheck_frequenties(true); }
				if(request.getParameter("tekst.check_webreferenties") == null){ tekst.setCheck_webreferenties(false); }
				else {tekst.setCheck_webreferenties(true); }
				if(request.getParameter("tekst.check_notities") == null){ tekst.setCheck_notities(false); }
				else {tekst.setCheck_notities(true); }
				return (mapping.findForward("success"));
			}
		}

		if (request.getParameter("Onthoud voorlopige gegevens.x") != null) {
		    setFile(referenceForm, filePath);

			return (mapping.findForward("success"));
		}

		if (request.getParameter("Button").equals("KiesSjabloon")) {

			setFile(referenceForm, filePath);

			int sjabloonId = oproep.getDistributie().getSjabloonId();
			oproep.getDistributie().setSjabloon((Sjabloon)Sjabloon.findByPK(sjabloonId));
			referenceForm.setOproep(oproep);
			Distributie distributie = oproep.getDistributie();
			distributie.setDistributiedatum(new java.sql.Date(new java.util.Date().getTime()));
			distributie.setSjabloonId(sjabloonId);
			/*check = */Distributie.update(distributie);
			session.setAttribute("DistributiekeuzeForm", referenceForm);
			return (mapping.findForward("success"));

		}

		return (mapping.findForward("backtopage"));

	}

	public void setFile(DistributiekeuzeForm referenceForm, String filePath) {
			FormFile theFile = referenceForm.getTheFile();
			String fileName = referenceForm.getFileName();
			if (theFile!=null) {
				fileName = theFile.getFileName();
				if (theFile.getFileName() != null && !theFile.getFileName().equals("")) {
					referenceForm.setFileName(fileName);
				}
			}
			if (theFile!=null && fileName.length()!=0) {
	  			uploadFile(theFile, filePath);
			}
	}

	public void uploadFile(FormFile file, String filePath) {

		//retrieve the file name
		//String fileName= file.getFileName();

		//retrieve the content type
		//String contentType = file.getContentType();
		//retrieve the file size
		//String size = (file.getFileSize() + " bytes");

		//String data = null;

		if(file.getFileSize()>0){

		}
		try {

			//retrieve the file data

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream stream = file.getInputStream();
			if ((file.getFileSize() > 0)&&(file.getFileSize() < (4*1024000))) {

				//write the file to the file specified
				//OutputStream bos = new FileOutputStream(filePath+fileName);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
					baos.write(buffer, 0, bytesRead);
				}
				baos.close();
				//data = "The file has been written to \"" + filePath + "\"";
			}else{
				// the file is to big to upload
			}
			//close the stream
			stream.close();
		}
		catch (FileNotFoundException fnfe) {

		}
		catch (IOException ioe) {

		}
		file.destroy();
	}



	/**
     * vervangt de new lines in dit veld door br - html tags in niet-eopro editeer modus
     * @param sjabloon de te formateren sjabloon
     */
    private void formatHTMLVelden(Sjabloon sjabloon) {
       sjabloon.setHandtekeningHTML(Util.formatNewLinesToBr(sjabloon.getHandtekening()));
       sjabloon.setInhoudHTML(Util.formatNewLinesToBr(sjabloon.getInhoud()));
       sjabloon.setSlotgroetHTML(Util.formatNewLinesToBr(sjabloon.getSlotgroet()));
    }


}

