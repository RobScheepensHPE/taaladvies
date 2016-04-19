<% Response.Buffer = TRUE %>
<!-- #include file="functions.asp" -->

<% ' Edit.asp
   ' This page is used to edit existing database content.
   ' The id variable holds the row number of the document
   ' to be edited.  %>

<% ' The g_id variable is requested from the querystring 
   ' and stored for use in the database query
	  Dim g_id, g_strTitleEdit, g_strHtmlEdit, g_strIDEdit
	  g_id = Request.QueryString("id") %>

<% If trim(Request.Form("btnSubmit")) = "Save" Then   
		' Declare variables, and store data from fields in contentEdit.asp
		' All data is checked, using the function SQLFilter, to ensure that
		' it is SQL safe.
 
		g_strTitleEdit = SQLFilter(Request.Form("editTitle"))
		g_strHtmlEdit  = SQLFilter(Request.Form("editHtml"))
		g_strIDEdit    = Request.Form("editID") 
		
		' Creates SQL statement based on above variables and then executes
		' the command   
		UpdateContentInDatabase g_strTitleEdit, g_strHtmlEdit, g_strIDEdit

		' Redirects to main table of contents page when finished
		Response.Redirect("index.asp")
	
	Else

		' Assuming that the g_id is non-empty, the content is 
		' retrieved from the database.  Assuming that the query
		' is non-empty (ie the row exists), the data is displayed
		' using the eWebEditPro object. 
   
		If g_id <> "" Then
		DBConnect "SELECT * FROM wysiwyg_tbl WHERE edit_id = " & g_id
			If Not objRS.EOF Then
				g_strTitleEdit = objRS("edit_title")
				g_strHtmlEdit  = objRS("edit_html")
				g_strIDEdit = g_id %>
			
				<html>
				<head>
				<title>Edit Document</title>
					<!-- #include virtual="/ewebeditpro4/ewebeditpro.asp" -->
				</head>
		
				<body>
					<form name="frmMain" action="edit.asp" method="post">
						<% ' A hidden field containing the g_id is used to 
						   ' to indicate to the posting page exactly which 
						   ' row is to be updated.  %>
						<input type=hidden name="editID" value="<% = g_id %>">
	
						<table border=0 width=100%>
						<tr>
							<td colspan=3>
								<font face="Verdana" size="3">
									<b>Edit Document</b>
								</font>
							</td>
						</tr>
						
						<tr>
							<td>
								<font face="Verdana" size="2">
									Title:
								</font>
							</td>
			
							<td align=left>
								<% ' The existing title is taken from the database and
								   ' displayed in this text field.  %>
								<input type="text" size=50 maxlength=255 name="editTitle" onkeyup="UpdateTitle('editHtml')" onchange="UpdateTitle('editHtml')" value="<% = g_strTitleEdit %>">
							</td>

							<td align=right> 
								<input type=submit name="btnSubmit" value="Save">
								<input type="Button" name="btnCancel" value="Cancel" onclick="eWebEditPro.actionOnUnload=EWEP_ONUNLOAD_NOSAVE; location.href='index.asp';">						
							</td>
						</tr>
									
						<tr>
							<td colspan=3>
								<font face="Verdana" size="2">
									Body:
								</font>
							</td>
						</tr>
						</table>
						
							<% ' The following line creates the an editor object,
							   ' specifying g_strHtmlEdit as the field to copy data into
							   ' once the submit button has been activated.  Notice
							   ' that the existing content, contained in objRS object,
							   ' is copied into that editor.  %>

								<% =eWebEditProEditor("editHtml", "100%", "75%", g_strHtmlEdit) %><br>
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
    									objMedia.AutomaticUpload().setProperty("ContentTitle", "<% = g_strTitleEdit %>");
    									// The description will hold the id value.    
    									objMedia.AutomaticUpload().setProperty("ContentDescription", "<% = g_id %>");
                                    }
								}
								
								// This updates the title value when they change it
								function UpdateTitle(sEditor)
								{
									// Update the title
                                    if(ValidEditorObject(sEditor))
                                    {
									    eWebEditPro.instances[sEditor].editor.MediaFile().AutomaticUpload().setProperty("ContentTitle", 
											window.document.frmMain.editTitle.value);
                                    }
								}
								
								// If we upload then disable the save button, so that we don't save twice.
								eWebEditProExecCommandHandlers["cmdmfuuploadcontent"] = function(sEditorName, strCmdName, strTextData, lData)
								{
									window.document.frmMain.btnSubmit.disabled = true;
									window.document.frmMain.btnCancel.value = "Done";
									
									// Now, disable the command in the editor so that they don't upload the response.
									eWebEditPro.instances[sEditorName].editor.Toolbars().CommandItem("cmdmfuuploadcontent").Enable(false);

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

	<% ' At the end of each page's script portion, the DBClose 
	   ' function ought to be called to close and destroy the
	   ' recordset objects
		DBClose %>
		
	<% End If %>
<% End If %>