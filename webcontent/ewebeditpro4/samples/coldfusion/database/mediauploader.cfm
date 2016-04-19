<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<cfinclude template="ewebeditprodefinedsn2.cfm">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Media File Upload</title>
</head>
<CFQUERY NAME="extensions" DATASOURCE="#DSN#">
	SELECT	extension_tbl.extension_str, extension_tbl.filetype_id, filetype_tbl.filetype_description
	FROM	extension_tbl, filetype_tbl
	WHERE	extension_tbl.filetype_id = filetype_tbl.filetype_id	
</CFQUERY>
<cfset variable.ExtensionList = "">
<cfset variable.ExtensionIDList = "">
<cfset variable.FiletypeList = "">
<cfloop query="extensions">
	<cfset variable.TmpString = UCASE(extensions.extension_str)>
	<cfif NOT ((mid(variable.TmpString, 1, 1) GTE '0') AND (mid(variable.TmpString, 1, 1) LTE 'Z'))>
		<cfset variable.TmpString = mid(variable.TmpString,2,len(variable.TmpString))>
	</cfif>
	<cfif len(variable.ExtensionList)>
		<cfset variable.ExtensionList = variable.ExtensionList & "," & variable.TmpString>
		<cfset variable.ExtensionIDList = variable.ExtensionIDList & "," & extensions.filetype_id>
		<cfset variable.FiletypeList = variable.FiletypeList & "," & extensions.filetype_description>
	<cfelse>
		<cfset variable.ExtensionList = variable.TmpString>
		<cfset variable.ExtensionIDList = extensions.filetype_id>
		<cfset variable.FiletypeList = extensions.filetype_description>
	</cfif>
</cfloop>

<!--- CnvString, the file path may have a space, Netscape has a problem with --->	
	<script language="Javascript">
		function checkExtension(cStr){
			if (cStr == "") { return false; }
			var strEditorName = "<cfoutput>#url.editorname#</cfoutput>";
			var cStrTemp = cStr;
			var cTemp1 = cStrTemp.split(".");
			cTemp3 = cTemp1[cTemp1.length - 1];
			var cTemp = cTemp3.toLowerCase();
			var mystring = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyString("ValidExtensions");
			extensions = mystring.split(",");
			for (i=0; i<extensions.length; i++) {
				if ( cTemp == extensions[i]) break
			}
			if (i == extensions.length) {
				alert('You have not selected a file with one of the valid extensions: ' + top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyString("ValidExtensions"));
				return false;
			}
			top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("IsLocal", true);
			top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("SrcFileLocationName", cStr);
			if ((top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK") > 0) && ((top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("FileSize")/1024) > top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK"))) {
				alert ('The file selected is too large to be uploaded.\nPlease select a different file.\nMaximum file size allowed = ' + top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK") + 'k bytes');
				return false;
			}
			mystring = "<cfoutput>#variable.ExtensionList#</cfoutput>";
			extensions = mystring.split(",");
			for (i=0; i<extensions.length; i++) {
				extensions[i] = extensions[i].toLowerCase();
				if ( cTemp == extensions[i]) return true;
			}
				alert('The database does not contain this extension.\nYou can not upload this file.');
				
				return false;
		}
		
		function CnvString( cStr ){
			var nLen = cStr.length;
			var cNew = "";
			for (count=0; count < nLen; count++) {
				cTemp=cStr.substring(count, count+1);
				if ( cTemp == " " ) {cNew = cNew + "%20";} 	
				else {cNew = cNew + cTemp;}
			} 
			return cNew;
		} 	

		function getname(cStr, title){
			if (checkExtension(cStr)){
				var mediavalue=CnvString(cStr);
				var place='mediapreview.cfm?medianame=' + mediavalue + '&editorname=<cfoutput>#url.editorname#</cfoutput>';
				var place2='medialist.cfm?editorname=<cfoutput>#url.editorname#</cfoutput>';
				var place3 ='mediainsert.cfm?action=local&SrcFileLocationName=' + mediavalue + '&title=' + title + '&editorname=<cfoutput>#url.editorname#</cfoutput>';
				var strFilename = cStr;
				var iloop = 0;
				
				strFilename = strFilename.toLowerCase();
				fileextension = strFilename.split(".");
				var strTmpString = "";
				strTmpString = "<cfoutput>#variable.ExtensionList#</cfoutput>";
				strTmpString = strTmpString.toLowerCase();
				extension = strTmpString.split(",");
				var strTmpString = "<cfoutput>#variable.FiletypeList#</cfoutput>";
				filetype = strTmpString.split(",");
				var strTmpString = "<cfoutput>#variable.ExtensionIDList#</cfoutput>";
				filetypeIDList = strTmpString.split(",");
				
				for (iloop = 0; iloop < extension.length; iloop++)
				{
					if (fileextension[fileextension.length - 1] == extension[iloop])
					{
						break;
					}
				}
				document.upload.extension_id.value = filetypeIDList[iloop];
				document.upload.file_type.value = filetype[iloop];
				var strEditorName = "<cfoutput>#url.editorname#</cfoutput>";
				
				document.upload.editor_media_path.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyString("Transferroot");
				document.upload.web_media_path.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyString("Webroot");
				top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("IsLocal", true);
				top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("SrcFileLocationName", cStr);
				if ((top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK") > 0) && ((top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("FileSize")/1024) > top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK"))) {
					alert ('The file selected is too large to be uploaded.\nPlease select a different file.\nMaximum file size allowed = ' + top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK") + 'k bytes');
					return false;
				}
				document.upload.file_size.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("FileSize");
				document.upload.height.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("ImageHeight");
				document.upload.width.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("ImageWidth");

				var place4='mediainformation.cfm?filesize=' + document.upload.file_size.value + '&height=' + document.upload.height.value + '&width=' + document.upload.width.value
				parent.mediapreview.location.href = place;
				parent.medialist.location.href = place2;
				parent.mediainsert.location.href = place3;
				parent.mediainfo.location.href=place4;
				}
		}
	</script>


