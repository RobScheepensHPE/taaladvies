 <!-- #include file="functions.asp" -->
 <!-- Copyright 2000-2002, Ektron, Inc. -->
<!-- Revision Date: 03-Jul-2002 -->

<!-- Get the list of media on the site  -->

<%
Dim SQL, Record, nMedia, Media_recordcount, objMediaConn, objMediaRS, iLoop
 if trim(Request.Form("btnSubmit")) = "Delete" Then   
 
	If Request.Form("media_id") <> "" Then
		SQL = "DELETE FROM media_tbl WHERE media_id = " & Request.Form("media_id")
		Set objMediaConn = Server.CreateObject("ADODB.Connection")
		objMediaConn.ConnectionString = "DSN=" & dsn
		objMediaConn.Open
		objMediaConn.Execute SQL
		objMediaConn.Close
	end if
		
end if
SQL = "Select media_id, media_title, media_path, media_filesize, media_width, media_height, media_filename AS filename" _
			& " From media_tbl" _
			& " WHERE media_deleted = 0" _
			& " Order by media_title"

Set objMediaConn = Server.CreateObject("ADODB.Connection")
objMediaConn.ConnectionString = "DSN=" & dsn
objMediaConn.Open
Set objMediaRS = Server.CreateObject("ADODB.RecordSet")
objMediaRS.Open SQL, objMediaConn
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<title>Media List</title>
	<script language="JavaScript">
		<%
		Media_recordcount = 0
		if (objMediaRS.EOF = False) then
			while (objMediaRS.EOF = False)
				Media_recordcount = Media_recordcount + 1
				objMediaRS.MoveNext
			Wend
		end if
		%>
	
		var MediaID = new Array(<%=(Media_recordcount)%>);
		var MediaList = new Array (<%=(Media_recordcount)%>);
		var ImageAltText = new Array (<%=(Media_recordcount)%>);
		var MediaInfo = new Array (<%=(Media_recordcount)%>);
		<%
		nMedia = 0
		%>
		<%
		if (Media_recordcount > 0) then
			objMediaRS.MoveFirst
			for nMedia = 0 to Media_recordcount -1
				%>
				MediaID[<%=(nMedia)%>] = <%=(objMediaRS("media_id"))%>;
				MediaList[<%=(nMedia)%>] = '<%=(objMediaRS("media_path"))%><%=(objMediaRS("filename"))%>';
				ImageAltText[<%=(nMedia)%>]='<%=(Replace(objMediaRS("media_title"), " ", "%20"))%>';
				MediaInfo[<%=(nMedia)%>]='filesize=<%=(objMediaRS("media_filesize"))%>&height=<%=(objMediaRS("media_height"))%>&width=<%=(objMediaRS("media_width"))%>';
				<%
				objMediaRS.MoveNext
				%>
			<%
			Next
		end if
		%>
		// when you select an image, show it in the preview frame
		function openit(ch) {
			if (ch != "") {
				var strEditorName = "<%=(Request.QueryString("editorname"))%>"
				if (ch.options[ch.selectedIndex].value == "ignore") { return; }
				var place='mediapreview.asp?media_id=' + ch.options[ch.selectedIndex].value;
				var place2='mediauploader.asp?editorname=' + strEditorName + '&upload=' + top.opener.eWebEditPro[strEditorName].MediaFile().getPropertyBoolean("AllowUpload");
				var place3='mediainsert.asp?editorname=<%=(Request.Querystring("editorname"))%>&action=server&media_id=' + ch.options[ch.selectedIndex].value;
				var place4='mediainformation.asp?' + MediaInfo[ch.selectedIndex]
				parent.mediapreview.location.href = place;
				parent.mediauploader.location.href = place2;
				parent.mediainsert.location.href = place3;
				parent.mediainfo.location.href = place4;
				for (i=0; i< <%=(Media_recordcount)%>; i++)	{
					if (MediaID[i] == ch.options[ch.selectedIndex].value)	{
						top.medialist.document.frmItem.filename.value = MediaList[i];
						top.medialist.document.frmItem.media_id.value = MediaID[i];
					}
				}
			}
		}
	function checkSelect()
	{
		if ((document.frmItem.media_selected.selectedIndex != -1) && (document.frmItem.media_selected.options[document.frmItem.media_selected.selectedIndex].value != "ignore")) {
			document.frmItem.media_id.value=document.frmItem.media_selected.options[document.frmItem.media_selected.selectedIndex].value;
			return confirm('Are you sure you want to Delete this image?');
		}
		else {
			alert('You need to select an image first');
			return false;
		}		
	}
	</script>
</head>

<body bgcolor="d3d3d3">
<table width="100%" cellpadding="2" cellspacing="2">
<form name="frmItem" action='medialist.asp?editorname=<%=(Request.QueryString("editorname"))%>' method="post" onsubmit="return checkSelect()">
<tr>
	<td><font face="arial" size=2><b>To Select an Existing File:</b></font></td>
</tr>
	<td>
		<select name="media_selected" size="8" onChange="openit(document.frmItem.media_selected);">
		<%
		if (Media_recordcount > 0) then
			objMediaRS.MoveFirst
			for Record = 0 to Media_recordcount -1
			%>
				<option value="<%=(objMediaRS("media_id"))%>"><%=(objMediaRS("media_title"))%>
				<%
				objMediaRS.MoveNext
				%>
			<%
			next
		end if
		%>
		<option value="ignore">
		<%
		for iLoop = 1 to 25
			response.write("&nbsp;")
		next
		%>
		</select>
	</td>
</tr>
<tr>
	<td align="center" colspan="2">
		<input type=submit name="btnSubmit" value="Delete">
		<input type="hidden" name="media_id" value="">
		<input type="hidden" name="filename" value="">
		<input type="hidden" name="editorname" value="<%=(Request.QueryString("editorname")) %>">
	</td>
</tr>	
</form>
</table>
<%
objMediaRS.Close
objMediaConn.Close
%>
</body>
</html>