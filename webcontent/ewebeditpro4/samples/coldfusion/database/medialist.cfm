<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<!--- Get the list of media on the site  --->

<cfinclude template="ewebeditprodefinedsn2.cfm">
<cfif IsDefined("Button") AND Button eq "Delete">
	<!--- Set an image to deleted, pass the media_id to be deleted --->
	<cfquery name="qd_image" DATASOURCE="#DSN#">
		UPDATE	media_tbl
		SET 	media_deleted = 1
		WHERE	media_id = #media_id# 
	</cfquery>
</cfif>
<CFQUERY NAME="media_onserver" DATASOURCE="#DSN#">
	Select media_id, media_title, media_path, media_filesize, media_width, media_height, media_filename  AS filename
	From media_tbl
	WHERE media_deleted = 0
	Order by media_title
</CFQUERY> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<title>Media List</title>
	<script language="JavaScript">
		function sethiddenid() {
			if ((document.frmItem.media_selected.selectedIndex != -1) && (document.frmItem.media_selected.options[document.frmItem.media_selected.selectedIndex].value != "ignore")) {
				document.frmItem.media_id.value=document.frmItem.media_selected.options[document.frmItem.media_selected.selectedIndex].value;
				return confirm('Are you sure you want to Delete this image?');
			}
			else {
				alert('You need to select an image first');
				return false;
			}
			
		}
		
		<cfif media_onserver.recordcount eq 0>
			<cfset nMedia = 0>
		<cfelse>
			<cfset nMedia = media_onserver.recordcount - 1>
		</cfif>
		
		var MediaID = new Array(<cfoutput>#nMedia#</cfoutput>);
		var MediaList = new Array (<cfoutput>#nMedia#</cfoutput>);
		var ImageAltText = new Array (<cfoutput>#nMedia#</cfoutput>);
		var MediaInfo = new Array (<cfoutput>#nMedia#</cfoutput>);
		<cfset nMedia = 0>
		<cfoutput query="media_onserver"> 
			MediaID[#nMedia#] = #media_onserver.media_id#;MediaList[#nMedia#] = '#media_onserver.media_path##media_onserver.filename#';ImageAltText[#nMedia#]='#UrlEncodedFormat(media_onserver.media_title)#'
			MediaInfo[#nMedia#] = 'filesize=#media_onserver.media_filesize#&height=#media_onserver.media_height#&width=#media_onserver.media_width#'<cfset nMedia = nMedia + 1>
		</cfoutput>
		// when you select an image, show it in the preview frame
		function openit(ch) {
			if (ch != "") {
				var strEditorName = "<cfoutput>#url.editorname#</cfoutput>";
				if (ch.options[ch.selectedIndex].value == "ignore") { return; }
				var place='mediapreview.cfm?media_id=' + ch.options[ch.selectedIndex].value;
				var place2='mediauploader.cfm?editorname=' + strEditorName + '&upload=' + top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyBoolean("AllowUpload");
				var place3='mediainsert.cfm?editorname=<cfoutput>#url.editorname#</cfoutput>&action=server&media_id=' + ch.options[ch.selectedIndex].value;
				var place4='mediainformation.cfm?' + MediaInfo[ch.selectedIndex]

				parent.mediapreview.location.href = place;
				parent.mediauploader.location.href = place2;
				parent.mediainsert.location.href = place3;
				parent.mediainfo.location.href = place4;
				for (i=0; i< <cfoutput>#media_onserver.recordcount#</cfoutput>; i++)	{
					if (MediaID[i] == ch.options[ch.selectedIndex].value)	{
						top.medialist.document.frmItem.filename.value = MediaList[i];
						top.medialist.document.frmItem.media_id.value = MediaID[i];
					}
				}
			}
		}
	</script>
</head>

<body bgcolor="d3d3d3">
<table width="100%" cellpadding="2" cellspacing="2">
<form name="frmItem" onsubmit="return sethiddenid();" action="medialist.cfm?editorname="<cfoutput>#url.editorname#</cfoutput>>
<tr>
	<td><font face="arial" size=2><b>To Select an Existing File:</b></font></td>
</tr>
	<td>
		<select name="media_selected" size="8" onChange="openit(document.frmItem.media_selected);">
		<cfoutput query="media_onserver">
			<option value="#media_onserver.media_id#">#media_onserver.media_title#
		</cfoutput>
			<option value="ignore"><CFOUTPUT>#repeatString("&nbsp;", 20)#</CFOUTPUT>
		</select>
	</td>
</tr>
<tr>
	<td align="center" colspan="2">
		<input type="submit" name="button" value="Delete">
		<input type="hidden" name="media_id" value="">
		<input type="hidden" name="filename" value="">
		<input type="hidden" name="editorname" value='<cfoutput>#url.editorname#</cfoutput>'>
	</td>
</tr>	

</form>
</table>
</body>
</html>