<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!--  
	Copyright 2001,	Ektron Inc.  Amherst NH  
	Latest Revision: 25-Aug-03
-->

<html>
<head>
	<title>Edit File</title>
	<script language="JavaScript1.2" src="..\..\..\ewebeditpro.js"></script>
</head>

<body>
<CFSET cACTIONPAGE = "editfile.cfm">
<CFSET strFilename = "">
<CFSET strContent = "">

<CFIF IsDefined("form.filename") AND Len(Trim(Form.Filename))>
	<CFSET strFilename = "#Form.Filename#">
<CFELSEIF IsDefined("URL.name") AND Len(Trim(URL.name))>
	<CFSET strFilename = "#URL.name#">
</CFIF>

<CFSET bSave = 0>
<CFIF IsDefined("URL.save")>
	<CFSET bSave = 1>
</CFIF>

<!--- 
 **********************************************************************
 FOR SECURITY REASONS, DO NOT ALLOW OPEN OR SAVE
 To enable Open and Save and permit files to be read from and written to
 your web server, remove the next five lines. --->
<CFSET strFilename = ""> <!--- ' PREVENT OPEN OF ANY FILE FROM THE SERVER FILE SYSTEM. Comment this line if you like to allow open of any file from the server file system. --->
<CFSET bSave = 0> <!--- ' PREVENT SAVE TO THE SERVER FILE SYSTEM. Comment this line if you like to allow save to the server file system. --->

<font size=4 color="red" face="Arial, Helvetica">For security reasons, the Open and Save features have been disabled. Access to the server's file system should only be allowed within a secured, password protected environment.</font>
<!--- 
********************************************************************** --->

<CFIf cgi.SERVER_PORT_SECURE EQ "1" >
	<CFSET strPath = "https://">
<CFElse>
	<CFSET strPath = "http://">
</CFIf>
<CFSET strPath = "#variables.strPath##cgi.SERVER_NAME#">
<CFIf cgi.SERVER_PORT neq "80">
	<CFSET strPath = "#variables.strPath#:#cgi.SERVER_PORT#">
</CFIf>
<CFSET strPath = "#variables.strPath##cgi.PATH_INFO#">
<CFSET strPath = Left("#variables.strPath#", "#Len(variables.strPath)#-#Len(variables.cACTIONPAGE)#")>

<CFIF Len(variables.strFilename) GT 0 >
	<cfif Left(variables.strFilename,1) EQ "/">
		<CFSET MapPath = ReplaceNoCase("#cgi.PATH_INFO#","/","\","ALL")>
		<CFSET MapPath = ReplaceNoCase("#cgi.PATH_TRANSLATED#","#variables.MapPath#","","ALL")>
	<cfelse>
		<CFSET MapPath = "#GetDirectoryFromPath(GetCurrentTemplatePath())#">
	</cfif>
	<CFSET strServerFilename = "#variables.MapPath##strFilename#">
<CFELSE>
	<CFSET strServerFilename = "">
</CFIF>

<CFSET strErrMsg = "">
<CFIF variables.bSave>
	<CFSET strContent = "#Form.Content#"> <!--- ' entire document --->
	<CFTRY> 	
		<cfif FileExists(variables.strServerFilename) is "Yes">
			<CFFILE ACTION="DELETE" File="#variables.strServerFilename#">
		</cfif> 
		<CFFILE action="WRITE" file="#variables.strServerFilename#" output="#variables.strContent#">
		<CFCATCH type="Application">
			<CFSET strErrMsg = "Unable to save file: #variables.strServerFilename#">
		</CFCATCH>
	</CFTRY> 
<CFELSEIF Len(strFilename) GT 0 >
	<cfif FileExists(variables.strServerFilename) is "Yes">
		<CFTRY>
			<CFFILE action="READ" file="#variables.strServerFilename#" variable="strContent">
			<CFCATCH type="Application">
				<CFSET strErrMsg = "Unable to open file: #variables.strServerFilename#">
			</CFCATCH>
		</CFTRY>
	<cfelse>
		<CFSET strErrMsg = "Unable to find file: #variables.strServerFilename#">
	</cfif> 
<CFELSE>
	<CFSET strContent = "">
</CFIF>

<CFSET EP = Len(variables.strFilename)>
<CFSET P = 0>
<CFLOOP condition="(variables.P EQ 0) AND (variables.EP GT 1)">
	<CFSET P = FindNoCase("/", "#variables.strFilename#", "#variables.EP#")>
	<CFSET EP = EP - 1>
</CFLOOP>
<CFIF P GT 0 >
	<CFSET strBaseURL = "#Left(variables.strFilename,variables.P)#">
<CFELSE>
	<CFSET strBaseURL = "#variables.strFilename#">
</CFIF>

<h1 style="{font-family: sans-serif; font-size: large}">Edit Static HTML File</h1>
<CFFORM name="frmMain" action="#variables.cACTIONPAGE#" method="post">
	<input type="submit" name="btnOpen" value="Open">&nbsp;&nbsp;&nbsp;
	<input type="submit" name="btnSave" value="Save" onclick='frmMain.action += "?save=1"'>
	<CFIf Len(strErrMsg) GT 0 >
		<br><br>
		<font face="Arial,Helvetica" color=red size=2><b><cfoutput>#variables.strErrMsg#</cfoutput></b></font>
	</CFIf>
	<br><br>
	<font face="Arial,Helvetica" size="3"><b>File name:</b></font>
	<cfoutput><input type="text" name="Filename" maxlength="1024" size="64" value="#variables.strFilename#"></cfoutput>
	<br><br>
	<font face="Arial,Helvetica" size="3"><b>Contents:</b></font>
	<br>
		
	<script language="JavaScript1.2">
	<!--
		eWebEditPro.parameters.editorGetMethod = "getDocument"; // Get the entire document, not just the body.
		eWebEditPro.parameters.baseURL = "<cfoutput>#variables.strBaseURL#</cfoutput>";
	//-->
	</script>
	
	
	<cfmodule template="../../../eWebEditPro4.cfm" EditorName="Content" Width="100%" Height="70%" Value="#variables.strContent#">
	
	<br><br>
</CFFORM>
<CFIF variables.bSave>
	<font face="Arial,Helvetica" size="2"><cfoutput><a href="#variables.strFilename#" target="_blank">View #variables.strFilename#</a></cfoutput></font>
</CFIF>
</body>
</html>
