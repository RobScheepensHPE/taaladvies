<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<!--- Note that in the images directory needs to be a file imagepath.cfm  --->

<!-- #include file="functions.asp" -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Media File Notification</title>
</head>

<body bgcolor="d3d3d3">
<%
Dim fileObj, strReturnString, ErrorCode, EditorMediaPath, FileTitle, WebMediaPath, ImageDate
Dim FileSize, ExtensionID, Width, Height, FileType, BinaryFormData

BinaryFormData = Request.BinaryRead(Request.TotalBytes)
Set fileObj = CreateObject("EktronFileIO.EkFile")
EditorMediaPath = fileObj.EkFormFieldValue(BinaryFormData, "editor_media_path", ErrorCode)
FileTitle = fileObj.EkFormFieldValue(BinaryFormData, "file_title", ErrorCode)
WebMediaPath = fileObj.EkFormFieldValue(BinaryFormData, "web_media_path", ErrorCode)
ImageDate = fileObj.EkFormFieldValue(BinaryFormData, "img_date", ErrorCode)
FileSize = fileObj.EkFormFieldValue(BinaryFormData, "file_size", ErrorCode)
ExtensionID = fileObj.EkFormFieldValue(BinaryFormData, "extension_id", ErrorCode)
Width = fileObj.EkFormFieldValue(BinaryFormData, "width", ErrorCode)
Height = fileObj.EkFormFieldValue(BinaryFormData, "height", ErrorCode)
FileType = fileObj.EkFormFieldValue(BinaryFormData, "file_type", ErrorCode)

strReturnString = fileObj.EkFileSave(BinaryFormData, "uploadfilephoto", _
																	Server.MapPath(EditorMediaPath), ErrorCode, "makeunique")


%>
<%
if (ErrorCode <> 0) then
%>
	<script language="JavaScript">
		alert("Upload Failed\n" + "<%=(strReturnString)%>" + "\nErrorCode=" + "<%=(ErrorCode)%>");
		top.close();
	</script> 
<%
else
%>
<%
	AddFileToDatabase FileTitle, WebMediaPath, strReturnString, ImageDate, FileSize, ExtensionID, Width, Height
%>
	<script language="JavaScript">
		var strEditorName = "<%=(Request.QueryString("editorname"))%>";
		top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("IsLocal", false);
		top.opener.eWebEditPro.instances['<%=(Request.QueryString("editorname"))%>'].insertMediaFile('<%=(WebMediaPath)%>/<%=(strReturnString)%>', 0, '<%=(FileTitle)%>','<%=(FileType)%>', '<%=(Width)%>', '<%=(Height)%>');
		top.close();
	</script> 
<%
end if
%>
</body>
</html>

