<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Media File Selection</title>
<cfinclude template="ewebeditprodefinedsn2.cfm">
<CFQUERY NAME="extensions" DATASOURCE="#DSN#">
	SELECT	extension_tbl.extension_str, extension_tbl.filetype_id, filetype_tbl.filetype_description
	FROM	extension_tbl, filetype_tbl
	WHERE	extension_tbl.filetype_id = filetype_tbl.filetype_id	
</CFQUERY>
<cfset variable.ExtensionList = "">
<cfset variable.FiletypeList = "">
<cfloop query="extensions">
	<cfset variable.TmpString = UCASE(extensions.extension_str)>
	<cfif NOT ((mid(variable.TmpString, 1, 1) GTE '0') AND (mid(variable.TmpString, 1, 1) LTE 'Z'))>
		<cfset variable.TmpString = mid(variable.TmpString,2,len(variable.TmpString))>
	</cfif>
	<cfif len(variable.ExtensionList)>
		<cfset variable.ExtensionList = variable.ExtensionList & "," & variable.TmpString>
		<cfset variable.FiletypeList = variable.FiletypeList & "," & extensions.filetype_description>
	<cfelse>
		<cfset variable.ExtensionList = variable.TmpString>
		<cfset variable.FiletypeList = extensions.filetype_description>
	</cfif>
</cfloop>
<script Language="Javascript">

	function CFLocalProvideMediaFile()
	{
		if (top.opener.closed)
		{	
			alert("Operation has been cancelled because the editor page has been closed.");
		}
		else
		{
			if (parent.mediauploader.document.upload.file_title.value == "")
			{
				alert("File Description is required.");
			}
			else
			{
				var strEditorName = "<cfoutput>#url.editorname#</cfoutput>";
				var strFilename = parent.mediauploader.document.upload.uploadfilephoto.value;
				strFilename = strFilename.toLowerCase();
				fileextension = strFilename.split(".");
				var strTmpString = "<cfoutput>#variable.ExtensionList#</cfoutput>";
				strTmpString = strTmpString.toLowerCase();
				extension = strTmpString.split(",");
				var strTmpString = "<cfoutput>#variable.FiletypeList#</cfoutput>";
				filetype = strTmpString.split(",");
				for (iloop = 0; iloop < extension.length; iloop++)
				{
					if (fileextension[fileextension.length - 1] == extension[iloop])
					{
						parent.mediauploader.document.upload.submit();
						break;
					}
				}
			}
		}
	}

	function CheckForTitle() {
			if (parent.mediauploader.document.upload.file_title.value == "")
			{
				alert("File Description is required.");
				return false;
			}
			return true;
	}
	
	function CheckExtension() {
		var strEditorName = "<cfoutput>#url.editorname#</cfoutput>";
		var strFilename = parent.mediauploader.document.upload.uploadfilephoto.value;
		strFilename = strFilename.toLowerCase();
		fileextension = strFilename.split(".");
		
		var mystring = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyString("ValidExtensions");
		extensions = mystring.split(",");
		for (i = 0; i < extensions.length; i++) {
			if ( fileextension[fileextension.length - 1] == extensions[i]) break
		}
		if (i == extensions.length) {
			alert('You have not selected a file with one of the valid extensions: ' + top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyString("ValidExtensions"));
			return false;
		}
		var strTmpString = "<cfoutput>#variable.ExtensionList#</cfoutput>";
		strTmpString = strTmpString.toLowerCase();
		extension = strTmpString.split(",");
		var strTmpString = "<cfoutput>#variable.FiletypeList#</cfoutput>";
		filetype = strTmpString.split(",");
		for (iloop = 0; iloop < extension.length; iloop++)
		{
			if (fileextension[fileextension.length - 1] == extension[iloop])
			{
				return true;
			}
		}
		alert('You have not selected a file with one of the valid extensions: ' + top.opener.eWebEditPro['<cfoutput>#url.editorname#</cfoutput>'].MediaFile().getPropertyString("ValidExtensions"));
		return false;
	}

	function NoPicture()
	{
		if (top.opener.closed)
		{	
			alert("Operation has been cancelled because the editor page has been closed.");
		}
		else if (parent.mediauploader.document.upload.uploadfilephoto.value != "")
		{
			if (CheckForTitle() == false || CheckExtension() == false)
			{
				parent.mediauploader.document.upload.file_title.focus();
				return false;
			}
			else
			{
				CFLocalProvideMediaFile();
				return true;
			}
		}
		else
		{
			alert("Select a File.");
		}
	}
	
	function CheckInformation() {
		if (CheckForTitle() == false || CheckExtension() == false)
		{
			return false;
		}
		return true;
	}
