<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<!--- Note that in the images directory needs to be a file imagepath.cfm  --->

<cfinclude template="ewebeditprodefinedsn2.cfm">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Media File Notification</title>
</head>

<body bgcolor="d3d3d3">
	<br>
	<br>
	
	<!--- <script language="JavaScript">alert("<cfoutput>#form.editor_media_path#/imagepath.cfm</cfoutput>");</script> --->
	<cfinclude template="#form.editor_media_path#/imagepath.cfm">
	<CF_ewebeditprouploadfile 
		allowexts="#form.extensions#" 
		destdir="#variable.DestDir#"
		renamefile="Yes" 
		uploadfile="form.uploadfilephoto" 
		nameconflict="MAKEUNIQUE"
		TempDir="#variable.DestDir#">
	<cfif errorlevel>
		<script language="JavaScript">
			alert("Upload Failed");
			top.close();
		</script> 
	<cfelse>
		<CFQUERY NAME="i_media" DATASOURCE="#DSN#">
			INSERT INTO	media_tbl (media_title, media_path, media_filename, media_upload_date, media_filesize, user_name,
						site_id, media_deleted, extension_id, media_width, media_height)
			VALUES	('#form.file_title#', '#form.web_media_path#/', '#uploadedfilename#', '#form.img_date#', #form.file_size#, 'user name',
					0, 0, #form.extension_id#, #form.width#, #form.height#)
		</CFQUERY>
		<script language="JavaScript">
			var strEditorName = "<cfoutput>#url.editorname#</cfoutput>";
			top.opener.eWebEditPro[strEditorName].MediaFile().setProperty("IsLocal", false);
			top.opener.eWebEditPro.instances['<cfoutput>#url.editorname#</cfoutput>'].insertMediaFile('<cfoutput>#form.web_media_path#/#uploadedfilename#</cfoutput>', 0, '<cfoutput>#form.file_title#</cfoutput>','<cfoutput>#form.file_type#</cfoutput>', '<cfoutput>#form.width#</cfoutput>', '<cfoutput>#form.height#</cfoutput>');
			top.close();
		</script> 
	</cfif>
</body>
</html>

