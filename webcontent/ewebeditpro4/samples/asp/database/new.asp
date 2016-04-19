<% Response.Buffer = TRUE %>
<!-- #include file="functions.asp" -->

<% ' contentNew.asp
   ' This page displays a form, with the eWebEditorPro object.
   ' The page posts to actionNew.asp, which creates the
   ' database row.  %> 

<%  If Trim(Request.Form("btnSubmit")) = "Save" Then
		' Defines variables and retrieves SQL-safe data from the 
		' form using the SQLFilter function.
			
			dim edit_title, edit_html
			
			edit_title = SQLFilter(Request.Form("edit_title"))
			edit_html  = SQLFilter(Request.Form("edit_html"))

			If edit_title = "" then Response.Redirect("error.asp") 
			
			AddNewContentToDatabase edit_title, edit_html
		
			Response.Redirect("index.asp")
	
	Else %>

	<html>
	<head>
		<title>Create New Document</title>
			<!-- #include virtual="/ewebeditpro4/ewebeditpro.asp" -->
	</head>
		
	<body>
					
			<form name="frmMain" action="new.asp" method="post">
			<table border=0 cellspacing=0 cellpadding=5 width=100%>
			<tr>
				<td colspan=3>
					<font face="Verdana" size="3">
						<b>Create New Document</b>
					</font>
				</td>
			</tr>
			<tr>
				<td>
					<font face="Verdana" size="2">
						Title:
					</font>
				</td>
		
				<td>
					<input type="text" maxlength=255 size=50 name="edit_title" onkeyup="UpdateTitle('edit_html')" onchange="UpdateTitle('edit_html')">
				</td>
				
				<td align=right>
					<input type=submit name="btnSubmit" value="Save">
					<input type="Button" name="btnCancel" value="Cancel" onclick="eWebEditPro.actionOnUnload=EWEP_ONUNLOAD_NOSAVE; location.href='index.asp';">
				</td>
			</tr>
	
			<tr>
				<td valign=top colspan=3> 
					<font face="Verdana" size="2">
						Body
					</font>
			</td>
			</table>
			
				<% ' The following line creates the an editor object, eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE
				   ' specifying edit_html as the field to copy data into
				   ' once the submit button has been activated.  %>
			   
				<% =eWebEditProEditor("edit_html", "100%", "75%", "") %><br>
				<script language="JavaScript1.2">
				<!--
				eWebEditPro.addEventHandler("onready", "initTransferMethod(eWebEditPro.event.srcName, 'mediamanager.asp', 'mediaautoreceive.asp')");
				function initTransferMethod(sEditor, strURL, strAutoURL)
				{
                    if(ValidEditorObject(sEditor))
                    {
    					var objMedia;
    					objMedia = eWebEditPro.instances[sEditor].editor.MediaFile();
    	
    					// The GUI Selection method:
    					objMedia.setProperty("TransferMethod", strURL);
    					// The Automatic Accept method:
    					objMedia.AutomaticUpload().setProperty("TransferMethod", strAutoURL);
    					// Information about the Content
    					//objMedia.AutomaticUpload().setProperty("ContentTitle", "");  // not ready yet
    					// The description shows that this is a new document.
    					objMedia.AutomaticUpload().setProperty("ContentDescription", "New");
                    }
				}
				
				// This updates the title value when they change it
				function UpdateTitle(sEditor)
				{
					// Update the title
                    if(ValidEditorObject(sEditor))
                    {
					    eWebEditPro.instances[sEditor].editor.MediaFile().AutomaticUpload().setProperty("ContentTitle", 
								window.document.frmMain.edit_title.value);
                    }
				}
				
				// If we upload then disable the save button, so that we don't save twice.
				eWebEditProExecCommandHandlers["cmdmfuuploadcontent"] = function(sEditorName, strCmdName, strTextData, lData)
				{
					window.document.frmMain.btnSubmit.disabled = true;
					window.document.frmMain.btnCancel.value = "Done";

					// Now, disable the command in the editor so that they don't upload the response.
					var objMedia;
					objMedia = eWebEditPro.instances[sEditorName].editor.MediaFile();
	
					eWebEditPro.instances[sEditorName].editor.Toolbars().CommandItem("cmdmfuuploadcontent").Enable(false);
					
					// If this is our first post of this content,
					// then determine the created ID value for this content.
					if("New" == objMedia.AutomaticUpload().getPropertyString("ContentDescription"))
					{
						// When the script responds, for this example, it returns the 
						// created ID number for the new content in a "ContentID" tag.
						// We will get the response from the editor, parse the contents
						// of this custom tag, and update the ID value so that we can 
						// update content if there is a subsequent content upload.
						var strResp;
						var idxStart;
						var idxEnd;
						var strNextID;
						
						strResp = eWebEditPro.instances[sEditorName].editor.GetContent("htmlbody");
						idxStart = strResp.indexOf("<ContentID>");
						if(idxStart > 0)
						{
							idxStart += 11;
							idxEnd = strResp.indexOf("</ContentID>");
							if(idxEnd > 0)
							{
								if(idxStart < idxEnd)
								{
									// Get the new ID
									strNextID = strResp.substring(idxStart, idxEnd);
									objMedia.AutomaticUpload().setProperty("ContentDescription", strNextID);
								}
							}
						}
					}
					
					window.status = "The upload of content is complete.";
				}
		
				// If they undo because they want to keep editing, then we can re-enable the upload.
				eWebEditProExecCommandHandlers["cmdundo"] = function(sEditorName, strCmdName, strTextData, lData)
				{
					window.document.frmMain.btnSubmit.disabled = false;
					window.document.frmMain.btnCancel.value = "Cancel";

					// Now, disable the command in the editor so that they don't upload the response.
					objMenus = eWebEditPro.instances[sEditorName].editor.Toolbars().CommandItem("cmdmfuuploadcontent").Enable(true);
				}
                
                // Performs a quick check to ensure that the name is valid
                // and that we are the control object and not a text area field.
                function ValidEditorObject(sEditor)
                {
            		if (typeof eWebEditPro == "object")
            		{
                        if (typeof eWebEditPro.instances[sEditor] == "object")
                        {
                            if (eWebEditPro.instances[sEditor].isEditor())
                            {
                                return(true);
                            }
                        }
            		}
                    return(false);
                }
                
				// -->
				</script>
			</form>
	</body>		
	</html>

<% End If %>