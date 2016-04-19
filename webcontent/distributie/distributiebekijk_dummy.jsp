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
		function printWindow() {
			window.print();
			window.location.href("distributiebekijk.jsp");
		}
	</script>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		

</head>

<bean:define name="DistributiekeuzeForm" property="taalvragen" type="java.util.ArrayList" id="taalvragenbean" />

<body onload="javascript:printWindow()">

<html:form method="post" action="/distributiekeuze">
	<table width="600" align="center" cellpadding="0" cellspacing="0"><tr><td>
	<hr />
	<logic:equal name="DistributiekeuzeForm" property="oproep.type" value="1">
		<bean:size name="taalvragenbean" id="size_taalvragenbean" />
		<logic:greaterThan name="size_taalvragenbean" value="1">
		<p>Overzicht van de taalvragen</p>
		<ul>
			<logic:iterate id="taalvragen" name="taalvragenbean" indexId="i">	
				<logic:equal name="taalvragen" property="check_titel" value="true">	
					<li>
						<a href="#titel<bean:write name="i" />">
							<logic:notEqual name="taalvragen" property="titel" value="">
								<bean:write name="taalvragen" property="titel" filter="true" />
							</logic:notEqual>
							<logic:equal name="taalvragen" property="titel" value="">
								<bean:write name="taalvragen" property="vraag" filter="true" />
							</logic:equal>
						</a>
					</li>				
				</logic:equal>
			</logic:iterate>
		</ul>		
		<hr />
		</logic:greaterThan>
		<logic:iterate id="taalvragen" name="taalvragenbean" indexId="i">	
		
			<a name="titel<bean:write name="i" />">
			<table width="100%">
				<logic:equal name="taalvragen" property="check_titel" value="true">
				<logic:present name="taalvragen" property="titel">
				<logic:notEqual name="taalvragen" property="titel" value="">
				<logic:notEqual name="taalvragen" property="titel" value="<p></p>">	
					<tr>
						<td valign="top" width="5%"><b>Titel  </b></td>
						<td>		
							</a>
							<bean:write name="taalvragen" property="titel" filter="false" />
						</td>
					</tr>
				</logic:notEqual>
				</logic:notEqual>
				</logic:present>
				</logic:equal>
				<logic:equal name="taalvragen" property="check_vraag" value="true">
				<logic:present name="taalvragen" property="vraag">
				<logic:notEqual name="taalvragen" property="vraag" value="">
				<logic:notEqual name="taalvragen" property="vraag" value="<p></p>">	
					<tr>
						<td valign="top" width="5%"><b>Vraag  </b></td>
						<td>		
							<bean:write name="taalvragen" property="vraag" filter="false" />
						</td>
					</tr>
				</logic:notEqual>
				</logic:notEqual>
				</logic:present>
				</logic:equal>
				<logic:equal name="taalvragen" property="check_antwoord" value="true">
				<logic:present name="taalvragen" property="antwoordHTML">
				<logic:notEqual name="taalvragen" property="antwoordHTML" value="">
				<logic:notEqual name="taalvragen" property="antwoordHTML" value="<p></p>"> 	
					<tr>
						<td valign="top"><b>Antwoord  </b></td>
						<td>		
							<bean:write name="taalvragen" property="antwoordHTML" filter="false" />
						</td>
					</tr>
				</logic:notEqual>
				</logic:notEqual>
				</logic:present>					
				</logic:equal>
				<logic:equal name="taalvragen" property="check_toelichting" value="true">
				<logic:present name="taalvragen" property="toelichtingHTML">
				<logic:notEqual name="taalvragen" property="toelichtingHTML" value="">
				<logic:notEqual name="taalvragen" property="toelichtingHTML" value="<p></p>">
					<tr>
						<td valign="top"><b>Toelichting  </b></td>
						<td>			
							<bean:write name="taalvragen" property="toelichtingHTML" filter="false" />	
						</td>
					</tr>
				</logic:notEqual>
				</logic:notEqual>
				</logic:present>				
				</logic:equal>
				<logic:equal name="taalvragen" property="check_bijzonderheid" value="true">
				<logic:present name="taalvragen" property="bijzonderheidHTML">
				<logic:notEqual name="taalvragen" property="bijzonderheidHTML" value="">
				<logic:notEqual name="taalvragen" property="bijzonderheidHTML" value="<p></p>">	
					<tr>
						<td valign="top"><b>Bijzonderheid  </b></td>
						<td>	
							<bean:write name="taalvragen" property="bijzonderheidHTML" filter="false" />
						</td>
					</tr>
				</logic:notEqual>
				</logic:notEqual>
				</logic:present>
				</logic:equal>
				<logic:equal name="taalvragen" property="check_categorien" value="true">	
					<tr>
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
				</logic:equal>	
				<logic:equal name="taalvragen" property="check_naslagreferenties" value="true">	
					<tr>
						<td valign="top"><b>Naslagwerk  </b></td>
						<td>
							<logic:present name="taalvragen" property="naslagreferenties">
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
							</logic:present>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="taalvragen" property="check_bronnen" value="true">	
					<tr>
						<td valign="top"><b>Bron  </b></td>
						<td>
							<logic:present name="taalvragen" property="bronnen">
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
							</logic:present>		
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="taalvragen" property="check_citaten" value="true">	
					<tr>
						<td valign="top"><b>Citaat  </b></td>
						<td>
							<logic:present name="taalvragen" property="citaten">
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
							</logic:present>		
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="taalvragen" property="check_frequenties" value="true">	
					<tr>
						<td valign="top"><b>Frequentie  </b></td>
						<td>
							<logic:present name="taalvragen" property="frequenties">
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
							</logic:present>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="taalvragen" property="check_webreferenties" value="true">	
					<tr>
						<td valign="top"><b>Koppeling  </b></td>
						<td>
							<logic:present name="taalvragen" property="webreferenties">
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
							</logic:present>
						</td>
					<tr>
				</logic:equal>
				<logic:equal name="taalvragen" property="check_notities" value="true">	
					<tr>
						<td valign="top"><b>Notitie  </b></td>
						<td>
							<logic:present name="taalvragen" property="notities">
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
							</logic:present>
						</td>
					</tr>
				</logic:equal>
				<tr><td colspan="2"><hr /></td></tr>
				<logic:equal name="taalvragen" property="check_teksten" value="true">
					<logic:present name="taalvragen" property="teksten">
						<tr>
							<td colspan="2">
								<logic:iterate name="taalvragen" property="teksten" id="teksteni">									
									<bean:write name="teksteni" property="titelHTML" filter="false" />
									<table>
										<tr>
											<td>&nbsp;</td>
											<td><hr /></td>
										</tr>
										<logic:present name="teksteni" property="tekstblokken">
											<logic:iterate name="teksteni" property="tekstblokken" id="tekstblokkeni">
												<tr>
													<td valign="top"><bean:write name="tekstblokkeni" property="titelHTML" filter="false" /></td>
													<td><bean:write name="tekstblokkeni" property="tekstblokHTML" filter="false" /><hr /></td>
												</tr>
											</logic:iterate>
										</logic:present>
									</table>
									<hr />
								</logic:iterate>
							</td>
						</tr>
					</logic:present>
				</logic:equal>
			</table>
		</logic:iterate>
	</logic:equal>
	<logic:equal name="DistributiekeuzeForm" property="oproep.type" value="2">
	<bean:define name="DistributiekeuzeForm" property="tekst.tekstblokken" id="tekstblokkenbean" />	
		
		

		<p align="center">
			<bean:write name="DistributiekeuzeForm" property="tekst.titelHTML" filter="false" />
		</p>
		<hr />
		<table align="center">
			<logic:present name="DistributiekeuzeForm" property="tekst.tekstblokken">
				<logic:iterate name="DistributiekeuzeForm" property="tekst.tekstblokken" id="tekstblokkeni">
					<tr>
						<td valign="top"><bean:write name="tekstblokkeni" property="titelHTML" filter="false" /></td>
						<td><bean:write name="tekstblokkeni" property="tekstblokHTML" filter="false" /><hr /></td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
		<table>	
			<logic:equal name="DistributiekeuzeForm" property="tekst.check_categorien" value="true">	
				<tr>
					<td valign="top"><b>Categorie  </b></td>
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
			</logic:equal>	
			<logic:equal name="DistributiekeuzeForm" property="tekst.check_naslagreferenties" value="true">	
				<tr>
					<td valign="top"><b>Naslagwerk  </b></td>
					<td>
						<logic:present name="DistributiekeuzeForm" property="tekst.naslagreferenties">
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
						</logic:present>
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="DistributiekeuzeForm" property="tekst.check_bronnen" value="true">	
				<tr>
					<td valign="top"><b>Bron  </b></td>
					<td>
						<logic:present name="DistributiekeuzeForm" property="tekst.bronnen">
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
						</logic:present>		
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="DistributiekeuzeForm" property="tekst.check_citaten" value="true">	
				<tr>
					<td valign="top"><b>Citaat  </b></td>
					<td>
						<logic:present name="DistributiekeuzeForm" property="tekst.citaten">
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
						</logic:present>		
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="DistributiekeuzeForm" property="tekst.check_frequenties" value="true">	
				<tr>
					<td valign="top"><b>Frequentie  </b></td>
					<td>
						<logic:present name="DistributiekeuzeForm" property="tekst.frequenties">
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
						</logic:present>
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="DistributiekeuzeForm" property="tekst.check_webreferenties" value="true">	
				<tr>
					<td valign="top"><b>Koppeling  </b></td>
					<td>
						<logic:present name="DistributiekeuzeForm" property="tekst.webreferenties">
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
						</logic:present>
					</td>
				<tr>
			</logic:equal>
			<logic:equal name="DistributiekeuzeForm" property="tekst.check_notities" value="true">	
				<tr>
					<td valign="top"><b>Notitie  </b></td>
					<td>
						<logic:present name="DistributiekeuzeForm" property="tekst.notities">
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
						</logic:present>
					</td>
				</tr>
			</logic:equal>		
		</table>
		
			
	</logic:equal>
	</td></tr></table>
</html:form>
</body>
</html:html>