package be.vlaanderen.sbs.s6.taaladvies.distributie;

import java.io.File;
//import org.apache.struts.upload.FormFile;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import be.vlaanderen.sbs.s6.taaladvies.model.Bron;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Citaat;
import be.vlaanderen.sbs.s6.taaladvies.model.Frequentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagreferentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Notitie;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;
import be.vlaanderen.sbs.s6.taaladvies.model.Webreferentie;
import be.vlaanderen.sbs.s6.taaladvies.utils.Mailing;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class DistributieMailing {

	public static void startMail(DistributiekeuzeForm form, String filePath) {

		try{

			StringBuffer mailMessage = new StringBuffer();

			mailMessage.append("<p>\n");
			if(form.getOproep().getDistributie().getSjabloon().getAanspreking() != null)
			{
				mailMessage.append(form.getOproep().getDistributie().getSjabloon().getAanspreking());
			}
			else
			{
				if (form.getOproep().getNaam().equals("Onbekend") || form.getOproep().getNaam().equals("")) {
					if (form.getOproep().getGeslacht().equals("o")) {
						mailMessage.append("Geachte heer, <br>Geachte mevrouw");
					}
					else if (form.getOproep().getGeslacht().equals("m")) {
						mailMessage.append("Geachte heer");
					}
					else {
						mailMessage.append("Geachte mevrouw");
					}
				}
				else {
					if (form.getOproep().getGeslacht().equals("m")) {
						mailMessage.append("Geachte heer ");
					}
					else if (form.getOproep().getGeslacht().equals("v")) {
						mailMessage.append("Geachte mevrouw ");
					}
					else {
						mailMessage.append("Geachte heer of mevrouw ");
					}
					mailMessage.append(form.getOproep().getNaam());
				}

				mailMessage.append(",\n");
			}

			mailMessage.append("</p>\n");
			mailMessage.append("<p>");
			mailMessage.append(form.getOproep().getDistributie().getSjabloon().getInhoudHTML());
			mailMessage.append("</p>\n");
			mailMessage.append("<p>");
			mailMessage.append(form.getOproep().getDistributie().getSjabloon().getSlotgroetHTML());
			mailMessage.append("</p>\n");
			mailMessage.append("<p>");
			mailMessage.append(form.getOproep().getGebruiker().getVoornaam() + " " + form.getOproep().getGebruiker().getNaam());
			mailMessage.append("<br />\n");
			mailMessage.append(form.getOproep().getDistributie().getSjabloon().getHandtekeningHTML());
			mailMessage.append("</p>\n");

			mailMessage.append("<hr />\n");

			if (form.getOproep().getType() == 1) {

				if (form.getTaalvragen().size() > 1) {
					mailMessage.append("<p>Overzicht van de taalvragen </p>\n");
					mailMessage.append("<ul>\n");
					for (int i = 0, j = form.getTaalvragen().size(); i < j; i++) {
						Taalvraag taalvraag = (Taalvraag)form.getTaalvragen().get(i);
						if (taalvraag.getCheck_titel()) {
							mailMessage.append("<li>\n");
							mailMessage.append("<a href='#titel" + i + "'>");
							if (!taalvraag.getTitel().trim().equals("")) {
								mailMessage.append(taalvraag.getTitel());
							}
							else {
								mailMessage.append(taalvraag.getVraag());
							}
							mailMessage.append("</a>\n");
							mailMessage.append("</li>\n");
						}
					}
					mailMessage.append("</ul>\n");
					mailMessage.append("<hr />\n");
				}

				for (int i = 0, j = form.getTaalvragen().size(); i < j; i++) {
					mailMessage.append("<table width='100%'>\n");
					mailMessage.append("<a name='titel" + i + "'></a>\n");
					Taalvraag taalvraag = (Taalvraag)form.getTaalvragen().get(i);
					if (taalvraag.getCheck_titel()) {
					if (taalvraag.getTitel() != null && !taalvraag.getTitel().equals("") && !taalvraag.getTitel().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top' width='5%'><b>Titel  </b></td>\n");
						mailMessage.append("<td>\n");
						mailMessage.append(taalvraag.getTitel());
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
					}
					if (taalvraag.getCheck_vraag()) {
					if (taalvraag.getVraag() != null && !taalvraag.getVraag().equals("") && !taalvraag.getVraag().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top' width='5%'><b>Vraag  </b></td>\n");
						mailMessage.append("<td>");
						mailMessage.append(taalvraag.getVraag());
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
					}
					if (taalvraag.getCheck_antwoord()) {
					if (taalvraag.getAntwoordHTML() != null && !taalvraag.getAntwoordHTML().equals("") && !taalvraag.getAntwoordHTML().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'><b>Antwoord  </b></td>\n");
						mailMessage.append("<td>");
						mailMessage.append(taalvraag.getAntwoordHTML());
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
					}
					if (taalvraag.getCheck_toelichting()) {
					if (taalvraag.getToelichtingHTML() != null && !taalvraag.getToelichtingHTML().equals("") && !taalvraag.getToelichtingHTML().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'><b>Toelichting  </b></td>\n");
						mailMessage.append("<td>");
						mailMessage.append(taalvraag.getToelichtingHTML());
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
					}
					if (taalvraag.getCheck_bijzonderheid()) {
					if (taalvraag.getBijzonderheidHTML() != null && !taalvraag.getBijzonderheidHTML().equals("") && !taalvraag.getBijzonderheidHTML().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'><b>Bijzonderheid  </b></td>\n");
						mailMessage.append("<td>");
						mailMessage.append(taalvraag.getBijzonderheidHTML());
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
					}
					if (taalvraag.getCheck_categorien()) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'><b>Categorie  </b></td>\n");
						mailMessage.append("<td>");
						if (taalvraag.getCategorien() != null) {
							java.util.ArrayList<Categorie> categorien = taalvraag.getCategorien();
							for (int k = 0, l = categorien.size(); k < l; k++) {
								Categorie categorie = (Categorie)categorien.get(k);
								if (categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getId() != 0) {
									mailMessage.append(categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getOmschrijving());
									mailMessage.append(">");
									mailMessage.append(categorie.getOmschrijving());
								}
								else {
									if (categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getId() != 0) {
										mailMessage.append(categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getOmschrijving());
										mailMessage.append(">");
										mailMessage.append(categorie.getOmschrijving());
									}
									else {
										if (categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getId() != 0) {
											mailMessage.append(categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getOmschrijving());
											mailMessage.append(">");
											mailMessage.append(categorie.getOmschrijving());
										}
										else {
											if (categorie.getSuperCategorie().getSuperCategorie().getId() != 0) {
												mailMessage.append(categorie.getSuperCategorie().getSuperCategorie().getOmschrijving());
												mailMessage.append(">");
												mailMessage.append(categorie.getOmschrijving());
											}
											else {
												if (categorie.getSuperCategorie().getId() != 0) {
													mailMessage.append(categorie.getSuperCategorie().getOmschrijving());
													mailMessage.append(">");
													mailMessage.append(categorie.getOmschrijving());
												}
												else {
													if (categorie.getId() != 0) {
														mailMessage.append(categorie.getOmschrijving());
													}
												}
											}
										}
									}
								}
								mailMessage.append("\n<br />\n");
							}
						}
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
					if (taalvraag.getCheck_naslagreferenties()) {
						if (taalvraag.getNaslagreferenties() != null) {
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'><b>Naslagwerk  </b></td>\n");
							mailMessage.append("<td>");
							java.util.ArrayList<Naslagreferentie> naslagreferenties = taalvraag.getNaslagreferenties();
							mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
							mailMessage.append("<tr bgcolor='#dddddd'>\n");
							mailMessage.append("<th align='left'>Verkorte titel</th>\n");
							mailMessage.append("<th align='left'>Pagina</th>\n");
							mailMessage.append("<th align='left'>Variant</th>\n");
							mailMessage.append("<th align='left'>Informatie</th>\n");
							mailMessage.append("</tr>\n");
							for (int k = 0, l = naslagreferenties.size(); k < l; k++) {
								Naslagreferentie naslagreferentie = (Naslagreferentie)naslagreferenties.get(k);
								mailMessage.append("<tr>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(naslagreferentie.getNaslagwerk().getOmschrijving());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(naslagreferentie.getPaginas());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(naslagreferentie.getVariant());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(naslagreferentie.getCitaatHTML());
								mailMessage.append("</td>\n");
								mailMessage.append("</tr>\n");
							}
							mailMessage.append("</table>\n");
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
					}
					if (taalvraag.getCheck_bronnen()) {
						if (taalvraag.getBronnen() != null) {
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'><b>Bron  </b></td>\n");
							mailMessage.append("<td>");
							java.util.ArrayList<Bron> bronnen = taalvraag.getBronnen();
							mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
							mailMessage.append("<tr bgcolor='#dddddd'>\n");
							mailMessage.append("<th align='left'>Titel</th>\n");
							mailMessage.append("<th align='left'>Pagina</th>\n");
							mailMessage.append("<th align='left'>Variant</th>\n");
							mailMessage.append("<th align='left'>Informatie</th>\n");
							mailMessage.append("</tr>\n");
							for (int k = 0, l = bronnen.size(); k < l; k++) {
								Bron bron = (Bron)bronnen.get(k);
								mailMessage.append("<tr>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(bron.getTitel());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(bron.getPaginas());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(bron.getVariant());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(bron.getCitaatHTML());
								mailMessage.append("</td>\n");
								mailMessage.append("</tr>\n");
							}
							mailMessage.append("</table>\n");
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
					}
					if (taalvraag.getCheck_citaten()) {
						if (taalvraag.getCitaten() != null) {
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'><b>Citaat  </b></td>\n");
							mailMessage.append("<td>");
							java.util.ArrayList<Citaat> citaten = taalvraag.getCitaten();
							mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
							mailMessage.append("<tr bgcolor='#dddddd'>\n");
							mailMessage.append("<th align='left'>Zoekomgeving</th>\n");
							mailMessage.append("<th align='left'>Specificatie</th>\n");
							mailMessage.append("<th align='left'>URL</th>\n");
							mailMessage.append("<th align='left'>Variant</th>\n");
							mailMessage.append("<th align='left'>Citaat</th>\n");
							mailMessage.append("</tr>\n");
							for (int k = 0, l = citaten.size(); k < l; k++) {
								Citaat citaat = (Citaat)citaten.get(k);
								mailMessage.append("<tr>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(citaat.getZoekomgeving().getOmschrijving());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(citaat.getSpecificatie());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'><a href='");
								mailMessage.append(citaat.getUrl());
								mailMessage.append("' target='blank'>link</a></td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(citaat.getVariant());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(citaat.getCitaatHTML());
								mailMessage.append("</td>\n");
								mailMessage.append("</tr>\n");
							}
							mailMessage.append("</table>\n");
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
					}
					if (taalvraag.getCheck_frequenties()) {
						if (taalvraag.getCitaten() != null) {
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'><b>Frequentie  </b></td>\n");
							mailMessage.append("<td>");
							java.util.ArrayList<Frequentie> frequenties = taalvraag.getFrequenties();
							mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
							mailMessage.append("<tr bgcolor='#dddddd'>\n");
							mailMessage.append("<th align='left'>Zoekomgeving</th>\n");
							mailMessage.append("<th align='left'>Specificatie</th>\n");
							mailMessage.append("<th align='left'>Variant</th>\n");
							mailMessage.append("<th align='left'>Aantal</th>\n");
							mailMessage.append("</tr>\n");
							for (int k = 0, l = frequenties.size(); k < l; k++) {
								Frequentie frequentie = frequenties.get(k);
								mailMessage.append("<tr>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(frequentie.getZoekomgeving().getOmschrijving());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(frequentie.getSpecificatie());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(frequentie.getVariant());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(frequentie.getAantal());
								mailMessage.append("</td>\n");
								mailMessage.append("</tr>\n");
							}
							mailMessage.append("</table>\n");
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
					}
					if (taalvraag.getCheck_webreferenties()) {
						if (taalvraag.getWebreferenties() != null) {
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'><b>Koppeling  </b></td>\n");
							mailMessage.append("<td>");
							java.util.ArrayList<Webreferentie> webreferenties = taalvraag.getWebreferenties();
							mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
							mailMessage.append("<tr bgcolor='#dddddd'>\n");
							mailMessage.append("<th align='left'>Omgeving</th>\n");
							mailMessage.append("<th align='left'>URL</th>\n");
							mailMessage.append("<th align='left'>Toelichting</th>\n");
							mailMessage.append("</tr>\n");
							for (int k = 0, l = webreferenties.size(); k < l; k++) {
								Webreferentie webreferentie = webreferenties.get(k);
								mailMessage.append("<tr>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(webreferentie.getOmgeving());
								mailMessage.append("</td>\n");
								mailMessage.append("<td valign='top'><a href='");
								mailMessage.append(webreferentie.getUrl());
								mailMessage.append("' target='_blank'>link</a></td>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(webreferentie.getToelichting());
								mailMessage.append("</td>\n");
								mailMessage.append("</tr>\n");
							}
							mailMessage.append("</table>\n");
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
					}
					if (taalvraag.getCheck_notities()) {
						if (taalvraag.getNotities() != null) {
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'><b>Notitie  </b></td>\n");
							mailMessage.append("<td>");
							java.util.ArrayList<Notitie> notities = taalvraag.getNotities();
							mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
							for (int k = 0, l = notities.size(); k < l; k++) {
								Notitie notitie = notities.get(k);
								mailMessage.append("<tr>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(notitie.getGebruiker().getVoornaam());
								mailMessage.append(" ");
								mailMessage.append(notitie.getGebruiker().getNaam());
								mailMessage.append(" op ");
								mailMessage.append(notitie.getDatumString());
								mailMessage.append("</td>\n");
								mailMessage.append("</tr>\n");
								mailMessage.append("<tr>\n");
								mailMessage.append("<td valign='top'>");
								mailMessage.append(notitie.getNotitieHTML());
								mailMessage.append("</td>\n");
								mailMessage.append("</tr>\n");
							}
							mailMessage.append("</table>\n");
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
					}
					mailMessage.append("<tr><td colspan='2'><hr /></td></tr>\n");
					if (taalvraag.getCheck_teksten()) {
						if (taalvraag.getTeksten() != null) {
							mailMessage.append("<tr>\n");
							mailMessage.append("<td colspan='2'>\n");
							mailMessage.append("</tr>\n");
							mailMessage.append("<tr>\n");
							mailMessage.append("<td colspan='2'>");
							java.util.ArrayList<Tekst> teksten = taalvraag.getTeksten();
							for (int k = 0, l = teksten.size(); k < l; k++) {
								Tekst tekst = (Tekst)teksten.get(k);
								mailMessage.append("<p>");
								mailMessage.append(tekst.getTitel());
								mailMessage.append("</p>\n");
								mailMessage.append("<table>\n");
								mailMessage.append("<tr>\n");
								mailMessage.append("<td>&nbsp;</td>\n");
								mailMessage.append("<td><hr /></td>\n");
								mailMessage.append("</tr>\n");
								if (tekst.getTekstblokken() != null) {
									java.util.ArrayList<Tekstblok> tekstblokken = tekst.getTekstblokken();
									for (int m = 0, n = tekstblokken.size(); m < n; m++) {
										Tekstblok tekstblok = (Tekstblok)tekstblokken.get(m);
										mailMessage.append("<tr>\n");
										mailMessage.append("<td valign='top'>");
										mailMessage.append(tekstblok.getTitel());
										mailMessage.append("</td>\n");
										mailMessage.append("<td>");
										mailMessage.append(tekstblok.getTekstblokHTML());
										mailMessage.append("<hr />");
										mailMessage.append("</td>\n");
										mailMessage.append("</tr>\n");
									}
								}
								mailMessage.append("</table>\n");
								mailMessage.append("<hr />\n");
							}
						}
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
					mailMessage.append("</table>\n");
				}
			}
			else {
				Tekst tekst = form.getTekst();
				mailMessage.append("<p>");
				mailMessage.append(tekst.getTitel());
				mailMessage.append("</p>\n");
				mailMessage.append("<hr />\n");
				mailMessage.append("<table align='center'>\n");
				if (tekst.getTekstblokken() != null) {
					java.util.ArrayList<Tekstblok> tekstblokken = tekst.getTekstblokken();
					for (int m = 0, n = tekstblokken.size(); m < n; m++) {
						Tekstblok tekstblok = (Tekstblok)tekstblokken.get(m);
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'>");
						mailMessage.append(tekstblok.getTitel());
						mailMessage.append("</td>\n");
						mailMessage.append("<td>");
						mailMessage.append(tekstblok.getTekstblokHTML());
						mailMessage.append("<hr />");
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
				}
				mailMessage.append("</table>\n");
				mailMessage.append("<table>");
				if (tekst.getCheck_categorien()) {
					mailMessage.append("<tr>\n");
					mailMessage.append("<td valign='top'><b>Categoriën: </b></td>\n");
					mailMessage.append("<td>");
					if (tekst.getCategorien() != null) {
						java.util.ArrayList<Categorie> categorien = tekst.getCategorien();
						for (int k = 0, l = categorien.size(); k < l; k++) {
							Categorie categorie = (Categorie)categorien.get(k);
							if (categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getId() != 0) {
								mailMessage.append(categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getOmschrijving());
								mailMessage.append(">");
								mailMessage.append(categorie.getOmschrijving());
							}
							else {
								if (categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getId() != 0) {
									mailMessage.append(categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getSuperCategorie().getOmschrijving());
									mailMessage.append(">");
									mailMessage.append(categorie.getOmschrijving());
								}
								else {
									if (categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getId() != 0) {
										mailMessage.append(categorie.getSuperCategorie().getSuperCategorie().getSuperCategorie().getOmschrijving());
										mailMessage.append(">");
										mailMessage.append(categorie.getOmschrijving());
									}
									else {
										if (categorie.getSuperCategorie().getSuperCategorie().getId() != 0) {
											mailMessage.append(categorie.getSuperCategorie().getSuperCategorie().getOmschrijving());
											mailMessage.append(">");
											mailMessage.append(categorie.getOmschrijving());
										}
										else {
											if (categorie.getSuperCategorie().getId() != 0) {
												mailMessage.append(categorie.getSuperCategorie().getOmschrijving());
												mailMessage.append(">");
												mailMessage.append(categorie.getOmschrijving());
											}
											else {
												if (categorie.getId() != 0) {
													mailMessage.append(categorie.getOmschrijving());
												}
											}
										}
									}
								}
							}
							mailMessage.append("\n<br />\n");
						}
					}
					mailMessage.append("</td>\n");
					mailMessage.append("</tr>\n");
				}
				if (tekst.getCheck_naslagreferenties()) {
					if (tekst.getNaslagreferenties() != null) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'><b>Naslagwerk  </b></td>\n");
						mailMessage.append("<td>");
						java.util.ArrayList<Naslagreferentie> naslagreferenties = tekst.getNaslagreferenties();
						mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
						mailMessage.append("<tr bgcolor='#dddddd'>\n");
						mailMessage.append("<th align='left'>Verkorte titel</th>\n");
						mailMessage.append("<th align='left'>Pagina</th>\n");
						mailMessage.append("<th align='left'>Variant</th>\n");
						mailMessage.append("<th align='left'>Informatie</th>\n");
						mailMessage.append("</tr>\n");
						for (int k = 0, l = naslagreferenties.size(); k < l; k++) {
							Naslagreferentie naslagreferentie = (Naslagreferentie)naslagreferenties.get(k);
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(naslagreferentie.getNaslagwerk().getOmschrijving());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(naslagreferentie.getPaginas());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(naslagreferentie.getVariant());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(naslagreferentie.getCitaatHTML());
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
						mailMessage.append("</table>\n");
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
				}
				if (tekst.getCheck_bronnen()) {
					if (tekst.getBronnen() != null) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'><b>Bron  </b></td>\n");
						mailMessage.append("<td>");
						java.util.ArrayList<Bron> bronnen = tekst.getBronnen();
						mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
						mailMessage.append("<tr bgcolor='#dddddd'>\n");
						mailMessage.append("<th align='left'>Titel</th>\n");
						mailMessage.append("<th align='left'>Pagina</th>\n");
						mailMessage.append("<th align='left'>Variant</th>\n");
						mailMessage.append("<th align='left'>Informatie</th>\n");
						mailMessage.append("</tr>\n");
						for (int k = 0, l = bronnen.size(); k < l; k++) {
							Bron bron = (Bron)bronnen.get(k);
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(bron.getTitel());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(bron.getPaginas());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(bron.getVariant());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(bron.getCitaatHTML());
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
						mailMessage.append("</table>\n");
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
				}
				if (tekst.getCheck_citaten()) {
					if (tekst.getCitaten() != null) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'><b>Citaat  </b></td>\n");
						mailMessage.append("<td>");
						java.util.ArrayList<Citaat> citaten = tekst.getCitaten();
						mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
						mailMessage.append("<tr bgcolor='#dddddd'>\n");
						mailMessage.append("<th align='left'>Zoekomgeving</th>\n");
						mailMessage.append("<th align='left'>Specificatie</th>\n");
						mailMessage.append("<th align='left'>URL</th>\n");
						mailMessage.append("<th align='left'>Variant</th>\n");
						mailMessage.append("<th align='left'>Citaat</th>\n");
						mailMessage.append("</tr>\n");
						for (int k = 0, l = citaten.size(); k < l; k++) {
							Citaat citaat = (Citaat)citaten.get(k);
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(citaat.getZoekomgeving().getOmschrijving());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(citaat.getSpecificatie());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'><a href='");
							mailMessage.append(citaat.getUrl());
							mailMessage.append("' target='_blank'>link</a></td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(citaat.getVariant());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(citaat.getCitaatHTML());
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
						mailMessage.append("</table>\n");
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
				}
				if (tekst.getCheck_frequenties()) {
					if (tekst.getCitaten() != null) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'><b>Frequentie  </b></td>\n");
						mailMessage.append("<td>");
						java.util.ArrayList<Frequentie> frequenties = tekst.getFrequenties();
						mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
						mailMessage.append("<tr bgcolor='#dddddd'>\n");
						mailMessage.append("<th align='left'>Zoekomgeving</th>\n");
						mailMessage.append("<th align='left'>Specificatie</th>\n");
						mailMessage.append("<th align='left'>Variant</th>\n");
						mailMessage.append("<th align='left'>Aantal</th>\n");
						mailMessage.append("</tr>\n");
						for (int k = 0, l = frequenties.size(); k < l; k++) {
							Frequentie frequentie = (Frequentie)frequenties.get(k);
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(frequentie.getZoekomgeving().getOmschrijving());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(frequentie.getSpecificatie());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(frequentie.getVariant());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(frequentie.getAantal());
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
						mailMessage.append("</table>\n");
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
				}
				if (tekst.getCheck_webreferenties()) {
					if (tekst.getWebreferenties() != null) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'><b>Koppeling  </b></td>\n");
						mailMessage.append("<td>");
						java.util.ArrayList<Webreferentie> webreferenties = tekst.getWebreferenties();
						mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
						mailMessage.append("<tr bgcolor='#dddddd'>\n");
						mailMessage.append("<th align='left'>Omgeving</th>\n");
						mailMessage.append("<th align='left'>URL</th>\n");
						mailMessage.append("<th align='left'>Toelichting</th>\n");
						mailMessage.append("</tr>\n");
						for (int k = 0, l = webreferenties.size(); k < l; k++) {
							Webreferentie webreferentie = (Webreferentie)webreferenties.get(k);
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(webreferentie.getOmgeving());
							mailMessage.append("</td>\n");
							mailMessage.append("<td valign='top'><a href='");
							mailMessage.append(webreferentie.getUrl());
							mailMessage.append("' target='_blank'>link</a></td>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(webreferentie.getToelichting());
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
						mailMessage.append("</table>\n");
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
				}
				if (tekst.getCheck_notities()) {
					if (tekst.getNotities() != null) {
						mailMessage.append("<tr>\n");
						mailMessage.append("<td valign='top'><b>Notitie  </b></td>\n");
						mailMessage.append("<td>");
						java.util.ArrayList<Notitie> notities = tekst.getNotities();
						mailMessage.append("<table cellpadding='2' width='100%' align='center'>\n");
						for (int k = 0, l = notities.size(); k < l; k++) {
							Notitie notitie = (Notitie)notities.get(k);
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(notitie.getGebruiker().getVoornaam());
							mailMessage.append(" ");
							mailMessage.append(notitie.getGebruiker().getNaam());
							mailMessage.append(" op ");
							mailMessage.append(notitie.getDatumString());
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
							mailMessage.append("<tr>\n");
							mailMessage.append("<td valign='top'>");
							mailMessage.append(notitie.getNotitieHTML());
							mailMessage.append("</td>\n");
							mailMessage.append("</tr>\n");
						}
						mailMessage.append("</table>\n");
						mailMessage.append("</td>\n");
						mailMessage.append("</tr>\n");
					}
				}
				mailMessage.append("</table>\n");
			}


			Mailing mailer = new Mailing();
			mailer.setContentType(AppConf.getResource("mail.contentType"));

			if (form.getOproep().getDomein().getId() == 1)
				mailer.setFrom(AppConf.getResource("mail.fromIntern"));
			else
				mailer.setFrom(AppConf.getResource("mail.fromExtern"));

			mailer.setTo(form.getOproep().getEmail());
			mailer.setCc(form.getOproep().getDistributie().getCc());

			StringBuffer bcc = new StringBuffer(form.getOproep().getDistributie().getBcc().trim());

			if(bcc.length() > 0 && !bcc.toString().equals(""))
			{
				bcc.append(";");
			}

			mailer.setBcc(bcc.toString());
			mailer.setBody(mailMessage.toString());
			mailer.setSubject(form.getOproep().getDistributie().getSjabloon().getOnderwerp());
			//mailer.setSmtpserver(AppConf.getResource("mail.host"));
			if (form.getTheFile() != null) {
				//FormFile theFile = form.getTheFile();
				String fileName = form.getFileName();
				if (fileName != null && !fileName.equals("")) {
					File[] attachments = new File[1];
					attachments[0] = new File(filePath + fileName);
					mailer.setAttachments(attachments);
				}
			}
			mailer.sendMail();

		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}

	}

}

