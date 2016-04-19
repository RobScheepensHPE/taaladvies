<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn />

<html:html locale="true">
<head>
	<title>Databank Taaladvies</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
	<link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript" />

	<script>
		
		function doSelect1() {
			if (document.forms['KopieerTaalvraagForm'].elements['check_Vraag'].checked == true) {
				document.forms['KopieerTaalvraagForm'].elements['check_Titel'].checked = false;
				document.forms['KopieerTaalvraagForm'].elements['check_Vraag'].checked = false;
				document.forms['KopieerTaalvraagForm'].elements['check_Antwoord'].checked = false;
				document.forms['KopieerTaalvraagForm'].elements['check_Toelichting'].checked = false;
				document.forms['KopieerTaalvraagForm'].elements['check_Bijzonderheid'].checked = false;
			}
			else {
				document.forms['KopieerTaalvraagForm'].elements['check_Titel'].checked = true;
				document.forms['KopieerTaalvraagForm'].elements['check_Vraag'].checked = true;
				document.forms['KopieerTaalvraagForm'].elements['check_Antwoord'].checked = true;
				document.forms['KopieerTaalvraagForm'].elements['check_Toelichting'].checked = true;
				document.forms['KopieerTaalvraagForm'].elements['check_Bijzonderheid'].checked = true;
			}
		}
		function doSelect2() {
			if (document.forms['KopieerTaalvraagForm'].elements['check_Categorie'].checked == true) {
				document.forms['KopieerTaalvraagForm'].elements['check_Categorie'].checked = false;
				document.forms['KopieerTaalvraagForm'].elements['check_Naslagreferentie'].checked = false;
				document.forms['KopieerTaalvraagForm'].elements['check_Bron'].checked = false;
				document.forms['KopieerTaalvraagForm'].elements['check_Citaat'].checked = false;
				document.forms['KopieerTaalvraagForm'].elements['check_Frequentie'].checked = false;
				document.forms['KopieerTaalvraagForm'].elements['check_Webreferentie'].checked = false;
				document.forms['KopieerTaalvraagForm'].elements['check_Notitie'].checked = false;
			}
			else {
				document.forms['KopieerTaalvraagForm'].elements['check_Categorie'].checked = true;
				document.forms['KopieerTaalvraagForm'].elements['check_Naslagreferentie'].checked = true;
				document.forms['KopieerTaalvraagForm'].elements['check_Bron'].checked = true;
				document.forms['KopieerTaalvraagForm'].elements['check_Citaat'].checked = true;
				document.forms['KopieerTaalvraagForm'].elements['check_Frequentie'].checked = true;
				document.forms['KopieerTaalvraagForm'].elements['check_Webreferentie'].checked = true;
				document.forms['KopieerTaalvraagForm'].elements['check_Notitie'].checked = true;
			}
		}
	</script>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		

</head>

<body>

<h2>Kopieer Taalvraag</h2>
<p>
	<a class="bigger" href="../overzicht/resultaatkopieerTLV.jsp">
		Zoekresultaten
	</a>
