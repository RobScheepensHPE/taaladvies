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

	<bean:define name="DistributiekeuzeForm" property="tekst.tekstblokken" type="java.util.ArrayList" id="tekstblokkenbean" />	

	<script>
        function doSubmit(doAction) {
            document.forms['DistributiekeuzeForm'].action = doAction;
            document.forms['DistributiekeuzeForm'].submit();
        }

        function doSelect1() {
            if (document.forms['DistributiekeuzeForm'].elements['tekst.check_titel'].checked) {
                document.forms['DistributiekeuzeForm'].elements['tekst.check_titel'].checked = false;
            } else {
                document.forms['DistributiekeuzeForm'].elements['tekst.check_titel'].checked = true;
            }
        }
        function doSelect2() {
            if (document.forms['DistributiekeuzeForm'].elements['tekst.check_categorien'].checked) {
                document.forms['DistributiekeuzeForm'].elements['tekst.check_categorien'].checked = false;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_naslagreferenties'].checked = false;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_bronnen'].checked = false;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_citaten'].checked = false;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_frequenties'].checked = false;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_webreferenties'].checked = false;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_notities'].checked = false;
            }
            else {
                document.forms['DistributiekeuzeForm'].elements['tekst.check_categorien'].checked = true;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_naslagreferenties'].checked = true;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_bronnen'].checked = true;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_citaten'].checked = true;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_frequenties'].checked = true;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_webreferenties'].checked = true;
                document.forms['DistributiekeuzeForm'].elements['tekst.check_notities'].checked = true;
            }
        }
    </script>
    <%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>
</head>

<body>
<h2>Selectie tekstonderdelen</h2>

<html:form method="post" action="/distributiekeuze">
	<p>		
		<a href="../do/initOproep_TekstInvoer?id=<bean:write name="DistributiekeuzeForm" property="oproep.id" />">
			<bean:write name="DistributiekeuzeForm" property="oproep.id" /> 
		</a>							
		<bean:write name="DistributiekeuzeForm" property="oproep.voornaam" /> 
		<bean:write name="DistributiekeuzeForm" property="oproep.naam" />
	</p>	
    <img src="<html:rewrite page='/images/button_selecteer_tekstgegevens.gif'/>" border="0" onclick="doSelect1()"  alt="Selecteer tekstgegevens"/>    
    <table cellspacing="2">
		<tr>
			<td valign="top"><html:checkbox name="DistributiekeuzeForm" property="tekst.check_titel" /></td>
			<td valign="top"><b>Tekst  </b></td>
			<td><bean:write name="DistributiekeuzeForm" property="tekst.titel" filter="true" /></td>
		</tr>
	</table>	
	<hr />
    <img src="<html:rewrite page="/images/button_selecteer_hulpmiddelen.gif"/>" border="0" onclick="doSelect2()"  alt="Selecteer hulpmiddelen"/>
        <table cellspacing="2">
			<tr>
				<td valign="top"><html:checkbox name="DistributiekeuzeForm" property="tekst.check_categorien" /></td>
				<td valign="top"><b>Categorie</b></td>
				<td>
					<logic:present name="DistributiekeuzeForm" property="tekst.categorien">
						<logic:iterate property="tekst.categorien" name="DistributiekeuzeForm" id="categorieni">
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
			<logic:present name="DistributiekeuzeForm" property="tekst.naslagreferenties">
				<tr>
					<td valign="top"><html:checkbox name="DistributiekeuzeForm" property="tekst.check_naslagreferenties" /></td>
					<td valign="top"><b>Naslagwerk  </b></td>
					<td valign="top">
						<table cellpadding="2" width="100%" align="center">
							<tr bgcolor="#dddddd">
								<th align="left">Verkorte titel</th>
								<th align="left">Pagina</th>
								<th align="left">Variant</th>
								<th align="left">Informatie</th>
							</tr>		
							<logic:iterate name="DistributiekeuzeForm" property="tekst.naslagreferenties" id="naslagreferentiesi">
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
			<logic:present name="DistributiekeuzeForm" property="tekst.bronnen">
				<tr>
					<td valign="top"><html:checkbox name="DistributiekeuzeForm" property="tekst.check_bronnen" /></td>
					<td valign="top"><b>Bron  </b></td>
					<td valign="top">	
						<table cellpadding="2" width="100%" align="center">
							<tr bgcolor="#dddddd">
								<th align="left">Titel</th>
								<th align="left">Pagina</th>
								<th align="left">Variant</th>
								<th align="left">Informatie</th>
							</tr>		
							<logic:iterate name="DistributiekeuzeForm" property="tekst.bronnen" id="bronneni">
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
			<logic:present name="DistributiekeuzeForm" property="tekst.citaten">
				<tr>
					<td valign="top"><html:checkbox name="DistributiekeuzeForm" property="tekst.check_citaten" /></td>
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
							<logic:iterate name="DistributiekeuzeForm" property="tekst.citaten" id="citateni">
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
			<logic:present name="DistributiekeuzeForm" property="tekst.frequenties">
				<tr>
					<td valign="top"><html:checkbox name="DistributiekeuzeForm" property="tekst.check_frequenties" /></td>
					<td valign="top"><b>Frequentie  </b></td>
					<td valign="top">
						<table cellpadding="2" width="100%" align="center">
							<tr bgcolor="#dddddd">
								<th align="left">Zoekomgeving</th>
								<th align="left">Specificatie</th>
								<th align="left">Variant</th>
								<th align="left">Aantal</th>
							</tr>		
							<logic:iterate name="DistributiekeuzeForm" property="tekst.frequenties" id="frequentiesi">
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
			<logic:present name="DistributiekeuzeForm" property="tekst.webreferenties">
				<tr>
					<td valign="top"><html:checkbox name="DistributiekeuzeForm" property="tekst.check_webreferenties" /></td>
					<td valign="top"><b>Koppeling  </b></td>
					<td valign="top">
						<table cellspacing="5" width="100%" align="center">
							<tr bgcolor="#dddddd">
								<th align="left">Omgeving</th>
								<th align="left">URL</th>
								<th align="left">Toelichting</th>
							</tr>		
							<logic:iterate name="DistributiekeuzeForm" property="tekst.webreferenties" id="webreferentiesi">
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
			<logic:present name="DistributiekeuzeForm" property="tekst.notities">
				<tr>
					<td valign="top"><html:checkbox name="DistributiekeuzeForm" property="tekst.check_notities" /></td>
					<td valign="top"><b>Notitie  </b></td>
					<td valign="top">
						<table cellpadding="2" width="100%" align="center">
							<logic:iterate name="DistributiekeuzeForm" property="tekst.notities" id="notitiesi">
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
	</table>
			
<p><html:image property="Selectie bevestigen" value="Selectie bevestigen" page="/images/button_selectie_bevestigen.gif" /></p>
	
</html:form>
</body>
</html:html>