</script>

</head>
<body bgcolor="d3d3d3" <cfif IsDefined("url.focus")> <cfoutput>onload="self.focus();"</cfoutput> </cfif>>
<cfif IsDefined("url.action") AND url.action IS "local">
	<cfform action="mediainsert.cfm?editorname=<cfoutput>#url.editorname#</cfoutput>&action=upload" method="POST">
	<table width="100%" cellpadding="2" cellspacing="2" height="100%">
		<tr>
			<td align="right">
				<input type="Submit" name="insert_button" value="    OK     " align="MIDDLE" OnClick="return CheckInformation();">
			</td>
			<td align="left">
				<input type="Submit" name="cancel_button" value="Cancel" align="MIDDLE" onclick="javascript:top.close();">
			</td>
		</tr>	
	</table>
	</cfform>
<cfelseif IsDefined("url.action") AND url.action IS "server">
	<CFQUERY NAME="media" DATASOURCE="#DSN#">
		Select	media_path, media_filename, media_title, media_width, media_height
		From	media_tbl
		WHERE	media_id = #url.media_id#
				AND media_deleted = 0
	</CFQUERY> 
	<script language="javascript">
		function CFServerProvideMediaFile()
		{
			if (top.opener.closed)
			{	
				alert("Operation has been cancelled because the editor page has been closed.");
			}
			else
			{
				if (parent.mediauploader.document.upload.uploadfilephoto.value != "")
				{
					if (CheckForTitle() == false)
					{
						parent.mediauploader.document.upload.file_title.focus();
						return false;
					}
					else
					{
						CFLocalProvideMediaFile();
						return true;
					}
				}
				var strEditorName = "<cfoutput>#url.editorname#</cfoutput>";
				var strFilename = "<cfoutput>#media.media_path##media.media_filename#</cfoutput>";
				strFilename = strFilename.toLowerCase();
				fileextension = strFilename.split(".");
				var strTmpString = "<cfoutput>#variable.ExtensionList#</cfoutput>";
				strTmpString = strTmpString.toLowerCase();
				extension = strTmpString.split(",");
				var strTmpString = "<cfoutput>#variable.FiletypeList#</cfoutput>";
				filetype = strTmpString.split(",");
				for (iloop = 0; iloop < extension.length; iloop++)
				{
					if (fileextension[fileextension.length - 1] == extension[iloop])
					{
						top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("IsLocal", false);
						top.opener.eWebEditPro.instances['<cfoutput>#url.editorname#</cfoutput>'].insertMediaFile('<cfoutput>#media.media_path##media.media_filename#', 0, '#media.media_title#</cfoutput>',  filetype[iloop], '<cfoutput>#media.media_width#</cfoutput>', '<cfoutput>#media.media_height#</cfoutput>');
						top.close();
					}
				}
			}
		}
	</script>
	<cfform action="mediainsert.cfm?editorname=<cfoutput>#url.editorname#</cfoutput>&action=local" method="POST">
	<table width="100%" cellpadding="2" cellspacing="2" height="100%">
		<tr>
			<td align="right">
				<input type="Submit" name="insert_button" value="    OK    " align="MIDDLE" onclick="CFServerProvideMediaFile();">
			</td>
			<td align="left">
				<input type="Submit" name="cancel_button" value="Cancel" align="MIDDLE" onclick="javascript:top.close();">
			</td>
		</tr>	
	</table>
	</cfform>
<cfelseif IsDefined("url.action") AND url.action IS "upload">
	<table width="100%" cellpadding="2" cellspacing="2" height="100%">
		<tr>
			<td align="center">
			<font face="verdana" size="4">
				Uploading File
			</font>
			</td>
		</tr>	
		<tr>
			<td align="center">
			<font face="verdana" size="4">
				Please Wait...
			</font>
			</td>
		</tr>	
	</table>
	<script language="javascript">
		CFLocalProvideMediaFile();
	</script>	
<cfelse>
	<cfform action="mediainsert.cfm?editorname=<cfoutput>#url.editorname#</cfoutput>" method="POST">
	<table width="100%" cellpadding="2" cellspacing="2" height="100%">
		<tr>
			<td align="right">
				<input type="button" name="insert_button" value="    OK    " align="MIDDLE" onclick="NoPicture();">
			</td>
			<td align="left">
				<input type="Submit" name="cancel_button" value="Cancel" align="MIDDLE" onclick="javascript:top.close();">
			</td>
		</tr>	
	</table>
	</cfform>
</cfif>
</body>
</html>
