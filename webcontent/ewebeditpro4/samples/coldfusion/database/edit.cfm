<!-- Ektron Inc.  Amherst NH  Copyright ©1999-2001 -->
<!--
  Name: Edit.cfm
   
  Descr:  A Cold Fusion page to display a editor.  In the case of adding a document, no URL parameter is provided.  
  A blank title text box and a blank editor is display.  In the case of editing a document, a document id is provided 
  as a URL parameter.  The Title text box is populated with the document title and an editor is display with the document 
  content.
	
  Revision Date: 2001-04-06
  
-->
<cfinclude template="ewebeditprodefinedsn2.cfm">
<html>
<head>
<title>eWebEditPro</title>
	<cfset title = "">
	<cfset editorValue = "">
	<script language="JavaScript">
		function PopUpWindow (url, hWind, nWidth, nHeight, nScroll) {
			var cToolBar = "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=" + nScroll + ",resizable=0,width=" + nWidth + ",height=" + nHeight
	  		var popupwin = window.open(url, hWind, cToolBar);
			if (null == popupwin)
			{
				alert("Unable to open pop-up window.  Please turn off your pop-up blocker and try again.");
			}
		}
		
	</script>
	<cfif IsDefined("url.edit_id")> <!--- In the case of document edit, get the document title and document content --->
		<CFQUERY NAME="q_gethtml" DATASOURCE="#DSN#">
			SELECT edit_title, edit_html
			FROM wysiwyg_tbl
			WHERE edit_id = #url.edit_id#
		</CFQUERY>
	</cfif>
</head>
<body>
	<cfform name="frmDocMaint" action="action.cfm" method="post">
		<cfif IsDefined("url.edit_id")> <!--- Display the document title and the document content if url.edit_id is provided --->
			<cfset title="#q_gethtml.edit_title#">
			<cfset editorValue = "#q_gethtml.edit_html#">
			<cfoutput><input type="Hidden" name="edit_id" value="#url.edit_id#"></cfoutput>
		</cfif>
		<font size="3" face="verdana, arial, helvetica"><b>
		<cfif IsDefined("url.edit_id")>Edit<cfelse>Add</cfif> Document</b></font><br>
		<font size="2" face="verdana, arial, helvetica">Title:</font>&nbsp;
		
		<cfoutput>
			<input type="text" maxlength=255 size=50 name="edit_title" 
				value="#variables.title#" 
				onkeyup="UpdateTitle('myContent1')" onchange="UpdateTitle('myContent1')">
		</cfoutput>
		&nbsp;&nbsp;&nbsp;
		<input type="Submit" name="btnSave" value="Save">&nbsp;
		<input type="Button" name="btnCancel" value="Cancel" onclick="eWebEditPro.actionOnUnload=EWEP_ONUNLOAD_NOSAVE; location.href='index.cfm';"><BR>
		<font size="2" face="verdana, arial, helvetica">Body:</font><BR>
		<cfmodule template="../../../eWebEditPro4.cfm" EditorName="myContent1" Width="100%" Height="75%" Value="#variables.editorValue#" >
		<script language="JavaScript1.2">
		<!--
		eWebEditPro.addEventHandler("onready", "initTransferMethod(eWebEditPro.event.srcName, 'mediamanager.cfm', 'mediaautoreceive.cfm')");
		function initTransferMethod(sEditor, strURL, strAutoURL)
		{
			if (ValidEditorObject(sEditor))
			{
				var strTitle;
				var objMedia;
				objMedia = eWebEditPro.instances[sEditor].editor.MediaFile();

				strTitle = window.document.frmDocMaint.edit_title.value;
				
				// The GUI Selection method:
				objMedia.setProperty("TransferMethod", strURL);
				// The Automatic Accept method:
				objMedia.AutomaticUpload().setProperty("TransferMethod", strAutoURL);
				// Information about the Content
				objMedia.AutomaticUpload().setProperty("ContentTitle", strTitle);
				// The description shows that this is a new document 
				// or holds the ID value.
				if(0 == strTitle.length)
				{
					objMedia.AutomaticUpload().setProperty("ContentDescription", "New");
				}
				else
				{
					objMedia.AutomaticUpload().setProperty("ContentDescription", window.document.frmDocMaint.edit_id.value);
				}
				// The type of publication for the upload.    
				objMedia.AutomaticUpload().setProperty("ContentType", "htmlbody");
			}
		}
			
		// This updates the title value when they change it
		function UpdateTitle(sEditor)
		{
			if (ValidEditorObject(sEditor))
			{
				// Update the title
				eWebEditPro.instances[sEditor].editor.MediaFile().AutomaticUpload().setProperty("ContentTitle", 
						window.document.frmDocMaint.edit_title.value);
			}
		}
				
		// If we upload then disable the save button, so that we don't save twice.
		eWebEditProExecCommandHandlers["cmdmfuuploadcontent"] = function(sEditorName, strCmdName, strTextData, lData)
		{
			window.document.frmDocMaint.btnSave.disabled = true;
			window.document.frmDocMaint.btnCancel.value = "Done";
			
			// Now, disable the command in the editor so that they don't upload the response.
			eWebEditPro.instances[sEditorName].editor.Toolbars().CommandItem("cmdmfuuploadcontent").Enable(false);
		}
				
		// If they undo because they want to keep editing, then we can re-enable the upload.
		eWebEditProExecCommandHandlers["cmdundo"] = function(sEditorName, strCmdName, strTextData, lData)
		{
			window.document.frmDocMaint.btnSave.disabled = false;
			window.document.frmDocMaint.btnCancel.value = "Cancel";

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
	</cfform>
</body>
</html>
