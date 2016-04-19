<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn />

<html:html locale="true">
<head>
	<title>Databank Taaladvies</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
	<link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript" />
	<script language="JavaScript1.2" src="<html:rewrite page='/style/taaladvies.js' />"></script>	
	<logic:equal name="eopro" value="true">
		<script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>
	
		<script>
			var appletLoaded = new Array(3);
			
			for(var i=0;i<appletLoaded.length;i++)
				appletLoaded[i] = false;
	
			function InhoudLoaded()
			{
				if (eWebEditPro.event.srcName == "oproep.distributie.sjabloon.inhoudHTML") {
					appletLoaded[0] = true;
				}
			}
			
			function SlotgroetLoaded()
			{
				if (eWebEditPro.event.srcName == "oproep.distributie.sjabloon.slotgroetHTML") {
					appletLoaded[1] = true;
				}
			}
	
			function HandtekeningLoaded()
			{
				if (eWebEditPro.event.srcName == "oproep.distributie.sjabloon.handtekeningHTML") {
					appletLoaded[2] = true;
				}
			}
			
			function AppletsLoaded()
			{
				for(var i=0;i<appletLoaded.length;i++)
				{
					if(!appletLoaded[i])
					{
						return false;
					}
				}
				
				return true;
			}
			function doSubmit(doAction){
				<logic:notEqual name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.id" value="0">
					if(fnOnSubmit()){
						document.forms['DistributiekeuzeForm'].action = doAction;
						document.forms['DistributiekeuzeForm'].submit();
					}
				</logic:notEqual>
				<logic:equal name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.id" value="0">
						document.forms['DistributiekeuzeForm'].action = doAction;
						document.forms['DistributiekeuzeForm'].submit();
				</logic:equal>
			}

		function fnOnSubmit() {
			if(AppletsLoaded()){
				eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE;
				document.DistributiekeuzeForm.elements["oproep.distributie.sjabloon.inhoud"].value = eWebEditPro.instances["oproep.distributie.sjabloon.inhoudHTML"].editor.getBodyText();
				document.DistributiekeuzeForm.elements["oproep.distributie.sjabloon.slotgroet"].value = eWebEditPro.instances["oproep.distributie.sjabloon.slotgroetHTML"].editor.getBodyText();
				document.DistributiekeuzeForm.elements["oproep.distributie.sjabloon.handtekening"].value = eWebEditPro.instances["oproep.distributie.sjabloon.handtekeningHTML"].editor.getBodyText();
				eWebEditPro.save();
				return true;
			} else {
				alert("<bean:message key='algemeen.opmaaknotready'/> ");
				return false;
			}
		}		
		
		</script>
	</logic:equal>
	
	<logic:notEqual name="eopro" value="true">
		<script>
			function doSubmit(doAction){
				document.forms['DistributiekeuzeForm'].action = doAction;
				document.forms['DistributiekeuzeForm'].submit();
			}
			
			function fnOnSubmit() {
					document.DistributiekeuzeForm.elements["oproep.distributie.sjabloon.inhoudHTML"].value = document.DistributiekeuzeForm.elements["oproep.distributie.sjabloon.inhoud"].value;
					document.DistributiekeuzeForm.elements["oproep.distributie.sjabloon.slotgroetHTML"].value = document.DistributiekeuzeForm.elements["oproep.distributie.sjabloon.slotgroet"].value;
					document.DistributiekeuzeForm.elements["oproep.distributie.sjabloon.handtekeningHTML"].value = document.DistributiekeuzeForm.elements["oproep.distributie.sjabloon.handtekening"].value;
			}			
		</script>
	</logic:notEqual>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		

</head>

<bean:define name="DistributiekeuzeForm" property="taalvragen" type="java.util.ArrayList" id="taalvragenbean" />
<bean:define name="DistributiekeuzeForm" property="sjablonen" type="java.util.ArrayList" id="sjablonen" />

<body>

