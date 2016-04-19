<%@ page import="be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag" %>
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
		function doSubmit(doAction){
 		   	document.forms['DistributiekeuzeForm'].action = doAction;
 		   	document.forms['DistributiekeuzeForm'].submit();
		}
		
		function doSelect1() {
			if (document.forms['DistributiekeuzeForm'].elements['check_titel'].checked == true) {
				document.forms['DistributiekeuzeForm'].elements['check_titel'].checked = false;
				document.forms['DistributiekeuzeForm'].elements['check_vraag'].checked = false;
				document.forms['DistributiekeuzeForm'].elements['check_antwoord'].checked = false;
				document.forms['DistributiekeuzeForm'].elements['check_toelichting'].checked = false;
				document.forms['DistributiekeuzeForm'].elements['check_bijzonderheid'].checked = false;
			}
			else {
				document.forms['DistributiekeuzeForm'].elements['check_titel'].checked = true;
				document.forms['DistributiekeuzeForm'].elements['check_vraag'].checked = true;
				document.forms['DistributiekeuzeForm'].elements['check_antwoord'].checked = true;
				document.forms['DistributiekeuzeForm'].elements['check_toelichting'].checked = true;
				document.forms['DistributiekeuzeForm'].elements['check_bijzonderheid'].checked = true;
			}
		}
		function doSelect2() {
			if (document.forms['DistributiekeuzeForm'].elements['check_categorien'].checked == true) {
				document.forms['DistributiekeuzeForm'].elements['check_categorien'].checked = false;
				if (document.forms['DistributiekeuzeForm'].elements['check_naslagreferenties'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_naslagreferenties'].checked = false;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_bronnen'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_bronnen'].checked = false;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_citaten'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_citaten'].checked = false;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_frequenties'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_frequenties'].checked = false;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_webreferenties'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_webreferenties'].checked = false;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_notities'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_notities'].checked = false;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_teksten'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_teksten'].checked = false;
				}
			}
			else {
				document.forms['DistributiekeuzeForm'].elements['check_categorien'].checked = true;
				if (document.forms['DistributiekeuzeForm'].elements['check_naslagreferenties'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_naslagreferenties'].checked = true;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_bronnen'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_bronnen'].checked = true;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_citaten'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_citaten'].checked = true;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_frequenties'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_frequenties'].checked = true;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_webreferenties'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_webreferenties'].checked = true;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_notities'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_notities'].checked = true;
				}
				if (document.forms['DistributiekeuzeForm'].elements['check_teksten'] != null) {
					document.forms['DistributiekeuzeForm'].elements['check_teksten'].checked = true;
				}
			}
		}
	</script>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		

</head>

<bean:define name="DistributiekeuzeForm" property="taalvragen" type="java.util.ArrayList" id="taalvragenbean" />

<body>

<h2>Selectie vraagonderdelen</h2>

<html:form method="post" action="/distributiekeuze">
	<p>
		<logic:equal name="DistributiekeuzeForm" property="oproep.type" value="1">
			<a class="bigger" href="../do/initOproep_TaalvragenInvoer?id=<bean:write name="DistributiekeuzeForm" property="oproep.id" />">
				<bean:write name="DistributiekeuzeForm" property="oproep.id" /> 
			</a>							
			<bean:write name="DistributiekeuzeForm" property="oproep.voornaam" /> 
			<bean:write name="DistributiekeuzeForm" property="oproep.naam" />
		</logic:equal>							
		<logic:equal name="DistributiekeuzeForm" property="oproep.type" value="2">
			<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="DistributiekeuzeForm" property="oproep.id" />">
				<bean:write name="DistributiekeuzeForm" property="oproep.id" /> 
			</a>							
			<bean:write name="DistributiekeuzeForm" property="oproep.voornaam" /> 
			<bean:write name="DistributiekeuzeForm" property="oproep.naam" />
		</logic:equal>			
	</p>
	<input type="hidden" name="index" value="<%=request.getParameter("index")%>" />	
    <img src="<html:rewrite page="/images/button_selecteer_vraaggegevens.gif"/>" border="0" onclick="doSelect1()"  alt="Selecteer"/>
    <table cellspacing="2">
		<%
            Taalvraag taalvragen = (Taalvraag) taalvragenbean.get(Integer.parseInt(request.getParameter("index")));
            pageContext.setAttribute("taalvragen", taalvragen);
        %>
		<logic:notEqual name="taalvragen" property="titel" value="">
			<tr>
				<td valign="top"><html:checkbox name="taalvragen" property="check_titel" /></td>
				<td valign="top"><b>Titel  </b></td>
				<td><bean:write name="taalvragen" property="titel" filter="false" /></td>
			</tr>
		</logic:notEqual>
		<tr>
			<td valign="top"><html:checkbox name="taalvragen" property="check_vraag" /></td>
			<td colspan="2" valign="top"><b>Taalvraag  </b></td>
		</tr>
		<tr>
			<td colspan="3"><bean:write name="taalvragen" property="vraag" filter="false" /></td>
		</tr>
		<logic:notEqual name="taalvragen" property="antwoord" value="">
			<tr>
				<td valign="top"><html:checkbox name="taalvragen" property="check_antwoord" /></td>
				<td colspan="2" valign="top"><b>Antwoord  </b></td>
			</tr>
			<tr>
				<td colspan="3"><bean:write name="taalvragen" property="antwoordHTML" filter="false" /></td>
			</tr>
		</logic:notEqual>
		<logic:notEqual name="taalvragen" property="toelichting" value="">
			<tr>
				<td valign="top"><html:checkbox name="taalvragen" property="check_toelichting" /></td>
				<td colspan="2" valign="top"><b>Toelichting  </b></td>
			</tr>
			<tr>
				<td colspan="3"><bean:write name="taalvragen" property="toelichtingHTML" filter="false" /></td>
			</tr>
		</logic:notEqual>
		<logic:present name="taalvragen" property="bijzonderheid">
			<logic:notEqual name="taalvragen" property="bijzonderheid" value="">
				<tr>
					<td valign="top"><html:checkbox name="taalvragen" property="check_bijzonderheid" /></td>
					<td colspan="2" valign="top"><b>Bijzonderheid  </b></td>
				</tr>
				<tr>
					<td colspan="3"><bean:write name="taalvragen" property="bijzonderheidHTML" filter="false" /></td>
				</tr>
			</logic:notEqual>
		</logic:present>
	</table>	
	<hr />
	<img src="../images/button_selecteer_hulpmiddelen.gif" border="0" onclick="doSelect2()" />
	<table cellspacing="2">
		<tr>
			<td valign="top"><html:checkbox name="taalvragen" property="check_categorien" /></td>
			<td valign="top"><b>Categorie  </b></td>
			<td>
				<logic:present name="taalvragen" property="categorien">
					<logic:iterate property="categorien" name="taalvragen" id="categorieni">
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
		<logic:present name="taalvragen" property="naslagreferenties">
			<tr>
				<td valign="top"><html:checkbox name="taalvragen" property="check_naslagreferenties" /></td>
				<td valign="top"><b>Naslagwerk  </b></td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Verkorte titel</th>
							<th align="left">Pagina</th>
							<th align="left">Variant</th>
							<th align="left">Informatie</th>
						</tr>		
						<logic:iterate name="taalvragen" property="naslagreferenties" id="naslagreferentiesi">
							<tr>
								<td valign="top"><bean:write name="naslagreferentiesi" property="naslagwerk.omschrijving" filter="true" /></td>
								<td valign="top"><bean:write name="naslagreferentiesi" property="paginas" filter="true" /></td>
								<td valign="top"><bean:write name="naslagreferentiesi" property="variant" filter="true" /></td>
								<td valign="top"><bean:write name="naslagreferentiesi" property="citaatHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
		</logic:present>
		<logic:present name="taalvragen" property="bronnen">
			<tr>
				<td valign="top"><html:checkbox name="taalvragen" property="check_bronnen" /></td>
				<td valign="top"><b>Bron  </b></td>
				<td valign="top">	
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Titel</th>
							<th align="left">Pagina</th>
							<th align="left">Variant</th>
							<th align="left">Informatie</th>
						</tr>		
						<logic:iterate name="taalvragen" property="bronnen" id="bronneni">
							<tr>
								<td valign="top"><bean:write name="bronneni" property="titelHTML" filter="false" /></td>
								<td valign="top"><bean:write name="bronneni" property="paginas" filter="true" /></td>
								<td valign="top"><bean:write name="bronneni" property="variant" filter="true" /></td>
								<td valign="top"><bean:write name="bronneni" property="citaatHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
		</logic:present>
		<logic:present name="taalvragen" property="citaten">
			<tr>
				<td valign="top"><html:checkbox name="taalvragen" property="check_citaten" /></td>
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
						<logic:iterate name="taalvragen" property="citaten" id="citateni">
							<tr>
								<td valign="top"><bean:write name="citateni" property="zoekomgeving.omschrijving" filter="true" /></td>
								<td valign="top"><bean:write name="citateni" property="specificatie" filter="true" /></td>
								<td valign="top"><a href="<bean:write name="citateni" property="url" filter="true" />" target="blank">link</a></td>
								<td valign="top"><bean:write name="citateni" property="variant" filter="true" /></td>
								<td valign="top"><bean:write name="citateni" property="citaatHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>				
				</td>
			</tr>
		</logic:present>
		<logic:present name="taalvragen" property="frequenties">
			<tr>
				<td valign="top"><html:checkbox name="taalvragen" property="check_frequenties" /></td>
				<td valign="top"><b>Frequentie  </b></td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Zoekomgeving</th>
							<th align="left">Specificatie</th>
							<th align="left">Variant</th>
							<th align="left">Aantal</th>
						</tr>		
						<logic:iterate name="taalvragen" property="frequenties" id="frequentiesi">
							<tr>
								<td valign="top"><bean:write name="frequentiesi" property="zoekomgeving.omschrijving" filter="true" /></td>
								<td valign="top"><bean:write name="frequentiesi" property="specificatie" filter="true" /></td>
								<td valign="top"><bean:write name="frequentiesi" property="variant" filter="true" /></td>
								<td valign="top"><bean:write name="frequentiesi" property="aantal" filter="true" /></td>
							</tr>
						</logic:iterate>		
					</table>					
				</td>
			<tr>
		</logic:present>
		<logic:present name="taalvragen" property="webreferenties">
			<tr>
				<td valign="top"><html:checkbox name="taalvragen" property="check_webreferenties" /></td>
				<td valign="top"><b>Koppeling  </b></td>
				<td valign="top">
					<table cellspacing="5" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left">Omgeving</th>
							<th align="left">URL</th>
							<th align="left">Toelichting</th>
						</tr>		
						<logic:iterate name="taalvragen" property="webreferenties" id="webreferentiesi">
							<tr>
								<td valign="top"><bean:write name="webreferentiesi" property="omgeving" filter="true" /></td>
								<td valign="top"><a href="<bean:write name="webreferentiesi" property="url" filter="true" />" target="blank">link</a></td>									
								<td valign="top"><bean:write name="webreferentiesi" property="toelichtingHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
		</logic:present>
		<logic:present name="taalvragen" property="notities">
			<tr>
				<td valign="top"><html:checkbox name="taalvragen" property="check_notities" /></td>
				<td valign="top"><b>Notitie  </b></td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<logic:iterate name="taalvragen" property="notities" id="notitiesi">
							<tr>
								<td valign="top"><b><bean:write name="notitiesi" property="gebruiker.voornaam" /> <bean:write name="notitiesi" property="gebruiker.naam" filter="true" /> op <bean:write name="notitiesi" property="datumString" />: </b></td>
							</tr>
							<tr>
								<td valign="top"><bean:write name="notitiesi" property="notitieHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
		</logic:present>
		<logic:present name="taalvragen" property="teksten">
			<tr>
				<td valign="top"><html:checkbox name="taalvragen" property="check_teksten" /></td>
				<td valign="top"><b>Teksten als bijlage </b></td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<logic:iterate name="taalvragen" property="teksten" id="teksteni">
							<tr>
								<td><bean:write name="teksteni" property="titel" filter="true" /></td>
							</tr>
						</logic:iterate>
					</table>
				</td>
			</tr>
		</logic:present>
	</table>
			
<p><html:image property="Selectie bevestigen" value="Selectie bevestigen" page="/images/button_selectie_bevestigen.gif" /></p>
	
</html:form>
</body>
</html:html>