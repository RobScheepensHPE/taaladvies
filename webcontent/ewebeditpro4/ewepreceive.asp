<%
' This code is available for modification.
' Modify to follow the requirements of the server.

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
	Dim strCommand
	Dim ErrorCode
	
	' Extract the "actiontyp" field.
	' This contains the upload command.
	strCommand = g_objUpload.EkFormFieldValue(g_binaryFormData, "actiontyp", ErrorCode)
	
	' These are the possible commands:
	If strCommand = "uploadfile" Then
		ReceiveSubmittedFiles
	ElseIf strCommand = "uploadcontent" Then
		ReceiveContent
	End If
End Sub
	
'''''''''''''''''''''''''''''''''''''''''''''''''
' This function will receive the files and send back
' the required response data.  There is no processing
' of the files and there is no affecting the file data.
Sub ReceiveSubmittedFiles()
    Dim fileObj
    Dim ErrorCode, iFileIdx
 
    strNewFileName = g_objUpload.EkFileSave(g_binaryFormData, "uploadfilephoto", _
        Server.MapPath(g_LogicalRefDestination), ErrorCode, "makeunique")        ' was 
		
	iFileCount = g_objUpload.FileCount()
	If iFileCount > 0 then
		Do while iFileIdx < iFileCount
			iFileIdx = iFileIdx + 1
			Set fileObj = g_objUpload.FileObject(iFileIdx)
            ' If you are using a port you will need to add the
            ' port to the file path:
            '      & ":" & Request.ServerVariables("SERVER_PORT") 
			fileObj.FileUrl("HTTP://" & Request.ServerVariables("SERVER_NAME") & g_LogicalRefDestination & "/" & fileObj.FileName())
		loop
	End If

	Response.Write("<html><body><h1>Upload Received</h1><p>The file resides in:</p></p>" + g_LogicalRefDestination + "</p>" + g_objUpload.ResponseData() + "</body></html>") 
	
End Sub

'''''''''''''''''''''''''''''''''''''''''''''''''
' This routine processes the submission of the 
' content contained within the eWebEditPro editor.
Sub ReceiveContent()
	Dim strResp
	Dim ErrorCode
	
	strResp = "<html><body>"
	
	strResp = strResp & "<H2>Content Successfully Received</h2>"
	strResp = strResp & "<p style='color:red'>However, the sample page that received the content <i>does not</i> save the posted content on the server.</p>"
	strResp = strResp & "<p style='color:red; font:bold'>The content is not saved.</p>"
	strResp = strResp & "<p style='color:red'>Modify the sample receiving page to save the content or specify another receiving page that does save the content.</p>"
	strResp = strResp & "<p style='color:red; font:bold'>Click on 'Undo' to restore your content.</p>"
	strResp = strResp & "<br>"
	strResp = strResp & "Content Title:&nbsp;&nbsp;" & g_objUpload.EkFormFieldValue(g_binaryFormData, "content_title", ErrorCode) & "<br>"
	strResp = strResp & "Content Size:&nbsp;&nbsp;" & g_objUpload.EkFormFieldValue(g_binaryFormData, "content_size", ErrorCode) & "<br>"
	strResp = strResp & "Content Description:&nbsp;&nbsp;" & g_objUpload.EkFormFieldValue(g_binaryFormData, "content_description", ErrorCode) & "<br>"
	
	strResp = strResp & "Content Type:&nbsp;&nbsp;" & g_objUpload.EkFormFieldValue(g_binaryFormData, "content_type", ErrorCode)
	strResp = strResp & "<br>"

	strResp = strResp & "<H3>Submitted Content Below</h3><hr>"
	strResp = strResp & Server.HTMLEncode(g_objUpload.EkFormFieldValue(g_binaryFormData, "content_text", ErrorCode))
	
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

'''''''''''''''''''''''''''''''''''''''''''''''''
'''''''''''''''''''''''''''''''''''''''''''''''''
' Below is an example of how you process
' the uploaded files and send back other
' file information such as thumbnail values.
'Sub CreateThumbnailsFromFiles()
'	Dim iClientMajorRev, iClientMinorRev, iFileCount
'    Dim g_binaryFormData, g_objUpload, fileObj, g_LogicalRefDestination
'    Dim strNewFileName, strFileLoc, ErrorCode, iFileIdx
' 
'    g_binaryFormData = Request.BinaryRead(Request.TotalBytes)
'    set g_objUpload = CreateObject("EwepTransfer.EkFile")
'	
'    strNewFileName = g_objUpload.EkFileSave(g_binaryFormData, "uploadfilephoto", _
'        Server.MapPath(g_LogicalRefDestination), ErrorCode, "makeunique")
'		
'	iFileCount = g_objUpload.FileCount()
'	If iFileCount > 0 then
'		Do while iFileIdx < iFileCount
'			iFileIdx = iFileIdx + 1
'			Set fileObj = g_objUpload.FileObject(iFileIdx)
'			strNewFileName = fileObj.FileName()
'			strFileLoc = "HTTP://" & Request.ServerVariables("SERVER_NAME") & g_LogicalRefDestination & "/" & strNewFileName
'			fileObj.FileUrl(strFileLoc)
'			fileObj.Thumbnail(CreateThumbnail(strFileLoc))
'			fileObj.ThumbReference(ExtractThumbnailRef(strFileLoc))
'		loop
'		
'		'Retrieve client data
'		iClientMajorRev = g_objUpload.ClientMajorRev()
'		iClientMinorRev = g_objUpload.ClientMinorRev() 
'		
'		Response.Write(g_objUpload.ResponseData()) 
'	End If
'	
'End Sub
%> 


