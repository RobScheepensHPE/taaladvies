<cfinclude template="ewebeditprodefinedsn2.cfm">

<cfinclude template="#trim(replace(form.editor_media_path, "-", "", "ALL"))#/imagepath.cfm">

<cfset variable.uploadcommand="#form.actiontyp#">
<cfset variable.ErrorNumber="0">
<cfset variable.ErrorDesc="">

<cfif form.actiontyp eq "uploadcontent">


		<html>
		<body>
	
		<cfif form.content_description neq "New"> 
			<H1>Updated Content Received</H1>
			
			<!--- Update the Database and return to index.cfm for the list of documents --->
			<!--- checks to see if the image title entered in the form has either single or double quotes --->
			<!--- """" checks for double quotes in the image title --->
			<cfif '#form.content_title#' CONTAINS "'" OR '#form.content_title#' CONTAINS """">
				<script language="JavaScript">
					alert ('The file name can not contain any single or double quotes')
					history.back();
				</script> 
			</cfif>
			<!--- Update the Database --->
			<CFQUERY NAME="q_gethtmla" DATASOURCE="#DSN#">
				UPDATE 	wysiwyg_tbl
				SET		edit_title = '#Trim(form.content_title)#', edit_html = '#Trim(form.content_text)#'
				WHERE	edit_id = #form.content_description#
			</CFQUERY>
			<!--- re-direct back to the index page --->
			<script language="JavaScript"> 
				window.location.href="index.cfm?n=<cfoutput>#timeformat(now(),'hhmmss')#</cfoutput>";
			</script>
		<cfelse>
			<H1>New Content Received</H1>
			
			<!--- insert the new content to the Database --->
	
			<CFQUERY NAME="q_gethtmlb" DATASOURCE="#DSN#">
				INSERT INTO	wysiwyg_tbl (edit_title, edit_html)
				VALUES	('#Trim(form.content_title)#', '#Trim(form.content_text)#')
			</CFQUERY>
			<!--- re-direct back to the index page --->
			<script language="JavaScript">
				window.location.href="index.cfm?n=<cfoutput>#timeformat(now(),'hhmmss')#</cfoutput>";
			</script>
	
		</cfif>
		
		<p style='color:red'>Click on 'Undo' to restore your content and continue editing.</p>
		<br>
		Content Title:&nbsp;&nbsp;
		<cfoutput>#form.content_title#</cfoutput>
		<br>
		Content Size:&nbsp;&nbsp;
		<cfoutput>#form.content_size#</cfoutput>
		<br>
		Content Description:&nbsp;&nbsp; 
		<cfoutput>#form.content_description#</cfoutput>
		<br>
		
		</body>
		</html>


</cfif>

<cfif form.actiontyp eq "uploadfile">
<CF_ewebeditprouploadfile 
	allowexts="#trim(replace(form.extensions, "-", "", "ALL"))#" 
	destdir="#variable.DestDir#"
	renamefile="Yes" 
	uploadfile="uploadfilephoto" 
	nameconflict="MAKEUNIQUE"
	TempDir="#variable.DestDir#">
<cfif errorlevel>

<cfelse>
 	<CFQUERY NAME="i_media" DATASOURCE="#DSN#">
		INSERT INTO	media_tbl (media_title, media_path, media_filename, media_upload_date, media_filesize, user_name,
					site_id, media_deleted, extension_id, media_width, media_height)
		VALUES	('#uploadedfilename#', '#trim(replace(form.editor_media_path, "-", "", "ALL"))#/', '#uploadedfilename#', #DateFormat(Now(), "MM/DD/YY")#, #trim(replace(fileSize, "-", "", "ALL"))#, 'user name',
				0, 0, 1, 0, 0)
	</CFQUERY>
<XML ID="EktronFileIO">
<?xml version="1.0"?>
<UPLOAD>
  <FILEINFO ID="0" discard="False">
    <FSRC><cfoutput>#trim(original_name)#</cfoutput></FSRC><!--- Original source given by the client should go in here --->
    <FURL><cfoutput>#trim(replace(form.editor_media_path, "-", "", "ALL"))#/#uploadedfilename#</cfoutput></FURL>
    <FID></FID>
    <FSIZE><cfoutput>#form.file_size#</cfoutput></FSIZE>
    <DESC><cfoutput>#form.file_title#</cfoutput></DESC>
    <THUMBURL></THUMBURL>
    <THUMBHREF></THUMBHREF>
    <FTYPE><cfoutput>#form.file_type#</cfoutput></FTYPE>
    <DWIDTH><cfoutput>#form.width#</cfoutput></DWIDTH>
    <DHEIGHT><cfoutput>#form.height#</cfoutput></DHEIGHT>
	<!---
	Note:
		ColdFusion chokes on DBORDER because ColdFusion tags used to start with DB
		so it automatically converts DB to CF which becomes CFORDER which is an invalid tag
	--->	
    <cfoutput><DB</cfoutput>ORDER></DB<cfoutput>ORDER></cfoutput>
    <FRAGMENT></FRAGMENT>
    <FERROR value="0"></FERROR>
  </FILEINFO>
</UPLOAD>
</XML>	
	
	
</cfif>
	
</cfif>

<cfif form.actiontyp neq "uploadcontent">
	<cfif form.actiontyp neq "uploadfile">
	
	<html>
	<body>
	<H1>Content Received</h1>
	<p>Upload command is not recognized.</p>
	<p>It was [ <cfoutput>#form.actiontyp#</cfoutput> ]</p>
	
	</body>
	</html>
	
	</cfif>
</cfif>