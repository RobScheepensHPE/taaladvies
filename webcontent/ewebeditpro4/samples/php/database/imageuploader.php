<?php
	require("globals.php");
	include("ewebeditprofunctions.php");
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Image Uploader</title>
	<script language="JavaScript1.2">
	
	//the following function handles the preview of the and image information of the image upload screen	
			function getname(cStr, title){
			if (VerifyFile(cStr)) //Go to function to verify that it is a valid file
					{ 
				var strFilename=CnvString(cStr); //make sure file path has no spaces for netscape.  go to function to convert them to %20
				var imagePreviewWindow='imagepreview.php?imagename=' + strFilename; // construct the link to preview window.  Will be used below
				
				var strEditorName = top.opener.eWebEditPro.event.srcName;//get editor name
									top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("IsLocal", true); //Tell editor image is local
				top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("SrcFileLocationName", cStr); //tell editor file location
				strFileSize = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("FileSize"); //get file size
				strHeight = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("ImageHeight"); //get image height
				strWidth = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("ImageWidth"); //get image width
				var imageInfoWindow='imageinformation.php?filesize=' +strFileSize + '&height=' + strHeight + '&width=' + strWidth //construct link for image info window
				parent.preview.location.href = imagePreviewWindow; //go to preview window
				parent.imageinfo.location.href=imageInfoWindow; // go to image info window
				}
		}
		
	
	//the following function replaces spaces with %20 for netscape support
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
	
		
	//The following function is used to verify the image before uploading
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
			if (strFile == "") //make sure they selected an image
				{ 
				return false; 
				}
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
			//the following verifies that the file has not exceeded the size set in the xml data file.
			top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("IsLocal", true); //tell editor image is local
			top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("SrcFileLocationName", cStrTemp); //tell editor image location
				document.upload.file_size.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("FileSize"); //set hidden field to file size of image
				document.upload.height.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("ImageHeight"); //set hidden field to height of image
				document.upload.width.value = top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("ImageWidth"); //set hidden field to widht of image
			//make sure we did not exceed file size
			if ((top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK") > 0) && ((top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("FileSize")/1024) > top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK"))) {
				alert ('The file selected is too large to be uploaded.\nPlease select a different file.\nMaximum file size allowed = ' + top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyInteger("MaxFileSizeK") + 'k bytes');
				
				return false;
			}
			return true;
		}
		
	</script>
</head>
<body bgcolor="d3d3d3" leftmargin=0 topmargin=0 marginheight=0 marginwidth=0 onload="self.focus()">
<table cellpadding="0" cellspacing="0" border="0" width="100%" height="99%">
<tr>
	<td valign="top">
		<table cellpadding="3" cellspacing="0" width="100%">
		<?php
			if (!isset($imagefile)) {
			echo "
				<form name=\"upload\" action=\"imageuploader.php?\" method=\"post\" onSubmit=\"return VerifyUpload(this.imagefile.value, this.imagetitle.value);\" enctype=\"multipart/form-data\">
		<input type=\"hidden\" value=\"0\" name=\"file_size\">
		<input type=\"hidden\" value=\"0\" name=\"width\">
		<input type=\"hidden\" value=\"0\" name=\"height\">
			
				<tr>
					<td><font face=\"verdana\" size=\"2\"><b>Select a Local File:</b></font></td>
				</tr>

				<tr>
					<td><input type=\"file\" name=\"imagefile\" size=\"15\" style=\"font-family: Verdana;\">&nbsp;&nbsp<input type=\"Button\" value=\"Preview\" onClick=\"getname(upload.imagefile.value, upload.imagetitle.value);\" style=\"font-family: Verdana;\"></td>
				</tr>

				<tr>
					<td><b>Enter a description for the file</b><br><input type=\"text\" name=\"imagetitle\" size=\"30\" style=\"font-family: Verdana;\"></td>
				</tr>
	
				<tr>
					<td><br>
					<p align=\"center\"><input type=\"Submit\" value=\"Upload to library\"></p>
					</td>
				</tr>
				</form>
			";
			}
			else {
				echo "<tr>\n<td colspan=\"2\"><font face=\"verdana\" size=\"2\">\n";
				eWebEditProEditorImageUpload($gstrDataSourceName, $imagetitle, $imagefile, $imagefile_name, $imagepath, $gstrImageUploadDirectory, $file_size, $width, $height);
			}
		?>
		</table>
	</td>
</tr>
</table>
</body>
</html>
