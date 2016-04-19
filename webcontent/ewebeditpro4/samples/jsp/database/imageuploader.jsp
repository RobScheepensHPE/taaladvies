
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Image Uploader</title>
	<script language="JavaScript1.2">
		
	//convert spaces to %20 for netscape
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
	
	//Preview file function
		function PreviewFile(cStr){
				var strEditorName = top.opener.eWebEditPro.event.srcName; //gets the editor's name
				var imagevalue=CnvString(cStr);
				var place='imagepreview.jsp?imagename=' + imagevalue;
				parent.preview.location.href = place;
				
			//Get Image attributes.  These are all documented.
			top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("IsLocal", true);
			top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("SrcFileLocationName", cStr);					
				strFileSize = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("FileSize");
				strHeight = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("ImageHeight");
				strWidth = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("ImageWidth");
			var place4='imageinformation.jsp?filesize=' + strFileSize + '&height=' + strHeight + '&width=' + strWidth;
				parent.imageinfo.location.href=place4;
		}
		
	//Function to verify upload information on the file
		function  VerifyUpload(strFile, strTitle) {
			nLen=strTitle.length;
			strFileName=strFile
			//makes sure an image was selected before uploading
			if (strFile == ""){
			alert("Click Browse and Select a file");
			return false;
			}
			//makes sure you entered a description
			if (!nLen) {
				alert("The description field is required.  This becomes the ALT text for the image.");
				return false;
			}
			//checks to make sure the description does not contain characters that may break some javascript
			for (var count=0; count < nLen; count++) {
				cTemp=strTitle.substring(count, count+1);
				if (( cTemp == '"' ) || ( cTemp == "'" ) || ( cTemp == "/" ) || ( cTemp == "\\" )|| ( cTemp == "." )){
					alert("No single quotes, double quotes, periods or slashes are allowed in the description");
					return false;
					}
				} 
				return VerifyFile(strFileName); //go to the next function to verify extensions, file size
			}

		//The following function verifies the extensions, file size
		function VerifyFile(strFile){
			if (strFile == "") { return false; }
			var strEditorName = top.opener.eWebEditPro.event.srcName; //gets the editor's name
			var cStrTemp = strFile;
			var cTemp1 = cStrTemp.split("."); //finds the period in the file name
			cTemp3 = cTemp1[cTemp1.length - 1]; //gets the extension
			var cTemp = cTemp3.toLowerCase(); //converts it to lower case
			var mystring = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyString("ValidExtensions"); //gets the valid extensions specified in the xml data file (config.xml)
			extensions = mystring.split(","); 
			//checks the file extensions against the valid ones
			for (i=0; i<extensions.length; i++) {
				if ( cTemp == extensions[i]) break
			}
			if (i == extensions.length) { //if the extension is not valid, display the alert box
				alert('You have not selected a file with one of the valid extensions: ' + mystring);
				return false;
			}
			
			
		
			//the following verifies that the file has not exceeded the size set in the xml data file.  These methods and objects are documented
				top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("IsLocal", true);					top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("SrcFileLocationName", cStrTemp);
				if ((top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK") > 0) && ((top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("FileSize")/1024) > top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK"))) {
				alert ('The file selected is too large to be uploaded.\nPlease select a different file.\nMaximum file size allowed = ' + top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK") + 'k bytes');
				return false;
			}
			
			document.upload.file_size.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("FileSize");
			document.upload.height.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("ImageHeight");
			document.upload.width.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("ImageWidth");
			return true;
		}
		
	</script>
</head>
<body bgcolor="d3d3d3" leftmargin=0 topmargin=0 marginheight=0 marginwidth=0 onload="self.focus()">
<table cellpadding="0" cellspacing="0" border="0" width="100%" height="99%">
<tr>
	<td valign="top">
		<table cellpadding="3" cellspacing="0" width="100%">
		<% 
		String editorname = request.getParameter("editor"); 
		if(request.getParameter("imagetitle") == null) { 
		%> 
				<form action="imagesave.jsp" method="post" name="upload" onSubmit="return VerifyUpload(this.imagefile.value, this.imagetitle.value);" enctype="multipart/form-data">
				<input type="hidden" name="editor"  value="<%= editorname %>">
				<tr>
					<td><font face="verdana" size="2"><b>Upload a New File:</b></font></td>
				</tr>
				<tr>
					<td><input type="file" name="imagefile" size="15" style="font-family: Verdana;">&nbsp; &nbsp; <input type="button" name="preview" value="Preview" style="font-family: Verdana;" onClick="PreviewFile(document.forms[0].imagefile.value);"></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td><font face="verdana" size="2"><b>Enter a description for the file:</b> </font><br><input type="text" name="imagetitle" size="30" style="font-family: Verdana;"></td>
				</tr>
				<tr><td>&nbsp;</td></tr>

				<tr>
					<td>
					<p align="center"><input type="Submit" value="Upload to Library"></p>
					</td>
				</tr>
				<tr>
					<td><p>&nbsp;</p><font face="verdana" size="2"><a href="helpjspsmartupload.htm" target="_blank">Help</a> with jspSmartUpload</font>
					</td>
				</tr>
			
				<input type="hidden" value="0" name="width">
				<input type="hidden" value="0" name="height">
				<input type="hidden" value="0" name="file_size">
				</form>
		<% } %> 
		</table>
	</td>
</tr>
</table>
</body>
</html>
