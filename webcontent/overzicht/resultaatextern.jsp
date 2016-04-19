<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/iflogic.tld" prefix="iflogic" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn />

<html:html locale="true">
<head>
<title>Databank Taaladvies</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<html:rewrite page='/style/taaladvies.css' />"    rel="stylesheet" type="text/css">
<link href="<html:rewrite page='/style/taaladvies.js' />"    type="text/JavaScript"/>
</head>

<body>

<table width="100%">
	<tr>
		<td>
			<h2>Zoekresultaten</h2>
		</td>
		<td align="right">
			<a href="../profielen.jsp">
				<html:img page="/images/button_uitloggen.gif" border="0" />
			</a>
		</td>
	</tr>
</table>

<html:errors/>

Op basis van uw trefwoorden en criteria werden de volgende gegevens gevonden :

<logic:present name="cacheTLV" scope="session">

	<iflogic:if name="cacheTLV" property="maxCount" op="equal" value="0">
	<iflogic:then>						
		<table>
			<th>Er werden geen taalvragen gevonden die aan de zoekcriteria voldoen</th>
		</table>
	</iflogic:then>
	<iflogic:else>
	
		<bean:define name="cacheTLV" property="records" id="taalvragen" />	
	
		<table cellspacing="5" width="100%" align="center">
			<tr>
				<th colspan="3">
					<b>Taalvragen (Pagina <bean:write name="cacheTLV" property="currentCount"/>/<bean:write name="cacheTLV" property="pageCount"/>)</b>
				</th>
			</tr>
			<tr bgcolor="#dddddd">
				<!-- <th align="left">ID</th> -->
				<th align="left" width="40%">Taalvraag</th>
				<th align="left" width="20%">Oproepdatum</th>
				<th align="left" width="40%">Categorie&euml;n</th>
			</tr>			
			<logic:iterate name="taalvragen" id="currentItem" type="be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag">
				<tr>
					<!-- <td><bean:write name="currentItem" property="id" filter="false"/></td> -->
					<td valign="top">
						<a href="../do/raadplegerRead?id=<bean:write name="currentItem" property="id" />&type=1">
							<bean:write name="currentItem" property="titel" filter="false"/>
						</a>
					</td>
					<td valign="top"><bean:write name="currentItem" property="oproep.oproepdatumString" filter="false"/></td>				
					<td valign="top">
						<logic:present name="currentItem" property="categorien">
							<ul>
								<logic:iterate id="i" name="currentItem" property="categorien">
									<li>
										<logic:notEqual name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
											<bean:write name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
											<bean:write name="i" property="omschrijving" /> 
										</logic:notEqual>
										<logic:equal name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
											<logic:notEqual name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
												<bean:write name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
												<bean:write name="i" property="omschrijving" />
											</logic:notEqual>
											<logic:equal name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
												<logic:notEqual name="i" property="superCategorie.superCategorie.superCategorie.id" value="0">
													<bean:write name="i" property="superCategorie.superCategorie.superCategorie.omschrijving" /> > 
													<bean:write name="i" property="omschrijving" />
												</logic:notEqual>
												<logic:equal name="i" property="superCategorie.superCategorie.superCategorie.id" value="0">
													<logic:notEqual name="i" property="superCategorie.superCategorie.id" value="0">
														<bean:write name="i" property="superCategorie.superCategorie.omschrijving" /> >
														<bean:write name="i" property="omschrijving" />
													</logic:notEqual>							
													<logic:equal name="i" property="superCategorie.superCategorie.id" value="0">
														<logic:notEqual name="i" property="superCategorie.id" value="0">
															<bean:write name="i" property="superCategorie.omschrijving" /> > 
															<bean:write name="i" property="omschrijving" />
														</logic:notEqual>
														<logic:equal name="i" property="superCategorie.id" value="0">
															<logic:notEqual name="i" property="id" value="0">
																<bean:write name="i" property="omschrijving" />
															</logic:notEqual>
														</logic:equal>
													</logic:equal>
												</logic:equal>
											</logic:equal>
										</logic:equal>
									</li>								
								</logic:iterate>
							</ul>
						</logic:present>
					</td>					
				</tr>
			</logic:iterate>
			<tr><td colspan="3"><hr /></td></tr>	
			<tr><td colspan="3">
			
			<iflogic:if name="cacheTLV" property="currentCount" op="notequal" value="1">
				<iflogic:then>
					<html:link href="../do/zoekenVervolgExtern?method=previousTLV"><html:img page="/images/button_vorige.gif" border="0" /></html:link> | 
				</iflogic:then>
			</iflogic:if>           												
			 
			<iflogic:if name="cacheTLV" property="lastPage" op="notequal" value="true">
					<iflogic:then>			
						<html:link href="../do/zoekenVervolgExtern?method=nextTLV"><html:img page="/images/button_volgende.gif" border="0" /></html:link></td></tr>
					</iflogic:then>
			</iflogic:if>		
						 
		</table>	
	</iflogic:else>
	</iflogic:if>           									
	<br>
