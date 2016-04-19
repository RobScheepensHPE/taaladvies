<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
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
			if (document.forms['KopieerTekstForm'].elements['check_Titel'].checked == true) {
				document.forms['KopieerTekstForm'].elements['check_Titel'].checked = false;
			}
			else {
				document.forms['KopieerTekstForm'].elements['check_Titel'].checked = true;
			}
		}
		function doSelect2() {
			if (document.forms['KopieerTekstForm'].elements['check_Categorie'].checked == true) {
				document.forms['KopieerTekstForm'].elements['check_Categorie'].checked = false;
				document.forms['KopieerTekstForm'].elements['check_Naslagreferentie'].checked = false;
				document.forms['KopieerTekstForm'].elements['check_Bron'].checked = false;
				document.forms['KopieerTekstForm'].elements['check_Citaat'].checked = false;
				document.forms['KopieerTekstForm'].elements['check_Frequentie'].checked = false;
				document.forms['KopieerTekstForm'].elements['check_Webreferentie'].checked = false;
				document.forms['KopieerTekstForm'].elements['check_Notitie'].checked = false;
			}
			else {
				document.forms['KopieerTekstForm'].elements['check_Categorie'].checked = true;
				document.forms['KopieerTekstForm'].elements['check_Naslagreferentie'].checked = true;
				document.forms['KopieerTekstForm'].elements['check_Bron'].checked = true;
				document.forms['KopieerTekstForm'].elements['check_Citaat'].checked = true;
				document.forms['KopieerTekstForm'].elements['check_Frequentie'].checked = true;
				document.forms['KopieerTekstForm'].elements['check_Webreferentie'].checked = true;
				document.forms['KopieerTekstForm'].elements['check_Notitie'].checked = true;
			}
		}
	</script>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		

</head>

<body>

<h2>Kopieer Tekst</h2>
<p>
	<a class="bigger" href="../overzicht/resultaatkopieerTXT.jsp">
		Zoekresultaten
	</a>
</p>
<html:form method="post" action="/kopieerTekst">
	<html:hidden name="KopieerTekstForm" property="tekst.id" />
	<table cellspacing="2">					
		<tr>
			<td valign="top"><html:checkbox name="KopieerTekstForm" property="check_Titel" /></td>
			<td valign="top"><b>Titel  </b></td>
			<td><bean:write name="KopieerTekstForm" property="tekst.titelHTML" filter="false" /></td>
		</tr>
		<logic:present name="KopieerTekstForm" property="tekstblokken">	
			
			
			<logic:iterate name="KopieerTekstForm" property="tekst.tekstblokken" id="tekstblok" type="be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok" >
				
				<tr bgcolor="#cccccc">
					<th colspan="3"><html:checkbox name="tekstblok" property="check_Titel"  indexed="true"/>Tekstblok <bean:write name="tekstblok"  property="titel" filter="true" /></th>
				</tr>
				<tr>
					<td colspan="3"><bean:write name="tekstblok"  property="tekstblokHTML" filter="false" /></td>
				</tr>
				<tr>
					<td colspan="3"><hr /></td>
				</tr>
			</logic:iterate>
		</logic:present>		
	<hr />
	<table cellspacing="2">
		<tr>
			<td valign="top"><html:checkbox name="KopieerTekstForm" property="check_Categorie" /></td>
			<td valign="top"><b>Categorie  </b></td>
			<td>
				<logic:present name="KopieerTekstForm" property="tekst.categorien">
					<logic:iterate property="tekst.categorien" name="KopieerTekstForm" id="categorieni">
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
		<logic:present name="KopieerTekstForm" property="tekst.naslagreferenties">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTekstForm" property="check_Naslagreferentie" /></td>
				<td valign="top"><b>Naslagwerk  </b></td>
				<td valign="top">				
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Verkorte titel</th>
							<th align="left">Pagina</th>
							<th align="left">Variant</th>
							<th align="left">Informatie</th>
						</tr>		
						<logic:iterate name="KopieerTekstForm" property="tekst.naslagreferenties" id="naslagreferentiesi">
							<tr>
								<td><bean:write name="naslagreferentiesi" property="naslagwerk.omschrijving" filter="true" /></td>
								<td><bean:write name="naslagreferentiesi" property="paginas" filter="true" /></td>
								<td><bean:write name="naslagreferentiesi" property="variant" filter="true" /></td>
								<td><bean:write name="naslagreferentiesi" property="citaatHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
		</logic:present>
		<logic:present name="KopieerTekstForm" property="tekst.bronnen">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTekstForm" property="check_Bron" /></td>
				<td valign="top"><b>Bron  </b></td>
				<td valign="top">	
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Titel</th>
							<th align="left">Pagina</th>
							<th align="left">Variant</th>
							<th align="left">Informatie</th>
						</tr>		
						<logic:iterate name="KopieerTekstForm" property="tekst.bronnen" id="bronneni">
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
		<logic:present name="KopieerTekstForm" property="tekst.citaten">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTekstForm" property="check_Citaat" /></td>
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
						<logic:iterate name="KopieerTekstForm" property="tekst.citaten" id="citateni">
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
		<logic:present name="KopieerTekstForm" property="tekst.frequenties">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTekstForm" property="check_Frequentie" /></td>
				<td valign="top"><b>Frequentie  </b></td>
				<td valign="top">				
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Zoekomgeving</th>
							<th align="left">Specificatie</th>
							<th align="left">Variant</th>
							<th align="left">Aantal</th>
						</tr>		
						<logic:iterate name="KopieerTekstForm" property="tekst.frequenties" id="frequentiesi">
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
		<logic:present name="KopieerTekstForm" property="tekst.webreferenties">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTekstForm" property="check_Webreferentie" /></td>
				<td valign="top"><b>Koppeling  </b></td>
				<td valign="top">
					<table cellspacing="5" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Omgeving</th>
							<th align="left">URL</th>
							<th align="left">Toelichting</th>
						</tr>		
						<logic:iterate name="KopieerTekstForm" property="tekst.webreferenties" id="webreferentiesi">
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
		<logic:present name="KopieerTekstForm" property="tekst.notities">
			<tr>
				<td valign="top"><html:checkbox name="KopieerTekstForm" property="check_Notitie" /></td>
				<td valign="top"><b>Notitie  </b></td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<logic:iterate name="KopieerTekstForm" property="tekst.notities" id="notitiesi">
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
	</table>
			
<p>
	<html:image property="Kopieer" value="Kopieer" page="/images/button_kopieer.gif" />
</p>
	
</html:form>
</body>
</html:html>