<body bgcolor="d3d3d3" onload="self.focus();">
<cfif IsDefined("url.upload") AND url.upload IS "true">
<!--- Present dialog box to upload a media item from local system--->
<table width="100%" cellpadding="2" cellspacing="2">
	<cfform action="medianotification.cfm?editorname=#url.editorname#" method="POST" enctype="multipart/form-data" name="upload" OnSubmit="return getname(upload.uploadfilephoto.value, upload.file_title.value);">
		<input type="hidden" value="uploadfile" name="actiontyp">
		<input type="hidden" value="<cfoutput>#DateFormat(Now(), "MM/DD/YY")#</cfoutput>" name="img_date">
		<input type="hidden" value="1" name="extension_id">
		<input type="hidden" value="1" name="file_type">
		<input type="hidden" value="temp" name="editor_media_path">
		<input type="hidden" value="temp1" name="web_media_path">
		<input type="hidden" name="extensions" value="">
		<input type="hidden" value="0" name="file_size">
		<input type="hidden" value="0" name="width" size="5">
		<input type="hidden" value="0" name="height" size="5">
	<tr>
		<td>
		<font face="arial" size="2">
			<b>To Select a Local File:</b>
		</font>
		</td>
	</tr>
	<tr>
		<td>
			<cfif FindNoCase("MSIE",#cgi.http_user_agent#,1)>
				<cfif FindNoCase("MSIE 5.0",#cgi.http_user_agent#,1)>
					<input type="File" name="uploadfilephoto" size="20" maxlength="256" align="MIDDLE">
				<cfelse>
					<input type="File" name="uploadfilephoto" size="20" maxlength="256" align="MIDDLE" onChange="getname(upload.uploadfilephoto.value, upload.file_title.value);">
				</cfif>
			<cfelse>
				<input type="File" name="uploadfilephoto" size="20" maxlength="256" align="MIDDLE" onClick="getname(upload.uploadfilephoto.value, upload.file_title.value);">
			</cfif>
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
		<font face="arial" size="2">
			<b>Enter a description for the file.</b>
		</font>
		</td>
	</tr>			
	<tr>
		<td>
			<cfif FindNoCase("MSIE 5.0",#cgi.http_user_agent#,1)>
				<input type="text" name="file_title" size="32" maxlength="32" onFocus="getname(upload.uploadfilephoto.value, upload.file_title.value);">
			<cfelse>
				<input type="text" name="file_title" size="32" maxlength="32">
			</cfif>
		</td>
	</tr>	
	<script language="javascript">
		var strEditorName = "<cfoutput>#url.editorname#</cfoutput>";
		document.upload.extensions.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyString("ValidExtensions");
	</script>
	</cfform>
</table>
</cfif>
</body>
</html>