</p>
<html:form method="post" action="/kopieerTaalvraag">
	<html:hidden name="KopieerTaalvraagForm" property="taalvraag.id" />
	<html:hidden name="KopieerTaalvraagForm" property="taalvraag.vraag" />
	<img src="../images/button_selecteer_vraaggegevens.gif" border="0" onclick="doSelect1()" />
	<table cellspacing="2">					
		<tr>
			<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Titel" /></td>
			<td valign="top"><b>Titel  </b></td>
			<td><bean:write name="KopieerTaalvraagForm" property="taalvraag.titel" filter="false" /></td>
		</tr>
		<tr>
			<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Vraag" /></td>
			<td colspan="2" valign="top"><b>Taalvraag  </b></td>
		</tr>
		<tr>
			<td colspan="3"><bean:write name="KopieerTaalvraagForm" property="taalvraag.vraag" filter="false" /></td>
		</tr>
		<tr>
			<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Antwoord" /></td>
			<td colspan="2" valign="top"><b>Antwoord  </b></td>
		</tr>
		<tr>
			<td colspan="3"><bean:write name="KopieerTaalvraagForm" property="taalvraag.antwoordHTML" filter="false" /></td>
		</tr>
		<tr>
			<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Toelichting" /></td>
			<td colspan="2" valign="top"><b>Toelichting  </b></td>
		</tr>
		<tr>
			<td colspan="3"><bean:write name="KopieerTaalvraagForm" property="taalvraag.toelichtingHTML" filter="false" /></td>
		</tr>
		<tr>
			<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Bijzonderheid" /></td>
			<td colspan="2" valign="top"><b>Bijzonderheid  </b></td>
		</tr>
		<tr>
			<td colspan="3"><bean:write name="KopieerTaalvraagForm" property="taalvraag.bijzonderheidHTML" filter="false" /></td>
		</tr>
	</table>	
	<hr />
	<img src="../images/button_selecteer_hulpmiddelen.gif" border="0" onclick="doSelect2()" />		
	<table cellspacing="2">
		<tr>
			<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Categorie" /></td>
			<td valign="top"><b>Categorie  </b></td>
			<td>
				<logic:present name="KopieerTaalvraagForm" property="taalvraag.categorien">
					<logic:iterate property="taalvraag.categorien" name="KopieerTaalvraagForm" id="categorieni">
						<logic:notEqual name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
							<bean:write name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
							<bean:write name="categorieni" property="omschrijving" /> 
						</logic:notEqual>
						<logic:equal name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
							<logic:notEqual name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
								<bean:write name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
								<bean:write name="categorieni" property="omschrijving" />
							</logic:notEqual>
							<logic:equal name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
								<logic:notEqual name="categorieni" property="superCategorie.superCategorie.superCategorie.id" value="0">
									<bean:write name="categorieni" property="superCategorie.superCategorie.superCategorie.omschrijving" /> > 
									<bean:write name="categorieni" property="omschrijving" />
								</logic:notEqual>
								<logic:equal name="categorieni" property="superCategorie.superCategorie.superCategorie.id" value="0">
									<logic:notEqual name="categorieni" property="superCategorie.superCategorie.id" value="0">
										<bean:write name="categorieni" property="superCategorie.superCategorie.omschrijving" /> >
										<bean:write name="categorieni" property="omschrijving" />
									</logic:notEqual>							
									<logic:equal name="categorieni" property="superCategorie.superCategorie.id" value="0">
										<logic:notEqual name="categorieni" property="superCategorie.id" value="0">
											<bean:write name="categorieni" property="superCategorie.omschrijving" /> > 
											<bean:write name="categorieni" property="omschrijving" />
										</logic:notEqual>
										<logic:equal name="categorieni" property="superCategorie.id" value="0">
											<logic:notEqual name="categorieni" property="id" value="0">
												<bean:write name="categorieni" property="omschrijving" />
											</logic:notEqual>
										</logic:equal>
									</logic:equal>
								</logic:equal>
							</logic:equal>
						</logic:equal>
						<br	>							
					</logic:iterate>
				</logic:present>
			</td>
		</tr>
		<logic:present name="KopieerTaalvraagForm" property="taalvraag.naslagreferenties">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Naslagreferentie" /></td>
				<td valign="top"><b>Naslagwerk  </b></td>
				<td valign="top">
				
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Verkorte titel</th>
							<th align="left">Variant</th>
							<th align="left">Pagina's</th>
							<th align="left">Informatie</th>
						</tr>		
						<logic:iterate name="KopieerTaalvraagForm" property="taalvraag.naslagreferenties" id="naslagreferentiesi">
							<tr>
								<td><bean:write name="naslagreferentiesi" property="naslagwerk.omschrijving" filter="true" /></td>
								<td><bean:write name="naslagreferentiesi" property="variant" filter="true" /></td>
								<td><bean:write name="naslagreferentiesi" property="paginas" filter="true" /></td>
								<td><bean:write name="naslagreferentiesi" property="citaatHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
		</logic:present>
		<logic:present name="KopieerTaalvraagForm" property="taalvraag.bronnen">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Bron" /></td>
				<td valign="top"><b>Bron  </b></td>
				<td valign="top">				
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Titel</th>
							<th align="left">Pagina</th>
							<th align="left">Variant</th>
							<th align="left">Informatie</th>
						</tr>		
						<logic:iterate name="KopieerTaalvraagForm" property="taalvraag.bronnen" id="bronneni">
							<tr>
								<td><bean:write name="bronneni" property="titelHTML" filter="false" /></td>
								<td><bean:write name="bronneni" property="paginas" filter="true" /></td>
								<td><bean:write name="bronneni" property="variant" filter="true" /></td>
								<td><bean:write name="bronneni" property="citaatHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>					
				</td>
			</tr>
		</logic:present>
		<logic:present name="KopieerTaalvraagForm" property="taalvraag.citaten">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Citaat" /></td>
				<td valign="top"><b>Citaat  </b></td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Zoekomgeving</th>
							<th align="left">Specificatie</th>
							<th align="left">URL</th>
							<th align="left">Variant</th>
							<th align="left">Citaat</th>

						</tr>		
						<logic:iterate name="KopieerTaalvraagForm" property="taalvraag.citaten" id="citateni">
							<tr>
								<td><bean:write name="citateni" property="zoekomgeving.omschrijving" filter="true" /></td>
								<td><bean:write name="citateni" property="specificatie" filter="true" /></td>
								<td><bean:write name="citateni" property="url" filter="true" /></td>
								<td><bean:write name="citateni" property="variant" filter="true" /></td>
								<td><bean:write name="citateni" property="citaatHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>				
				</td>
			</tr>
		</logic:present>
		<logic:present name="KopieerTaalvraagForm" property="taalvraag.frequenties">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Frequentie" /></td>
				<td valign="top"><b>Frequentie  </b></td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Zoekomgeving</th>
							<th align="left">Specificatie</th>
							<th align="left">Variant</th>
							<th align="left">Aantal</th>
						</tr>		
						<logic:iterate name="KopieerTaalvraagForm" property="taalvraag.frequenties" id="frequentiesi">
							<tr>
								<td><bean:write name="frequentiesi" property="zoekomgeving.omschrijving" filter="true" /></td>
								<td><bean:write name="frequentiesi" property="specificatie" filter="true" /></td>
								<td><bean:write name="frequentiesi" property="variant" filter="true" /></td>
								<td><bean:write name="frequentiesi" property="aantal" filter="true" /></td>
							</tr>
						</logic:iterate>		
					</table>					
				</td>
			<tr>
		</logic:present>
		<logic:present name="KopieerTaalvraagForm" property="taalvraag.webreferenties">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Webreferentie" /></td>
				<td valign="top"><b>Koppeling  </b></td>
				<td valign="top">
					<table cellspacing="5" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Omgeving</th>
							<th align="left">URL</th>
							<th align="left">Toelichting</th>
						</tr>		
						<logic:iterate name="KopieerTaalvraagForm" property="taalvraag.webreferenties" id="webreferentiesi">
							<tr>
								<td><bean:write name="webreferentiesi" property="omgeving" filter="true" /></td>
								<td><bean:write name="webreferentiesi" property="url" filter="true" /></td>
								<td><bean:write name="webreferentiesi" property="toelichtingHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
		</logic:present>
		<logic:present name="KopieerTaalvraagForm" property="taalvraag.notities">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Notitie" /></td>
				<td valign="top"><b>Notitie  </b></td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<logic:iterate name="KopieerTaalvraagForm" property="taalvraag.notities" id="notitiesi">
							<tr>
								<td><b><bean:write name="notitiesi" property="gebruiker.voornaam" /> <bean:write name="notitiesi" property="gebruiker.naam" filter="true" /> op <bean:write name="notitiesi" property="datumString" />: </b></td>
							</tr>
							<tr>
								<td><bean:write name="notitiesi" property="notitieHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
		</logic:present>
		<logic:present name="KopieerTaalvraagForm" property="taalvraag.teksten">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTaalvraagForm" property="check_Teksten" /></td>
				<td valign="top"><b>Teksten als bijlage </b></td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<logic:iterate name="KopieerTaalvraagForm" property="taalvraag.teksten" id="teksteni">
							<tr>
								<td><bean:write name="teksteni" property="titel" filter="true" /></td>
							</tr>
						</logic:iterate>
					</table>
				</td>
			</tr>
		</logic:present>
		
	</table>
			
<p><html:image property="Kopieer" value="Kopieer" page="/images/button_kopieer.gif" /></p>
	
</html:form>
</body>
</html:html>