</logic:present>

<logic:present name="cacheTXT" scope="session">		
	<iflogic:if name="cacheTXT" property="maxCount" op="equal" value="0">
	<iflogic:then>						
		<table>
			<th>Er werden geen teksten gevonden die aan de zoekcriteria voldoen</th>
		</table>
	</iflogic:then>
	<iflogic:else>
	
		<bean:define name="cacheTXT" property="records" id="teksten" />	
				
		<table cellspacing="5" width="100%" align="center">
			<tr>
				<th colspan="3"><b>Teksten (Pagina <bean:write name="cacheTXT" property="currentCount"/>/<bean:write name="cacheTXT" property="pageCount"/>)</b></th>
			</tr>
			<tr bgcolor="#dddddd">
				<th align="left" width="40%">Tekst</th>
				<th align="left" width="20%">Oproepdatum</th>
				<th align="left" width="40%">Categorie&euml;n</th>
			</tr>				
			<logic:iterate name="teksten" id="currentItem" type="be.vlaanderen.sbs.s6.taaladvies.model.Tekst">
				<tr>
					<td valign="top">
						<a href="../do/raadplegerRead?id=<bean:write name="currentItem" property="id" />&type=2">
							<bean:write name="currentItem" property="titel" filter="false"/>
						</a>
					</td>
					<td valign="top"><bean:write name="currentItem" property="oproep.oproepdatumString" /></td>
					<td valign="top">
						<logic:present name="currentItem" property="categorien">
							<ul>
								<logic:iterate id="i" name="currentItem" property="categorien">
									<li>
										<logic:notEqual name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
											<bean:write name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
											<bean:write name="i" property="omschrijving" /> 
										</logic:notEqual>
										<logic:equal name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
											<logic:notEqual name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
												<bean:write name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
												<bean:write name="i" property="omschrijving" />
											</logic:notEqual>
											<logic:equal name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
												<logic:notEqual name="i" property="superCategorie.superCategorie.superCategorie.id" value="0">
													<bean:write name="i" property="superCategorie.superCategorie.superCategorie.omschrijving" /> > 
													<bean:write name="i" property="omschrijving" />
												</logic:notEqual>
												<logic:equal name="i" property="superCategorie.superCategorie.superCategorie.id" value="0">
													<logic:notEqual name="i" property="superCategorie.superCategorie.id" value="0">
														<bean:write name="i" property="superCategorie.superCategorie.omschrijving" /> >
														<bean:write name="i" property="omschrijving" />
													</logic:notEqual>							
													<logic:equal name="i" property="superCategorie.superCategorie.id" value="0">
														<logic:notEqual name="i" property="superCategorie.id" value="0">
															<bean:write name="i" property="superCategorie.omschrijving" /> > 
															<bean:write name="i" property="omschrijving" />
														</logic:notEqual>
														<logic:equal name="i" property="superCategorie.id" value="0">
															<logic:notEqual name="i" property="id" value="0">
																<bean:write name="i" property="omschrijving" />
															</logic:notEqual>
														</logic:equal>
													</logic:equal>
												</logic:equal>
											</logic:equal>
										</logic:equal>
									</li>								
								</logic:iterate>
							</ul>
						</logic:present>					
					</td>
				</tr>
			</logic:iterate>
			<tr><td colspan="3"><hr /></td></tr>	
			<tr><td colspan="3">
			
				<iflogic:if name="cacheTXT" property="currentCount" op="notequal" value="1">
					<iflogic:then>
						<html:link href="../do/zoekenVervolgExtern?method=previousTXT"><html:img page="/images/button_vorige.gif" border="0" /></html:link> | 
					</iflogic:then>
				</iflogic:if>           												
			 	<iflogic:if name="cacheTXT" property="lastPage" op="notequal" value="true">
			 		<iflogic:then>
			 			<html:link href="../do/zoekenVervolgExtern?method=nextTXT"><html:img page="/images/button_volgende.gif" border="0" /></html:link>
					</iflogic:then>
				</iflogic:if>
			</td></tr>
		</table>	
	</iflogic:else>
	</iflogic:if>           									
	<br>
</logic:present>

<p>
	<html:link page="/do/initZoekenExtern"><html:img page="/images/button_nieuwe_zoekopdracht.gif" border="0" /></html:link>
	<html:link page="/overzicht/zoekenextern.jsp"><html:img page="/images/button_verfijn_zoekopdracht.gif" border="0" /></html:link>
</p>
</body>
</html:html>















