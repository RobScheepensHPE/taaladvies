<% Response.Buffer = TRUE %>
<!-- #include file="functions.asp" -->
<%
' mediaautoreceive.asp
' Receives files without involving the ASP database user interface.
%>

<%
	Dim g_LogicalRefDestination
	Dim g_objUpload
	Dim g_binaryFormData
	
	Set g_objUpload = CreateObject("eWepAutoSvr4.EkFile")
    g_binaryFormData = Request.BinaryRead(Request.TotalBytes)

	' This is where the files will be seen from the web,
	' NOT the physical disk drive location.
	CreateVirtualDestination	
	
	'Recieve and save the files
	ProcessSubmittedForm
	
'''''''''''''''''''''''''''''''''''''''''''''''''
' Examines the submitted for to determine what 
' the client is uploading and to perform the
' appropriate operation.
Sub ProcessSubmittedForm()
	Dim strCommand, ErrorCode
	
	' Extract the "actiontyp" field.
	' This contains the upload command.
	strCommand = g_objUpload.EkFormFieldValue(g_binaryFormData, "actiontyp", ErrorCode)
	
	' These are the possible commands:
	If strCommand = "uploadfile" Then
		ReceiveSubmittedFiles  ' Saves the submitted files.
	ElseIf strCommand = "uploadcontent" Then
		ReceiveContent
	Else
		Response.Write("<html><body><h1>Unknown Posting.</h1></body></html>") 
	End If
End Sub

	
'''''''''''''''''''''''''''''''''''''''''''''''''
' This function will receive the files and send back
' the required response data.  There is no processing
' of the files and there is no affecting the file data.
Sub ReceiveSubmittedFiles()
    Dim objFile, iErrorCode
	Dim strFileAltTitle, strImageDate
	Dim iFileSize, iExtensionID, iWidth, iHeight, strFileType
	 
	strFileAltTitle = g_objUpload.EkFormFieldValue(g_binaryFormData, "file_title", iErrorCode)
	'strReqWebRoot = g_objUpload.EkFormFieldValue(g_binaryFormData, "web_media_path", iErrorCode)
	strImageDate = g_objUpload.EkFormFieldValue(g_binaryFormData, "img_date", iErrorCode)
	iFileSize = g_objUpload.EkFormFieldValue(g_binaryFormData, "file_size", iErrorCode)
	iExtensionID = g_objUpload.EkFormFieldValue(g_binaryFormData, "extension_id", iErrorCode)
	iWidth = g_objUpload.EkFormFieldValue(g_binaryFormData, "width", iErrorCode)
	iHeight = g_objUpload.EkFormFieldValue(g_binaryFormData, "height", iErrorCode)
	strFileType = g_objUpload.EkFormFieldValue(g_binaryFormData, "file_type", iErrorCode)
	
    strNewFileName = g_objUpload.EkFileSave(g_binaryFormData, "uploadfilephoto", _
        Server.MapPath(g_LogicalRefDestination), iErrorCode, "makeunique")

	If g_objUpload.FileCount() > 0 then
		Set objFile = g_objUpload.FileObject(1)
		strNewFileName = objFile.FileName()
		objFile.FileUrl(MakeMediaPathName(g_LogicalRefDestination, strNewFileName))  ' see:  functions.asp
			
		AddFileToDatabase strFileAltTitle, g_LogicalRefDestination, strNewFileName, strImageDate, iFileSize, iExtensionID, iWidth, iHeight
		
		Set objFile = Nothing
	End If
		
	Response.Write(g_objUpload.ResponseData()) 
	
End Sub

'''''''''''''''''''''''''''''''''''''''''''''''''
' This routine processes the submission of the 
' content contained within the eWebEditPro editor.
Sub ReceiveContent()
	Dim strResp
	Dim ErrorCode
	Dim strTitle
	Dim strHtml
	Dim strID
	
	strTitle = SQLFilter(g_objUpload.EkFormFieldValue(g_binaryFormData, "content_title", ErrorCode))
	strHtml = SQLFilter(g_objUpload.EkFormFieldValue(g_binaryFormData, "content_text", ErrorCode))
	strID = g_objUpload.EkFormFieldValue(g_binaryFormData, "content_description", ErrorCode)
	
	strResp = "<html><body>"
	
	If "New" = strID Then
		strResp = strResp & "<H2>New Content Received</h2>"
		AddNewContentToDatabase strTitle, strHtml
	Else
		strResp = strResp & "<H2>Updated Content Received</h2>"
		UpdateContentInDatabase strTitle, strHtml, strID
	End If
	strResp = strResp & "<p style='color:red'>Click on 'Undo' to show your content and continue editing.</p>"
		
	strResp = strResp & "<br><ContentTitle>" & strTitle & "</ContentTitle><br>"
	strResp = strResp & "<ContentID>" & ContentIDFromTitle(strTitle) & "</ContentID><br>"
	'strResp = strResp & "Content Size:&nbsp;&nbsp;" & g_objUpload.EkFormFieldValue(g_binaryFormData, "content_size", ErrorCode) & "<br>"
	'strResp = strResp & "Content Description:&nbsp;&nbsp;" & strID & "<br>"
	
	'strResp = strResp & "Content Type:&nbsp;&nbsp;" & g_objUpload.EkFormFieldValue(g_binaryFormData, "content_type", ErrorCode)
	
	'strResp = strResp & "<br>"
	'strResp = strResp & "<H3>Submitted Content Below</h3><hr>"
	'strResp = strResp & Server.HTMLEncode(strHtml)
	
	strResp = strResp & "<hr>"
	
	strResp = strResp & "</body></html>"

	Response.Write(strResp)
End Sub
'''''''''''''''''''''''''''''''''''''''''''''''''
' This is where the files will be seen from the web,
' NOT the physical disk drive location.
Sub CreateVirtualDestination()
	Dim strCur, strDirs, iMax, idx
	Dim ErrorCode
	
	g_LogicalRefDestination = g_objUpload.EkFormFieldValue(g_binaryFormData, "editor_media_path", ErrorCode)
	If len(g_LogicalRefDestination) = 0 then
		' A directory was not sent to us.
		strCur = Request.ServerVariables("URL")
		strDirs = Split(strCur, "/")
		iMax = UBound(strDirs) 
		If iMax > 0 Then
			idx = 1
			strCur = strDirs(0)
			while idx < iMax
				strCur = strCur & "/" & strDirs(idx)
				idx = idx + 1
			Wend
			g_LogicalRefDestination = strCur & "/upload"
		Else
			'Could not split the directory.
			g_LogicalRefDestination = "/ewebeditpro4/upload"
		End If
	End If
	
End Sub
%> 