<h2>Distributiekeuze</h2>

<html:form method="post" action="/distributiekeuze" enctype="multipart/form-data">

	<table cellspacing="5">
		<tr>
			<td>
				<logic:equal name="DistributiekeuzeForm" property="oproep.type" value="1">
					<logic:equal name="DistributiekeuzeForm" property="ro" value="false">
						<a href="../do/initOproep_TaalvragenInvoer?id=<bean:write name="DistributiekeuzeForm" property="oproep.id" />">
					</logic:equal>
					<logic:equal name="DistributiekeuzeForm" property="ro" value="true">
						<a href="../do/initOproep_TaalvragenInvoer?id=<bean:write name="DistributiekeuzeForm" property="oproep.id" />&ro=true">
					</logic:equal>
						Oproep <bean:write name="DistributiekeuzeForm" property="oproep.id" />
					</a>							
				</logic:equal>							
				<logic:equal name="DistributiekeuzeForm" property="oproep.type" value="2">
					<logic:equal name="DistributiekeuzeForm" property="ro" value="false">
						<a href="../do/initOproep_TekstInvoer?id=<bean:write name="DistributiekeuzeForm" property="oproep.id" />">
					</logic:equal>
					<logic:equal name="DistributiekeuzeForm" property="ro" value="true">
						<a href="../do/initOproep_TekstInvoer?id=<bean:write name="DistributiekeuzeForm" property="oproep.id" />&ro=true">
					</logic:equal>	
						Oproep <bean:write name="DistributiekeuzeForm" property="oproep.id" />
					</a>							
				</logic:equal>			
			</td>
		</tr>
	</table>                 
	
	<hr />

	<p>U kunt de volgende gegevens aanpassen, maar de aangepaste gegevens zullen niet in de database worden bewaard.</p>
	
	<table cellspacing="5">
		<tr>
			<td><html:img page="/images/label_voornaam.gif" /></td>
			<td><bean:write name="DistributiekeuzeForm" property="oproep.voornaam" /></td>
		</tr>
		<tr>
			<td><html:img page="/images/label_naam.gif" /></td>
			<td>
				<logic:equal name="DistributiekeuzeForm" property="oproep.naam" value="Onbekend">
					<input type="text" name="oproep.naam" size="40" />
				</logic:equal>
				<logic:notEqual name="DistributiekeuzeForm" property="oproep.naam" value="Onbekend">
					<html:text name="DistributiekeuzeForm" property="oproep.naam" size="40" />
				</logic:notEqual>
			</td>
		</tr>
		<logic:equal name="DistributiekeuzeForm" property="oproep.distributie.medium.omschrijving" value="E-mail">
			<tr>
				<td><html:img page="/images/label_email.gif" /></td>
				<td><html:text name="DistributiekeuzeForm" property="oproep.email" size="40" /></td>
			</tr>
		</logic:equal>
		<tr>
			<td><html:img page="/images/label_geslacht.gif" /></td>
			<td>
				<html:select name="DistributiekeuzeForm" property="oproep.geslacht">
					<option value="o" <logic:equal name="DistributiekeuzeForm" property="oproep.geslacht" value="o">SELECTED</logic:equal>>Onbekend</option>
					<option value="m" <logic:equal name="DistributiekeuzeForm" property="oproep.geslacht" value="m">SELECTED</logic:equal>>Man</option>
					<option value="v" <logic:equal name="DistributiekeuzeForm" property="oproep.geslacht" value="v">SELECTED</logic:equal>>Vrouw</option>
				</html:select>
			</td>
		</tr>
		<logic:equal name="DistributiekeuzeForm" property="oproep.distributie.medium.omschrijving" value="E-mail">	
			<tr>
				<td><html:img page="/images/label_cc.gif" /></td>
				<td><html:text name="DistributiekeuzeForm" property="oproep.distributie.cc" size="40" /></td>
			</tr>
			<tr>
				<td><html:img page="/images/label_bcc.gif" /></td>
				<td><html:text name="DistributiekeuzeForm" property="oproep.distributie.bcc" size="40" /></td>
			</tr>
			<tr>
				<td><html:img page="/images/label_bestand_bijvoegen.gif" /></td>
				<td>
					<html:file property="theFile" />
					<logic:present name="DistributiekeuzeForm" property="fileName">
						<logic:notEqual name="DistributiekeuzeForm" property="fileName" value="">
						&nbsp;(Bestand momenteel opgeladen: <bean:write name="DistributiekeuzeForm" property="fileName" />)
						</logic:notEqual>
					</logic:present>
				</td>
			</tr>		

		<tr><td colspan="2"><hr /></td></tr>
		<logic:notEqual name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.id" value="0">	
			<tr>
				<td colspan="2">
					<html:image property="Onthoud voorlopige gegevens" value="Onthoud voorlopige gegevens" page="/images/button_onthoud_voorlopige_gegevens.gif" onclick="return fnOnSubmit()" />
					<html:image property="Bekijk" value="Bekijk" page="/images/button_bekijk_print.gif" />						
					<logic:equal name="DistributiekeuzeForm" property="oproep.distributie.medium.omschrijving" value="E-mail">
						<html:image property="E-mail" value="E-mail" page="/images/button_email.gif" />
					</logic:equal>			
				</td>
			</tr>
		</logic:notEqual>	
		<tr>
			<td valign="top"><html:img page="/images/label_kies_sjabloon.gif" /></td>
			<td>
				<html:select name="DistributiekeuzeForm" property="oproep.distributie.sjabloonId" onchange="doSubmit('../do/distributiekeuze?Button=KiesSjabloon')">
					<option value="0">Selecteer...</option>
					<html:options collection="sjablonen" property="id" labelProperty="omschrijving" />
				</html:select>
			</td>
		</tr>
		<logic:notEqual name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.id" value="0">				

			<html:hidden name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.inhoudHTML" />
			<html:hidden name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.slotgroetHTML" />
			<html:hidden name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.handtekeningHTML" />
			<logic:equal name="eopro" value="true">
				<html:hidden name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.inhoud" />
				<html:hidden name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.slotgroet" />
				<html:hidden name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.handtekening" />
			</logic:equal>

			<tr>
				<td><html:img page="/images/label_onderwerp.gif" /></td>
				<td><html:text name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.onderwerp" size="40" /></td>
			</tr>
			<tr>
				<td><html:img page="/images/label_aanspreking.gif" /></td>
				<td><html:text name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.aanspreking" size="40" /> (optioneel)</td>
			</tr>			
			<tr><td><html:img page="/images/label_inhoud.gif" /></td>	
				<td>
					<logic:equal name="eopro" value="true">			
						<c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
						<script language="JavaScript1.2">		
							eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
							eWebEditPro.create("oproep.distributie.sjabloon.inhoudHTML", "700", "200");
							eWebEditPro.addEventHandler("onready",  InhoudLoaded);
						</script> 		
					</logic:equal>
					
					<logic:notEqual name="eopro" value="true">
						<html:textarea name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.inhoud" rows="7" style="width: 100%;"/>
					</logic:notEqual>


				</td>
			</tr>
			<tr><td><html:img page="/images/label_slotgroet.gif" /></td>  
				<td>
					<logic:equal name="eopro" value="true">
						<c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
						<script language="JavaScript1.2">		
							eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
							eWebEditPro.create("oproep.distributie.sjabloon.slotgroetHTML", "700", "150");
							eWebEditPro.addEventHandler("onready",  SlotgroetLoaded);
						</script> 	
					</logic:equal>
					
					<logic:notEqual name="eopro" value="true">
						<html:textarea name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.slotgroet" rows="7" style="width: 100%;"/>
					</logic:notEqual>

				</td>
			</tr>
			<tr><td><html:img page="/images/label_handtekening.gif" /></td>
				<td>
					<logic:equal name="eopro" value="true">
						<c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
						<script language="JavaScript1.2">		
							eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
							eWebEditPro.create("oproep.distributie.sjabloon.handtekeningHTML", "700", "150");
							eWebEditPro.addEventHandler("onready",  HandtekeningLoaded);
						</script> 
					</logic:equal>
					
					<logic:notEqual name="eopro" value="true">
						<html:textarea name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.handtekening" rows="7" style="width: 100%;"/>
					</logic:notEqual>

				</td>
			</tr>

			</logic:notEqual>
		</logic:equal>	
		</table>
		
		<logic:equal name="DistributiekeuzeForm" property="oproep.distributie.medium.omschrijving" value="E-mail">
			<logic:equal name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.id" value="0">	
				<html:image property="Onthoud voorlopige gegevens" value="Onthoud voorlopige gegevens" page="/images/button_onthoud_voorlopige_gegevens.gif" onclick="return fnOnSubmit()" />
				<a href="distributiebekijk.jsp">
					<img src="<html:rewrite page='/images/button_bekijk_print.gif' />" border="0"  alt="Bekijk/Print"/>
				</a>
			</logic:equal>
		</logic:equal>
		<logic:notEqual name="DistributiekeuzeForm" property="oproep.distributie.sjabloon.id" value="0">
			<html:image property="Onthoud voorlopige gegevens" value="Onthoud voorlopige gegevens" page="/images/button_onthoud_voorlopige_gegevens.gif" onclick="return fnOnSubmit()" />
		</logic:notEqual>
		<logic:notEqual name="DistributiekeuzeForm" property="oproep.distributie.medium.omschrijving" value="E-mail">
			<a href="distributiebekijk.jsp">
				<img src="<html:rewrite page='/images/button_bekijk_print.gif' />" border="0"  alt="Bekijk/Print"/>
			</a>
			<html:image property="Onthoud voorlopige gegevens" value="Onthoud voorlopige gegevens" page="/images/button_onthoud_voorlopige_gegevens.gif" />
		</logic:notEqual>	
	<hr />

	<logic:equal name="DistributiekeuzeForm" property="oproep.type" value="1">
		<table cellspacing="5">					
			<tr bgcolor="#dddddd">
				<th align="left">Taalvraag</th>
				<th align="left">Categorie</th>
			</tr>
			<logic:iterate id="taalvragen" name="taalvragenbean" indexId="index">
				<tr>
					<td>
						<a href="distributievraag.jsp?index=<bean:write name="index" />">
							<logic:notEqual name="taalvragen" property="titel" value="">
								<logic:notEqual name="taalvragen" property="titel" value="<p></p>">
									<bean:write name="taalvragen" property="titel" filter="true" />
								</logic:notEqual>
							</logic:notEqual>
							<logic:equal name="taalvragen" property="titel" value="">
								<bean:write name="taalvragen" property="vraag" filter="true" />
							</logic:equal>
							<logic:equal name="taalvragen" property="titel" value="<p></p>">
								<bean:write name="taalvragen" property="vraag" filter="true" />
							</logic:equal>
						</a>
					</td>
					<td>
						<logic:present name="taalvragen" property="categorien">
							<logic:iterate id="i" name="taalvragen" property="categorien">
								<logic:notEqual name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
									<bean:write name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> --> 
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
								<br>							
							</logic:iterate>
						</logic:present>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</logic:equal>	
	<logic:equal name="DistributiekeuzeForm" property="oproep.type" value="2">
		<table cellspacing="5">
			<tr>
				<th>Tekst</th>
			</tr>
				<td>
					<a href="distributietekst.jsp">
						<bean:write name="DistributiekeuzeForm" property="tekst.titel" filter="true" />
					</a>
				</td>
			<tr>
		</table>
	</logic:equal>
</html:form>
</body>
</html:html>