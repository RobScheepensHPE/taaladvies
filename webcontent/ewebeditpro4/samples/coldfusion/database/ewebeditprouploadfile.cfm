<!---   Ektron Inc.  Amherst NH  Copyright (©) 1999 --->
<!--- 
  Name: uploadfile.cfm
   
  Descr:  Shows the content as HTML 
  
  Author, date: William Rogers, 11/9/99
  
  Notes: 
  
--->
<!---
	* 	Return: uploadedfilename
			causes a file uploaded to the directory to always have a unique name

 --->

<cfparam name="Attributes.AllowExts">
<cfparam name="Attributes.DestDir">
<cfparam name="Attributes.UploadFile">
<cfparam name="Attributes.RenameToFilePrefix" default="">
<cfparam name="Attributes.RenameFile" default="Yes">
<cfparam name="Attributes.JavascriptErrors" default="No">
<cfparam name="Attributes.ShowDebug" default="false">
<cfparam name="Attributes.NameConflict" default="ERROR">
<cffile action="UPLOAD" filefield="#Attributes.UploadFile#" destination="#Attributes.DestDir#" nameconflict="#Attributes.NameConflict#">
<cfif UCase(Attributes.AllowExts) neq "ALL" AND UCase(Attributes.AllowExts) neq "*" AND NOT ListFindNoCase(Attributes.AllowExts, File.ClientFileExt)>
	<cfset variable.uploadedfile = Attributes.DestDir & File.ServerFile>
	<cffile action="DELETE" file="#variable.uploadedfile#">
	<cfset caller.errorlevel = 1>
	<cfset caller.errormsg = "Invalid File Type">
	<cfif Attributes.JavascriptErrors>
		<cfoutput>
		<script language="JavaScript">
			alert ("Error: #file.clientfileext# is an invalid file type for this application.  You may upload only #Attributes.UploadFileExt# files");
		</script>
		</cfoutput>
	</cfif>
<cfelse>
	<cfset variable.thunbnailfile = file.ServerFile>
	<cfset caller.fileSize=file.fileSize>
	<cfset caller.original_name=file.attemptedserverfile><!---this is needed for auto image upload.  CF will rename the file if a duplicate file exists.  When this occurs, CF returns this file as the original.  The auto upload needs the original, un-renamed file--->
	<cfset caller.uploadedfilename = file.ServerFile>
	<cfset caller.errorlevel = 0>
</cfif>	
