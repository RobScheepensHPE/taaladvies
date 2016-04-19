<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Media File Selection</title>
<!-- #include file="functions.asp" -->
<%
Dim SQL, Record, nMedia, Media_recordcount, objMediaConn, objMediaRS, ExtensionList, FiletypeList, TmpString

SQL = "SELECT	extension_tbl.extension_str, extension_tbl.filetype_id, filetype_tbl.filetype_description" _
			& " FROM	extension_tbl, filetype_tbl" _
			& " WHERE	extension_tbl.filetype_id = filetype_tbl.filetype_id	"

Set objMediaConn = Server.CreateObject("ADODB.Connection")
objMediaConn.ConnectionString = "DSN=" & dsn
objMediaConn.Open
Set objMediaRS = Server.CreateObject("ADODB.RecordSet")
objMediaRS.Open SQL, objMediaConn

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
FiletypeList = ""
objMediaRS.MoveFirst
for nMedia = 0 to Media_recordcount - 1
	TmpString = UCase(objMediaRS("extension_str"))
	if (NOT ((mid(TmpString, 1, 1) >= "0") AND (mid(TmpString, 1, 1) <= "Z")) ) then
		TmpString = mid(TmpString,2,len(TmpString))
	end if
	if len(ExtensionList) then
		ExtensionList = ExtensionList & "," & TmpString
		FiletypeList = FiletypeList & "," & objMediaRS("filetype_description")
	else
		ExtensionList = TmpString
		FiletypeList = objMediaRS("filetype_description")
	end if
	objMediaRS.MoveNext
Next
objMediaRS.Close
objMediaConn.Close
%>
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
				var strFilename = parent.mediauploader.document.upload.uploadfilephoto.value;
				strFilename = strFilename.toLowerCase();
				fileextension = strFilename.split(".");
				var strTmpString = "<%=(ExtensionList)%>";
				strTmpString = strTmpString.toLowerCase();
				extension = strTmpString.split(",");
				var strTmpString = "<%=(FiletypeList)%>";
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

	function CheckExtension() {
		var strFilename = parent.mediauploader.document.upload.uploadfilephoto.value;
		strFilename = strFilename.toLowerCase();
		fileextension = strFilename.split(".");
		//////////////////////////////////////////////////////////////////////////////////////
		var mystring = top.opener.eWebEditPro['<%=(Request.QueryString("editorname"))%>'].MediaFile().getPropertyString("ValidExtensions");
		extensions = mystring.split(",");
		for (i = 0; i < extensions.length; i++) {
			if ( fileextension[fileextension.length - 1] == extensions[i]) break
		}
		if (i == extensions.length) {
			alert('You have not selected a file with one of the valid extensions: ' + top.opener.eWebEditPro['<%=(Request.QueryString("editorname"))%>'].MediaFile().getPropertyString("ValidExtensions"));
			return false;
		}
		/////////////////////////////////////////////////////////////////////////////////////////		
		var strTmpString = "<%=(ExtensionList)%>";
		strTmpString = strTmpString.toLowerCase();
		extension = strTmpString.split(",");
		var strTmpString = "<%=(FiletypeList)%>";
		filetype = strTmpString.split(",");
		for (iloop = 0; iloop < extension.length; iloop++)
		{
			if (fileextension[fileextension.length - 1] == extension[iloop])
			{
				return true;
			}
		}
		alert('You have not selected a file with one of the valid extensions: ' + top.opener.eWebEditPro['<%=(Request.QueryString("editorname"))%>'].MediaFile().getPropertyString("ValidExtensions"));
		return false;
	}
	
	function CheckForTitle() {
			if (parent.mediauploader.document.upload.file_title.value == "")
			{
				alert("File Description is required.");
				return false;
			}
			return true;
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
<body bgcolor="d3d3d3" <%if (Request.QueryString("focus") = "true") then Response.Write("onload='self.focus();'")%>>
<%
if (Request.QueryString("action") = "local") then
%>
	<form action="mediainsert.asp?editorname=<%Request.QueryString("editorname")%>&action=upload" method="POST" id=form1 name=form1>
	<table width="100%" cellpadding="2" cellspacing="2" height="100%">
		<tr>
			<td align="right">
				<input type="Submit" name="insert_button" value="    OK    " align="MIDDLE" OnClick="return CheckInformation();">
			</td>
			<td align="left">
				<input type="Submit" name="cancel_button" value="Cancel" align="MIDDLE" onclick="javascript:top.close();">
			</td>
		</tr>	
	</table>
	</form>
<%
elseif (Request.QueryString("action") =  "server") then
%>
	<%
	SQL = "Select	media_path, media_filename, media_title, media_width, media_height" _
				& " From	media_tbl" _
				& " WHERE	media_id = " & Request.QueryString("media_id") & " AND media_deleted = 0"

	Set objMediaConn = Server.CreateObject("ADODB.Connection")
	objMediaConn.ConnectionString = "DSN=" & dsn
	objMediaConn.Open
	Set objMediaRS = Server.CreateObject("ADODB.RecordSet")
	objMediaRS.Open SQL, objMediaConn

	%>
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
				var strFilename = "<%=(objMediaRS("media_path"))%><%=(objMediaRS("media_filename"))%>";
				strFilename = strFilename.toLowerCase();
				fileextension = strFilename.split(".");
				var strTmpString = "<%=(ExtensionList)%>";
				strTmpString = strTmpString.toLowerCase();
				extension = strTmpString.split(",");
				var strTmpString = "<%=(FiletypeList)%>";
				filetype = strTmpString.split(",");
				for (iloop = 0; iloop < extension.length; iloop++)
				{
					if (fileextension[fileextension.length - 1] == extension[iloop])
					{
						top.opener.eWebEditPro['<%=(Request.QueryString("editorname"))%>'].MediaFile().setProperty("IsLocal", false);
						top.opener.eWebEditPro.instances['<%=(Request.QueryString("editorname"))%>'].insertMediaFile('<%=(objMediaRS("media_path"))%><%=(objMediaRS("media_filename"))%>', 0, '<%=(objMediaRS("media_title"))%>',  filetype[iloop], '<%=(objMediaRS("media_width"))%>', '<%=(objMediaRS("media_height"))%>');
						top.close();
					}
				}
			}
		}
	</script>
	<form action="mediainsert.asp?editorname=<%Request.QueryString("editorname")%>&action=local" method="POST" id=form2 name=form2>
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
	</form>
	<%
	objMediaRS.Close
	objMediaConn.Close
	%>
<%
elseif (Request.QueryString("action") =  "upload") then
%>
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
<%
else
%>
	<form action="mediainsert.asp?editorname=<%Request.QueryString("editorname")%>" method="POST" id=form3 name=form3>
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
	</form>
<%
end if
%>
</body>
</html>
