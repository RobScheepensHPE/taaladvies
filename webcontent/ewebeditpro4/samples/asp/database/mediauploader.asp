<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<!-- #include file="functions.asp" -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Media File Upload</title>
</head>
<%
Dim SQL, Record, nMedia, Media_recordcount, objMediaConn, objMediaRS, ExtensionList, ExtensionIDList
Dim FiletypeList, TmpString

SQL = "SELECT	extension_tbl.extension_str, extension_tbl.filetype_id, filetype_tbl.filetype_description" _
			& " FROM	extension_tbl, filetype_tbl" _
			& " WHERE	extension_tbl.filetype_id = filetype_tbl.filetype_id"

Set objMediaConn = Server.CreateObject("ADODB.Connection")
objMediaConn.ConnectionString = "DSN=" & dsn
objMediaConn.Open
Set objMediaRS = Server.CreateObject("ADODB.RecordSet")
objMediaRS.Open SQL, objMediaConn
%>
<%
Media_recordcount = 0
if (objMediaRS.EOF = False) then
	while (objMediaRS.EOF = False)
		Media_recordcount = Media_recordcount + 1
		objMediaRS.MoveNext
	Wend
end if
%>
<%
ExtensionList = ""
ExtensionIDList = ""
FiletypeList = ""
objMediaRS.MoveFirst 
for nMedia = 0 to Media_recordcount - 1
	TmpString = UCase(objMediaRS("extension_str"))
	if (NOT ((mid(TmpString, 1, 1) >= "0") AND (mid(TmpString, 1, 1) <= "Z")) ) then
		TmpString = mid(TmpString,2,len(TmpString))
	end if
	if len(ExtensionList) then
		ExtensionList = ExtensionList & "," & TmpString
		ExtensionIDList = ExtensionIDList & "," & objMediaRS("filetype_id")
		FiletypeList = FiletypeList & "," & objMediaRS("filetype_description")
	else
		ExtensionList = TmpString
		ExtensionIDList = objMediaRS("filetype_id")
		FiletypeList = objMediaRS("filetype_description")
	end if
	objMediaRS.MoveNext
Next
%>
<!--- CnvString, the file path may have a space, Netscape has a problem with --->	
	<script language="Javascript">
	var OldFilename = "";
		function checkExtension(cStr){
			if (cStr == "") { return false; }
			var strEditorName = "<%=(Request.QueryString("editorname"))%>";
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
			mystring = "<%=(ExtensionList)%>";
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
				var place='mediapreview.asp?medianame=' + mediavalue + '&editorname=<%=(Request.QueryString("editorname"))%>';
				var place2='medialist.asp?editorname=<%=(Request.QueryString("editorname"))%>';
				var place3 ='mediainsert.asp?action=local&SrcFileLocationName=' + mediavalue + '&title=' + title + '&editorname=<%=(Request.QueryString("editorname"))%>';
				var strFilename = cStr;
				var iloop = 0;
				
				strFilename = strFilename.toLowerCase();
				fileextension = strFilename.split(".");
				var strTmpString = "";
				strTmpString = "<%=(ExtensionList)%>";
				strTmpString = strTmpString.toLowerCase();
				extension = strTmpString.split(",");
				var strTmpString = "<%=(FiletypeList)%>";
				filetype = strTmpString.split(",");
				var strTmpString = "<%=(ExtensionIDList)%>";
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
				var strEditorName = "<%=(Request.QueryString("editorname"))%>";
				
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

				var place4='mediainformation.asp?filesize=' + document.upload.file_size.value + '&height=' + document.upload.height.value + '&width=' + document.upload.width.value
				parent.mediapreview.location.href = place;
				parent.medialist.location.href = place2;
				parent.mediainsert.location.href = place3;
				parent.mediainfo.location.href=place4;
				}
		}
	</script>


<body bgcolor="d3d3d3">
<%
if (Request.QueryString("upload") = "true") then
%>
	<!--- Present dialog box to upload a media item from local system--->
	<table width="100%" cellpadding="2" cellspacing="2">
		<form action="medianotification.asp?editorname=<%=(Request.QueryString("editorname"))%>" method="POST" enctype="multipart/form-data" name="upload" OnSubmit="return getname(upload.uploadfilephoto.value, upload.file_title.value);">
			<input type="hidden" value="uploadfile" name="actiontyp">
			<input type="hidden" value="<%=(Now)%>" name="img_date">
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
				<%
				if (InStr(1, UCase(Request.ServerVariables("http_user_agent")), "MSIE")) then
					if (InStr(1, UCase(Request.ServerVariables("http_user_agent")), "MSIE 5.0")) then
					%>
						<input type="File" name="uploadfilephoto" size="20" maxlength="256" align="MIDDLE"">
					<%
					else
					%>
						<input type="File" name="uploadfilephoto" size="20" maxlength="256" align="MIDDLE" onChange="getname(upload.uploadfilephoto.value, upload.file_title.value);">
					<%
					end if
					%>
				<%
				else
				%>
					<input type="File" name="uploadfilephoto" size="20" maxlength="256" align="MIDDLE" onClick="getname(upload.uploadfilephoto.value, upload.file_title.value);">
				<%
				end if
				%>
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
				<%
				if (InStr(1, UCase(Request.ServerVariables("http_user_agent")), "MSIE 5.0")) then
				%>
					<input type="text" name="file_title" size="32" maxlength="32" onFocus="getname(upload.uploadfilephoto.value, upload.file_title.value);">
				<%
				else
				%>
					<input type="text" name="file_title" size="32" maxlength="32">
				<%
				end if
				%>
			</td>
		</tr>	
		<script language="javascript">
			var strEditorName = "<%=(Request.QueryString("editorname"))%>";
			document.upload.extensions.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyString("ValidExtensions");
		</script>
		</form>
	</table>
<%
end if
objMediaRS.Close
objMediaConn.Close
%>
</body>
</html>
